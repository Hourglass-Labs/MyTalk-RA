/*
 * Filename: Call.java
 * Package: it.hourglass.myTalk.client
 * Author: Thomas Rossetto 
 * Date: 20130527
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  --------+------------+------------------
 *  0.2		| 2013/05/27 | Introduzione del javadoc e pulizia generale del codice
 *  --------+------------+------------------
 *  0.1		| 2013/05/23 | Creazione metodi
 * ---------+------------+------------------
 *  0.0     | 2013/05/20 | Creazione struttura classe
 *
 * This software is distributed under GNU/GPL 2.0.

*/
package it.hourglass.myTalk.client.communication;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import it.hourglass.myTalk.client.communication.WSMessageBuilder;
import it.hourglass.myTalk.client.communication.WSConnection.CallMessageCallback;
import it.hourglass.myTalk.client.wrappers.ConsoleLog;
import it.hourglass.myTalk.client.wrappers.GetUserMediaUtils;
import it.hourglass.myTalk.client.wrappers.MediaStream;
import it.hourglass.myTalk.client.wrappers.PeerConnectionCallbacks;
import it.hourglass.myTalk.client.wrappers.PeerConnectionWrapper;
import it.hourglass.myTalk.client.wrappers.RTCConfiguration;
import it.hourglass.myTalk.client.wrappers.RTCSessionDescription;
import it.hourglass.myTalk.client.wrappers.SDPCreateOfferCallback;
import it.hourglass.myTalk.client.wrappers.SDPOfferMediaConstraints;
import it.hourglass.myTalk.client.wrappers.mozRTCSessionDescription;

/** 
 *  <p>
 *  Questa classe è utilizzata per implementare la chiamata  in tutte le sue forme e combinazioni di stream
 *  </p>
 *  @author Thomas Rossetto
 *  @version 0.1
 *  
 */


public class Call implements PeerConnectionCallbacks, CallMessageCallback{
	private PeerConnectionWrapper pc;
	private Video remoteVideoElement,localVideoElement;
	private WSConnection wsConnection;
	private String callId;
	private Direction direction;
	private CallStateListener callStateListener;
	private String mynick= null;
	private MediaStream localStream = null;
	private boolean audio,video,chat;
	private TextArea TextAreaChat;
	private long callTime= 0;
	private String callBytes="";
	private TextBox callByteU=null;
	private TextBox calltLenght= null;
	private TextBox callByteD= null;
	private boolean open=true;
	private SDPCreateOfferCallback impl = new SDPCreateOfferCallback(){
		/** 
		 *  <p>
		 *  Metodo utilizzato se l'sdp ottenuto è valido.
		 *  L'sdp viene impostato come descrizione locale, e viene preparato il messaggio da mandare come risposta.
		 *  </p>
		 *  @author Thomas Rossetto
		 *  @param sdp Sdp valido ottenuto.
		 */
		
		@Override
		public void RTCSessionDescriptionCallback(RTCSessionDescription sdp) {
			ConsoleLog.consoleLog("Imposto SDP locale.");
			ConsoleLog.consoleDebug(sdp);
			pc.setLocalDescription(sdp);
			wsConnection.send(WSMessageBuilder.relayMsg(callId,mynick, new JSONObject(sdp)));				
		}
		
		@Override
		public void mozRTCSessionDescriptionCallback(mozRTCSessionDescription sdp) {
			ConsoleLog.consoleLog("Imposto SDP locale.");
			ConsoleLog.consoleDebug(sdp);
			pc.setLocalDescription(sdp);
			wsConnection.send(WSMessageBuilder.relayMsg(callId,mynick, new JSONObject(sdp)));				
		}
		/** 
		 *  <p>
		 *  Metodo utilizzato se l'sdp ottenuto non è valido.
		 *  Viene visualizzato un messaggio di errore e la chiamata si interrompe.
		 *  </p>
		 *  @author Thomas Rossetto
		 *  @param error Descrizione dell'errore
		 */
		
		@Override
		public void RTCPeerConnectionErrorCallback(JavaScriptObject error) {
			wsConnection.send(WSMessageBuilder.endCallMsg(callId,error.toString()));
			ConsoleLog.consoleLog("Got error while getting local SDP");
			ConsoleLog.consoleDebug(error);
			Call.this.callStateListener.onCallTerminate("Error geting local SDP");
			
		}
	};
	
