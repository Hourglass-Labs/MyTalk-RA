/*
 * Filename: Dialer.java
 * Package: it.hourglass.myTalk.server.WSServer.elaborator
 * Author: Thomas Rossetto 
 * Date: 20130527
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  --------+------------+------------------
 *  0.3		| 2013/09/02 | Bugfix, e correzioni varie.
 *  --------+------------+------------------
 *  0.2		| 2013/07/08 | Creazione metododi per servire le richieste di switch 5-6-7-8-9.
 * ---------+------------+------------------
 *  0.1		| 2013/07/01 | Creazione metododi per servire le richieste di switch 1-2-3-4.
 * ---------+------------+------------------
 *  0.0     | 2013/06/29 | Creazione struttura di base.
 *
 * This software is distributed under GNU/GPL 2.0.

*/
package elaborator;

import interf.ParserOut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.WsOutbound;

import exception.*;

/** 
 *  <p>
La classe ha lo scopo di eseguire l’elaborazione dei dati e di produrre un output o un
servizio atteso.
 *  </p>
 *  @author Thomas Rossetto
 * 
 */

class Dialer {
	private HashMap<String, List<String>> regUserFriends = new HashMap <String, List<String>>();
	private HashMap<String, WsOutbound> regUserWs = new HashMap <String, WsOutbound>();
	
	
	Dialer(){
		
	}

	/** 
	 *  <p>
	Metodo utilizzato per elaborare la richiesta di registrazione presso il server. Viene
creato un id temporaneo di 6 caratteri che verrà utilizzato come nick temporaneo
per l’utente che ha inoltrato la richiesta.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param t mappa su cui fare la ricerca inversa
	 *  @param o Oggetto di cui si vuole avere la chiave.
	 * 
	 */	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List reverseSearch(HashMap<String, WsOutbound> t, Object w){
		boolean f=false;
		Set ref= t.keySet();
		Iterator it = ref.iterator();
		List list = new ArrayList();
		while (it.hasNext() && !f){
			Object o = it.next();
			if(t.get(o).equals(w)){
				list.add(o);
			}
		}
		return list;
	}
	
	/** 
	 *  <p>
	Metodo utilizzato per elaborare la richiesta di registrazione presso il server. Viene
creato un id temporaneo di 6 caratteri che verrà utilizzato come nick temporaneo
per l’utente che ha inoltrato la richiesta.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param ws Websocket di chi ha fatto la richiesta di elaborazione
	 * 
	 */
	
