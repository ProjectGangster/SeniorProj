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

public class LV2ProActivity extends Activity {
	
	//textview
	private TextView LV2des;
	//buttons
	private Button LV2CateBut1;
		
	//intend
	private Intent intentToPList;
				
	//code for communication between activity	
	protected int b1 = 165;

	
	//OnCLickListener
	private OnClickListener LV2CLS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lv2_pro);
		
		//intend
		intentToPList = new Intent(LV2ProActivity.this,ProductListActivity.class);
						
		//OnClickListener creation
		intentToPList.putExtra("catid", b1);
		int n = b1;
		startActivityForResult(intentToPList, n);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lv2, menu);
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
