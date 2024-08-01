package com.stock.repositories;

import com.stock.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPurchaseRepo extends JpaRepository<Purchase,Integer> {
    @Query(nativeQuery = true, value = "select * from purchase order by purchase_id desc limit 1")
    Purchase getLastPurchaseDetails();
    List<Purchase> getPurchasesByPurchaseId(Integer id);
    @Query(nativeQuery = true, value = "select SUM(`total_purchase_quantity`) from purchase")
    Integer getTotalOverallPurchaseQty();
    @Query(nativeQuery = true, value = "select SUM(`total_purchase_amount`) from purchase")
    Double getTotalOverallPurchaseAmount();
}
