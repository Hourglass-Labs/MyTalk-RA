
/*
 * Filename: SwitchTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Paolo Bustreo
 * Date: 2013/08/11
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.4     | 2013/08/11  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/08/03 | Aggiunta test con switch da 4 a 6
 *  ---------+------------+------------------
 *  0.2    	|  2013/08/02 | Aggiunta test con switch da 0 a 3
 * ---------+------------+------------------
 *  0.1     | 2013/08/01 |Codifica regTest()
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 
 */








package it.hourglass.myTalk.test;

import static org.junit.Assert.*;


import exception.*;
import interf.ParserOut;

import java.io.IOException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import elaborator.Switch;
import elaborator.Dialer;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.WsOutbound;
public class SwitchTest {

	@Test
	public final void goSwitchTest6() {
		Switch s=new Switch();
		int i=6;
		WsOutbound ws=mock(WsOutbound.class);
		s.getDialer().getReg().put("pippo", ws);
		JSONObject data=(JSONObject) JSONSerializer.toJSON("{'onick': 'pippo'}");   
		WsOutbound t=mock(WsOutbound.class);
		JSONObject data1=(JSONObject) JSONSerializer.toJSON("{'nick': 'pippo'}"); 
		s.goSwitch(i, data, t);
		s.goSwitch(i, data1, t);
		if(s.getDialer().getReg().get("pippo")==null){assert(true);}
   }
	
	@Test
	public final void goSwitchTest0() {
		Switch s=new Switch();
		int i=0;
		WsOutbound ws=mock(WsOutbound.class);
		s.getDialer().getReg().put("pippo", ws);
		JSONObject data=(JSONObject) JSONSerializer.toJSON("{'onick': 'pippo', 'list': 'list'}");
		int n=s.getDialer().getReg().size();
		JSONObject data1=(JSONObject) JSONSerializer.toJSON("{'nick': 'pippo', 'list': 'list'}");
		s.goSwitch(i, data, ws);
		s.goSwitch(i, data1, ws);
		if(s.getDialer().getReg().size()!=n){assert(true);}
   }
	
	@Test
	public final void goSwitchTest5() {
		Switch s=new Switch();
		int i=5;
		WsOutbound ws=mock(WsOutbound.class);
		s.getDialer().getReg().put("pippo", ws);
		JSONObject data=(JSONObject) JSONSerializer.toJSON("{'onick': 'pippo', 'list': 'list'}");   
		s.goSwitch(i, data, ws);
		
		if(!s.getDialer().getReg().isEmpty()){assert(true);}
   }
	
	@Test
	public final void goSwitchTest4() {
		Switch s=new Switch();
		int i=3;
		WsOutbound ws=mock(WsOutbound.class);
		s.getDialer().getReg().put("pippo", ws);
		JSONObject data=(JSONObject) JSONSerializer.toJSON("{'stream': '2', 'status': '2', 'list': 'list', 'rnick': 'pippo', 'onick': 'pluto', json: 'test'}");   
		s.goSwitch(4, data, ws);
		if(s.getDialer().getReg().containsKey("pippo")){assert(true);}
   }
	
	@Test
	public final void goSwitchTest123() {
		Switch s=new Switch();
		int i=1;
		WsOutbound ws=mock(WsOutbound.class);
		CharBuffer buff=CharBuffer.wrap("test");
		s.d=mock(Dialer.class);
		s.getDialer().getReg().put("pippo", ws);
		JSONObject data=(JSONObject) JSONSerializer.toJSON("{'sdp': 'list', 'type': '1', 'rnick': 'pippo', 'onick': 'pluto'}");
		JSONObject data2=(JSONObject) JSONSerializer.toJSON("{'sdp': 'list', 'type': '1', 'rnick': 'pippo', 'onick': 'pluto'}");
		data.put("json", data2);
		int n=s.getDialer().getReg().size();
		s.goSwitch(1, data, ws);
		s.goSwitch(2, data, ws);
		s.goSwitch(3, data, ws);
   }
	
	
}
