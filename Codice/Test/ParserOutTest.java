
/*
 * Filename: ParserOutTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Paolo Bustreo
 * Date: 2013/08/09
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.2     | 2013/08/09  | Approvazione Test
 * ---------+------------+------------------
 *  0.1     | 2013/08/08 |Codifica sendToClientTest()
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 
 */


package it.hourglass.myTalk.test;
import interf.ParserOut;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.IOException;
import org.apache.catalina.websocket.WsOutbound;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserOutTest {

	
	@Test
	public final void sendToClientTest() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		ParserOut po= new ParserOut();
		WsOutbound ws=mock(WsOutbound.class);
		String STest= "";
		try {
		po.sendToClient(ws, STest);
		assertEquals("\n", outContent.toString());
		}catch (IOException e) {e.printStackTrace();}
	}
}
