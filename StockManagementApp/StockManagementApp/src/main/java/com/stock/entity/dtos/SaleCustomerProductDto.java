package com.stock.entity.dtos;

import com.stock.entity.Product;
import com.stock.entity.Sale;
import com.stock.entity.SaleProduct;
import lombok.Data;

import java.util.List;

@Data
public class SaleCustomerProductDto {
    private List<Product> products;
    private Integer saleProductQty;
    private Double totalAmount;
}