	synchronized void register(WsOutbound ws){
		boolean ok = false;
		String id = null;
		while(!ok){
			UUID random = UUID.randomUUID();
			id = random.toString().substring(0, 6);
			System.out.println(id);
			if(regUserWs.get(id)==null){
				ok=true;	
			}
		}	
		regUserWs.put(id,ws);	
		showTable();
		ParserOut.sendInfo(ws, "Registration successful with temporary nickname : " + id+"");
		
		try {
			ParserOut.sendToClient(ws, "{\"type\" :\"registration\",\"id\":\""+id+"\"}");
			System.out.println("Registration successful with temporary nickname : " + id+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 *  <p>
	Metodo utilizzato per elaborare la richiesta di registrazione presso il server. L’utente
viene registrato con il nickname da lui comunicato. Una volta registrato
sul server gli viene notificato chi è online.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param ws Websocket di chi ha fatto la richiesta di elaborazione
	 *  @parma s Nome specifico con cui registarsi sul server
	 * 
	 */
	
	synchronized void register(WsOutbound ws, String s) throws alreadyExist{
		/*if(regUserWs.get(s)!=null){
			throw new alreadyExist(ws);
		}*/
		regUserWs.put(s,ws);	
		showTable();
		ParserOut.sendInfo(ws, "Registration successful with selected nickname : " + s+"");
		try {
			ParserOut.sendToClient(ws, "{\"type\" :\"registration\",\"id\":\""+s+"\"}");
			System.out.println("Registration successful with temporary nickname : " + s+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> n = sendNotificationOn(s);
		getFriendStatus(ws,n);
	}
	
	/** 
	 *  <p>
	Metodo utilizzato per inviare all’utente r l’offerta di chiamata inviata da o. Viene
sollevata un eccezione nel caso il ricevente non sia registrato presso il server.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param obj Blocco di informazioni utili alla libreria webRTC.
	 *  @param sdp Contiene l'sdp della chiamante.
	 *  @param type Tipo della richiesta: Domanda o risposta.
	 *  @param o Nome dell'offerente. 
	 *  @param r Nome del ricevente.
	 * 
	 */
	
	void sendOffer(JSONObject obj,String sdp,String type, String o, String r) throws notFound{
		sdp= sdp.replaceAll("\r", "\\r)").replaceAll("\n","\\n");
		WsOutbound t=regUserWs.get(r);
		if(t==null){
			throw new notFound(regUserWs.get(o));
			}
		else{
		try {
			System.out.println("Sdp offer "+o +" --> "+ r +" \n");
			ParserOut.sendToClient(t, "{\"sdp\":"+obj+",\"nick\":\""+o+"\",\"type\":\""+type+"\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    t=regUserWs.get(o);
		ParserOut.sendInfo(t, "Offer send.");
		}
	}
	
	/** 
	 *  <p>
		Metodo utilizzato per inviare una richiesta di inizio chiamata.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param obj Blocco di informazioni utili alla libreria webRTC.
	 *  @param o Nome dell'offerente. 
	 *  @param r Nome del ricevente.
	 * 
	 */
	
	void sendCandidate(JSONObject obj, String o,String r) throws notFound{
		WsOutbound t=regUserWs.get(r);
		if(t==null){
		throw new notFound(regUserWs.get(o));
		}
		else{	
			try{
		ParserOut.sendToClient(t, "{\"json\":"+obj+",\"nick\":\""+o+"\",\"type\":\"candidate\"}");
		System.out.println("Sdp candidate "+o +" --> "+ r +" \n");
			}
			catch(IOException e){
				sendIncoming(r, o, 0,0);
			}
		
		}
	}
	
	/** 
	 *  <p>
		Metodo utilizzato per inviare messaggi di tipo Incoming da o a r. Se l’incoming
		è di accettazione(s=2) allora viene inviato anche il tipo della chiamata con str,
		altrimenti viene inviata senza.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param o Nome dell'offerente. 
	 *  @param r Nome del ricevente.
	 *  @param s Status, indica se è la richiesta o la risposta negativa(1) o positiva(2).
	 * 
	 */
	
	void sendIncoming(String o, String r, int s,int str) throws notFound{
		WsOutbound t=regUserWs.get(r);
		if(t==null){
			throw new notFound(regUserWs.get(o));
		}
		else{
		
		try {
			if(s==2){
				ParserOut.sendToClient(t, "{\"offer\":\""+o+"\",\"type\":\"incoming\", \"status\":"+s+", \"stream\":"+str+"}");
			}
			else{
				ParserOut.sendToClient(t, "{\"offer\":\""+o+"\",\"type\":\"incoming\", \"status\":"+s+" }");
			}
			System.out.println("Sdp incoming "+o +" --> "+ r +" \n");
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}
	
	/** 
	 *  <p>
		Metodo utilizzato per rimuovere un utente tramite il suo nickname dalla tabella
degli utenti registrati. Una volta eliminato (se l’utente è registrato) viene
notificato a tutti i suoi contatti l’avvenuta disconnessione.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param s Nome dell'utente che si è sconnesso.
	 * 
	 */
	
	synchronized void byeUser(String s){
			regUserWs.remove(s);
			if(s.length()>6)
			sendNotificationOff(s);
			showTable();
		}
	
	/** 
	 *  <p>
		Metodo utilizzato per rimuovere un utente tramite il riferimento al suo web socket 
		dalla tabella degli utenti registrati. Una volta eliminato (se l’utente è registrato)
	viene notificato a tutti i suoi contatti l’avvenuta disconnessione.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param n Websocket dell'utente che si è sconnesso.
	 * 
	 */
	
	synchronized void byeUser(WsOutbound n){
		String k = (String)reverseSearch(regUserWs, n).get(0);
		byeUser(k);	
		}

	
	public void showTable(){
		System.out.println("\n*Utenti autenticati nel sistema*\n" + regUserWs.toString() + "\n*Amicizie degli utenti autenticati*\n" + regUserFriends.toString() );
	}
	
	/** 
	 *  <p>
		Metodo utilizzato per associare una lista di contatti ad un nickname tramite
l’aggiunta di list nella lista regUserFriends.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param mynick Nickname del possessore della lista amici.
	 *  @param list Lista amici.
	 * 
	 */
	
	public void sendList(String mynick, List<String> list){
		regUserFriends.put(mynick, list);
	}
	
	/** 
	 *  <p>
		Metodo utilizzato per sapere lo stato di tutti i contatti online.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param ws Websocket del possessore della lista amici.
	 *  @param l Lista amici.
	 * 
	 */
	
	private void getFriendStatus(WsOutbound ws, List<String> l){
		for(int i=0;i<=l.size()-1;i++){
			try {
				ParserOut.sendToClient(ws,"{\"nick\":\""+l.get(i)+"\",\"status\":1, \"type\":\"notification\" }");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("*Invio stato amici.");
		}
		
	}
	
	/** 
	 *  <p>
		Metodo utilizzato per notificare a tutti i contatti dell’utente n il suo stato attivo.
Il metodo ritorna una lista di contatti online.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param n Nickname dell'utente di cui bisogna dare notifica.
	 * 
	 * 
	 */
	
	private List<String> sendNotificationOn(String n){
		List<String> l = regUserFriends.get(n);
		List<String> listOn= new ArrayList<String>();
		List<String> listOff= new ArrayList<String>();
		for(int i=0;i<= l.size()-1;i++){
			try{
			ParserOut.sendToClient(regUserWs.get(l.get(i)),"{\"nick\":\""+n+"\",\"status\":1, \"type\":\"notification\" }");
				listOn.add(l.get(i));
				System.out.println("*"+n+":1");
			}
			catch(NullPointerException e){
					listOff.add(l.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		//if(listOn.size()>=listOff.size()) return listOn;
		//else return listOff;
		return listOn;
	}
	
	/** 
	 *  <p>
		Metodo utilizzato per notificare a tutti i contatti dell’utente n il suo stato offline.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param n Nickname dell'utente di cui bisogna dare notifica.
	 * 
	 * 
	 */
	
	private void sendNotificationOff(String n){
		List<String> l = regUserFriends.get(n);
		for(int i=0;i<= l.size()-1;i++){
			try{
			ParserOut.sendToClient(regUserWs.get(l.get(i)),"{\"nick\":\""+n+"\",\"status\":0, \"type\":\"notification\" }");
			System.out.println("*"+n+":0");
			}
			catch(NullPointerException e){
			} catch (IOException e) {
				// TODO Auto-generated catch block
				byeUser(l.get(i));
			}
		}

	}
	
	/** 
	 *  <p>
		Metodo utilizzato per informare l’utente nick della fine della chiamata. Info può
contenere informazioni aggiuntive sul termine di quest’ultima.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param n Nickname dell'utente a cui notificare la fine della chiamata.
	 *  @param info Informazioni aggiuntive nel caso di qualche errore.
	 * 
	 * 
	 */
	
	public void sendEndCall(String nick, String info ){
		WsOutbound ws = regUserWs.get(nick);
		try {
			ParserOut.sendToClient(ws,"{\"type\":\"endcall\" }");
			System.out.println("*Fine chiamata a "+nick);
			if(info.length()>0)
				ParserOut.sendInfo(ws, info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 *  <p>
		Metodo utilizzato per inviare vari tipi di notifiche a r. Il tipo della notifica viene
identificato dalla variabile i.
	 *  </p>
	 *  @author Thomas Rossetto
	 *  @param o Nome dell'offerente. 
	 *  @param r Nome del ricevente.
	 *  @param i Indica la tipologia di notifica: 
	 *  1 - Richiesta amicizia 
	 *  2 - Conferma amicizia
	 *  3 - Invio Messaggio
	 *  4 - Cancellazione amicizia
	 * 
	 */
	
	public void sendNotification (String o, String r, int i){
		WsOutbound ws = regUserWs.get(r);
		String msg="";
		if(i==1){
			System.out.println("Richiesta amicizia");
		}
		else if(i==2){
			System.out.println("Conferma amicizia");
			if(regUserWs.get(o)==null)
				msg+=", \"stat\":0";
			else msg+=", \"stat\":1";
			regUserFriends.get(o).add(r);
			regUserFriends.get(r).add(o);
		}
		else if (i==3){
			System.out.println("Invio messaggio");
		}
		else if(i==4){
			System.out.println("Cancellazione amicizia");
			regUserFriends.get(o).remove(r);
			regUserFriends.get(r).remove(o);
		}
		try {
			if(i==2){
				ParserOut.sendToClient(regUserWs.get(o),"{\"type\":\"notification\" , \"sender\":\""+r+"\", \"stype\":"+i+" "+msg+" }");
			}
			ParserOut.sendToClient(ws,"{\"type\":\"notification\" , \"sender\":\""+o+"\", \"stype\":"+i+" "+msg+" }");
			ParserOut.sendToClient(regUserWs.get(o),"{\"nick\":\""+r+"\",\"status\":1, \"type\":\"notification\" }");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e2){
			System.out.println("Chi ha richiesto l'amiciza è offline");
			try {
				ParserOut.sendToClient(regUserWs.get(o),"{\"nick\":\""+r+"\",\"status\":0, \"type\":\"notification\" }");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
