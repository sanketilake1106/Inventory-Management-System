package com.stock.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String customerName;
	@Column(unique = true)
	private String customerEmail;
	@Column(unique = true)
	private String customerContact;
	private String customerAddress;
	@ManyToMany(mappedBy = "customers")
	@JsonIgnore
	private Set<Sale> sales;

}
