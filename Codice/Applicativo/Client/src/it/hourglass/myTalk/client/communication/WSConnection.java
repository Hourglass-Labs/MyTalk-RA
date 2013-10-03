/*
 * Filename: WSConnection.java
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
 *  0.1		| 2013/05/21 | Creazione metodi
 * ---------+------------+------------------
 *  0.0     | 2013/05/20 | Creazione struttura classe
 *
 * This software is distributed under GNU/GPL 2.0.

*/
package it.hourglass.myTalk.client.communication;

import it.hourglass.myTalk.client.communication.WSMessageBuilder;
import it.hourglass.myTalk.client.communication.Call.CallStateListener;
import it.hourglass.myTalk.client.communication.Call.Direction;
import it.hourglass.myTalk.client.view.EndCallPopup;
import it.hourglass.myTalk.client.view.ErrorPopup;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.wrappers.ConsoleLog;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.media.client.Video;
import de.csenk.gwt.ws.client.WebSocketConnection;
import de.csenk.gwt.ws.client.js.JavaScriptWebSocketFactory;
import de.csenk.gwt.ws.shared.Connection;
import de.csenk.gwt.ws.shared.Handler;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;


/** 
 *  <p>
 *  Questa classe viene utilizzata per la creazione e la gestione della connessione 
 *  al websocket della servlet che si occupa della gestione delle chiamate e degli 
 *  utenti online. Si occupa inoltre della creazione e della gestione della chiamata.
 *  </p>
 *  @author Thomas Rossetto
 *  @version 0.1
 *  
 */

public class WSConnection implements Handler {
	private String url;
	private Connection connection;
	private WSConnectionStateCallback callback;
	private CallMessageCallback callMessagesCallback;
	private String mynick;
	private Call call=null;
	private JSONObject incomingReq=null;
	private boolean session=false;
	private HandlerManager eventBus;
	/**
	 * Unico costruttore della classe WSConnection.
	 * Definisce le variabili url e cb locali per poi passare alla connessione vera e propia tramite il metodo connect().
	 * @author Thomas Rossetto
	 * @param url 	URL che indica dove si trova la servlet
	 * @param cb 	istanza dell'interfaccia callback con i metodi definiti dal chiamante
	 */
	
	public WSConnection(String url, WSConnectionStateCallback cb, HandlerManager eb) {
		this.url = url;
		this.callback = cb;
		this.eventBus=eb;
		connect();
	}
	
	/**
	 * Metodo utilizzato per l'aggiunta di una nuova chiamata su questo WS.
	 * @author Thomas Rossetto
	 * @param callback	viene passato l'oggetto da inserire come riferimento alla chiamata.
	 */
	
	public void addCallMessageCallback(
			CallMessageCallback callback) {
		callMessagesCallback = callback;
	}
	
	/**
	 * Metodo utilizzato per ottenere il campo dati "call" appartenenete alla classe "Call".
	 * @author Thomas Rossetto
	 * 
	 */
	
	public Call getCall() {
		return call;
	}
	
	/**
	 * Metodo utilizzato per la rimozione di una nuova chiamata su questo WS.
	 * @author Thomas Rossetto
	 * 
	 */
	
	public void removeCallMessageCallback() {
		callMessagesCallback=null;
	}
	
	/**
	 * Metodo utilizzato per la creazione del WebSocket vero e proprio assegnandone il riferimento alla 
	 * variabile connection.
	 * @author Thomas Rossetto
	 * 
	 */
	
	public void connect(){
		try{
		connection = new WebSocketConnection(url, this,
				new JavaScriptWebSocketFactory());
		}catch(java.lang.IllegalStateException e){
			ConsoleLog.consoleLog("Impossibile creare una connessione al server, potrebbe essere occupato o in manutenzione, prova ad aggioranre la pagina.");
		}
	}

	/**
	 * Metodo utilizzato per spedire un oggetto tramite WebSocket. 
	 * Viene stampato sulla console del browser il rispettivo oggetto.
	 * @author Thomas Rossetto
	 * @param connection Indica la connesione WS.
	 */
	
	public synchronized void send(Object o) {
		ConsoleLog.consoleLog("C -- > S : "+ o.toString());
		connection.send(o);
	}

