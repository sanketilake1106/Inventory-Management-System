package com.stock.service;

import com.stock.entity.Supplier;
import com.stock.entity.dtos.batchDtos.BatchSupplierDto;
import java.util.List;

public interface ISupplierService {

    List<Supplier> addSupplierDetails(BatchSupplierDto supplierDto);

    List<Supplier> fetchAllSuppliers();

    List<Supplier> fetchAllSuppliersByName(String name);

    Supplier fetchSupplierByContact(String supplierContact);

    Supplier getLastSupplierInformation();
}
