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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String slug;
	private String company;
	private String city;
	private String address;
	@Pattern(regexp = "\\d{10}", message = "you must enter 10 numbers")
	private String phone;
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please provide a valid email address")
	private String email;
	private int status;
	private String note;
	private Float totalPrice;
	private int userId;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date createdAt = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private Users users;
	
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Order(int id, @NotEmpty(message = "Please fill out this field!") String name,
			@NotEmpty(message = "Please fill out this field!") String slug, String company,
			@NotEmpty(message = "Please fill out this field!") String city,
			@NotEmpty(message = "Please fill out this field!") String address,
			@NotEmpty(message = "Please fill out this field!") @Pattern(regexp = "\\d{10}", message = "you must enter 10 numbers") String phone,
			@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please provide a valid email address") @NotEmpty(message = "Please fill out this field!") String email,
			int status, String note, Float totalPrice, int userId, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.company = company;
		this.city = city;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.note = note;
		this.totalPrice = totalPrice;
		this.userId = userId;
		this.createdAt = createdAt;
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


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Float getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	
}
