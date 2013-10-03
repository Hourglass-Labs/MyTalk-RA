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

import java.util.Date;

import it.hourglass.myTalk.client.presenter.ValuesCheck;
import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.presenter.display.PersonalDataProfileManagementDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.view.profilemanagement.NewEmailConfirmPopupView;

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
 * Questa classe funge da presenter alla PersonalDataProfileManagmentView
 * Questa classe si occupa di gestire la logica per la richiesta di modifica dei dati
 * personali del proprio profilo
 * @author Umberto Martinati
 *
 */
public class PersonalDataProfileManagementPresenter implements WidgetPresenter{
	

	private ValuesCheck valueCheck=new ValuesCheck();
	private User user;
	private PersonalDataProfileManagementDisplay display;
	private String email;
	private String password;
	private String rePassword;
	private String name;
	private String lastName;
	private String birthday;
	private String birthmonth;
	private String birthyear;
	private Date birthdate;
	private boolean encryptionNeeded;
	private char gender;
	private RPCServiceAsync RPCall = GWT.create(RPCService.class);
	private final DialogBox safeSave = new DialogBox();
	
	
	/**
	 * Costruttore della classe. Esso si occupa di inizializzare i campi dati e di collegari
	 * i pulsanti della view.
	 */
	public PersonalDataProfileManagementPresenter(PersonalDataProfileManagementDisplay display){
		this.user = Profile.getUser();
		this.display = display;
		safeSave.setGlassEnabled(true);
		bind();
	}
	/**
	 * Questo metodo si occupa di aggiungere la logica di controllo in risposta alla pressione
	 * di un tasto della view.
	 * In risposta alla pressione del pulsante change richiede che la view diventi modificabile.
	 * In risposta alla pressione del pulsante confirm si controlla se i dati inseriti siano
	 * validi, se è stata modificata l'email si invia il codice di conferma e infine
	 * si richiede il salvataggio delle modifiche (tranne quelle della mail).
	 */
	private void bind(){
		display.getChangeButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.change();		
			}
		});
		
		display.getConfirmButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				email = display.getEmailTextBox().getValue();
	    	    password = display.getpasswordTextBox().getValue();
	    	    rePassword = display.getConfirmPasswordTextBox().getValue();
	    	    name = display.getNameTextBox().getValue();
	    	    lastName = display.getLastNameTextBox().getValue();
	    	    birthday = display.getDayListBox();
	    	    birthmonth = display.getMonthListkBox();
	    	    birthyear = display.getYearListBox();
	    	    gender = display.getSexListBox().charAt(0);
	    		encryptionNeeded = false;
				
	    	    if(checkValues()){
	    	    	if(!user.getPassword().equals(password))
	    	    		encryptionNeeded = true;
					if(!(user.getEmail().equals(email))){
						final String oldEmail = user.getEmail();
						final String newEmail = email;
						sendValidation(oldEmail, newEmail);
						return;
					}
					changeLocalValues();
					Profile.saveUser(encryptionNeeded);
					display.visualize();
				}
				else
					display.visualize();
				
			}
		});
	}
	
	/**
	 * Questo metodo si occupa di controllare se i dati inseriti sono validi.
	 * @return
	 */
	private boolean checkValues(){		
		String controlName = valueCheck.checkName(name);
		display.showError(controlName);
		if(controlName.length()!=0)
			return false;
		
		String controlLastName = valueCheck.checkLastName(lastName);
		display.showError(controlLastName);
		if(controlLastName.length()!=0)
			return false;
		
		if(!(password.equals(user.getPassword()))){
			String passwordsErr = valueCheck.checkPasswords(password, rePassword);
			display.showError(passwordsErr);
			if(passwordsErr.length() !=0)
				return false;
		}
		
		String controlEmail = valueCheck.checkEmail(email);
		display.showError(controlEmail);
		if(controlEmail.length()!=0)
			return false;
		
		birthdate = valueCheck.checkDate(birthyear, birthmonth, birthday);
		if(birthdate == null) {
			display.showError("Data inserita non valida.");
			return false;
		}
		else display.showError("");
		
		return true;
	}
	
	/**
	 * Questo metodo si occupa di salvare le modifiche nel profilo locale.
	 */
	private void changeLocalValues(){
		user.setName(name);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setPassword(password);
		user.setBirthdate(birthdate);
	}

	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
	
	/**
	 * Questo metodo si occupa di inviare il codice di validazione per la modifica del cambio
	 * dell'email.
	 * @param oldEmail
	 * @param newEmail
	 */
	private void sendValidation(final String oldEmail, final String newEmail){
		if(RPCall == null) {
			RPCall = GWT.create(RPCService.class);
	    }
	    safeSave.setText("Hai scelto di modificare il tuo indirizzo email. Ti stiamo mandando un'email per averne conferma...");
		safeSave.center();
		safeSave.show();
		
		AsyncCallback<String> callback = new AsyncCallback<String>() {
		@Override
		public void onFailure(Throwable caught) {
			safeSave.removeFromParent();
			PopupInformation notification = new PopupInformation("Impossibile accedere al servizio.",false);
		}
		@Override
		public void onSuccess(String result) {
			safeSave.removeFromParent();
			changeLocalValues();
			NewEmailConfirmPopupPresenter popup = 
					new NewEmailConfirmPopupPresenter(newEmail, display,
							new NewEmailConfirmPopupView(), encryptionNeeded);
		}
	};
	    RPCall.setValidation(user.getEmail(), callback);		  
  }
	
}
