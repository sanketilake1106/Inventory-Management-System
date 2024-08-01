package com.stock.entity.dtos.batchDtos;

import com.stock.entity.dtos.CustomerDto;

import java.util.List;
public class BatchCustomerDto {
    private List<CustomerDto> customers;

    public BatchCustomerDto(List<CustomerDto> customers) {
        this.customers = customers;
    }
    public BatchCustomerDto() { }
    public List<CustomerDto> getCustomers() {
        return customers;
    }
    public void setCustomers(List<CustomerDto> customers) {
        this.customers = customers;
    }
}
