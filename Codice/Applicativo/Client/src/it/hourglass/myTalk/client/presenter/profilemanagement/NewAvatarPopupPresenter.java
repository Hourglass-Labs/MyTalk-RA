/*
 * Filename: AvatarProfileManagementPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati - Lorenzo Cresce Gioele
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
 *  0.2    	|  2013/06/26 | Aggiunta metodo bind e init
 * ---------+------------+------------------
 *  0.1     | 2013/06/16 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 */
package it.hourglass.myTalk.client.presenter.profilemanagement;


import it.hourglass.myTalk.client.presenter.display.NewAvatarPopupDisplay;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;

public class NewAvatarPopupPresenter {


	
	HandlerManager eventBus;
	User user;
	NewAvatarPopupDisplay display;
	
	public NewAvatarPopupPresenter(NewAvatarPopupDisplay display){
		this.user=Profile.getUser();
		this.display=display;
		bind();
	}
	
	private void bind(){
		display.getConfirmButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				user.setAvatar(display.getUrl().getValue());
				Profile.saveUser(false);
			}
		});
		
		display.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				removePopup();
			}
		});
		
	}
	
	public void removePopup(){
		this.display.removePopup();
	}
}
