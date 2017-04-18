package com.niit.dao;

import java.util.List;

import com.niit.model.Cart;

public interface CartDAO {
	
     List<Cart> getAllCart();
	
	public boolean saveCart(Cart cart);
	
	public boolean updateCart(Cart cart);
	
	public boolean deleteCart(Cart cart);
	
	public boolean deleteCart(String id);
	
	public Cart getCartById(int crtID);
	
	//public int getQuantity(String userId, String productname);
	
	public int getTotalAmount(String cartID);

	List<String> getProductsInCart(int cartID);

	List<Cart> getCartUser(String userID);

	void generateBill(Integer attribute);
	
}
