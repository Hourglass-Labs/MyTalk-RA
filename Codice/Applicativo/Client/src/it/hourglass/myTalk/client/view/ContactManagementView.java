/*
 * Filename: ContactManagementView.java
 * Package: it.hourglass.myTalk.client.view
 * Author: Sasa Ilievski
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  2.0     | 2013/09/10  | Approvazione classe
 *  ---------+------------+------------------
 *  1.1     | 2013/09/1  | Cambio package interfaccia display
 *  ---------+------------+------------------
 *  1.0     | 2013/07/6  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/06/18 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import it.hourglass.myTalk.client.presenter.display.ContactManagementDisplay;

public class ContactManagementView implements ContactManagementDisplay{

	AbsolutePanel contactPanel;
	AbsolutePanel friendshipRequestPanel;
	AbsolutePanel content;
	AbsolutePanel title;
	Button friendshipRequestButton;
	TextBox userRequest;
	
	public ContactManagementView()
	{
		content= new AbsolutePanel();
		content.getElement().setId("GestioneContatti");
		
		HTML title= new HTML("<h1>Gestione Contatti</h1>");
		title.getElement().setId("title");
		content.add(title);
		
		AbsolutePanel addFriend= new AbsolutePanel();
		addFriend.getElement().setId("aggiungiAmico");
		content.add(addFriend);
		
		HTML subtitle= new HTML("<h2>Aggiungi amico</h2>");
		subtitle.getElement().setId("titleAmico");
		addFriend.add(subtitle);
		
		Label textFriend= new Label("Inserisci l'username dell'utente che vuoi aggiungere");
		textFriend.getElement().setId("TestoUsername");
		addFriend.add(textFriend);
		
		userRequest= new TextBox();
		userRequest.getElement().setId("TBUsername");
		addFriend.add(userRequest);
		
		friendshipRequestButton=new Button("Aggiungi");
		friendshipRequestButton.getElement().setId("Aggiungi");
		addFriend.add(friendshipRequestButton);
		
		AbsolutePanel messagePanel= new AbsolutePanel();
		messagePanel.getElement().setId("richiesteAmicizia");
		content.add(messagePanel);
		
		
		HTML friendshipRequestTitle= new HTML("<h2>Richieste di amicizia</h2>");
		friendshipRequestTitle.getElement().setId("titleRichiesteAmicizia");
		messagePanel.add(friendshipRequestTitle);
		
		friendshipRequestPanel= new AbsolutePanel();
		friendshipRequestPanel.getElement().setId("PanelloRichiesteAmicizia");
		friendshipRequestPanel.getElement().getStyle().clearOverflow();
		messagePanel.add(friendshipRequestPanel);
		

		
		AbsolutePanel friendshipPanel= new AbsolutePanel();
		friendshipPanel.getElement().setId("Amicizie");
		content.add(friendshipPanel);
		
		HTML friendshipTitle= new HTML("<h2>Lista Amici</h2>");
		friendshipTitle.getElement().setId("titleAmicizie");
		friendshipPanel.add(friendshipTitle);
		
		contactPanel= new AbsolutePanel();
		contactPanel.getElement().setId("PanelloAmicizie");
		contactPanel.getElement().getStyle().clearOverflow();
		friendshipPanel.add(contactPanel);

	}
	
		
	@Override
	public HasClickHandlers getFriendshipRequestButton() {
		return friendshipRequestButton;
	}
	@Override
	public AbsolutePanel getView() {
		return content;
	}

	@Override
	public HasValue<String> getUserRequest() {
		return userRequest;
	}

	@Override
	public void addFriend(AbsolutePanel panel) {
		contactPanel.add(panel);	
	}
	
	@Override
	public void addFriendMessage(AbsolutePanel panel) {
		friendshipRequestPanel.add(panel);
		
	}

}
