/*
 * Filename: WSMessageBuild.java
 * Package: it.hourglass.myTalk.client
 * Author: Thomas Rossetto 
 * Date: 20130520
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  --------+------------+------------------
 *  0.1		| 2013/05/21 | Creazione metodi
 * ---------+------------+------------------
 *  0.0     | 2013/05/20 | Creazione struttura classe
 *
 * This software is distributed under GNU/GPL 2.0.

*/
package it.hourglass.myTalk.client.communication;



import it.hourglass.myTalk.client.wrappers.ConsoleLog;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/** 
 *  <p>
 *  Questa classe viene utilizzata per la creazione e composizione dei JSON da inviare alla servlet 
 *  per lo scambio di informazioni e per la richiesta di servizi.
 *  </p>
 *  @author Thomas Rossetto
 *  @version 0.1
 *  @
 */
public class WSMessageBuilder {
	public static String registrationMsg(String nickName) {
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("registration"));
		JSONObject data = new JSONObject();
		if(nickName != null){
			data.put("nick", new JSONString(nickName));
		}
		jso.put("data", data);
		return jso.toString();
	}
	
	/**
	 * Si occupa della creazione del JSON di inizio chiamata. Questo viene solitamente creato all'inizio 
	 * della comunicazione tra due utenti
	 * @author Thomas Rossetto
	 * @param callee 	Id univoco che identifica l'utente a cui arriverà la richiesta.
	 * @param mynick 	Id univoco dell'utente che effettua la chiamata.
	 * @return Ritorna il JSON atto a far partire la richiesta di chiamata.
	 * 
	 */
	
	public static String callMsg(String callee, String mynick,int s) {
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("incoming"));
		JSONObject data = new JSONObject();
		data.put("onick", new JSONString(mynick));
		data.put("rnick", new JSONString(callee));
		data.put("status", new JSONNumber(2));
		data.put("stream", new JSONNumber(s));
		jso.put("data", data);
		return jso.toString();
	}

	/**
	 * Si occupa della creazione del JSON di conferma della chiamata.
	 * Questo viene solitamente creato quando l'utente ricevente acconsente all'inizio della chiamata.
	 * @author Thomas Rossetto
	 * @param callee 	Id univoco che identifica l'utente a cui arriverà la richiesta.
	 * @param mynick 	Id univoco dell'utente che effettua la chiamata.
	 * @return Ritorna il JSON atto a dare riposta positiva alla richiesta di chiamata.
	 * 
	 */
	
	public static String confirmCallMsg(String callee, String mynick) {
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("incoming"));
		JSONObject data = new JSONObject();
		data.put("onick", new JSONString(mynick));
		data.put("rnick", new JSONString(callee));
		data.put("status", new JSONNumber(1));
		jso.put("data", data);
		return jso.toString();
	}
	
	/**
	 * Si occupa della creazione del JSON di negazione della chiamata.
	 * Questo viene solitamente creato quando l'utente ricevente rifiuta l'inizio della chiamata.
	 * @author Thomas Rossetto
	 * @param callee 	Id univoco che identifica l'utente a cui arriverà la richiesta.
	 * @param mynick 	Id univoco dell'utente che effettua la chiamata.
	 * @return Ritorna il JSON atto a dare riposta negativa alla richiesta di chiamata.
	 * 
	 */
	
	public static String declineCallMsg(String callee, String mynick) {
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("incoming"));
		JSONObject data = new JSONObject();
		data.put("onick", new JSONString(mynick));
		data.put("rnick", new JSONString(callee));
		data.put("status", new JSONNumber(0));
		jso.put("data", data);
		return jso.toString();
	}
	
	
	public static String endCallMsg(String callid,String info) {//TODO:da ridefinire, fine chiamata
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("endcall"));
		JSONObject data = new JSONObject();
		data.put("mynick", new JSONString(callid));
		data.put("info", new JSONString(info));
		jso.put("data", data);
		return jso.toString();
	}

	
	/**
	 * Si occupa della creazione del JSON contenete le informazioni utili alle librerie webRTC.
	 * Possono essere scambiati tre tipi di messaggi aventi la medesima struttura, ovvero l'offerta dell'sdp 
	 * da parte di chi inizia la chiamata (offer), la risposta di quest'ultimo con il suo sdp (answer) e lo 
	 * delle informazioni sullo stream (candidate)
	 * Questo viene solitamente creato quando l'utente ricevente rifiuta l'inizio della chiamata.
	 * @author Thomas Rossetto
	 * @param callee 	Id univoco che identifica l'utente a cui arriverà la richiesta.
	 * @param mynick 	Id univoco dell'utente che effettua la chiamata.
	 * @return Ritorna il JSON atto ad infiare l'informazione necessaria al corretto funzionamento di webRTC.
	 * 
	 */
	
	public static String relayMsg(String callee ,String mynick,JSONObject toRelay ) {
		JSONObject jso = new JSONObject();
		/*if(((JSONString)toRelay.get("type")).stringValue().equals("candidate"))//TODO: da eliminare una volta finito !!
		jso.put("type", new JSONString("candidate"));
		else jso.put("type", new JSONString("offer"));
		*/
		jso.put("type",(JSONString)toRelay.get("type"));
		JSONObject data = new JSONObject();
		data.put("onick", new JSONString(mynick));
		data.put("rnick", new JSONString(callee));
		data.put("json",toRelay);
		jso.put("data",data);
		return jso.toString();
	}
	
	public static String byeMsg(String mynick) {
		JSONObject jso = new JSONObject();
		jso.put("type",new JSONString("bye"));
		JSONObject data = new JSONObject();
		data.put("onick",new JSONString(mynick));
		jso.put("data",data);
		return jso.toString();
	}
	
	public static String contactList(String mynick ,String[] l ) {
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("list"));
		JSONObject data = new JSONObject();
		data.put("onick", new JSONString(mynick));
		String s=new String("");
		for(int i=0;i<l.length;i++){
			if(i!=0) s+=",";
			s+= l[i];
		}
		data.put("list", new JSONString(s));
		jso.put("data",data);
		return jso.toString();
	}
	
	public static String sendNotificationFriendRequest(String callee,String mynick) {
		return sendNotificationMsg(callee,mynick,1);
	}
	
	public static String sendNotificationAcceptFriendRequest(String callee,String mynick) {
		return sendNotificationMsg(callee,mynick,2);
	}
	
	public static String sendNotificationMsgSend(String callee,String mynick) {
		return sendNotificationMsg(callee,mynick,3);
	}
	
	public static String sendNotificationDeleteFriend(String callee,String mynick) {
		return sendNotificationMsg(callee,mynick,4);
	}
	
	
	private static String sendNotificationMsg(String callee,String mynick,int i  ) {
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("notification"));
		JSONObject data = new JSONObject();
		data.put("onick", new JSONString(mynick));
		data.put("rnick", new JSONString(callee));
		data.put("stype", new JSONNumber(i));
		jso.put("data",data);
		return jso.toString();
	}
	
	public static String Ping() {
		JSONObject jso = new JSONObject();
		jso.put("type", new JSONString("ping"));
		return jso.toString();
	}
	
	
}