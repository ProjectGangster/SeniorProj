package com.example.shona;

public class Product {

	private String id = "0";
	private String name = "";
	private String brand = "";
	private double volume = 0.0;
	private double price = 0.0;
	private String description = "n/a";
	private int shelf = 0;
	private double x,y = 0;
	public Product(String id,String n,String b,double v, double p, String des,int s,double x,double y){
		this.id = id;
		this.name = n;
		this.brand = b;
		this.volume = v;
		this.price = p;
		this.description = des;
		this.shelf = s;
		this.x = x;
		this.y = y;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getShelf() {
		return shelf;
	}
	public void setShelf(int shelf) {
		this.shelf = shelf;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
}
