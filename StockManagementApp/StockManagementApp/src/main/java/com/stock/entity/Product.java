package com.stock.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;
import java.util.List;
import java.util.Set;


@Setter
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	@Column(unique = true)
	private String productHSNNo;
	private String productName;
	@Column(columnDefinition = "LONGTEXT", length = 100000)
	private String productDescription;
	private String productCategory;
	private Double productCost;
	private String productDate;

	@ManyToMany(mappedBy = "product")
	@JsonIgnore
	private List<PurchaseProduct> purchaseProduct;

	@ManyToMany(mappedBy = "product")
	@JsonIgnore
	private List<SaleProduct> saleProduct;

}
