package com.niit.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProductDAO;
import com.niit.model.Product;

@EnableTransactionManagement
@Repository("productDAO")
public class ProductDAOimpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDAOimpl(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;		
	}
	
	
	@Transactional
	public List<Product> getAllProduct() {
		//select * from category -sql query
		// use hql(hibernate query language)
		// from Category -mention Domain Class name not table name
		//convert the hibernate query into db specific language
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	public boolean saveProduct(Product product) {
		try
		{
			//getcurrentSession opens the existing session
			//opencurrentSession creates a new session if not present
		sessionFactory.getCurrentSession().save(product);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	public boolean updateProduct(Product product) {
		try
		{
		sessionFactory.getCurrentSession().update(product);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean deleteProduct(Product product) {
		try
		{
		sessionFactory.getCurrentSession().delete(product);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean deleteProduct(String id) {
		try {
			sessionFactory.getCurrentSession().delete(getProductById(id));
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public Product getProductById(String id) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where productID ='"+ id +"'" ).uniqueResult();// uniqueresult() or list().get(0);
	}

	@Transactional
	public Product getProductByName(String name) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where productName ='"+ name +"'" ).list().get(0);
	}



}
