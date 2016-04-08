package controllers;
 
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.GoodsDao;
import dao.ProvidersDao;
import util.ApplicationContextProvider;

 

@Controller
public class DataController {
	
	public DataController(ApplicationContextProvider arg) {
		this.applicationContextProvider = arg;
	}
	
	public ApplicationContextProvider applicationContextProvider;
	
	@RequestMapping("/getGoodsByCategory")
	public void getGoodsByCategory(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), goodsDao.findByCategory(category));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getGoodsByCategoryWithProividerName")
	public void getGoodsByCategoryWithProivderName(HttpServletRequest request, HttpServletResponse response) {
		String category = request.getParameter("category");
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), goodsDao.findByCategoryWithProviderName(category));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getAllGoods")
	public void getAllGoods(HttpServletRequest request, HttpServletResponse response) {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), goodsDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getAllGoodsWithProviderName")
	public void getAllGoodsWithProviderName(HttpServletRequest request, HttpServletResponse response) {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), goodsDao.findAllWithProviderName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getAllCategories")
	public void getAllCategories(HttpServletRequest request, HttpServletResponse response) {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), goodsDao.selectAllCategories());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getAllProviders")
	public void getAllProviders(HttpServletRequest request, HttpServletResponse response) {
		ProvidersDao goodsDao =  (ProvidersDao) applicationContextProvider.getApplicationContext().getBean("ProvidersDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), goodsDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/WEB-INF/jsp/login.jsp");
	}*/
	
}