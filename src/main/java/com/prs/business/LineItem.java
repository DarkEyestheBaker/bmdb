package com.prs.business;

import com.prs.*;

import java.util.*;

import javax.persistence.*;

@Entity
public class LineItem {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int requestID;
	private int productID;
	private int quantity;

	private LineItem() {
		super();
}
public LineItem(int id, int requestID, int productID, int quantity) {
	super();
	this.requestID = id;
	this.requestID = requestID;
	this.productID = productID;
	this.quantity = quantity;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRequestId() {
	return requestID;
}
public void setRequestId(int requestId) {
	this.requestID = requestId;
}
public int getProductId() {
	return productID;
}
public void setProductId(int productId) {
	this.productID = productId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

}