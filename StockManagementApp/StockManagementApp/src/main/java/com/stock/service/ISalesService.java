package com.stock.service;

import com.stock.entity.Product;
import com.stock.entity.Sale;
import com.stock.entity.dtos.SaleCustomerProductDto;
import com.stock.entity.dtos.batchDtos.BatchSaleDto;

import java.util.List;

public interface ISalesService {
    Sale addBatchSaleDto(BatchSaleDto batchSaleDto);
    List<SaleCustomerProductDto> getAllSaleProductByCustomerId(Integer id);
    Integer getTotalSaleQtyByDate(String date);
    Integer getTotalOverallSaleQty();
    Double getTotalSaleAmount();
}
