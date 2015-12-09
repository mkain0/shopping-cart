package com.shoppingCart.app.dao;

import com.shoppingCart.app.model.Cart;

public interface CartDao {

	Cart findBy(Long idCart);
	Long save(Cart cart);
	void update(Cart cart);
	
}
