package com.vegetable.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "orderDetail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer orderId;
	private Integer productId;
	private Integer quantity;
	private float totalPrice;
	private Date createdAt = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId", insertable = false, updatable = false)
	private Order orders;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", insertable = false, updatable = false)
	private Product product;
	
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetail(Integer id, Integer orderId, Integer productId, Integer quantity,
			@NotEmpty(message = "This field cannot be left blank!") float totalPrice, Date createdAt, Order orders,
			Product product) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.orders = orders;
		this.product = product;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	

}
