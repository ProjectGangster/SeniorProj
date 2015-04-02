package com.example.shona;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

import android.location.Location;

public class Route {
private static List<Integer> route;

	
	public static List<Integer> genRoute(int current, int dest, int destNum){
/*
 * get from db
 */
//routeSize = ;
		route = new ArrayList<Integer>();
		route.add(current);
/*
 * get Location from db
 */
		//route.add(Location from db);
		/*
		 * test
		 */
		int curr = current;// = LocationtoNearestPoint(current);
		String path = Route_HandleJSON.getRoute(curr, destNum);
		String []points = path.split(",");
		//Point p = new Point();
		//Location[] temp = p.getAllPoints();
		for(int i=0;i<points.length;i++){
			route.add(Integer.parseInt(points[i]));
		}
		return route;
	}
	
//	public static int LocationtoNearestPoint(Location l){
//		int i;
//		int min = 0;
//		Point p = new Point();
//		Double sum,val;
//		Double la,lo;
//		la = l.getLatitude();
//		lo = l.getLongitude();
//		sum = Math.pow(p.getPoint(0).getLatitude()-la,2.0) + Math.pow(p.getPoint(0).getLongitude()-lo,2.0);
//		for (int j = 1; j < p.size(); j++) {
//			val = Math.pow(p.getPoint(j).getLatitude()-la,2.0) + Math.pow(p.getPoint(j).getLongitude()-lo,2.0);
//			if(val<sum)
//			{
//				sum = val;
//				min = j;
//			}
//		}
//		return min;
//	}
//	
	//get all points around the given point
	public static int[] getAround(int row){
		return Point.allPoints[row];
	}
}

