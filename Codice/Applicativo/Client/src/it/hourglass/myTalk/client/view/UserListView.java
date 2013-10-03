/*
 * Filename: UserListView.java
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
 *  0.1     | 2013/06/26 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.view;

import java.util.Map.Entry;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

import it.hourglass.myTalk.client.presenter.display.UserListDisplay;


public class UserListView implements UserListDisplay{

	AbsolutePanel contenent;
	boolean status;
	Image statusImage;
	Label uniqueId;
	PushButton callButton;
	PushButton messageButton;
	PushButton profileButton;
	
	public UserListView(Entry<String, Boolean> userInformation){
	
	contenent= new AbsolutePanel();	
	status=userInformation.getValue();
	uniqueId=new Label();
	
	statusImage=new Image();
	contenent.add(statusImage);
	setStatusImage(status);
	statusImage.setStyleName("Stato");
	
	uniqueId.setText(userInformation.getKey());

	contenent.setStyleName("Utente");
	contenent.add(uniqueId);
	uniqueId.setStyleName("NomeUtenteLista");
	
	AbsolutePanel buttons=new AbsolutePanel();
	buttons.getElement().setId("BottoniChiamata");
	contenent.add(buttons);
	
	
	Image profileImage=new Image("profile.gif");
	profileButton = new PushButton(profileImage);
	profileButton.setStyleName("Button");
	profileButton.getElement().setId("Profilo");
	buttons.add(profileButton);
	
	Image messageImage=new Image("message.gif");
	messageButton = new PushButton(messageImage);
	messageButton.setStyleName("Button");
	messageButton.getElement().setId("Messaggio");
	buttons.add(messageButton);
	
	Image callImage=new Image("call.gif");
	callButton = new PushButton(callImage);
	callButton.setStyleName("Button");
	callButton.getElement().setId("Chiamata");
	buttons.add(callButton);
	

	
	
	}
	
	private void setStatusImage(boolean status){
		this.status=status;
		if(status==true){
			statusImage.setUrl("online.gif");
		}
		else
			statusImage.setUrl("offline.gif");
	}
	
	
	@Override
	public AbsolutePanel getView() {
		return contenent;
	}



	@Override
	public HasClickHandlers getCallButton() {
		return callButton;
	}



	@Override
	public HasClickHandlers getMessageButton() {
		return messageButton;
	}



	@Override
	public HasClickHandlers getProfileButton() {
		return profileButton;
	}


	@Override
	public void changeStatus(boolean status) {
		setStatusImage(status);
	}

	@Override
	public void remove() {
		contenent.removeFromParent();
	}

}
