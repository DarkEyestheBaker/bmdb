package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.prs.*;
import com.prs.business.LineItem;
import com.prs.business.Request;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;

public class RequestController {
	/*
	 * A controller will implement 5 CRUD methods (Test in this order): 
	 * 1) GET ALL
	 * 2) GET BY ID 
	 * 3) POST - Insert 
	 * 4) PUT - Update 
	 * 5) DELETE - delete
	 */
	@Autowired   			//Wires database to your controller
	private RequestRepo requestRepo;
	
// GET ALL Requests
@GetMapping("/requests")
public List<Request>getAllRequests() {
	return requestRepo.findAll();
}
	
	//GET Request by ID
	@GetMapping("/{id}")
	public Optional<Request>getbyId(@PathVariable int id) {
				return requestRepo.findById(id);
	}
	
	//ADD Request
	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		r = requestRepo.save (r);
		return r;
	}
	private void recalculateTotal(Request r) {
		
	}

	// PUT (update) a Request and recalculate
	@PutMapping("/")
	public Request updateRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}
	// DELETE Request by ID 
	@DeleteMapping("/{id}")
	public Request deleteRequest(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);
			if (r.isPresent()) {
				requestRepo.deleteById(id);
			}else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found");
}
			return r.get();
}
}
