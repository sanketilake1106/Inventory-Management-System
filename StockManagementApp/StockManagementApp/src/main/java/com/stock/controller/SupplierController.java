package com.stock.controller;

import com.stock.entity.Supplier;
import com.stock.entity.dtos.batchDtos.BatchSupplierDto;
import com.stock.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private ISupplierService service;

    @PostMapping("/addSupplier")
    public ResponseEntity<?> addSupplier(@RequestBody BatchSupplierDto batchSupplierDto){
        return  ResponseEntity.ok(service.addSupplierDetails(batchSupplierDto));
    }

    @GetMapping("/getAllSuppliers")
    public ResponseEntity<?> getAllSuppliers(){
        return ResponseEntity.ok(service.fetchAllSuppliers());
    }

    @PostMapping("/getSuppliersByName")
    public ResponseEntity<?> getSuppliersByName(@RequestBody Supplier supplier){
        return ResponseEntity.ok(service.fetchAllSuppliersByName(supplier.getSupplierName()));
    }

    @PostMapping("/getSupplierByContact")
    public ResponseEntity<?> getSupplierByContact(@RequestBody Supplier supplier){
        return ResponseEntity.ok(service.fetchSupplierByContact(supplier.getSupplierContact()));
    }

    @GetMapping("/getLastSupplier")
    public ResponseEntity<?> getLastSupplier(){
        return ResponseEntity.ok(service.getLastSupplierInformation());
    }
}
