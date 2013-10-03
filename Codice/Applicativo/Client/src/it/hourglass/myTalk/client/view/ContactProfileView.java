/*
 * Filename: ContactProfileView.java
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

import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.presenter.display.ContactProfileDisplay;
import it.hourglass.myTalk.client.view.contactprofile.AvatarContactProfileView;
import it.hourglass.myTalk.client.view.contactprofile.ExtendedDataContactProfileView;
import it.hourglass.myTalk.client.view.contactprofile.PersonalDataContactProfileView;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;

public class ContactProfileView implements ContactProfileDisplay{
	AbsolutePanel contenent;
	AbsolutePanel title;
	AbsolutePanel avatarPanel;
	AbsolutePanel personalDataPanel;
	AbsolutePanel extendedDataPanel;
	AbsolutePanel footerPage;
	AvatarContactProfileView avatarContact;
	PersonalDataContactProfileView personalData;
	ExtendedDataContactProfileView extendedData;
	

		public ContactProfileView()
		{
			contenent= new AbsolutePanel();
			contenent.getElement().setId("Profilo");
			title= new AbsolutePanel();
			title.getElement().setId("title");
			contenent.add(title);
			title.add(new HTML("<h1>Profilo</h1>"));
			avatarPanel=new AbsolutePanel();
			avatarPanel.getElement().setId("immagineProfilo");
			personalDataPanel=new AbsolutePanel();
			personalDataPanel.getElement().setId("datiPersonali");
			extendedDataPanel=new AbsolutePanel();
			extendedDataPanel.getElement().setId("datiEstesi");
			}
		
		public void init(User user){
			avatarContact=new AvatarContactProfileView(user);
			avatarPanel.add(avatarContact.getView());
			contenent.add(avatarPanel);
			personalData=new PersonalDataContactProfileView(user);
			personalDataPanel.add(personalData.getView());
			contenent.add(personalDataPanel);
			extendedData=new ExtendedDataContactProfileView(user);
			extendedDataPanel.add(extendedData.getView());
			contenent.add(extendedDataPanel);
		}

		@Override
		public AbsolutePanel getView() {
			return contenent;
		}
}
