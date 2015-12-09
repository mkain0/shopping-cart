package com.shoppingCart.app.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingCart.app.exception.AuthenticationFailedException;
import com.shoppingCart.app.model.Customer;
import com.shoppingCart.app.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Customer> login(@RequestParam("username") String username, @RequestParam("password") String password) 
			throws NoSuchAlgorithmException, AuthenticationFailedException {
		Customer customer = customerService.authentication(username, password);
		return new ResponseEntity<Customer> (customer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> addCustomer(@RequestBody Customer customer, HttpServletRequest request) 
			throws URISyntaxException, NoSuchAlgorithmException {
		Long id = customerService.addCustomer(customer);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
}
