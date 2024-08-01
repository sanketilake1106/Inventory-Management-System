package com.stock.entity.dtos;

import lombok.Data;

@Data
public class SupplierDto {
    private Integer supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierContact;
    private String supplierAddress;
}
