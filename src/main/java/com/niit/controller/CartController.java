package com.niit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.CartDAO;
import com.niit.dao.ProductDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Address;
import com.niit.model.Cart;
import com.niit.model.Product;
import com.niit.model.User;

@Controller
public class CartController {
	
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	public HttpSession session;
	
	@RequestMapping("/goMyCart")
	public ModelAndView goToCart(Map<String, Object> map)
	{
		log.debug("Start of method goToCart");
		int cartID=(Integer)session.getAttribute("CurrentCartID");
		List<String> prodInCartList=cartDAO.getProductsInCart(cartID);
		//err ArrayList<String> tempProdList= (ArrayList<String>) prodInCartList.get(0);
		List<Product> products=productDAO.getAllProduct();
		List<Product> productOfCart=new ArrayList<Product>();
		for(String s: prodInCartList)
		{
			for(Product p: products)
			{
				if(p.getProductID().equals(s))
				{
					productOfCart.add(p);
				}
			}
		}
		ModelAndView mv=new ModelAndView("/UserCart");
		mv.addObject("prList",productOfCart);
		/*List<String> prodIDs=new ArrayList<String>();
				prodIDs=cartDAO.getProductsInCart(cartID);
		ListIterator<String> pIdsIter=prodIDs.listIterator();
		//List<Product> prodList=productDAO.getAllProduct();
		List<Product> products=new ArrayList<Product>();
				products=productDAO.getAllProduct();
		ListIterator<Product> prodIter=products.listIterator();
		List<Product> prodIDsInCart=new ArrayList<Product>();
		for(int i=0;i<prodIDs.size();i++)
		{
			for(int j=0;j<products.size();j++)
			{
				if(prodIDs.get(i).equals(products.get(j).getProductID()))
				{
					prodIDsInCart.add(products.get(j));
				}
			}
		}*/
		/*while(pIdsIter.hasNext())
		{
			while(prodIter.hasNext())
			{
				String tempProdId=prodIter.next().getProductID();
				String tempPId=(String) pIdsIter.next();
				if(tempPId.equals(tempProdId))
				{
					prodIDsInCart.add(prodIter.next());
				}
			}
		}*/
		/*
		map.put("prList", prodIDsInCart );
		//map.put("path", path);
		ModelAndView mv=new ModelAndView("/UserCart", map);
		mv.addObject("prList", prodIDsInCart);*/
		log.debug("End of method goToCart");
		return mv;
	}

	@RequestMapping("/addProductToCart")
	public ModelAndView addProductCart(@RequestParam("prID") String prodId)//,@RequestParam("prQty") int productQuantity
	{
		log.debug("Start of method addProductCart");
		String path="F:\\EclipseMain\\Projects\\SportsCar\\src\\main\\webapp\\Resources\\Images\\";
		List<Product> prodList=productDAO.getAllProduct();
		ModelAndView mv=null;
		Product product=productDAO.getProductById(prodId);
		User user=userDAO.getUserByName((String) session.getAttribute("UserName"));
		List<Cart> carts=cartDAO.getCartUser(user.getUserID());
		if(session.getAttribute("CurrentCartID")!=null && carts.size()!=0)
		{
			int crtID=(Integer)session.getAttribute("CurrentCartID");
			Cart cart=cartDAO.getCartById(crtID);
			
			Cart innerCart=new Cart();
			Iterator<Cart> iter=carts.iterator();
			while(iter.hasNext())
			{
				innerCart = iter.next();
			}
			if(innerCart.getCartStatus().equals("delivered") || innerCart.getCartStatus()=="deployed")
			{
				mv=new ModelAndView("/CreateCart");
				mv.addObject("userID", user.getUserID());
				log.debug("End of method addProductCart");
			}
			else
			{
				cart.getProductsinCart().add(product);
				int costADD=cart.getTotalCost();
				costADD+=product.getProductCost();
				cart.setTotalCost(costADD);
				cartDAO.updateCart(cart);
				mv=new ModelAndView("/Products");
				mv.addObject("prList", prodList);
				mv.addObject("path", path);
				log.debug("End of method addProductCart");
			}
			return mv;
		}
		else
		{
			mv=new ModelAndView("/CreateCart");
			mv.addObject("userID", user.getUserID());
			log.debug("End of method addProductCart");
			return mv;
		}
	}
	
	@RequestMapping("/createCartOfUser")
	public ModelAndView createCart(@RequestParam("usID") String userID,@RequestParam("usName") String userName,@RequestParam("addr1") String addrLine1,@RequestParam("addr2") String addrLine2,@RequestParam("addr3") String addrLine3,@RequestParam("addr4") int addrLine4)
	{
		String path="F:\\EclipseMain\\Projects\\Philosophia\\Philosophia\\src\\main\\webapp\\Resources\\Images\\";
		List<Product> prodList=productDAO.getAllProduct();
		Cart cart=new Cart();
		User user=userDAO.getUserById(userID);
		Date date=new Date();
		Address address=new Address();
		address.setStreet(addrLine1);
		address.setCity(addrLine2);
		address.setState(addrLine3);
		address.setPincode(addrLine4);
		cart.setUser_Id(userID);
		cart.setDelivery_name(userName);
		cart.setBillingAddress(address);
		cart.setUser_contact(user.getUserContact());
		cart.setTotalCost(0);
		cart.setDateAdded(date);
		cart.setCartStatus("Created");
		cartDAO.saveCart(cart);
		ModelAndView mv=new ModelAndView("/Products");
		mv.addObject("prList", prodList);
		mv.addObject("path", path);
		session.setAttribute("CurrentCartID", cart.getCartId());
		return mv;
	}
	
	@RequestMapping("goGenerateBill")
	public ModelAndView goGenerateBill()
	{
		cartDAO.generateBill((Integer)session.getAttribute("CurrentCartID"));
		ModelAndView mv=new ModelAndView("/Home");
		return mv;
	}
	
	@RequestMapping("/removeProdFromCart")
	public ModelAndView goRemoveProduct(@RequestParam("prID") String prodId)
	{
		Product product=productDAO.getProductById(prodId);
		Cart cart=cartDAO.getCartById((Integer)session.getAttribute("CurrentCartID"));
		cart.getProductsinCart().remove(product);
		cartDAO.updateCart(cart);
		ModelAndView mv=new ModelAndView("/UserCart");
		return mv;
	}
}
