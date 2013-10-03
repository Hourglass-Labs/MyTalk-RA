/*
 * Filename: FriendMessageView.java
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

import it.hourglass.myTalk.client.shared.Message;

import it.hourglass.myTalk.client.presenter.display.FriendMessageDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public class FriendMessageView implements FriendMessageDisplay{
	
	Message message;
	AbsolutePanel content;
	Button acceptButton;
	Button refuseButton;

	
	public FriendMessageView(Message message)
	{
		content= new AbsolutePanel();
		content.getElement().setId("MessaggioAmicizia");
		
		Label username=new Label(message.getSender()+" ha richiesto di essere tuo amico.");
		username.getElement().setId("Utente");
		content.add(username);
		
		acceptButton=new Button("Accetta");
		acceptButton.getElement().setId("Bottone");
		content.add(acceptButton);
		
		refuseButton=new Button("Rifiuta");
		refuseButton.getElement().setId("Bottone");
		content.add(refuseButton);
		
		
	}
	
	@Override
	public HasClickHandlers getAcceptButton() {
		// TODO Auto-generated method stub
		return acceptButton;
	}
	@Override
	public HasClickHandlers getRefuseButton() {
		// TODO Auto-generated method stub
		return refuseButton;
	}
	@Override
	public AbsolutePanel getView() {
		// TODO Auto-generated method stub
		return content;
	}
	@Override
	public void selfDelete(){
		content.removeFromParent();
	}
}
