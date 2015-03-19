package com.example.shona;

import android.location.Location;

public class Route {
	private static Location[] route;
	private static int routeSize = 1;
	
	
	
	public static Location[] genRoute(Location current, Location dest){
/*
 * get from db
 */
	String path = Route_HandleJSON.getBeaconDetail(current, dest);

//routeSize = ;
		route = new Location[routeSize];
		route[0] = current;
		route[routeSize-1] = dest;
/*
 * get Location from db
 */
		for(int i=1;i<(routeSize-1);i++){
//route[i] = from db;
		}
		return route;
	}

	//Point to Location
	public Location PtoL(int i){
		Location l = new Location("l");
		Location point[] = new Location[29];

		//set Latitute and Longitute of each point
		point[0].setLatitude(1.1);
		point[0].setLongitude(1.1);
		point[1].setLatitude(1.1);
		point[1].setLongitude(1.1);
		point[2].setLatitude(1.1);
		point[2].setLongitude(1.1);
		point[3].setLatitude(1.1);
		point[3].setLongitude(1.1);
		point[4].setLatitude(1.1);
		point[4].setLongitude(1.1);
		point[5].setLatitude(1.1);
		point[5].setLongitude(1.1);
		point[6].setLatitude(1.1);
		point[6].setLongitude(1.1);
		point[7].setLatitude(1.1);
		point[7].setLongitude(1.1);
		point[8].setLatitude(1.1);
		point[8].setLongitude(1.1);
		point[9].setLatitude(1.1);
		point[9].setLongitude(1.1);
		point[10].setLatitude(1.1);
		point[10].setLongitude(1.1);
		point[11].setLatitude(1.1);
		point[11].setLongitude(1.1);
		point[12].setLatitude(1.1);
		point[12].setLongitude(1.1);
		point[13].setLatitude(1.1);
		point[13].setLongitude(1.1);
		point[14].setLatitude(1.1);
		point[14].setLongitude(1.1);
		point[15].setLatitude(1.1);
		point[15].setLongitude(1.1);
		point[16].setLatitude(1.1);
		point[16].setLongitude(1.1);
		point[17].setLatitude(1.1);
		point[17].setLongitude(1.1);
		point[18].setLatitude(1.1);
		point[18].setLongitude(1.1);
		point[19].setLatitude(1.1);
		point[19].setLongitude(1.1);
		point[20].setLatitude(1.1);
		point[20].setLongitude(1.1);
		point[21].setLatitude(1.1);
		point[21].setLongitude(1.1);
		point[22].setLatitude(1.1);
		point[22].setLongitude(1.1);
		point[23].setLatitude(1.1);
		point[23].setLongitude(1.1);
		point[24].setLatitude(1.1);
		point[24].setLongitude(1.1);
		point[25].setLatitude(1.1);
		point[25].setLongitude(1.1);
		point[26].setLatitude(1.1);
		point[26].setLongitude(1.1);
		point[27].setLatitude(1.1);
		point[27].setLongitude(1.1);
		point[28].setLatitude(1.1);
		point[28].setLongitude(1.1);
		
		
		return point[i];
	}
	
	//Location to nearestPoint
	public int LtoP(){
		return 0;
	}
}


