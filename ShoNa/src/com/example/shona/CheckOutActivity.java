package com.example.shona;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CheckOutActivity extends Activity {
	//button
	private Button proceed;
	//intent
	private Intent intentNav;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_out);
		//buttons
		proceed = (Button)findViewById(R.id.button1);
		proceed.setContentDescription("proceed");
		//show all products
		MainActivity.payList.getShowList();
		
		//intent creation
		intentNav = new Intent(CheckOutActivity.this, NavActivity.class);
				
		//setOnCLick
		proceed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//ask if user wants to go to the cashier
				AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
		    	dialog.setTitle("Are you sure to go to the cashier?");
		    	//activate button OnClickListener
		    	dialog.setNegativeButton(R.string.okButton, new DialogInterface.OnClickListener() {
					//activate button OnClickListener creation
					public void onClick(DialogInterface arg0, int arg1) {
						intentNav.putExtra("nType", 2);
						startActivity(intentNav);
					}
				});//end activate button
		    	dialog.setPositiveButton(R.string.cancelButton, null);
		    	dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_out, menu);
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
