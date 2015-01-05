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

public class LV2FoodActivity extends Activity {
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
	
	//OnCLickListener
	private OnClickListener lv2CLS;
	
	/*
	 * Intent
	 */
	//intent
	private Intent intentToFresh;
	private Intent intentToFrozen;
	private Intent intentToPack;
	private Intent intentToCI;
	private Intent intentToBake;
	private Intent intentToDairy;
	//code for communication between activity
	protected int fresh = 33;
	protected int frozen = 34;
	protected int pack = 35;
	protected int ci = 36;
	protected int bake = 37;
	protected int dairy = 38;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv2_food);
		//textview
		LV2des = (TextView) findViewById(R.id.textView1);
		LV2des.setContentDescription("Please choose a product category");
		//buttons
		LV2CateBut1 = (Button)findViewById(R.id.button1);
		LV2CateBut1.setContentDescription("Fresh Food");
		LV2CateBut2 = (Button)findViewById(R.id.button2);
		LV2CateBut2.setContentDescription("Frozen Food");
		LV2CateBut3 = (Button)findViewById(R.id.button3);
		LV2CateBut3.setContentDescription("Package Food");
		LV2CateBut4 = (Button)findViewById(R.id.button4);
		LV2CateBut4.setContentDescription("Cooking ingredients");
		LV2CateBut5 = (Button)findViewById(R.id.button5);
		LV2CateBut5.setContentDescription("Bakery");
		LV2CateBut6 = (Button)findViewById(R.id.button6);
		LV2CateBut6.setContentDescription("Eggs & Dairy");
		
		//intent creation
		intentToFresh = new Intent(LV2FoodActivity.this,FoodLV3freshActivity.class);
		intentToFrozen = new Intent(LV2FoodActivity.this,FoodLV3frozenActivity.class);
		intentToPack = new Intent(LV2FoodActivity.this,FoodLV3packActivity.class);
		intentToCI = new Intent(LV2FoodActivity.this,FoodLV3ciActivity.class);
		intentToBake = new Intent(LV2FoodActivity.this,FoodLV3bakeActivity.class);
		intentToDairy = new Intent(LV2FoodActivity.this,BevLV3nalActivity.class);
		
		//OnClickListener creation
		lv2CLS = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==LV2CateBut1){//fresh food
					Log.i("ButtonL2", "1");
					//openLV3
					startActivityForResult(intentToFresh, fresh);
					finish();
				}
				else if(v==LV2CateBut2){//frozen food
					Log.i("ButtonL2", "2");
					//openLV3
					startActivityForResult(intentToFrozen, frozen);
					finish();
				}
				else if(v==LV2CateBut3){//package
					Log.i("ButtonL2", "3");
					//openLV3
					startActivityForResult(intentToPack, pack);
					finish();
				}
				else if(v==LV2CateBut4){//cooking ingredients
					Log.i("ButtonL2", "4");
					//openLV3
					startActivityForResult(intentToCI, ci);
					finish();
				}
				else if(v==LV2CateBut5){//bakery
					Log.i("ButtonL2", "5");
					//openLV3
					startActivityForResult(intentToBake, bake);
					finish();
				}
				else if(v==LV2CateBut6){//eggs & dairy
					Log.i("ButtonL2", "6");
					//openLV3
					startActivityForResult(intentToDairy, dairy);
					finish();
				}
			}//end OnClick
		};//end creation
		
				
		//set OnClickListener
		LV2CateBut1.setOnClickListener(lv2CLS);
		LV2CateBut2.setOnClickListener(lv2CLS);
		LV2CateBut3.setOnClickListener(lv2CLS);
		LV2CateBut4.setOnClickListener(lv2CLS);
		LV2CateBut5.setOnClickListener(lv2CLS);
		LV2CateBut6.setOnClickListener(lv2CLS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lv2_food, menu);
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
