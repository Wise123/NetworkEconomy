package controllers;
 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ClientsDao;
import dao.GoodsDao;
import dao.ProvidersDao;
import tables.Client;
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
	
	@RequestMapping(value = "/getAllGoods", produces = "text/plain;charset=UTF-8",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<Good> getAllGoods(HttpServletRequest request, HttpServletResponse response) {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		return goodsDao.findAll();
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
	
	//http://localhost:8080/EconomicServlet/createGood?jsonGood={"idGood":5,"idProvider":"4","name":" Супер футболка","price":2000,"description":"Летние легкие футболки","category":"Футболки","countOnStock":27,"imagePath":""}
	//does work
	//@RequestParam("img_file") MultipartFile imgFile
	@RequestMapping(value = "/createGood", method=RequestMethod.POST)
	public void createGood(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true) String jsonGood) {
		System.out.println(jsonGood);
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		Good good = null;
		try {
			good = mapper.readValue(jsonGood, Good.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (good != null) {
			goodsDao.create(good);
		}
		System.out.println(good);
	}
	
	
	//http://localhost:8080/EconomicServlet/updateGood?jsonGood={"idGood":4,"idProvider":"4","name":" Говенная футболка","price":2000,"description":"Летние легкие футболки","category":"Футболки","countOnStock":27,"imagePath":"resources/img/t-shirt-1.png"}
	//does work
	@RequestMapping(value = "/updateGood", method=RequestMethod.POST)
	public void updateGood(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true) String jsonGood) {
		System.out.println(jsonGood);
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		Good good = null;
		try {
			good = mapper.readValue(jsonGood, Good.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (good != null) {
			goodsDao.update(good);
		}
		System.out.println(good);
	}
	//http://localhost:8080/EconomicServlet/deleteGood?idGood=4 does work
	@RequestMapping("/deleteGood")
	public void deleteGood(@RequestParam Integer idGood,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		goodsDao.deleteById(idGood);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}
	
	
	
	
	@RequestMapping(value="/login",produces = "text/plain;charset=UTF-8",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String login(HttpServletResponse response,@RequestParam(required=true) String login,@RequestParam(required=true) String password) throws JsonProcessingException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		String errorJson = "{\"error\": \"incorrect login or pass\"}";	
		ClientsDao clientsDao =  (ClientsDao) applicationContextProvider.getApplicationContext().getBean("ClientsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Client client = clientsDao.findByNameAndPass(login,password);
			String result = new String(mapper.writeValueAsString(client).getBytes(),"UTF-8");
			System.out.println(client.getLastName());
			System.out.println(result);
			return result;
		}catch (EmptyResultDataAccessException | UnsupportedEncodingException e){
			return errorJson;
		}
		
			
			

		//return new ModelAndView("/WEB-INF/jsp/login.jsp");
	}
	
	
}