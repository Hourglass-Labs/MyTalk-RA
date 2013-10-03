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
 *  1.3     | 2013/06/30 | Aggiunta metodo cambio email
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/26 | Aggiunta metodo bind e init
 * ---------+------------+------------------
 *  0.1     | 2013/06/16 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 */
package it.hourglass.myTalk.client.presenter.profilemanagement;

import it.hourglass.myTalk.client.presenter.display.PersonalDataProfileManagementDisplay;
import it.hourglass.myTalk.client.presenter.display.NewEmailConfirmPopupDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Questa classe funge da presenter alla ExtendedDataProfileManagementView
 * Questa classe si occupa di gestire la logica per la richiesta di modifica dei dati
 * estesi del proprio profilo
 * @author Umberto Martinati
 *
 */
public class NewEmailConfirmPopupPresenter {

	
	private final DialogBox safeSave = new DialogBox();
	private RPCServiceAsync RPCall = GWT.create(RPCService.class);
	private User user;
	private NewEmailConfirmPopupDisplay display;
	private PersonalDataProfileManagementDisplay parentDisplay;
	private String email;
	private boolean encryptionNeeded = false;
	
	/**
	 * Costruttore della clase. Si occupa di inizializzare i campi dati e di collegare
	 * i pulsanti della view con la logica attraverso il metodo bind
	 */
	public NewEmailConfirmPopupPresenter(String email, 
			PersonalDataProfileManagementDisplay parentDisplay,
			NewEmailConfirmPopupDisplay display,boolean encryption){
		this.user = Profile.getUser();
		this.display = display;
		this.parentDisplay = parentDisplay;
		this.email = email;
		this.encryptionNeeded = encryption;
		safeSave.setGlassEnabled(true);
		bind();
	}
	
	/**
	 * Questo metodo è utilizzato per aggiungere la logica in risposta alla pressione dei
	 * tasti della view.
	 * Se la nuova mail viene confermata (attraverso il controllo del codice di validazione)
	 * si assicura di salvare la modifica nel profilo locale e nel database.
	 */
	private void bind(){
		display.getConfirmButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				safeSave.setText("Verifica codice di validazione in corso...");
				safeSave.center();
				safeSave.show();
				if(RPCall == null) {
					RPCall = GWT.create(RPCService.class);
			    }
			    
				AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {
					safeSave.removeFromParent();
				}
				@Override
				public void onSuccess(Boolean result) {
					safeSave.removeFromParent();
					if(result.equals(false)){
						display.setError("Il codice di sicurezza non corrisponde.");
					}					
					else{	user.setEmail(email);
							Profile.saveUser(encryptionNeeded);
						 	display.close();
						 	parentDisplay.visualize();
					}
				}
			};
			 RPCall.checkValidation(user.getUniqueId(),
					 display.getSecretCode().getValue(), callback);		

			}
		});
		display.getCancelButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.close();
				
			}
		});
		
	}
}
