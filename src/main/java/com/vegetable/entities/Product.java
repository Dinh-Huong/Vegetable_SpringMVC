package com.vegetable.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "Please fill out this field!")
	private String name;
	@NotEmpty(message = "Please fill out this field!")
	private String slug;
	@Min(value = 0, message = "Price must be greater than or equal to 0")
	@NotNull(message = "Please fill out this field!")
	private float price;
	@NotEmpty(message = "Please fill out this field!")
	private String description;
	private int evaluate;
	private int status;
	private String thumbnail;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date createdAt = new Date();
	private int categoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private Category category;
	

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails ;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, @NotEmpty(message = "Please fill out this field!") String name,
			@NotEmpty(message = "Please fill out this field!") String slug,
			@Min(value = 0, message = "Price must be greater than or equal to 0") @NotNull(message = "Please fill out this field!") float price,
			@NotEmpty(message = "Please fill out this field!") String description, int evaluate, int status,
			@NotEmpty(message = "Please fill out this field!") String thumbnail, Date createdAt, int categoryId,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.price = price;
		this.description = description;
		this.evaluate = evaluate;
		this.status = status;
		this.thumbnail = thumbnail;
		this.createdAt = createdAt;
		this.categoryId = categoryId;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(int evaluate) {
		this.evaluate = evaluate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	

	
	
	
	
}
