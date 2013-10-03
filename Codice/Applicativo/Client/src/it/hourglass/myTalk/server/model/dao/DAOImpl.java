/*
 * Filename: FriendMessagePresenter.java
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
 *  0.3     | 2013/06/20  | Aggiunta metodo richiesta amicizia 
 *  ---------+------------+------------------
 *  0.2    	|  2013/06/2 | Aggiunta metodi per il login e registrazione
 * ---------+------------+------------------
 *  0.1     | 2013/05/30 |Codifica classe
 *  ---------+------------+------------------
 * This software is distributed under GNU/GPL 2.0.
 * 
 * 
 */

package it.hourglass.myTalk.server.model.dao;

import java.util.List;

import it.hourglass.myTalk.client.shared.Friendships;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.server.model.businesslogic.Security;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 * Classe designata alla persistenza dei dati sfruttando il framework Hibernate.
 * 
 * <p>La presente classe ha lo scopo di fornire metodi utilizzabili 
 * dall'applicativo per sfruttare gli strumenti offerti da Hibernate 
 * automaticamente, senza necessità di conoscerne il funzionamento.
 * Ciascun metodo soddisfa i diversi bisogni che possano essere riscontrati 
 * dalle varie funzionalità dell'applicativo.</p>
 * 
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
	public class DAOImpl implements DAO{
	private static DAO instance;
	/**
	 * Costruttore della classe.
	 * 
	 * <p>E' reso private per far coincidere la struttura della classe con quella
	 * di un Singleton. Lo scopo è assicurare che gli accessi al DB da parte delle
	 * servlet siano sempre possibile da un unico punto d'accesso.</p>
	 */
	private DAOImpl(){
		
	}
	/**
	 * Restituisce un'istanza della classe o la crea nel caso questa non sia stata
	 * ancora inizializzata.
	 * @return instance Istanza della classe DAOImpl
	 */
	public static synchronized DAO getInstance(){
	    if (instance == null)
	      instance = new DAOImpl();
	    return instance; 
	  }
/**
 * Fornisce una sessione Hibernate.
 * 
 * <p>Permette l'ottenimento di una sessione valida sfruttando la classe di 
 * supporto HibernateUtil. Ogni funzionalità di Hibernate è raggiungibile
 * attraverso questa.<p>
 * 
 * @see it.hourglass.myTalk.server.model.dao.DAO
 * @return Sessione valida per utilizzare i metodi ORM di Hibernate.
 */
	private Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	/**
	 * Persiste un utente nel database.
	 * 
	 * <p>Ricevuto un oggetto User{@link it.hourglass.myTalk.client.shared.User} in parametro, 
	 * questo viene memorizzato nel DB associato. Viene ritornata una stringa 
	 * che descrive l'esito dell'operazione.</p>
	 * 
	 * @see it.hourglass.myTalk.server.model.dao.HibernateUtil
	 * @param user Oggetto User che si desidera memorizzare.
	 * @return  String che offre un resoconto dell'esito dell'operazione.
	 */
	@Override	
	public String storeUser(User user){
		try{
	        Session session = getSession();
	        session.beginTransaction();
	        session.saveOrUpdate(user);
	        session.getTransaction().commit();
	        return "Profilo aggiornato. ";
	      }
	      catch(HibernateException er){
	          System.err.println(er.getCause());
	          return "Database al momento non raggiungibile.";
	      }
	}
	/**
	 * Ritorna un oggetto User{@link it.hourglass.myTalk.client.shared.User} dal DB
	 * cercandolo per 'uniqueId'.
	 * 
	 * <p>Fornendo al metodo un identificativo univoco, questo ritorna un
	 * oggetto User{@link it.hourglass.myTalk.client.shared.User} corrispondente
	 * al record nel DB avente l'identificativo univoco come chiave.
	 * 	 * 
	 *  @param uniqueId Stringa corrispondente all'identificativo univoco 
	 *  dell'Utente cercato.
	 *  @return L'oggetto User{@link it.hourglass.myTalk.client.shared.User} associato
	 *  al record dell'Utente cercato. <code>null</code> se l'Utente non esiste.
	 */
	@Override
	public User fetchUserById(String uniqueId){
		try{
			Session session = getSession();
			session.beginTransaction();
			User user = (User)session.get(User.class, uniqueId);
			session.getTransaction().commit();
			return user;
        		}
		catch(HibernateException er){
			throw er;
      }
	}
	/**
	 * Ritorna un oggetto User{@link it.hourglass.myTalk.client.shared.User} dal DB
	 * cercandolo per 'email'.
	 * 
	 * <p> Fornita l'email dell'Utente che si desidera trovare, viene ritornato l'oggetto User{@link it.hourglass.myTalk.client.shared.User}
	 * corrispondente alla sua voce nel DB.</p>
	 * 
	 * @param userEmail Stringa dell'indirizzo email dell'Utente cercato.
	 * @return user L'oggetto User{@link it.hourglass.myTalk.client.shared.User} associato
	 *  al record dell'Utente cercato. <code>null</code> se l'Utente non esiste.
	 */
	@Override
	public User fetchUserByEmail(String userEmail) throws HibernateException{
		String query = "from User u where u.email = :userEmail";
		Session session = getSession();
		session.beginTransaction();
		User user =(User)session.createQuery(query)
				.setString("userEmail", userEmail).uniqueResult();
		session.getTransaction().commit();
		return user;
	}
	/**
	 * Ritorna un oggetto User{@link it.hourglass.myTalk.client.shared.User}
	 * dopo averne settato il campo password a stringa vuota.
	 * 
	 * <p>Utilizzato per ricevere da database un oggetto User{@link it.hourglass.myTalk.client.shared.User}
	 * associato al campo 'uniqueId' fornito e privato di password per motivi di sicurezza.</p>
	 * 
	 * @param uniqueId Stringa corrispondente all'identificativo univoco dell'Utente cercato.
	 * @return L'oggetto User{@link it.hourglass.myTalk.client.shared.User} associato
	 *  al record dell'Utente cercato, privato di password. <code>null</code> se l'Utente non esiste.
	 */
	@Override
	public User fetchFriend(String uniqueId){
		try{
			User friend = fetchUserById(uniqueId);
			if(friend != null)
				friend.setPassword("");
			return friend;
        		}
		catch(HibernateException er){
			throw er;
		}
	}
	/**
	 * Attribuisce una stringa al campo 'validation' del record associato
	 * all' indirizzo email desiderato.
	 * 
	 * <p>Ricevuti in parametro indirizzo email e codice di validazione 
	 * desiderati, il metodo ricerca il record associato a tale indirizzo
	 * e ne modifica il campo 'validation'. Ritorna infine a quale identificativo
	 * unico il record è associato, o un messaggio d'errore se l'indirizzo email
	 * non è associato ad alcun record.
	 * 
	 * @param email Indirizzo email dell'utente.
	 * @param validation Codice di validazione casuale da associare all'account.
	 * @return uniqueId Identificativo unico dell'account associato o una stringa
	 * di errore se l'email non è associata ad alcun account.
	 */
	@Override
	public String setValidation(String email, String validation) throws HibernateException{
		try{
			User user = fetchUserByEmail(email);
			if(user == null)
				return "L'indirizzo email inserito non appartiene ad alcun utente MyTalk.";
			user.setValidation(validation);
			Session session = getSession();
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
			return user.getUniqueId();
		}
		catch(HibernateException er){
			System.err.println(er.getCause());
			throw er;
		}
	}
	/**
	 * Controlla che il codice di validazione inserito sia quello associato 
	 * all'account richiesto.
	 * 
	 * <p>Fornito un appropriato 'uniqueId', il metodo controlla che esista un
	 * account associato a questo e che il codice di validazione inserito sia
	 * quello correttamente ad esso associato. Ritorna <code>true</code> se ciò
	 * accade, <code>false</code> altrimenti.</p>
	 * 
	 * @param uniqueId Identificativo univoco dell'account associato.
	 * @param validation Codice di validazione da controllare.
	 * @return  <code>true</code> se i codici coincidono, <code>false</code> altrimenti.
	 */
	@Override
	public boolean checkValidation(String uniqueId, String validation) 
			throws HibernateException{
		try{
			User user = fetchUserById(uniqueId);
			if(user==null)
				return false;
			if(user.getValidation().equals(validation)){
				user.setEnabled(true);
				Session session = getSession();
				session.beginTransaction();
				session.update(user);
				session.getTransaction().commit();
				return true;
			}
			
			return false;
		}
	      catch(HibernateException er){
	    	  System.err.println(er.getCause());
	    	  return false;
	      }
	}
	/**
	 * Attribuisce la password desiderata all'account associato all' 'uniqueId'
	 * fornito.
	 * 
	 * <p>Fornito un identificativo univoco e una password, la seconda è attribuita al
	 * record associato al primo. Se l'account non esiste viene ritornato un valore <code>
	 * false</code>.</p>
	 * 
	 * @param uniqueId L'identificativo univoco associato all'account desiderato.
	 * @param password La password che si desidera associare all'account.
	 * @return   <code>true</code> se l'operazione è andata a buon fine, <code>false</code>
	 * altrimenti o se l'account non esiste.
	 */
	@Override
	public boolean setPassword(String uniqueId, String password) 
			throws HibernateException{
		try{
			User user = fetchUserById(uniqueId);
			if(user==null){
				return false;
		}
		Security securitybox = new Security();
        user.setPassword(securitybox.encryptPassword(password));
		Session session = getSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        return true;
		}
		catch(HibernateException er){
	    	  System.err.println(er.getCause());
	    	  return false;
			}
	}
	/**
	 * Ritorna gli identificativi degli amici di un Utente.
	 * 
	 * <p>Fornito l'identificativo univoco associato all'account di un Utente,
	 * è ritornata una lista di identificativi univoci associati agli account
	 * degli amici.</p>
	 * <p>Se non esiste alcun account associato al dato identificativo univoco,
	 * viene ritornato il valore <code>null</code>
	 * 
	 * @param uniqueId Identificativo univoco dell'utente di cui si desidera ottenere
	 * la lista di amicizie
	 * @return friendlist List di amicizie associate all'utente. <code>null</code>
	 * se l'identificativo non è associato ad alcun account.
	 */
	@Override	
	public List<String> fetchFriendships(String uniqueId){
		try{
			String query = "SELECT f.friend2 FROM Friendships AS f WHERE f.friend1 = :uniqueId AND f.accepted = TRUE" +
					" UNION SELECT f.friend1 FROM Friendships AS f WHERE f.friend2 = :uniqueId AND f.accepted = TRUE";
			Session session = getSession();
			session.beginTransaction();
			List<String> friendlist = session.createSQLQuery(query)
					.setString("uniqueId", uniqueId).list();
			session.getTransaction().commit();
			return friendlist;
		}
		catch(HibernateException er){
			throw er;
		}
	}
	/**
	 * Passati due identificativi univoci in parametro, ritorna un oggetto
	 * rappresentante il loro rapporto di contatti amici.
	 * 
	 * @return friendship oggetto Friendships {@link it.hourglass.myTalk.client.shared.Friendships}
	 * costruito sul record identificante il rapporto di contatti amici
	 * tra i due utenti rispettivamente associati agli identificativi univoci specificati.
	 */
	@Override
	public Friendships fetchSpecificFriendship(String friend1, String friend2){
		try{
			String query = "from Friendships AS f where (f.friend1 = :friend1 and f.friend2= :friend2)" +
					" or (f.friend1 = :friend2 and f.friend2 = :friend1)";
			Session session = getSession();
			session.beginTransaction();
			Query q = session.createQuery(query);
			q.setString("friend1", friend1);
			q.setString("friend2", friend2);
			Friendships friendship = (Friendships)q.uniqueResult();
			session.getTransaction().commit();
			return friendship;
		}
		catch(HibernateException er){
			throw er;
		}
	}
	/**
	 * Ritorna tutti i messaggi con destinatario l'utente associato all'identificativo
	 * univoco passato in parametro.
	 * 
	 * @return messagelist List{@link java.util.List} di Message{@link it.hourglass.myTalk.client.shared.Message}
	 * costruiti su record di messaggi destinati all'utente specificato.
	 */
	@Override
	public List<Message> fetchMessages(String uniqueId){
		try{
			String query = "from Message AS m where m.recipient = :uniqueId";
			Session session = getSession();
			session.beginTransaction();
			List<Message> messagelist = session.createQuery(query)
					.setString("uniqueId", uniqueId).list();
			session.getTransaction().commit();
			return messagelist;
		}
		catch(HibernateException er){
			throw er;
		}
	}
	/**
	 * Crea o provoca un update del record su cui è costruito l'oggetto
	 * Message {@link it.hourglass.myTalk.client.shared.Message} passato
	 * in parametro.
	 */
	@Override
	public void updateMessage(Message message){
		try{
			Session session = getSession();
	        session.beginTransaction();
	        session.saveOrUpdate(message);
	        session.getTransaction().commit();
	      }
	      catch(HibernateException er){
	          System.err.println(er.getCause());
	          throw er;
	      }
	}
	/**
	 * Crea o provoca un update del record su cui è costruito l'oggetto
	 * Friendships {@link it.hourglass.myTalk.client.shared.Friendships} passato
	 * in parametro.
	 */
	@Override
	public void updateFriendship(Friendships friendship){
		try{
			Session session = getSession();
	        session.beginTransaction();
	        session.saveOrUpdate(friendship);
	        session.getTransaction().commit();
	      }
	      catch(HibernateException er){
	          System.err.println(er.getCause());
	          throw er;
	      }
	}
	/**
	 * Elimina dal database il record su cui è costruito
	 * Friendships {@link it.hourglass.myTalk.client.shared.Friendships} passato
	 * in parametro.
	 */
	public void deleteFriendship(Friendships friendship){
		Session session = getSession();
		try{
	        session.beginTransaction();
	        session.delete(friendship);
	        session.getTransaction().commit();
	      }
	      catch(HibernateException er){
	          System.err.println(er.getCause());
	          session.getTransaction().rollback();
	          throw er;
	      }
	}
	/**
	 * Elimina dal database il record su cui è costruito
	 * Message {@link it.hourglass.myTalk.client.shared.Message} passato
	 * in parametro.
	 */
	public void deleteMessage(Message message){
		Session session = getSession();
		try{
	        session.beginTransaction();
	        session.delete(message);
	        session.getTransaction().commit();
	      }
	      catch(HibernateException er){
	          System.err.println(er.getCause());
	          session.getTransaction().rollback();
	          throw er;
	      }
	}
	
}
