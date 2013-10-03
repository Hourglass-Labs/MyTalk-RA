/*
 * Filename: Profile.java
 * Package: it.hourglass.myTalk.client.shared
 * Author: Lorenzo Cresce Gioele
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.1     | 2013/05/30 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.shared;

import java.util.HashMap;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;

import it.hourglass.myTalk.client.communication.WSConnection;
import it.hourglass.myTalk.client.communication.WSMessageBuilder;
import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.rpcservice.RPCServiceAsync;
import it.hourglass.myTalk.client.view.PopupInformation;
/**
 * Classe che mantiene i dati relativi all'Utente principale garantendone
 * coerenza e accessibilità in tutte le componenti dell'applicativo.
 * 
 * <p>Mette a disposizione metodi per l'immediato recupero e memorizzazione
 * di alcune classi utilizzate per il recupero e la persistenza dei dati durante lo svolgersi
 * dell'attività dell'Utente. Ogni altra classe del client può avere
 * accesso ad esse da un unico punto d'accesso.</p>
 * 
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
public class Profile {
	
		  private static Profile instance;
		  private static User user = new User();
		  private static HashMap<String, Boolean> friendlist = new HashMap<String, Boolean>();
		  private static List<Message> messagelist;
		  private static WSConnection ws;
		  
		  /**
		   * Costruttore della classe Profile.
		   * 
		   * <p>E' volutamente definito protected per far adottare le
		   * connotazioni del pattern <code>Singleton</code> alla classe.
		   * Così facendo, si assicura l'unicità dell'oggetto e la coerenza
		   * nell'utilizzo delle componenti che lo compongono</p>
		   */
		  private Profile(){
		  }
		  /**
		   * Ritorna un'istanza dell'oggetto.
		   * 
		   * <p>Viene controllato se l'oggetto non sia stato ancora creato ad 
		   * opera di altre componenti. In tal caso, viene costruito e si
		   * ritorna un puntatore ad esso </p>
		   * @return instance Puntatore all'istanza della classe.
		   */
		  public static synchronized Profile getInstance(){
		    if (instance == null)
		      instance = new Profile();
		    return instance; 
		  }
		  /**
		   * Permette la sostituzione dell'elemento User della classe.
		   * @param setUser Elemento User{@link it.hourglass.myTalk.client.shared.User}
		   * da associare all'oggetto Profile.
		   */
		  public static void setUser(User setUser){
			  user = setUser;
		  }
		  /** 
		   * Permette la sostituzione dell'elemento friendlist della classe.
		   * 
		   * <p>Utilizzato per associare al Profilo una lista (<code>List<Message></code) 
		   * di nick di utenti amici con associato il loro status.</p>
		   * 
		   * @param setFriendlist L'HashMap da assegnare all'oggetto Profile.
		   */
		  public static void setFriendList(HashMap<String, Boolean> setFriendlist){
			  friendlist = setFriendlist;
		  }
		  /** 
		   * Permette la sostituzione dell'elemento messagelist della classe.
		   * 
		   * <p>Utilizzato per associare al Profilo una lista (<code>List<Message></code) 
		   * di messaggi il cui destinatario risulti essere l'utente associato al profilo.</p>
		   * 
		   * @param setMessagelist La List da assegnare all'oggetto Profile.
		   */
		  public static void setMessageList(List<Message> setMessagelist){
			  messagelist = setMessagelist;
		  }
		  
		  /**
		   * Ritorna un puntatore all'oggetto User{@link it.hourglass.myTalk.client.shared.User}
		   * associato al Profile.
		   * 
		   * @return user Puntatore all'oggetto User{@link it.hourglass.myTalk.client.shared.User}
		   * associato al Profile.
		   */
		  public static User getUser(){
			  return user;
		  }
		  /**
		   * Stabilisce una RPC così da consentire il salvataggio dell'User
		   * associato al profilo nel database.
		   * 
		   * @param encrypt Utilizzato per indicare la necessità che il campo
		   * password venga criptato o meno.
		   */
		  public static void saveUser(boolean encrypt){
			  final DialogBox safeSave = new DialogBox();
			  safeSave.setGlassEnabled(true);
			  safeSave.setText("Salvataggio dei cambiamenti in corso...");
			  safeSave.center();
			  safeSave.show();
			  
			  AsyncCallback<String> callback = new AsyncCallback<String>() {
			    
				public void onFailure(Throwable caught) {
			       GWT.log(caught.getMessage(), null);
			       }
			    public void onSuccess(String result) {
			    	safeSave.removeFromParent();
			    	PopupInformation notification = new PopupInformation(result,false);
			    	}
			    };
			    getService().storeUser(user, encrypt, callback);
			}
		  /**
		   * Ritorna un puntatore all'oggetto HashMap{@link java.util.HashMap} friendlist associato
		   * al Profile.
		   * @return friendlist Puntatore all'oggetto HashMap{@link java.util.HashMap} friendlist
		   * associato al Profile.
		   */
		  public static HashMap<String, Boolean> getFriendList(){
			  return friendlist;
		  }
		  /**
		   * Ritorna un puntatore all'oggetto List{@link java.util.List} messagelist associato
		   * al Profile.
		   * @return friendlist Puntatore all'oggetto List{@link java.utilList} messagelist
		   * associato al Profile.
		   */
		  public static List<Message> getMessageList(){
			  return messagelist;
		  }
		  /**
		   * Consente l'aggiornamento della lista dei messaggi con destinatario l'utente associato al profilo.
		   * 
		   * <p>Viene instaurata una RPC per consentire il prelievo di una lista di messaggi
		   *  aggiornata direttamente dal DB. Il ritorno con successo della RPC stessa 
		   * è un oggetto List{@link java.utilList} adatto ad essere assegnato al puntatore
		   * <code>messagelist</code>.</p>
		   */
		  public static void refreshMessageList(){
			  AsyncCallback<List<Message>> callback = new AsyncCallback<List<Message>>() {
				  public void onFailure(Throwable caught) {
				       GWT.log(caught.getMessage(), null);
				       }
				    public void onSuccess(List<Message> refreshedMessageList) {
				    	messagelist = refreshedMessageList;
				    	}
				    };
				    getService().refreshMessageList(user.getUniqueId(), callback);
				}
		  /**
		   * Rimuove un messaggio dalla lista di messaggi <code>messagelist</code>con destinatario l'utente.
		   * @param msg Il messaggio che si desidera sia eliminato dalla lista.
		   */
		  public static void removeMessage(Message msg){
			  messagelist.remove(msg);
		  }
		  /**
		   * Consente l'inserimento di un nuovo contatto nella lista di contatti
		   * amici dell'utente associato al Profile.
		   * 
		   * <p>Viene inserita una nuova voce nella lista <code>friendlist</code> del profilo.
		   * Successivamente, una notifica è inoltrata al server WS, così che l'utente
		   * aggiunto sia a sua volta notificato dell'operazione.</p>
		   * @param friendId Identificativo univoco del contatto da aggiungere
		   */
		  public static void addFriend(String friendId){
			  friendlist.put(friendId, false);
			  ws.send( WSMessageBuilder.sendNotificationAcceptFriendRequest(friendId, user.getUniqueId()));
		  }
		  /**
		   * Rimuove un contatto dalla lista di contatti amici <code>friendlist</code> associata all'utente.
		   * @param friendId L'identificativo univoco del contatto amico da rimuovere.
		   */
		  public static void removeFriend(String friendId){
			  friendlist.remove(friendId);
			  ws.send( WSMessageBuilder.sendNotificationDeleteFriend(friendId, user.getUniqueId()));
		  }
		  /**
		   * Inoltra una notifica al server del fatto che sia stata generata una
		   * richiesta d'amicizia.
		   * 
		   * <p>Nel momento in cui l'utente si trova a generare un messaggio 
		   * legato a una richiesta d'amicizia rivolto ad un altro utente,
		   * si invia notivia al server WS, così che il destinatario ne
		   * sia notificato.</p>
		   * @param recipient Identificativo univoco del destinatario della
		   * richiesta.
		   */
		  public static void friendReqNotificationWS(String recipient){
			  ws.send( WSMessageBuilder.sendNotificationFriendRequest(recipient, user.getUniqueId()));
		  }
		  /**
		   * Inoltra una notifica al server del fatto che sia stata generato un messaggio.
		   * 
		   * <p>Nel momento in cui l'utente si trova a generare un messaggio 
		   * rivolto ad un altro utente, si invia notivia al server WS, così che 
		   * il destinatario ne sia notificato.</p>
		   * @param recipient Identificativo univoco del destinatario della
		   * richiesta.
		   */
		  public static void sentMessageNotificationWS(String recipient){
			  ws.send( WSMessageBuilder.sendNotificationMsgSend(recipient, user.getUniqueId()));
		  }  
		  /**
		   * Consente di memorizzare una connessione a server WebSocket.
		   * @param s Connessione WSConnection{@link it.hourglass.myTalk.client.communication.WSConnection} 
		   * al server WebSocket da memorizzare.
		   */
		  public static void setWS(WSConnection s){
			  ws=s;	  
		  }
		  /**
		   * Costruisce un array degli amici dell'utente legato al profilo
		   * da inviare al server WebSocket e lo invia.
		   */
		  public static void sendFriendlistWS(){
			  WSMessageBuilder.contactList(user.getUniqueId(), friendlist.keySet().toArray(new String[friendlist.size()]));
			  ws.send( WSMessageBuilder.contactList(user.getUniqueId(), friendlist.keySet().toArray(new String[friendlist.size()])));
			  ws.regOnServer(user.getUniqueId());
			 
		  }
		  /**
		   * Ricava una connessione per stabilire una RPC
		   * @return Puntatore a una connessione RPC funzionante.
		   */
		  private static RPCServiceAsync getService() {
				return GWT.create(RPCService.class);
			    }
}