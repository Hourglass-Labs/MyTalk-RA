/*
 * Filename: BaseViewPresenter.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Umberto Martinati
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.4     | 2013/07/5  | Approvazione classe
 *  ---------+------------+------------------
 *  0.3		| 2013/05/30 | Aggiunta metodo controllo sessione
 *  ---------+------------+------------------
 *  0.2    	|  2013/05/10 | Aggiunta metodi switch view
 * ---------+------------+------------------
 *  0.1     | 2013/05/8 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.

*/

package it.hourglass.myTalk.client.presenter;



import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.event.HomePageRequestEvent;
import it.hourglass.myTalk.client.event.LoginRequestEvent;
import it.hourglass.myTalk.client.presenter.display.BaseDisplay;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.view.FooterView;

import it.hourglass.myTalk.client.view.BaseView;
import it.hourglass.myTalk.client.view.CallView;
import it.hourglass.myTalk.client.view.ContactListView;
import it.hourglass.myTalk.client.view.ContactManagementView;
import it.hourglass.myTalk.client.view.ContactProfileView;
import it.hourglass.myTalk.client.view.EditProfileView;
import it.hourglass.myTalk.client.view.HomeView;
import it.hourglass.myTalk.client.view.LoginView;
import it.hourglass.myTalk.client.view.AnnoMenuView;
import it.hourglass.myTalk.client.view.MessageView;
import it.hourglass.myTalk.client.view.PasswordRecoverView;
import it.hourglass.myTalk.client.view.RegistrationView;
import it.hourglass.myTalk.client.view.SignedMenuView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
/**
 * Questa classe e' il presenter della base view.
 * Essa espone alla BaseView un'interfacia attraferso la quale controlla il comportamente della View stessa.
 * 
 * @author Umberto Martinati
 *
 */
public class BaseViewPresenter{
/**
 * Questa interfaccia espone i metodi che la BaseView puo' richiedere al presenter
 * @author Umberto Martinati
 *
 */
	
	 private boolean signedIn;
	 private final WSConnection wsconnection;
	 private final HandlerManager eventBus;
	 private final BaseDisplay display;
	 private WidgetPresenter menuPresenter;
	 private WidgetPresenter bodyPresenter;
	 private FooterPresenter footerPresenter;
	 private ContactListPresenter contacListPresenter;
	 private RPCServiceAsync sessionManagmentSvc = GWT.create(RPCService.class);//PROXY per la rpc
	 
	  /**
	   * Costruttore della classe BaseViewPresenter.
	   * Questo costruttore si occupa di inizializzare i campi del BaseViewPresenter.
	   * @param view Questo campo e' la base view. Nel costruttore viene creata un'istanza di BaseView
	   * @param menuPresenter e' il presenter del menu.
	   * @param bodyPresenter e' il presenter del corpo centrale della view
	   * @param footerPresenter e' il presenter del footer della view.
	   */
	   public BaseViewPresenter(WSConnection wsconnection, HandlerManager eventBus) {
		this.signedIn=false;
		this.wsconnection = wsconnection;
	    this.eventBus = eventBus;
	    this.display = new BaseView();
	  }
	   
