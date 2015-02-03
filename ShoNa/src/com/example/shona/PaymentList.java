package com.example.shona;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class PaymentList {
	private List<Product> list;
	private Product product;
	private String[] showList;
	private String temp = "";
	private double sum = 0.0;
	//constructor
	public PaymentList(){
		list = new ArrayList<Product>();
		showList = new String[]{"Name 	Price","Total Price		0.0"};
	}
	public void addList(int id){
		//loop to check if id matches from DB
		---if(product.getId()==id){
			list.add(product);
			//temp for total price
			showList[showList.length-1] = ""+product.getName()+"	"+product.getPrice();
			updateTotalPrice();
		}
	}//end
	public String[] getShowList(){
		//show name and price of products in the list
		for(int i=0;i<showList.length;i++){
			Log.i("PAYMENT-----", ""+showList[0]);
		}
		return showList;
	}//end showList
	private void updateTotalPrice(){
		sum = 0.0;
		for(int i=0;i<list.size();i++){
			sum += list.get(i).getPrice();
		}
		showList[showList.length] = "Total Price 	"+sum;
	}
}
