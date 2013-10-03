package it.hourglass.myTalk.test;
import java.util.Date;

import  org.junit.Test;
import junit.framework.Assert;

import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PushButton;

import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;


import it.hourglass.myTalk.client.presenter.ProfileManagment.PersonalDataProfileManagmentPresenter;


import it.hourglass.myTalk.client.view.ProfileManagment.PersonalDataProfileView;


/*
 * Filename: TestValuesCheck.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/11
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.6     | 2013/08/11  | Approvazione Test
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
public class PersonalDataProfileManagmentTest{
	
	@GwtMock HandlerManager eventBus;
	@GwtMock PersonalDataProfileView display;
	@GwtMock HasValue<String> s;
	
	@Test
	public void checkValuesTest(){
		
		HandlerManager eventBus   = GWT.create(HandlerManager.class);
		PersonalDataProfileView display   = GWT.create(PersonalDataProfileView.class);
		HasValue<String> s = GWT.create(HasValue.class);
		
		 PushButton pb=new PushButton();
		  when(display.getChangeButton()).thenReturn(pb);
		  when(display.getConfirmButton()).thenReturn(pb);
		
		PersonalDataProfileManagmentPresenter edpm = new PersonalDataProfileManagmentPresenter(eventBus, display);
		edpm.name = "";
		assertEquals(false, edpm.checkValues());
		
		edpm.name = "xzz";
		edpm.lastName = "";
		assertEquals(false, edpm.checkValues());
		
		edpm.name = "xzz";
		edpm.lastName = "xzz";
		edpm.password = "xzz";
		edpm.rePassword = "yzz";
		assertEquals(false, edpm.checkValues());
		
		edpm.name = "xzz";
		edpm.lastName = "xzz";
		edpm.password = "xzz";
		edpm.rePassword = "xzz";
		edpm.email = "";
		assertEquals(false, edpm.checkValues());
		
		edpm.name = "xzz";
		edpm.lastName = "xzz";
		edpm.password = "xzzrszdgvdzrgvd";
		edpm.rePassword = "xzzrszdgvdzrgvd";
		edpm.email = "";
		assertEquals(false, edpm.checkValues());
		
		edpm.name = "xzz";
		edpm.lastName = "xzz";
		edpm.password = "xzzrszdgvdzrgvd";
		edpm.rePassword = "xzzrszdgvdzrgvd";
		edpm.email = "sxfvdv@jxczvn.jk";
		edpm.birthyear = "";
		edpm.birthdate = new Date();
		edpm.birthmonth = "";
		assertEquals(false, edpm.checkValues());
		
		edpm.name = "xzz";
		edpm.lastName = "xzz";
		edpm.password = "xzzrszdgvdzrgvd";
		edpm.rePassword = "xzzrszdgvdzrgvd";
		edpm.email = "sdv@jn.jkn";
		edpm.birthyear = "9";
		edpm.birthdate = new Date(9);
		edpm.birthmonth = "9";
		assertEquals(false, edpm.checkValues());

		edpm.checkValues();
		
		edpm.changeLocalValues();
		
		edpm.sendValidation( "dsc", "sc");
	}
}









