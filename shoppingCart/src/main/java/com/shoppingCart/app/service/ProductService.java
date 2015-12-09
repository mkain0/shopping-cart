package com.shoppingCart.app.service;

import java.util.List;

import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Product;

public interface ProductService {

	Product findBy(Long idProduct) throws ProductNotFoundException;
	Product findBy(String description) throws ProductNotFoundException;
	List<Product> findByCategory(String category) throws ProductNotFoundException;
	List<Product> findAll() throws ProductNotFoundException;
	
}
