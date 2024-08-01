package com.stock.entity.dtos.batchDtos;

import com.stock.entity.Supplier;
import java.util.List;


public class BatchSupplierDto {
    private List<Supplier> suppliers;

    public BatchSupplierDto(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public BatchSupplierDto() {
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
