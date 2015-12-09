package com.shoppingCart.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingCart.app.model.Product;

@Repository
public class ProductDaoImp implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
	    return sessionFactory.getCurrentSession();
	  }
	
	@Override
	public Product findBy(Long idProduct) {
		String queryString = "SELECT p FROM Product AS p WHERE p.idProduct = :idProduct";
		return (Product) getSession().createQuery(queryString)
								.setParameter("idProduct", idProduct)
								.uniqueResult();
	}

	@Override
	public Product findBy(String description) {
		String queryString = "SELECT p FROM Product AS p WHERE p.description = :description";
		return (Product) getSession().createQuery(queryString)
								.setParameter("description", description)
								.uniqueResult();
	}
	
	@Override
	public List<Product> findByCategory(String category) {
		String queryString = "SELECT p FROM Product AS p WHERE p.category.description = :category";
		return getSession().createQuery(queryString)
					.setParameter("category", category)
					.list();
	}

	@Override
	public List<Product> findAll() {
		String queryString = "SELECT p FROM Product AS p";
		return getSession().createQuery(queryString)
					.list();
	}

}
