/*
 * Filename: FriendMessagePresenter.java
 * Package: it.hourglass.myTalk.server.model.businesslogic
 * Author: Lorenzo Cresce Gioele
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0     | 2013/07/6  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/06/2 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */
package it.hourglass.myTalk.server.model.businesslogic;

import java.util.UUID;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 * Classe che si occupa di fornire varie funzionalità relative alla sicurezza
 * dell'account.
 * 
 * <p>Fornisce dei metodi per l'amministrazione delle password e dei codici
 * di validazione associati a un account.</p>
 * 
 * @author Gioele Lorenzo Cresce
 * @version 0.1
 */
public class Security {
	private final BasicPasswordEncryptor passwordEncryptor  
							= new BasicPasswordEncryptor();
	private UUID random; 
	/**
	 * Genera e ritorna una stringa composta da lettere e numeri randomici di lunghezza pari a 32 caratteri
	 * @return  Una stringa lunga 32 caratteri, alfanumerica, di contenuto random.
	 */
	public String generateValidation(){
		random = UUID.randomUUID();
		return random.toString().substring(0, 32);
	}
	/**
	 * Ricevuta una stringa in parametro, ritorna il corrispettivo Message Digest
	 * @param plain Stringa da criptare
	 * @return  Message Digest della stringa passata in parametro 
	 */
	public String encryptPassword(String plain){
		return passwordEncryptor.encryptPassword(plain);		
	}
	/**
	 * Valuta se la seconda stringa passata in parametro consiste in un Message Digest
	 * valido della prima. 
	 * @param plain Stringa in chiaro
	 * @param encrypted Stringa criptata
	 * @return  <code>true</code> se la seconda costituisce un MD valido della prima,
	 * <code>false</code> altrimenti.
	 */
	public boolean checkPasswords(String plain, String encrypted){
		return passwordEncryptor.checkPassword(plain, encrypted);
	}
	
	
}
