package edu.jhu.cs.oose.project.group14.ihungry.androidapp;

import java.awt.Image;

public class ListMenuItem {
	String title; 
	double rating;
	double price;
//	int quantity;
//	Image thumb_image;
//	public Button btn_quantity;

	public ListMenuItem(String title_in, double rating_in, double price_in){
		this.title = title_in;
		this.rating = rating_in;
		this.price = price_in;
	}
	
	public void setTitle(String title_in){
		this.title = title_in;
	}
	
	public void setRating(double rating_in){
		this.rating = rating_in;
	}
	
	public void setPrice(double price_in){
		this.price = price_in;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public double getRating(){
		return this.rating;
	}
	
	public double getPrice(){
		return this.price;
	}

}
