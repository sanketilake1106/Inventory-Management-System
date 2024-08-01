package com.stock.service;

import com.stock.entity.Stock;

public interface IStockService {
    Stock getStockByProductId(Integer id);
    Integer getTotalOverallStockQty();
    Double getTotalStockAmount();
}
