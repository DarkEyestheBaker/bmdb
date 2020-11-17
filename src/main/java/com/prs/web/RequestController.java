package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prs.db.*;
import com.prs.business.*;

@CrossOrigin           // Security related
@RestController      // I am a Controller!
@RequestMapping("/request")  //url search

public class RequestController {
	/*A controller will implement 5 CRUD methods (Test in this order):
	 * 1) GET ALL
	 * 2) GET BY ID
	 * 3) POST - Insert
	 * 4) PUT - Update
	 * 5) DELETE - delete
	 */
	@Autowired       // Wires database to your controller
	private RequestRepo requestRepo;
		
//	GET ALL Requests
	@GetMapping("/")
	public List<Request> getAll(@PathVariable int id, String login, String status) {
		return requestRepo.findAll();
	}
	
//	GET Request BY ID
	@GetMapping("/{id}")
	public Optional<Request> getbyId(@PathVariable int id) {
		return requestRepo.findById(id);
	}
	
//	ADD a Request
	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}
// UPDATE a Request
	@PutMapping("/")
	public Request updateRequest(@RequestBody Request r) {
	r = requestRepo.save(r);
	return r;
}
//DELETE a Request
@DeleteMapping("/{id}")
public Request deleteRequest(@PathVariable int id) {
	Optional<Request> r = requestRepo.findById(id);
	if (r.isPresent()) {
		requestRepo.deleteById(id);
	}else {
		System.out.println("Error - request not found for id " + id);
	}
	return r.get();
	}

}