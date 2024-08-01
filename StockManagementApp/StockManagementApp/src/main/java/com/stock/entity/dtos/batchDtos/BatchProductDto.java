package com.stock.entity.dtos.batchDtos;

import com.stock.entity.Product;
import com.stock.entity.Purchase;
import com.stock.entity.Supplier;
import com.stock.entity.dtos.batchDtos.helper.ProductQuantity;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchProductDto {
    private List<Product> products;
    private List<ProductQuantity> productQuantity;
    private Supplier supplier;
    private Integer totalPurchaseQuantity;
    private Double totalPurchaseAmount;
}
