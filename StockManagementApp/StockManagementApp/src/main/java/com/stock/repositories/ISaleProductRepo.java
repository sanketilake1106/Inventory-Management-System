package com.stock.repositories;

import com.stock.entity.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleProductRepo extends JpaRepository<SaleProduct, Integer> {
}
