package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/requests") // url search
public class RequestController {

	/*
	 * A controller will implement 5 CRUD methods (Test in this order): 
	 * 1) GET ALL
	 * 2) GET BY ID 
	 * 3) POST - Insert 
	 * 4) PUT - Update 
	 * 5) DELETE - delete
	 */
	@Autowired // Wires database to your controller
	private RequestRepo requestRepo;

// GET ALL Requests
	@GetMapping("/")
	public List<Request> getAllRequests() {
		return requestRepo.findAll();
	}

	// GET Request by ID
	@GetMapping("/{id}")
	public Optional<Request> getRequestById(@PathVariable int id) {
	
		return requestRepo.findById(id);
	}

	// Review requests by ID
	@GetMapping("/requests/review/{id}")
	public List<Request> getRequestByIdAndStatus(@PathVariable int id) {
		return  requestRepo.findAllById(id) ;
	}

	// POST (Add) Request
	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		if (r != null) {
			System.out.println("Request submitted successfully.");
			System.out.println("Total price = " + r.getTotal());
			return requestRepo.save(r);
		} else {
			System.out.println("No request submitted.");
			return null;
		}
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
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found.");
		}
		return r.get();
	}
	// Auto-Approve under $50
	@PutMapping("/review")
			public Request setRequestsApproval(@RequestBody Request r) {
		if(r.getTotal() >= 50.00) {
			r.setStatus("Review");
		}else {
			r.setStatus("Approved");
		}
		r.setSubmittedDate(java.time.LocalDateTime.now());
		r = requestRepo.save(r);
		return r;
	}

	// Approve Request over $50
	@PutMapping("/approve")
	public Request approveRequest(@RequestBody Request r) {
		r.setStatus("Approved");
		r = requestRepo.save(r);
		return r;
	}
	// Rejected request
	@PutMapping("/reject")
			public Request rejectRequest(@RequestBody Request r) {
		r.setStatus("Rejected");
		r = requestRepo.save(r);
		return r;
	}
}
