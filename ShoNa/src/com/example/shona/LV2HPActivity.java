package com.example.shona;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