	/** 
	 *  <p>
	 *  Unico costruttore di Call. 
	 *  Per prima cosa vengono inizializzate tutte le variabili necessarie. 
	 *  Si passa poi alla configurazione della PeerConnection, indicando il server STUN a cui 
	 *  richiedere le informazioni per la propria raggiungibilità, per poi creare un fine un'istanza di PeerConnection.
	 *  Come ultima cosa,in base alla direzione della comunicazione, viene o fatta partire chiamata passando il JSON di incoming arrivato
	 *  precedentemente, oppure viene richieso l'accesso alle proprio risorse audio/video.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  
	 *  
	 */
	
	public Call(Direction direction,WSConnection wsConnection,Video localVideoElement,
			Video remoteVideoElement,CallStateListener listener,String callId,
			TextArea textachat, int n, TextBox calltLenght, TextBox callByteUp, TextBox callByteDw) {
		setStream(n);
		ConsoleLog.consoleLog("Inizio creazione della chiamata...");
		
		this.callId = callId;
		this.mynick=wsConnection.getMyNick();
		this.wsConnection=wsConnection;
		this.remoteVideoElement=remoteVideoElement;
		this.localVideoElement=localVideoElement;
		this.direction=direction;
		this.callStateListener=listener;
		ConsoleLog.consoleLog(audio +" " +video);
		this.TextAreaChat=textachat;
		this.calltLenght=calltLenght;
		this.callByteU=callByteUp;
		this.callByteD=callByteDw;
		
		RTCConfiguration conf = new RTCConfiguration(
				new String[] { "stun:stun.l.google.com:19302" });
		pc = new PeerConnectionWrapper(conf, this, chat);
		
		if(direction.equals(Direction.IN)){
			handle_incoming(wsConnection.getIncomingJson());
		}
		else{
			if(getStream()!=4)
			getUserMedia();
			else connecting();
		}
	}

	private void setStream(int s){
		if(s==1) { 
			audio=true;
			video=chat=false;
		}
		else if (s==2){
			audio=video=true;
			chat=false;
		}
		else if(s==3){
			audio=video=chat=true;
		}
		else{
			audio=video=false;
			chat=true;
		}
	}
	
