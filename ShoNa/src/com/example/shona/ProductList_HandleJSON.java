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
   //PList.add(new Player(1));
   
   
   private String urlString = null;
   
   
   public volatile boolean parsingComplete = true;
   
   public ProductList_HandleJSON(int CatNumber){
	  this.urlString = "http://www.numpun.lnw.mn/shona/API/productlist.php?ID="+"CatNumber"; 
   }
   
   public ArrayList<Product> getPList() {
		return PList;
	}
  
   @SuppressLint("NewApi")
   public void readAndParseJSON(String in) {
      try { 
    	  Log.d("test", in);
    	  JSONObject reader = new JSONObject(in);
         
         JSONArray jArray = reader.getJSONArray("Product_list");
         
         for(int i =0;i<jArray.length();i++){
        	 Product a = new Product();
        	 PList.add(a);
         }
         //country = jArray.getJSONObject(0).getString("Name");
         //reader[0].get("Name");
         //JSONObject sys  = reader.getJSONObject("sys");
         //country = sys.getString("country");

//         JSONObject main  = reader.getJSONObject("main");
//         temperature = main.getString("temp");
//
//         pressure = main.getString("pressure");
//         humidity = main.getString("humidity");

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
   static String convertStreamToString(java.io.InputStream is) {
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
   }
}
final class Product{
	private int i;
}