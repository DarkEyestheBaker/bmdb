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

import com.prs.business.Product;
import com.prs.business.Vendor;
import com.prs.db.VendorRepo;

@CrossOrigin 									// Security related
@RestController 								// I am a Controller!
@RequestMapping("/vendor")  	// url search

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
	private List<Vendor> getAll() {
		return vendorRepo.findAll();
	}
	//GET Product BY ID
	@GetMapping("/{id}")
	public Optional<Vendor>getbyId(@PathVariable int id) {
				return vendorRepo.findById(id);
	}
	
	//ADD Product
	@PostMapping("/")
	public Vendor addVendor(@RequestBody Vendor v) {
		v = vendorRepo.save (v);
		return v;
	}
	// UPDATE a Product
	@PutMapping("/")
	public Product updateMovie(@RequestBody Product p) {
		v = vendorRepo.save(v);
		return p;
	}
	// DELETE Product by ID
	@DeleteMapping("/{id}")
	public Vendor deleteVendort(@PathVariable int id) {
		Optional<Vendor> v = vendorRepo.findById(id);
			if (v.isPresent()) {
				vendorRepo.deleteById(id);
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendor not found");
}
			return v.get();
}
}


}
