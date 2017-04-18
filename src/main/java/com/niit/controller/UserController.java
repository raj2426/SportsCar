package com.niit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.dao.CartDAO;
import com.niit.dao.ProductDAO;
import com.niit.dao.UserDAO;
import com.niit.model.Product;
import com.niit.model.User;

@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	public HttpSession session;
	
	@RequestMapping("/login")
	public ModelAndView userlogin(@RequestParam("uname") String userid,@RequestParam("upwd") String userpwd)
	{
		log.debug("Start of method userlogin");
		User user=userDAO.getUserById(userid);
		ModelAndView mv;
		String pwd=user.getUserPassword();
		if(pwd.equals(userpwd))
		{
			if(user.getUserRole().equals("Admin"))
			{
				mv=new ModelAndView("/Home");
				session.setAttribute("AdminMsg","AdminLoggedIn");
				session.setAttribute("UserName", user.getUserName());
				session.setAttribute("loginMsg", null);
			}
			else
			{
				mv=new ModelAndView("/Home");
				session.setAttribute("UserMsg","LoggedIn");
				session.setAttribute("UserName", user.getUserName());
				session.setAttribute("loginMsg", null);
			}
		}
		else
		{
			mv=new ModelAndView("/Home");
			session.setAttribute("loginMsg", "notloggedIn");
			mv.addObject("msg","Invlaid Login");
		}
		log.debug("End of method userlogin");
		return mv;
	}
	
	@RequestMapping("/goRegister")
	public ModelAndView showRegister()
	{
		log.debug("Start of method showRegister");
		ModelAndView mv=new ModelAndView("/Register","command", new User());
		log.debug("End of method showRegister");
		return mv;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public ModelAndView insertUser(@ModelAttribute User user)
	{
		log.debug("Start of method insertUser");
		user.setUserRole("ROLE_USER");
		userDAO.saveUser(user);
		ModelAndView mv=new ModelAndView("/Home");
		log.debug("End of method insertUser");
		return mv;
	}
	
	@RequestMapping("/goLogout")
	public ModelAndView logoutUser()
	{
		log.debug("Start of method logoutUser");
		ModelAndView mv=new ModelAndView("/Home");
		session.removeAttribute("UserMsg");
		session.removeAttribute("UserName");
		session.removeAttribute("AdminMsg");
		session.setAttribute("loginMsg", "notloggedIn");
		log.debug("End of method logoutUser");
		return mv;
	}
	
	@RequestMapping("/goCart")
	public ModelAndView goToCart(Map<String, Object> map)
	{
		log.debug("Start of method goToCart");
		String path="F:\\EclipseMain\\Projects\\Philosophia\\Philosophia\\src\\main\\webapp\\Resources\\Images\\";
		List<Product> prodList=productDAO.getAllProduct();
		map.put("prList", prodList );
		map.put("path", path);
		ModelAndView mv=new ModelAndView("/AddtoCart",map);
		log.debug("End of method goToCart");
		return mv;
	}

	//viewCart
	//
	
}
