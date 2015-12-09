package com.shoppingCart.app.dao;

import java.util.List;

import com.shoppingCart.app.model.Product;

public interface ProductDao {

	Product findBy(Long idProduct);
	Product findBy(String description);
	List<Product> findByCategory(String category);
	List<Product> findAll();
	
}
