/*
 * Filename: ContactListView.java
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
 *  0.1     | 2013/06/19 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasClickHandlers;

import it.hourglass.myTalk.client.presenter.display.ContactListDisplay;

public class ContactListView implements ContactListDisplay{

	AbsolutePanel contenent;
	AbsolutePanel list;
	AbsolutePanel groupNamePanel;
	Button getContactManagmentPageButton;
	HTML groupName;
	
	public ContactListView(){
		
		contenent= new AbsolutePanel();
		contenent.getElement().setId("ListaContatti");
		Style lista=contenent.getElement().getStyle();
		lista.clearOverflow();
		AbsolutePanel buttonPanel=new AbsolutePanel();
		contenent.add(buttonPanel);
		getContactManagmentPageButton=new Button("Gestisci contatti");
		getContactManagmentPageButton.setStyleName("bottone");
		buttonPanel.add(getContactManagmentPageButton);
		

		
		list= new AbsolutePanel();
		list.getElement().setId("GruppoUtenti");

		
		groupName=new HTML("Amici");
		groupNamePanel= new AbsolutePanel();
		groupNamePanel.getElement().setId("NomeGruppo");
		groupNamePanel.add(groupName);
		list.add(groupNamePanel);
		
		contenent.add(list);
		
		
	}
	
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}

	@Override
	public void addUser(AbsolutePanel userView) {
		list.add(userView);
	}

	@Override
	public void removeContactList() {
		this.contenent.removeFromParent();
		
	}

	@Override
	public HasClickHandlers getContactManagmentPage() {
		return getContactManagmentPageButton;
	}	

}
