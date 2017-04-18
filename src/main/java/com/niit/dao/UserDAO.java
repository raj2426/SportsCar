package com.niit.dao;

import java.util.List;
import java.util.Set;

import com.niit.model.Cart;
import com.niit.model.User;

public interface UserDAO {
	
	public List<User> getAllUser();
	
	public boolean saveUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);
	
	public boolean deleteUser(String id);
	
	public User getUserById(String id);
	
	public User getUserByName(String name);
	
	public Set<Cart> getCartsOfUser(String userID);

}
