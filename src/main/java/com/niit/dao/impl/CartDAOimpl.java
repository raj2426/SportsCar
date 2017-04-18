package com.niit.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.CartDAO;
import com.niit.model.Cart;
import com.niit.model.Category;
import com.niit.model.Supplier;

@EnableTransactionManagement
@Repository("cartDAO")
public class CartDAOimpl implements CartDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CartDAOimpl(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;		
	}

	public List<Cart> getAllCart() {
		return sessionFactory.getCurrentSession().createQuery("from Cart").list();

	}

	public boolean saveCart(Cart cart) {
		try
		{
		sessionFactory.getCurrentSession().save(cart);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCart(Cart cart) {
		try
		{
		sessionFactory.getCurrentSession().update(cart);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCart(Cart cart) {
		try
		{
		sessionFactory.getCurrentSession().delete(cart);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCart(String id) {
		try {
			sessionFactory.getCurrentSession().delete(getCartById(id));
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Cart getCartById(String id) {
		return (Cart) sessionFactory.getCurrentSession().createQuery("from Cart where cartId ='"+ id +"'" ).uniqueResult();// uniqueresult() or list().get(0);

	}

	public int getTotalAmount(String cartID) {
		Cart cart;
		cart=(Cart) sessionFactory.getCurrentSession().createQuery("from Cart where cartId='"+cartID+"'" ).uniqueResult();
		return cart.getTotalCost();
	}

	@Override
	public Cart getCartById(int crtID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getProductsInCart(int cartID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cart> getCartUser(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateBill(Integer attribute) {
		// TODO Auto-generated method stub
		
	}

}
