package it.hourglass.myTalk.test;


import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

//COMPONENTI TESTATE
import it.hourglass.myTalk.server.model.businesslogic.*;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.shared.Profile;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.User;

/*
 * Filename: TestValuesCheck.java
 * Package: it.hourglass.myTalk.test
 * Author: Giovanni Morlin
 * Date: 2013/08/11
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  0.7     | 2013/08/11  | Approvazione Test
 *  ---------+------------+------------------
 *  0.6     | 2013/08/06  | Ampliamento dei test
 *  ---------+------------+------------------
 *  0.5     | 2013/08/04  | Revisione, ampliamento dei test a seguito della RQ
 *  ---------+------------+------------------
 *  0.4     | 2013/07/07  | Approvazione Test
 *  ---------+------------+------------------
 *  0.3		| 2013/07/05  | Stesura delle invocazioni ai metodi alla classe testata
 *  ---------+------------+------------------
 *  0.2    	| 2013/07/04  | Stesura mock 
 * ---------+-------------+------------------
 *  0.1     | 2013/07/02  | Dichiarazione della classe e dei  parametri
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
*/

public class TestPresenterIntegrations extends GWTTestCase{

	  private RPCServiceAsync sessionManagmentSvc;
	  private String username = "giovanni";
	  private String password = "ciaociao";

	  
	  @Override
	  public String getModuleName() {
	  	return "it.hourglass.myTalk.MyTalk";
	  }	  
	  
//gwtSetup() eredita il SetUp() di Junit 3, 
//parte prima di OGNI metodo di test
@Override
 protected void gwtSetUp(){
/* Registro manualmente un utente che userò sempre per testare le
 * funzioanlità di ogni componente
 */
	sessionManagmentSvc = GWT.create(RPCService.class);
	 username = "giovanni";
	 password = "ciaociao1";
}
 
//Viene invocato dopo ogni metodo di test
@Override
protected void gwtTearDown(){}

private static RPCServiceAsync getService() {
	return GWT.create(RPCService.class);
	}

 public final void testLoginPresenter(){   
	 delayTestFinish(100000);
	 
		AsyncCallback<Boolean> callbackBoolean = new AsyncCallback<Boolean>() {
		@Override
		public void onFailure(Throwable caught) {
			finishTest();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(!result){
				//login non è andato abuon fine
				//viene notificato un msg di erroe
				finishTest();
			}else{
				//l'utente è stato riconosciuto 
				//viene cambiata la view
				finishTest();
			}
		}
	};
	    
	getService().checkLogin(username, password, callbackBoolean);	
	    
 }

 public void testBaseViewPresenter() {
	 delayTestFinish(100000);
	 
	 //Set up degli oggetti inviati tramite callback, in caso di successo o meno
    	AsyncCallback<String> callbackBoolean = new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				finishTest();
			}

			@Override
			public void onSuccess(String result) {
				if(result == null){
					//Se non esiste una sessione attiva 
					//all'utente viene mostrata la view 
					//per gli utenti non autenticati
					finishTest();
					}
				else{
					//Esiste una sessione attiva 
					//Vegono invocati gli eventi per mostrare
					//i widget per gli utenti autenticati
				}
			}
		};
    	//Faccio la chiamata al servizio che controlla la validità della sessione
   		getService().checkSession(callbackBoolean);
 }
 
  	public void testContactProfilePrsenter(){
	 delayTestFinish(100000);	
	 
	 AsyncCallback<User> callback = new AsyncCallback<User>() {
		    public void onFailure(Throwable caught) {
		    	finishTest();
		       }
		    public void onSuccess(User user) {
		    	if(user!=null){
		    	//Significa che user è salvato nella lista
		    	//conatti e lo mostra a video
		    		finishTest();
		    	}
		    	else{
		    	//Viene notificato a video che la il profilo
		    	//non esiste
		    		assertEquals(null, user);
		    		finishTest();
		    	}
		    }
		};
		    getService().fetchFriendProfile(Profile.getUser().getViewFriendProfile(), callback);
 }

	public void testFriendContactPresenter(){
		delayTestFinish(100000);	
		
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				finishTest();
			}
			public void onSuccess(String outcome) {
				//Viene stamapata a video una stringa
				//che enuncia lo stato dell'operazione
				assertEquals( "Operazione al momento non disponibile. ",
						outcome);
				finishTest();
			}
				
	    };
	    getService().removeFriend(Profile.getUser().getUniqueId(), "x", callback);
	}


