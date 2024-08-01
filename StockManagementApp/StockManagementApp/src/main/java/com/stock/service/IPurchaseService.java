package com.stock.service;

import com.stock.entity.dtos.batchDtos.BatchProductDto;
import com.stock.entity.Purchase;

import java.util.List;

public interface IPurchaseService {
    Purchase addPurchaseDetailsAndStock(BatchProductDto batchProductDto);

    Purchase fetchLastPurchase();
    List<Purchase> getPurchaseById(Integer id);

    Integer getTotalOverallPurchaseQty();
    Double getTotalOverallPurchaseAmount();
}
