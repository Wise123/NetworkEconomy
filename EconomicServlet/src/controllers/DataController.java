package controllers;
 
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		String category = request.getParameter("category");
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), goodsDao.findByCategory(category));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/WEB-INF/jsp/login.jsp");
	}*/
	
}