		 public void doSessionControl() {
			 //Inizializzo il proxy per il servizio SessionManagment
		    	if(sessionManagmentSvc == null) {
		    		sessionManagmentSvc = GWT.create(RPCService.class);
		    	}
		    	
		    	//Set up degli oggetti inviati tramite callback, in caso di successo o meno
		    	AsyncCallback<String> callback = new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {// se viene invocata una throws

					}

					@Override
					public void onSuccess(String result) {
						if(result == null){
							   menuPresenter = new AnnoMenuPresenter(eventBus, new AnnoMenuView());
							   display.switchMenu(menuPresenter.getView());
							   wsconnection.setSession(false);
						}
						else{
							Profile.getUser().setUniqueId(result);
							eventBus.fireEvent(new LoginRequestEvent());
							signedIn=true;
							wsconnection.setSession(true);
						}
					}
				};
		    	//Faccio la chiamata al servizio che controlla la validità della sessione
		   		sessionManagmentSvc.checkSession(callback);
	}
	   
	   /**
	    * Questa funzione si occupa di inizializzare la view. Controlla se è presente una sessione
	    * valida e ggiunge il footer
	    */
	   public void init(){
		   
		   doSessionControl();
		   footerPresenter=new FooterPresenter(new FooterView("Connessione al server in corso"));
		   display.switchFooter(footerPresenter.getView());
	   }
	   
	   /**
	    * Questo metodo si occua di eseguire lo switch del sistema da stato non autenticato a stato atuenticato.
	    */
	   
	   public void switchLoggedIn(){
		   
		   signedIn=true;
		   menuPresenter=new SignedMenuPresenter(eventBus, new SignedMenuView());
		   display.switchMenu(menuPresenter.getView());
		   contacListPresenter = new ContactListPresenter(eventBus, wsconnection, new ContactListView());
		   display.addContactList(contacListPresenter.getView());
	   }
	   
	   public void deleteContactList(){
		   contacListPresenter.removeContactList();
		   contacListPresenter=new ContactListPresenter(null, null, null);
		   
	   }
	   
	   public void switchLoggedOut(){
		   //togli lista utenti
		   signedIn=false;
		   contacListPresenter.removeContactList();
		   menuPresenter=new AnnoMenuPresenter(eventBus, new AnnoMenuView());
		   display.switchMenu(menuPresenter.getView());
	   }
	   
	   public void switchHome(){
		   bodyPresenter=new HomePresenter(new HomeView());
		   display.switchBody(bodyPresenter.getView());
	   }
	   /**
	    * Questo metodo si occua di eseguire lo switch view del corpo centrale inserendo la CallView
	    */
	   public void switchCall(){
		   bodyPresenter=new CallPresenter(wsconnection, eventBus, new CallView(),false);
		   display.switchBody(bodyPresenter.getView());
	   }
	   
	   public void switchCallRequest(){
		   bodyPresenter=new CallPresenter(wsconnection, eventBus, new CallView(),true);
		   display.switchBody(bodyPresenter.getView());
	   }
	   
	   /**
	    * Questo metodo si occua di eseguire lo switch view del corpo centrale inserendo la LoginView
	    */
	   public void switchLogin(){
		   bodyPresenter = new LoginPresenter(wsconnection, eventBus, new LoginView());
		   display.switchBody(bodyPresenter.getView());
	   }
	   
	   /**
	    * Questo metodo si occua di eseguire lo switch view del corpo centrale inserendo la RegistrationView
	    */
	   public void switchRegistration(){
		   bodyPresenter=new RegistrationPresenter(new RegistrationView());
		   display.switchBody(bodyPresenter.getView());
	   }
	   /**
	    * Questo funzione si occupa di richiedere il refresh del footer con il nuovo id identificativo
	    */
	   public void refreshFooter() {
		footerPresenter.refreshFooter(Profile.getUser().getUniqueId());
	   }
	   
	   /**
	    * Questo metodo si occua di eseguire lo switch view del corpo centrale inserendo la RecoverPasswordVuew
	    */
	  public void switchPasswordRecover(){
		   bodyPresenter=new PasswordRecoverPresenter(wsconnection, eventBus, new PasswordRecoverView());
		   display.switchBody(bodyPresenter.getView());
	   }

	   /**
	    * Questo metodo si occua di eseguire lo switch view del corpo centrale inserendo la MessageView
	    */
	  public void switchMessage(){
		  if(signedIn==true){
			   bodyPresenter=new MessagePresenter(new MessageView());
			   display.switchBody(bodyPresenter.getView());
		  }
		  else
			  eventBus.fireEvent(new HomePageRequestEvent());
	  }
	   /**
	    * Questo metodo si occua di eseguire lo switch view del corpo centrale inserendo la EditProfileView
	    */
	  public void switchEditProfile(){
		  if(signedIn==true){
			  bodyPresenter=new EditProfilePresenter(eventBus, new EditProfileView());
			  display.switchBody(bodyPresenter.getView());
		  }
		  else
			  eventBus.fireEvent(new HomePageRequestEvent());
	  }
	  
	  public void switchContactManagement(){
		  if(signedIn==true){
			  bodyPresenter=new ContactManagementPresenter(eventBus, new ContactManagementView());
			  display.switchBody(bodyPresenter.getView());
		  }
		  else
			  eventBus.fireEvent(new HomePageRequestEvent());
	  }

	  public void changeContactStatus(String name,int status){
		  boolean s;
		  if(status==1) s=true;
		  else s=false;
		  contacListPresenter.changeStatus(name, s);
	  }
	  
	  public void switchFriendContactView(){
		  bodyPresenter=new ContactProfilePresenter(eventBus,new ContactProfileView());
		  display.switchBody(bodyPresenter.getView());
	  }
	  
	  public void removeContactFromContactList(String contact){
		  if(signedIn==true){
			  contacListPresenter.removeContact(contact);
		  }
		  else
			  eventBus.fireEvent(new HomePageRequestEvent());
	  }
	  
	  public void refreshContactList(){
		  contacListPresenter.removeContactList();
		  contacListPresenter = new ContactListPresenter(eventBus, wsconnection, new ContactListView());
		  display.addContactList(contacListPresenter.getView());
	  }

}
