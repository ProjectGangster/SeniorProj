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
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;

public class ProductListActivity extends Activity {
	/*
	 * layout components
	 */
	private ExpandableListView listView;
	
	//intent
	private Intent intentFromLV3;
	private Intent intentToNavPro;	
	//code
	protected int navPro = 995;
	
	//product types
	private int type = 1;
	
	private ProductList_HandleJSON obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list);
		//layout
		listView = (ExpandableListView)findViewById(R.id.expandableListView1);
		
		//get product type
		intentFromLV3 = getIntent();
		type = intentFromLV3.getIntExtra("proType", 0);
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
				startActivityForResult(intentToNavPro, navPro);
			}
		});//end activate button
    	dialog.setPositiveButton(R.string.cancelButton, null);
    	dialog.show();
    	
    	
    	int category=0;
    	obj = new ProductList_HandleJSON(category);
	    obj.fetchJSON();

	    while(obj.parsingComplete);
	     //listView.add
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
