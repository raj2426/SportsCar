package com.niit.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="cart")
@Component
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String cartId;
	
	private String user_Id;
	
	@ManyToOne
	@JoinColumn(name = "user_Id", nullable = false, updatable = false, insertable = false)
	private User user;
	
	private String address;
	
	private String delivery_name;
	
	private double user_contact;
	
	@ElementCollection
	@JoinTable(name="Products_In_Cart")
	private Set<Product> productsinCart= new HashSet<Product>();
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street",column=@Column(name="bill_street")),
		@AttributeOverride(name="city",column=@Column(name="bill_city")),
		@AttributeOverride(name="state",column=@Column(name="bill_state")),
		@AttributeOverride(name="pincode",column=@Column(name="bill_pincode"))
	})
	private Address billingAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street",column=@Column(name="ship_street")),
		@AttributeOverride(name="city",column=@Column(name="ship_city")),
		@AttributeOverride(name="state",column=@Column(name="ship_state")),
		@AttributeOverride(name="pincode",column=@Column(name="ship_pincode"))
	})
	private Address shippingAddress;

	private int totalCost;
	
	private Date dateAdded;
	
	private String cartStatus;
		
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(String cartStatus) {
		this.cartStatus = cartStatus;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDelivery_name() {
		return delivery_name;
	}

	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}

	public double getUser_contact() {
		return user_contact;
	}

	public void setUser_contact(double user_contact) {
		this.user_contact = user_contact;
	}

	public Set<Product> getProductsinCart() {
		return productsinCart;
	}

	public void setProductsinCart(Set<Product> productsinCart) {
		this.productsinCart = productsinCart;
	}
	
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
}
