/*
 * Filename: AppController.java
 * Package: it.hourglass.myTalk.client.appcontroller
 * Author: Umberto Martinati - Thomas Rossetto - Lorenzo Cresce Gioele
 * Date: 2013/03/30
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0     | 2013/07/02 | Approvazione classe
 *  ---------+------------+------------------
 *  0.4		| 2013/06/01 | Completamento classe
 *  ---------+------------+------------------
 *  0.3		| 2013/05/10 | Aggiunti metodi Bind e relativi sotto/metodi
 *  ---------+------------+------------------
 *  0.2		| 2013/05/05 | Codifica collegamento server 
 *  ---------+------------+------------------
 *  0.1		| 2013/04/16 | Codifica sistema eventi
 * ---------+------------+------------------
 *  0.0     | 2013/03/30 |Codifica classe
 *
 * This software is distributed under GNU/GPL 2.0.

*/


package it.hourglass.myTalk.client.appcontroller;

import it.hourglass.myTalk.client.view.PopupInformation;
import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.communication.WSMessageBuilder;
import it.hourglass.myTalk.client.communication.WSConnection.WSConnectionStateCallback;
import it.hourglass.myTalk.client.event.CallPageRequestEvent;
import it.hourglass.myTalk.client.event.CallPageRequestEventHandler;
import it.hourglass.myTalk.client.event.CallRequestEvent;
import it.hourglass.myTalk.client.event.CallRequestEventHandler;
import it.hourglass.myTalk.client.event.CallRequestRefusedEvent;
import it.hourglass.myTalk.client.event.CallRequestRefusedEventHandler;
import it.hourglass.myTalk.client.event.ContactManagementPageRequestEvent;
import it.hourglass.myTalk.client.event.ContactManagementPageRequestEventHandler;
import it.hourglass.myTalk.client.event.FriendContactProfilePageRequest;
import it.hourglass.myTalk.client.event.FriendContactProfilePageRequestHandler;
import it.hourglass.myTalk.client.event.HomePageRequestEvent;
import it.hourglass.myTalk.client.event.HomePageRequestEventHandler;
import it.hourglass.myTalk.client.event.IdChangedEvent;
import it.hourglass.myTalk.client.event.IdChangedEventHandler;
import it.hourglass.myTalk.client.event.LoginPageRequestEvent;
import it.hourglass.myTalk.client.event.LoginPageRequestEventHandler;
import it.hourglass.myTalk.client.event.LoginRequestEvent;
import it.hourglass.myTalk.client.event.LoginRequestEventHandler;
import it.hourglass.myTalk.client.event.LogoutRequestEvent;
import it.hourglass.myTalk.client.event.LogoutRequestEventHandler;
import it.hourglass.myTalk.client.event.MessagePageRequestEvent;
import it.hourglass.myTalk.client.event.MessagePageRequestEventHandler;
import it.hourglass.myTalk.client.event.NewFriendContactListEvent;
import it.hourglass.myTalk.client.event.NewFriendContactListEventHanlder;
import it.hourglass.myTalk.client.event.PasswordRecoverPageRequestEventHandler;
import it.hourglass.myTalk.client.event.ProfileManagementPageRequestEvent;
import it.hourglass.myTalk.client.event.ProfileManagementPageRequestEventHandler;
import it.hourglass.myTalk.client.event.PasswordRecoverPageRequestEvent;
import it.hourglass.myTalk.client.event.RegistrationPageRequestEvent;
import it.hourglass.myTalk.client.event.RegistrationPageRequestEventHandler;
import it.hourglass.myTalk.client.event.RemoveFriendContactEvent;
import it.hourglass.myTalk.client.event.RemoveFriendContactEventHandler;
import it.hourglass.myTalk.client.presenter.BaseViewPresenter;
import it.hourglass.myTalk.client.presenter.CallPopupPresenter;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.SignIn;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.view.CallPopup;
import it.hourglass.myTalk.client.wrappers.ConsoleLog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/** 
 *  <p>Questa classe si occupa della creazione del programma MyTalk e del controllo dello stesso.
 *  Il programma utilizza il sistema History fornito dal framework GWT per il riconoscimento delle richieste fatte dall'utente.
 *  Tali richieste sono intercettate dal sistema HandlerManager anch'esso fornito dal framework GWT 
 *  </p>
 *  @author Umberto Martinati
 *  @version 0.2
 * 
 */

