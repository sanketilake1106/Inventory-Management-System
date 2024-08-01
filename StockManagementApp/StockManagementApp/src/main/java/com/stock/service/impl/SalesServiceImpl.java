package com.stock.service.impl;

import com.stock.entity.*;
import com.stock.entity.dtos.SaleCustomerProductDto;
import com.stock.entity.dtos.batchDtos.BatchSaleDto;
import com.stock.repositories.*;
import com.stock.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class SalesServiceImpl implements ISalesService {
    @Autowired
    private ISalesRepo salesRepo;
    @Autowired
    private IStockRepo stockRepo;
    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private ICustomerRepo customerRepo;
    @Autowired
    private ISaleProductRepo saleProductRepo;


    @Override
    public Sale addBatchSaleDto(BatchSaleDto batchSaleDto) {
        Integer totalSaleQuantity = batchSaleDto.getTotalSaleQuantity();
        Double totalSaleAmount = batchSaleDto.getTotalSaleAmount();
        Customer customer = batchSaleDto.getCustomer();
        List<Product> products = batchSaleDto.getProducts();

//        System.out.println(totalSaleAmount);
//        System.out.println(totalSaleQuantity);
//        System.out.println(customer);
//        System.out.println(products);

        Customer customerByCustomerContact = customerRepo.getCustomerByCustomerContact(customer.getCustomerContact());
        if(customerByCustomerContact==null){
            Customer savedCustomer = customerRepo.save(customer);
            Sale sale = new Sale();
            sale.setSaleDate(LocalDate.now().toString());
            sale.setSaleQuantity(totalSaleQuantity);
            sale.setTotalSalesAmount(totalSaleAmount);
            sale.setCustomers(Set.of(savedCustomer));
            Sale s = salesRepo.save(sale);
            batchSaleDto.getProducts().forEach(pr ->{
                SaleProduct saleProduct = new SaleProduct();
                saleProduct.setSale(List.of(s));
                saleProduct.setProduct(List.of(pr));
                saleProductRepo.save(saleProduct);

                Product productByProductId =
                        productRepo.getProductByProductId(pr.getProductId());

                Stock stock = new Stock();
                Stock stockByProductProductId = stockRepo.getStockByProductProductId(pr.getProductId());

                if(stockByProductProductId!=null){
                    List<Stock> listStock = new ArrayList<>();
                    batchSaleDto.getProductQuantity().forEach(q ->{
                        stock.setStockId(stockByProductProductId.getStockId());
                        stock.setProduct(stockByProductProductId.getProduct());
                        stock.setStockDate(LocalDate.now().toString());
                        stock.setStockSaleQuantity(totalSaleQuantity + stockByProductProductId.getStockSaleQuantity());
                        stock.setStockPurchaseQuantity(stockByProductProductId.getStockPurchaseQuantity());
                        stock.setRemainingQuantity(stockByProductProductId.getRemainingQuantity() - q.getProductQty());
                        stock.setStockTotalAmount(stockByProductProductId.getStockTotalAmount() -
                                (productByProductId.getProductCost() * q.getProductQty()));
                        listStock.add(stock);
                    });
                    stockRepo.saveAll(listStock);
                }
            });
            return s;
        }
        else{
            Sale sale = new Sale();
            sale.setSaleDate(LocalDate.now().toString());
            sale.setSaleQuantity(totalSaleQuantity);
            sale.setTotalSalesAmount(totalSaleAmount);
            sale.setCustomers(Set.of(customerByCustomerContact));
            Sale s = salesRepo.save(sale);
            batchSaleDto.getProducts().forEach(pr ->{
                SaleProduct saleProduct = new SaleProduct();
                saleProduct.setSale(List.of(s));
                saleProduct.setProduct(List.of(pr));
                saleProductRepo.save(saleProduct);

                Product productByProductId =
                        productRepo.getProductByProductId(pr.getProductId());

                Stock stock = new Stock();
                Stock stockByProductProductId = stockRepo.getStockByProductProductId(pr.getProductId());

                if(stockByProductProductId!=null){
                    List<Stock> listStock = new ArrayList<>();
                    batchSaleDto.getProductQuantity().forEach(q ->{
                        stock.setStockId(stockByProductProductId.getStockId());
                        stock.setProduct(stockByProductProductId.getProduct());
                        stock.setStockDate(LocalDate.now().toString());
                        stock.setStockSaleQuantity(totalSaleQuantity + stockByProductProductId.getStockSaleQuantity());
                        stock.setStockPurchaseQuantity(stockByProductProductId.getStockPurchaseQuantity());
                        stock.setRemainingQuantity(stockByProductProductId.getRemainingQuantity() - q.getProductQty());
                        stock.setStockTotalAmount(stockByProductProductId.getStockTotalAmount() -
                                (productByProductId.getProductCost() * q.getProductQty()));
                        listStock.add(stock);
                    });
                    stockRepo.saveAll(listStock);
                }
            });
            return s;
        }
    }

    @Override
    public List<SaleCustomerProductDto> getAllSaleProductByCustomerId(Integer id) {
        Customer customerByCustomerId = customerRepo.getCustomerByCustomerId(id);
        Set<Customer> customerSet = new HashSet<>();
        List<SaleCustomerProductDto> products = new ArrayList<>();
        customerSet.add(customerByCustomerId);
        List<Sale> allByCustomers = salesRepo.getAllByCustomers(customerSet);
        allByCustomers.forEach(cp->{
            cp.getSaleProducts().forEach(sp->{
                SaleCustomerProductDto dto = new SaleCustomerProductDto();
                dto.setProducts(sp.getProduct());
                dto.setSaleProductQty(cp.getSaleQuantity());
                sp.getProduct().forEach(pp->{
                    double totalAmount = pp.getProductCost() * cp.getSaleQuantity();
                    dto.setTotalAmount(totalAmount);
                });
                products.add(dto);
            });
        });
        return products;

    }

    @Override
    public Integer getTotalSaleQtyByDate(String date) {
        return salesRepo.getTotalSaleByDate(date);
    }

    @Override
    public Integer getTotalOverallSaleQty() {
        return salesRepo.getTotalOverallSale();
    }

    @Override
    public Double getTotalSaleAmount() {
        return salesRepo.getTotalSaleAmount();
    }
}

