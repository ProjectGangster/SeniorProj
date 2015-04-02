 package com.example.shona;

import com.estimote.sdk.Beacon;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.location.Location;
import android.util.Log;



public class Route_HandleJSON {

   //PList.add(new Player(1));
   Location l[];
   private String urlString = null;
   private String route;
   private int beacon_ID;




   public volatile boolean parsingComplete = true;
	
   public String getRoute() {
	   return route;
   }  

   public int getBeacon_ID() {
	   return beacon_ID;
   }  
   public Route_HandleJSON(String Url){
	   this.urlString = Url;
	   
	  //this.urlString = "http://www.numpun.lnw.mn/shona/API/route.php?ID="+CatNumber;
   }


   public static String getRoute(int loc,int des) {
	   String url = "http://www.numpun.lnw.mn/shona/API/beacon.php?loc="+loc+"&des="+des;
	   Route_HandleJSON obj = new Route_HandleJSON(url);
	   obj.fetchRoute();

	   while(obj.parsingComplete);
	   
	   return obj.getRoute();
	}
 

   public static int getPoint(int p) {
	   String url = "http://www.numpun.lnw.mn/shona/API/point.php?id="+p;
	   Route_HandleJSON obj = new Route_HandleJSON(url);
	   obj.fetchPoint();

	   while(obj.parsingComplete);
	   
	   return obj.getBeacon_ID();
	}
   @SuppressLint("NewApi")
   
   public void fetchRoute(){
      Thread thread = new Thread(new Runnable(){
         @Override
         public void run() {
         try {
        	Log.d("URL", urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
         InputStream stream = conn.getInputStream();

      String data = convertStreamToString(stream);

      readAndParseJSON(data);
         stream.close();

         } catch (Exception e) {
            e.printStackTrace();
         }
         }
      });

       thread.start(); 		
   }
   
   public void fetchPoint(){
	      Thread thread = new Thread(new Runnable(){
	         @Override
	         public void run() {
	         try {
	        	Log.d("URL", urlString);
	            URL url = new URL(urlString);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setReadTimeout(10000 /* milliseconds */);
	            conn.setConnectTimeout(15000 /* milliseconds */);
	            conn.setRequestMethod("GET");
	            conn.setDoInput(true);
	            // Starts the query
	            conn.connect();
	         InputStream stream = conn.getInputStream();

	      String data = convertStreamToString(stream);

	      readAndParseJSON_p(data);
	         stream.close();

	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         }
	      });

	       thread.start(); 		
	   }
	   
   
	   public void readAndParseJSON(String in) {
		      try { 
		    	  Log.d("test", in);
		    	  JSONObject reader = new JSONObject(in);
		    	 
		         String Route;
		         int Loca,Desc;
		         Loca = reader.getInt("Location");
		         Desc = reader.getInt("Destination");
		         Route = reader.getString("Route");
		         Log.d("Route", Loca+" to "+Desc+": "+Route);
		        	 //public Product(int id,String n,String b,double v, double p, String des,int s,int x,int y){
	        	 this.route = Route;
		         parsingComplete = false;


		        } catch (Exception e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		        }

		   }
	   
	   public void readAndParseJSON_p(String in) {
		      try { 
		    	  Log.d("test", in);
		    	  JSONObject reader = new JSONObject(in);
		    	 
		         String Route;
		         int B_ID;
		         B_ID = Integer.parseInt(reader.getString("Beacon_ID"));
		         Log.d("Beacon ID: ", "Beacon ID: " + B_ID);
		        	 //public Product(int id,String n,String b,double v, double p, String des,int s,int x,int y){
	        	 this.beacon_ID = B_ID;
		         parsingComplete = false;


		        } catch (Exception e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		        }

		   }
	   
		   
   static String convertStreamToString(java.io.InputStream is) {
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
   }
}
