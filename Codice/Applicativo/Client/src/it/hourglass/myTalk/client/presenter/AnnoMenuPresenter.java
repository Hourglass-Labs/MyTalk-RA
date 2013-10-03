/*
 * Filename: AnnoMenuPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati
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
 *  ---------+------------+------------------
 *  0.2    	|  2013/05/10 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/05/10 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */


package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.event.HomePageRequestEvent;
import it.hourglass.myTalk.client.event.CallPageRequestEvent;
import it.hourglass.myTalk.client.event.LoginPageRequestEvent;
import it.hourglass.myTalk.client.event.PasswordRecoverPageRequestEvent;
import it.hourglass.myTalk.client.event.RegistrationPageRequestEvent;


import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.presenter.display.AnnoMenuDisplay;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;
/**
 * Questa classe è il presenter del widget Menù.
 * Questa classe si occupa di eseguire i metodi per lo switch view del menu di un utente anonimo
 * @author Umberto Martinati
 *
 */

public class AnnoMenuPresenter implements WidgetPresenter{
	
	  private final HandlerManager eventBus;
	  private final AnnoMenuDisplay display;
	  
	/**
	 * Costruttore della classe AnnoMenuPresenter.
	 * Questo costruttore si occuperïà di inizializzare i campi dati necessari e di collegare i pulsanti
	 * della View con i metodi di questa classe.
	 * @param eventBus
	 * @param display
	 */
	public AnnoMenuPresenter(HandlerManager eventBus,AnnoMenuDisplay display){
		this.eventBus=eventBus;
		this.display=display;
		bind();
	}
	
	/**
	 * Questa funzione ha il compito di assegnare al click di un bottone della MenuView un metodo
	 * del MenuPresenter.
	 */
	private void bind(){
		
		this.display.getHomeButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new HomePageRequestEvent());
			}
		});
		
		this.display.getCallButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CallPageRequestEvent());
			}
		});
		
		this.display.getLoginButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LoginPageRequestEvent());
			}
		});
		
		this.display.getRegistrationButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new RegistrationPageRequestEvent());
				
			}
		});
		
		this.display.getRecuperaPasswordButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new PasswordRecoverPageRequestEvent());
			}
		});
	}

/**
 * Questo metodo ritorna il widget Menu.
 */
	public AbsolutePanel getView() {
		return display.getView();
	}


	
}