/*
 * Filename: EditProfileView.java
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
 *  0.1     | 2013/06/10 |Codifica classe
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

import it.hourglass.myTalk.client.presenter.display.EditProfileDisplay;

public class EditProfileView implements EditProfileDisplay{

	
		AbsolutePanel contenent;
		AbsolutePanel title;
		AbsolutePanel avatarPanel;
		AbsolutePanel personalDataPanel;
		AbsolutePanel extendedDataPanel;
		AbsolutePanel footerPage;
		
		Button messageButton;
		
			public EditProfileView()
			{
				contenent= new AbsolutePanel();
				contenent.getElement().setId("Profilo");
				
				title= new AbsolutePanel();
				title.getElement().setId("titolo");
				contenent.add(title);
				title.add(new HTML("<h1>Profilo</h1>"));
				
				avatarPanel=new AbsolutePanel();
				avatarPanel.getElement().setId("immagineProfilo");
				personalDataPanel=new AbsolutePanel();
				personalDataPanel.getElement().setId("datiPersonali");
				extendedDataPanel=new AbsolutePanel();
				extendedDataPanel.getElement().setId("datiEstesi");
				footerPage=new AbsolutePanel();
				footerPage.getElement().setId("footer");
				
				
			}
			
			
	@Override
	public HasClickHandlers getMessageButton() {
		return messageButton;
	}


	@Override
	public AbsolutePanel getView() {
		return contenent;
	}

	@Override
	public void addAvatarView(AbsolutePanel panel) {
		avatarPanel.add(panel);
		contenent.add(avatarPanel);
		
	}

	@Override
	public void addPersonalDataView(AbsolutePanel panel) {
		personalDataPanel.add(panel);
		contenent.add(personalDataPanel);
		
	}

	@Override
	public void addExtendedDataView(AbsolutePanel panel) {
		extendedDataPanel.add(panel);
		contenent.add(extendedDataPanel);
		
	}

	@Override
	public void addFooter() {
		
		messageButton=new Button("Messaggi");
		footerPage.add(messageButton);
		contenent.add(footerPage);
		
	}

}
