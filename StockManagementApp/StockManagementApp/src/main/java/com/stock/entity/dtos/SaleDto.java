package com.stock.entity.dtos;

import com.stock.entity.Customer;
import lombok.Data;

import java.util.Set;

@Data
public class SaleDto {
    private Integer salesId;
    private Integer saleQuantity;
    private String saleDate;
    private Set<Customer> customers;
    private Integer productId;
    private Double totalSalesAmount;
}
