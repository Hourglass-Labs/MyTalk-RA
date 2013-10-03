/*
 * Filename: Friendship.java
 * Package: it.hourglass.myTalk.client.shared
 * Author: Lorenzo Cresce Gioele
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.1     | 2013/06/02 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.shared;
import java.util.HashMap;
import java.util.List;
/**
 * Classe che funge da contenitore di multiple classi. 
 * 
 * <p>Il suo scopo è quello di consentire il passaggio di tutte le informazioni 
 * utili di un Utente mediante un'unica transazione. Ciascuna struttura in essa
 * contenuta avrà poi scopi diversi ed indipendenti, e sarà trattata in maniera 
 * autonoma.</p>
 * 
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
public class SignIn implements java.io.Serializable  {
	private User user;
	private HashMap<String, Boolean> friendlist;
	private List<Message> messagelist;
	/**
	 * Costruttore standard della classe.
	 */
	public SignIn()	{};
	/**
	 * Secondo costruttore della classe. Assegna il valore alle tre variabili descritte sopra grazie ai parametri formali.
	 * @param user
	 * @param friendlist
	 * @param messagelist
	 */
	public SignIn(User user, HashMap<String, Boolean> friendlist, List<Message> messagelist){
		this.user = user;
		this.friendlist = friendlist;
		this.messagelist = messagelist;
	}
	/**
	 * Terzo costruttore della classe. Assegna il valore del parametro formale 
	 * alla variabile user e inizializza la variabile friendlist.
	 * @param user
	 */
	public SignIn(User user){
		this.user = user;
		this.friendlist = new HashMap<String, Boolean>();
	}
	/**
	 * Quarto costruttore della classe. Assegna il valore dei parametri formali
	 * alle variabili user e messagelist e inizializza la 
	 * variabile friendlist.
	 * @param user
	 * @param messagelist
	 */
	public SignIn(User user, List<Message> messagelist){
		this.user = user;
		this.messagelist = messagelist;
		this.friendlist = new HashMap<String, Boolean>();
	}
	/**
	 *  Metodo che ritorna il puntatore user.
	 * @return user
	 */
	public User getUser(){
		return user;
	}
	/**
	 * Metodo che ritorna il puntatore friendlist.
	 * @return friendlist
	 */
	public HashMap<String, Boolean> getFriendList(){
		return friendlist;
	}
	/**
	 * Metodo che ritorna il puntatore messagelist.
	 * @return messagelist
	 */
	public List<Message> getMessageList(){
		return messagelist;
	}
}