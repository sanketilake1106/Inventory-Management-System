package com.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.stock.entity.dtos.batchDtos.BatchCustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.stock.repositories.ICustomerRepo;
import com.stock.entity.Customer;
import com.stock.service.ICustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerCustomServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepo repo;

	@Override
	public List<Customer> addCustomerDetails(BatchCustomerDto customerDto) {
		List<Customer> list = new ArrayList<>();
		customerDto.getCustomers().forEach(c->{
			Customer dto = new Customer();
			dto.setCustomerName(c.getCustomerName());
			dto.setCustomerEmail(c.getCustomerEmail());
			dto.setCustomerContact(c.getCustomerContact());
			dto.setCustomerAddress(c.getCustomerAddress());
			list.add(dto);
		});
			return repo.saveAll(list);
	}

	@Override
	public Customer fetchCustomerById(Integer customerId) {

		Optional<Customer> optional = repo.findById(customerId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Customer> fetchAllCustomers() {

		return repo.findAll();
	}

	@Override
	@Transactional
	@Modifying
	public String changeCustomer(Customer customer) {

		if (repo.existsById(customer.getCustomerId())) {
			repo.save(customer);
			return "customer updated..!";
		}

		return "customer not updated..!";
	}

	@Override
	@Transactional
	@Modifying
	public String removeCustomer(Integer customerId) {

		if (repo.existsById(customerId)) {
			repo.deleteById(customerId);
			return "customer deleted..!";
		}
		return "customer not deleted...!";
	}

	@Override
	public Customer isCustomerPresent(String email, String contact) {
		return repo.getCustomerByCustomerEmailAndCustomerContact(email,contact);
	}

	@Override
	public Customer getLastCustomer() {
		return repo.getLastCustomer();
	}

}
