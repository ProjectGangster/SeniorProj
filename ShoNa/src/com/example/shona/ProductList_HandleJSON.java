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
import android.util.Log;


public class ProductList_HandleJSON {

   ArrayList<Product> PList = new ArrayList<Product>();
   Product P;
   //PList.add(new Player(1));
   
   
   private String urlString = null;
   
   
   public volatile boolean parsingComplete = true;
   
   public ProductList_HandleJSON(int CatNumber,int x){
	  if(x==0)
		  this.urlString = "http://www.numpun.lnw.mn/shona/API/productlist.php?ID="+CatNumber;
	  else if(x==1)
		  this.urlString = "http://www.numpun.lnw.mn/shona/API/productdatail.php?ID="+CatNumber;
   }
   public Product getProduct() {
	   return P;
   }
   public ArrayList<Product> getPList() {
	   return PList;
   }
   public static ArrayList<Product> getPList(int CatNumber) {
	   //String url = "http://www.numpun.lnw.mn/shona/API/productlist.php?ID="+"CatNumber";
	   ProductList_HandleJSON obj = new ProductList_HandleJSON(CatNumber,0);
	   obj.fetchJSON();

	   while(obj.parsingComplete);
	   
	   return obj.getPList();
	}
   
   public static Product getPDetail(int PID) {
	   //String url = "http://www.numpun.lnw.mn/shona/API/productlist.php?ID="+"CatNumber";
	   ProductList_HandleJSON obj = new ProductList_HandleJSON(PID,1);
	   obj.fetchJSON1();

	   while(obj.parsingComplete);
	   
	   return obj.getProduct();
	}
  
   @SuppressLint("NewApi")
   public void readAndParseJSON(String in) {
      try { 
    	  Log.d("test", in);
    	  JSONObject reader = new JSONObject(in);
         
         JSONArray jArray = reader.getJSONArray("Product_list");
         
         int id,shelf,x,y;	       
         double price,vol;
         String name,desc,brand;         
         for(int i =0;i<jArray.length();i++){
        	 id = jArray.getJSONObject(i).getInt("id");
        	 name = jArray.getJSONObject(i).getString("Name");
        	 price = jArray.getJSONObject(i).getDouble("Price");
        	 desc = jArray.getJSONObject(i).getString("Detail");
        	 brand = jArray.getJSONObject(i).getString("Brand");
        	 vol = jArray.getJSONObject(i).getDouble("Volume");	
        	 shelf = jArray.getJSONObject(i).getInt("Shelf");
        	 x = jArray.getJSONObject(i).getInt("X");
        	 y = jArray.getJSONObject(i).getInt("Y");
        	 
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
	    	 
	         int id,shelf,x,y;
	         double price,vol;
	         String name,desc,brand;         
	         	 id = reader.getInt("id");
	        	 name = reader.getString("Name");
	        	 price = reader.getDouble("Price");
	        	 desc = reader.getString("Detail");
	        	 brand = reader.getString("Brand");
	        	 vol = reader.getDouble("Volume");	
	        	 shelf = reader.getInt("Shelf");
	        	 x = reader.getInt("X");
	        	 y = reader.getInt("Y");
	        	 //public Product(int id,String n,String b,double v, double p, String des,int s,int x,int y){
	        	 Product a = new Product(id,name,brand,vol,price,desc,shelf,x,y);
	        	 this.P = a; 
	         
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
   
   static String convertStreamToString(java.io.InputStream is) {
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
   }
}
