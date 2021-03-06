package com.example.shona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LV2CCActivity extends Activity {
	/*
	 * view components
	 */
	//textview
	private TextView LV2des;
	//buttons
	private Button LV2CateBut1;
	private Button LV2CateBut2;
	
	//OnCLickListener
	private OnClickListener LV2CLS;
	
	/*
	 * Intent
	 */
	//intent
	private Intent intentToCac;
	private Intent intentToAif;
	
	//code for communication between activity
	protected int cac = 55;
	protected int aif = 56;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv2_cc);
		//textview
		LV2des = (TextView) findViewById(R.id.textView1);
		LV2des.setContentDescription("Please choose a product category");
		//buttons
		LV2CateBut1 = (Button)findViewById(R.id.button1);
		LV2CateBut1.setContentDescription("Car Care");
		LV2CateBut2 = (Button)findViewById(R.id.button2);
		LV2CateBut2.setContentDescription("Air Freshener");
		
		//Intent creation
		intentToCac = new Intent(LV2CCActivity.this,CcLV3carActivity.class);
		intentToAif = new Intent(LV2CCActivity.this,CcLV3aifActivity.class);
		
		//OnClickListener creation
		LV2CLS = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==LV2CateBut1){//cac
					Log.i("ButtonL2", "1");
					//openLV3
					startActivityForResult(intentToCac, cac);
				}
				else if(v==LV2CateBut2){//aif
					Log.i("ButtonL2", "2");
					//openLV3
					startActivityForResult(intentToAif, aif);
				}
			}
		};//end creation
		
		//set OnClickListener
		LV2CateBut1.setOnClickListener(LV2CLS);
		LV2CateBut2.setOnClickListener(LV2CLS);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intentFromAnother){
	    super.onActivityResult(requestCode, resultCode, intentFromAnother);
	    if(MainActivity.toHome){
			finish();
		}
	    else{
	    	//do sth
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lv2_cc, menu);
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
		default:
			break;
		}
		return false;
	}
}
