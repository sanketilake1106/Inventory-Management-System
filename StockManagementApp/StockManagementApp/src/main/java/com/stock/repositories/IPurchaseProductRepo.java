package com.stock.repositories;

import com.stock.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseProductRepo extends JpaRepository<PurchaseProduct,Integer> {


}
