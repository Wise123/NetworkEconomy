package controllers;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.GoodsDao;
import tables.Good;
import util.ApplicationContextProvider;

 

@Controller
public class DataController {
	
	public DataController(ApplicationContextProvider arg) {
		this.applicationContextProvider = arg;
	}
	
	public ApplicationContextProvider applicationContextProvider;
	
	@RequestMapping("/getGoodsByCategory")
	public void getGoodsByCategory(HttpServletRequest request, HttpServletResponse response) {
		
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		
	}
	
	
	/*@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/WEB-INF/jsp/login.jsp");
	}*/
	
}