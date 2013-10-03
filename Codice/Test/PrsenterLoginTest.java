package it.hourglass.myTalk.test;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.objectweb.asm.Handle;

import it.hourglass.myTalk.client.WSConnection;
import it.hourglass.myTalk.client.RPCService.RPCServiceAsync;
import it.hourglass.myTalk.client.event.LoginRequestEvent;
import it.hourglass.myTalk.client.presenter.LoginPresenter;
import it.hourglass.myTalk.client.presenter.LoginPresenter.Display;
import it.hourglass.myTalk.client.profile.Profile;
import it.hourglass.myTalk.client.view.LoginView;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTestWithMockito;
import com.googlecode.gwt.test.Mock;
import com.googlecode.gwt.test.assertions.GwtAssertions;
import com.googlecode.gwt.test.utils.GwtReflectionUtils;
import com.googlecode.gwt.test.utils.events.Browser;


/*
 * Filename: PresenterLoginTest.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/13
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.6     | 2013/08/13  | Approvazione Test
 *  ---------+------------+------------------
 *  0.5     | 2013/08/08  | Revisione dei test a seguito della RQ
 *  ---------+------------+------------------
 *  0.4     | 2013/07/05  | Approvazione Test
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
public class PrsenterLoginTest extends GwtTestWithMockito {
	
	@Mock
	private RPCServiceAsync rpcA;
	
	@Mock
	WSConnection wsconnection;
	
	@Mock
	HandlerManager eventBus;
	
	@Mock
	Display display;
	
	@Mock
	HasClickHandlers hch;
	
	@Mock
	LoginView lv;
	
	@Mock
	HasValue<String> su;
	
	@Mock
	HasValue<String> gi;
	
	@Mock
	Profile f;
	
	@Test
	public void doLoginControlTest() {
		
		
		//Necessaria altrimenti il costruttore non compila
		Mockito.when(lv.getLoginButton()).thenReturn(hch);

		LoginPresenter lpt = new LoginPresenter(wsconnection, eventBus, lv);
		
		Mockito.when(lv.getUsername()).thenReturn(su);
		Mockito.when(lv.getPassword()).thenReturn(gi);
		
		Mockito.when(lv.getPassword().getValue()).thenReturn("y");
		Mockito.when(lv.getUsername().getValue()).thenReturn("x");
		
		doSuccessCallback(false).when(rpcA).checkLogin(Mockito.eq("x"), Mockito.eq("y"), Mockito.any(AsyncCallback.class));
				
		lpt.doLoginControl("x", "y");
		
		Mockito.verify(rpcA).checkLogin(Mockito.eq("x"), Mockito.eq("y"), Mockito.any(AsyncCallback.class));
		
		
	
	}
	

}
