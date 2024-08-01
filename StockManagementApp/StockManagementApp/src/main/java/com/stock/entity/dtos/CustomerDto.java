package com.stock.entity.dtos;

import lombok.Data;
@Data
public class CustomerDto {
    private Integer customerId;
    private String customerName;
    private String customerEmail;
    private String customerContact;
    private String customerAddress;
}
