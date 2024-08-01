package com.stock.service;

import com.stock.entity.Purchase;
import com.stock.entity.PurchaseProduct;
import com.stock.entity.dtos.PurchaseProductDto;

import java.util.List;

public interface IPurchaseProductService {
    List<PurchaseProductDto> getAllPurchaseProducts();
}
