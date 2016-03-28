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
public class PagesController {
	
	public PagesController(ApplicationContextProvider arg) {
		this.applicationContextProvider = arg;
	}
	
	public ApplicationContextProvider applicationContextProvider;
	
	@RequestMapping("/index")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse response) {
		
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		
		Good qwe = goodsDao.findByCategory(null).get(0);
		System.out.println(qwe.getName());
		System.out.println(qwe.getIdGood());
		System.out.println(qwe.getIdProvider());
		System.out.println(qwe.getPrice());
		System.out.println(qwe.getDescription());
		System.out.println(qwe.getCategory());
		System.out.println(qwe.getCountOnStock());
		System.out.println(qwe.getImagePath());
		return new ModelAndView("/WEB-INF/jsp/index.jsp");
	}
	
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/WEB-INF/jsp/login.jsp");
	}
	
}