package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	public HttpSession session;
	
@RequestMapping("/")
public ModelAndView show()
{
	ModelAndView mv=new ModelAndView("/Home");
	session.setAttribute("AdminMsg",null);
	session.setAttribute("loginMsg","not logged in");
		return mv;
}

@RequestMapping("/clickHome")
public ModelAndView showHome()
{
	ModelAndView mv=new ModelAndView("/Home");
	
	return mv;
}

@RequestMapping("/Login")
public ModelAndView showLoginPage()
{
	ModelAndView mv=new ModelAndView("/Login");
	return mv;
}
@RequestMapping("/Register")
public ModelAndView showRegistrationPage()
{
	ModelAndView mv=new ModelAndView("/Register");
	return mv;
}
@RequestMapping("/About us")
public ModelAndView showAboutusPage()
{
	ModelAndView mv=new ModelAndView("/About us");
	return mv;
}
@RequestMapping("/Admin")
public ModelAndView showAdminPage()
{
	ModelAndView mv=new ModelAndView("/Admin");
	return mv;
}
@RequestMapping("/Products")
public ModelAndView showProductsPage()
{
	ModelAndView mv=new ModelAndView("/Products");
	return mv;
}
}
