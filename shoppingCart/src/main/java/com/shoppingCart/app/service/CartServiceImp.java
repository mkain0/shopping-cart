package com.shoppingCart.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingCart.app.dao.CartDao;
import com.shoppingCart.app.dao.OrderDao;
import com.shoppingCart.app.dao.ProductDao;
import com.shoppingCart.app.model.Cart;
import com.shoppingCart.app.model.LineItem;
import com.shoppingCart.app.model.Product;
import com.shoppingCart.app.util.OrderStatus;
import com.shoppingCart.app.model.Order;
import com.shoppingCart.app.model.Order.BuilderOrder;

@Service
@Transactional
public class CartServiceImp implements CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderDao orderDao;

	@Override
	public Long save(Cart cart) {
		return cartDao.save(cart);
	}

	@Override
	public void add(Long idCart, Long idProduct, Integer quantity) {
		Cart cart = cartDao.findBy(idCart);
		Product product = productDao.findBy(idProduct);
		cart.getLinesItems().add(new LineItem(cart, product, quantity, product.getPrice()));
		cartDao.update(cart);
	}

	@Override
	public Long ordered(Long idCart) {
		Cart cart = cartDao.findBy(idCart);
		Order order = new BuilderOrder()
				.setCustomer(cart.getCustomer())
				.setOrdered(new Date())
				.setStatus(OrderStatus.NEW.toString())
				.setTotal(cart.calculateTotal())
				.setLinesItems(cart.getLinesItems())
				.build();
		return orderDao.save(order);
	}

}
