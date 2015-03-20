package com.example.shona;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener extends Activity implements LocationListener{
	public static double latitude = 0.0;
	public static double longitude = 0.0;
	public static double heading = 0.0;
	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		latitude = loc.getLatitude();
		longitude = loc.getLongitude();
		heading = loc.getBearing();
		setUserLocation(latitude, longitude);
		//setLocationText(latitude, longitude);
		//setHeadingText(heading);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "GSP is unable", Toast.LENGTH_LONG);
	}

	public static double getLatitude() {
		return latitude;
	}

	public static double getLongitude() {
		return longitude;
	}

	public static double getHeading() {
		return heading;
	}
	
	public static void setUserLocation(double lat, double lon){
		NaviActivity.userLoc.setLatitude(lat);
		NaviActivity.userLoc.setLongitude(lon);
	}
	public static void setDistanceText(double lat1, double lon1,double lat2, double lon2){
		float[] result = new float[3];
		Location.distanceBetween(lat1, lon1, lat2, lon2, result);
		NaviActivity.disValue.setText(result[0]+" meters");
		NaviActivity.headingValue.setText(result[1]+"");
	}
	/*public static void setLocationText(double lat, double lon){
		NaviActivity.disValue.setText(lat+"\n"+lon);
	}
	public static void setHeadingText(double head){
		NaviActivity.headingValue.setText(""+head);
	}
	*/
}
