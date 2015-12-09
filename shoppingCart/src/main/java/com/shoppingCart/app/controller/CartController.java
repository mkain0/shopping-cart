package com.shoppingCart.app.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingCart.app.model.Cart;
import com.shoppingCart.app.service.CartService;

@RestController
public class CartController {

	@Autowired
	CartService cartService;

	@RequestMapping(value = "/users/{idUser}/carts", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> create(@RequestBody Cart cart, HttpServletRequest request) throws URISyntaxException {
		Long id = cartService.save(cart);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users/{idUser}/carts/{idCart}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Void> addProduct(@PathVariable("idCart") Long idCart, @RequestParam("idProduct") Long idProduct,
			@RequestParam("quantity") Integer quantity) {
		cartService.add(idCart, idProduct, quantity);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> ordered(@PathVariable("idCart") Long idCart, HttpServletRequest request) throws URISyntaxException {
		Long id = cartService.ordered(idCart);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
}
