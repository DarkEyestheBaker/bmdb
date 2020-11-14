package com.prs.business;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LineItem {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int requestId;
	private int productId;
	private int quantity;

	private LineItem() {
		super();
}
public LineItem(int id, int requestId, int productId, int quantity) {
	super();
	this.requestId = id;
	this.requestId = requestId;
	this.productId = productId;
	this.quantity = quantity;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRequestId() {
	return requestId;
}
public void setRequestId(int requestId) {
	this.requestId = requestId;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public List<LineItem> findAll() {
	return null;
}
public Optional<LineItem> findById(int id2) {
	return null;
}
public LineItem save(LineItem item) {
	return null;
}
public void deleteById(int id2) {
	
	
}

	
}

