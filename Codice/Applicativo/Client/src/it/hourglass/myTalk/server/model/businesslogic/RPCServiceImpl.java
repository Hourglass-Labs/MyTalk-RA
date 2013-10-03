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

package it.hourglass.myTalk.server.model.businesslogic;


import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import it.hourglass.myTalk.client.rpcservice.RPCService;
import it.hourglass.myTalk.client.shared.Friendships;
import it.hourglass.myTalk.client.shared.SignIn;
import it.hourglass.myTalk.client.shared.User;
import it.hourglass.myTalk.client.shared.Message;
import it.hourglass.myTalk.server.model.dao.DAO;
import it.hourglass.myTalk.server.model.dao.DAOImpl;
/**
 *
 * @author Gioele Lorenzo Cresce
 */
public class RPCServiceImpl extends RemoteServiceServlet implements RPCService {
	
	private DAO DAO = DAOImpl.getInstance();
	private Security tools = new Security();
	
	/**
	 * Ricevuto un oggetto User{@link it.hourglass.myTalk.client.shared.User}
	 * in parametro, utilizza metodi dell'oggetto DAO{@link it.hourglass.myTalk.client.model.DAO.DAO}
	 * per permetterne la persistenza nel DB.
	 * 
	 * <p>Viene verificato che non esistano record associati al medesimo indirizzo
	 * email dell'oggetto User che deve essere memorizzato. In caso negativo, 
	 * viene invocato il metodo dell'oggetto DAO appropriato alla sua memorizzazione
	 * nel DB</p>
	 * 
	 * @param user Oggetto User che si desidera memorizzare nel DB.
	 * @param encryption Se impostato a <code>true</code>, prima di memorizzare
	 * l'oggetto User, il suo campo password viene criptato.
	 * 
	 * @return Una stringa con l'esito dell'operazione.
	 */
	@Override
	public String storeUser(User user, boolean encryption) {
		try{
			User conflict = DAO.fetchUserByEmail(user.getEmail());
			if(conflict != null && !(conflict.getUniqueId()
					.equals(user.getUniqueId())))
				return "Indirizzo email già associato ad un altro account.";
			if(encryption)
				user.setPassword(tools.encryptPassword(user.getPassword()));
			return DAO.storeUser(user);
		}
		catch(HibernateException er){
			System.err.println(er.getCause());
			return "Database al momento non raggiungibile.";
    }
 }
	/**
	 * Ricevuto un oggetto User{@link it.hourglass.myTalk.client.shared.User}
	 * in parametro, utilizza metodi dell'oggetto DAO{@link it.hourglass.myTalk.client.model.DAO.DAO}
	 * per permetterne la persistenza nel DB.
	 * 
	 * <p>Viene verificato che non esistano record associati al medesimo indirizzo
	 * email o uniqueId dell'oggetto User che deve essere memorizzato. 
	 * In caso negativo, viene invocato il metodo dell'oggetto DAO appropriato alla sua memorizzazione
	 * nel DB, non prima che il campo password sia stato criptato e il campo
	 * validation settato a <code>false</code>.</p> A seguito di tale operazione,
	 * viene poi inviata un'email all'indirizzo email dell'utente associato all'oggetto
	 * User in questione.
	 * 
	 * @param user Oggetto User che si desidera memorizzare nel DB.
	 * 
	 * 
	 * @return Una stringa con l'esito dell'operazione.
	 */
	@Override
	public String register(User user){
		try{
	        if(DAO.fetchUserById(user.getUniqueId())!= null)
	        	return "Login già in uso.";
	        if(DAO.fetchUserByEmail(user.getEmail())!= null)
	        	return "Indirizzo email già in uso.";
	        user.setPassword(tools.encryptPassword(user.getPassword()));
	        user.setValidation(tools.generateValidation());
	        user.setEnabled(false);
	        DAO.storeUser(user);
			String subject = "hourglass MyTalk - Codice di Conferma Registrazione";
			String content = "Gentile "+ user.getName()+",\nstai ricevendo questa email "+
								"in quanto e' stata avviata una procedura di registrazione al nostro servizio."+
								"\n\nSe ritieni che ci sia un errore, ignorala semplicemente, altrimenti " + 
								"il codice identificativo che dovrai inserire e':\n\n" + user.getValidation() +
								"\n\nGrazie della pazienza!\n\nTeam hourglass - MyTalk";
					
			EmailSender es = new EmailSender(user.getEmail(), subject, content);
			es.sendEmail();
	        return "Il tuo account è stato creato, ma non puoi ancora accedere a tutte le funzionalita' di MyTalk. " +
	        		" Abbiamo inviato un'email all'indirizzo inserito. Dopo averla ricevuta, procedi con" +
					" \"Conferma Registrazione\"";
	      }
	      catch(HibernateException er){
	          System.err.println(er.getCause());
	          return "Database al momento non raggiungibile.";
	      }
	}
	