public class AppController implements ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final WSConnection wsconnection;
	private BaseViewPresenter view;

	/**
	 * Costruttore della classe AppController.
	 * Si occupa di assegnare l'eventHandler e di inserire in esso i gli eventi tramite il metodo bind().
	 * Viene successivamente creata la connessione con il server Web Socket tramite l'instanziazione di un 
	 * oggetto di tipo WSConnection. A quest'oggetto vengono ridefiniti i metodi:
	 * onConnectionOpened: riceve la risposta all'apertura della connessione con il server.
	 * onConnectionClosed: riceve la risposta alla chiusura della connessione con il server.
	 * onCallUnrelatedMessage: questo metodo si occupa di gestire l'arrivo di un messaggio JSON dal server.
	 * Infine il costruttore si occupa di creare la BaseView.
	 * @author Umberto Martinati
	 * @param eventBus: HandlerManager creato all'avvio del programma.
	 * @param wsconnection: oggetto che permette la connessione web socket
	 * @param view: view di base.
	 * 
	 */
	public AppController(final HandlerManager eventBus) {

	  this.eventBus = eventBus;
	  bind();
	  final String wsServerUrl="ws://test2.testmytalk.cloudbees.net/test";

	  wsconnection = new WSConnection(wsServerUrl,
				new WSConnection.WSConnectionStateCallback() {

					@Override
					public void onConnectionOpened() {
						//ricevi id
					//	ConsoleLog.consoleLog("Connessione riuscita");
					}
					@Override
					public void onConnectionClosed() {

					}
					@Override
					public void onCallUnrelatedMessage(String type,JSONObject jso) {
						if (type.equals("registration")) {
								Profile.getUser().setUniqueId(((JSONString)jso.get("id")).stringValue());
								eventBus.fireEvent(new IdChangedEvent());
							}
						else if(type.equals("incoming")){
							ConsoleLog.consoleLog("popup!");
							CallPopupPresenter popup=new CallPopupPresenter(eventBus, new CallPopup(((JSONString)jso.get("offer")).toString(),(int)((JSONNumber)jso.get("stream")).doubleValue()));
						}			
						else if(type.equals("notification")){
							if(jso.get("status")!=null && (int)((JSONNumber) jso.get("status")).doubleValue()==1){
								view.changeContactStatus(((JSONString) jso.get("nick")).stringValue(),(int)((JSONNumber) jso.get("status")).doubleValue());
							}
							else if(jso.get("status")!=null && (int)((JSONNumber) jso.get("status")).doubleValue()==0)
							{
								view.changeContactStatus(((JSONString) jso.get("nick")).stringValue(),(int)((JSONNumber) jso.get("status")).doubleValue());
							}
							else{
								int stype= (int)((JSONNumber) jso.get("stype")).doubleValue();
								String sender = ((JSONString) jso.get("sender")).stringValue();
	  
								switch (stype) {
								case 1: // RIcezione Richiesta amicizia
									Profile.refreshMessageList();
									PopupInformation friendRequestPopup=new PopupInformation("Hai ricevuto una richiesta di amicizia da "+sender, false);
								break;
								case 2: // Ricezione Accettazione amicizia
									int jsonStatus = (int)((JSONNumber) jso.get("stat")).doubleValue();
									boolean stat;
									  if(jsonStatus==1) 
										  stat=true;
									  else stat=false;
									PopupInformation acceptedFreindRequestPopup=new PopupInformation("Hai instaurato un'amicizia con "+sender, false);
									eventBus.fireEvent(new NewFriendContactListEvent(sender, stat));
									break;
								case 3:// Ricezione messaggio
									Profile.refreshMessageList();
									PopupInformation messagePopup=new PopupInformation("Hai ricevuto un messaggio da "+sender,true);
									break;
								case 4:// Ricezione avviso eliminazione
									PopupInformation removeFriendPopup=new PopupInformation("L'utente "+sender+" ti ha eliminato dalla sua lista amici.",true);
									eventBus.fireEvent(new RemoveFriendContactEvent(sender));
								break;
								
								}
								
							}
							
						}

					}

	  },eventBus);
	  Timer timer = new Timer(){
		  public void run() {
		       wsconnection.send(WSMessageBuilder.Ping());
		       ConsoleLog.consoleLog("ping");
		    }
	  };
	  timer.scheduleRepeating(50000);
	  this.view= new BaseViewPresenter(wsconnection, eventBus);
	  Profile.setWS(wsconnection);

  }


  /**
   * Funzione che aggiunge all'eventHendler gli eventi e indica al sistema cosa fare al verificarsi degli stessi.
   * Gli eventi sono definiti nel package it.hourglass.myTalk.client.event. Ad ogni evento aggiunto all'event bus andra'
   * ridefinito il metodo contenuto nella classe Handler dell'evento.
   */
  private void bind() {

    History.addValueChangeHandler(this);

    eventBus.addHandler(HomePageRequestEvent.TYPE, new HomePageRequestEventHandler() {

		@Override
		public void onHomePageRequestEvent(HomePageRequestEvent Request) {
			doHomePageRequest();

		}
	});
    

    eventBus.addHandler(CallRequestEvent.TYPE,
        new CallRequestEventHandler() {
          public void onCallRequest(CallRequestEvent event) {
        	  doCallRequest();
          }
        }
    );  
    
    eventBus.addHandler(CallRequestRefusedEvent.TYPE,
            new CallRequestRefusedEventHandler() {
              public void onCallRequestRefused(CallRequestRefusedEvent event) {
              refuseCall();
              }
            }
        );
    
    eventBus.addHandler(IdChangedEvent.TYPE, new IdChangedEventHandler() {

		@Override
		public void onIdChangedEvent(IdChangedEvent Request) {
			doChangeFooter();
		}
		});

    
    eventBus.addHandler(LoginPageRequestEvent.TYPE,
            new LoginPageRequestEventHandler() {
			@Override
			public void onLoginPageRequest(LoginPageRequestEvent Request) {
				doLoginPageRequest();
			}
            });  

    
    eventBus.addHandler(RegistrationPageRequestEvent.TYPE,
            new RegistrationPageRequestEventHandler() {
              public void onRegistrationPageRequestEvent(RegistrationPageRequestEvent event) {
                doRegisterPageRequest();
              }
            });  
    
    eventBus.addHandler(CallPageRequestEvent.TYPE,
            new CallPageRequestEventHandler() {
              public void onCallPageRequest(CallPageRequestEvent event) {
                doCallPageRequest();
              }
            });  
    
    eventBus.addHandler(PasswordRecoverPageRequestEvent.TYPE, new PasswordRecoverPageRequestEventHandler() {
		
		@Override
		public void onPasswordRecoverPageRequest(
				PasswordRecoverPageRequestEvent event) {
			doRecuperaPasswordRequest();
		}
	});

    
    eventBus.addHandler(ProfileManagementPageRequestEvent.TYPE, new ProfileManagementPageRequestEventHandler() {

		@Override
		public void onProfileManagementPageRequest(
				ProfileManagementPageRequestEvent event) {
			doProfileManagementPageRequest();

		}
	});
    
    eventBus.addHandler(MessagePageRequestEvent.TYPE, new MessagePageRequestEventHandler() {
		@Override
		public void onMessagePageRequestEvent(MessagePageRequestEvent event) {
			doMessagePageRequest();
		}
	});
    
    eventBus.addHandler(LogoutRequestEvent.TYPE, new LogoutRequestEventHandler() {	
		@Override
		public void onLogoutRequest(LogoutRequestEvent event) {
			doLogoutRequest();

		}
	});    
    
    eventBus.addHandler(LoginRequestEvent.TYPE, new LoginRequestEventHandler() {	
		@Override
		public void onLoginRequest(LoginRequestEvent event) {
			doLoginRequest();	
		}
	});
    
    
    eventBus.addHandler(ContactManagementPageRequestEvent.TYPE, new ContactManagementPageRequestEventHandler() {

		@Override
		public void onContactManagementPageRequest(
				ContactManagementPageRequestEvent contactManagementPageRequestEvent) {
			doContactManagementPageRequest();

		}
	});
    
    eventBus.addHandler(FriendContactProfilePageRequest.TYPE, new FriendContactProfilePageRequestHandler() {
		
		@Override
		public void onFriendContactProfilePageRequest(
				FriendContactProfilePageRequest friendContactProfilePageRequest) {
			doFriendContactProfilePageRequest();
			
		}
	});
    
    eventBus.addHandler(RemoveFriendContactEvent.TYPE, new RemoveFriendContactEventHandler() {
		
		@Override
		public void onRemoveFriendContactEvent(RemoveFriendContactEvent event,
				String contact) {
			doRemoveContact(contact);
			
		}
	});
    
    eventBus.addHandler(NewFriendContactListEvent.TYPE, new NewFriendContactListEventHanlder() {
		
		@Override
		public void onNewFriendContactListEvent(NewFriendContactListEvent event,
				String contact,Boolean status) {
			doNewFriendContactList(contact,status);
			
		}
	});
    
  }
    
  private void doChangeFooter(){
	  view.refreshFooter();
  }
  
  private void doLoginPageRequest(){
	  History.newItem("login");
  }
  
  private void doRegisterPageRequest(){
	  History.newItem("registration");
  }
  
  private void doCallPageRequest(){
	  History.newItem("call");
  }

  private void doHomePageRequest(){
	  History.newItem("home");
  }
  
  private void doRecuperaPasswordRequest(){
	  History.newItem("password_recover");
  }
  
  private void doCallRequest(){
	  History.newItem("call_request");
  }
  
  private void doProfileManagementPageRequest(){
	  History.newItem("profile");
  }
  
  private void doMessagePageRequest(){
	  History.newItem("message");
  }
  
  private void refuseCall(){
	  wsconnection.refuseCall();
  }
  
  private void doLoginRequest(){
	  signIn(Profile.getUser().getUniqueId());
  }
  
  private void doLogoutRequest(){
	  Profile.setUser(new User());
	  view.switchLoggedOut();
	  eventBus.fireEvent(new IdChangedEvent());
	  History.newItem("home");
	  wsconnection.setSession(false);
	  wsconnection.regOnServer();
  }
  
  
  private void doContactManagementPageRequest(){
	  History.newItem("contact_management");
  }
  
  private void doFriendContactProfilePageRequest(){
	  History.newItem("friend_profile");
  }
  
  private void doRemoveContact(String contact){
	  Profile.getFriendList().remove(contact);
	  view.removeContactFromContactList(contact);
  }
  
  private void doNewFriendContactList(String contact,Boolean status){
	  Profile.getFriendList().put(contact, status);
	  view.refreshContactList();
  }
  
  /**
   * Questa funzione si occupa di modificare lo stato del sistema quando viene generato un nuovo History Token
   * 
   */
  
