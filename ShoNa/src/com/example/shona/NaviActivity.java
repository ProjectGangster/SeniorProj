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
import android.location.Location;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class NaviActivity extends Activity{
	/*
	 * Beacon
	 */
	private BeaconManager beaconManager;
	private Beacon beacon;
	//region for discovering beacons	
	private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);
	
	//beacon distance
	private double dis[];
	private String id[];
	private double temp[];
	private double closeDis[] = new double[3];
	private String closeID[] = new String[3];
	
	//user's position
	public static Location userPo;
	private Location b1 = new Location("b1Location");
	private Location b2 = new Location("b2Location");
	private Location b3 = new Location("b3Location");
	private double latitude1 = 0.0;
	private double longitude1 = 0.0;
	private double latitude2 = 0.0;
	private double longitude2 = 0.0;
	private double latitude3 = 0.0;
	private double longitude3 = 0.0;
	
	//navigation type: 0=default, 1=to product, 2=to cashier
	private int navType = 0;
	//destination location
	private Location destination[] = new Location[2];
	//product
	private int proType = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navi);
		
		//get navigation type
		navType = getIntent().getIntExtra("navType", 0);
		//check navigation type
		if(navType==1){
			//to product
			proType = getIntent().getIntExtra("proType", 1);
			destination = Shelf.getZone(proType);
		}
		else if(navType==2){
			//to cashier
//destination[0] = ;
//destination[1] = ;
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
			//Bluetooth is on, then start connecting to the service
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
			        	  id = new String[blist.size()];
			        	  for(int i=0;i<blist.size();i++){
			        		  beacon = blist.get(i);
				        	  Log.i("----------------", "beacon no."+i);
				        	  Log.i("----------------", "uuid"+beacon.getProximityUUID());
				        	  id[i] = beacon.getProximityUUID();
				        	  dis[i] = calculateAccuracy(beacon.getMeasuredPower(), beacon.getRssi());
			        	  }
			        	  //get 3 closest beacons and setup their latitudes and longitudes
			        	  threeClosest(getDis());
			        	  //calculate the position of the user
			        	  userPo = getLocationWithTrilateration(b1, b2, b3, closeDis[0], closeDis[1], closeDis[2]);
			        	  
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
		Log.i(">>>>>>>>>>closest 1", ""+temp[1]);
		Log.i(">>>>>>>>>>closest 1", ""+temp[2]);
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
		
//-------------------------------getLat/Long from DB
		
		//setLat/Long of 3 closest
		b1.setLatitude(latitude1);
		b1.setLongitude(longitude1);
		b2.setLatitude(latitude2);
		b2.setLongitude(longitude2);
		b3.setLatitude(latitude3);
		b3.setLongitude(longitude3);
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


	public String[] getId() {
		return id;
	}


	public double[] getTemp() {
		return temp;
	}


	public double[] getCloseDis() {
		return closeDis;
	}


	public String[] getCloseID() {
		return closeID;
	}

	public static Location getUserPo() {
		return userPo;
	}


	public Location[] getDestination() {
		return destination;
	}


	public void setDestination(Location[] destination) {
		this.destination = destination;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.navi, menu);
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
