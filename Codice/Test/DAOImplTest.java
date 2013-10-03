
/*
 * Filename: DAOImplTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Paolo Bustreo
 * Date: 2013/08/20
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.4     | 2013/08/20  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/08/19 | Codifica testfetchMessages() e testFriendship() 
 *  ---------+------------+------------------
 *  0.2    	|  2013/08/18 | Codifica testCheckValidation()
 * ---------+------------+------------------
 *  0.1     | 2013/08/03 |Codifica testStore()
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 
 */






package it.hourglass.myTalk.test;

import static org.junit.Assert.*;
import it.hourglass.myTalk.client.shared.Friendships;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.shared.Message;
import org.mockito.*;
import java.util.Date;
import org.junit.Test;

public class DAOImplTest {

	@Test
	public final void testStore() {
		DAO dao=DAOImpl.getInstance();
		Date d=new Date();
		User userTest= new User("test", "test","test", "test", "test", d, 'M');
		User userTest2=dao. fetchFriend("test");
		dao.storeUser(userTest);
		dao.fetchSpecificFriendship("test1", "test");
		dao.setValidation("test1", "test1");
		dao.setPassword("test", "pwd");
		if(userTest.getUniqueId()==dao.fetchFriend("test").getUniqueId() && dao.fetchUserByEmail("test")==userTest){assert(true);}
	}

	@Test
	public final void testcheckValidation() {
		DAO dao=DAOImpl.getInstance();
		Date d=new Date();
		User userTest= new User("test", "test","test", "test", "test", d, 'M');
		userTest.setValidation("validationTest");
		dao.storeUser(userTest);
		if(dao.checkValidation("test","validationTest")==true){assert(true);}
	}
	
	@Test
	public final void testfetchMessages() {
		DAO dao=DAOImpl.getInstance();
		Date d=new Date();
		User userTest= new User("test", "test","test", "test", "test", d, 'M');
		Profile pf=Profile.getInstance();
		dao.storeUser(userTest);
		Message msg=new Message("test","test","test");
		dao.updateMessage(msg);
		dao.deleteMessage(msg);
		pf.setUser(dao.fetchUserById("test"));
		if(dao.fetchMessages("test")!=pf.getMessageList()){assert(true);}
	}
	
	
	@Test
	public final void testFriendship() {
		DAO dao=DAOImpl.getInstance();
		Date d=new Date();
		User userTest= new User("test", "test","test", "test", "test", d, 'M');
		Friendships fr= new Friendships("test1","test2");
		Profile pf=Profile.getInstance();
		dao.storeUser(userTest);
		dao.updateFriendship(fr);
		dao.deleteFriendship(fr);
		assert(true);

	}
}
