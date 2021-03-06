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

public class MainActivity extends Activity {
	/*
	 * view components
	 */
	//textview
	private TextView LV1des;
	//buttons
	private Button LV1CateBut1;
	private Button LV1CateBut2;
	private Button LV1CateBut3;
	private Button LV1CateBut4;
	private Button LV1CateBut5;
	private Button LV1CateBut6;
	private Button LV1CateBut7;
	private Button LV1CateBut8;
	
	//OnCLickListener
	private OnClickListener lv1CLS;
	
	/*
	 * Intent
	 */
	//intent to next lv
	private Intent intentToBev;
	private Intent intentToFood;
	private Intent intentToSnack;
	private Intent intentToHB;
	private Intent intentToHP;
	private Intent intentToCC;
	private Intent intentToPC;
	private Intent intentToPro;
	//menu item
	private Intent intentToCheckout;
	
	/*
	 * code for communication between activity
	 */
	//to next lv
	protected int Bev = 21;
	protected int Food = 22;
	protected int Snack = 23;
	protected int HB = 24;
	protected int HP = 25;
	protected int CC = 26;
	protected int PC = 27;
	protected int Pro = 28;
	//menu item
	protected int checkout = 1;

	//back to home
	public static boolean toHome = false;
	//payment list
	public static PaymentList payList = new PaymentList();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//textview
		LV1des = (TextView) findViewById(R.id.textView1);
		LV1des.setContentDescription("Please choose a product category");
		//buttons
		LV1CateBut1 = (Button)findViewById(R.id.button1);
		LV1CateBut1.setContentDescription("Beverage");
		LV1CateBut2 = (Button)findViewById(R.id.button2);
		LV1CateBut2.setContentDescription("Food");
		LV1CateBut3 = (Button)findViewById(R.id.button3);
		LV1CateBut3.setContentDescription("Snacks");
		LV1CateBut4 = (Button)findViewById(R.id.button4);
		LV1CateBut4.setContentDescription("Health & Beauty");
		LV1CateBut5 = (Button)findViewById(R.id.button5);
		LV1CateBut5.setContentDescription("Household Products");
		LV1CateBut6 = (Button)findViewById(R.id.button6);
		LV1CateBut6.setContentDescription("Car Care");
		LV1CateBut7 = (Button)findViewById(R.id.button7);
		LV1CateBut7.setContentDescription("Pet Care");
		LV1CateBut8 = (Button)findViewById(R.id.button8);
		LV1CateBut8.setContentDescription("Promotion");
		
		//Intent creation
		//next lv
		intentToBev = new Intent(MainActivity.this,LV2BevActivity.class);
		intentToFood = new Intent(MainActivity.this,LV2FoodActivity.class);
		intentToSnack = new Intent(MainActivity.this,LV2SnackActivity.class);
		intentToHB = new Intent(MainActivity.this,LV2HBActivity.class);
		intentToHP = new Intent(MainActivity.this,LV2HPActivity.class);
		intentToCC = new Intent(MainActivity.this,LV2CCActivity.class);
		intentToPC = new Intent(MainActivity.this,LV2PCActivity.class);
		intentToPro = new Intent(MainActivity.this,LV2ProActivity.class);
		//menu item
		intentToCheckout = new Intent(MainActivity.this,CheckOutActivity.class);
		
		//OnClickListener creation
		lv1CLS = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v==LV1CateBut1){//Beverage
					Log.i("ButtonL1", "1");
					//openLV2
					startActivityForResult(intentToBev, Bev);
				}
				else if(v==LV1CateBut2){//Food
					Log.i("ButtonL1", "2");
					//openLV2
					startActivityForResult(intentToFood, Food);
				}
				else if(v==LV1CateBut3){//Snacks
					Log.i("ButtonL1", "3");
					//openLV2
					startActivityForResult(intentToSnack, Snack);
				}
				else if(v==LV1CateBut4){//Health & Beauty
					Log.i("ButtonL1", "4");
					//openLV2
					startActivityForResult(intentToHB, HB);
				}
				else if(v==LV1CateBut5){//Household Product
					Log.i("ButtonL1", "5");
					//openLV2
					startActivityForResult(intentToHP, HP);
				}
				else if(v==LV1CateBut6){//Car Care
					Log.i("ButtonL1", "6");
					//openLV2
					startActivityForResult(intentToCC, CC);
				}
				else if(v==LV1CateBut7){//Pet Care
					Log.i("ButtonL1", "7");
					//openLV2
					startActivityForResult(intentToPC, PC);
				}
				else if(v==LV1CateBut8){//Promotion Product
					Log.i("ButtonL1", "8");
					//openLV2
					startActivityForResult(intentToPro, Pro);
				}
			}//end OnClick
		};//end creation
		
		//set OnClickListener
		LV1CateBut1.setOnClickListener(lv1CLS);
		LV1CateBut2.setOnClickListener(lv1CLS);
		LV1CateBut3.setOnClickListener(lv1CLS);
		LV1CateBut4.setOnClickListener(lv1CLS);
		LV1CateBut5.setOnClickListener(lv1CLS);
		LV1CateBut6.setOnClickListener(lv1CLS);
		LV1CateBut7.setOnClickListener(lv1CLS);
		LV1CateBut8.setOnClickListener(lv1CLS);
	}
	
	
	@Override
	protected void onResume() {
	    super.onResume();
	}
	
	@Override
	protected void onPause() {
		 super.onPause();  
	}

	@Override
	protected void onStop() {
	   super.onStop();
	}
	  
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intentFromAnother){
	    super.onActivityResult(requestCode, resultCode, intentFromAnother);
	    toHome = false;
	    if(requestCode == Bev && resultCode == RESULT_OK){
	    	Log.i("menu item", "from bev");
	    }
	    
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.checkout_settings:
			//go to checkout
			startActivityForResult(intentToCheckout, checkout);
			break;
		default:
			toHome = false;
			break;
		}
		return false;
	}
}
