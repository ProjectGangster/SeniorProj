package com.example.shona;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NavToProductActivity extends Activity {
	//intent
	private Intent intentToProductDetails;
	//code
	protected int details = 994;
	//product id
	private int id = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nav_to_product);
		
		//get product id
		id = getIntent().getIntExtra("proID", 0);
		
		//intent creation
		intentToProductDetails = new Intent(NavToProductActivity.this,ProductDetailsActivity.class);
		//ask if user wants to go to the cashier
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	dialog.setTitle("Nav pro");
    	//activate button OnClickListener
    	dialog.setNegativeButton(R.string.okButton, new DialogInterface.OnClickListener() {
			//activate button OnClickListener creation
			public void onClick(DialogInterface arg0, int arg1) {
				intentToProductDetails.putExtra("proID", getId());
				startActivityForResult(intentToProductDetails, details);
				finish();
			}
		});//end activate button
    	dialog.setPositiveButton(R.string.cancelButton, null);
    	dialog.show();
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nav_to_product, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.back_settings:
			//go to checkout
			setResult(RESULT_OK);
			finish();
			break;
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
