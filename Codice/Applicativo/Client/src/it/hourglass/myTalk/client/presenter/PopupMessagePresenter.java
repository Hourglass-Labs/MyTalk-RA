/*
 * Filename: PopupMessagePresenter.java
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
 *  1.0     | 2013/07/28  | Approvazione classe 
 *  ---------+------------+------------------
 *  0.1     | 2013/06/28  |Aggiunti metodi invio messaggi
 *  ---------+------------+------------------
 *  0.1     | 2013/05/20  |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.presenter.display.PopupMessageDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.PopupInformation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;
/**
 * Questa classe funge da presenter alla classe {@link it.hourglass.myTalk.client.view.PopupMessageView}
 *  e permette di aver accesso e di controllare i suoi elementi. Nello specifico,
 *  permette la creazione e l'invio di un nuovo messaggio di posta ad un 
 *  Utente amico.
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
public class PopupMessagePresenter{


	
	private String recipient;
	private String sender;
	private PopupMessageDisplay display;
	private final DialogBox safeSave = new DialogBox();
	/**
	 * Costruttore della classe. Inizializza le variabili.
	 * @param sender
	 * @param recipient
	 * @param display
	 */
	public PopupMessagePresenter(String sender,String recipient, PopupMessageDisplay display){
		this.sender=sender;
		this.recipient=recipient;
		this.display=display;
		safeSave.setGlassEnabled(true);
		bind();
	}
	/**
	 * Aggiunge ai relativi bottoni l'evento di invio del messaggio o 
	 * per chiudere la finestra del messaggio.
	 */
	private void bind(){
		this.display.getSendButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String content = display.getText().getValue();
				if(content.length()>255){
					display.error();
				}
				else{
					safeSave.setText("Invio messaggio in corso...");
					safeSave.center();
  			  		safeSave.show();
					Message mess = new Message(sender, recipient, content);
					sendMessage(mess);
				}
			}
		});
		
		this.display.getCancellButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.remove();				
			}
		});
	}
	/**
	 * Ritorna una variabile di tipo RPCServiceAsync per la chiamata di procedura remota.
	 * @return
	 */
	private static RPCServiceAsync getService() {
		return GWT.create(RPCService.class);
    	}
	/**
	 *  Metodo che perfeziona l'invio del messaggio vero e proprio (coincidente
	 *  con l'inserimento della voce nel \textit{database}) utilizzando 
	 *  un'apposita <code>RPC</code>. Al ritorno da quest'ultima, viene notificato 
	 *  il server WS così che a sua volta possa informare il destinatario 
	 *  dell'arrivo di un messaggio.
	 * @param mess
	 */
	private void sendMessage(final Message mess){
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				safeSave.removeFromParent();
				GWT.log(caught.getMessage(), null);
				PopupInformation info = new PopupInformation("Errore: database al momento irraggiungibile.",false);
			}
			public void onSuccess(String result) {
				GWT.log(result);
				safeSave.removeFromParent();
				PopupInformation info = new PopupInformation(result,false);
				display.remove();
				Profile.sentMessageNotificationWS(mess.getRecipient());
			}
	    };
	    getService().sendMessage(mess, callback);
	}
	
}
