/*
 * Filename: FriendMessagePresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati - Giovanni Morlin - Lorenzo Cresce Gioele
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
 *  0.3   	|  2013/06/30 | Aggiunta metodi per l'approvazione
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.event.HomePageRequestEvent;
import it.hourglass.myTalk.client.event.LoginRequestEvent;
import it.hourglass.myTalk.client.presenter.WidgetPresenter;
import it.hourglass.myTalk.client.presenter.display.PasswordRecoverDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
/**
 * Classe 
 * @author Giovanni Morlin
 *
 */
public class PasswordRecoverPresenter implements WidgetPresenter{
	
	
	  private final DialogBox safeSave = new DialogBox();
	  private final HandlerManager eventBus;
	  private final PasswordRecoverDisplay display;
	  private RPCServiceAsync RPCall = GWT.create(RPCService.class);
	  private String idUtente = "";
	  private final ValuesCheck VC = new ValuesCheck();
		
	  public PasswordRecoverPresenter(WSConnection wsconnection, HandlerManager eventBus, PasswordRecoverDisplay display) {
		safeSave.setGlassEnabled(true);
		this.eventBus = eventBus;
	    this.display = display;
	    bind();
	  }
	  
	  /**
	   * Si occupa di assegnare al click di un bottone della RecuperoPasswordView un metodo
	   * della classe RecuperoPasswordPresenter.
	   */
	  private void bind(){
		 this.display.getRequestButton().addClickHandler(new ClickHandler() {// EMAIL
			
			@Override
			public void onClick(ClickEvent event) {
				doRequestRecover();
			}
		});
		 
		 this.display.getContineuButton().addClickHandler(new ClickHandler() {// CODICE SEGRETO
			
			@Override
			public void onClick(ClickEvent event) {
				doContinueRequest();
				
			}
		});
		 
		 this.display.getConfirmButton().addClickHandler(new ClickHandler() {//CAMBIO PASSWORD
			
			@Override
			public void onClick(ClickEvent event) {
				doSaveNewPassword();
			}
		});
	  }

	  /**
	   * Riceve come parametro una mail valida e si occupa di invocare il 
	   * metodo rpc setValidation per verificare che la mail sia salvata
	   * nel database, in caso contrario viene segnalato un'erroe a video.
	   * Se la mail corrisponde viene cambiata la view e viene salvato 
	   * l'identificativo univoco dell'utente a cui ?associata la mail 
	   * ritornato dal metodo rpc setValidation 
	   * 
	   * @param email
	   */
	  private void doValidation(String email){
			if(RPCall == null) {
				RPCall = GWT.create(RPCService.class);
		    }
		    
			AsyncCallback<String> callback = new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				safeSave.removeFromParent();
			}
			@Override
			public void onSuccess(String result) {
				safeSave.removeFromParent();
				if(result.length()==0){
					display.showLoginError("L'indirizzo inserito non e' presente nella piattaforma MyTalk");
				}					
				else{
					idUtente = result;
					display.switchFase2();
				}
			}
		};
		    RPCall.setValidation(email, callback);		  
	  }
	  
	  /**
	   * Controlla che la mail inserita per il cambio password sia valida 
	   * in caso contrario segnala a video un errore.
	   * Se la mail risulta valida viene passata come parametro al metodo doValidation
	   */
	  private void doRequestRecover(){
		  String res = VC.checkEmail(this.display.getEmail().getText());
		  if(res.length()!=0)
			  display.showErrorFase1(res);
		  else{
			  String s = (String) display.getEmail().getValue();
			  safeSave.setText("Ti stiamo inviando l'email, un attimo di pazienza...");
			  safeSave.center();
			  safeSave.show();
			  doValidation(s);
			}
	  }
	  
	  
	  /**
	   * Controlla che il codice inserito corrisponda a quello
	   * realmente generato dal server della piattaforma MyTalk
	   * per consentire il cambio password. Il tutto avviene mediante
	   * la chiamata al metodo rpc checkValidation e passando come parametri
	   * identificativo univoco dell'utente, in modo da facilitare le quey nel db
	   * e il codice di sicurezza. In caso di riscontro positivo la view
	   * verr?cambiata altrimenti compararir?a video una stringa che segnala 
	   * i dettagli sull'impossibilit?di effettuare l'operazione richiesta 
	   * 
	   * @param uniqueId
	   * @param validation
	   */
	  private void doCheckValidation(String uniqueId, String validation){
			if(RPCall == null) {
				RPCall = GWT.create(RPCService.class);
		    }
		    
			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Boolean result) {
				if(result.equals(false)){
					display.showLoginError("Il codice di sicurezza non corrisponde");
				}					
				else{
					display.switchFase3();
				}
			}
		};
		
		if(!idUtente.equals(""))
		    RPCall.checkValidation(idUtente, validation, callback);		  
	  }	  
	  
	  /**
	   * Recupera il codice di sicurezza inserito e lo passa come parametro al 
	   * metodo doCheckValidation
	   */
	  private void doContinueRequest(){
		  String s = (String) display.getConfirmCode().getValue();
		  doCheckValidation(idUtente, s);
		}
	  
	  /**
	   * Si occuppa di invocare il metodo rpc setPassword per
	   * cambiare la password e a cambiare la view passando a quella 
	   * principale(home)
	   * 
	   * @param uniqueId
	   * @param password
	   */
	  private void doChangePassword(String uniqueId, String password){
			if(RPCall == null) {
				RPCall = GWT.create(RPCService.class);
		    }
		    
			AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Boolean result) {
				if(result.equals(false)){
					display.showLoginError("Errore nell' inserimento della password");
				}					
				else{
					display.showLoginError("cambio password avvenuto con successo");
					eventBus.fireEvent(new HomePageRequestEvent());
				}
			}
		};
		
		RPCall.setPassword(uniqueId, password, callback);		  
	  }	  
	  
	  /**
	   * Recupera dalla terza view la nuova password e la sua conferma.
	   * Inizialmente controlla che la nuova password sia valida assieme
	   * alla sua conferma altrimenti ne segnala a video la causa dell'errore.
	   * Altrimenti viene invocato il metodo doChangePassword che si
	   * occupperà di invocare i metodi appropiati per il cambio password
	   */
	  private void doSaveNewPassword(){
		  String passwordsErr = VC.checkPasswords(this.display.getPassword().getText(), this.display.getConfirmPassword().getText());
		 if(passwordsErr.length()!= 0){
			  display.showErrorFase3(passwordsErr);
			  return;
		}
		else {
			doChangePassword(idUtente, this.display.getPassword().getText());
		}
	}
	 
	  
	@Override
	public AbsolutePanel getView() {
		return display.getView();
	}
}
