package com.stock.service.impl;

import com.stock.repositories.*;
import com.stock.entity.*;
import com.stock.entity.dtos.batchDtos.BatchProductDto;
import com.stock.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements  IPurchaseService{

    @Autowired
    private IPurchaseRepo purchaseRepo;

    @Autowired
    private IStockRepo stockRepo;

    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private ISupplierRepo supplierRepo;

    @Autowired
    private IPurchaseProductRepo purchaseProductRepo;

    @Override
    public Purchase addPurchaseDetailsAndStock(BatchProductDto batchProductDto){
        Integer supplierId = batchProductDto.getSupplier().getSupplierId();
        Double totalPurchaseAmount = batchProductDto.getTotalPurchaseAmount();
        Integer totalPurchaseQuantity = batchProductDto.getTotalPurchaseQuantity();
        Supplier supplier = supplierRepo.findById(supplierId).get();

        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(supplier);

        Purchase purchase = new Purchase();
        purchase.setTotalPurchaseQuantity(totalPurchaseQuantity);
        purchase.setTotalPurchaseAmount(totalPurchaseAmount);
        purchase.setSupplier(supplierList);
        purchase.setPurchaseDate(LocalDate.now().toString());
        Purchase p = purchaseRepo.save(purchase);

        batchProductDto.getProducts().forEach(product -> {
            PurchaseProduct pp = new PurchaseProduct();
            pp.setPurchase(List.of(p));
            pp.setProduct(List.of(product));
            purchaseProductRepo.save(pp);

            Product productByProductId =
                    productRepo.getProductByProductId(product.getProductId());

            Stock stock = new Stock();

            Stock stockByProductProductId = stockRepo.getStockByProductProductId(product.getProductId());
            if(stockByProductProductId==null){
                batchProductDto.getProductQuantity().forEach(q ->{
                    stock.setProduct(productByProductId);
                    stock.setStockDate(LocalDate.now().toString());
                    stock.setStockSaleQuantity(0);
                    stock.setStockPurchaseQuantity(q.getProductQty());
                    stock.setRemainingQuantity(q.getProductQty());
                    stock.setStockTotalAmount(productByProductId.getProductCost() * q.getProductQty());
                    stockRepo.save(stock);
                });
            }
            else{
                List<Stock> listStock = new ArrayList<>();
                batchProductDto.getProductQuantity().forEach(q ->{
                    stock.setStockId(stockByProductProductId.getStockId());
                    stock.setProduct(stockByProductProductId.getProduct());
                    stock.setStockDate(LocalDate.now().toString());
                    stock.setStockSaleQuantity(0);
                    stock.setStockPurchaseQuantity(stockByProductProductId.getStockPurchaseQuantity() + q.getProductQty());
                    stock.setRemainingQuantity(stockByProductProductId.getRemainingQuantity() + q.getProductQty());
                    stock.setStockTotalAmount(stockByProductProductId.getStockTotalAmount() +
                            (productByProductId.getProductCost() * q.getProductQty()));
                    listStock.add(stock);
                });
                stockRepo.saveAll(listStock);
            }
        });
        return  p;
    }

    @Override
    public Purchase fetchLastPurchase() {
        return purchaseRepo.getLastPurchaseDetails();
    }

    @Override
    public List<Purchase> getPurchaseById(Integer id) {
        return purchaseRepo.getPurchasesByPurchaseId(id);
    }

    @Override
    public Integer getTotalOverallPurchaseQty() {
        return purchaseRepo.getTotalOverallPurchaseQty();
    }

    @Override
    public Double getTotalOverallPurchaseAmount() {
        return purchaseRepo.getTotalOverallPurchaseAmount();
    }
}
