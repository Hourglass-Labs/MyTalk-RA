/*
 * Filename: ProfileTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Paolo Bustreo
 * Date: 2013/08/29
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.3     | 2013/08/29 | Approvazione Test
 *  ---------+------------+------------------
 *  0.2	| 2013/08/28 | Codifica SignInTest()
 * ---------+------------+------------------
 *  0.1     | 2013/08/27 |Codifica friendTest() e MessageTest()
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 
 */


package it.hourglass.myTalk.test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.shared.Friendships;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.SignIn;
import it.hourglass.myTalk.client.shared.User;

import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.runner.RunWith;
import java.util.ArrayList;

@RunWith(GwtMockitoTestRunner.class)
public class ProfileTest {
	
	@GwtMock WSConnection ws;
	@GwtMock Message msg;

	@Test
	public final void FriendTest()  {
		Profile pf=Profile.getInstance();
		WSConnection ws=mock(WSConnection.class);
		Date d=new Date();
		User usTest= new User("test", "test","test", "test", "test", d, 'M');
		User usTest2=new User("test1", "test1", "test1", "test1", "test1", "test1", "test1", d, 'M', "test1","test1","test1", "test1", "test1", "test1", false);
		//Testing banale di metodi get e set
		List<String> uc=new ArrayList<String>();
		Friendships fr=new Friendships("test1", "test2");
		Friendships fr1=new Friendships("test3", "test4", true);
		fr.getAccepted(); fr.getFriend1(); fr.getFriend2(); fr.getFriendshipId(); fr.setAccepted(true);
		fr.setFriendshipId(1); fr.setFriend1("test3"); fr.setFriend2("test4");
		usTest2.setAltEmail("test2");
		usTest2.setInterests("test11"); if(usTest2.getBirthdate()==d){usTest2.setEnabled(true);}
		uc.add(usTest2.getAltEmail());
		uc.add(usTest2.getAvatar());
		uc.add(usTest2.getGender()+" ");
		uc.add(usTest2.getHometown());
		uc.add(usTest2.getInspirations());
		uc.add(usTest2.getInterests());
		uc.add(usTest2.getLastName());
		uc.add(usTest2.getName()+usTest2);
		uc.add(usTest2.getPassword()+usTest2.getRapidCall()+usTest2.getUniqueId()+usTest2.getValidation());
		pf.setUser(usTest);
		pf.setWS(ws);
		pf.addFriend("test1");
		pf.addFriend("test2");
		pf.friendReqNotificationWS("test2");
		pf.removeFriend("test1");
		pf.sendFriendlistWS();
		//pf.saveUser(true);
		if(pf.getFriendList().size()!=0){assert(true);}
		else fail("Test Fallito");
	}
	
	@Test
	public final void MessageTest()  {
		Profile pf=Profile.getInstance();
		WSConnection ws=mock(WSConnection.class);
		Date d=new Date();
		User usTest= new User("test", "test","test", "test", "test", d, 'M');
		User usTest1= new User("test1", "test1","test1", "test1", "test1", d, 'M');
		pf.setUser(usTest);
		pf.setWS(ws);
		Message msg=new Message("test", "test", d, "test", true); //Si assume che la classe Message non contenga errori data la sua banalita'
		Message msg1=new Message("test3", "test4","testing");
		Message msg2=new Message("test5", "test6", d);
		Message msgFake= new Message();
		//Utilizzo banale di metodi "get" e "set"
		msg1.setSender("test");
		msg1.setFriendReq(false);
		msg1.getFriendReq();
		msg1.setContent("testing");
		msg1.getContent();
		msg.setCreated(d);
		msg.getSender();
		msg.getMessageId();
		msg.setMessageId(1);
		if(msg1.getCreated()==d && msg1.getRecipient()=="test4"){msg1.setRecipient("test1");}
		List<Message> messageList=new ArrayList<Message>();;
		pf.setMessageList(messageList);
		pf.getMessageList().add(msg);
		pf.getMessageList().add(msg1);
		pf.sentMessageNotificationWS("test1");
		pf.removeMessage(msg1);
		pf.refreshMessageList();
		if(pf.getMessageList().get(0).equals(msg)){assert(true);}
		else fail("Test Fallito");
	}
	
	@Test
	public final void SignInTest() {
		Date d=new Date();
		User usTest= new User("test", "test","test", "test", "test", d, 'M');
		List<Message> lMsg=new ArrayList();
		HashMap<String, Boolean> lFrn= new HashMap<String, Boolean>();
		SignIn s=new SignIn(usTest, lMsg);
		SignIn ss= new SignIn(usTest, lFrn, lMsg);
		SignIn sss=new SignIn(usTest);
		if(s.getMessageList()==lMsg && s.getUser()==usTest){assert(true);}
		
		
	}

}