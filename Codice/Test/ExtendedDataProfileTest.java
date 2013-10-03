package it.hourglass.myTalk.test;
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


import it.hourglass.myTalk.client.presenter.ProfileManagment.ExtendedDataProfileManagmentPresenter;

import it.hourglass.myTalk.client.view.ProfileManagment.ExtendedDataProfileManagmentView;

/*
 * Filename: ExtendedDataProfileTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/14
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.7     | 2013/08/14  | Approvazione Test
 *  ---------+------------+------------------
 *  0.6     | 2013/08/06  | Ampliamento dei test
 *  ---------+------------+------------------
 *  0.5     | 2013/08/04  | Revisione, ampliamento dei test a seguito della RQ
 *  ---------+------------+------------------
 *  0.4     | 2013/07/06  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/07/06 | Stesura delle invocazioni ai metodi alla classe testata
 *  ---------+------------+------------------
 *  0.2    	| 2013/07/05 | Stesura mock 
 * ---------+------------+------------------
 *  0.1     | 2013/07/03 | Dichiarazione della classe e dei  parametri
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
*/

@RunWith(GwtMockitoTestRunner.class)
public class ExtendedDataProfileTest{
	
	@GwtMock HandlerManager eventBus;
	@GwtMock ExtendedDataProfileManagmentView display;
	@GwtMock HasValue<String> s;
	
	@Test
	public void checkValuesTest(){
		
		HandlerManager eventBus   = GWT.create(HandlerManager.class);
		ExtendedDataProfileManagmentView display   = GWT.create(ExtendedDataProfileManagmentView.class);
		HasValue<String> s = GWT.create(HasValue.class);
		
		 PushButton pb=new PushButton();
		  when(display.getChangeButton()).thenReturn(pb);
		  when(display.getConfirmButton()).thenReturn(pb);
		
		String x = "x";  
		  
	
		ExtendedDataProfileManagmentPresenter edpm = new ExtendedDataProfileManagmentPresenter(eventBus, display);
		edpm.description = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		assertEquals(false, edpm.checkValues());
		
		edpm.description = "x";
		edpm.inspirations = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		assertEquals(false, edpm.checkValues());
		
		edpm.description = "x";
		edpm.inspirations = "x";		
		edpm.interests = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		
		edpm.description = "x";
		edpm.inspirations = "x";		
		edpm.interests = "x";
		edpm.hometown = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		assertEquals(false, edpm.checkValues());
		
		edpm.description = "x";
		edpm.inspirations = "x";		
		edpm.interests = "x";
		edpm.hometown = "x";
		edpm.currentLocation = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		assertEquals(false, edpm.checkValues());		

		edpm.description = "x";
		edpm.inspirations = "x";		
		edpm.interests = "x";
		edpm.hometown = "x";
		edpm.currentLocation = "x";
		assertEquals(true, edpm.checkValues());
		
		edpm.bind();
		edpm.changeLocalValues();
		
	}
}









