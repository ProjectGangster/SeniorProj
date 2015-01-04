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

public class LV2BevActivity extends Activity {
	/*
	 * view components
	 */
	//textview
	private TextView LV2des;
	//buttons
	private Button LV2CateBut1;
	private Button LV2CateBut2;
	
	//OnCLickListener
	private OnClickListener lv2CLS;
	
	/*
	 * Intent
	 */
	//intent
	private Intent intentToAl;
	private Intent intentToNoAl;
	//code for communication between activity
	protected int al = 31;
	protected int nal = 32;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv2_bev);
		//textview
		LV2des = (TextView) findViewById(R.id.textView1);
		LV2des.setContentDescription("Please choose a product category");
		//buttons
		LV2CateBut1 = (Button)findViewById(R.id.button1);
		LV2CateBut1.setContentDescription("Alcoholic");
		LV2CateBut2 = (Button)findViewById(R.id.button2);
		LV2CateBut2.setContentDescription("Non-Alcoholic");
		
		//intent creation
		intentToAl = new Intent(LV2BevActivity.this,BevLV3ALActivity.class);
		intentToNoAl = new Intent(LV2BevActivity.this,BevLV3NALActivity.class);
		
		//OnClickListener creation
		lv2CLS = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==LV2CateBut1){//Al
					Log.i("ButtonL2", "1");
					//openLV3
					startActivityForResult(intentToAl, al);
					finish();
				}
				else if(v==LV2CateBut2){//Non-Al
					Log.i("ButtonL2", "2");
					//openLV3
					startActivityForResult(intentToNoAl, nal);
					finish();
				}
			}//end OnClick
		};//end creation
		
		//set OnClickListener
		LV2CateBut1.setOnClickListener(lv2CLS);
		LV2CateBut2.setOnClickListener(lv2CLS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lv2_bev, menu);
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
