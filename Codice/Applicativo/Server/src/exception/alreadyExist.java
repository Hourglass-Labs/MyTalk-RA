package exception;

import java.io.IOException;

import interf.ParserOut;

import org.apache.catalina.websocket.WsOutbound;

@SuppressWarnings("serial")
public class alreadyExist extends Throwable {

	public alreadyExist(WsOutbound ws){
		try {
			ParserOut.sendToClient(ws, "{\"type\":\"error\", \"error\":\"Esiste già un utente registrato con lo stesso nome, cambialo!\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
