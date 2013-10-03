/*
 * Filename: PopupMessageView.java
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
 *  0.1     | 2013/06/20 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import it.hourglass.myTalk.client.presenter.display.PopupMessageDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

public class PopupMessageView implements PopupMessageDisplay{
	private DialogBox MessageDialogBox;
	private String receiver;
	private TextArea textMessage;
	private Button cancel;
	private Button send;
	private Label lenghtLabel;
	
	public PopupMessageView(String receiver)
	{
		MessageDialogBox = new DialogBox();
		AbsolutePanel layout = new AbsolutePanel();
		layout.getElement().setId("PopUpMessaggio");
		MessageDialogBox.setWidget(layout);
		
		Label receiverLabel= new Label (receiver+":");
		receiverLabel.getElement().setId("Nome");
		layout.add(receiverLabel);
		
		lenghtLabel=new Label("Il messggio deve essere minore di 255 caratteri");
		lenghtLabel.getElement().setId("Label");
		layout.add(lenghtLabel);
		
		textMessage= new TextArea();
		textMessage.getElement().setId("Messaggio");
		layout.add(textMessage);
		
		cancel= new Button("Indietro");
		cancel.getElement().setId("Indietro");
		layout.add(cancel);
		
		send=new Button("Invia");
		send.getElement().setId("Invia");
		layout.add(send);
		
		
		MessageDialogBox.center();
		MessageDialogBox.show();
		
	}

	@Override
	public HasClickHandlers getSendButton() {
		return send;
	}

	@Override
	public HasClickHandlers getCancellButton() {
		return cancel;
	}

	@Override
	public void remove() {
		MessageDialogBox.removeFromParent();
	}

	@Override
	public HasValue<String> getText() {
		return textMessage;
	}
	@Override
	public void error(){
		lenghtLabel.getElement().setId("Errore");
	}
	
}
