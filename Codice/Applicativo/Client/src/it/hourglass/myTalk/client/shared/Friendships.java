/*

 * Filename: Friendship.java
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
/**
 *  * Tale classe modella, in forma POJO, la relazione di amicizia che intercorre tra
 * due Contatti. 
 * 
 * <p>Oltre ad un intero rappresentante un indice biunivoco per indicare 
 * il rapporto d'amicizia, vengono memorizzati gli identificativi univoci
 * associati agli account degli utenti interessati dalla relazione e un
 * campo <code>Boolean</code> indicante la provvisorietà o meno della
 * relazione. Nel momento in cui avverrà un'accettazione dell'amicizia,
 * il valore del suddetto campo è progettato per essere settato a 
 * <code>true</code>
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 *
 */
public class Friendships  implements java.io.Serializable {
    private Integer friendshipId;
    private String friend1;
    private String friend2;
    private Boolean accepted;
    /**
     * Costruttore standard della classe.
     */
   public Friendships() {
   }

	/**
	 * Costruttore della classe richiedente i due identificativi
	 * univoci.
	 * @param friend1
	 * @param friend2
	 */
   public Friendships(String friend1, String friend2) {
       this.friend1 = friend1;
       this.friend2 = friend2;
   }
   /**
    * Costruttore della classe richiedente i due identificativi
    * univoci e l'indicatore di accettazione dell'amicizia.
    * @param friend1
    * @param friend2
    * @param accepted
    */
   public Friendships(String friend1, String friend2, Boolean accepted) {
      this.friend1 = friend1;
      this.friend2 = friend2;
      this.accepted = accepted;
   }
  /**
   * Metodo che ritorna l'identificativo univoco del rapporto d'amicizia
   * @return friendshipId Intero indicante l'identificativo univoco del 
   * rapporto di amicizia.
   */
   public Integer getFriendshipId() {
       return this.friendshipId;
   }
   /**
    * Metodo per modificare l'identificativo univoco del rapporto
    * d'amicizia.
    * @param friendshipId
    */
   public void setFriendshipId(Integer friendshipId) {
       this.friendshipId = friendshipId;
   }
   /**
    * Ritorna una stringa indicante l'identificativo univoco
    * di uno dei due utenti (1)
    * @return friend1 Identificativo univoco dell'utente 1
    */
   public String getFriend1() {
       return this.friend1;
   }
   /**
    * Modifica l'identificativo univoco di uno dei due utenti (1)
    * @param friend1 Nuovo identificativo univoco.
    */
   public void setFriend1(String friend1) {
       this.friend1 = friend1;
   }
   /**
    * Ritorna una stringa indicante l'identificativo univoco
    * di uno dei due utenti (2)
    * @return friend2 Identificativo univoco dell'utente 2
    */
   public String getFriend2() {
       return this.friend2;
   }
   /**
    * Modifica l'identificativo univoco di uno dei due utenti (2)
    * @param friend2 Nuovo identificativo univoco.
    */
   public void setFriend2(String friend2) {
       this.friend2 = friend2;
   }
   /**
    * Ritorna il valore indicante l'accettazione dell'amicizia.
    * @return  <code>true</code> se l'amicizia risulta accettata
    * (definitiva), <code>false</code> altrimenti.
    */
   public Boolean getAccepted() {
       return this.accepted;
   }
   /**
    * Imposta il valore indicante l'accettazione dell'amicizia
    * @param accepted <code>true</code> se l'amicizia risulta accettata
    * (definitiva), <code>false</code> altrimenti.
    */
   public void setAccepted(Boolean accepted) {
       this.accepted = accepted;
   }

}