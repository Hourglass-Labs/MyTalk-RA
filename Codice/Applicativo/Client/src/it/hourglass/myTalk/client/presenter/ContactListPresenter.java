/*
 * Filename: ContactListPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati
 * Date: 2013/06/20
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
 *  0.4     | 2013/06/26  | Aggiunta metodi elimiznazione contatto
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/21 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/20 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.client.presenter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;
import it.hourglass.myTalk.client.presenter.display.ContactListDisplay;

import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.event.ContactManagementPageRequestEvent;
import it.hourglass.myTalk.client.presenter.ContactListPresenter;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.UserListView;


/**
 * Questa classe funge da presenter alla ContactListView
 * Questa classe si occupa di gestire la logica per la creazione della ContactList per un utente 
 * che si è autenticato.
 * @author Umberto Martinati
 *
 */
public class ContactListPresenter implements WidgetPresenter{

	

	
	HashMap<String, Boolean> contactList;
	WSConnection wsconnection;
	HandlerManager eventBus;
	Iterator<Entry<String, Boolean>> iterator;
	ContactListDisplay display;
	HashMap<String, UserListPresenter> friendContactList;
	/**
	 * Costuttore della classe ContactListPresenter. Esso si occupa di inizializzare i campi e di
	 * inizializzare l'hashMap contenente gli amici dell'utente. Per fare ciò la classe recuper la FirendList dal profilo e 
	 * successivamente si occupa di estrarre i dati e creare per ogni contatto un'istanza di 
	 * UserListPresenter inserendolo in friendContactList. Infine per ogni UserListPresenter creato si occupa di aggiungere la
	 * UserListView alla ContactListView
	 */
	public ContactListPresenter(HandlerManager eventBus, WSConnection wsconnection,ContactListDisplay display){
		this.contactList=Profile.getFriendList();
		this.eventBus=eventBus;
		this.wsconnection=wsconnection;
		this.display=display;
		iterator=contactList.entrySet().iterator();
		friendContactList=new HashMap<String, UserListPresenter>();
		initList();
		addListView();
		bind();
	}
	
	/**
	 * Questo metodo si occupa di creare per ogni utente presente nella friendList
	 * un istanza di UserListPresenter aggiungendo tale oggetto in friendContactList.
	 */
	public void initList(){
		while(iterator.hasNext()){
			Entry<String, Boolean> userInformation=iterator.next();
			friendContactList.put(userInformation.getKey(),new UserListPresenter(eventBus,wsconnection,userInformation,new UserListView(userInformation)));
		}
	}
	
	/**
	 * Questo metodo si occupa di aggiungere alla ContactListView tutte le istanze di UserListView
	 * contenute in friendContactList.
	 */
	public void addListView(){
		Iterator<Entry<String, UserListPresenter>> viewIterator=friendContactList.entrySet().iterator();
		while(viewIterator.hasNext()){
			display.addUser(viewIterator.next().getValue().getView());
		}
	}
	
	/**
	 * Questo metodo si occupa di gestire la logica del bottone per la richiesta della gestione
	 * delle amicizie.
	 */
	private void bind(){
		display.getContactManagmentPage().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ContactManagementPageRequestEvent());
				
			}
		});
	}
	
	/**
	 * Questo metodo si occupa di restituire la ContactListView.
	 */
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
	
	/**
	 * Questo metodo richiede l'eliminazione della ContactListView.
	 */
	public void removeContactList(){
		this.display.removeContactList();
	}

	/**
	 * Questo metodo si occupa di cambiare lo stato del contatto con identificativo name.
	 */
	public void changeStatus(String name,Boolean status){
		if(friendContactList.containsKey(name))
			friendContactList.get(name).changeStatus(status);
	}	
	
	/**
	 * Questo metodo si occupa di rimuovere dalla friendContactList, e dalla ContactListView un contatto.
	 */
	public void removeContact(String contact){
		friendContactList.get(contact).remove();
		friendContactList.remove(contact);
	}
	
}
