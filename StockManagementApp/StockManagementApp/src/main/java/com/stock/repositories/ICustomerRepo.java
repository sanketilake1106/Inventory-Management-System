package com.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stock.entity.Customer;

@Repository
public interface ICustomerRepo extends JpaRepository<Customer, Integer> {
    Customer getCustomerByCustomerEmailAndCustomerContact(String email, String contact);
    Customer getCustomerByCustomerId(Integer id);
    Customer getCustomerByCustomerContact(String contact);
    @Query(nativeQuery = true, value = "SELECT * FROM customer WHERE customer_id =(SELECT MAX(customer_id) FROM customer)")
    Customer getLastCustomer();
}
