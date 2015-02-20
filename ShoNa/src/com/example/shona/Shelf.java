package com.example.shona;

import android.location.Location;

public class Shelf {
	//product numType
	private static int numType = 1;
	//shelf location
	private Location shelfStartLocation = new Location("shelf start Location");
	private Location shelfEndLocation = new Location("shelf end Location");
	//zone location
	private static Location[] zoneStart;
	private static Location[] zone = new Location[2];
	
	@SuppressWarnings("static-access")
	public Shelf(int numType,double startLat,double startLong,double endLat,double endLong){
		this.numType = numType;
		shelfStartLocation.setLatitude(startLat);
		shelfStartLocation.setLongitude(startLong);
		shelfEndLocation.setLatitude(endLat);
		shelfEndLocation.setLongitude(endLong);
		zoneStart = new Location[numType];
		zoneStart[0] = shelfStartLocation;
		zoneStart[numType] = shelfEndLocation;
	}
	
	//add product numType into shelf
	public void addTypeLatLong(int numType,double endLat,double endLong){
		zoneStart[numType].setLatitude(endLat);
		zoneStart[numType].setLongitude(endLong);
	}
	
	public static Location[] getZone(int numType){
		//check if given parameter is out of bound
		if(numType<=getType()){
			zone[0] = zoneStart[numType-1];//start
			zone[1] = zoneStart[numType];//end
		}
		return zone;
	}

	public static int getType() {
		return numType;
	}

	@SuppressWarnings("static-access")
	public void setType(int type) {
		this.numType = type;
	}

	
}
