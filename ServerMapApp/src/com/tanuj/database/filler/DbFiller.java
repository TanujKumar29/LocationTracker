package com.tanuj.database.filler;

import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import com.tanuj.database.dao.MyMapDb;

public class DbFiller {
	MyMapDb mapDb=new MyMapDb();
public MyMapDb setJsonString(String jsonString)
{
	
	try {
		JSONObject jsonObj = new JSONObject(jsonString);
		//SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		
		System.out.println((jsonObj.get("latitude")).toString()+" ,"+(jsonObj.get("longitude")).toString());
		//+" ,"+(jsonObj.get("datetime")).toString());
		mapDb.setLatitude((jsonObj.get("latitude")).toString());
		mapDb.setLongitude((jsonObj.get("longitude")).toString());
	//	mapDb.setDateTime((jsonObj.get("datetime")).toString());
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return mapDb;
}
}
