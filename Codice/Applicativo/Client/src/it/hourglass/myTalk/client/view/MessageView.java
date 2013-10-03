/*
 * Filename: MessageView.java
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
 *  0.1     | 2013/06/22 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;
import it.hourglass.myTalk.client.presenter.display.MessageDisplay;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;

public class MessageView implements MessageDisplay{
	
	
	private AbsolutePanel contenent;
	private AbsolutePanel title;
	private AbsolutePanel messageTitle;
	private AbsolutePanel messagePanel;

	
	public MessageView()
	{
		contenent= new AbsolutePanel();
		contenent.getElement().setId("Messaggi");
		
		title= new AbsolutePanel();
		title.getElement().setId("titolo");
		contenent.add(title);
		title.add(new HTML("<h1>Messaggi</h1>"));
		
		messageTitle= new AbsolutePanel();
		messageTitle.getElement().setId("sottotitolo");
		contenent.add(messageTitle);
		messageTitle.add(new HTML("<h2>Messaggi</h2>"));
		
		messagePanel=new AbsolutePanel();
		messagePanel.getElement().setId("contenutoMessaggi");
		messagePanel.getElement().getStyle().clearOverflow();
		contenent.add(messagePanel);

	}

	@Override
	public void addMessage(AbsolutePanel panel) {
		messagePanel.add(panel);
		
	}

	@Override
	public AbsolutePanel getView() {
		return contenent;
	}
}
