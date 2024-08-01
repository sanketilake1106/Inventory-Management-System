package com.stock.entity.dtos;

import com.stock.entity.Customer;
import lombok.Data;

@Data
public class SaleProductDto {
    private Integer id;
    private String saleProductName;
    private Integer saleProductQty;
    private Double productUnitPrice;
    private String saleProductDate;
    private Customer customer;
    private Double saleProductAmount;
}
