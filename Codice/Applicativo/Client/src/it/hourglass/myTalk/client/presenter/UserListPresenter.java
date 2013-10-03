/*
 * Filename: LoginPresenter.java
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
 *  0.2    	|  2013/06/26 | Aggiunta metodo bind e cambio stato
 * ---------+------------+------------------
 *  0.1     | 2013/06/26 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 */
package it.hourglass.myTalk.client.presenter;


import java.util.Map.Entry;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;

import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.event.CallPageRequestEvent;
import it.hourglass.myTalk.client.event.FriendContactProfilePageRequest;
import it.hourglass.myTalk.client.presenter.display.UserListDisplay;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.view.PopupMessageView;

/**
 * Questa classe funge da presenter alla UserListView
 * Questa classe si occupa di gestire la logica per la richiesta di una chiamata rapida, della
 * visione del profilo di un utente e della richiesta di invio di un messaggio verso un utente e
 * per il cambio dello stato nella lista contatti.
 * @author Umberto Martinati
 *
 */
public class UserListPresenter implements WidgetPresenter{


	
	private HandlerManager eventBus;
	private WSConnection wsConnection;
	private Entry<String, Boolean> contactInformation;
	private UserListDisplay display;
	
	/**
	 * Costruttore della classe.
	 * Si occupa di inizializzare i campi dati e collegare la logica ai pulsanti.
	 * @param eventBus
	 * @param wsConnection
	 * @param contactInformation
	 * @param view
	 */
	public UserListPresenter(HandlerManager eventBus,WSConnection wsConnection, Entry<String, Boolean> contactInformation,UserListDisplay view) {
		this.wsConnection=wsConnection;
		this.contactInformation=contactInformation;
		this.display=view;
		this.eventBus=eventBus;
		bind();
	}
	
	public AbsolutePanel getView() {
		return display.getView();
	}
	
	/**
	 * Metodo che collega la logica ai pulsanti della view corrispettiva.
	 * Per la chiamata rapida si occupa di settare il campo RapidCall di User e di lanciare l'evento
	 * di richiesta della pagina di chiamata.
	 * Per l'invio di un messaggio si occupa di creare un'istanza di PopupMessagePresenter il quale
	 * gestisce la logica di invio di un messaggio.
	 * Per richiedere il profilo di un amico si occupa di settare il campo ViewFriendProfile di User e 
	 * di lanciare l'evento di richiesta della pagina di visione del profilo.
	 */
	private void bind(){
		this.display.getCallButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(contactInformation.getValue()==true){
				Profile.getUser().setRapidCall(contactInformation.getKey());
				eventBus.fireEvent(new CallPageRequestEvent());
				}
				else
				{
					PopupInformation popup=new PopupInformation("Il contatto che stai chiamando non e' online", true);
				}
			}
		});
		
		this.display.getMessageButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PopupMessagePresenter popup=new PopupMessagePresenter(Profile.getUser().getUniqueId(), contactInformation.getKey(), new PopupMessageView(Profile.getUser().getUniqueId()));
			}
		});
		
		this.display.getProfileButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Profile.getUser().setViewFriendProfile(contactInformation.getKey());
				eventBus.fireEvent(new FriendContactProfilePageRequest());
			}
		});
	
	}
	
	/**
	 * Questo metodo si occupa di cambiare lo stato dell'utente. Richiede poi il cambio
	 * dell'icona visiva dello stato di un utente.
	 * @param status
	 */
	public void changeStatus(boolean status){
		contactInformation.setValue(status);
		display.changeStatus(status);
	}
	
	public void remove(){
		this.display.remove();
	}
	

}
