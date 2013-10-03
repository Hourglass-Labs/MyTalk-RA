package it.hourglass.myTalk.test;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import junit.framework.TestCase;

import org.junit.Test;

import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPTransport;

import it.hourglass.myTalk.server.model.businesslogic.EmailSender;

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
 *  0.6     | 2013/08/11  | Approvazione Test
 *  ---------+------------+------------------
 *  0.5     | 2013/08/04  | Revisione dei test a seguito della RQ
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

public class EmailSenderTest extends TestCase{

	/**
	 * ESITP: ATTESO </br>
	 * Campo del destinatario è valido, viene settatto
	 * lo stesso campo del miettente
	 */
//	@Test
	public void testSendEmail(){
		String recipient = "no-reply@hourglabs.org";
		String subject  = "PROVA";
		String content  = "PROVA";
		EmailSender es = new EmailSender(recipient, subject, content);
		es.sendEmail();
	}
	
	/**
	 * ESITO: ATTESO</br>
	 * La mail non riesce ade essere inviata in quanto
	 * il campo recipient non è valido e viene sollevata 
	 * un'eccezzione di tipo MessagingException nella 
	 * classe EmailSender al momento dell'invio della mail
	 */
	@Test
	public void testSendEmailTest2(){
		
			String recipient = "//";
			String subject  = "PROVA";
			String content  = "PROVA";
			EmailSender es = new EmailSender(recipient, subject, content);
			es.sendEmail();

		}

}
