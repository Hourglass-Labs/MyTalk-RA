/*
 * Filename: ParserOut.java
 * Package: it.hourglass.myTalk.server.WSServer.interf
 * Author: Thomas Rossetto 
 * Date: 20130529
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  --------+------------+------------------
 *  1.0		| 2013/05/30 | Test e inserimento Javadoc.
 * ---------+------------+------------------
 *  0.0     | 2013/05/29 | Creazione struttura.
 *
 * This software is distributed under GNU/GPL 2.0.

*/

package interf;

import java.io.IOException;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.WsOutbound;


/** 
 *  <p>
 *  Questa classe ha lo scopo di assemblare i JSON che verranno inviati poi ai client.
 *  </p>
 *  @author Thomas Rossetto
 * 
 */
public class ParserOut {

	/** 
     *  <p>
     *  Metodo utilizzato per inviare il JSON al client del Web Socket ws.
     *  </p>
     *  @author Thomas Rossetto
     *  @param ws Websocket dove inviare il messaggio.
     *  @param s Stringa da inviare.
     */
	
	public static synchronized void sendToClient(WsOutbound ws, String s)throws IOException{
		CharBuffer buff = CharBuffer.allocate(s.length());
		buff.put(s);
		buff.flip();
		try {
			ws.writeTextMessage(buff);
			ws.flush();
			//System.out.println(s);
		}
		catch (IOException e) {
			
			e.printStackTrace();}
		
	}
	
	/** 
     *  <p>
     *  Metodo utilizzato per inviare un’informazione generica al client del Web Socket
ws.
     *  </p>
     *  @author Thomas Rossetto
     *  @param ws Websocket dove inviare il messaggio.
     *  @param s Stringa da inviare.
     */
	
	public static void sendInfo(WsOutbound ws, String s){
		
		try {
			sendToClient(ws, "{\"type\" :\"info\",\"msg\":\""+s+"\" }");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}