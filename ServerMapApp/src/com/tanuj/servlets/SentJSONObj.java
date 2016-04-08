package com.tanuj.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.tanuj.database.dao.MyMapDb;
import com.tanuj.database.filler.ConnectRetreive;

public class SentJSONObj extends HttpServlet{
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
			System.out.println("do get called");
		}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	System.out.println("do post called");
	String coordinates;
	coordinates=getPostData(req);
	System.out.println(coordinates);
	  resp.setContentType("text/html");
	  
		  OutputStream dataOutput = resp.getOutputStream();
	        dataOutput.write(coordinates.getBytes());
	        dataOutput.flush();
	        dataOutput.close();
	}
	public String getPostData(HttpServletRequest req) {
		ConnectRetreive con=new ConnectRetreive();
		MyMapDb mapRet=new MyMapDb();
	    String sb = null;
	    
	    try {
	        BufferedReader reader = req.getReader();
	        reader.mark(10000);
      
	        mapRet=con.retrieve();
	        System.out.println("mapRet="+mapRet);
        	sb=mapRet.getLatitude()+","+mapRet.getLongitude();
        	//sb.append(mapRet.getLongitude()).append("\n");
	        reader.reset();
	        // do NOT close the reader here, or you won't be able to get the post data twice
	    } catch(IOException e) {
	        
			System.out.println("getPostData couldn't.. get the post data"+e);  // This has happened if the request's reader is closed    
	    }
	    System.out.println("string buffer="+sb);
	    return sb;
	}
	
	MyMapDb values=new MyMapDb();
	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;
	
	public MyMapDb retrieve() {
		
		values=null;
		Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
      Session session=sessionFactory.openSession();
      session.beginTransaction();
      values=(MyMapDb) session.get(MyMapDb.class, 1);
      return values;
		 }
}
