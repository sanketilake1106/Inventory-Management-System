package com.stock.service.impl;

import com.stock.entity.SaleProduct;
import com.stock.entity.dtos.SaleDto;
import com.stock.entity.dtos.SaleProductDto;
import com.stock.repositories.ISaleProductRepo;
import com.stock.service.SaleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleProductServiceImpl implements SaleProductService {

    @Autowired
    private ISaleProductRepo saleProductRepo;

    @Override
    public List<SaleProductDto> getAllSaleProducts() {
        List<SaleProduct> all = saleProductRepo.findAll();
        List<SaleProductDto> dtoList = new ArrayList<>();
        all.forEach(s->{
            SaleProductDto dto = new SaleProductDto();
            final Double[] productPrice = {0.0};
            final Integer[] salesQty = {0};
            s.getProduct().forEach(p->{
                productPrice[0] = p.getProductCost();
                dto.setSaleProductName(p.getProductName());
                s.getSale().forEach(s1->{
                    dto.setSaleProductQty(s1.getSaleQuantity());
                });
                dto.setProductUnitPrice(p.getProductCost());
            });
            s.getSale().forEach(s2->{
                dto.setSaleProductDate(s2.getSaleDate());
                dto.setId(s2.getSalesId());
                s2.getCustomers().forEach(dto::setCustomer);
                salesQty[0] = s2.getSaleQuantity();
            });
            dto.setSaleProductAmount(productPrice[0] * salesQty[0]);
            dtoList.add(dto);
        });
        return dtoList;
    }
}
