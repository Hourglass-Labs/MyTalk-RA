/*
 * Filename: ValueCheck.java
 * Package: it.hourglass.myTalk.client.presenter
 * Author: Lorenzo Cresce Gioele
 * Date: 2013/05/8
 *
 * Diary:
 *
 *  Version | Date     	 | Changes
 *  ---------+------------+------------------
 *  1.0     | 2013/07/6  | Approvazione classe
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/27 | Aggiunta metodi controllo dati
 * ---------+------------+------------------
 *  0.1     | 2013/06/27 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 */

package it.hourglass.myTalk.client.presenter;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
/**
 * Offre metodi per il controllo della conformità ai pattern desiderati
 *  di stringhe in parametro.
 *  
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
public class ValuesCheck {
	
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{8,32}$";
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PW_PATTERN = "^[a-zA-Z0-9]{8,32}$";
	/**
	 * Controlla che la stringa passata in parametro sia conforme al pattern
	 * corrispondente ai criteri scelti per lo Username.
	 * 
	 * <p>Il pattern associato alla stringa da controllare impone che:
	 * <ul>
	 * <li>L'username sia alfanumerico</li>
	 * <li>La lunghezza non sia inferiore a 8 caratteri o maggiore di 32</li>
	 * <li>Non siano presenti spazi ma solo <code>underscore</code></li>
	 * </ul>
	 * </p>
	 * @param uniqueId Stringa da controllare.
	 * @return err Una stringa d'errore se il parametro non è conforme
	 * al pattern, una stringa vuota altrimenti.
	 */
	public String checkUniqueId(String uniqueId){
	    String err = validate(USERNAME_PATTERN, uniqueId);
	    if(err=="INVALID")
	    	return "L'username dev'essere alfanumerico";
	    else
	    	return err;
	}
	/**
	 * Controlla che le due password inserite siano conformi al pattern scelto
	 * per le password e uguali tra loro.
	 * <p>Il pattern scelto per l'inserimento delle password impone che:
	 * <ul>
	 * <li>Le password siano alfanumeriche, senza spazi o segni
	 * d'interpunzione</li>
	 * <li>La lunghezza delle password sia non inferiore a 8 caratteri né
	 * maggiore di 32 </li>
	 * </ul>
	 * </p>
	 * @param password Primo inserimento della password.
	 * @param rePassword Secondo inserimento della password.
	 * @return passErr Una stringa d'errore se i criteri scelti non risultano
	 * rispettati, vuota altrimenti.
	 */
	public String checkPasswords(String password, String rePassword){
		String passErr = validate(PW_PATTERN, password);
	    if(passErr=="INVALID") return "La password dev'essere un'unica parola di almeno 8 caratteri alfanumerici.";
	    if(!password.equalsIgnoreCase(rePassword))
	        return "Le due password inserite non coincidono.";
	    return passErr;
	    }
	/**
	 * Controlla che la stringa inserita sia non vuota, maggiore di 2 caratteri
	 * ma minore di 32.
	 * @param name Stringa da controllare.
	 * @return Una stringa vuota in assenza di errori, una stringa che segnali
	 * l'errore in caso contrario.
	 */
	public String checkName(String name){
	if(name.length()==0)
		return "Campo obbligatorio.";
	else if (name.length()>32 || name.length()<2)
			return "Il nome non puo' contenere meno di 2 caratteri e piu' di 32.";
	return "";
	}
	/**
	 * Controlla che la stringa inserita sia non vuota, maggiore di 2 caratteri
	 * ma minore di 32.
	 * @param lastName Stringa da controllare.
	 * @return Una stringa vuota in assenza di errori, una stringa che segnali
	 * l'errore in caso contrario.
	 */
	public String checkLastName(String lastName){
	if(lastName.length()==0)
		return "Campo obbligatorio.";
	else if (lastName.length()>32 || lastName.length()<2)
		return "Il nome non puo' contenere meno di 2 caratteri e piu' di 32.";
	return "";
	}
	/**
	 * Controlla che l'indirizzo email inserito sia conforme al pattern
	 * stabilito.
	 * @param email Stringa contenente l'indirizzo email da controllare
	 * @return Stringa d'errore se l'email non è ben formata:	 * 
	 */
	public String checkEmail(String email){
		String res = validate(EMAIL_PATTERN, email);
		if(res=="INVALID") return "Inserire un indirizzo email valido.";
	else
		return res;
	}
	/**
	 * Controlla che le stringhe inserite compongano una data corrispondente
	 * al formato <code>yyyy-MM-dd</code> e valida.
	 * @param birthyear Stringa in formato <code>yyyy</code> indicante l'anno.
	 * @param birthmonth Stringa in formato <code>MM</code> indicante il mese.
	 * @param birthday Stringa in formato <code>dd</code> indicante il giorno.
	 * @return Un oggetto Date{@link java.util.Date} valido se i criteri sono
	 * rispettati, <code>null</code> altrimenti.
	 */
	public Date checkDate(String birthyear, String birthmonth, String birthday){
	    DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");
	    try{
	        Date birthdate = dtf.parseStrict(birthyear + "-"+ birthmonth + "-" + birthday);
	        return birthdate;
	        }    
	    catch(IllegalArgumentException e){
	        return null;
	    }
	}
	/**
	 * Metodo per il controllo della conformità di una stringa al pattern
	 * inserito.
	 * @param exp Pattern di cui applicare i criteri
	 * @param word Stringa da controllare
	 * @return Una stringa che segnali se il campo è vuoto o non conforme al
	 * pattern ("INVALID"). Vuota altrimenti.
	 */
	private String validate(final String exp, String word){
		RegExp pattern = RegExp.compile(exp);
		MatchResult match = pattern.exec(word);
		if(word.length()==0)
			return "Campo obbligatorio.";		
		if(match==null)
			return "INVALID";
		return "";
		}
}
