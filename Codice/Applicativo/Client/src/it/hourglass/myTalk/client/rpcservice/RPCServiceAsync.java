/*
 * Filename: RPCService.java
 * Package: it.hourglass.myTalk.client.rpcservice
 * Author: Lorenzo Cresce Gioele
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+-----------------
 *  1.0     | 2013/07/6  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/06/02 |Codifica interfaccia
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.rpcservice;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.SignIn;
import it.hourglass.myTalk.client.shared.User;

/**
 *Interfaccia richiesta per la corretta implementazione dello strumento RPC 
 *di GWT. Consente la corretta associazione tra il metodo chiamato dal client 
 *utilizzando la definizione in RPCService e il tipo corretto di callback ad 
 *esso associato, di ritorno dalla servlet relativa.
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
public interface RPCServiceAsync {

    public void storeUser(User user, boolean encryption, AsyncCallback<String> callback);
    public void register(User user, AsyncCallback<String> callback);
    public void checkSession(AsyncCallback<String> callback);
	public void checkLogin(String userName, String password, AsyncCallback<Boolean> callback);
	public void signIn(String uniqueId, AsyncCallback<SignIn> callback);
	public void fetchFriendProfile(String uniqueId, AsyncCallback<User> callback);
	public void setValidation(String email, AsyncCallback<String> callback); 
	public void checkValidation(String uniqueId, String validation, AsyncCallback<Boolean> callback);
	public void setPassword(String uniqueId, String password, AsyncCallback<Boolean> callback);	
	public void resetSession(AsyncCallback<Boolean> callback);
	public void sendMessage(Message mess, AsyncCallback<String> callback);
	public void deleteMessage(Message mess, AsyncCallback<String> callback);
	public void refreshMessageList(String uniqueId,  AsyncCallback<List<Message>> callback);
	public void friendshake(Message request, boolean accepted, AsyncCallback<Boolean> callback);
	public void removeFriend(String userId, String friendId, AsyncCallback<String> callback);
	
	
}
