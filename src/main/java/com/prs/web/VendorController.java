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

@CrossOrigin 									// Security related
@RestController 								// I am a Controller!
@RequestMapping("/vendors")  	// url search

public class VendorController {
	/*
	 * A controller will implement 5 CRUD methods (Test in this order): 
	 * 1) GET ALL
	 * 2) GET BY ID 
	 * 3) POST - Insert 
	 * 4) PUT - Update 
	 * 5) DELETE - delete
	 */
	@Autowired   			//Wires database to your controller
	private VendorRepo vendorRepo;
	
	//GET ALL Vendors
	@GetMapping("/")
	private List<Vendor> getAllVendors() {
		return vendorRepo.findAll();
	}
	//GET Vendor BY ID
	@GetMapping("/{id}")
	public Optional<Vendor>getbyId(@PathVariable int id) {
				return vendorRepo.findById(id);
	}
	
	//ADD Vendor
	@PostMapping("/")
	public Vendor addVendor(@RequestBody Vendor v) {
		v = vendorRepo.save (v);
		return v;
	}
	// UPDATE a Vendor
	@PutMapping("/")
	public Vendor updateVendor(@RequestBody Vendor v) {
		v = vendorRepo.save(v);
		return v;
	}
	// DELETE Vendor by ID
	@DeleteMapping("/{id}")
	public Vendor deleteVendor(@PathVariable int id) {
		Optional<Vendor> v = vendorRepo.findById(id);
		
		// isPresent will return true if a Vendor was found
			if (v.isPresent()) {
				vendorRepo.deleteById(id);
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
}
			return v.get();
}
	
}