package com.prs.web;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prs.db.*;
import com.prs.business.*;

@CrossOrigin // Security related
@RestController // I am a Controller!
@RequestMapping("/item") // url search

public class LineItemController {
	/* A controller will implement 5 CRUD methods (Test in this order): 
	 * 1) GET ALL
	 * 2) GET BY ID 
	 * 3) POST - Insert 
	 * 4) PUT - Update 
	 * 5) DELETE - delete
	 */
@Autowired   			//Wires database to your controller
	private LineItemRepo lineItemRepo;

// GET ALL LineItems
@GetMapping("/")
public List<LineItem>getAll() {
	return lineItemRepo.findAll();
}
	
	//GET LineItem BY ID
	@GetMapping("/{id}")
	public Optional<LineItem>getbyId(@PathVariable int id) {
				return lineItemRepo.findById(id);
	}
	
	//ADD LineItem
	@PostMapping("/")
	public LineItem updateLineItem(@RequestBody LineItem item) {
		item = lineItemRepo.save(item);
		return item;
	}
	
	// DELETE LineItem by ID
	@DeleteMapping("/{id}")
	public LineItem deleteLineItem(@PathVariable int id) {
		Optional<LineItem> item = lineItemRepo.findById(id);
			if (item.isPresent()) {
				lineItemRepo.deleteById(id);
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
}
			return item.get();
}
}