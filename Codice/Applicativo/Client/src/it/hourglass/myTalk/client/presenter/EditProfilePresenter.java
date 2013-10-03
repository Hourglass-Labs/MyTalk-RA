/*
 * Filename: EditProfilePresenter.java
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
 *  0.2    	|  2013/06/28 | Aggiunta metodo bind e init
 * ---------+------------+------------------
 *  0.1     | 2013/06/16 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import it.hourglass.myTalk.client.event.MessagePageRequestEvent;
import it.hourglass.myTalk.client.presenter.profilemanagement.AvatarProfileManagementPresenter;
import it.hourglass.myTalk.client.presenter.profilemanagement.ExtendedDataProfileManagementPresenter;
import it.hourglass.myTalk.client.presenter.profilemanagement.PersonalDataProfileManagementPresenter;
import it.hourglass.myTalk.client.view.profilemanagement.AvatarProfileManagementView;
import it.hourglass.myTalk.client.view.profilemanagement.ExtendedDataProfileManagementView;
import it.hourglass.myTalk.client.view.profilemanagement.PersonalDataProfileView;
import it.hourglass.myTalk.client.presenter.display.EditProfileDisplay;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Costruisce ed espone le sottoclassi designate alle modifiche delle varie
 * sezioni del profilo (avatar, dati personali, dati estesi), oltre che
 * fornire l'accesso all'amministrazione dei messaggi.
 * @author Umberto Martinati
 * @versione 1.0
 */
public class EditProfilePresenter implements WidgetPresenter{  

  
  private final HandlerManager eventBus;
  private final EditProfileDisplay display;
  private AvatarProfileManagementPresenter avatarPresenter;
  private PersonalDataProfileManagementPresenter personalDataPresenter;
  private ExtendedDataProfileManagementPresenter extendedDataPresenter;
  /**
   * Costruttore della classe. Assegna alle variabili i parametri passati ed evoca 
   * i metodi per l'inizializzazione.
   * @param eventBus
   * @param display
   */
  public EditProfilePresenter(HandlerManager eventBus, EditProfileDisplay display) {
    this.eventBus = eventBus;
    this.display = display;
    init();
    bind();
    
  }
  /**
   * Vengono create e assegnate le istanze di presenter necessarie da associare 
   * ai campi privati della classe. Esse gestiranno la modifica delle varie 
   * sezioni del Profilo dell'utente.
   */
  private void init(){
	  avatarPresenter=new AvatarProfileManagementPresenter(new AvatarProfileManagementView());
	  personalDataPresenter=new PersonalDataProfileManagementPresenter(new PersonalDataProfileView());
	  extendedDataPresenter=new ExtendedDataProfileManagementPresenter(eventBus, new ExtendedDataProfileManagementView());
	  display.addAvatarView(avatarPresenter.getView());
	  display.addPersonalDataView(personalDataPresenter.getView());
	  display.addExtendedDataView(extendedDataPresenter.getView());
	  display.addFooter();
  }
  /**
   * Aggiunge il comportamento che il programma deve avere quando viene cliccato
   * il tasto per la visualizzazione dei messaggi.
   */
  private void bind() {
    this.display.getMessageButton().addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {
    		eventBus.fireEvent(new MessagePageRequestEvent());
	}
    });
    

  }


@Override
public AbsolutePanel getView() {
	// TODO Auto-generated method stub
	return display.getView();
}

  
}