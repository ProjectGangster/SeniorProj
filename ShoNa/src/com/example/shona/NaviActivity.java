package com.example.shona;

import java.util.Arrays;
import java.util.List;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class NaviActivity extends Activity implements LocationListener{
	/*
	 * layout text view
	 */
	private TextView destText;
	private TextView destName;
	private TextView disText;
	private TextView disValue;
	private TextView headingText;
	private TextView headingValue;
	/*
	 * Beacon
	 */
	private BeaconManager beaconManager;
	private Beacon beacon;
	//region for discovering beacons	
	private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);
	//beacon distance
	private double dis[];
	private int id[];//minor
	private double temp[];
	private double closeDis[] = new double[3];
	private int closeID[] = new int[3];
	
	//user's position
	public static Location userLoc;
	private Location b1 = new Location("b1Location");
	private Location b2 = new Location("b2Location");
	private Location b3 = new Location("b3Location");
	private double latitude1 = 0.0;
	private double longitude1 = 0.0;
	private double latitude2 = 0.0;
	private double longitude2 = 0.0;
	private double latitude3 = 0.0;
	private double longitude3 = 0.0;
	
	/*
	 * intent
	 */
	private Intent intentToBC = new Intent(NaviActivity.this,BarcodeScanActivity.class);
	protected int bc = 555;
	
	//navigation type: 0=default, 1=to product, 2=to cashier
	private int navType = 0;
	//destination location
	private Location destination = new Location("desLoc");
	@SuppressWarnings("unused")
	private Region destReg;
	private Location destLoc;
	//product
	@SuppressWarnings("unused")
	private int proType = 1;
	//navigation route
	private Location[] navRoute = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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

		//get navigation type
		navType = getIntent().getIntExtra("navType", 0);
		//check navigation type
		if(navType==1){
			//to product
			proType = getIntent().getIntExtra("proType", 1);
/*
 * send product type to db to get product type's name
 */
			destName.setText("product Type");
//get the beacon of the productType
/*
 * 
 * 
 */
//destLoc = Beacon_HandleJSON.getBeaconLocation("1");

		}
		else if(navType==2){
			//to cashier
			destName.setText("Cashier");
			destName.setContentDescription("Cashier");
//get cashier's beacon info
/*
 * destBeac = new Region();//from db	
 * destLoc = BeaconLocation;
 */
//destLoc = Beacon_HandleJSON.getBeaconLocation("1");

		}
		//beaconManager
		beaconManager = new BeaconManager(this);
		//check Bluetooth signal
		if(!beaconManager.isBluetoothEnabled()){
			//Bluetooth is off
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
	    	dialog.setTitle("Please turn on Bluetooth before using this application");
	    	//activate button OnClickListener				
	    	dialog.setPositiveButton("OK", new OnClickListener() {	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//close the program
					finish();
				}
			});
	    	dialog.show();
		}
		else{
			//Blue tooth is on, then start connecting to the service
			connectToService();
			beaconManager.setRangingListener(new BeaconManager.RangingListener(){
				@Override
				public void onBeaconsDiscovered(Region region, final List<Beacon> blist) {
					// Note that results are not delivered on UI thread.
			        runOnUiThread(new Runnable() {
			          @Override
			          public void run() {
			            // Note that beacons reported here are already sorted by estimated
			            // distance between device and beacon.
			        	  Log.i("----------------", "Found beacons: "+blist.size());
			        	  dis = new double[blist.size()];
			        	  id = new int[blist.size()];
			        	  for(int i=0;i<blist.size();i++){
			        		  beacon = blist.get(i);
				        	  Log.i("----------------", "beacon no."+i);
				        	  Log.i("----------------", "minor"+beacon.getMinor());
				        	  id[i] = beacon.getMinor();
				        	  dis[i] = calculateAccuracy(beacon.getMeasuredPower(), beacon.getRssi());
			        	  }
			        	  //get 3 closest beacons and setup their latitudes and longitudes
			        	  threeClosest(getDis());
			        	  //calculate the position of the user
			        	  userLoc = getLocationWithTrilateration(b1, b2, b3, closeDis[0], closeDis[1], closeDis[2]);
			        	  navigation(userLoc, destLoc);
			          }//end runnable
			        });//end runOnUiThread
				}//end beaconDiscover
			});//end setRanging
		}//end else
	}//end onCreate
	
	
	@Override
	  protected void onDestroy() {
	    beaconManager.disconnect();
	    super.onDestroy();
	  }
	
	 @Override
	  protected void onStop() {
	    try {
	      beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS_REGION);
	    } catch (RemoteException e) {
	      Log.i("STOPPPP", "Error while stopping ranging");
	    }
	    super.onStop();
	  }
	 
	 @Override
	  protected void onResume() {
		 connectToService();
		 super.onResume();
	  }
	 
	//connecting beacons
	private void connectToService() {
	    beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
	      @Override
	      public void onServiceReady() {
	        try {
	          beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
	        } catch (RemoteException e) {
	          Toast.makeText(NaviActivity.this, "Cannot start ranging, something terrible happened",
	              Toast.LENGTH_LONG).show();
	          Log.i("CANNOT RANGE", "Cannot start ranging");
	        }
	      }
	    });
	}
	
	/*
	 * calculate the accuracy
	 * from http://stackoverflow.com/questions/20416218/understanding-ibeacon-distancing/20434019#20434019
	 */
	protected static double calculateAccuracy(int txPower, double rssi) {
		  if (rssi == 0) {
		    return -1.0; // if we cannot determine accuracy, return -1.
		  }

		  double ratio = rssi*1.0/txPower;
		  if (ratio < 1.0) {
		    return Math.pow(ratio,10);
		  }
		  else {
		    double accuracy =  (0.89976)*Math.pow(ratio,7.7095) + 0.111;    
		    return accuracy;
		  }
	}   
	
	/*
	 *1. get beacon’s ID
	 *2. match ID
	 *3. getLat/Long from DB
	 *4. setLat/Long of it 
	 */
	protected void threeClosest(double[] dist){
		temp = dist;
		//sort to get the 3 minimum distance
		Arrays.sort(temp);
		Log.i(">>>>>>>>>>closest 1", ""+temp[0]);
		Log.i(">>>>>>>>>>closest 2", ""+temp[1]);
		Log.i(">>>>>>>>>>closest 3", ""+temp[2]);
		closeDis[0] = temp[0];
		closeDis[1] = temp[1];
		closeDis[2] = temp[2];
		for(int j=0;j<closeDis.length;j++){
			for(int i=0;i<dist.length;i++){
				//find 3 closest distance beacons' id
				if(closeDis[j]==dist[i]){
					closeID[j] = getId()[i];
				}
			}//end dist
		}//end closeDis
		
/*
 * -------------------------------getLat/Long from DB
 */
		Double beacon3[] = Beacon_HandleJSON.getBeacon3(closeID);
		
		//setLat/Long of 3 closest
		b1.setLatitude(beacon3[0]);
		b1.setLongitude(beacon3[1]);
		b2.setLatitude(beacon3[2]);
		b2.setLongitude(beacon3[3]);
		b3.setLatitude(beacon3[4]);
		b3.setLongitude(beacon3[5]);
	}
	
	/*
	 *get location from triangle beacons
	 * from http://stackoverflow.com/questions/20332856/triangulate-example-for-ibeacons
	 */
	public static Location getLocationWithTrilateration(Location beaconA, Location beaconB, Location beaconC, double distanceA, double distanceB, double distanceC){

	    double bAlat = beaconA.getLatitude();
	    double bAlong = beaconA.getLongitude();
	    double bBlat = beaconB.getLatitude();
	    double bBlong = beaconB.getLongitude();
	    double bClat = beaconC.getLatitude();
	    double bClong = beaconC.getLongitude();

	    double W, Z, foundBeaconLat, foundBeaconLong, foundBeaconLongFilter;
	    W = distanceA * distanceA - distanceB * distanceB - bAlat * bAlat - bAlong * bAlong + bBlat * bBlat + bBlong * bBlong;
	    Z = distanceB * distanceB - distanceC * distanceC - bBlat * bBlat - bBlong * bBlong + bClat * bClat + bClong * bClong;

	    foundBeaconLat = (W * (bClong - bBlong) - Z * (bBlong - bAlong)) / (2 * ((bBlat - bAlat) * (bClong - bBlong) - (bClat - bBlat) * (bBlong - bAlong)));
	    foundBeaconLong = (W - 2 * foundBeaconLat * (bBlat - bAlat)) / (2 * (bBlong - bAlong));
	    //`foundBeaconLongFilter` is a second measure of `foundBeaconLong` to mitigate errors
	    foundBeaconLongFilter = (Z - 2 * foundBeaconLat * (bClat - bBlat)) / (2 * (bClong - bBlong));

	    foundBeaconLong = (foundBeaconLong + foundBeaconLongFilter) / 2;

	    Location foundLocation = new Location("Location");
	        foundLocation.setLatitude(foundBeaconLat);
	        foundLocation.setLongitude(foundBeaconLong);

	    return foundLocation;
	}

	private void navigation(Location current, Location dest){
		//current position is not near destination
		if(current.distanceTo(dest)>1){
			//generate route to destination
			navRoute = Route.genRoute(current, dest);
		}
		while(!navRoute.equals(null)){
			//navigate
			for(int i=0;i<navRoute.length;i++){
				Log.i("Routeeee","distance : "+getDistance(current, navRoute[i]));
				Log.i("Routeeee","heading : "+getHeading(current, navRoute[i]));
				disValue.setText(getDistance(current, navRoute[i]));
				disValue.setContentDescription(getDistance(current, navRoute[i]));
				headingValue.setText(getHeading(current, navRoute[i]));
				headingValue.setContentDescription(getHeading(current, navRoute[i]));
			}
		}
		//notify reaching destination zone
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    	if(navType==1){
    		//to product
    		dialog.setTitle("You have reach the product zone");
	    	//activate button OnClickListener				
	    	dialog.setPositiveButton("OK", new OnClickListener() {	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//go to barcode scanner
		    		startActivityForResult(intentToBC, bc);
		    		finish();
				}
			});
	    	dialog.show();
    	}
    	else if(navType==2){
    		//to cashier
    		dialog.setTitle("You have reach the cashier zone");
	    	//activate button OnClickListener				
	    	dialog.setPositiveButton("OK", new OnClickListener() {	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//close
					finish();
				}
			});
	    	dialog.show();
    	}
	}//end navigation

	public BeaconManager getBeaconManager() {
		return beaconManager;
	}


	public Beacon getBeacon() {
		return beacon;
	}


	public static Region getAllEstimoteBeaconsRegion() {
		return ALL_ESTIMOTE_BEACONS_REGION;
	}


	public double[] getDis() {
		return dis;
	}


	public int[] getId() {
		return id;
	}


	public double[] getTemp() {
		return temp;
	}


	public double[] getCloseDis() {
		return closeDis;
	}


	public int[] getCloseID() {
		return closeID;
	}

	public static Location getUserPo() {
		return userLoc;
	}


	public Location getDestination() {
		return destination;
	}


	public void setDestination(Location destination) {
		this.destination = destination;
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		connectToService();
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "GPS is enable", Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "GPS is disable", Toast.LENGTH_SHORT).show();
	}
	/*
	 * nav
	 */
	private String getDistance(Location now, Location dest){
		return now.distanceTo(dest)+" meters";
	}
	private String getHeading(Location now, Location dest){
		String side = " degrees on the right side";
		double degree = 0.0;
		degree = now.bearingTo(dest);
		if(degree==0.0 || degree == 360){
			side = "degrees, go straight";
		}
		else if(degree==180.0){
			side = "degrees on the back";
		}
		else if(degree>180 && degree<360){
			degree -= 360;
			side = "degrees on the left side";
		}
		return degree+side;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.navi, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.home_settings:
			//go to home
			MainActivity.toHome = true;
			setResult(RESULT_OK);
			finish();
			break;
		default:
			break;
		}
		return false;
	}
}