@Override
public void onValueChange(ValueChangeEvent<String> event) {
	  String token = event.getValue();

	    if (token != null) {
	    	if(token.equals("home")){
	    		view.switchHome();
	    		return;
	    	}
	    	if (token.equals("call")) {
	    		view.switchCall();
	    		return;
	    		}
	    	if (token.equals("login")) {
	    		view.switchLogin();
	    		return;
	    		}
	    	if (token.equals("registration")) {
	    		view.switchRegistration();
	    		return;
	    		}
	    		if (token.equals("password_recover")){
	    		view.switchPasswordRecover();
	    		return;
	    		}
	    		if (token.equals("call_request")){
	    			view.switchCallRequest();
	    		return;
	    		}

	    		if(token.equals("message")){
	    			view.switchMessage();
	    			return;
	    		}
	    		if(token.equals("profile")){
	    			view.switchEditProfile();
	    			return;
	    		}
	    		if(token.equals("contact_management")){
	    			view.switchContactManagement();
	    			return;
	    		}
	    		
	    		if(token.equals("friend_profile")){
	    			view.switchFriendContactView();
	    			return;
	    		}
	    		else History.fireCurrentHistoryState();
	    	}
	  } 
/**
 * Qyesto metodo si occupa di recuperare il profilo di un utente in seguito a una richiesta di autenticazione.
 * @param uniqueId
 */
