package it.hourglass.myTalk.server.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
/**
 * Classe di inizializzazione delle sessioni di Hibernate.
 * 
 * <p>Il suo scopo è quello di fornire una sessione aggiornata e utile all'utilizzo
 * delle funzionalità offerte dal framework Hibernate. Così facendo, si garantisce una
 * corretta concorrenza e salvaguardia da eventuali conflitti delle transazioni
 * effettuate. </p>
 * @author Hyde
 *
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	  static {
		  Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	  }

	  public static SessionFactory getSessionFactory() {
	    return sessionFactory;
	  }
	}