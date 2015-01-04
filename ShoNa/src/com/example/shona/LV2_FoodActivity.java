package com.example.shona;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LV2_FoodActivity extends Activity {
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
		LV2CateBut4.setContentDescription("Organic Food");
		LV2CateBut5 = (Button)findViewById(R.id.button5);
		LV2CateBut5.setContentDescription("Bakery");
		LV2CateBut6 = (Button)findViewById(R.id.button6);
		LV2CateBut6.setContentDescription("Dairy");
		
		//OnClickListener creation
		/*lv1CLS = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v==LV1CateBut1){//Beverage
					Log.i("ButtonL1", "1");
					//openLV2
					startActivityForResult(intentToBev, Bev);
					finish();
				}
				else if(v==LV1CateBut2){//Food
					Log.i("ButtonL1", "2");
					//openLV2
					startActivityForResult(intentToFood, Food);
					finish();
				}
				else if(v==LV1CateBut3){//Snack
					Log.i("ButtonL1", "3");
					//openLV2
					startActivityForResult(intentToSnack, Snack);
					finish();
				}
				else if(v==LV1CateBut4){//Health & Beauty
					Log.i("ButtonL1", "4");
					//openLV2
					startActivityForResult(intentToHB, HB);
					finish();
				}
				else if(v==LV1CateBut5){//Household Product
					Log.i("ButtonL1", "5");
					//openLV2
					startActivityForResult(intentToHP, HP);
					finish();
				}
				else if(v==LV1CateBut6){//Car Care
					Log.i("ButtonL1", "6");
					//openLV2
					startActivityForResult(intentToCC, CC);
					finish();
				}
		*/
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
