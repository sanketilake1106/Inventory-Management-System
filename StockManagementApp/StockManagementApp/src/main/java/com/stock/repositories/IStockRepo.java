package com.stock.repositories;

import com.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepo extends JpaRepository<Stock, Integer> {
    Stock getStockByProductProductId(Integer id);
    @Query(nativeQuery = true, value = "select SUM(remaining_quantity) from stock")
    Integer getTotalOverallStockQty();

    @Query(nativeQuery = true, value = "select SUM(stock_total_amount) from stock")
    Double getTotalStockAmount();
}
