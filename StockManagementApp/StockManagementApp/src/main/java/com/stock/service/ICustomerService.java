package com.stock.service;

import java.util.List;

import com.stock.entity.Customer;
import com.stock.entity.dtos.batchDtos.BatchCustomerDto;

public interface ICustomerService {

	List<Customer> addCustomerDetails(BatchCustomerDto customer);

	Customer fetchCustomerById(Integer customerId);

	List<Customer> fetchAllCustomers();

	String changeCustomer(Customer customer);

	String removeCustomer(Integer customerId);

	Customer isCustomerPresent(String email, String contact);
	Customer getLastCustomer();
}
