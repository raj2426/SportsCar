package com.niit.dao;

import java.util.List;
import com.niit.model.Category;

public interface CategoryDAO {
	
	public List<Category> getAllCategory();
	
	//create category
	public boolean saveCategory(Category category);
	
	//update category
	public boolean updateCategory(Category category);
	
	//delete category
	public boolean deleteCategory(Category category);
	
	//delete category
	public boolean deleteCategory(String id);
	
	//get category by id
	public Category getCategoryById(String id);
	
	//get category by name
	public Category getCategoryByName(String name);
	

}
