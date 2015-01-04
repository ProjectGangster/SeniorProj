package com.example.shona;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	/**
	 * view components
	 */
	//textview
	TextView LV1des;
	//buttons
	Button LV1CateBut1;
	Button LV1CateBut2;
	Button LV1CateBut3;
	Button LV1CateBut4;
	Button LV1CateBut5;
	Button LV1CateBut6;
	Button LV1CateBut7;
	Button LV1CateBut8;

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
		LV1CateBut3.setContentDescription("Snack");
		LV1CateBut4 = (Button)findViewById(R.id.button4);
		LV1CateBut4.setContentDescription("Health & Beauty");
		LV1CateBut5 = (Button)findViewById(R.id.button5);
		LV1CateBut5.setContentDescription("Household Product");
		LV1CateBut6 = (Button)findViewById(R.id.button6);
		LV1CateBut6.setContentDescription("Car Care");
		LV1CateBut7 = (Button)findViewById(R.id.button7);
		LV1CateBut7.setContentDescription("Pet Care");
		LV1CateBut8 = (Button)findViewById(R.id.button8);
		LV1CateBut8.setContentDescription("Promotion Product");
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return false;
	}
}
