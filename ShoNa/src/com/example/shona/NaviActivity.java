package com.example.shona;

import java.util.Arrays;
import java.util.List;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class NaviActivity extends Activity{
	/*
	 * layout text view
	 */
	private TextView destText;
	public static TextView destName;
	private TextView disText;
	public static TextView disValue;
	private TextView headingText;
	public static TextView headingValue;
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
	private double closest = 0.0;
	private int now = 0;//point number
	

	//user's position
	public int userPos = -1;//default value

	/*
	 * intent
	 */
	private Intent intentToBC = new Intent(NaviActivity.this,BarcodeScanActivity.class);
	protected int bc = 555;
	
	//navigation type: 0=default, 1=to product, 2=to cashier
	private int navType = 0;
	//destination
	private int destPos = -2;
	private int destNum;
	//product
	private int proType = 1;
	//navigation route
	private List<Integer> navRoute = null;

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
		
		//init location services
		/*lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ll = new MyLocationListener();		
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
		*/

		//get navigation type
		navType = getIntent().getIntExtra("navType", 0);
		//check navigation type
		if(navType==1){
			//to product
			destNum = getIntent().getIntExtra("proType", 1);
/*
 * send product type to db to get product type's name
 */
			//destLoc =ProductList_HandleJSON.getTypeLocation(proType);
			
		}
		else if(navType==2){
			//to cashier
			destName.setText("Cashier");
			destName.setContentDescription("Cashier");
			//cashier location
			destPos = 0;
			destNum = 0;
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
			//connectToService();
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
				        	  if((""+beacon.getMinor()).equals("45677")){
				        		  Log.i("----------------", "blueburry 1");
				        	  }
				        	  else if((""+beacon.getMinor()).equals("9327")){
				        		  Log.i("----------------", "mint 1");
				        	  }
				        	  else if((""+beacon.getMinor()).equals("29827")){
				        		  Log.i("----------------", "mint 2");
				        	  }
				        	  else if((""+beacon.getMinor()).equals("59895")){
				        		  Log.i("----------------", "ice 2");
				        	  }
				        	  else if((""+beacon.getMinor()).equals("19552")){
				        		  Log.i("----------------", "blue 2");
				        	  }
				        	  id[i] = beacon.getMinor();
				        	  dis[i] = calculateAccuracy(beacon.getMeasuredPower(), beacon.getRssi());
				        	  //get the nearest beacon
				        	  getClosest(getDis());
			        	  }//end for
			        	  navigation(userPos, destPos,destNum);
			          }//end runnable
			        });//end runOnUiThread
				}//end beaconDiscover
			});//end setRanging
			//navigation(getUserPo(), getDestination(), getDestNum());
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
		 //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
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
	protected void getClosest(double[] dist){
		temp = dist;
		//sort to get the nearest
		Arrays.sort(temp);
		Log.i(">>>>>>>>>>closest 1", ""+temp[0]);
		closest = temp[0];
		for(int i=0;i<dist.length;i++){
			//find the nearest beacons' id
			if(closest==dist[i]){
				now = getId()[i];
			}
		}//end dist
		
	}
	
	private void navigation(int current, int dest, int destNum){
		//generate route to destination
		navRoute = Route.genRoute(current, dest, destNum);
		int next = -99;
		int[] around = new int[4];
		while(!navRoute.equals(null)){
			//navigate
			for(int i =0;i<navRoute.size();i++){
				//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
				/*Log.i("USERLOC", getUserPo().getLatitude()+","+getUserPo().getLongitude());
				Log.i("NavRouteLoc", "no: "+i+" = "+navRoute.get(i).getLatitude()+","+navRoute.get(i).getLongitude());
				Log.i("Routeeee","distance : "+getDistance(getUserPo(), navRoute.get(i)));
				Log.i("Routeeee","heading : "+getHeading(getUserPo(), navRoute.get(i)));
				//ll.setDistanceText(getUserPo().getLatitude(), getUserPo().getLongitude(), navRoute.get(i).getLatitude(), navRoute.get(i).getLongitude());
				*/
				next = navRoute.get(i);
				Log.i("SHOW NAV", "next point is"+ next);
				around = Route.getAround(getNow());
				while(closest>0.2){
					if(getNow()==next){//0
						//right way
						headingValue.setText("go straight");
						disValue.setText(closest+" meters");
					}
					else{
						//wrong way
						if(getNow()==around[1] && next==around[2]){
							headingValue.setText("please turn right");
						}
						else if(getNow()==around[2] && next==around[3]){
							headingValue.setText("please turn right");
						}
						else if (getNow()==around[3] && next==around[0]){
							headingValue.setText("please turn right");
						}
						else if (getNow()==around[0] && next==around[1]){
							headingValue.setText("please turn right");
						}
						else if(getNow()==around[1] && next==around[0]){
							headingValue.setText("please turn left");
						}
						else if(getNow()==around[2] && next==around[1]){
							headingValue.setText("please turn left");
						}
						else if(getNow()==around[3] && next==around[2]){
							headingValue.setText("please turn left");
						}
						else if(getNow()==around[0] && next==around[1]){
							headingValue.setText("please turn left");
						}
						else if(getNow()==around[1] && next==around[3]){
							headingValue.setText("please turn back");
						}
						else if(getNow()==around[2] && next==around[0]){
							headingValue.setText("please turn back");
						}
						else if(getNow()==around[3] && next==around[1]){
							headingValue.setText("please turn back");
						}
						else if(getNow()==around[0] && next==around[2]){
							headingValue.setText("please turn back");
						}

						disValue.setText("WRONG WAY!!");
					}
				}
				//arrived a point in the route and move on the next point
				navRoute.remove(i);
			}
		}//approaching the destination
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

	
	public double getClosest() {
		return closest;
	}


	public int getNow() {
		return now;
	}


	public int getDestNum(){
		return destNum;
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
