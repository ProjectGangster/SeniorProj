package com.example.shona;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HbLV3hcActivity extends Activity {
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
	
	//OnCLickListener
	private OnClickListener LV3CLS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hb_lv3hc);
		//textview
				LV3des = (TextView) findViewById(R.id.textView1);
				LV3des.setContentDescription("Please choose a product category");
				//buttons
				LV3CateBut1 = (Button)findViewById(R.id.button1);
				LV3CateBut1.setContentDescription("Shampoo");
				LV3CateBut2 = (Button)findViewById(R.id.button2);
				LV3CateBut2.setContentDescription("Conditioners");
				LV3CateBut3 = (Button)findViewById(R.id.button3);
				LV3CateBut3.setContentDescription("Hair Treatments");
				LV3CateBut4 = (Button)findViewById(R.id.button4);
				LV3CateBut4.setContentDescription("Hair Styling");
				LV3CateBut5 = (Button)findViewById(R.id.button5);
				LV3CateBut5.setContentDescription("Hair Coloring");
				
				//set OnClickListener
				LV3CateBut1.setOnClickListener(LV3CLS);
				LV3CateBut2.setOnClickListener(LV3CLS);
				LV3CateBut3.setOnClickListener(LV3CLS);
				LV3CateBut4.setOnClickListener(LV3CLS);
				LV3CateBut5.setOnClickListener(LV3CLS);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hb_lv3hc, menu);
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
		return false;	}
}
