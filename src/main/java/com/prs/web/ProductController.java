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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.*;
import com.prs.db.*;

@CrossOrigin // Security related
@RestController // I am a Controller!
@RequestMapping("/product") // url search

public class ProductController {
	/*
	 * A controller will implement 5 CRUD methods (Test in this order): 
	 * 1) GET ALL
	 * 2) GET BY ID 
	 * 3) POST - Insert 
	 * 4) PUT - Update 
	 * 5) DELETE - delete
	 */
	@Autowired   			//Wires database to your controller
	private ProductRepo productRepo;
	
// GET ALL Products
@GetMapping("/")
public List<Product>getAll() {
	return productRepo.findAll();
}
	
	//GET Product BY ID
	@GetMapping("/{id}")
	public Optional<Product>getbyId(@PathVariable int id) {
				return productRepo.findById(id);
	}
	
	//ADD Product
	@PostMapping("/")
	public Product addProduct(@RequestBody Product p) {
		p = productRepo.save (p);
		return p;
	}
	// UPDATE a Product
	@PutMapping("/")
	public Product updateProduct(@RequestBody Product p) {
		p = productRepo.save(p);
		return p;
	}
	// DELETE Product by ID
	@DeleteMapping("/{id}")
	public Product deleteProduct(@PathVariable int id) {
		Optional<Product> p = productRepo.findById(id);
			if (p.isPresent()) {
				productRepo.deleteById(id);
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
}
			return p.get();
}
}


