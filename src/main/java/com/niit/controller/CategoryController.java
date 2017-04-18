package com.niit.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.CategoryDAO;
import com.niit.model.Category;

@Controller
public class CategoryController {
	
	Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping("/selCategoryAdd")
	public ModelAndView showCategorydd()
	{
		log.debug("Start of go to Category add");
		ModelAndView mv=new ModelAndView("/CategoryAdd","command", new Category());//"/SupplierAdd","command", new Supplier());
		log.debug("End of go to Category add");
		return mv;
	}
	
	@RequestMapping(value="/addCategory",  method = RequestMethod.POST)
	public ModelAndView addCategoryFunction(@ModelAttribute Category category)
	{
		log.debug("Start of Category add");
		categoryDAO.saveCategory(category);
		ModelAndView mv= new ModelAndView("/Admin");
		mv.addObject("msg", "Category ADDED");
		log.debug("End of Category add");
		return mv;
	}
	
	@RequestMapping("/selCategoryEdit")
	public ModelAndView showCategoryEdit(Map<String, Object> map)
	{
		log.debug("Start of Category edit");
		List<Category> categList=categoryDAO.getAllCategory();
		map.put("crList", categList );
		ModelAndView mv=new ModelAndView("/CategoryEdit",map);
		return mv;
	}
	
	@RequestMapping("/findCategory")
	public ModelAndView findCategory(@RequestParam("cID") String cgID, Map<String, Object> map)
	{
		Category category=categoryDAO.getCategoryById(cgID);
		ModelAndView mv=new ModelAndView("/CategoryEdit2","command", new Category() );
		mv.addObject("cgFound", category);
		return mv;
	}
	
	@RequestMapping(value="/editCategory",  method = RequestMethod.POST)
	public ModelAndView editCategoryFunction(@ModelAttribute Category category,@RequestParam("ctID") String cgID,@RequestParam("ctName") String cgName,@RequestParam("ctDesc") String cgDesc)
	{
		category.setCategoryID(cgID);
		category.setCategoryName(cgName);
		category.setCategorydescription(cgDesc);
		categoryDAO.updateCategory(category);
		ModelAndView mv= new ModelAndView("/Admin");
		mv.addObject("msg", "Category Edited");
		return mv;
	}
	
	@RequestMapping("/selCategoryDelete")
	public ModelAndView deleteCategoryFunction()
	{
		ModelAndView mv=new ModelAndView("/CategoryDelete");
		return mv;
	}
	
	@RequestMapping("/deleteCategory")
	public ModelAndView deleteCategory(@RequestParam("cID") String cgID)
	{
		categoryDAO.deleteCategory(cgID);
		ModelAndView mv=new ModelAndView("/Admin");
		return mv;
	}
	
	@RequestMapping("/selCategoryView")
	public ModelAndView viewCategory(Map<String, Object> map)
	{
		List<Category> categList=categoryDAO.getAllCategory();
		map.put("cgList", categList );
		ModelAndView mv=new ModelAndView("/CategoryView",map);
		return mv;
	}
	
}