public void testFriendMessagePresenter(){
	delayTestFinish(100000);
	Message friendReq = new Message("x","x","x");
	boolean accepted = true;
	
	AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
		
		public void onFailure(Throwable caught) {
			finishTest();
		}
		public void onSuccess(Boolean result) {
			if(result){
				//Viene notificato mediante un popup
				//che la richiesta di amicizia sia
				//stata accettata
				finishTest();
			}
			else{
				//Viene segnalato attarverso un popup come la 
				//richiesta di amicizia sia stata negata
				//o che non esiste una richiesta di amicizia
				//salvata 
				finishTest();
			}
		}
    };
    getService().friendshake(friendReq, accepted, callback);
}

	
public void testRegistrationPresenter(){
	delayTestFinish(100000);
	User user = new User("giovanni","x","x","x","x",new Date(),'x');
	
	AsyncCallback<String> callback = new AsyncCallback<String>() {
		
		public void onFailure(Throwable caught) {
			finishTest();}
		public void onSuccess(String result) {
			//Viene stampato a video un messagio
			//che notifica lo stato dell'operazione
			//assertEquals("Login già in uso.", result);//à!!
			finishTest();}
    };
    getService().register(user, callback);
    
    AsyncCallback<Boolean> callback2 = new AsyncCallback<Boolean>() {
	@Override
	public void onFailure(Throwable caught) {
		finishTest();}
	
	@Override
	public void onSuccess(Boolean result) {
		if(result.equals(false)){
		//viene notificato a video che il codice di sicurezza
		//non è quello corretto	
		}					
		else{
		//Viene notificato a video che il codice di sicurezza
		//è quello corretto, l'account viene abilitato.
		}
	}
	};
	getService().checkValidation("x","x",callback2);		  
}

	 
	 
public void testTextMessagePresenter(){
	delayTestFinish(100000);
	Message message = new Message("x","x","x");
	
	AsyncCallback<String> callback = new AsyncCallback<String>() {
		public void onFailure(Throwable caught) {
			finishTest();}
		public void onSuccess(String outcome) {
			//Viene stampata a video una stringa che notifica 
			//l'operazione eseguita.
			assertEquals("Operazione al momento non disponibile. ", outcome);
			finishTest();}
			
    };
    getService().deleteMessage(message, callback);
}


public void testSignedMenuPresenter() {
	delayTestFinish(100000);
	AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
	@Override
	public void onFailure(Throwable caught) {finishTest();}
	@Override
	public void onSuccess(Boolean result) {
		//Non vengono svolte operazioni in seguito
		//alla cancellazione della sessione attiva con 
		//il server
		assertEquals(new Boolean(true), result);
		finishTest();}					

};
    getService().resetSession(callback);	
}


public void testContactManagmentPresenter() {
	 delayTestFinish(100000);
	 Message mess = new Message("x","x","x");
	 //imposto a vero il capo richiesta di amicizia 
	 mess.setFriendReq(true);
	 
   	AsyncCallback<String> callback = new AsyncCallback<String>() {
		public void onFailure(Throwable caught) {
			finishTest();
		}
		public void onSuccess(String result) {
			//Verifico che l'erroe sia quello che mi
			//aspetto. La view dovrà notificare a video
			//il parametro result.
			System.out.println(result);
			assertEquals("L'utente desiderato non esiste.",
					result);
			finishTest();
		}
};
	getService().sendMessage(mess, callback);
}



	 public void testPopupMessagePresenter(){
		 delayTestFinish(100000);
		 Message mess = new Message("x","x","x");
		 
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				finishTest();
			}
			public void onSuccess(String result) {
				System.out.println(result);
				assertEquals("Messaggio inviato con successo.",result);
				finishTest();
			}
	    };
	    getService().sendMessage(mess, callback);
	}
 

//Utilizzata nel metodo testPasswordRecoverPresenter
String idUtente = "";


public void testPasswordRecoverPresenter(){
	delayTestFinish(100000);
	String email = "x";
	
	AsyncCallback<String> callback = new AsyncCallback<String>() {
	@Override
	public void onFailure(Throwable caught) {
		finishTest();
	}
	@Override
	public void onSuccess(String result) {
		if(result.length()==0){
		//viene stampata a video una stringa
		//d'errore che notifica come
		//l'indirizzo inserito non sia salvato nel DB
			assertEquals(0, result.length());
			finishTest();
		}					
		else{
			idUtente = result;
			//salvo l' id univoco ritornato dal db dentro
			//una variabile dell' oggetto e continuo alla 
			//seconda fase switchando la view
			finishTest();
		}
	}
};
    getService().setValidation(email, callback);	
    
    delayTestFinish(100000);
    AsyncCallback<Boolean> callback2 = new AsyncCallback<Boolean>() {
		@Override
		public void onFailure(Throwable caught) {
			finishTest();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result.equals(false)){
			//notifico tramite un messaggio di errore sulla view
			//che il codice di sicurezza inserito non risulta valido
			//quindi non posso procedere con il cambio password
				finishTest();
			}					
			else{
			//il codice inserito risulta valido, la view viene 
			//cambiata.Al prossimo passo sarà possibile impostare
			//la nuova password
				finishTest();
			}
		}
	};
	
	if(!idUtente.equals(""))
	    getService().checkValidation(idUtente, "x", callback2);	
	
	delayTestFinish(100000);
	AsyncCallback<Boolean> callback3 = new AsyncCallback<Boolean>() {
		@Override
		public void onFailure(Throwable caught) {
			finishTest();
		}
		@Override
		public void onSuccess(Boolean result) {
			if(result.equals(false)){
			//notifico a view che la password non soddisfa
			// i requisiti di sicurezza richiesti
				finishTest();
			}					
			else{
			// il cambio password è avvenuto con successo
				finishTest();
			}
		}
	};
	
	getService().setPassword(idUtente, password, callback3);
  }	
}





