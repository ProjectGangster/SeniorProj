package com.example.shona;

import android.location.Location;

public class Route {
	private static Location[] route;
	private static int routeSize = 1;
	
	public static Location[] genRoute(Location current, Location dest){
/*
 * get from db
 */
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
}
