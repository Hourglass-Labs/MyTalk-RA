/*
 * Filename: ContactManagementPresenter.java
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
 *  0.4     | 2013/06/25 | Aggiunta metodi aggiunta e rimozione amici
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/18 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/18 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.FriendContactView;
import it.hourglass.myTalk.client.view.FriendMessageView;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.presenter.display.ContactManagementDisplay;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;


/**
 * Questa classe rappresenta il presenter della pagina ContactManagmentView.
 * Questa pagina si occupa di gestire le amicizie di un utente dando la possibilità di richiedere
 * l'amicizia ad un utente, eliminare amicizie o confermarle.
 * @author Umberto
 *
 */
public class ContactManagementPresenter implements WidgetPresenter{



	private HandlerManager eventBus;
	private ContactManagementDisplay display;
	private HashMap<String, Boolean> contactMap; 
	private List<FriendContactPresenter> friendContact;
	private List<FriendMessagePresenter> friendMessage;
	private final DialogBox safeSave = new DialogBox();
	
	/**
	 * Costruttore della classe. Si occupa di inizializzare i campi dati, e di estrarre le amicizie
	 * e le richieste di amicizia dal Profilo. Una volta che sono state estratte viene creato per
	 * ogni amico un'istanza di FriendContactPresenter la quale viene aggiunta nella HashMap friendContact
	 *  e per ogni messaggio un istanza di FriendMessagePresenter la quale viene aggiunta nell'hashMap friendMessage.
	 *  Infine si invoco i metodi addContact() e addMessage() per aggiungere i presenter alla view.
	 */
	public ContactManagementPresenter(HandlerManager eventBus,ContactManagementDisplay display) {
		
		this.eventBus=eventBus;
		this.display=display;
		safeSave.setGlassEnabled(true);
		List<Message> messageList=Profile.getMessageList();
		friendContact=new ArrayList<FriendContactPresenter>();
		friendMessage=new ArrayList<FriendMessagePresenter>();
		contactMap=Profile.getFriendList();
		
		
		Iterator<Entry<String, Boolean>> iteratorFriendContact=contactMap.entrySet().iterator();
		while(iteratorFriendContact.hasNext()){
			String name=iteratorFriendContact.next().getKey();
			friendContact.add(new FriendContactPresenter(eventBus,new FriendContactView(name),name));
		}
		
		Iterator<Message> iteratorFriendMessage=messageList.iterator();
		while(iteratorFriendMessage.hasNext()){
			Message message = iteratorFriendMessage.next();
			if(message.getFriendReq()==true)
				friendMessage.add(new FriendMessagePresenter(new FriendMessageView(message), message));	
		}
		addContact();
		addMessage();
		bind();
	}
	/**
	 * Questo metodo si occupa di aggiungere per ogni amico presente in friendContact la view corrispondente
	 * ad ogni presenter alla ContactManagmentView
	 */
	public void addContact(){
		Iterator<FriendContactPresenter> iteratorFriendContactPresenter=friendContact.iterator();
		while(iteratorFriendContactPresenter.hasNext())
			display.addFriend(iteratorFriendContactPresenter.next().getView());
	}
	
	/**
	 * Questo metodo si occupa di aggiungere per ogni richiesta di amicizia presente in friendMessage 
	 * la view corrispondente d ogni presenter alla ContactManagmentView
	 */
	public void addMessage(){
	Iterator<FriendMessagePresenter> iteratorFriendMessage=friendMessage.iterator();
	while(iteratorFriendMessage.hasNext())
		display.addFriendMessage(iteratorFriendMessage.next().getView());
	}
	
	/**
	 * Questo metodo si occupa di aggiungere la logica ai bottoni presenti nella ContactManagmentView.
	 */
	private void bind(){
		display.getFriendshipRequestButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(display.getUserRequest().getValue().equals(Profile.getUser().getUniqueId())){
					PopupInformation info = new PopupInformation("Non e' possibile mandare richieste di amicizia a se stessi.",true);
				}
				else{
					safeSave.setText("Invio richiesta in corso...");
					safeSave.center();
				  	safeSave.show();
				  	String content = "FriendRequest";
					Message mess = new Message(Profile.getUser().getUniqueId(),
							display.getUserRequest().getValue(), content);
					mess.setFriendReq(true);
					friendReq(mess);
				}
						
			}
		});
	}
	
	private static RPCServiceAsync getService() {
		return GWT.create(RPCService.class);
    	}
	
	/**
	 * Questo metodo si occupa di inviare una  richiesta di amicizia
	 * @param mess
	 */
	private void friendReq(final Message mess){
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
				Profile.friendReqNotificationWS(mess.getRecipient());
			}
	    };
	    getService().sendMessage(mess, callback);
	}
	
	/**
	 * Questo metodo si occupa di restituire la ContactManagmentView attuale.
	 */
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}

	
}