	/**
	 * Metodo che viene eseguito appena la comunicazione con il WebSocket inizia.
	 * In questo caso vengono chiamate le istruzioni specificate dal chiamante della classe WSConnection
	 * e poi viene fatta la registrazione al server per essere considerati reperibili.
	 * @author Thomas Rossetto
	 * @param connection Indica la connesione WS.
	 */
	
	@Override
	public void onConnectionOpened(Connection connection) throws Throwable {
		ConsoleLog.consoleLog("Connection opened!");
		if(!session)
		regOnServer();
	}

	/**
	 * Metodo che viene eseguito appena la comunicazione con il WebSocket viene chiusa.
	 * In questo caso vengono chiamate le istruzioni specificate dal chiamante della classe WSConnection.
	 * @author Thomas Rossetto
	 * @param o Oggetto che dev'essere spedito.
	 */
	
	@Override
	public void onConnectionClosed(Connection connection) throws Throwable {
		ConsoleLog.consoleLog("La connessione ﾃｨ stata chiusa");
		//send(WSMessageBuilder.byeMsg(mynick)); 
		//TODO:verificare se dava errore !
		callback.onConnectionClosed();

	}
	
	/**
	 * Metodo utilizzato per rendere evidenza delle eccezzioni, che verranno stampate nella console.
	 * @author Thomas Rossetto
	 * @param o Oggetto che dev'essere spedito.
	 */
	
	@Override
	public void onExceptionCaught(Connection connection, Throwable caught) {
		ConsoleLog.consoleLog("Exception caught on web scoket channel " + caught);
		caught.printStackTrace();

	}

	/**
	 * Metodo utilizzato alla ricezione di un messaggio.
	 * Viene per prima cosa stampato sulla console l'oggetto in arrivo, se si tratta di un oggetto atteso viene proseguita l'elaborazione,
	 * altrimenti viene abortita. La prosecuzione dell'elaborazione dei dati ��ｽｨ affidata al metodo hendleMessage().
	 * @author Thomas Rossetto
	 * @param message Oggetto che viene ricevuto.
	 * 
	 */
	
	@Override
	public void onMessageReceived(Connection connection, Object message)
			throws Throwable {
		JSONObject jso = (JSONObject) JSONParser.parseStrict((String) message);
			ConsoleLog.consoleLog("S --> C : "+(String) message);
		if (!jso.containsKey("type")) {
			return;
		}
		String type = ((JSONString) jso.get("type")).stringValue();
		handleMessage(type,jso);
        
	}

	/**
	 * Metodo utilizzato per fare il parsing del messaggio ricevuto, conoscendone il tipo per la corretta trattazione di quest'ultimo.
	 * Viene controllato che il messaggio sia una risposta da parte del server alla richiesta di registrazione (registration), 
	 * un' informazione da stampare a video(info), l'inizio di una telefonata(incoming con stato 2) oppure il rifiuto di una telefonata.
	 * Se non si tratta di nessuna di queste il messaggio viene passato al metodo chiamante onMessageReceived() definito
	 * dal chiamante di WSConnection.
	 * @author Thomas Rossetto
	 * @param type Tipo del messaggio ricevuto in ingresso.
	 * @param jso JSON contenente i dati del messaggio ricevuto.
	 * 
	 */
	
	private void handleMessage(String type, JSONObject jso) {
		
		if(type.equals("notification")){
			callback.onCallUnrelatedMessage(type,jso);
		}
		else if(type.equals("error")){
			new ErrorPopup(eventBus, "Si verificato il seguente errore: "+((JSONString)jso.get("error")).stringValue());
		}
		else if(type.equals("registration")){
			mynick= ((JSONString) jso.get("id")).stringValue();
			ConsoleLog.consoleLog("Il mio nick è : "+mynick);
			callback.onCallUnrelatedMessage(type,jso);
		}
		else if(type.equals("info")){
			ConsoleLog.consoleLog(jso.get("msg").toString());
			//new PopupMessage(jso.get("msg").toString());
		}
		else if(type.equals("endcall")){
			closeCall(Direction.IN);
		}
		else if(type.equals("incoming") && (int)((JSONNumber) jso.get("status")).doubleValue()!=1){
			ConsoleLog.consoleLog(type);
			if((int)((JSONNumber) jso.get("status")).doubleValue()==2){
				if(call!=null){
					send(WSMessageBuilder.declineCallMsg(((JSONString)jso.get("offer")).stringValue(),mynick));
				}
				else{
					incomingReq= jso;
					callback.onCallUnrelatedMessage(type,jso);
				}
			}
			else {
				if ((int)((JSONNumber) jso.get("status")).doubleValue()==0){
				new ErrorPopup(eventBus,"Il destinatario ha rifiutato la telefonata");
				}
			}
		}
		else {
			ConsoleLog.consoleLog(type);
			callMessagesCallback.onMessageReceived(type, jso);
			}
		}
		
