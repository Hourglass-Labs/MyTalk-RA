/*
 * Filename: CallPopuPresenter.java
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
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.1     | 2013/06/2  |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import it.hourglass.myTalk.client.event.CallRequestEvent;
import it.hourglass.myTalk.client.event.CallRequestRefusedEvent;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.presenter.display.CallPopupDisplay;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Timer;

/**
 * Questa classe funge da presenter all popup CallPopup
 * Questa classe si occupa di definire il comportamento che il programma deve avere quando clicco 
 * uno dei pulsanti dell popup CallPopup.
 * Inoltre si occupa di rifiutare automaticamente la chiamata se essa viene ignorata per più di  30 secondi.
 * @author Umberto Martinati
 *
 */
public class CallPopupPresenter{
	
	
	  private final HandlerManager eventBus;
	  private final CallPopupDisplay display;
	  private boolean answered=false;
	  /**
	   * Costruttore della classe. Esso si occupa di inizializzare i campi dati e di inizializzare il timer
	   * per ignorare la chiamata.
	   */
	  public CallPopupPresenter(HandlerManager eventBus, CallPopupDisplay display) {
	    this.eventBus = eventBus;
	    this.display = display;
	    
	    
	    Timer timer = new Timer(){
			  
	    	public void run() {
	    		if(!answered){
				  CallPopupPresenter.this.display.chiudi();
				  if(CallPopupPresenter.this.display.getName().length()>8)
				  new PopupInformation("Ti ha chiamato "+  CallPopupPresenter.this.display.getName(),false);
				  CallPopupPresenter.this.eventBus.fireEvent(new CallRequestRefusedEvent()); 
			    }
	    	}
		  };
		  timer.schedule(30000);
	    bind();
	  }
	   
	  /**
	   * Questo metodo si occupa di aggiungere la logica alla pressione di un tasto del popup.
	   */
	  public void bind() {
		  this.display.getAcceptButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				answered=true;
				display.chiudi();
				eventBus.fireEvent(new CallRequestEvent());
				
			}
		});
		  
		  this.display.getRefuseButton().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					answered=true;
					display.chiudi();
					eventBus.fireEvent(new CallRequestRefusedEvent());
				}
			});
		  
	  }



	  
	  
}
