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
import dao.OrdersDao;
import dao.ProvidersDao;
import tables.Client;
import tables.Good;
import tables.Provider;
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
	@ResponseBody
	public String deleteGood(@RequestParam Integer idGood,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		goodsDao.deleteById(idGood);
		}catch (Exception e){
			return "{\"error:\":\""+e.getMessage()+"\"}";
		}
		//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		return "{\"status:\":\"OK\"}";
	}
	
	
	
	
	@RequestMapping(value="/login",produces = "text/plain",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public void login(HttpServletResponse response,@RequestParam(required=true) String name,@RequestParam(required=true) String password) throws JsonProcessingException {
		//response.setCharacterEncoding("UTF-8");
		String errorJson = "{\"error\": \"incorrect login or pass\"}";	
		ClientsDao clientsDao =  (ClientsDao) applicationContextProvider.getApplicationContext().getBean("ClientsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Client client = clientsDao.findByNameAndPass(name,password);
			//String result = new String(mapper.writeValueAsString(client).getBytes(),"UTF-8");
			System.out.println(client.getLastName());
			//System.out.println(result);
			response.getWriter().write(mapper.writeValueAsString(client));
		}catch (EmptyResultDataAccessException | IOException e){
			try {
				response.getOutputStream().write(errorJson.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
			
			

		//return new ModelAndView("/WEB-INF/jsp/login.jsp");
	}
	//http://localhost:8080/EconomicServlet/createProvider?jsonProvider={"idProvider":"5","address":"Tailand","title":"Law","description":"some descr"}
	@RequestMapping(value = "/createProvider", method=RequestMethod.POST)
	public void createProvider(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true) String jsonProvider) {
		System.out.println(jsonProvider);
		ProvidersDao providersDao =  (ProvidersDao) applicationContextProvider.getApplicationContext().getBean("ProvidersDao");
		ObjectMapper mapper = new ObjectMapper();
		Provider provider= null;
		try {
			provider = mapper.readValue(jsonProvider, Provider.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (provider != null) {
			providersDao.create(provider);
		}
		System.out.println(provider);
	}
	
	//http://localhost:8080/EconomicServlet/updateProvider?jsonProvider={"idProvider":"5","address":"Tailand","title":"Law","description":"some updated descr"}
	@RequestMapping(value = "/updateProvider", method=RequestMethod.POST)
	public void updateProvider(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true) String jsonProvider) {
		System.out.println(jsonProvider);
		ProvidersDao providersDao =  (ProvidersDao) applicationContextProvider.getApplicationContext().getBean("ProvidersDao");
		ObjectMapper mapper = new ObjectMapper();
		Provider provider= null;
		try {
			provider = mapper.readValue(jsonProvider, Provider.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (provider != null) {
			providersDao.update(provider);
		}
		System.out.println(provider);
	}
	
	@RequestMapping("/deleteProvider")
	@ResponseBody
	public String deleteProvider(@RequestParam Integer idProvider,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ProvidersDao providersDao =  (ProvidersDao) applicationContextProvider.getApplicationContext().getBean("ProvidersDao");
			providersDao.deleteById(idProvider);
		}catch (Exception e){
			return "{\"error:\":\""+e.getMessage()+"\"}";
		}
		//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		return "{\"status:\":\"OK\"}";
	}
	
	@RequestMapping("/getAllOrdersInfo")
	public void getAllOrdersInfo(HttpServletRequest request, HttpServletResponse response) {
		OrdersDao ordersDao =  (OrdersDao) applicationContextProvider.getApplicationContext().getBean("OrdersDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(response.getOutputStream(), ordersDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//http://localhost:8080/EconomicServlet/getOrder?orderId=2
	@RequestMapping("/getOrder")
	@ResponseBody
	public List<Good> getOrder(@RequestParam String orderId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		return goodsDao.findGoodsByOrderId(orderId);
	}
	
}