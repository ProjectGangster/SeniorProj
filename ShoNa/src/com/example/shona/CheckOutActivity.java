package com.example.shona;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CheckOutActivity extends Activity {
	/*
	 * Intent
	 */
	//intent
	private Intent intentToCashier;
	//code for communication between activity
	protected int cash = 100;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_out);
		//show all products
		
		
    	
		//intent
		intentToCashier = new Intent(CheckOutActivity.this,NavToCashierActivity.class);
		
		//ask if user wants to go to the cashier
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	dialog.setTitle("Are you sure to go to the cashier?");
    	//activate button OnClickListener
    	dialog.setNegativeButton(R.string.okButton, new DialogInterface.OnClickListener() {
			//activate button OnClickListener creation
			public void onClick(DialogInterface arg0, int arg1) {
				startActivityForResult(intentToCashier, cash);
			}
		});//end activate button
    	dialog.setPositiveButton(R.string.cancelButton, null);
    	dialog.show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_out, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}