/*
 * Filename: TextMessagePResenter.java
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
 *  0.2    	|  2013/06/25 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/25 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 */

package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.presenter.display.TextMessageDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.PopupInformation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

/**
 * Questa classe funge da presenter alla TextMessageView
 * Questa classe si occupa di gestire la logica per eliminare un messaggio che un utente ha ricevuto
 * (sia dalla lista locale che nel database).
 * ricevuti da un utente.
 * @author Umberto Martinati
 *
 */
public class TextMessagePresenter implements WidgetPresenter{

	
	private Message message;
	private TextMessageDisplay display;
	private final DialogBox safeSave = new DialogBox();
	

	
	/**
	 * Costruttore della classe.
	 * Si occupa di inizializzare i campi dati e di collegare i pulstante della view
	 * per l'eliminazione del messagio.
	 * @param display
	 * @param message
	 */
	public TextMessagePresenter(TextMessageDisplay display, Message message){
		this.message = message;
		this.display=display;
		safeSave.setGlassEnabled(true);
		bind();
	}
	
	
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
	
	/**
	 * Questo metodo si occupa di aggiungere la logica in risposta alla pressione del tasto
	 * elimina nella view TextMessageView.
	 * Una volta che viene premuto si occupa di richeidere l'eliminazione del messagio
	 */
	private void bind(){
		this.display.getRemoveButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				safeSave.setText("Cancellazione in corso...");
  			  	safeSave.center();
  			  	safeSave.show();
  				deleteMessage();
				
			}
		});
	}

	private static RPCServiceAsync getService() {
		return GWT.create(RPCService.class);
    	}
	
	/**
	 * Questa funzione si occupa di eliminare il messaggio dalla lista messaggi locale e dal database.
	 */
	private void deleteMessage(){
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				safeSave.removeFromParent();
				GWT.log(caught.getMessage(), null);
			}
			public void onSuccess(String outcome) {
				safeSave.removeFromParent();
				Profile.removeMessage(message);
				PopupInformation info = new PopupInformation(outcome,false);
				display.selfDelete();
				}
				
	    };
	    getService().deleteMessage(message, callback);
	}

}
