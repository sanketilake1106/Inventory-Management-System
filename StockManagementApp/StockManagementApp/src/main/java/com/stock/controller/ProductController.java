package com.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stock.entity.Product;
import com.stock.service.IProductService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private IProductService service;

	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody Product product ) throws IOException {
		Product prod= service.saveProduct(product);
		if (prod != null) {
			return new ResponseEntity<Product>(prod, HttpStatus.OK);
		}
		return new ResponseEntity<>((Product) null, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getProduct/{pid}")
	public ResponseEntity<?> getProduct(@PathVariable("pid") Integer pid) {
		Product product = service.fetchProductById(pid);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/getProductsCategory")
	public ResponseEntity<?> getAllProductCategory(){
		System.out.println(service.getProductsCategory());
		return new ResponseEntity<List<String>>(service.getProductsCategory(),HttpStatus.OK);
	}
	@GetMapping("/getProducts")
	public ResponseEntity<?> getAllProduct(){
		return new ResponseEntity<List<Product>>(service.fetchAllProducts(),HttpStatus.OK);
	}

	@PostMapping("/getProduct")
	public ResponseEntity<?> getProduct(@RequestBody Product product) {
		Product productByCategory = service.fetchProductById(product.getProductId());
		return new ResponseEntity<Product>(productByCategory, HttpStatus.OK);
	}

	@GetMapping("/getLastProduct")
    public ResponseEntity<?> getLastProduct(){
		 return ResponseEntity.ok(service.fetchLastProduct());
	}

	@GetMapping("/getProductByCategory/{category}")
	public ResponseEntity<?> getProductByCategory(@PathVariable("category") String category){
		System.out.println(category);
		List<Product> productByCategory = service.getProductByCategory(category);
		return ResponseEntity.ok(productByCategory);
	}


}
