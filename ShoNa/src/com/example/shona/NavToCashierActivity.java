package com.example.shona;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

public class NavToCashierActivity extends Activity {
	/*
	 * Beacon
	 */
	//private BeaconConnection bcCon;
	private BeaconManager beaconManager;
	private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
	private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nav_to_cashier);
		//beacon
		beaconManager  = new BeaconManager(this);
		//bcCon = new BeaconConnection(this, beacon, connectionCallback)
		beaconManager.setRangingListener(new BeaconManager.RangingListener() {
		    @Override 
		    public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
		      Log.i("BEACONNNNN", "Ranged beacons: " + beacons);
		    }

		});
		
	}
	@Override
	protected void onResume() {
	    super.onResume();
	    // Should be invoked in #onStart.
		 /* beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
		    @Override public void onServiceReady() {
		      try {
		        beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
		      } catch (RemoteException e) {
		        Log.e(TAG, "Cannot start ranging", e);
		      }
		    }
		  });
		  */
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	  
	@Override
	protected void onStop() {
	    super.onStop();
	    try {
	        beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS);
	    } catch (RemoteException e) {
	        Log.i("BEACONNNNN", "Cannot stop but it does not matter now", e);
	    }
	}
	  
	@Override
	protected void onDestroy() {
		super.onDestroy();
		beaconManager.disconnect();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nav_to_cashier, menu);
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