class Point{
	/*
	 * row = point, column = front,right,back and left orderly (referred to the picture)
	 * if there is no point in that direction, it will return default value.
	 */
	public static int[][] allPoints = new int[25][4];
	private int def = -1;//default value
	public Point(){
		for(int i=0;i<allPoints.length;i++){
			for(int j=0;j<allPoints[i].length;j++){
				allPoints[i][j] = def;
			}
		}
		//0
		allPoints[0][0] = 1;
		allPoints[0][3] = 7;
		//1
		allPoints[1][0] = 2;
		allPoints[1][2] = 0;
		allPoints[1][3] = 8;
		//2
		allPoints[2][0] = 2;
		allPoints[2][2] = 1;
		allPoints[2][3] = 23;
		//3
		allPoints[3][0] = 4;
		allPoints[3][2] = 2;
		allPoints[3][3] = 24;//check
		//4
		allPoints[4][0] = 5;
		allPoints[4][2] = 3;
		//5
		allPoints[5][0] = 6;
		allPoints[5][2] = 4;
		allPoints[5][3] = 22;
		//6
		allPoints[6][2] = 5;
		//7
		allPoints[7][0] = 8;
		allPoints[7][1] = 0;
		allPoints[7][3] = 11;
		//8
		allPoints[8][0] = 8;
		allPoints[8][1] = 0;
		allPoints[8][3] = 11;
		//9
		allPoints[9][0] = 10;
		allPoints[9][1] = 23;
		allPoints[9][2] = 8;
		allPoints[9][3] = 13;
		//10
		allPoints[10][1] = 24;
		allPoints[10][2] = 9;
		allPoints[10][3] = 14;
		//11
		allPoints[11][0] = 12;
		allPoints[11][1] = 7;
		allPoints[11][3] = 15;
		//12
		allPoints[12][1] = 8;
		//13
		allPoints[13][0] = 14;
		allPoints[13][1] = 9;
		allPoints[13][3] = 16;
		//14
		allPoints[14][1] = 10;
		allPoints[14][3] = 17;
		//15
		allPoints[15][0] = 16;
		allPoints[15][1] = 11;
		//16
		allPoints[16][0] = 17;
		allPoints[16][1] = 13;
		allPoints[16][2] = 15;
		//17
		allPoints[17][0] = 18;
		allPoints[17][1] = 14;
		allPoints[17][2] = 16;
		//18
		allPoints[18][0] = 19;
		allPoints[18][1] = 20;
		allPoints[18][2] = 17;
		//19
		allPoints[19][1] = 12;
		allPoints[19][2] = 18;
		//20
		allPoints[20][1] = 21;
		allPoints[20][3] = 18;
		//21
		allPoints[21][1] = 22;
		allPoints[21][3] = 20;
		//22
		allPoints[22][1] = 5;
		allPoints[22][2] = 24;
		allPoints[22][3] = 21;
		//23
		allPoints[23][0] = 24;
		allPoints[23][1] = 2;
		allPoints[23][3] = 9;
		//24
		allPoints[24][0] = 22;
		allPoints[24][1] = 3;
		allPoints[24][2] = 23;
		allPoints[24][3] = 10;
		
		/*point[0] = new Location("0");
		point[1] = new Location("1");
		point[2] = new Location("2");
		point[3] = new Location("3");
		point[4] = new Location("4");
		point[5] = new Location("5");
		point[6] = new Location("6");
		point[7] = new Location("7");
		point[8] = new Location("8");
		point[9] = new Location("9");
		point[10] = new Location("10");
		point[11] = new Location("11");
		point[12] = new Location("12");
		point[13] = new Location("13");
		point[14] = new Location("14");
		point[15] = new Location("15");
		point[16] = new Location("16");
		point[17] = new Location("17");
		point[18] = new Location("18");
		point[19] = new Location("19");
		point[20] = new Location("20");
		point[21] = new Location("21");
		point[22] = new Location("22");
		point[23] = new Location("23");
		//cashier
		point[0].setLatitude(13.735963893135763);
		point[0].setLongitude(100.5338692200375);
		//normal
		point[1].setLatitude(13.732084982383336);
		point[1].setLongitude(100.53049461500146);
		point[2].setLatitude(13.736088429440715);
		point[2].setLongitude(100.53377951430048);
		point[3].setLatitude(13.733866643163898);
		point[3].setLongitude(100.53200814336833);
		//buying
		point[4].setLatitude(13.735915110459304);
		point[4].setLongitude(100.53383946428124);
		point[5].setLatitude(13.736164388259626);
		point[5].setLongitude(100.53395530218307);
		point[6].setLatitude(13.735399518833363);
		point[6].setLongitude(100.53356864075757);
		//normal
		point[7].setLatitude(13.733473360125807);
		point[7].setLongitude(100.53168964163198);
		point[8].setLatitude(13.735954882589853);
		point[8].setLongitude(100.53385932939176);
		point[9].setLatitude(13.736076001090682);
		point[9].setLongitude(100.533846756537);
		//buying
		point[10].setLatitude(13.736149887567139);
		point[10].setLongitude(100.53403979176704);
		//normal
		point[11].setLatitude(13.735985937541104);
		point[11].setLongitude(100.53386821420912);
		//buying
		point[12].setLatitude(13.73350061005784);
		point[12].setLongitude(100.53172098925799);
		point[13].setLatitude(13.735974160967148);
		point[13].setLongitude(100.53386511290495);
		point[14].setLatitude(13.732722557101862);
		point[14].setLongitude(100.53104495976841);
		//normal
		point[15].setLatitude(13.73597546016214);
		point[15].setLongitude(100.53386033522014);
		point[16].setLatitude(13.73596837719944);
		point[16].setLongitude(100.53385212073499);
		point[17].setLatitude(13.735954253947115);
		point[17].setLongitude(100.53384709181313);
		point[18].setLatitude(13.735912637797869);
		point[18].setLongitude(100.53382831635003);
		point[19].setLatitude(13.735902453785515);
		point[19].setLongitude(100.53383602770094);
		//buying
		point[20].setLatitude(13.733718402259239);
		point[20].setLongitude(100.53192386132112);
		point[21].setLatitude(13.735892814596868);
		point[21].setLongitude(100.533836297707);
		//normal
		point[22].setLatitude(13.735893652787185);
		point[22].setLongitude(100.53383426750128);
		point[23].setLatitude(13.73588657007900);
		point[23].setLongitude(100.53383032800679);
		*/
	}
}


