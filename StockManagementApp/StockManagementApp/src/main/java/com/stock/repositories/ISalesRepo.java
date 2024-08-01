package com.stock.repositories;

import com.stock.entity.Customer;
import com.stock.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ISalesRepo extends JpaRepository<Sale,Integer> {
    List<Sale> getAllByCustomers(Set<Customer> customers);
    @Query(nativeQuery = true, value = "select SUM(`sale_quantity`) from sale where `sale_date`=?")
    Integer getTotalSaleByDate(String date);

    @Query(nativeQuery = true, value = "select SUM(`sale_quantity`) from sale")
    Integer getTotalOverallSale();

    @Query(nativeQuery = true, value = "select SUM(`total_sales_amount`) from sale")
    Double getTotalSaleAmount();
}
