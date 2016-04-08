package com.tanuj.database.filler;



import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.tanuj.database.dao.MyMapDb;

public class ConnectRetreive {
	MyMapDb values=new MyMapDb();
	
	public MyMapDb retrieve() {
		
	  Session session=HibernateSessionFactory.getSessionFactory().openSession();
      session.beginTransaction();
      DetachedCriteria maxQuery = DetachedCriteria.forClass( MyMapDb.class );
      maxQuery.setProjection( Projections.max( "locationID" ) );

      Criteria query = session.createCriteria(MyMapDb.class);
      query.add( Property.forName( "locationID" ).eq( maxQuery ) );
      List result=query.list();
     // Criteria criteria = session.createCriteria(MyMapDb.class).setProjection(Projections.max("locationID"));
      Iterator i=result.iterator();
      while(i.hasNext())
      {
    	  
      values=(MyMapDb)i.next();
      System.out.println(values.getLatitude());
      System.out.println(values.getLongitude());
      }
     // values=(MyMapDb) session.get(MyMapDb.class, 1);
      session.getTransaction().commit();
      session.close();	
      System.out.println("hiiiiiiii returning valll   "+values);
      return values;
		 }
}
