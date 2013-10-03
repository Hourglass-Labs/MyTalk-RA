package exception;

import java.io.IOException;

import interf.ParserOut;

import org.apache.catalina.websocket.WsOutbound;

@SuppressWarnings("serial")
public class notFound extends Throwable{
	
	public notFound(WsOutbound ws){
		try {
			ParserOut.sendToClient(ws, "{\"type\":\"error\", \"error\":\"utente non trovato\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
