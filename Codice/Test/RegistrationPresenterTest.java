package it.hourglass.myTalk.test;

import it.hourglass.myTalk.client.presenter.RegistrationPresenter;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.Mock;


/*
 * Filename: RegistrationPresenterTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/11
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.6     | 2013/08/11 | Approvazione Test
 *  ---------+------------+------------------
 *  0.5     | 2013/08/07 | Revisione dei test a seguito della RQ
 *  ---------+------------+------------------
 *  0.4     | 2013/07/5  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/07/03 | Stesura delle invocazioni ai metodi alla classe testata
 *  ---------+------------+------------------
 *  0.2    	| 2013/07/02 | Stesura mock 
 * ---------+------------+------------------
 *  0.1     | 2013/07/01 | Dichiarazione della classe e dei  parametri
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 
 */

@GwtModule("it.hourglass.myTalk.MyTalk")
public class RegistrationPresenterTest extends GwtTestWithMockito{

	@Mock
	RegistrationPresenter.Display display;
	@Mock
	HasValue<String> username, email, password, repassword, name, lname, gender;
	@Mock
	HasClickHandlers rb, sccb, ccb;
	
	@Test
	public void getAllCheckedTest(){
		Mockito.when(display.getUsername()).thenReturn(username);
		Mockito.when(username.getValue()).thenReturn("xxxxx");

		Mockito.when(display.getEmail()).thenReturn(email);
		Mockito.when(email.getValue()).thenReturn("xxxx@kjb.com");//errore sull' email
		
		Mockito.when(display.getPassword()).thenReturn(password);
		Mockito.when(password.getValue()).thenReturn("xxxxxxxx1");
		
		Mockito.when(display.getRePassword()).thenReturn(repassword);
		Mockito.when(repassword.getValue()).thenReturn("xxxxxxxx1");
		
		Mockito.when(display.getName()).thenReturn(name);
		Mockito.when(name.getValue()).thenReturn("xxxxxx");
		
		Mockito.when(display.getLastName()).thenReturn(lname);
		Mockito.when(lname.getValue()).thenReturn("xxxxx");
		
		String birthday   = "xxxx";
		Mockito.when(display.getBirthDay()).thenReturn(birthday);
		
		String birthmonth = "9";
		Mockito.when(display.getBirthMonth()).thenReturn(birthmonth);

		String birthyear  = "7";
		Mockito.when(display.getBirthYear()).thenReturn(birthyear);

		Mockito.when(display.getGender()).thenReturn("m");
		
		Mockito.when(display.getRegisterButton()).thenReturn(rb);
		
		Mockito.when(display.getShowConfirmCodeButton()).thenReturn(sccb);
		
		Mockito.when(display.getConfirmCodeButton()).thenReturn(ccb);
		
		RegistrationPresenter rp = new RegistrationPresenter(display);
		
		assertEquals(true,rp.getAllChecked());
	}

}
