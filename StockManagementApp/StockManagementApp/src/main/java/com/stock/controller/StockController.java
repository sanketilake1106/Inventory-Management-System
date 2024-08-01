package com.stock.controller;

import com.stock.entity.Stock;
import com.stock.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stock.service.IStockService;

@RestController
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockController {

	@Autowired
	private IStockService service;


    @GetMapping("/getStockByProductId/{id}")
    public ResponseEntity<?> getStockByProductId(@PathVariable("id") Integer id){
        Stock stockByProductId = service.getStockByProductId(id);
        return ResponseEntity.ok(stockByProductId);
    }
    @GetMapping("/getTotalStockQty")
    public ResponseEntity<?> getTotalStockQty(){
        return ResponseEntity.ok(service.getTotalOverallStockQty());
    }

    @GetMapping("/getTotalStockAmount")
    public ResponseEntity<?> getTotalStockAmount(){
        return ResponseEntity.ok(service.getTotalStockAmount());
    }


}
