package com.stock.service.impl;

import com.stock.entity.dtos.PurchaseProductDto;
import com.stock.repositories.IPurchaseProductRepo;
import com.stock.entity.PurchaseProduct;
import com.stock.service.IPurchaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseProductServiceImpl implements IPurchaseProductService {

    @Autowired
    private IPurchaseProductRepo purchaseProductRepo;

    @Override
    public List<PurchaseProductDto> getAllPurchaseProducts() {
        List<PurchaseProduct> all = purchaseProductRepo.findAll();
        List<PurchaseProductDto> dtoList = new ArrayList<>();
        all.forEach(p->{
            PurchaseProductDto dto = new PurchaseProductDto();
            final Double[] productPrice = {0.0};
            final Integer[] purchaseQty = {0};
            p.getProduct().forEach(pro->{
                dto.setProductName(pro.getProductName());
                dto.setProductPrice(pro.getProductCost());
                productPrice[0] = pro.getProductCost();
            });
            p.getPurchase().forEach(pur->{
                dto.setPurchaseId(pur.getPurchaseId());
                dto.setPurchaseProductId(pur.getPurchaseId());
                dto.setPurchaseDate(pur.getPurchaseDate());
                dto.setTotalPurchaseQuantity(pur.getTotalPurchaseQuantity());
                pur.getSupplier().forEach(dto::setSupplier);
                purchaseQty[0] = pur.getTotalPurchaseQuantity();
            });
                dto.setTotalPurchaseAmount(productPrice[0] * purchaseQty[0]);
            dtoList.add(dto);
        });
        return dtoList;
    }
}
