/*
 * Filename: TextView.java
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

import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.presenter.display.TextMessageDisplay;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public class TextMessageView implements TextMessageDisplay{
	
	private AbsolutePanel content;
	private Button remove;
	
	public TextMessageView(Message message)
	{
		content= new AbsolutePanel();
		content.getElement().setId("MessaggioTesto");
		
		String date = (DateTimeFormat.getFormat("dd/MM/yyyy - HH:mm:ss")
				.format(message.getCreated()));
		Label sender=new Label(message.getSender()+"  "+date);
		sender.getElement().setId("Utente");
		content.add(sender);
		
		Label text= new Label(message.getContent());
		text.getElement().setId("Messaggio");
		content.add(text);
		
		remove=new Button("Cancella");
		remove.getElement().setId("ButtonElimina");
		content.add(remove);
		
	}


	@Override
	public AbsolutePanel getView() {
		return content;
	}


	@Override
	public Button getRemoveButton() {
		return remove;
	}
	
	@Override
	public void selfDelete(){
		content.removeFromParent();
	}
	
}


