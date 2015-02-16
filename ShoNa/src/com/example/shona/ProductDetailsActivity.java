package com.example.shona;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ProductDetailsActivity extends Activity {
	/*
	 * view components
	 */
	//textview
	private TextView proNameText;
	private TextView proName;
	private TextView proBrandText;
	private TextView proBrand;
	private TextView proVolText;
	private TextView proVol;
	private TextView proPriceText;
	private TextView proPrice;
	private TextView proDesText;
	private TextView proDes;
	//buttons
	private Button okbutt;
	
	//intent
	private Intent intentToBC;
	private Intent intentToCheckout;
	//code
	protected int bc = 998;
	protected int check = 997; 
	
	//product
	private int id = 0;
	private Product temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_detials);
		
		//intent creation
		intentToBC = new Intent(ProductDetailsActivity.this,BarcodeScanActivity.class);
		intentToCheckout = new Intent(ProductDetailsActivity.this,CheckOutActivity.class);
		id = getIntent().getIntExtra("proID", 0);
		
				
		//textview
		proNameText = (TextView) findViewById(R.id.textView1);
		proNameText.setContentDescription("Name");
		proName = (TextView) findViewById(R.id.text1);
		proBrandText = (TextView) findViewById(R.id.TextView02);
		proBrandText.setContentDescription("Brand");
		proBrand = (TextView) findViewById(R.id.text2);
		proBrand.setContentDescription("");
		proVolText = (TextView) findViewById(R.id.textView3);
		proVolText.setContentDescription("Volumn");
		proVol = (TextView) findViewById(R.id.text3);
		proVol.setContentDescription("");
		proPriceText = (TextView) findViewById(R.id.textView5);
		proPriceText.setContentDescription("Price");
		proPrice = (TextView) findViewById(R.id.text4);
		proPrice.setContentDescription("");
		proDesText = (TextView) findViewById(R.id.textView7);
		proDesText.setContentDescription("Description");
		proDes = (TextView) findViewById(R.id.text5);
		proDes.setContentDescription("");
		
		//search the product from id
		/*
		temp = ProductList_HandleJSON.getPDetail(id);
				//connect product details to the ui
		proName.setText(temp.getName());
		proName.setContentDescription(""+temp.getName());
		proBrand.setText(temp.getBrand());
		proBrand.setContentDescription(""+temp.getBrand());
		proVol.setText(""+temp.getVolume());
		proVol.setContentDescription(""+temp.getVolume());
		proPrice.setText(""+temp.getPrice());
		proPrice.setContentDescription(""+temp.getPrice());
		proDes.setText(temp.getDescription());
		proDes.setContentDescription(""+temp.getDescription());
		*/
		
		//buttons
		okbutt = (Button)findViewById(R.id.button1);
		okbutt.setContentDescription("OK");
		
		//setOnClickListener
		okbutt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(v==okbutt){
					//ask if user wants to go to scan the bc
					AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
			    	dialog.setTitle("Please scan the barcode of this product");
			    	//activate button OnClickListener
			    	dialog.setNegativeButton(R.string.okButton, new DialogInterface.OnClickListener() {
						//activate button OnClickListener creation
						public void onClick(DialogInterface arg0, int arg1) {
							startActivityForResult(intentToBC, bc);
						}
					});//end activate button
			    	dialog.setPositiveButton(R.string.cancelButton, null);
			    	dialog.show();
				}
			}
		});
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
		getMenuInflater().inflate(R.menu.product_detials, menu);
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
