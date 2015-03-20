package com.example.shona;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductListActivity extends Activity {
	//intent
	private ListView listview;
	private Intent intentFromLV3;
	private Intent intentToNavPro;	
	//code
	protected int navPro = 995;
	
	//product types
	private int productType = 0;
	private ProductList_HandleJSON obj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list);
		
		//listview = 
		listview = (ListView) findViewById(R.id.ListView1);
		//get product productType
		intentFromLV3 = getIntent();
		//productType = intentFromLV3.getIntExtra("proType", 0);
		productType = intentFromLV3.getIntExtra("catid", 0);
		Log.i("PROTYPEEEEE", ""+productType);
		
		//intent creation
		intentToNavPro = new Intent(ProductListActivity.this,NaviActivity.class);

    	ArrayList<Product> product = ProductList_HandleJSON.getPList(productType+"");
    	ArrayList<Product> list = new ArrayList<Product>();    	
    	ListAdapter Adapter = new ListAdapter(this, list);
    	listview.setAdapter(Adapter);  
    	    	
    	listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
    		@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
    			Product p = (Product) parent.getAdapter().getItem(position);
    			Log.d("Product Click", p.getName()+"");
    			intentToNavPro.putExtra("Pid", p.getId());
    			intentToNavPro.putExtra("Shelf", p.getShelf());
    			intentToNavPro.putExtra("X", p.getX());
    			intentToNavPro.putExtra("Y", p.getY());
				
    			intentToNavPro.putExtra("proType", getType());
				intentToNavPro.putExtra("navType", 1);
				startActivityForResult(intentToNavPro, navPro);			
			}
    	});
    	 

        for (int i = 0; i < product.size(); ++i) {
          //list.add(product.get(i).getName());
          //.add("test"+i);
    		Adapter.addProduct(product.get(i));
        }
    	Adapter.notifyDataSetChanged();

    	//listview.setSelection(Adapter.getCount() - 1);
	    //listView.add
    	
    	//ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
    	//listview.setAdapter(Adapter);
	    
    	

    	
	}

	public int getType() {
		return productType;
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.home_settings:
			//go to home
			MainActivity.toHome = true;
			setResult(RESULT_OK);
			finish();
			break;
		default:
			break;
		}
		return false;
	}
}
