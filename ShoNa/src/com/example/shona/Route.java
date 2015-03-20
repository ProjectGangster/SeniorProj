package com.example.shona;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

public class Route {
private static List<Location> route;
	
	public static List<Location> genRoute(Location current, Location dest){
/*
 * get from db
 */
//routeSize = ;
		route = new ArrayList<Location>();
		route.add(current);
/*
 * get Location from db
 */
		//route.add(Location from db);
		
		return route;
	}
		//set Latitute and Longitute of each point
		/*point[0].setLatitude(1.1);
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
		
	//current point
	int Cp = LocationtoNearestPoint(current);
	//End point
	int Ep = LocationtoNearestPoint(dest);
	String path = Route_HandleJSON.getBeaconDetail(Cp, Ep);

	String[] points = path.split(",");
	
	routeSize = points.length+2;
		route = new Location[routeSize];
		route[0] = current;
		route[1] = PtoL(Cp);
/*
 * get Location from db
 */
	/*
		for(int i=0;i<points.length;i++){
			route[i+2] = point[Integer.parseInt(points[i])];
//route[i] = from db;
		}
		return route;
	}
	*/
	/*
	//Location to nearestPoint
	public static int LocationtoNearestPoint(Location l){
		int i;
		int min = 0;
		Double sum,val;
		Double la,lo;
		la = l.getLatitude();
		lo = l.getLongitude();
		sum = Math.pow(point[0].getLatitude()-la,2.0) + Math.pow(point[0].getLongitude()-lo,2.0);
		for (int j = 1; j < point.length; j++) {
			val = Math.pow(point[j].getLatitude()-la,2.0) + Math.pow(point[j].getLongitude()-lo,2.0);
			if(val<sum)
			{
				sum = val;
				min = j;
			}
		}
		return min;
	}
	*/
}


