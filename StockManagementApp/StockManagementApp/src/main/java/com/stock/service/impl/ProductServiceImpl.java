package com.stock.service.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.entity.Product;
import com.stock.repositories.IProductRepo;
import com.stock.service.IProductService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepo repo;


	@Override
	public Product saveProduct(String productHSNNo,  String productName, String productDescription,
							  String productCategory,  Double productCost, MultipartFile multipartFile) throws IOException {

		if(repo.getProductByProductHSNNo(productHSNNo)==null) {
			Product product = new Product();
			product.setProductHSNNo(productHSNNo);
			product.setProductName(productName);
			product.setProductDescription(productDescription);
			product.setProductCategory(productCategory);
			product.setProductCost(productCost);
			LocalDate date = LocalDate.now();
			product.setProductDate(date.toString());
			repo.save(product);
			if (repo.existsById(product.getProductId())) {
				return product;
			}
		}
			return null;

	}

	@Override
	public Product saveProduct(Product product) {
		product.setProductDate(LocalDate.now().toString());
		return repo.save(product);
	}

	@Override
	public Product fetchProductById(Integer id) {
		Optional<Product> optional = repo.findById(id);
		return optional.orElse(null);
	}

	@Override
	public List<String> getProductsCategory() {
		List<String> list = repo.findAll().stream()
				.map(Product::getProductCategory)
				.distinct()
				.toList();
		System.out.println(list);
		return list;

	}

	@Override
	public List<Product> fetchAllProducts() {
		return repo.findAll();
	}

	@Override
	public Product fetchLastProduct() {
		if(repo.findLastProduct().isPresent()){
			return repo.findLastProduct().get();
		}
		return null;
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		return repo.getProductsByProductCategory(category);
	}


}
