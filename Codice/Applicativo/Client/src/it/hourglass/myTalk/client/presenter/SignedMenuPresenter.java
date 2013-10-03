/*
 * Filename: SignedMenuPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0    | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.2    	|  2013/05/22 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/05/20 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */


package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.event.HomePageRequestEvent;
import it.hourglass.myTalk.client.event.CallPageRequestEvent;
import it.hourglass.myTalk.client.event.LogoutRequestEvent;
import it.hourglass.myTalk.client.event.ProfileManagementPageRequestEvent;

import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.presenter.display.SignedMenuDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
/**
 * Questa classe è il presenter del widget Menù.
 * Questa classe si occupa di eseguire i metodi per lo switch view del menu quando un utente esegue
 * il login
 * @author Umberto Martinati
 *
 */

public class SignedMenuPresenter implements WidgetPresenter{
	
	  private final HandlerManager eventBus;
	  private final SignedMenuDisplay display;
	  private RPCServiceAsync sessionManagmentSvc = GWT.create(RPCService.class);
	  
	  /**
	   * Questa interfaccia espone i metodi che la SignedMenuView implementerà e che il presenter 
	   * potrà richiedere.
	   * @author Umberto
	   *
	   */

	/**
	 * Costruttore della classe SignedMenuPresenter.
	 * Questo costruttore si occuperà di inizializzare i campi dati necessari e di collegare i pulsanti
	 * della View con i metodi di questa classe.
	 * @param eventBus
	 * @param display
	 */
	public SignedMenuPresenter(HandlerManager eventBus,SignedMenuDisplay display){
		this.eventBus=eventBus;
		this.display=display;
		bind();
	}
	
	/**
	 * Questa funzione ha il compito di assegnare al click di un bottone della SignedMenuView un metodo
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
		
		this.display.getLogoutButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutRequestEvent());
				doResetSession();
			}
		});
		
		
		this.display.getProfileManagementButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ProfileManagementPageRequestEvent());
			}
		});
	}

/**
 * Questo metodo ritorna il widget Menu.
 */
	public AbsolutePanel getView() {
		return display.getView();
	}
	
	/**
	 * Si occupa di invocare il metodo rpc resetSession per cancellare la sessione
	 * attiva con il server
	 */
	public void doResetSession() {
		if(sessionManagmentSvc == null) {
			sessionManagmentSvc = GWT.create(RPCService.class);
	    }
	    
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
		@Override
		public void onFailure(Throwable caught) {
		}
		@Override
		public void onSuccess(Boolean result) {
		}					

	};
	    sessionManagmentSvc.resetSession(callback);	
	}


	
}
