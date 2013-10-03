/*
 * Filename: LoginPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati - Giovanni Morlin
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  2.0     | 2013/09/10  | Approvazione classe
 *  ---------+------------+------------------
 *  1.1     | 2013/09/1  | Cambio package interfaccia display
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.3     | 2013/06/29  | Aggiunta metodi per creazione e lettura sessione
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/28 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/28 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;

import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.event.LoginRequestEvent;
import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.presenter.display.LoginDisplay;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Questa classe funge da presenter alla LoginView
 * Questa classe si occupa di gestire la logica per la richiesta di autenticazione di un utente
 * nel sistema.
 * @author Umberto Martinati
 *
 */
public class LoginPresenter implements WidgetPresenter{  

	private final DialogBox safeSave = new DialogBox();
  private final HandlerManager eventBus;
  private final LoginDisplay display;
  private RPCServiceAsync sessionManagmentSvc = GWT.create(RPCService.class);
  
  
  public LoginPresenter(WSConnection wsconnection, HandlerManager eventBus, LoginDisplay display) {
	safeSave.setGlassEnabled(true);  
    this.eventBus = eventBus;
    this.display = display;
    bind();
  }
    
   /**
    * Riceve come parametri lo username e la password inserita nell'apposita text box
    * della view ed invoca il metodo rpc checkLogin per controllare se i dati
    * inserti corrispondono a un profilo utente salvato nel database.
    * Nel caso in cui i si trovi un riscontro positivo sui parametri inseriti
    * in sede di login verr�ｽcambiata la view aggiungendo le funzioni per
    * l'utente loggato
    * 
    * @author giovannimorlin
    * @param username
    * @param password
    * 
    * 
    */
	public void doLoginControl(final String username,final String password) {
		if(sessionManagmentSvc == null) {
			sessionManagmentSvc = GWT.create(RPCService.class);
	    }
	    
		AsyncCallback<Boolean> callbackBoolean = new AsyncCallback<Boolean>() {
		@Override
		public void onFailure(Throwable caught) {
		}
		@Override
		public void onSuccess(Boolean result) {
			if(!result){
			safeSave.removeFromParent();
			display.showLoginError("L'username o la password inseriti non sono corretti o l'account non e' stato" +
					" ancora confermato.");
		}					
		else{
			safeSave.removeFromParent();
			display.showLoginError("Login effettuato con successo.");
			Profile.getUser().setUniqueId(username);
			eventBus.fireEvent(new LoginRequestEvent());
			}
		}
	};
	    sessionManagmentSvc.checkLogin(username, password, callbackBoolean);	
	}
  
	/**
	 * Ridefinisce le operazioni da eseguire alla pressione di un bottone
	 * della LoginView.Viene recuperata la password e lo username, e invocato
	 * il metodo doLoginControl per il controllo della corrispondeza 
	 * tra username e password 
	 */
  public void bind() {
    this.display.getLoginButton().addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {
    		String password = (String) display.getPassword().getValue();
        	String uniqueId = (String) display.getUsername().getValue();
            doLoginControl(uniqueId, password);
            safeSave.setText("Login in corso...");
			safeSave.center();
			safeSave.show();
    	}
    });
  }
@Override
public AbsolutePanel getView() {
	return display.getView();
}


  
}