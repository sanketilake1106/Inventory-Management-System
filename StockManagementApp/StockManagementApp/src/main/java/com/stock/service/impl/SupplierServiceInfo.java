package com.stock.service.impl;

import com.stock.repositories.ISupplierRepo;
import com.stock.entity.Supplier;
import com.stock.entity.dtos.batchDtos.BatchSupplierDto;
import com.stock.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceInfo implements ISupplierService {

    @Autowired
    private ISupplierRepo supplierRepo;

    @Override
    public List<Supplier> addSupplierDetails(BatchSupplierDto supplierDto) {
        List<Supplier> suppliers = new ArrayList<>();
        supplierDto.getSuppliers().forEach(supplier -> {
            Supplier sup = new Supplier();
            sup.setSupplierName(supplier.getSupplierName());
            sup.setSupplierEmail(supplier.getSupplierEmail());
            sup.setSupplierContact(supplier.getSupplierContact());
            sup.setSupplierAddress(supplier.getSupplierAddress());
            suppliers.add(sup);
        });
        return supplierRepo.saveAll(suppliers);
    }

    @Override
    public List<Supplier> fetchAllSuppliers() {
        return supplierRepo.findAll();
    }

    @Override
    public List<Supplier> fetchAllSuppliersByName(String name) {
        return supplierRepo.getSupplierBySupplierName(name);
    }

    @Override
    public Supplier fetchSupplierByContact(String supplierContact) {
        Optional<Supplier> optional = supplierRepo.getSupplierBySupplierContact(supplierContact);
        return optional.orElse(null);
    }

    @Override
    public Supplier getLastSupplierInformation() {
        return supplierRepo.getLastSupplier();
    }


}
