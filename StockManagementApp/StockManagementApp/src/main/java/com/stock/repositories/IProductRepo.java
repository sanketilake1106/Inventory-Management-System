package com.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stock.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer>{

    @Query(nativeQuery = true,value = "SELECT * FROM `product` WHERE product_id=(SELECT MAX(product_id) FROM `product`)")
    Optional<Product> findLastProduct();
    List<Product> getProductsByProductCategory(String category);
    Product getProductByProductHSNNo(String productHSNNo);

    Product getProductByProductId(Integer id);
}
