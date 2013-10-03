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

import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.presenter.display.AvatarProfileManagementDisplay;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.view.profilemanagement.NewAvatarPopupView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;



/**
 * Questa classe funge da presenter alla AvatarProfileManagementPresenter
 * Questa classe si occupa di gestire la logica per la richiesta di modifica del proprio avatar.
 * @author Umberto Martinati
 *
 */
public class AvatarProfileManagementPresenter implements WidgetPresenter{
	

	
	private AvatarProfileManagementDisplay display;
	
	/**
	 * Costruttore della classe. Esso si occupa di inizializzare i campi dati e di collegari
	 * i pulsanti della view.
	 */
	public AvatarProfileManagementPresenter(AvatarProfileManagementDisplay display){
		this.display=display;
		bind();
	}
	
	/**
	 * Questo metodo si occupa di aggiungere la logica in risposta alla pressione del pulsante
	 * per il cambio di avatar.
	 */
	private void bind(){
		display.getChangeAvatarButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				NewAvatarPopupPresenter popup=new NewAvatarPopupPresenter(new NewAvatarPopupView());
			}
		});
	}

	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
	

}
