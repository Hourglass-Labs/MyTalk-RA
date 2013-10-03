package it.hourglass.myTalk.test;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.googlecode.gwt.test.Mock;


import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.server.model.businesslogic.RPCServiceImpl;

/*
 * Filename: TestValuesCheck.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/10
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.7     | 2013/08/10  | Approvazione Test
 *  ---------+------------+------------------ 
 *  0.6     | 2013/08/05  | Ampliamento dei test
 *  ---------+------------+------------------
 *  0.5     | 2013/08/04  | Revisione, ampliamento dei test a seguito della RQ
 *  ---------+------------+------------------
 *  0.4     | 2013/07/07  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/07/05  | Stesura delle invocazioni ai metodi alla classe testata
 *  ---------+------------+------------------
 *  0.2    	| 2013/07/04  | Stesura mock 
 * ---------+-------------+------------------
 *  0.1     | 2013/07/02  | Dichiarazione della classe e dei  parametri
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
*/

@RunWith(GwtMockitoTestRunner.class)
public class TestRPCService{

		@GwtMock User user;
		@GwtMock Message message;
		
		RPCServiceImpl rsi = new RPCServiceImpl();
		

		@Test
		public void storeUserTest(){
			User user = GWT.create(User.class);
			when(user.getEmail()).thenReturn("morlingiovanni@gmail.com");
			when(user.getUniqueId()).thenReturn("x");
			
		assertEquals("Indirizzo email già associato ad un altro account.",
				rsi.storeUser(user,true));
		}
		
		@Test
		public void registerTest(){
			User user = GWT.create(User.class);
			when(user.getEmail()).thenReturn("morlingiovanni@gmail.com");
			when(user.getUniqueId()).thenReturn("x");
			
		assertEquals("Indirizzo email già in uso.",
				rsi.register(user));
		
			user = new User("PROVA2", "PROVA2", "PROVA2","PROVA", "PROVA", "PROVA","PROVA", new Date(), 'c' ,"PROVA","PROVA","PROVA","PROVA","PROVA","PROVA", true);
			rsi.register(user);
		}
		
		@Test
		public void checkLoginTest(){
			User user = GWT.create(User.class);
			when(user.getEmail()).thenReturn("morlingiovanni@gmail.com");
			when(user.getUniqueId()).thenReturn("x");
			
		assertEquals(false,
				rsi.checkLogin("x", "y"));
		

		}
		
		@Test
		public void signInTest(){
			String s = "giovanni";
			rsi.signIn(s);
		}
		
		@Test
		public void fetchFriendProfileTest(){
			String s = "giovanni";
			rsi.fetchFriendProfile(s);			
		}
		
		@Test
		public void LoginValidationTest(){
			rsi.loginValidation("giovanni", "ciaociao1");
		}
		
		@Test
		public void setValidationTest(){ 
			rsi.setValidation("morlingiovanni@gmail.com");
		}
		
		@Test
		public void sendMessageTest(){
			Message message = GWT.create(Message.class);
			when(message.getRecipient()).thenReturn(null);

			assertEquals("Database al momento non raggiungibile. ",
				rsi.sendMessage(message));
		}
		
		@Test
		public void deleteMessageTest(){
			Message message = GWT.create(Message.class);
			when(message.getRecipient()).thenReturn(null);
			
			assertEquals("Operazione al momento non disponibile. ",
				rsi.deleteMessage(message));
		}
		
		@Test
		public void friendshakeTest(){
			Message message = GWT.create(Message.class);
			when(message.getRecipient()).thenReturn(null);

			assertEquals(false, 
				rsi.friendshake(message, false));
		}
		
		@Test
		public void checkSessionTest(){
			rsi.checkSession();
		}

		
	
}




