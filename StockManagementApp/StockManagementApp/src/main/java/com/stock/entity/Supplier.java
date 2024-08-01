package com.stock.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;
    private String supplierName;
    @Column(unique = true)
    private String supplierEmail;
    @Column(unique = true)
    private String supplierContact;
    private String supplierAddress;
}
