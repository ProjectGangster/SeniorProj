package com.example.shona;

public class Shelf {
	//product type
	private int type = 1;
	private double shelfLat[];
	private double shelfLong[];
	
	public Shelf(int numType,double startLat,double startLong,double endLat,double endLong){
		this.type = numType;
		shelfLat = new double[numType+1];
		shelfLong = new double[numType+1];		
		shelfLat[0] = startLat;
		shelfLong[0] = startLong;
		shelfLat[numType] = endLat;
		shelfLong[numType] = endLong;
	}
	
	//add product type into shelf
	public void addTypeLatLong(int numType,double endLat,double endLong){
		shelfLat[numType-1] = endLat;
		shelfLong[numType-1] = endLong;
	}
	
	public double[][] getTypeZone(int numType){
		double position[][] = new double[2][2];
		if(getType()==1){
			//if the shelf has only 1 type of product
			position[0][0] = shelfLat[0];
			position[0][1] = shelfLong[0];
			position[1][0] = shelfLat[1];
			position[1][1] = shelfLong[1];
		}
		else if(numType<=getType()){
			position[0][0] = shelfLat[numType-1];
			position[0][1] = shelfLong[numType-1];
			position[1][0] = shelfLat[numType];
			position[1][1] = shelfLong[numType];
		}
		else{
			//not exist
			position[0][0] = -1;
			position[0][1] = -1;
			position[1][0] = -1;
			position[1][1] = -1;
		}
		return position;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double[] getShelfLat() {
		return shelfLat;
	}

	public void setShelfLat(double[] shelfLat) {
		this.shelfLat = shelfLat;
	}

	public double[] getShelfLong() {
		return shelfLong;
	}

	public void setShelfLong(double[] shelfLong) {
		this.shelfLong = shelfLong;
	}
	
}
