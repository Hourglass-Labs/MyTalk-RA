/*
 * Filename: Switch.java
 * Package: it.hourglass.myTalk.server.WSServer.elaborator
 * Author: Thomas Rossetto 
 * Date: 20130529
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  --------+------------+------------------
 *  0.3		| 2013/07/01 | Bugfix, correzioni di procedure.
 *  --------+------------+------------------
 *  0.2		| 2013/06/05 | Creazione servizi 5-6-7-8-9.
 * ---------+------------+------------------
 *  0.1		| 2013/06/03 | Creazione servizi 1-2-3-4.
 * ---------+------------+------------------
 *  0.0     | 2013/05/29 | Creazione struttura di base.
 *
 * This software is distributed under GNU/GPL 2.0.

*/

package elaborator;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.websocket.WsOutbound;

import exception.*;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/** 
 *  <p>
 *  La classe ha lo scopo di eseguire l’elaborazione corretta dei dati in base al tipo di
messaggio ricevuto.
 *  </p>
 *  @author Thomas Rossetto
 * 
 */

public class Switch {
	private Dialer d = new Dialer();
	
	public Switch(){}
	
	/** 
	 *  <p>
	 *  Metodo utilizzato per scegliere l’elaborazione corretta in base al tipo di messaggio.
		Il parametro data contiene i dati necessari per la corretta elaborazione.
		Le varie elaborazioni sono le seguenti :
 	 *	0 - registration
 	 *	1 - offer
 	 *	2 - answer
   	 *	3 - candidate
 	 *	4 - incoming
 	 *	5 - list
 	 *	6 - bye
 	 *	7 - ping
 	 *	8 - endcall
 	 *	9 - notification
	 *  </p>
	 *  @author Thomas Rossetto
	 * @param type Indica il tipo del JSON
	 * @param data JSON contenente i dati della comunicazione
	 * @param t Riferimento al websocket di chi ha fatto la richiesta di elaborazione 
	 */
	
	public void goSwitch(int type,JSONObject data,WsOutbound t){
		JSONObject json = data;
		switch(type){
		
		case 0:      
			try{
			String nick = json.getString( "nick" );
			d.register(t, nick);
			}catch (JSONException e){
				d.register(t);
			}
			catch(alreadyExist e){}
            
        break;
		case 1:
			JSONObject sdpO = json.getJSONObject("json"); 
			String sdp = sdpO.getString("sdp");
			String ty = sdpO.getString("type");
			String onick = json.getString( "onick" );
			String rnick = json.getString( "rnick" );
            
			try {
				d.sendOffer(sdpO,sdp,ty,onick,rnick);
			} catch (notFound e) {}
        break;
		case 2:
			sdpO = json.getJSONObject("json"); 
			sdp = sdpO.getString("sdp");
			ty = sdpO.getString("type");
			onick = json.getString( "onick" );
			rnick = json.getString( "rnick" );
			try {
				d.sendOffer(sdpO,sdp,ty,onick,rnick);
			} catch (notFound e) {}
        break;
		case 3:
			sdpO = json.getJSONObject("json"); 
			onick = json.getString("onick");
			rnick = json.getString("rnick");
			try {
				d.sendCandidate(sdpO,onick,rnick);
			} catch (notFound e) {}
        break;
		
		case 4:
			onick = json.getString("onick");
			rnick = json.getString("rnick");
            int status = json.getInt("status");
            int stream = 0;
            if(status == 2) stream = json.getInt("stream");
            try {
            	d.sendIncoming(onick,rnick,status,stream);
            } catch (notFound e) {}
        break;
            
		case 5:
			onick = json.getString("onick");
			String list[]= json.getString("list").split(",");
            List<String> l = new ArrayList<String>();
			for(int i=0;i<=list.length-1;i++){
				l.add(list[i]);
            }
            	d.sendList(onick,l);
		break;

		case 6:
			try{
			onick = json.getString("onick");
			d.byeUser(onick);
			}
			catch(NullPointerException e){
			d.byeUser(t);
			}
			;
		break;
		
		case 7:
			//eventuale risposta al ping
		break;
		case 8:
			rnick = json.getString("mynick");
			String info = json.getString("info");
			d.sendEndCall(rnick, info);
		break;
		case 9:
			onick = json.getString("onick");
			rnick = json.getString("rnick");
			int stype = json.getInt("stype");
			d.sendNotification(onick, rnick, stype);
		break;
		}
		
		
	}
	
}
