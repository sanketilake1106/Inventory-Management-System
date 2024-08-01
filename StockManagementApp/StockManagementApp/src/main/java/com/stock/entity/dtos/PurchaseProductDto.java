package com.stock.entity.dtos;

import com.stock.entity.Supplier;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseProductDto {
    private Integer purchaseProductId;
    private String productName;
    private Double productPrice;
    private String purchaseDate;
    private Integer purchaseId;
    private Integer totalPurchaseQuantity;
    private Double totalPurchaseAmount;
    private Supplier supplier;
}
