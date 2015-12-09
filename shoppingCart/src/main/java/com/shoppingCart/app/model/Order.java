package com.shoppingCart.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "orders", catalog = "shopping_cart_db")
public class Order implements java.io.Serializable {

	private Long idorder;
	private Customer customer;
	private Date ordered;
	private String status;
	private BigDecimal total;
	private List<LineItem> linesItems = new ArrayList<LineItem>();

	public Order() {
	}

	public Order(Long idorder, Customer customer, Date ordered, String status, BigDecimal total, List<LineItem> linesItems) {
		this.idorder = idorder;
		this.customer = customer;
		this.ordered = ordered;
		this.status = status;
		this.total = total;
		this.linesItems = linesItems;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idorder", unique = true, nullable = false)
	public Long getIdorder() {
		return this.idorder;
	}

	public void setIdorder(Long idorder) {
		this.idorder = idorder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcustomer", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ordered", nullable = false, length = 19)
	public Date getOrdered() {
		return this.ordered;
	}

	public void setOrdered(Date ordered) {
		this.ordered = ordered;
	}

	@Column(name = "status", nullable = false, length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "total", nullable = false, precision = 10)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<LineItem> getLinesItems() {
		return this.linesItems;
	}

	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}

	public static class BuilderOrder {

		private Long idorder;
		private Customer customer;
		private Date ordered;
		private String status;
		private BigDecimal total;
		private List<LineItem> linesItems = new ArrayList<LineItem>();

		public BuilderOrder setIdorder(Long idorder) {
			this.idorder = idorder;
			return this;
		}

		public BuilderOrder setCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public BuilderOrder setOrdered(Date ordered) {
			this.ordered = ordered;
			return this;
		}

		public BuilderOrder setStatus(String status) {
			this.status = status;
			return this;
		}

		public BuilderOrder setTotal(BigDecimal total) {
			this.total = total;
			return this;
		}

		public BuilderOrder setLinesItems(List<LineItem> linesItems) {
			this.linesItems = linesItems;
			return this;
		}

		public Order build() {
			Order order = new Order(this.idorder, this.customer, this.ordered, 
					this.status, this.total, this.linesItems);
			return order;
		}
	}
}
