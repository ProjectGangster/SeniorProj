package com.example.shona;

public class Product {

<<<<<<< HEAD
//	protected int id;
//	protected String name;
//	protected double price;
//	protected String description;
//	
//	public Product(int id, String name, Double price, String description) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.price = price;
//		this.description = description;
//	}
//	

=======
>>>>>>> 84dcbe6848eb86e676bff5ffac76ad78e377feb8
	private int id = 0;
	private String name = "";
	private String brand = "";
	private double volume = 0.0;
	private double price = 0.0;
	private String description = "n/a";
	public Product(int id,String n,String b,double v, double p, String des){
		this.id = id;
		this.name = n;
		//this.brand = b;
		//this.volume = v;
		this.price = p;
		this.description = des;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
}
