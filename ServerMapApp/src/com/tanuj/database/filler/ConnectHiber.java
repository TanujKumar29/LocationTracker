package com.tanuj.database.filler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.tanuj.database.dao.MyMapDb;

public class ConnectHiber {
	
	

public void estConnSaveData(MyMapDb values)
{
	 
	    
      Session session=HibernateSessionFactory.getSessionFactory().openSession();
      session.beginTransaction();
      session.save(values);
      session.getTransaction().commit();
     // session.close();	
}
}
