package com.prs.web;

import com.prs.*;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.*;
import com.prs.db.*;

@CrossOrigin 									// Security related
@RestController 								// I am a Controller!
@RequestMapping("/line-items") 	// url search

public class LineItemController {
	/*
	 * A controller will implement 5 CRUD methods (Test in this order): 
	 * 1) GET ALL
	 * 2) GET BY ID 
	 * 3) POST - Insert 
	 * 4) PUT - Update 
	 * 5) DELETE - delete
	 */
	@Autowired   			//Wires database to your controller
	private LineItemRepo lineItemRepo;
	@Autowired
	private RequestRepo requestRepo;
	
// GET ALL LineItems
@GetMapping("/")
public List<LineItem>getAllLineItems() {
	return lineItemRepo.findAll();
}
	
	//GET LineItem by ID
	@GetMapping("/{id}")
	public Optional<LineItem>getbyId(@PathVariable int id) {
				return lineItemRepo.findById(id);
	}
	
	//ADD LineItem and recalculate total
	@PostMapping("/")
	public LineItem addLineItem(@RequestBody LineItem li) {
		li = lineItemRepo.save(li);
		recalculateTotal(li);
		return li;
	}
	

	// PUT (update) a LineItem and recalculate
	@PutMapping("/")
	public LineItem updateLineItem(@RequestBody LineItem li) {
		li = lineItemRepo.save(li);
		return li;
	}
	// DELETE LineItem by ID and recalculate
	@DeleteMapping("/{id}")
	public LineItem deleteLineItem(@PathVariable int id) {
		Optional<LineItem> li = lineItemRepo.findById(id);
			if (li.isPresent()) {
				lineItemRepo.deleteById(id);
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "LineItem not found");
}
			return li.get();
}
	// Get all LineItems by Request
	@GetMapping("/request-lines/{id}")
	public List<LineItem> getAllByRequestID(@PathVariable int id) {
		return lineItemRepo.findByRequestId(id);
	}
	
	//Recalculate total
	public void recalculateTotal(LineItem li) {
		List<LineItem> lineItems = lineItemRepo.findByRequestId(li.getRequest().getId());
		
		double total = 0.0;
		for(LineItem lineItem: lineItems) {
			Product p = lineItem.getProduct();
			total +=(p.getPrice()* lineItem.getQuantity());
		}
		Request request = li.getRequest();
		request.setTotal(total);
		requestRepo.save(request);
	}
}


