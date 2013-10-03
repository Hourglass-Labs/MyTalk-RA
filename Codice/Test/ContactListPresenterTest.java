package it.hourglass.myTalk.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;

import it.hourglass.myTalk.client.WSConnection;
//Classi testate
import it.hourglass.myTalk.client.presenter.ContactListPresenter;
import it.hourglass.myTalk.client.presenter.UserListPresenter;
import it.hourglass.myTalk.client.view.ContactListView;

/*
 * Filename: ContactListPresenterTest.java
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
 *  0.5     | 2013/08/04  | Revisione dei test a seguito della RQ
 *  ---------+------------+------------------
 *  0.4     | 2013/07/07  | Approvazione Test
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
public class ContactListPresenterTest{

	@GwtMock WSConnection wsconnection; 
	@GwtMock HandlerManager eventBus;  
	@GwtMock ContactListView display;   
	@GwtMock UserListPresenter amedeo;  
	@GwtMock AbsolutePanel a;          
	
	@Test
	public void addUserTest(){
		
		WSConnection wsconnection = GWT.create(WSConnection.class);
		HandlerManager eventBus   = GWT.create(HandlerManager.class);
		ContactListView display   = GWT.create(ContactListView.class);
		UserListPresenter amedeo  = GWT.create(UserListPresenter.class);
		AbsolutePanel a           = GWT.create(AbsolutePanel.class);
		
		AbsolutePanel ap = new AbsolutePanel();
		when(amedeo.getView()).thenReturn(ap);
		
		PushButton b = new PushButton();
		when(display.getContactManagmentPage()).thenReturn(b);
		
		//Creo un'istanza della classe di prova
		ContactListPresenter clp = new ContactListPresenter(eventBus, wsconnection, display);
		
		//Carico la lista degli amici con dei valori di prova
		clp.friendContactList.put("amedeo", amedeo);
		
		//aggiungo alla view gli utenti
		clp.addListView();
		
		//verifico che un utente in particolare sia stato caricato
		verify(display).addUser(clp.friendContactList.get("amedeo").getView());	
	}
	
	@Test
	public void removecontactTest(){
		
		WSConnection wsconnection = GWT.create(WSConnection.class);
		HandlerManager eventBus   = GWT.create(HandlerManager.class);
		ContactListView display   = GWT.create(ContactListView.class);
		UserListPresenter amedeo  = GWT.create(UserListPresenter.class);
		AbsolutePanel a           = GWT.create(AbsolutePanel.class);
		
		AbsolutePanel ap = new AbsolutePanel();
		when(amedeo.getView()).thenReturn(ap);
		
		PushButton b = new PushButton();
		when(display.getContactManagmentPage()).thenReturn(b);
		
		//Creo un'istanza della classe di prova
		ContactListPresenter clp = new ContactListPresenter(eventBus, wsconnection, display);
		
		//Carico la lista degli amici con dei valori di prova
		clp.friendContactList.put("amedeo", amedeo);
		
		//aggiungo alla view gli utenti
		clp.addListView();
		
		//rimuovo un amico dalla lista
		clp.removeContact("amedeo");
			
		//verifico che il contatto eliminato sia stato effettivamente canecllato
		assertEquals(null, clp.friendContactList.get("amedo"));
	}
	
	@Test
	public void removeContactTest(){
		WSConnection wsconnection = GWT.create(WSConnection.class);
		HandlerManager eventBus   = GWT.create(HandlerManager.class);
		ContactListView display   = GWT.create(ContactListView.class);
		UserListPresenter amedeo  = GWT.create(UserListPresenter.class);
		AbsolutePanel a           = GWT.create(AbsolutePanel.class);
		
		AbsolutePanel ap = new AbsolutePanel();
		when(amedeo.getView()).thenReturn(ap);
		
		PushButton b = new PushButton();
		when(display.getContactManagmentPage()).thenReturn(b);
		
		//Creo un'istanza della classe di prova
		ContactListPresenter clp = new ContactListPresenter(eventBus, wsconnection, display);
		
		//Carico la lista degli amici con dei valori di prova
		clp.friendContactList.put("amedeo", amedeo);
		
		//aggiungo alla view gli utenti
		clp.addListView();
		
		//cancella la la view della lista contatti
		clp.removeContactList();
		//verifico che effettivamente sia stao invocato il metodo che mi aspetto
		verify(display).removeContactList();
				
		
	}
}