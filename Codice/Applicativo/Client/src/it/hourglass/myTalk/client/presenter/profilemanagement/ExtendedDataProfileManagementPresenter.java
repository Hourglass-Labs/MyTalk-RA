/*
 * Filename: AvatarProfileManagementPresenter.java
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
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/26 | Aggiunta metodo bind e init
 * ---------+------------+------------------
 *  0.1     | 2013/06/16 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 */
package it.hourglass.myTalk.client.presenter.profilemanagement;


import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.presenter.display.ExtendedDataProfileManagementDisplay;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Questa classe funge da presenter alla ExtendedDataProfileManagementView
 * Questa classe si occupa di gestire la logica per la richiesta di modifica dei dati
 * estesi del proprio profilo
 * @author Umberto Martinati
 *
 */
public class ExtendedDataProfileManagementPresenter implements WidgetPresenter{

	
	private ExtendedDataProfileManagementDisplay display;
	private User user;
	private String currentLocation;
	private String hometown;
	private String altEmail;
	private String description;
	private String inspirations;
	private String interests;
	
	/**
	 * Costruttore della classe. Esso si occupa di inizializzare i campi dati e di collegari
	 * i pulsanti della view.
	 */
	public ExtendedDataProfileManagementPresenter(HandlerManager eventBus,ExtendedDataProfileManagementDisplay display){
		this.user=Profile.getUser();
		this.display=display;
		bind();
	}
	
	/**
	 * Questo metodo si occupa di aggiungere i pulsanti per richiedere l'abilitazione della modifica
	 *  dei dati e la richiesta di salvataggio dei datiestesi di un utente.
	 */
	private void bind(){
		display.getChangeButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.changeView();
			}
		});
		
		display.getConfirmButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				currentLocation = display.getCity().getValue();
				hometown = display.getHometown().getValue();
				altEmail = display.getAltEmail().getValue();
				description = display.getDescriptions().getValue();
				inspirations = display.getInspirations().getValue();
				interests = display.getInterests().getValue();
				if(checkValues()){		
					changeLocalValues();
					Profile.saveUser(false);
					display.visualizeView();
					}
				else{
					display.visualizeView();
				}
			}
		});
	}
	
	/**
	 * Questo metodo si occupa di controllare la validità dei dati inseriti.
	 * @return
	 */
	private boolean checkValues(){		
		if(description.length() > 255){
			display.showError("La lunghezza del campo 'Descrizione' deve essere minore di 256 caratteri");
			return false;
		}
		else display.showError("");
		
		if(inspirations.length() > 255){
			display.showError("La lunghezza del campo 'Ispirazioni' deve essere minore di 256 caratteri");
			return false;
		}
		else display.showError("");
		
		if(interests.length() > 255){
			display.showError("La lunghezza del campo 'Interessi' deve essere minore di 256 caratteri");
			return false;
		}
		else display.showError("");
		
		if(hometown.length() > 32){
			display.showError("La lunghezza del campo 'Citta' d'origine' deve essere minore di 33 caratteri");
			return false;
		}
		else display.showError("");
		if(currentLocation.length() > 32){
			display.showError("La lunghezza del campo 'Citta' di Residenza' deve essere minore di 256 caratteri");
			return false;
		}
		else display.showError("");
		return true;

	}
	
	/**
	 * Questo metodo si occupa di cambiare i valori dei dati nel profilo locale di un utente
	 */
	private void changeLocalValues(){
		user.setCurrentLocation(currentLocation);
		user.setHometown(hometown);
		user.setAltEmail(altEmail);
		user.setDescription(description);
		user.setInspirations(inspirations);
		user.setInterests(interests);
	}

	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
}