private void signIn(String uniqueId){
	AsyncCallback<SignIn> callback = new AsyncCallback<SignIn>() {
    public void onFailure(Throwable caught) {
       GWT.log(caught.getMessage(), null);
       }
    public void onSuccess(SignIn data) {
    	Profile.setUser(data.getUser());
    	Profile.setFriendList(data.getFriendList());
    	Profile.setMessageList(data.getMessageList());
    	Profile.sendFriendlistWS();
    	view.switchLoggedIn();
    	eventBus.fireEvent(new IdChangedEvent());
    	History.newItem("home");
    	friendRequestControl();
    	}
    };
    getService().signIn(uniqueId, callback);
}

private static RPCServiceAsync getService() {
	return GWT.create(RPCService.class);
    }
/**
 * Metodo di inizializzazione della view e creazione del primo history token.
 */
	public void init(){
	view.init();
	String startToken=History.getToken();
	  if(startToken.isEmpty())
          History.newItem("home");
      else
          History.fireCurrentHistoryState();
	}
	/**
	 * Questo metodo controlla se un utente ha delle richieste di amicizia a cui deve ancora rispondere.
	 */
	private void friendRequestControl(){
		java.util.Iterator<Message> iteratorFriendMessage=Profile.getMessageList().iterator();
		while(iteratorFriendMessage.hasNext()){
			if(iteratorFriendMessage.next().getFriendReq()==true){
				PopupInformation popup=new PopupInformation("Hai delle richieste di amicizia a cui rispondere", false);
				return;
			}
		}
}

}