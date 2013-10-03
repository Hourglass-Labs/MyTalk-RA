/*
 * Filename: CallPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati - Paolo Bustreo - Thomas Rossetto
 * Date: 2013/06/10
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
 *  0.4     | 2013/06/30  | Aggiunta metodi chat
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/10 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/10 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;



import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.ui.Label;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import it.hourglass.myTalk.client.communication.Call;
import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.communication.Call.Direction;
import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.wrappers.ConsoleLog;
import it.hourglass.myTalk.client.presenter.display.CallDisplay;

/**
 * Questa classe funge da presenter alla CallView
 * Questa classe permette di aver accesso e di controllare gli elementi della callView, inoltre
 * si occupa di definire i metodi di risposta alle interazioni con l'utente.
 * @author Umberto Martinati
 *
 */

public class CallPresenter implements WidgetPresenter{  
  
  
  private final WSConnection wsconnection;
  private final HandlerManager eventBus;
  private final CallDisplay display;
  
  /**
   * Costruttore della classe CallPresenter.
   * Inizializza i campi dati e si occupa di collegare i bottoni della view con i metodi 
   * necessari
   * 
   * @param wsconnection
   * @param eventBus
   * @param display
   */

  public CallPresenter(WSConnection wsconnection, HandlerManager eventBus, CallDisplay display,boolean request) {

	    this.wsconnection = wsconnection;
	    this.eventBus = eventBus;
	    this.display = display;
	    bind();

	    if(Profile.getUser().getRapidCall()!=null){
			display.setCallname(Profile.getUser().getRapidCall());
	    	this.wsconnection.doCall(Call.Direction.OUT,Profile.getUser().getRapidCall(), display.getLocalVideo(), display.getGuestVideo(), display.getChat(),3, display.getLenght(), display.getByteU(), display.getByteD());
			mostra();
			Profile.getUser().setRapidCall(null);
	    }
	    
	    if(request==true){
		mostra();
		this.wsconnection.doCall(display.getLocalVideo(), display.getGuestVideo(), display.getChat(), display.getLenght(), display.getByteU(), display.getByteD(),display.getCalee());
	    return;
	    }

	    

	  }
  
  
  public void bind() {
    this.display.getSendButton().addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {
    		sendChatMessage();
	}
    });
    
    this.display.getChat().addKeyDownHandler(new KeyDownHandler() {

		@Override
		public void onKeyDown(KeyDownEvent event) {
			if(event.getNativeKeyCode()== KeyCodes.KEY_ENTER){
				sendChatMessage();
			}

		}
	});
    
    this.display.getCallButton().addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			String s = display.getStreamRequest();
			int n;
			if(s.equals("Audio")){
				n=1;
			}
			else if(s.equals("Audio/Video")){
				n=2;
			}
			else if (s.equals("Audio/Video/Chat")){
				n=3;
			}
			else {
				n=4;
			}
			wsconnection.doCall(Call.Direction.OUT,display.getcallUserTextBox().getText(), display.getLocalVideo(), display.getGuestVideo(), display.getChat(),n, display.getLenght(), display.getByteU(), display.getByteD());
			mostra();

		}
	});
    
    this.display.getCloseButton().addClickHandler(new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
			wsconnection.closeCall(Direction.OUT);
		}
	});
  }

  /**
   * Metodo per mostrare l'elemento video e le statistiche della chiamata.
   */
  private void mostra(){
	  this.display.mostra();
  }
  
@Override
public AbsolutePanel getView() {
	// TODO Auto-generated method stub
	return display.getView();
}
  

private void sendChatMessage(){
	wsconnection.getCall().sendMessage(wsconnection.getMyNick().concat(": ").concat(display.getTextBoxInvia().getText().toString().concat(" \n")));
	String lastChat=display.getChat().getText().toString();
	display.getChat().setText(lastChat.concat(wsconnection.getMyNick().concat(": ").concat(display.getTextBoxInvia().getText().concat(" \n"))));
	display.getTextBoxInvia().setValue("");
	ConsoleLog.consoleLog("INVIO MESSAGGIO");
}
  
  
}