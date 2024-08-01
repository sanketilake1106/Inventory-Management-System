package com.stock.service;

import com.stock.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

	Product saveProduct(String productHSNNo,  String productName, String productDescription,
					   String productCategory,
					    Double productCost, MultipartFile multipartFile) throws IOException;
	Product saveProduct(Product product);

	Product fetchProductById(Integer id);

	List<String> getProductsCategory();
	List<Product> fetchAllProducts();

	Product fetchLastProduct();

	List<Product> getProductByCategory(String category);
}