/*
 * Filename: ParserIn.java
 * Package: it.hourglass.myTalk.server.WSServer.interf
 * Author: Thomas Rossetto 
 * Date: 20130520
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  --------+------------+------------------
 *  1.0		| 2013/05/30 | Inserimento javadoc.
 *  --------+------------+------------------
 *  0.1		| 2013/05/24 | Creazione mappa dei tipi e metodi per l'analisi dei JSON ricevuti.
 * ---------+------------+------------------
 *  0.0     | 2013/05/20 | Creazione struttura servlet con tentativi triviali di comunicazione.
 *
 * This software is distributed under GNU/GPL 2.0.

*/

package interf;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.HashMap;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import elaborator.Switch;



/** 
 *  <p>
 *  Questa classe ha lo scopo di aprire un Web Socket e di interpretare correttamente i
JSON in ingresso inoltrando successivamente le informazioni alla classe preposta.
 *  </p>
 *  @author Thomas Rossetto
 * 
 */

public class ParserIn extends WebSocketServlet {
    private static final long serialVersionUID = 1L;
    private Switch one=new Switch(); //TODO: singleton !
    @SuppressWarnings("serial")
	private HashMap<String, Integer> typeID = new HashMap <String, Integer>() {{ put("registration",0);put("offer", 1); put("answer", 2); put("candidate", 3);put("incoming", 4);put("list", 5);put("bye", 6);put("ping", 7); put("endcall", 8); put("notification", 9);}};
    
    
    /** 
     *  <p>
     *  Questa classe ha lo scopo di definire un Web Socket e tutti i suoi metodi correlati.
     *  </p>
     *  @author Thomas Rossetto
     * 
     */
    
    private class MyMessageInbound extends MessageInbound{
    	WsOutbound myoutbound;
   	 
    	/** 
         *  <p>
         *  Metodo utilizzato quando viene creato ilWeb Socket. Assegna l’istanza a myoutbound.
         *  </p>
         *  @author Thomas Rossetto
         *  @param outbound L'istanza del websocket.
         */
    	
        @Override
        public void onOpen(WsOutbound outbound){
                System.out.println("Open Client.");
                this.myoutbound = outbound;
        }
 
        
        /** 
         *  <p>
         *  Metodo utilizzato quando viene chiuso il Web Socket. Viene inviato a switch un
messaggio per avvisare della disconnessione del Web Socket e quindi del client ad
esso collegato.
         *  </p>
         *  @author Thomas Rossetto
         * 
         */
        
        @SuppressWarnings("unused")
		public void onClose(){
            System.out.println("Close Client.");
            one.goSwitch(6,null,myoutbound);
        }
 
        /** 
         *  <p>
         *  Metodo utilizzato quando viene ricevuto un messaggio testuale. Viene individuato
il tipo di messaggio ed estratti i dati che esso conteneva per poi essere inviati
al metodo goSwitch() dell’istanza switch di Switch.
         *  </p>
         *  @author Thomas Rossetto
         *  @param cb Buffer da analizzare.
         */
        
        @Override
        public void onTextMessage(CharBuffer cb) throws IOException{
            //System.out.println("Accept Message : "+ cb);
            String jsonTxt = cb.toString();
            JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt );        
            String type = json.getString( "type" );
            JSONObject data = json.getJSONObject( "data" );
            one.goSwitch(ParserIn.this.getIDType(type),data,myoutbound);
        }
 
        
        @Override
        public void onBinaryMessage(ByteBuffer bb) throws IOException{
        }
    }
    
    /** 
     *  <p>
     *  E’ un metodo implementato dell’interfaccia WebSocketServlet. Viene invocato
quando viene richiesto un nuovo Web Socket. Il suo scopo è creare un’istanza di
MyMessageInbound.
     *  </p>
     *  @author Thomas Rossetto
     * 
     */
    
    @Override
    protected StreamInbound createWebSocketInbound(String string, HttpServletRequest hsr) {
        return new MyMessageInbound() {};
    }

    private int getIDType(String type){
    	int t = (Integer)typeID.get(type);
    	return t;	
    }
    
}
