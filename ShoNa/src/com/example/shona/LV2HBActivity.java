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

public class LV2HBActivity extends Activity {
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
	private Intent intentToBc;
	private Intent intentToFc;
	private Intent intentToHc;
	private Intent intentToDc;
	private Intent intentToMc;
	private Intent intentToWc;
	private Intent intentToCo;
	private Intent intentToFa;
	
	//code for communication between activity
	protected int bc = 39;
	protected int fc = 40;
	protected int hc = 41;
	protected int dc = 42;
	protected int mc = 43;
	protected int wc = 44;
	protected int co = 45;
	protected int fa = 46;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv2_hb);
		//textview
		LV2des = (TextView) findViewById(R.id.textView1);
		LV2des.setContentDescription("Please choose a product category");
		//buttons
		LV2CateBut1 = (Button)findViewById(R.id.button1);
		LV2CateBut1.setContentDescription("Body Care");
		LV2CateBut2 = (Button)findViewById(R.id.button2);
		LV2CateBut2.setContentDescription("Facial Care");
		LV2CateBut3 = (Button)findViewById(R.id.button3);
		LV2CateBut3.setContentDescription("Hair Care");
		LV2CateBut4 = (Button)findViewById(R.id.button4);
		LV2CateBut4.setContentDescription("Dental Care");
		LV2CateBut5 = (Button)findViewById(R.id.button5);
		LV2CateBut5.setContentDescription("Men Care");
		LV2CateBut6 = (Button)findViewById(R.id.button6);
		LV2CateBut6.setContentDescription("Women Care");
		LV2CateBut7 = (Button)findViewById(R.id.button7);
		LV2CateBut7.setContentDescription("Cosmetics");
		LV2CateBut8 = (Button)findViewById(R.id.button8);
		LV2CateBut8.setContentDescription("First Aid");
		
		//Intent creation
		intentToBc = new Intent(LV2HBActivity.this,HbLV3bcActivity.class);
		intentToFc = new Intent(LV2HBActivity.this,HbLV3fcActivity.class);
		intentToHc = new Intent(LV2HBActivity.this,HbLV3hcActivity.class);
		intentToDc = new Intent(LV2HBActivity.this,HbLV3dcActivity.class);
		intentToMc = new Intent(LV2HBActivity.this,LV2HPActivity.class);
		intentToWc = new Intent(LV2HBActivity.this,LV2CCActivity.class);
		intentToCo = new Intent(LV2HBActivity.this,LV2PCActivity.class);
		intentToFa = new Intent(LV2HBActivity.this,LV2ProActivity.class);
		
		//OnClickListener creation
		LV2CLS = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==LV2CateBut1){//bc
					Log.i("ButtonL2", "1");
					//openLV3
					startActivityForResult(intentToBc, bc);
					finish();
				}
				else if(v==LV2CateBut2){//fc
					Log.i("ButtonL2", "2");
					//openLV3
					startActivityForResult(intentToFc, fc);
					finish();
				}
				else if(v==LV2CateBut3){//hc
					Log.i("ButtonL2", "3");
					//openLV3
					startActivityForResult(intentToHc, hc);
					finish();
				}
				else if(v==LV2CateBut4){//dc
					Log.i("ButtonL2", "4");
					//openLV3
					startActivityForResult(intentToDc, dc);
					finish();
				}
				else if(v==LV2CateBut5){//mc
					Log.i("ButtonL2", "5");
					//openLV3
					startActivityForResult(intentToMc, mc);
					finish();
				}
				else if(v==LV2CateBut6){//wc
					Log.i("ButtonL2", "6");
					//openLV3
					startActivityForResult(intentToWc, wc);
					finish();
				}
				else if(v==LV2CateBut7){//co
					Log.i("ButtonL2", "7");
					//openLV3
					startActivityForResult(intentToCo, co);
					finish();
				}
				else if(v==LV2CateBut8){//fa
					Log.i("ButtonL2", "8");
					//openLV3
					startActivityForResult(intentToFa, fa);
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
		getMenuInflater().inflate(R.menu.lv2_hb, menu);
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
