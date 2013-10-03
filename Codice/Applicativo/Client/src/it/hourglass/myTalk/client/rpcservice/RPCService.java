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
 *  0.1     | 2013/06/2 |Codifica interfaccia
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.rpcservice;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.SignIn;
import it.hourglass.myTalk.client.shared.User;

/**
 * Espone tutti i metodi che verranno poi implementati dalla corrispettiva servlet
 * e saranno quindi disponibili per essere eseguiti come chiamate asincrone remote.
 * Vengono quindi definiti parametri di ingresso e valori di ritorno, che dovranno 
 * essere rispettati dall'implementazione lato server.
 * 
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
@RemoteServiceRelativePath("rpcservice")
public interface RPCService extends RemoteService {
	
	public String register(User user);
    public String storeUser(User user, boolean encryption);
    public String checkSession();
	public Boolean checkLogin(String userName, String password);
	public SignIn signIn(String uniqueId);
	public User fetchFriendProfile(String uniqueId);
	public String setValidation(String email); 
	public Boolean checkValidation(String uniqueId, String validation);
	public Boolean setPassword(String uniqueId, String password);
	public Boolean resetSession();
	public String sendMessage(Message mess);
	public String deleteMessage(Message mess);
	public List<Message> refreshMessageList(String uniqueId);
	public String removeFriend(String userId, String friendId);
	public Boolean friendshake(Message request, boolean accepted);
}
