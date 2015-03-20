package com.example.shona;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class TestLo extends Activity{
	private LocationManager lm = null;
	public static MyLocationListener ll = null;
	/*
	 * layout text view
	 */
	private TextView destText;
	public static TextView destName;
	private TextView disText;
	public static TextView disValue;
	private TextView headingText;
	public static TextView headingValue;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navi);
		/*
		 * text view
		 */
		destText = (TextView)findViewById(R.id.textView1);
		destText.setContentDescription("Going to");
		destName = (TextView)findViewById(R.id.textView2);
		disText= (TextView)findViewById(R.id.textView3);
		disText.setContentDescription("Distance");
		disValue= (TextView)findViewById(R.id.textView4);
		headingText= (TextView)findViewById(R.id.textView5);
		headingText.setContentDescription("Heading");
		headingValue= (TextView)findViewById(R.id.textView6);
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ll = new MyLocationListener();		
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
		ll.setLocationText(ll.getLatitude(), ll.getLongitude());
		ll.setHeadingText(ll.getHeading());
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
  
	@Override
	protected void onStop() {
		super.onStop();
	}


}
