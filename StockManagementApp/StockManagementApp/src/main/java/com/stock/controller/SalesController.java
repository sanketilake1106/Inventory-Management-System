package com.stock.controller;

import com.stock.entity.Sale;
import com.stock.entity.dtos.batchDtos.BatchSaleDto;
import com.stock.service.ISalesService;
import com.stock.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private ISalesService service;
    @Autowired
    private SaleProductService saleProductService;

    @PostMapping("/saleStock")
    public ResponseEntity<?> saleStock(@RequestBody BatchSaleDto batchSaleDto){
        return ResponseEntity.ok(service.addBatchSaleDto(batchSaleDto));
    }

    @GetMapping("/getAllSalesProduct")
    public ResponseEntity<?> getAllSalesProduct(){
        return ResponseEntity.ok(saleProductService.getAllSaleProducts());
    }


    @GetMapping("/getAllSaleProductByCustomerId/{id}")
    public ResponseEntity<?> getAllSaleProductByCustomerId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getAllSaleProductByCustomerId(id));
    }

    @GetMapping("/getTotalSaleByDate/{date}")
    public ResponseEntity<?> getTotalSaleByDate(@PathVariable String date){
        return ResponseEntity.ok(service.getTotalSaleQtyByDate(date));
    }

    @GetMapping("/getTotalOverallSale")
    public ResponseEntity<?> getTotalOverallSale(){
        return ResponseEntity.ok(service.getTotalOverallSaleQty());
    }
    @GetMapping("/getTotalSaleAmount")
    public ResponseEntity<?> getTotalSaleAmount(){
        return ResponseEntity.ok(service.getTotalSaleAmount());
    }

}
