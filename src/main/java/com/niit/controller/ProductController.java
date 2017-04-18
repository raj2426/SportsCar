package com.niit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.ProductDAO;
import com.niit.model.Product;

@Controller
public class ProductController {
	
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/selProductAdd")
	public ModelAndView showPAdd()
	{
		log.debug("Start of go to Product add");
		ModelAndView mv=new ModelAndView("/ProductAdd","command", new Product());
		log.debug("End of go to Product add");
		return mv;
	}
	
	@Transactional
	@RequestMapping(value="/addProduct",  method = RequestMethod.POST)
	public ModelAndView addProductFunction(@ModelAttribute Product product)
	{
		log.debug("Start of Product add");
		productDAO.saveProduct(product);
		String path="F:\\EclipseMain\\Projects\\Philosophia\\Philosophia\\src\\main\\webapp\\Resources\\Images\\";
		path=path+String.valueOf(product.getProductID())+".jpg";
		File f=new File(path);
		MultipartFile filedet= product.getProductImage();
		if(!filedet.isEmpty())
		{
			try
			{
			  byte[] bytes=filedet.getBytes();
			  System.out.println(bytes.length);
			  FileOutputStream fos=new FileOutputStream(f);
              BufferedOutputStream bs=new BufferedOutputStream(fos);
              		bs.write(bytes);
              		bs.close();
             		System.out.println("File Uploaded Successfully");
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised"+e);
			}
		}
		else
		{
			System.out.println("File is Empty not Uploaded");
			
		}


		ModelAndView mv= new ModelAndView("/Admin");
		mv.addObject("msg", "Product ADDED");
		log.debug("End of Product add");
		return mv;
	}
	
	@RequestMapping("/selProductEdit")
	public ModelAndView showProductEdit(Map<String, Object> map)
	{
		log.debug("Start of go to Product edit");
		List<Product> prodList=productDAO.getAllProduct();
		map.put("prList", prodList );
		ModelAndView mv=new ModelAndView("/ProductEdit",map);
		log.debug("End of go to Product edit");
		return mv;
	}
	
	@RequestMapping("/findProduct")
	public ModelAndView findProduct(@RequestParam("pID") String prID, Map<String, Object> map)
	{
		log.debug("Start of Product add");
		Product pr=productDAO.getProductById(prID);
		ModelAndView mv=new ModelAndView("/ProductEdit2","command", new Product() );
		mv.addObject("prFound", pr);
		log.debug("End of Product add");
		return mv;
	}
	
	@RequestMapping(value="/editProduct",  method = RequestMethod.POST)
	public ModelAndView editProductFunction(@ModelAttribute Product product,@RequestParam("prID") String pID)
	{
		product.setProductID(pID);
		productDAO.updateProduct(product);
		ModelAndView mv= new ModelAndView("/Admin");
		mv.addObject("msg", "Product Edited");
		return mv;
	}
	
	@RequestMapping("/selProductDelete")
	public ModelAndView deleteProductFunction()
	{
		ModelAndView mv=new ModelAndView("/ProductDelete");
		return mv;
	}
	
	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProduct(@RequestParam("pID") String prID)
	{
		productDAO.deleteProduct(prID);
		ModelAndView mv=new ModelAndView("/Admin");
		return mv;
	}
	
	@RequestMapping("/selProductView")
	public ModelAndView viewProduct(Map<String, Object> map)
	{
		String path="E:\\Workspace\\SportsCar\\src\\main\\webapp\\Resources\\Images";
		List<Product> prodList=productDAO.getAllProduct();
		map.put("prList", prodList );
		map.put("path", path);
		ModelAndView mv=new ModelAndView("/ProductView",map);
		return mv;
	}
	
	@RequestMapping("/goProdView")
	public ModelAndView showProducts()
	{
		System.out.println("Inside Prod view");
		List<Product> prList=productDAO.getAllProduct();
		ModelAndView mv=new ModelAndView("/Products");
		mv.addObject("prods", prList);
		System.out.println("Leaving Prod view");
		return mv;
	}



}
