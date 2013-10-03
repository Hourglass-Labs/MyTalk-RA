/*
 * Filename: FriendMessagePresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati
 * Date: 2013/06/27
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
 *  0.2    	|  2013/06/27 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/27 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.TextMessageView;
import it.hourglass.myTalk.client.presenter.display.MessageDisplay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Questa classe funge da presenter alla MessageView
 * Questa classe si occupa di gestire la logica per la visione e la cancellazione dei messaggi
 * ricevuti da un utente.
 * @author Umberto Martinati
 *
 */
public class MessagePresenter implements WidgetPresenter{

	private MessageDisplay display;
	private List<TextMessagePresenter> textMessage;
	
	
	/**
	 * Costruttore della classe.
	 * Si occupa di estrarre i messaggi e, per ogni messaggio estratto di creare un'istanza di 
	 * TextMessagePresenter la quale si occupa di gestire la logica di cancellazione del messaggio.
	 * Infine invoco il metodo addMessageView() per aggiungere i messaggi alla view.
	 * @param display
	 */
	public MessagePresenter(MessageDisplay display){
		this.display=display;
		textMessage= new ArrayList<TextMessagePresenter>();
		List<Message> textMessageList=Profile.getMessageList();
		
		Iterator<Message> iteratorTextMessage=textMessageList.iterator();
		
		while(iteratorTextMessage.hasNext()){
			Message message =iteratorTextMessage.next();
			if(message.getFriendReq()==false)
				textMessage.add(new TextMessagePresenter(new TextMessageView(message), message));
		}
		
		addMessageView();
		
	}
	
	/**
	 * Questo metodo si occupa di aggiungere alla view tutte le istanze di TextMessageView che
	 * sono state create.
	 */
	private void addMessageView(){
		Iterator<TextMessagePresenter> iteratorTextMessage=textMessage.iterator();
		while(iteratorTextMessage.hasNext()==true)
			display.addMessage(iteratorTextMessage.next().getView());
	}
	
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}

	
	
}
