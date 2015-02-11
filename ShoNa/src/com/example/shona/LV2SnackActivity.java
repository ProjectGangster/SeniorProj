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

public class LV2SnackActivity extends Activity {
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
	
	//intend
	private Intent intentToPList;
				
	//code for communication between activity	
	protected int b1 = 54;
	protected int b2 = 55;
	protected int b3 = 56;
	protected int b4 = 57;
	protected int b5 = 58;
					
	//OnCLickListener
	private OnClickListener lv2CLS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv2_snack);
		//textview
		LV2des = (TextView) findViewById(R.id.textView1);
		LV2des.setContentDescription("Please choose a product category");
		//buttons
		LV2CateBut1 = (Button)findViewById(R.id.button1);
		LV2CateBut1.setContentDescription("Confectionery");
		LV2CateBut2 = (Button)findViewById(R.id.button2);
		LV2CateBut2.setContentDescription("Chips");
		LV2CateBut3 = (Button)findViewById(R.id.button3);
		LV2CateBut3.setContentDescription("Bars");
		LV2CateBut4 = (Button)findViewById(R.id.button4);
		LV2CateBut4.setContentDescription("Biscuits & Crackers");
		LV2CateBut5 = (Button)findViewById(R.id.button5);
		LV2CateBut5.setContentDescription("Cookies");
	
		//intend
		intentToPList = new Intent(LV2SnackActivity.this,ProductListActivity.class);
						
		//OnClickListener creation
		lv2CLS = new OnClickListener() {
				
		@Override
		public void onClick(View v) {
		// TODO Auto-generated method stub
			int n=0;
			if(v==LV2CateBut1){//
				Log.i("ButtonL2", "1");
				//openList
				intentToPList.putExtra("catid", b1);
				n = b1;
			}
			else if(v==LV2CateBut2){//
				Log.i("ButtonL2", "2");
				//openList
				intentToPList.putExtra("catid", b2);
				n = b2;
			}
			else if(v==LV2CateBut3){//
				Log.i("ButtonL2", "3");
				//openList
				intentToPList.putExtra("catid", b3);
				n = b3;
			}
			else if(v==LV2CateBut4){//
				Log.i("ButtonL2", "4");
				//openList
				intentToPList.putExtra("catid", b4);
				n = b4;
			}
			else if(v==LV2CateBut5){//
				Log.i("ButtonL2", "5");
				//openList
				intentToPList.putExtra("catid", b5);
				n = b5;
			}
							
			startActivityForResult(intentToPList, n);
		}
	};			
		//set OnClickListener
		LV2CateBut1.setOnClickListener(lv2CLS);
		LV2CateBut2.setOnClickListener(lv2CLS);
		LV2CateBut3.setOnClickListener(lv2CLS);
		LV2CateBut4.setOnClickListener(lv2CLS);
		LV2CateBut5.setOnClickListener(lv2CLS);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intentFromAnother){
	    super.onActivityResult(requestCode, resultCode, intentFromAnother);
	    if(MainActivity.toHome){
			finish();
		}
	    else{
	    	//do sth
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lv2_snack, menu);
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
		default:
			break;
		}
		return false;
	}
}
