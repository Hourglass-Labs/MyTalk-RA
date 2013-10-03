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
 *  1.0     | 2013/09/10  | Approvazione classe
 * ---------+------------+------------------
 *  0.1     | 2013/06/2 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.server.model.businesslogic;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
/**
 * Classe utilizzata per l'invio di email agli utenti.
 * 
 * @author Giovanni Morlin
 *
 */
public class EmailSender {
  private String user     = "no-reply@hourglabs.org";
  private String password = "12345glass678910";;
  private String host     = "mail.hourglabs.org";
  private String sender = "no-reply@hourglabs.org";
  private String subject;
  private String recipient;
  private String content;
 
  /**
  * Costruttore completo, richiede i parametri
  * di connessione al server di posta
  * @param recipient
  * @param content
  * @param subject
  */
  public EmailSender(String recipient, String subject, String content){
	  this.recipient = recipient;
	  this.subject = subject;
	  this.content = content;
  }
 
  // Metodo che si occupa dell'invio effettivo della mail
  public void sendEmail() {
    int port = 465; //porta 25 per non usare SSL
 
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.user", sender);
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);
 
    // commentare la riga seguente per non usare SSL 
    props.put("mail.smtp.starttls.enable","true");
    props.put("mail.smtp.socketFactory.port", port);
 
    // commentare la riga seguente per non usare SSL 
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");
 
    Session session = Session.getInstance(props, null);
    session.setDebug(true);
 
    // Creazione delle BodyParts del messaggio
    MimeBodyPart messageBodyPart1 = new MimeBodyPart();
 
    try{
      // COSTRUZIONE DEL MESSAGGIO
      Multipart multipart = new MimeMultipart();
      MimeMessage msg = new MimeMessage(session);
 
      // header del messaggio
      msg.setSubject(subject);
      msg.setSentDate(new Date());
      msg.setFrom(new InternetAddress(sender));
 
      // destinatario
      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
 
      // corpo del messaggio
      messageBodyPart1.setText(content);
      multipart.addBodyPart(messageBodyPart1);
 
      // inserimento delle parti nel messaggio
      msg.setContent(multipart);
 
      Transport transport = session.getTransport("smtps"); //("smtp") per non usare SSL
      transport.connect(host, user, password);
      transport.sendMessage(msg, msg.getAllRecipients());
      transport.close();
 
      System.out.println("Email Sent.");
 
    }catch(AddressException ae) {
      ae.printStackTrace();
    }catch(NoSuchProviderException nspe){
      nspe.printStackTrace();
    }catch(MessagingException me){
      me.printStackTrace();
    }
  }
}