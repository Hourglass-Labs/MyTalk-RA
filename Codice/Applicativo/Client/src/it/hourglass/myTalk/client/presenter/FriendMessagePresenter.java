/*
 * Filename: FriendMessagePresenter.java
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
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.3     | 2013/06/25  | Aggiunta metodo friendReqReply
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/20 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/20 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.presenter.display.FriendMessageDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * Questa classe si occupa di gestire la logica per accettare o rifiutare una
 * richiesta di amicizia.
 * author Umberto Martinati
 *
 */
public class FriendMessagePresenter implements WidgetPresenter{

	
	private FriendMessageDisplay display;
	private Message friendReq;
	private final DialogBox safeSave = new DialogBox();
	
	/**
	 * Costruttore della classe. Si occupa di inizializzare i campi e di collegare i pulsanti
	 * accept/refuse della view corrispettiva (FriendMessageView).
	 * @param display
	 * @param friendReq
	 */
	public FriendMessagePresenter(FriendMessageDisplay display, Message friendReq){
		this.friendReq = friendReq;
		this.display = display;
		safeSave.setGlassEnabled(true);
		bind();
	}
	/**
	 * Questo metodo si occupa di collegare la logica ai pulsanti della view FriendMessageView
	 * (di cui Display è un riferimento).
	 */
	private void bind(){
		this.display.getAcceptButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				safeSave.setText("Inoltro risposta in corso...");
  			  	safeSave.center();
  			  	safeSave.show();
				friendReqReply(true);
			}
		});
		
		this.display.getRefuseButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				safeSave.setText("Inoltro risposta in corso...");
  			  	safeSave.center();
  			  	safeSave.show();
				friendReqReply(false);
				
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
	 * Questo metodo si occupa di inviare la risposta alla richiesta di amicizia.
	 * Si occupa di registrare la modifica nel database e di mandare il messaggio di risposta all'utente 
	 * tramite il server webSocket (tramite il metodo Profile.addFriend(friendReq.getSender());)
	 */
	private void friendReqReply(boolean accepted){
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				safeSave.removeFromParent();
				GWT.log(caught.getMessage(), null);
			}
			public void onSuccess(Boolean result) {
				safeSave.removeFromParent();
				if(result){
					PopupInformation info = new PopupInformation(friendReq.getSender() + " e' stato aggiunto alla tua lista contatti.",false);
					Profile.addFriend(friendReq.getSender());
				}
				else{
					PopupInformation info = new PopupInformation("Richiesta rifiutata.",false);
				}
				Profile.removeMessage(friendReq);
				display.selfDelete();
				
			}
	    };
	    getService().friendshake(friendReq, accepted, callback);
	}

}
