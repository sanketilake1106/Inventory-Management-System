package com.stock.controller;

import java.util.List;

import com.stock.entity.dtos.batchDtos.BatchCustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stock.entity.Customer;
import com.stock.service.ICustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private ICustomerService service;

	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody BatchCustomerDto customerDto) {
		List<Customer> customers = service.addCustomerDetails(customerDto);
		return ResponseEntity.ok(customers);
	}

	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable("customerId") Integer customerId) {
		Customer customer = service.fetchCustomerById(customerId);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/getCustomers")
	public ResponseEntity<?> getAllCustomers() {
		List<Customer> customers = service.fetchAllCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@PutMapping("/updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		String msg = service.changeCustomer(customer);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Integer customerId) {
		String msg = service.removeCustomer(customerId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/getLastCustomer")
	public ResponseEntity<?> getLastCustomer(){
		Customer customer = service.getLastCustomer();
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}

}