  	@Override
  	/**
  	 * Verifica attraverso il metodo {@link it.hourglass.myTalk.model.RPCServiceImpl.loginValidation} che lo
  	 * username e la password siano validi. Se questo è il caso crea una nuova
  	 *  sessione. In caso contrario restituisce false.
  	 */
  	public Boolean checkLogin(String userName, String password) {
  		try{
  			if(loginValidation(userName, password)){
  				HttpServletRequest request = getThreadLocalRequest();	       
  				HttpSession s = request.getSession(true);
  				s.setAttribute("username", userName);
  				s.setAttribute("ip", request.getRemoteHost());
  				s.setAttribute("inizio-sessione", Calendar.getInstance());
  				return true;	
  			}
  			else{ 
  				return false;
  				}
  		}
  		catch(HibernateException hibernate){
  			System.err.print("Problemi di accesso al DB: " + hibernate.getLocalizedMessage());
  			return false;
  			}
  		}
  	/**
  	 * Ricevuto un uniqueId in parametro, ritorna un oggetto SignIn{@link it.hourglass.myTalk.client.shared.SignIn}
  	 * opportunamente costruito. 
  	 * 
  	 * <p>L'oggetto ritornato contiene a sua volta: un oggetto User{@link it.hourglass.myTalk.client.shared.User}
  	 * associato all'uniqueId passato in parametro; un oggetto HashMap {@link java.util.HashMap}, 
  	 * composto da coppie chiave-valore di tipo <String, Boolean>, di cui
  	 * ciascuna stringa contiene un uniqueId associato ad un account amico
  	 * dell'account associato all'uniqueId passato in parametro e di cui ciascun 
  	 * Boolean è inizialmente settato a false.</p>; un oggetto List{@link java.util.List}, 
  	 * costituente una collezione di oggetti Message{@link it.hourglass.myTalk.client.shared.Message}
  	 * con destinatario l'utente associato al campo uniqueId passato in parametro.</p>
  	 * 
  	 *  @param uniqueId uniqueId associato all'account di cui si desidera
  	 *  recuperare i dati.
  	 *  
  	 *  @return signedIn Oggetto SignIn appositamente composto o <code>null</code>
  	 *  se non esiste alcun record associabile allo uniqueId passato in parametro.
  	 */
  	public SignIn signIn(String uniqueId){
  		List<String> friendlist = DAO.fetchFriendships(uniqueId);
		SignIn signedIn = new SignIn(DAO.fetchUserById(uniqueId), DAO.fetchMessages(uniqueId));
		for(String contact : friendlist)
			signedIn.getFriendList().put(contact, false);
		return signedIn;
  			}
	/**
	 * Ritorna un oggetto User{@link it.hourglass.myTalk.client.shared.User}
	 * la cui password è settata a stringa vuota.
	 * 
	 * @return oggetto User{@link it.hourglass.myTalk.client.shared.User}
	 */
  	public User fetchFriendProfile(String uniqueId){
		return DAO.fetchFriend(uniqueId);
	}
  	
	/**
  	 * Interroga il DB per verificare che esista una corrispondenza della coppia
  	 * di campi uniqueId e password passati in parametro.
  	 * 
  	 * @param uniqueId
  	 * @param password
  	 * @return
  	 * @throws HibernateException
  	 */
  	private boolean loginValidation(String uniqueId, String password) throws HibernateException{
		try{
	        User user = DAO.fetchUserById(uniqueId);
	        if(user!= null){
	        	if(tools.checkPasswords(password, user.getPassword()) && user.getEnabled()){
	        		return true;
	        	}
	        return false;
	        }
	        return false;
		}
	      catch(HibernateException er){
	         throw er;
	      }
	}
  	
