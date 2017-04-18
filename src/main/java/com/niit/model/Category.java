package com.niit.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.stereotype.Component;


@Table(name="category")
@Entity
@Component
public class Category {
	
	
	@Id
	private String categoryID;
	
	private String categoryName;
	
	private String categorydescription;
	
	@OneToMany(mappedBy="category",fetch = FetchType.EAGER)
	private Set<Product> productsInCategory;
	
	public Set<Product> getProductsInCategory() {
		return productsInCategory;
	}
	public void setProductsInCategory(Set<Product> productsInCategory) {
		this.productsInCategory = productsInCategory;
	}
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategorydescription() {
		return categorydescription;
	}
	public void setCategorydescription(String categorydescription) {
		this.categorydescription = categorydescription;
	}
	
	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryName=" + categoryName + ", categorydescription="
				+ categorydescription + "]";
	}
	
	
	
	
	
	
		
	
}
