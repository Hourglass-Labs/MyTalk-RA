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
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.4     | 2013/06/20  | Aggiunta metodi eliminazione amicizia
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/15 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/15 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.event.RemoveFriendContactEvent;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.presenter.display.FriendContactDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * Questa classe si occupa di gestire la logica per eliminare un'amicizia nella pagina di 
 * gestione delle amicizie.
 * @author Umberto
 *
 */
public class FriendContactPresenter implements WidgetPresenter{

	
	private HandlerManager eventBus;
	private FriendContactDisplay display;
	private String friendId;
	private final DialogBox safeSave = new DialogBox();
	
	/**
	 * Costruttore della classe. Si occupa di inizializzare i campi e di collegare i pulsante
	 * per eliminare un'amicizia della view corrispettiva (FriendContactView).

	 */
	public FriendContactPresenter(HandlerManager eventBus,FriendContactDisplay display, String friendUniqueId){
		this.eventBus=eventBus;
		this.display=display;
		this.friendId=friendUniqueId;
		safeSave.setGlassEnabled(true);
		bind();
	}
	
	/**
	 * Questo metodo si occupa di collegare la logica al pulsante di eliminazione di un'amicizia
	 * della view.
	 */
	private void bind(){	
		this.display.getRemoveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				safeSave.setText("Eliminazione contatto in corso...");
  			  	safeSave.center();
  			  	safeSave.show();
				deleteFriend();
				eventBus.fireEvent(new RemoveFriendContactEvent(friendId));
				
			}
		});
	}
	
	
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
	
	private static RPCServiceAsync getService() {
		return GWT.create(RPCService.class);
    	}
	/**
	 * Questo metodo si occupa della richiesta di rimozione di una amicizia.
	 * Si occuperà di registrare nel database la modifica e di notificare l'eliminazione 
	 * all'utente che è stato eliminato.
	 */
	private void deleteFriend(){
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				safeSave.removeFromParent();
				GWT.log(caught.getMessage(), null);
			}
			public void onSuccess(String outcome) {
				safeSave.removeFromParent();
				Profile.removeFriend(friendId);
				PopupInformation info = new PopupInformation(outcome,false);
				display.selfDelete();
				}
				
	    };
	    getService().removeFriend(Profile.getUser().getUniqueId(), friendId, callback);
	}
	

}
