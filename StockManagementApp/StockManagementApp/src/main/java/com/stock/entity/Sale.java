package com.stock.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;
    private Integer saleQuantity;
    private String saleDate;
    private Double totalSalesAmount;
    @ManyToMany
    private Set<Customer> customers;
    @ManyToMany(mappedBy = "sale")
    @JsonBackReference
    private List<SaleProduct> saleProducts;

}
