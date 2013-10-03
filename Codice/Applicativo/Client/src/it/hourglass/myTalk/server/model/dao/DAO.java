package it.hourglass.myTalk.server.model.dao;

import java.util.List;
import org.hibernate.HibernateException;
import it.hourglass.myTalk.client.shared.Friendships;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.client.shared.User;
/**
 * Interfaccia dell'oggetto DAO. 
 * 
 * <p>L'interfaccia DAO  ha la funzione di fornire dei metodi al resto
 * dell'applicativo che si occupino di offrire degli immediati ed univoci punti d'accesso 
 * al database e quindi permettere la persistenza dei dati sfruttando il framework Hibernate.</p>
 * 
 * @author Gioele Lorenzo Cresce
 * @version 1.0
 */
public interface DAO {
	public String storeUser(User user);
	public boolean setPassword(String uniqueId, String password) throws HibernateException;
	public User fetchFriend(String uniqueId) throws HibernateException;
	public User fetchUserById(String uniqueId) throws HibernateException;
	public User fetchUserByEmail(String email) throws HibernateException;
	public String setValidation(String email, String validation) throws HibernateException;
	public boolean checkValidation(String uniqueId, String validation) throws HibernateException;
	public List<String> fetchFriendships(String uniqueId);
	public Friendships fetchSpecificFriendship(String friend1, String friend2);
	public List<Message> fetchMessages(String uniqueId);
	public void updateMessage(Message message) throws HibernateException;
	public void updateFriendship(Friendships friendship) throws HibernateException;
	public void deleteMessage(Message message) throws HibernateException;
	public void deleteFriendship(Friendships friendship) throws HibernateException;

	
}
