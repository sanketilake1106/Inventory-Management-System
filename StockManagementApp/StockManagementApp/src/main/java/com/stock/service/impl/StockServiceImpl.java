package com.stock.service.impl;

import com.stock.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.repositories.ICustomerRepo;
import com.stock.repositories.IProductRepo;
import com.stock.repositories.IStockRepo;
import com.stock.service.IStockService;

@Service
public class StockServiceImpl implements IStockService {

	@Autowired
	private IStockRepo repo;
	
	@Autowired
	private IProductRepo prepo;
	
	@Autowired
	private ICustomerRepo crepo;

	@Override
	public Stock getStockByProductId(Integer id) {
		return repo.getStockByProductProductId(id);
	}

	@Override
	public Integer getTotalOverallStockQty() {
		return repo.getTotalOverallStockQty();
	}

	@Override
	public Double getTotalStockAmount() {
		return repo.getTotalStockAmount();
	}

}
