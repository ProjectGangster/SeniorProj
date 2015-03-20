package com.example.shona;

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


public class ProductList_HandleJSON {

   ArrayList<Product> PList = new ArrayList<Product>();
   Product P;
   Location l;
   //PList.add(new Player(1));
   
   
   private String urlString = null;
   
   
   public volatile boolean parsingComplete = true;
   
   public ProductList_HandleJSON(String CatNumber,int x){
	  if(x==0)
		  this.urlString = "http://www.numpun.lnw.mn/shona/API/productlist.php?ID="+CatNumber;
	  else if(x==1)
		  this.urlString = "http://www.numpun.lnw.mn/shona/API/productdetail.php?ID="+CatNumber;
	  else if(x==2)
		  this.urlString = "http://www.numpun.lnw.mn/shona/API/productlocation.php?ID="+CatNumber;
   }
   public Product getProduct() {
	   return P;
   }
   public ArrayList<Product> getPList() {
	   return PList;
   }
   public Location getL() {
	   return l;
   }
   public static ArrayList<Product> getPList(String CatNumber) {
	   //String url = "http://www.numpun.lnw.mn/shona/API/productlist.php?ID="+"CatNumber";
	   ProductList_HandleJSON obj = new ProductList_HandleJSON(CatNumber,0);
	   obj.fetchJSON();

	   while(obj.parsingComplete);
	   
	   return obj.getPList();
	}
   
   public static Product getPDetail(String PID) {
	   //String url = "http://www.numpun.lnw.mn/shona/API/productlist.php?ID="+"CatNumber";
	   ProductList_HandleJSON obj = new ProductList_HandleJSON(PID,1);
	   obj.fetchJSON1();

	   while(obj.parsingComplete);
	   
	   return obj.getProduct();
	}
  
   public static Location getTypeLocation(int PID) {
	   //String url = "http://www.numpun.lnw.mn/shona/API/productlocation.php?ID="+"CatNumber";
	   ProductList_HandleJSON obj = new ProductList_HandleJSON(PID+"",2);
	   obj.fetchJSON2();

	   while(obj.parsingComplete);
	   
	   return obj.getL();
	}
   
   @SuppressLint("NewApi")
   public void readAndParseJSON(String in) {
      try { 
    	  Log.d("test", in);
    	  JSONObject reader = new JSONObject(in);
         
         JSONArray jArray = reader.getJSONArray("Product_list");
         
         int shelf;
         double x,y;	       
         double price,vol;
         String id,name,desc,brand;         
         for(int i =0;i<jArray.length();i++){
        	 id = jArray.getJSONObject(i).getString("id");
        	 name = jArray.getJSONObject(i).getString("Name");
        	 price = jArray.getJSONObject(i).getDouble("Price");
        	 desc = jArray.getJSONObject(i).getString("Detail");
        	 brand = jArray.getJSONObject(i).getString("Brand");
        	 vol = jArray.getJSONObject(i).getDouble("Volume");	
        	 shelf = jArray.getJSONObject(i).getInt("Shelf");
        	 x = jArray.getJSONObject(i).getDouble("X");
        	 y = jArray.getJSONObject(i).getDouble("Y");
        	 
        	 Product a = new Product(id,name,brand,vol,price,desc,shelf,x,y);
	         PList.add(a);

         }
         parsingComplete = false;


        } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }

   }
   
   public void readAndParseJSON1(String in) {
	      try { 
	    	  Log.d("test", in);
	    	  JSONObject reader = new JSONObject(in);
	    	 
	         int shelf;
	         double x,y;
	         double price,vol;
	         String id,name,desc,brand;         
	         	 id = reader.getString("id");
	        	 name = reader.getString("Name");
	        	 price = reader.getDouble("Price");
	        	 desc = reader.getString("Detail");
	        	 brand = reader.getString("Brand");
	        	 vol = reader.getDouble("Volume");	
	        	 shelf = reader.getInt("Shelf");
	        	 x = reader.getDouble("X");
	        	 y = reader.getDouble("Y");
	        	 //public Product(int id,String n,String b,double v, double p, String des,int s,int x,int y){
	        	 Product a = new Product(id,name,brand,vol,price,desc,shelf,x,y);
	        	 this.P = a; 
	         
	        	 parsingComplete = false;


	        } catch (Exception e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	        }

	   }
   
   public void readAndParseJSON2(String in) {
	      try { 
	    	  Log.d("test", in);
	    	  JSONObject reader = new JSONObject(in);
	    	 
	         int shelf;
	         double x,y;
	         double price,vol;
	         String id,name,desc,brand;         
	         	 id = reader.getString("id");
	        	 x = reader.getDouble("Latitude");
	        	 y = reader.getDouble("Longitude");
	        	 //public Product(int id,String n,String b,double v, double p, String des,int s,int x,int y){
	        	 Location L = new Location("product Type");
	        	 L.setLatitude(x);
	        	 L.setLongitude(y);
	        	 this.l = L; 
	         
	        	 parsingComplete = false;


	        } catch (Exception e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	        }

	   }
   
   public void fetchJSON(){
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
   
   public void fetchJSON1(){
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

	      readAndParseJSON1(data);
	         stream.close();

	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         }
	      });

	       thread.start(); 		
	   }
   
   public void fetchJSON2(){
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

	      readAndParseJSON2(data);
	         stream.close();

	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	         }
	      });

	       thread.start(); 		
	   }

   
   static String convertStreamToString(java.io.InputStream is) {
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
   }
}
