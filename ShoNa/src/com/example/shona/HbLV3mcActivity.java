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

public class HbLV3mcActivity extends Activity {
	/*
	 * view components
	 */
	//textview
	private TextView LV3des;
	//buttons
	private Button LV3CateBut1;
	private Button LV3CateBut2;
	private Button LV3CateBut3;
	private Button LV3CateBut4;
	private Button LV3CateBut5;
	private Button LV3CateBut6;
	private Button LV3CateBut7;
	
	//intend
	private Intent intentToPList;
				
	//code for communication between activity	
	protected int b1 = 82;
	protected int b2 = 83;
	protected int b3 = 84;
	protected int b4 = 85;
	protected int b5 = 86;
	protected int b6 = 87;
	protected int b7 = 88;
	
	//OnCLickListener
	private OnClickListener LV3CLS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hb_lv3mc);
		//textview
		LV3des = (TextView) findViewById(R.id.textView1);
		LV3des.setContentDescription("Please choose a product category");
		//buttons
		LV3CateBut1 = (Button)findViewById(R.id.button1);
		LV3CateBut1.setContentDescription("Body Care");
		LV3CateBut2 = (Button)findViewById(R.id.button2);
		LV3CateBut2.setContentDescription("Facial Cleansers");
		LV3CateBut3 = (Button)findViewById(R.id.button3);
		LV3CateBut3.setContentDescription("Facial Moisturizers");
		LV3CateBut4 = (Button)findViewById(R.id.button4);
		LV3CateBut4.setContentDescription("Deodorant");
		LV3CateBut5 = (Button)findViewById(R.id.button5);
		LV3CateBut5.setContentDescription("Cologne");
		LV3CateBut6 = (Button)findViewById(R.id.button6);
		LV3CateBut6.setContentDescription("Shaving");
		LV3CateBut7 = (Button)findViewById(R.id.button7);
		LV3CateBut7.setContentDescription("Lubricant & Condoms");
		
		//intend
		intentToPList = new Intent(HbLV3mcActivity.this,ProductListActivity.class);
						
		//OnClickListener creation
		LV3CLS = new OnClickListener() {
				
		@Override
		public void onClick(View v) {
		// TODO Auto-generated method stub
			int n=0;
			if(v==LV3CateBut1){//
				Log.i("ButtonL3", "1");
				//openList
				intentToPList.putExtra("catid", b1);
				n = b1;
			}
			else if(v==LV3CateBut2){//
				Log.i("ButtonL3", "2");
				//openList
				intentToPList.putExtra("catid", b2);
				n = b2;
			}
			else if(v==LV3CateBut3){//
				Log.i("ButtonL3", "3");
				//openList
				intentToPList.putExtra("catid", b3);
				n = b3;
			}
			else if(v==LV3CateBut4){//
				Log.i("ButtonL3", "4");
				//openList
				intentToPList.putExtra("catid", b4);
				n = b4;
			}
			else if(v==LV3CateBut5){//
				Log.i("ButtonL3", "5");
				//openList
				intentToPList.putExtra("catid", b5);
				n = b5;
			}
			else if(v==LV3CateBut6){//
				Log.i("ButtonL3", "6");
				//openList
				intentToPList.putExtra("catid", b6);
				n = b6;
			}
			else if(v==LV3CateBut7){//
				Log.i("ButtonL3", "7");
				//openList
				intentToPList.putExtra("catid", b7);
				n = b7;
			}	
			
			startActivityForResult(intentToPList, n);
		}
	};		
		//set OnClickListener
		LV3CateBut1.setOnClickListener(LV3CLS);
		LV3CateBut2.setOnClickListener(LV3CLS);
		LV3CateBut3.setOnClickListener(LV3CLS);
		LV3CateBut4.setOnClickListener(LV3CLS);
		LV3CateBut5.setOnClickListener(LV3CLS);
		LV3CateBut6.setOnClickListener(LV3CLS);
		LV3CateBut7.setOnClickListener(LV3CLS);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hb_lv3mc, menu);
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
