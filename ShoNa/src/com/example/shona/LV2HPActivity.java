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

public class LV2HPActivity extends Activity {
	/*
	 * view components
	 */
	//textview
	private TextView LV2des;
	//buttons
	private Button LV2CateBut1;
	private Button LV2CateBut2;
	private Button LV2CateBut3;
	private Button LV2CateBut4;
	private Button LV2CateBut5;
	private Button LV2CateBut6;
	private Button LV2CateBut7;
	private Button LV2CateBut8;
	
	//OnCLickListener
	private OnClickListener LV2CLS;
	
	/*
	 * Intent
	 */
	//intent
	private Intent intentToPp;
	private Intent intentToKe;
	private Intent intentToPc;
	private Intent intentToLa;
	private Intent intentToDd;
	private Intent intentToHoc;
	private Intent intentToCs;
	private Intent intentToMi;
	
	//code for communication between activity
	protected int pp = 47;
	protected int ke = 48;
	protected int pc = 49;
	protected int la = 50;
	protected int dd = 51;
	protected int hoc = 52;
	protected int cs = 53;
	protected int mi = 54;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv2_hp);
		//textview
		LV2des = (TextView) findViewById(R.id.textView1);
		LV2des.setContentDescription("Please choose a product category");
		//buttons
		LV2CateBut1 = (Button)findViewById(R.id.button1);
		LV2CateBut1.setContentDescription("Paper Products");
		LV2CateBut2 = (Button)findViewById(R.id.button2);
		LV2CateBut2.setContentDescription("Kitchen Essentials");
		LV2CateBut3 = (Button)findViewById(R.id.button3);
		LV2CateBut3.setContentDescription("Pest Control");
		LV2CateBut4 = (Button)findViewById(R.id.button4);
		LV2CateBut4.setContentDescription("Laundry");
		LV2CateBut5 = (Button)findViewById(R.id.button5);
		LV2CateBut5.setContentDescription("Dish Detergent");
		LV2CateBut6 = (Button)findViewById(R.id.button6);
		LV2CateBut6.setContentDescription("Household Cleaners");
		LV2CateBut7 = (Button)findViewById(R.id.button7);
		LV2CateBut7.setContentDescription("Cleaning Supply");
		LV2CateBut8 = (Button)findViewById(R.id.button8);
		LV2CateBut8.setContentDescription("Miscellaneous");
		
		//Intent creation
		intentToPp = new Intent(LV2HPActivity.this,HbLV3ppActivity.class);
		intentToKe = new Intent(LV2HPActivity.this,HbLV3keActivity.class);
		intentToPc = new Intent(LV2HPActivity.this,HbLV3pcActivity.class);
		intentToLa = new Intent(LV2HPActivity.this,HbLV3laActivity.class);
		intentToDd = new Intent(LV2HPActivity.this,HbLV3ddActivity.class);
		intentToHoc = new Intent(LV2HPActivity.this,HbLV3hocActivity.class);
		intentToCs = new Intent(LV2HPActivity.this,HbLV3csActivity.class);
		intentToMi = new Intent(LV2HPActivity.this,HbLV3miActivity.class);
		
		//OnClickListener creation
		LV2CLS = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==LV2CateBut1){//pp
					Log.i("ButtonL2", "1");
					//openLV3
					startActivityForResult(intentToPp, pp);
					finish();
				}
				else if(v==LV2CateBut2){//ke
					Log.i("ButtonL2", "2");
					//openLV3
					startActivityForResult(intentToKe, ke);
					finish();
				}
				else if(v==LV2CateBut3){//pc
					Log.i("ButtonL2", "3");
					//openLV3
					startActivityForResult(intentToPc, pc);
					finish();
				}
				else if(v==LV2CateBut4){//la
					Log.i("ButtonL2", "4");
					//openLV3
					startActivityForResult(intentToLa, la);
					finish();
				}
				else if(v==LV2CateBut5){//dd
					Log.i("ButtonL2", "5");
					//openLV3
					startActivityForResult(intentToDd, dd);
					finish();
				}
				else if(v==LV2CateBut6){//hoc
					Log.i("ButtonL2", "6");
					//openLV3
					startActivityForResult(intentToHoc, hoc);
					finish();
				}
				else if(v==LV2CateBut7){//cs
					Log.i("ButtonL2", "7");
					//openLV3
					startActivityForResult(intentToCs, cs);
					finish();
				}
				else if(v==LV2CateBut8){//mi
					Log.i("ButtonL2", "8");
					//openLV3
					startActivityForResult(intentToMi, mi);
					finish();
				}
			}//end OnClick
		};//end creation
				
		//set OnClickListener
		LV2CateBut1.setOnClickListener(LV2CLS);
		LV2CateBut2.setOnClickListener(LV2CLS);
		LV2CateBut3.setOnClickListener(LV2CLS);
		LV2CateBut4.setOnClickListener(LV2CLS);
		LV2CateBut5.setOnClickListener(LV2CLS);
		LV2CateBut6.setOnClickListener(LV2CLS);
		LV2CateBut7.setOnClickListener(LV2CLS);
		LV2CateBut8.setOnClickListener(LV2CLS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lv2_h, menu);
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
