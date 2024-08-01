package com.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;
    private String stockDate;
    private Integer stockPurchaseQuantity;
    private Integer stockSaleQuantity;
    private Integer remainingQuantity;
    private Double stockTotalAmount;
    @OneToOne
    private Product product;

}
