package com.stock.controller;

import com.stock.entity.dtos.batchDtos.BatchProductDto;
import com.stock.service.IPurchaseProductService;
import com.stock.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

      @Autowired
      private IPurchaseService service;

      @Autowired
      private IPurchaseProductService purchaseProductService;

      @PostMapping("/purchaseStock")
      public ResponseEntity<?> purchaseStock(@RequestBody BatchProductDto purchases){
            return ResponseEntity.ok(service.addPurchaseDetailsAndStock(purchases));
      }

      @GetMapping("/getLastPurchase")
      public ResponseEntity<?> getLastPurchase(){
            return ResponseEntity.ok(service.fetchLastPurchase());
      }

      @GetMapping("/getAllPurchaseProducts")
      public ResponseEntity<?> getAllPurchaseProducts(){
            return ResponseEntity.ok(purchaseProductService.getAllPurchaseProducts());
      }

      @GetMapping("/getPurchaseById/{id}")
      public ResponseEntity<?> getPurchaseById(@PathVariable Integer id){
            return ResponseEntity.ok(service.getPurchaseById(id));
      }

      @GetMapping("/getTotalPurchaseQty")
      public ResponseEntity<?> getTotalPurchaseQty(){
            return ResponseEntity.ok(service.getTotalOverallPurchaseQty());
      }

      @GetMapping("/getTotalPurchaseAmount")
      public ResponseEntity<?> getTotalPurchaseAmount(){
            return ResponseEntity.ok(service.getTotalOverallPurchaseAmount());
      }


}
