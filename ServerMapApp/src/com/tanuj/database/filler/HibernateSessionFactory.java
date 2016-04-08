package com.tanuj.database.filler;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateSessionFactory {

	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	//to disallow creating objects by other classes.
	 
	    private HibernateSessionFactory() {
	    }
	//maling the Hibernate SessionFactory object as singleton
	 
	    public static synchronized SessionFactory getSessionFactory() {
	 
	        if (sessionFactory == null) {
	        	Configuration configuration = new Configuration();
	    	    configuration.configure();
	    	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	    	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	          
	        }
	        return sessionFactory;
	    }

  }