	/**
	 * Metodo utilizzato per far richiesta di registrazione al server.
	 * @author Thomas Rossetto
	 * 
	 */

	public void regOnServer(){
		if(mynick!=null)
		connection.send(WSMessageBuilder.byeMsg(mynick));
		connection.send(WSMessageBuilder.registrationMsg(null));
	}
	
	public void regOnServer(String s){
		if(mynick!=null)
		connection.send(WSMessageBuilder.byeMsg(mynick));
		connection.send(WSMessageBuilder.registrationMsg(s));
	}
	

	/**
	 * Metodo utilizzato per la creazione di un istanza di Call in uscita.
	 * @author Thomas Rossetto
	 * @param direction Indica la direzione della chiamata OUT in uscita, IN in entrata
	 * @param callnick Il nickname di chi sta iniziando la conversazione
	 * @param localVideo Riferimento all'elemento video locale, dove si vedr��ｿｽlo stream locale.
	 * @param remoteVideo Riferimento all'elemento video remoto, dove si vedr��ｿｽlo stream remoto.
	 */
	
	public void doCall(Call.Direction direction,String callnick, Video localVideo,Video remoteVideo, TextArea textachat,int s, TextBox calltLenght, TextBox callByteUp, TextBox callByteDown) {
		ConsoleLog.consoleLog("docall! ");
		CallStateListener listener = new CallStateListener() {

			@Override
			public void onCallTerminate(String cause) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onCallStarted() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCallProceding() {
				// TODO Auto-generated method stub

			}
		};
		
		this.call = new Call(direction, this,localVideo,remoteVideo,listener, callnick,textachat,s,  calltLenght, callByteUp, callByteDown);
		
	}
	
	/**
	 * Metodo utilizzato per la creazione di un istanza di Call, ovvero la chiamata vera e propria.
	 * In questo caso 
	 * @author Thomas Rossetto
	 * @param localVideo Riferimento all'elemento video locale, dove si vedr��ｿｽlo stream locale.
	 * @param remoteVideo Riferimento all'elemento video remoto, dove si vedr��ｿｽlo stream remoto.
	 */
	
	public void doCall(Video localStream,Video remoteVideo, TextArea textachat, TextBox calltLenght, TextBox callByteUp, TextBox callByteDown, Label nickRec) {
		nickRec.setText("Sei in chiamata con "+((JSONString)incomingReq.get("offer")).stringValue());
		doCall(Direction.IN,((JSONString)incomingReq.get("offer")).stringValue(),localStream,remoteVideo,textachat,(int)((JSONNumber)incomingReq.get("stream")).doubleValue(), calltLenght,callByteUp, callByteDown);

	}
	
	/**
	 * Metodo che ritorna il JSON incoming salvato in precedenza .
	 * @author Thomas Rossetto
	 */
	
	public JSONObject getIncomingJson(){
		return incomingReq;
	}
	
	/**
	 * Metodo utilizzato per pulire la variabile incomingReq.
	 * @author Thomas Rossetto
	 */
	
	public void clearIncomingJson(){
		incomingReq=null;
	}

	/**
	 * Metodo utilizzato per ritornare il nickname a me assegnato.
	 * @author Thomas Rossetto
	 */
	
	public String getMyNick(){
		return mynick;
	}
	
	public void refuseCall(){
		send(WSMessageBuilder.declineCallMsg(((JSONString)incomingReq.get("offer")).stringValue(), mynick));
	}
	
	public interface WSConnectionStateCallback {
		void onConnectionClosed();

		void onConnectionOpened();

		void onCallUnrelatedMessage(String type,JSONObject jso);
	}
	
	public interface CallMessageCallback {
		void onMessageReceived(String type, JSONObject jso);
	}
	
	public void setSession(boolean s){
		session = s;
	}
	
	
	
	public void closeCall(Call.Direction d){
		String time=call.hangup(d);
		String bytes=call.getCallBytes();
		call=null;
		new EndCallPopup(eventBus, time, bytes);
	}

	
	
}