	@Override
	/**
	 * Si occupa di delegare alle classi specializzate
	 * la creazione del codice di sicurezza per il cambio
	 * password e di associarlo all'email dell'utente che 
	 * ha richiesto il servizio. Infine crea e invia il 
	 * messagio email con il codice di sicurezza all'
	 * email associata all'utente.
	 */
	public String setValidation(String email) {
		User i = DAO.fetchUserByEmail(email);
		if(i == null)
			return("");
		else{
			String validation  = tools.generateValidation();
			String uniqueId = DAO.setValidation(email, validation);
			String subject = "hourglass MyTalk - Codice di verifica";
			String content = "Gentile Utente,\nstai ricevendo questa email "+
								"in quanto e' stata avviata una procedura di cambio password per il tuo account."+
								"\n\nSe ritieni che ci sia un errore, ignorala semplicemente, altrimenti " + 
								"il codice identificativo che dovrai inserire e':\n\n" + validation +
								"\n\nGrazie della pazienza!\n\nTeam hourglass - MyTalk";
					
			EmailSender es = new EmailSender(email, subject, content);
			es.sendEmail();
			
			return uniqueId;
		}
		
	}
  	/**
  	 * Verifica che la stringa validation passata in parametro sia correttamente
  	 * associata alla voce nel DB relativa al campo uniqueId passato.
  	 * 
  	 * @return <code>true</code> se la corrispondenza è verificata, <code>false</code>
  	 * altrimenti.
  	 */
	@Override
	public Boolean checkValidation(String uniqueId, String validation) {
		return DAO.checkValidation(uniqueId, validation);
	}
	/**
	 * Consente la modifica del campo password del record associato all'uniqueId passato
	 * in parametro.
	 * 
	 * @return <code>true</code> se l'operazione risulta andata a buon fine, <code>false</code>
	 * altrimenti.
	 */
	@Override
	public Boolean setPassword(String uniqueId, String password) {
		return DAO.setPassword(uniqueId, password);
	}
	/**
	 * Metodo destinato alla memorizzazione nel database di messaggi testuali o d'amicizia.
	 * 
	 * <p>Ricevuto in parametro un oggetto di tipo Message{@link it.hourglass.myTalk.client.shared.Message},
	 * si determina il tipo di messaggio che deve essere memorizzato. Nel caso questo sia una richiesta di amicizia,
	 * vengono compiuti dei controlli mediante classe DAO{@link it.hourglass.myTalk.server.model.dao.DAO}
	 * così da accertarsi che l'utente destinatario esista, non risulti già amico
	 * del mittente o non sia stata già inoltrata una richiesta d'amicizia da parte
	 * dello stesso mittente. Se gli esiti sono positivi, un'amicizia non confermata è creata nel DB.
	 * In caso contrario, delle stringhe d'errore sono ritornate.
	 * Sia nel caso che si tratti di una richiesta d'amicizia che di un semplice messaggio,
	 * il messaggio stesso è poi persistito nel database.</p>
	 * 
	 * @return Stringhe indicanti i diversi esiti delle operazioni compiute.
	 */
	@Override
	public String sendMessage(Message mess){
		try{
			if(mess.getFriendReq()){
				User user = DAO.fetchUserById(mess.getRecipient());
				if(user == null)
					return "L'utente desiderato non esiste.";
				Friendships friendship = DAO.fetchSpecificFriendship(mess.getSender(), mess.getRecipient());
				if(friendship!=null){
					if(friendship.getAccepted())
						return "L'utente selezionato è già presente tra le tue amicizie.";
				return "Esiste già una richiesta d'amicizia tra te e l'utente indicato.";
				}
				DAO.updateFriendship(new Friendships(mess.getSender(), mess.getRecipient(), false));							
			}
			DAO.updateMessage(mess);
			return "Messaggio inviato con successo.";
  			
  		}
  		catch(HibernateException hibernate){
  			return "Database al momento non raggiungibile. ";
  			}
	}
	/**
	 * Rimuove dal database un record associato al messaggio passato in parametro
	 * 
	 * @param mess Messaggio da eliminare
	 * @return  Stringhe indicanti l'esito dell'operazione.
	 */
	@Override
	public String deleteMessage(Message mess){
		try{
			DAO.deleteMessage(mess);
			return "Messaggio rimosso con successo.";
  			
  		}
  		catch(HibernateException hibernate){
  			return "Operazione al momento non disponibile. ";
  			}
	}
	/**
	 * Ritorna una lista aggiornata dei messaggi associati all'identificativo
	 * univoco passato in parametro.
	 * 
	 * @return  Un oggetto List{@link java.util.List} contenente i messaggi associati
	 * all'identificativo.
	 */
	@Override
	public List<Message> refreshMessageList(String uniqueId){
		return DAO.fetchMessages(uniqueId);		
	}
	@Override
	/**
	 * Metodo finalizzato all'accettazione di richieste d'amicizia intercorse tra due utenti.
	 * 
	 * <p>L'oggetto Message{@link it.hourglass.myTalk.client.shared.Message} associato
	 * alla richiesta d'amicizia, così come l'esito espresso in responso alla stessa
	 * sono passati in parametro. Viene preliminarmente verificato che nel tempo
	 * intercorso nella risposta la richiesta d'amicizia non sia stata in qualche
	 * modo eliminata. Se ciò non è, a seconda del responso viene abilitata
	 * l'amicizia creata durante l'inoltro della richiesta originale o eliminata
	 * del tutto l'amicizia temporanea. In entrambi i casi, il messaggio relativo
	 * è eliminato dal database. </p>
	 * 
	 * @return  <code>true</code> Nel caso in cui le operazioni siano andate a buon fine
	 * e l'amicizia accettata, <code>false</code> altrimenti.
	 */
	public Boolean friendshake(Message request, boolean accepted){
		try{
			Friendships friendship = DAO.fetchSpecificFriendship(request.getSender(), request.getRecipient());
			if(friendship == null)
				return false;
			if(accepted){			
				friendship.setAccepted(true);
				DAO.updateFriendship(friendship);
				DAO.deleteMessage(request);
				return true;			
			}
			else{
				DAO.deleteFriendship(friendship);
				DAO.deleteMessage(request);
				return false;
			}		
		}
		catch(HibernateException er){
			System.err.println(er.getMessage());
			return false;
		}
	}
	/**
	 * Elimina il record rappresentante il rapporto di amicizia instaurato tra
	 * due utenti.
	 * 
	 * @return  Una stringa con l'esito dell'operazione.
	 */
	@Override
	public String removeFriend(String user, String friend){
		try{
			Friendships friendship = DAO.fetchSpecificFriendship(user, friend);
			DAO.deleteFriendship(friendship);
			return "L'utente "+ friend + " e' stato rimosso dal tuo elenco amicizie.";
  			
  		}
  		catch(HibernateException hibernate){
  			return "Operazione al momento non disponibile. ";
  			}
	}
	@Override
  	/**
  	 * Si occupa di verificare che la sessione con
  	 * il client sia valida. Se questo è il caso 
  	 * aggiorna lo stato della sessione e 
  	 * ritorna il parametro di sessione username.
  	 * Altrimenti ritorna null.
  	 */
  	public String checkSession() {
  			HttpServletRequest r = getThreadLocalRequest();
  			boolean check = true;
  	        HttpSession s = r.getSession(false);
  	        if (s == null) {
  	            return null;
  	        }
  	        if (s.getAttribute("username") == null) {
  	            check = false;
  	        } else if ((s.getAttribute("ip") == null) || !((String) s.getAttribute("ip")).equals(r.getRemoteHost())) {
  	            check = false;
  	        } else {
  	            Calendar begin = (Calendar) s.getAttribute("inizio-sessione");
  	            Calendar last = (Calendar) s.getAttribute("ultima-azione");
  	            Calendar now = Calendar.getInstance();
  	            if (begin == null) {
  	                check = false;
  	            } else {
  	                long secondsfrombegin = (now.getTimeInMillis() - begin.getTimeInMillis()) / 1000;
  	                if (secondsfrombegin > 3 * 60 * 60) {
  	                    check = false;
  	                } else if (last != null) {
  	                    long secondsfromlast = (now.getTimeInMillis() - last.getTimeInMillis()) / 1000;
  	                    if (secondsfromlast > 30 * 60) {
  	                        check = false;
  	                    }
  	                }
  	            }
  	        }
  	        if (!check) {
  	            s.invalidate();
  	            return null;
  	        } else {
  	            s.setAttribute("ultima-azione", Calendar.getInstance());
  	            return (String)s.getAttribute("username");
  	        }		
  	}
	@Override
	/**
	 * Se esiste una sessione attiva, la distrugge.
	 */
	public Boolean resetSession() {
			HttpServletRequest r = getThreadLocalRequest();
	        HttpSession s = r.getSession(false);
	        if (s != null) {
	            s.invalidate();
	        }
	        return true;
	}
  }