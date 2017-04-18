package com.niit.dao;

import java.util.List;
import com.niit.model.Product;

public interface ProductDAO {
	
		public List<Product> getAllProduct();
		
		public boolean saveProduct(Product product);
		
		public boolean updateProduct(Product product);
		
		public boolean deleteProduct(Product product);
		
		public boolean deleteProduct(String id);
		
		public Product getProductById(String id);
		
		public Product getProductByName(String name);

}
