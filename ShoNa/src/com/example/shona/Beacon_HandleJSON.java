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



public class Beacon_HandleJSON {

  //PList.add(new Player(1));
   Beacon B;
   Location L;
   Double[] LatLong;
   private String urlString = null;
   
   public Beacon getBeacon(){
	   return this.B;
   }
   
   public Location getL() {
		return L;
	}

	public Double[] getLatLong() {
		return LatLong;
	} 
   
   public volatile boolean parsingComplete = true;
   
   public Beacon_HandleJSON(String Url){
	   this.urlString = Url;
	  //this.urlString = "http://www.numpun.lnw.mn/shona/API/productdetail.php?ID="+CatNumber;
   }


   public static Beacon getBeaconDetail(String Minor) {
	   String url = "http://www.numpun.lnw.mn/shona/API/beacon.php?Minor="+Minor;
	   Beacon_HandleJSON obj = new Beacon_HandleJSON(url);
	   obj.fetchBeaconDetail();

	   while(obj.parsingComplete);
	   
	   return obj.getBeacon();
	}
   public static Location getBeaconLocation(String Minor) {
	   String url = "http://www.numpun.lnw.mn/shona/API/beacon_location.php?Minor="+Minor;
	   Beacon_HandleJSON obj = new Beacon_HandleJSON(url);
	   obj.fetchBeaconLocation();

	   while(obj.parsingComplete);
	   
	   return obj.getL();
	}
   
   public static Double[] getBeacon3 (int[] ID) {
	   String url = "http://www.numpun.lnw.mn/shona/API/beacon_3.php?ID1="+ID[0]+"&ID2="+ID[1]+"&ID3="+ID[2];
	   Beacon_HandleJSON obj = new Beacon_HandleJSON(url);
	   obj.fetchBeacon3();

	   while(obj.parsingComplete);
	   	   return obj.getLatLong();
	}
  
   @SuppressLint("NewApi")
   
   public void fetchBeaconDetail(){
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
   
   public void fetchBeaconLocation(){
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

	      readAndParseJSON_Location(data);
	         stream.close();

	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         }
	      });

	       thread.start(); 		
	   }
   
   public void fetchBeacon3(){
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

	      readAndParseJSON_Beacon3(data);
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
		    	 
		         String UUID,name,macAddress;
		         int major,minor,measuredPower,rssi;         
		         	 UUID = reader.getString("UUID");
		         	 name = reader.getString("Name");
		        	 macAddress = reader.getString("MacAddress");
		        	 major = reader.getInt("Major");
		        	 minor = reader.getInt("Minor");
		        	 measuredPower = reader.getInt("Power");
		        	 rssi = reader.getInt("RSSI");	
		        	 Log.d("Beacon1", UUID+"");
		        	 //public Product(int id,String n,String b,double v, double p, String des,int s,int x,int y){
		        	 Beacon b = new Beacon(UUID, name, macAddress, major, minor, measuredPower, rssi);
		        	 this.B = b; 
		         
		        	 parsingComplete = false;


		        } catch (Exception e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		        }

		   }
	   

	   public void readAndParseJSON_Location(String in) {
		      try { 
		    	  Log.d("test", in);
		    	  JSONObject reader = new JSONObject(in);
		    	 
		         Double lat,lon;         
		         	 lat = reader.getDouble("Latitiude");
		        	 lon = reader.getDouble("Longitude");	
		        	 Log.d("Beacon2", lat+":"+lon);
		        	 //public Product(int id,String n,String b,double v, double p, String des,int s,int x,int y){
		        	 Location l = new Location("l");
		        	 l.setLatitude(lat);
		        	 l.setLongitude(lon);
		        	 this.L = l;
		         
		        	 parsingComplete = false;


		        } catch (Exception e) {
		           // TODO Auto-generated catch block
		           e.printStackTrace();
		        }

		   }
	   



	public void readAndParseJSON_Beacon3(String in) {
		      try { 
		    	  Log.d("test", in);
		    	  JSONObject reader = new JSONObject(in);
		         
		         JSONArray jArray = reader.getJSONArray("Beacon_list");
		         int order;
		         LatLong = new Double[6];
		         for(int i =0;i<jArray.length();i++){
		        	 //id = jArray.getJSONObject(i).getString("UUID");
		        	 order = jArray.getJSONObject(i).getInt("Order");
		        	 LatLong[(order-1)*2] = jArray.getJSONObject(i).getDouble("Latitude");
		        	 LatLong[(order-1)*2+1] = jArray.getJSONObject(i).getDouble("Longitude");
		        	 Log.d("Beacon3."+i, order+"");
		        	 Log.d("Beacon3."+i, LatLong[(order-1)*2]+":"+LatLong[(order-1)*2+1]);
		        	 
		         }
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
