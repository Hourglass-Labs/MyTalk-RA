
/*
 * Filename: DialerTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Paolo Bustreo
 * Date: 2013/08/10
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.4     | 2013/07/5  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/07/03 | Aggiunta seconda parte Test 
 *  ---------+------------+------------------
 *  0.2    	|  2013/07/02 | Aggiunta prima parte Test
 * ---------+------------+------------------
 *  0.1     | 2013/07/01 |Codifica regTest()
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 
 */

package it.hourglass.myTalk.test;
import interf.*;


import static org.junit.Assert.*;

import exception.*;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.junit.Test;
import org.mockito.Mockito;
import java.util.List;
import java.nio.CharBuffer;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import org.apache.catalina.websocket.WsOutbound;
import elaborator.Dialer;
public class DialerTest {

	@Test
	public final void regTest() {
		Dialer d=new Dialer();
		WsOutbound ws=mock(WsOutbound.class);
		d.getReg().put("TestNick",ws);
		try{ d.register(ws, "TestNick"); fail("Test Fallito");} catch(alreadyExist al){assert(true);}
   }
	
	@Test
	public final void regTest2() throws alreadyExist {
		Dialer d=new Dialer();
		WsOutbound ws=mock(WsOutbound.class);
		d.getReg().put("TestNick1",ws);
		try{d.register(ws, "TestNick2");}catch(NullPointerException ee){assert(true);}
   }
	@Test	
	public final void byeUser1Test() {
		Dialer d=new Dialer();
		WsOutbound ws=mock(WsOutbound.class);
		d.byeUser(ws);
		Collection<WsOutbound> v = d.getReg().values();
		if(!v.contains(ws)){assert(true);}
   }
	@Test	
	public final void byeUser2Test() {
		Dialer d=new Dialer();
		WsOutbound ws=mock(WsOutbound.class);
		
		d.sendList("test", new ArrayList<String>());
		d.byeUser(ws, "test");
		Collection<WsOutbound> v = d.getReg().values();
		if(!v.contains(ws)){assert(true);}
   }
	
	@Test
	public final void sendNotificationOnTest() throws notFound {
		Dialer d=new Dialer();
		List<String> lista = new ArrayList<String>();
		lista.add("test");
		lista.add("test1");
		d.regUserFriends.put("test", lista);
		d.sendNotificationOn("test");
		d.sendNotificationOff("test");
		assert(true);
	}
	
	@Test
	public final void sendOfferTest() throws notFound {
		Dialer d=new Dialer();
		JSONObject object=(JSONObject) JSONSerializer.toJSON("{'onick': 'pippo', 'list': 'list'}");
		try{d.sendOffer(object, "c", "s", "s", "s");}catch(NullPointerException ee){assert(true);}
	}
	
	
	@Test
	public final void sendCandidateTest() throws notFound {
		Dialer d=new Dialer();
		WsOutbound ws=mock(WsOutbound.class);
		d.regUserWs.put("test",ws);
		JSONObject object=(JSONObject) JSONSerializer.toJSON("{'onick': 'pippo', 'list': 'list'}");
		d.sendCandidate(object, "test", "test");
	}
	
	@Test
	public final void getFriendStatusTest() throws notFound {
		WsOutbound ws=mock(WsOutbound.class);
		Dialer d=new Dialer();
		 List<String> lista = new ArrayList<String>();
		 lista.add("test");
		d.getFriendStatus(ws, lista);
	}
}
