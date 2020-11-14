package com.prs.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int vendorId;
	private String partNumber;
	private String name;
	private double price;
	private String unit;

public Product() {
	super();
}
 public Product(int vendorId, String partNumber, String name, double price, String unit) {
	 super();
	 this.vendorId = vendorId;
	 this.partNumber = partNumber;
	 this.name = name;
	 this.price = price;
	 this.unit = unit;
 }
public int getVendorId() {
	return vendorId;
}
public void setVendorId(int vendorId) {
	this.vendorId = vendorId;
}
public String getPartNumber() {
	return partNumber;
}
public void setPartNumber(String partNumber) {
	this.partNumber = partNumber;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
  
}
