package it.hourglass.myTalk.test;
import java.util.Date;

import  org.junit.Test;
import junit.framework.Assert;

import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.PushButton;

import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;


import it.hourglass.myTalk.client.presenter.ProfileManagment.NewAvatarPopupPresenter;
import it.hourglass.myTalk.client.presenter.ProfileManagment.NewEmailConfirmPopupPresenter;
import it.hourglass.myTalk.client.presenter.ProfileManagment.PersonalDataProfileManagmentPresenter;


import it.hourglass.myTalk.client.view.ProfileManagment.PersonalDataProfileView;

/*
 * Filename: TestValuesCheck.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/07
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
*  0.4     | 2013/08/07  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/08/05  | Stesura delle invocazioni ai metodi alla classe testata
 *  ---------+------------+------------------
 *  0.2    	| 2013/08/04  | Stesura mock 
 * ---------+-------------+------------------
 *  0.1     | 2013/08/02  | Dichiarazione della classe e dei  parametri
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
*/

@RunWith(GwtMockitoTestRunner.class)
public class OtherProfileManagment{
	
	@GwtMock HandlerManager eventBus;
	@GwtMock PersonalDataProfileView display;
	@GwtMock HasValue<String> s;
	
	@Test
	public void checkConstructor(){
		
		HandlerManager eventBus   = GWT.create(HandlerManager.class);
		PersonalDataProfileView display   = GWT.create(PersonalDataProfileView.class);
		
		HasValue<String> s = GWT.create(HasValue.class);
		it.hourglass.myTalk.client.presenter.ProfileManagment.NewEmailConfirmPopupPresenter.Display d = GWT.create(it.hourglass.myTalk.client.presenter.ProfileManagment.NewEmailConfirmPopupPresenter.Display.class);

		
		NewEmailConfirmPopupPresenter edpm = new NewEmailConfirmPopupPresenter("x", display, d, false);
		
		it.hourglass.myTalk.client.presenter.ProfileManagment.NewAvatarPopupPresenter.Display x = GWT.create(it.hourglass.myTalk.client.presenter.ProfileManagment.NewAvatarPopupPresenter.Display.class);
		NewAvatarPopupPresenter n = new NewAvatarPopupPresenter( x);
		
	}
}









