package com.shoppingCart.app.dao;

import com.shoppingCart.app.model.Customer;

public interface CustomerDao {

	Customer findBy(String username);
	Long save(Customer customer);
	
}
