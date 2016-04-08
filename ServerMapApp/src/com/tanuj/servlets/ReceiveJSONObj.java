package com.tanuj.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.tanuj.database.dao.MyMapDb;
import com.tanuj.database.filler.ConnectHiber;
import com.tanuj.database.filler.DbFiller;



public class ReceiveJSONObj extends HttpServlet{
	DbFiller dbFill=new DbFiller();
	ConnectHiber conn=new ConnectHiber();
	MyMapDb mapDb=new MyMapDb();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("servlet called");
		getPostData(req);
	    resp.setContentType("text/html");
		 String str = "Successfully stored data";
	        ObjectOutputStream dataOutput = new ObjectOutputStream(resp.getOutputStream());
	        dataOutput.writeUTF(str);
	        dataOutput.flush();
	        dataOutput.close();
	}
	public String getPostData(HttpServletRequest req) {
	    StringBuilder sb = new StringBuilder();
	    try {
	        BufferedReader reader = req.getReader();
	        reader.mark(10000);

	        String line="";
	        while ((line = reader.readLine()) != null) {
	        	mapDb=dbFill.setJsonString(line);
	        	
	        	conn.estConnSaveData(mapDb);
	          //  System.out.println(line);
	            sb.append(line).append("\n");
	        } 
	        reader.reset();
	        // do NOT close the reader here, or you won't be able to get the post data twice
	    } catch(IOException e) {
	        
			System.out.println("getPostData couldn't.. get the post data"+e);  // This has happened if the request's reader is closed    
	    }

	    return sb.toString();
	}

}
