package controllers;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import util.ApplicationContextProvider;

 

@Controller
public class PagesController {
	
	public PagesController(ApplicationContextProvider arg) {
		this.applicationContextProvider = arg;
	}
	
	public ApplicationContextProvider applicationContextProvider;
	
	@RequestMapping("/index")
	public ModelAndView getIndexView(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/WEB-INF/jsp/index.jsp");
	}
	
	/*
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/WEB-INF/jsp/login.jsp");
	}*/
	
}