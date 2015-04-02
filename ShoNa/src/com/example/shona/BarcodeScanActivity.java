package com.example.shona;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BarcodeScanActivity extends Activity {
	//intent
	private Intent intentToPdetail;
	private Intent intentToCheckout;
	//code
	protected int check = 997;
		
	private Button scanBtn;
	private TextView formatTxt, contentTxt;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_barcode_scan);
		
		
		//intent creation
		intentToPdetail = new Intent(BarcodeScanActivity.this,ProductDetailsActivity.class);
		intentToCheckout = new Intent(BarcodeScanActivity.this,CheckOutActivity.class);
		//ask if user wants to go to the cashier
//		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//   	dialog.setTitle("Checkout?");
    	//activate button OnClickListener
//    	dialog.setNegativeButton(R.string.okButton, new DialogInterface.OnClickListener() {
			//activate button OnClickListener creation
//			public void onClick(DialogInterface arg0, int arg1) {
//				startActivityForResult(intentToCheckout, check);
//			}
//		});//end activate button
 //   	dialog.setPositiveButton(R.string.cancelButton, null);
 //   	dialog.show();
    	
    	IntentIntegrator scanIntegrator = new IntentIntegrator(this);
		scanIntegrator.initiateScan();
	}

	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	//retrieve scan result
    	IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
    	if (scanningResult != null) {
    		String scanContent = scanningResult.getContents();
    		String scanFormat = scanningResult.getFormatName();
    		intentToPdetail.putExtra("PID", scanContent);
    		startActivityForResult(intentToPdetail, check);
    		//we have a result
//    		formatTxt.setText("FORMAT: " + scanFormat);
//    		contentTxt.setText("CONTENT: " + scanContent);
//    		int PID = Integer.parseInt(scanContent);
//    		Product p = ProductList_HandleJSON.getPDetail(PID);
//    		Log.d("P ID", p.getId()+"");
    		//String finalUrl = "http://www.numpun.lnw.mn/shona/API/productdetail.php?ID="+pid;
//    	   // country.setText(finalUrl);
//    	   // obj = new HandleJSON(finalUrl);
//    	   // obj.fetchJSON();
//
//    	    while(obj.parsingComplete);
//    	     country.setText(obj.getCountry());
    	}
    	else{
    	    Toast toast = Toast.makeText(getApplicationContext(), 
    	        "No scan data received!", Toast.LENGTH_SHORT);
    	    toast.show();
    	}
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.barcode_scan, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.back_settings:
			//go to checkout
			MainActivity.toHome = false;
			setResult(RESULT_OK);
			finish();
			break;
		case R.id.home_settings:
			//go to home
			MainActivity.toHome = true;
			setResult(RESULT_OK);
			finish();
			break;
		case R.id.checkout_settings:
			//go to home
			MainActivity.toHome = false;
			startActivityForResult(intentToCheckout, check);
			break;
		default:
			break;
		}
		return false;
	}
}
