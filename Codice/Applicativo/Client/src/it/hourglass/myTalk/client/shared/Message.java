/*
 * Filename: Message.java
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
 *  0.1     | 2013/06/20 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.client.shared;

import java.util.Date;
/**
 *  Costituisce un messaggio di testo persistibile nel database.
 * 
 * <p>E' costituito da un identificativo univoco, un mittente, un destinatario,
 * la data di creazione, il corpo del messaggio e un indicatore che vada a
 * distinguere se si tratta di un messaggio normale oppure una richiesta 
 * d'amicizia.
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 *
 */
public class Message  implements java.io.Serializable {

     private Integer messageId;
     private String sender;
     private String recipient;
     private Date created;
     private String content;
     private Boolean friendReq = false;
     /**
      * Costruttore standard della classe.
      */
    public Message() {
    }
    /**
     * Costruttore della classe.
     * @param sender Mittente
     * @param recipient Destinatario
     * @param content Contenuto
     */
    public Message(String sender, String recipient, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }
	/**
	 * Costruttore della classe.
	 * @param sender Mittente
	 * @param recipient Destinatario
	 * @param created Data di creazione
	 */
    public Message(String sender, String recipient, Date created) {
        this.sender = sender;
        this.recipient = recipient;
        this.created = created;
    }
    /**
     * Costruttore della classe completo.
     * @param sender Mittente
     * @param recipient Destinatario
     * @param created Data di creazione
     * @param content Contenuto
     * @param friendReq <code>true</code> se è una richiesta d'amicizia
     */
    public Message(String sender, String recipient, Date created, String content, Boolean friendReq) {
       this.sender = sender;
       this.recipient = recipient;
       this.created = created;
       this.content = content;
       this.friendReq = friendReq;
    }
   /**
    * Restituisce l'identificatore univoco del messaggio.
    * @return messageId identificatore univoco del messaggio
    */
    public Integer getMessageId() {
        return this.messageId;
    }
    /**
     * Modifica l'identificatore univoco del messaggio.
     * @param messageId Nuovo identificatore univoco
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    /**
     * Restituisce l'identificativo univoco del mittente.
     * @return sender identificativo univoco del mittente
     */
    public String getSender() {
        return this.sender;
    }
    /**
     * Modifica il mittente.
     * @param sender Identificativo univoco del nuovo mittente
     */
    public void setSender(String sender) {
        this.sender = sender;
    }
    /**
     * Restituisce l'identificativo univoco del destinatario.
     * @return recipient identificativo univoco del destinatario
     */
    public String getRecipient() {
        return this.recipient;
    }
    /**
     * Modifica il destinatario.
     * @param recipient identificativo univoco del destinatario
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    /**
     * Restituisce la data di creazione del messaggio.
     * @return created Data di creazione del messaggio
     */
    public Date getCreated() {
        return this.created;
    }
    /**
     * Modifica la data di creazione del messaggio.
     * @param created Nuova data di creazione del messaggio
     */
    public void setCreated(Date created) {
        this.created = created;
    }
    /**
     * Restituisce il contenuto del messaggio.
     * @return content Contenuto del messaggio
     */
    public String getContent() {
        return this.content;
    }
    /**
     * Modifica il contenuto del messaggio.
     * @param content Nuovo contenuto del messaggio
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * Restituisce se il messaggio è una richiesta d'amicizia o meno.
     * @return <code>true</code> se il messaggio è una richiesta d'amicizia,
     * <code>false</code> altrimenti
     */
    public Boolean getFriendReq() {
        return this.friendReq;
    }
    /**
     * Modifica lo status di richiesta d'amicizia di un messaggio.
     * @param friendReq <code>true</code> se il messaggio è una richiesta d'amicizia,
     * <code>false</code> altrimenti
     */
    public void setFriendReq(Boolean friendReq) {
        this.friendReq = friendReq;
    }

}