	private int getStream(){
		int i;
		if(audio && !video && !chat) i=1;
		else if (audio && video && !chat) i=2;
		else if (audio && video && chat) i=3;
		else i=4;

		return i;
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per la creazione del messaggio ICECandidate e per l'invio di quest'ultimo al server.
	 *  Fa l'Override del metodo icecandidate() di PeerConnectionCallback
	 *  </p>
	 *  @author Thomas Rossetto
	 *  
	 */
	
	@Override
	public void onicecandidate(JavaScriptObject jso) {
		JSONObject o = new JSONObject(jso);
		if (o.containsKey("candidate") && !(o.get("candidate") instanceof JSONNull)){
			JSONObject candidate = (JSONObject) o.get("candidate");
			JSONObject toRelay= new JSONObject();
			toRelay.put("type", new JSONString("candidate"));
			toRelay.put("label", (JSONNumber)(candidate.get("sdpMLineIndex")));
			toRelay.put("id", (JSONString)(candidate.get("sdpMid")));
			toRelay.put("candidate", (JSONString)(candidate.get("candidate")));
			wsConnection.send(WSMessageBuilder.relayMsg(callId,mynick, toRelay));
		}
		
	}


	@Override
	public void onconnecting(JavaScriptObject jso) {
		
	}


	@Override
	public void onopen(JavaScriptObject jso) {
		
	}

	/** 
	 *  <p>
	 *  Metodo utilizzato per l'aggiunta di uno stream quando la conversazione è stata accettata da entrambe le parti.
	 *  Viene catturato lo stream e viene generato un URL per connettere l'elemento video con quest'ultimo.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param jso Oggetto javascript contenente lo stream.
	 */

	@Override
	public void onaddstream(JavaScriptObject jso) {
		callStateListener.onCallStarted();//TODO:da eliminare !
		JavaScriptObject stream  = ((JSONObject)(new JSONObject(jso)).get("stream")).getJavaScriptObject();
		String mediaBlobUrl = ConsoleLog.createMediaObjectBlobUrl(stream);
		remoteVideoElement.setSrc(mediaBlobUrl);
	}

	/** 
	 *  <p>
	 *  Metodo utilizzato quando lo stream viene rimosso. 
	 *  Viene mandato un messaggio all'interlocutore per avvisarlo che la chiamata è finita.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  
	 */
	
	@Override
	public void onremovestream(JavaScriptObject jso) { //TODO: chiusura della chiamata
		wsConnection.send(WSMessageBuilder.endCallMsg(callId,""));
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato quando il datachannel passa allo stato "open". 
	 *  Vengono effettuate due stampe nella console JavaScript del browser che riportano questo avvenimento.
	 *  </p>
	 *  @author Paolo Bustreo
	 *  
	 */
	
	@Override
	public void onchannelopen(JavaScriptObject jso) { 
		ConsoleLog.consoleLog("CHANNEL APERTO");
		ConsoleLog.consoleLog(pc.getState());
		
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato quando vi è un messaggio della chat in arrivo.
	 *  Viene riportato l'evento nella console Javascript del browser e viene stampato nella TextArea riservata alla chat.
	 *  </p>
	 *  @author Paolo Bustreo
	 *  
	 */
	
	@Override
	public void onchannelmessage(JavaScriptObject jso) { 
		ConsoleLog.consoleLog("MESSAGGIO RICEVUTO");
		String lastChat=TextAreaChat.getText().toString();
		TextAreaChat.setText(lastChat.concat(getMessage(jso)).toString());
		
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato quando il datachannel viene chiuso.
	 *  Viene riportato l'evento nella console Javascript del browser.
	 *  </p>
	 *  @author Paolo Bustreo
	 *  
	 */
	
	@Override
	public void onchannelclose(JavaScriptObject jso) { 
		ConsoleLog.consoleLog("CHANNEL CHIUSO");
		
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per inviare messaggi con la chat.
	 *  Utilizza il metodo "sendMessage"attraverso l'oggetto pc della classe PeerConnectionWrapper.
	 *  </p>
	 *  @author Paolo Bustreo
	 *  
	 */
	
	public void sendMessage(String str) { 
		pc.sendMessage(str);
		
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per ottenere dall'oggetto JavaScript il messaggio di testo.
	 *  Utilizza il metodo "getMessage"attraverso l'oggetto pc della classe PeerConnectionWrapper.
	 *  </p>
	 *  @author Paolo Bustreo
	 *  
	 */
	
	public String getMessage(JavaScriptObject jso) { 
		return pc.getMessage(jso);
		
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per smistare i messaggi in arrivo.
	 *  Vengono gestiti i messaggi di offerta(offer), risposta(answer), ICECandidate(candidate) e di ricezione chiamata(incoming).
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param type Tipo del messaggio ricevuto
	 *  @param jso Dati contenuti nel messaggio ricevuto
	 */
	
	public void onMessageReceived(String type , JSONObject jso) {
	
		if (type.equals("offer")) {
			handle_offer(jso);
		}
		else if (type.equals("answer")) {
			handle_answer(jso);
		}
		else if (type.equals("candidate")) {
			handle_candidate(jso);
		}
		else if (type.equals("incoming")){
			handle_incoming(jso);
		}
		
	}
		
	/** 
	 *  <p>
	 *  Metodo utilizzato per elaborare il messaggio di offerta.
	 *  Viene per prima cosa estratto l'sdp dell'offerente e inserito in RemoteDescription.
	 *  Poi viene creata la risposta. 
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param jso Dati contenuti nel messaggio ricevuto
	 */
	
	private void handle_offer(JSONObject jso) {
		JSONObject sdp = (JSONObject) jso.get("sdp");
		pc.setRemoteDescription(sdp.getJavaScriptObject());
		pc.createAnswer(new SDPOfferMediaConstraints(audio, video), impl);
	
	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato quando si riceve un messaggio di risposta(answer).
	 *  Viene impostato l'sdp remoto.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param jso JSON contenente i dati del messaggio ricevuto	 
	 */
	
	private void handle_answer(JSONObject jso){
		ConsoleLog.consoleLog(pc.getState());
		JSONObject sdp = (JSONObject) jso.get("sdp");
		ConsoleLog.consoleLog("Remote SDP");
		ConsoleLog.consoleDebug(sdp.getJavaScriptObject());
		pc.setRemoteDescription(sdp.getJavaScriptObject());

	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato quando si riceve un messaggio di risposta(answer).
	 *  Viene impostato l'sdp remoto.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param jso JSON contenente i dati del messaggio ricevuto.	 
	 */
	
	private void handle_candidate(JSONObject jso){
		JSONObject json = (JSONObject)jso.get("json");
		JSONObject con = new JSONObject();
		con.put("sdpMLineIndex", (JSONNumber)json.get("label"));
		con.put("candidate", (JSONString)json.get("candidate"));
		ConsoleLog.consoleLog("json -->>> "+ con.toString());
		pc.addIceCandidate(con.getJavaScriptObject());

	}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato quando si riceve un messaggio di richiesta chiamata(incoming).
	 *  Il metodo gestisce l'accettazione della chiamata e la risposta positiva del ricevente dell'offerta.
	 *  Viene impostato l'sdp remoto.
	 *  Viene salvata l'ora ed avviato un Timer che aggiorna le statistiche riguardo il tempo di chiamata,
	 *  latenza e bytes ricevuti.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param jso JSON contenente i dati del messaggio ricevuto.	 
	 */
	
	private void handle_incoming(JSONObject jso){
		ConsoleLog.consoleLog(jso.toString());
		callTime=System.currentTimeMillis();
		Timer t = new Timer() {
		      public void run() {
		    	  try {
		    	  if(open){
		    	  long minuti=((System.currentTimeMillis()-getCallTime())/1000)/60;
		    	  long secondi=((System.currentTimeMillis()-getCallTime())/1000)%60;
		    	  String time= String.valueOf(minuti).concat(":").concat(String.valueOf(secondi));
		    	  callBytes=pc.getBytesU();
		    	  calltLenght.setText(time);
		    	  callByteU.setText(String.valueOf(pc.getBytesU()));
		    	  callByteD.setText(String.valueOf(pc.getBytesD()));
		    	  }
		    	  } catch(Exception e){}
		      }
		    };
		    t.scheduleRepeating(5000);
		if(((int)(((JSONNumber)jso.get("status")).doubleValue()))==2){
				/* TODO: da riscrivere !
				 * faccio partire la cam, se accettato viene mandato json positivo, altrimenti negativo
				*/
				 if(getStream()!=4)
				getUserMedia();
				 else connecting();
			//s==1
			//else{
			//	wsConnection.send(WSMessageBuilder.declineCallMsg(mynick, callId));
			//}
		}//stutus==2
		else{
				handle_b_party_confirmation();

		}
	}

	/** 
	 *  <p>
	 *  Metodo utilizzato quando si riceve un messaggio di fine chiamata.
	 *  Il metodo pone fine alla chiamata, impostando a null il video remoto.
	 *  </p>
	 *  @author Thomas Rossetto
	 * 
	 */
	

	
	public String hangup(Direction d) {
		if(d.equals(Direction.OUT))
		wsConnection.send(WSMessageBuilder.endCallMsg(callId,mynick));
		pc.close();
		remoteVideoElement.setSrc("null");
		localVideoElement.setSrc("");
		localStream.stop();
		long minuti,secondi;
		if(getCallTime()==0){
			minuti=0;
			secondi=0;
		}
		else{
		minuti=((System.currentTimeMillis()-getCallTime())/1000)/60;
		secondi=((System.currentTimeMillis()-getCallTime())/1000)%60;
		}
  	  	open=false;
  	  	return String.valueOf(minuti).concat(":").concat(String.valueOf(secondi));
	}

/*
	private void handle_b_party_confirmation_timeout() {
		callStateListener.onCallTerminate("Remote didn't accept our call, timeout");
		
	}
*/


	/** 
	 *  <p>
	 *  Meteodo invocato quando la chiamata viene accettata.
	 *  Viene aggiunto lo stream alla PeerConnection e poi viene creata l'offerta da inviare.
	 *  </p>
	 *  @author Thomas Rossetto
	 * 
	 */
	
	private void handle_b_party_confirmation() {
		
		ConsoleLog.consoleLog("B-party confirmed our call, lets go!");
		ConsoleLog.consoleLog(pc.getState()); 
		if(audio || video)
		pc.addStream(localStream);
		pc.createOffer(new SDPOfferMediaConstraints(audio, video), impl);
		ConsoleLog.consoleLog(pc.getState());
		Timer t = new Timer() {
		      public void run() {
		    	  if(getCallTime()!=0) {
		    		  try {
		    	  long minuti=((System.currentTimeMillis()-getCallTime())/1000)/60;
		    	  long secondi=((System.currentTimeMillis()-getCallTime())/1000)%60;
		    	  String time= String.valueOf(minuti).concat(":").concat(String.valueOf(secondi));
		    	  callBytes=pc.getBytesU();
		    	  calltLenght.setText(time);
		    	  callByteU.setText(String.valueOf(pc.getBytesU()));
		    	  callByteD.setText(String.valueOf(pc.getBytesD()));
		    		  } catch(Exception c){}
		    	  }
		      }
		    };
		    t.scheduleRepeating(5000);
	}
	
	/** 
	 *  <p>
	 *  Meteodo invocato quando si vuole ottenere lo stream audio/video locale.
	 *  Viene richiesto il permesso di poter accedere allo stream audio/video locale.
	 *  Se tale accesso è autorizzato viene per prima cosa collegato lo stram locale con l'elemento video preposto a riceverlo,
	 *  viene poi aggiunto un riferimento della chiamata al MessageCallback di WSConnection.
	 *  Se la chiamata è in uscita verrà inviato un messaggio di incoming al richiedente, mentre nel caso fosse 
	 *  in entrata verrà inviato un messaggio di conferma all'offerente.
	 *  Nel caso in cui non sia possibile accedere allo stream e la chiamata sia in uscita verrà stampato nella console
	 *  del browser il motivo del fallimento, mentre se la chiamata è in entrata verrà mandato un messaggio di rifiuto della chiamata
	 *  all'offerente.
	 *  </p>
	 *  @author Thomas Rossetto
	 * 
	 */
	
	public void getUserMedia() {
		GetUserMediaUtils.getUserMedia(audio, video,
				new GetUserMediaUtils.GetUserMediaCallback() {

					@Override
					public void navigatorUserMediaSuccessCallback(MediaStream mediaStream) {
						localVideoElement.addSource(mediaStream.createMediaObjectBlobUrl());
						localStream = mediaStream;
						wsConnection.addCallMessageCallback(Call.this);
						
						if (direction.equals(Direction.OUT)) {
							callStateListener.onCallProceding();
							wsConnection.send(WSMessageBuilder.callMsg(callId,mynick,getStream()));
						}
						else{
							pc.addStream(localStream);
							ConsoleLog.consoleLog("Add UserMedia to stream");
							wsConnection.send(WSMessageBuilder.confirmCallMsg( callId,mynick));
						}

					}

					@Override
					public void navigatorUserMediaErrorCallback(
							JavaScriptObject error) {
						ConsoleLog.consoleLog("Errore in fase di accesso alo stream: [CODE " + error.toString() + "]");
						if(direction.equals(Direction.IN)){
						wsConnection.send(WSMessageBuilder.declineCallMsg(callId,mynick));
						}
					}
					
				});
	}
	
	private void connecting(){
		wsConnection.addCallMessageCallback(Call.this);
		if (direction.equals(Direction.OUT))
		wsConnection.send(WSMessageBuilder.callMsg(callId,mynick,getStream()));
		else{
		wsConnection.send(WSMessageBuilder.confirmCallMsg( callId,mynick));
		}
	}
	
	public interface CallStateListener {
		void onCallTerminate(String cause);
		void onCallStarted();
		void onCallProceding();
	}
	
	public enum Direction {
		IN,OUT
	}
	
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per elaborare il messaggio di offerta.
	 *  Viene per prima cosa estratto l'sdp dell'offerente e inserito in RemoteDescription.
	 *  Poi viene creata la risposta. 
	 *  </p>
	 *  @author Paolo Bustreo
	 */
	public long getCallTime() {
		return callTime;
	}
	
	public String getCallBytes() {
		return callBytes;
	}
	
	
}
