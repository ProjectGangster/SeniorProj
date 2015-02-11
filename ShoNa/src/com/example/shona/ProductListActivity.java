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
	private int type = 0;
	
	private ProductList_HandleJSON obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list);
		
		//listview = 
		listview = (ListView) findViewById(R.id.ListView1);
		//get product type
		intentFromLV3 = getIntent();
		//type = intentFromLV3.getIntExtra("proType", 0);
		type = intentFromLV3.getIntExtra("catid", 0);
		Log.i("PROTYPEEEEE", ""+type);
		
		//intent creation
		intentToNavPro = new Intent(ProductListActivity.this,NavToProductActivity.class);
		//ask if user wants to go to the cashier
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	dialog.setTitle("Product List");
    	//activate button OnClickListener
    	dialog.setNegativeButton(R.string.okButton, new DialogInterface.OnClickListener() {
			//activate button OnClickListener creation
			public void onClick(DialogInterface arg0, int arg1) {
				intentToNavPro.putExtra("proID", getType());
				startActivityForResult(intentToNavPro, navPro);
			}
		});//end activate button
    	dialog.setPositiveButton(R.string.cancelButton, null);
    	dialog.show();
    	
<<<<<<< HEAD
    	ArrayList<Product> product = ProductList_HandleJSON.getPList(type);
    	ArrayList<Product> list = new ArrayList<Product>();    	
    	ListAdapter Adapter = new ListAdapter(this, list);
    	listview.setAdapter(Adapter);  
    	    	
    	for (int i = 0; i < product.size(); ++i) {
          //list.add(product.get(i));
=======
    	
    	//int category=0;

//    	obj = new ProductList_HandleJSON(type);
//	    obj.fetchJSON();
//
//	    while(obj.parsingComplete);
    	/*ArrayList<Product> product = ProductList_HandleJSON.getPList(type);
    	ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < product.size(); ++i) {
          list.add(product.get(i).getName());
>>>>>>> 07d14fc04345d92f67f6b6ec9dd830a2fd0b6c0d
          //.add("test"+i);
    		Adapter.addProduct(product.get(i));
        }
<<<<<<< HEAD
    	Adapter.notifyDataSetChanged();

    	listview.setSelection(Adapter.getCount() - 1);
	    //listView.add
    	
=======
    	ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
    	listview.setAdapter(Adapter);
	    */
>>>>>>> 07d14fc04345d92f67f6b6ec9dd830a2fd0b6c0d

	}

	public int getType() {
		return type;
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
