package com.example.shona;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
public class ListAdapter extends ArrayAdapter{

		private final Activity context;
	    protected ArrayList<Product> product;
	    
	    public ListAdapter (Activity context, ArrayList<Product> product){
	        super(context, R.layout.productlist,product);
	        this.context = context;
	        this.product = product;
	    }

	    public void addProduct(Product product){
	        try {
	            Log.d("ProductList - addProduct", product.getName()+"");	
	            this.product.add(product);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public View getView(int position, View view, ViewGroup parent) {
	        LayoutInflater inflater = context.getLayoutInflater();
	        View rowView= inflater.inflate(R.layout.productlist, null, true);
	        TextView textView_first = (TextView) rowView.findViewById(R.id.firstLine);
	        TextView textView_second = (TextView) rowView.findViewById(R.id.secondLine);
	       
	        textView_first.setText(product.get(position).getName());
	        Log.d("Product "+position, product.get(position).getName());
	        textView_second.setText(product.get(position).getPrice()+" Bath");
	        Log.d("Product "+position, product.get(position).getPrice()+" บาท");
	        return rowView;
	    
	}
}
