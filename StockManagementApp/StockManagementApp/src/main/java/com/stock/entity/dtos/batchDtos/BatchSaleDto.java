package com.stock.entity.dtos.batchDtos;

import com.stock.entity.Customer;
import com.stock.entity.Product;
import com.stock.entity.dtos.batchDtos.helper.ProductQuantity;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchSaleDto {
    private List<Product> products;
    private List<ProductQuantity> productQuantity;
    private Customer customer;
    private Integer totalSaleQuantity;
    private Double totalSaleAmount;
}
