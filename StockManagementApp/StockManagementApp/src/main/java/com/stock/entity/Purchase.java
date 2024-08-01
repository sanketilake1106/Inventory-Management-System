package com.stock.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer purchaseId;
    private String purchaseDate;
    private Integer totalPurchaseQuantity;
    private Double totalPurchaseAmount;
    @ManyToMany
    private List<Supplier> supplier;
    @ManyToMany(mappedBy = "purchase")
    @JsonBackReference
    private List<PurchaseProduct> purchaseProduct;
}
