package com.stock.service;

import com.stock.entity.SaleProduct;
import com.stock.entity.dtos.SaleDto;
import com.stock.entity.dtos.SaleProductDto;

import java.util.List;

public interface SaleProductService {
    List<SaleProductDto> getAllSaleProducts();
}
