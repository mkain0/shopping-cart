package com.shoppingCart.app.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "carts", catalog = "shopping_cart_db")
public class Cart implements java.io.Serializable {

	private Long idCart;
	private Customer customer;
	private BigDecimal subtotal;
	private List<LineItem> linesItems = new ArrayList<LineItem>();

	public Cart() {
	}

	public Cart(Long idCart, Customer customer, BigDecimal subtotal) {
		this.idCart = idCart;
		this.customer = customer;
		this.subtotal = subtotal;
	}

	public Cart(Long idCart, Customer customer, BigDecimal subtotal, List<LineItem> linesItems) {
		this.idCart = idCart;
		this.customer = customer;
		this.subtotal = subtotal;
		this.linesItems = linesItems;
	}

	@Id
	@Column(name = "idcarts", unique = true, nullable = false)
	public Long getIdCart() {
		return this.idCart;
	}

	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcustomer", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customers) {
		this.customer = customers;
	}

	@Column(name = "subtotal", nullable = false, precision = 10)
	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<LineItem> getLinesItems() {
		return this.linesItems;
	}

	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}
	
	public BigDecimal calculateTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for (LineItem lineItem : this.getLinesItems()) {
			total.add(lineItem.getPrice().multiply(new BigDecimal(lineItem.getQuantity())));		
		}
		return total;
	}
}
