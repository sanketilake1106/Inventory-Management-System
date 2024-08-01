package com.stock.repositories;

import com.stock.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISupplierRepo extends JpaRepository<Supplier,Integer> {

    Supplier getSupplierBySupplierEmailAndSupplierContact(String email, String contact);

    List<Supplier> getSupplierBySupplierName(String supplierName);

    Optional<Supplier> getSupplierBySupplierContact(String contact);

    @Query(nativeQuery = true, value = "select * from supplier order by supplier_id desc limit 1")
    Supplier getLastSupplier();

}
