/*
 * Filename: RegistrationPresenter.java
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
 *  0.3     | 2013/06/29  | Aggiunta metodi per controlli e registrazione
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/6 | Aggiunta metodo bind
 * ---------+------------+------------------
 *  0.1     | 2013/06/6  |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 */

package it.hourglass.myTalk.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;

import it.hourglass.myTalk.client.presenter.display.RegistrationDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.User;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasValue;
import com.sun.servicetag.RegistrationData;

import java.util.Date;
/**
 * Classe che si occupa del controllo e utilizzo dei dati inseriti dall'utente
 * durante la registrazione.
 * 
 * <p>Nel momento in cui l'Utente ha perfezionato la Registrazione, tutti i dati
 * inseriti vengono controllati. Se non vi sono errori, viene perfezionata 
 * l'operazione di registrazione all'applicativo. In caso contrario, gli errori
 * vengono segnalati ed è possibile reiterarla. </p>
 * 
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
public class RegistrationPresenter implements WidgetPresenter{  
	/**
	 * Interfaccia che fornisce metodi utili  all'elaborazione 
	 * dei dati inseriti dall'utente.
	 * 
	 * <p>I metodi dell'interfaccia vengono implementati così da permettere di
	 * ricavare i dati inseriti dall'utente</p>
	 * 
	 *@see it.hourglass.myTalk.client.view.RegistrationView
	 */
	private boolean allChecked;
	private final DialogBox safeSave = new DialogBox();
	private final RegistrationDisplay display;
	private final ValuesCheck VC = new ValuesCheck();
	private String uniqueId;
	private String email;
	private String password;
	private String rePassword;
	private String name;
	private String lastName;
	private String birthday;
	private String birthmonth;
	private String birthyear;
	private Date birthdate;
	private char gender;
  /**
   * Costruttore della classe.
   * 
   * <p>Riceve in parametro un oggetto di tipo 'display', che costituisce di fatto
   * la view attraverso la quale l'utente può inserire i dati</p>
   * 
   * @param display Costituisce in questo caso la view adibita alla registrazione.
   */
	public RegistrationPresenter(RegistrationDisplay display) {
		safeSave.setGlassEnabled(true);
		this.display = display;
		this.allChecked = true;
		bind();
	}
	
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
    /**
     * Metodo che associa un'evento al click del tasto "Registrati"
     * 
     * <p>Alla pressione del tasto "Registrati", vengono ricavati tutti i dati
     * inseriti dall'utente e realizzati i successivi controlli.</p>
     */
	public void bind() {
		this.display.getRegisterButton().addClickHandler(new ClickHandler() {  
    	public void onClick(ClickEvent event) {
    		allChecked = true;
    		uniqueId = display.getUsername().getValue().toLowerCase();
    	    email = display.getEmail().getValue();
    	    password = display.getPassword().getValue();
    	    rePassword = display.getRePassword().getValue();
    	    name = display.getName().getValue();
    	    lastName = display.getLastName().getValue();
    	    birthday = display.getBirthDay();
    	    birthmonth = display.getBirthMonth();
    	    birthyear = display.getBirthYear();
    	    gender = display.getGender().charAt(0);
            checkValues();
            if(allChecked){
            	safeSave.setText("Registrazione in corso...");
  			  	safeSave.center();
  			  	safeSave.show();
            	register(new User(uniqueId, email, password, name, lastName, birthdate, gender));
            	}
             }           
     });
		
		this.display.getShowConfirmCodeButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.changeView();			
			}
		});
		
		this.display.getConfirmCodeButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				safeSave.setText("Verifica in corso...");
  			  	safeSave.center();
  			  	safeSave.show();
				checkValidation();
			}
		});
  }	
	/**
	 * Metodo che verifica se i dati inseriti dall'utente per la registrazione
	 * sono stati inseriti e sono corretti.
	 */
	private void checkValues(){
		String uniqueErr = VC.checkUniqueId(uniqueId);
		display.setWrongUsername(uniqueErr);
		if(uniqueErr.length()!= 0) allChecked = false;
		
		String passwordsErr = VC.checkPasswords(password, rePassword);
		display.setWrongPassword(passwordsErr);
		if(passwordsErr.length()!= 0) allChecked = false;
		
		String emailErr = VC.checkEmail(email);
		display.setWrongEmail(emailErr);
		if(emailErr.length()!= 0) allChecked = false;
		
		String nameErr = VC.checkName(name);
		display.setWrongName(nameErr);
		if(nameErr.length()!= 0) allChecked = false;
		
		String lastNameErr = VC.checkLastName(lastName);
		display.setWrongLastName(lastNameErr);
		if(lastNameErr.length()!= 0) allChecked = false;
		
		birthdate = VC.checkDate(birthyear, birthmonth, birthday);
		if(birthdate == null) {
			display.setWrongDate("Data inserita non valida.");
			allChecked = false;
		}
		else display.setWrongDate("");
	}

	private static RPCServiceAsync getService() {
		return GWT.create(RPCService.class);
    	}
	/**
	 * Metodo che effettua la registrazione vera e propria passando 
	 * i dati inseriti al server.
	 * @param user
	 */
	private void register(User user){
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				safeSave.removeFromParent();
				GWT.log(caught.getMessage(), null);
				display.setOutcome("Errore del server: problemi nella persistenza dei dati.");
			}
			public void onSuccess(String result) {
				GWT.log(result);
				safeSave.removeFromParent();
				display.setOutcome(result);
			}
	    };
	    getService().register(user, callback);
	}
	/**
	 * Metodo che controlla la correttezza del codice segreto inserito per la registrazione. 
	 * Instaura una RPC per il confronto del codice inserito con quello risultante
	 * nel database.
	 * In caso affermativo si avvisa l'utente che può usare tutte le funzionalità 
	 * extra di MyTalk.
	 */
	private void checkValidation(){
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				safeSave.removeFromParent();
				display.setConfirmationOutcome("Errore di connessione. Il database non è momentaneamente raggiungibile.");
			}
			@Override
			public void onSuccess(Boolean result) {
				if(result.equals(false)){
					safeSave.removeFromParent();
					display.setConfirmationOutcome("Il codice di sicurezza non corrisponde a quello inviato...");
				}					
				else{
					safeSave.removeFromParent();
					display.setConfirmationOutcome("Account abilitato! Puoi ora utilizzare tutte le funzionalita' " +
							" extra di MyTalk.");
				}
			}
		};
		
		if(!display.getConfirmUniqueId().getValue().isEmpty())
		    getService().checkValidation(display.getConfirmUniqueId().getValue(), display.getConfirmCode().getValue(), callback);		  
	  }	
	}