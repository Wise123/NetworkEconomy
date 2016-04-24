package controllers;
 
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tables.Client;
import tables.Good;
import tables.Order;
import tables.OrderGood;
import tables.Provider;
import tables.RegOrderGood;
import tables.RegularOrder;
import util.ApplicationContextProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import dao.ClientsDao;
import dao.GoodsDao;
import dao.OrdersDao;
import dao.OrdersGoodDao;
import dao.ProvidersDao;
import dao.RegOrdersGoodDao;
import dao.RegularOrdersDao;

 

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
	/*@RequestMapping(value = "/createGood", method=RequestMethod.POST)
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
	}*/
	
	
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
	
	@RequestMapping(value = "/getAllOrdersInfo",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<Order> getAllOrdersInfo(HttpServletRequest request, HttpServletResponse response) {
		OrdersDao ordersDao =  (OrdersDao) applicationContextProvider.getApplicationContext().getBean("OrdersDao");
		//ObjectMapper mapper = new ObjectMapper();
		/*try {
			mapper.writeValue(response.getOutputStream(), ordersDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return ordersDao.findAll();
	}
	
	//http://localhost:8080/EconomicServlet/getOrder?orderId=2
	@RequestMapping("/getOrder")
	@ResponseBody
	public List<Good> getOrder(@RequestParam String orderId,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		return goodsDao.findGoodsByOrderId(orderId);
	}
	
	//http://localhost:35212/EconomicServlet/createOrder?idOrder=3&idClient=1&goodsJson=[{"idGood":1,"idProvider":"1","name":"Синие джинсы","price":4000,"description":"Американские джинсы высокого качества","category":"Джинсы","countOnStock":20,"imagePath":"resources/img/jeans1.jpg"},{"idGood":2,"idProvider":"2","name":"джинсыыыы","price":3000,"description":"Обычные солдатские джинсы","category":"Джинсы","countOnStock":15,"imagePath":"resources/img/jeans2.jpg"},{"idGood":3,"idProvider":"3","name":"Чёрные джинсы","price":5000,"description":"Хит сезона","category":"Джинсы","countOnStock":30,"imagePath":"resources/img/jeans3.jpg"},{"idGood":4,"idProvider":"4","name":"Желтая футболка","price":2000,"description":"Летние легкие футболки","category":"Футболки","countOnStock":27,"imagePath":"resources/img/t-shirt-1.png"}]
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	@ResponseBody
	public List<Good> createOrder(@RequestParam String goodsJson, @RequestParam Integer idOrder, @RequestParam Integer idClient, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersDao ordersDao =  (OrdersDao) applicationContextProvider.getApplicationContext().getBean("OrdersDao");
		OrdersGoodDao ordersGoodDao =  (OrdersGoodDao) applicationContextProvider.getApplicationContext().getBean("OrdersGoodDao");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			List<Good> goods = mapper.readValue(goodsJson,TypeFactory.defaultInstance().constructCollectionType(List.class,  
					   Good.class));
			//System.out.println(goods);
			
			Order order = new Order(idOrder, idClient, new java.sql.Date(new Date().getTime()), goods.stream().mapToInt(Good::getPrice).sum(), true);
			ordersDao.create(order);
			
			goods.forEach((good) -> {
				OrderGood og = new OrderGood(order.getIdOrder(), good.getIdGood());
				ordersGoodDao.create(og);
			});
			
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@RequestMapping("/deleteOrder")
	@ResponseBody
	public String deleteOrder(@RequestParam Integer idOrder,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			OrdersDao ordersDao =  (OrdersDao) applicationContextProvider.getApplicationContext().getBean("OrdersDao");
			ordersDao.deleteByIdOrder(idOrder);
		}catch (Exception e){
			e.printStackTrace();
			return "{\"error:\":\""+e.getMessage()+"\"}";
		}
		//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		return "{\"status:\":\"OK\"}";
	}
	
	@RequestMapping("/deleteRegularOrder")
	@ResponseBody
	public String deleteRegularOrder(@RequestParam Integer idRegOrder,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			RegularOrdersDao regularOrdersDao =  (RegularOrdersDao) applicationContextProvider.getApplicationContext().getBean("RegularOrdersDao");
			regularOrdersDao.deleteByIdOrder(idRegOrder);
		}catch (Exception e){
			e.printStackTrace();
			return "{\"error:\":\""+e.getMessage()+"\"}";
		}
		//request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		return "{\"status:\":\"OK\"}";
	}
	
	@RequestMapping(value = "/getAllRegularOrdersInfo",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<RegularOrder> getAllRegularOrdersInfo(HttpServletRequest request, HttpServletResponse response) {
		RegularOrdersDao regularOrdersDao =  (RegularOrdersDao) applicationContextProvider.getApplicationContext().getBean("RegularOrdersDao");
		//ObjectMapper mapper = new ObjectMapper();
		/*try {
			mapper.writeValue(response.getOutputStream(), ordersDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return regularOrdersDao.findAll();
	}
	
	//http://localhost:35212/EconomicServlet/getRegularOrder?idRegOrder=2
	@RequestMapping("/getRegularOrder")
	@ResponseBody
	public List<Good> getRegularOrder(@RequestParam String idRegOrder,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		return goodsDao.findGoodsByRegOrderId(idRegOrder);
	}
	//http://localhost:35212/EconomicServlet/createRegularOrder?countOfMonth=3&idRegOrder=3&idClient=1&goodsJson=[{"idGood":1,"idProvider":"1","name":"Синие джинсы","price":4000,"description":"Американские джинсы высокого качества","category":"Джинсы","countOnStock":20,"imagePath":"resources/img/jeans1.jpg"},{"idGood":2,"idProvider":"2","name":"джинсыыыы","price":3000,"description":"Обычные солдатские джинсы","category":"Джинсы","countOnStock":15,"imagePath":"resources/img/jeans2.jpg"},{"idGood":3,"idProvider":"3","name":"Чёрные джинсы","price":5000,"description":"Хит сезона","category":"Джинсы","countOnStock":30,"imagePath":"resources/img/jeans3.jpg"},{"idGood":4,"idProvider":"4","name":"Желтая футболка","price":2000,"description":"Летние легкие футболки","category":"Футболки","countOnStock":27,"imagePath":"resources/img/t-shirt-1.png"}]
	@RequestMapping(value = "/createRegularOrder", method = RequestMethod.POST)
	@ResponseBody
	public List<Good> createRegularOrder(@RequestParam String goodsJson, @RequestParam Integer idRegOrder, @RequestParam Integer idClient,@RequestParam Integer countOfMonth, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegularOrdersDao regularOrdersDao =  (RegularOrdersDao) applicationContextProvider.getApplicationContext().getBean("RegularOrdersDao");
		RegOrdersGoodDao regOrdersGoodDao =  (RegOrdersGoodDao) applicationContextProvider.getApplicationContext().getBean("RegOrdersGoodDao");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			List<Good> goods = mapper.readValue(goodsJson,TypeFactory.defaultInstance().constructCollectionType(List.class,  
					   Good.class));
			//System.out.println(goods);
			
			RegularOrder order = new RegularOrder(idRegOrder, idClient,goods.size(), goods.stream().mapToInt(Good::getPrice).sum(), countOfMonth);
			regularOrdersDao.create(order);
			
			goods.forEach((good) -> {
				RegOrderGood og = new RegOrderGood(order.getIdRegord(), good.getIdGood());
				regOrdersGoodDao.create(og);
			});
			
			return goods;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//@RequestParam("img_file") MultipartFile imgFile
	@RequestMapping(value = "/createGood", method=RequestMethod.POST)
	public String createGood(/*@RequestBody MultipartFile imgFile,*/ HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true) String jsonGood) throws UnsupportedEncodingException, IOException {
		System.out.println(jsonGood);
		GoodsDao goodsDao =  (GoodsDao) applicationContextProvider.getApplicationContext().getBean("GoodsDao");
		ObjectMapper mapper = new ObjectMapper();
		Good good = null;
		try {
			good = mapper.readValue(jsonGood, Good.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String tmp = new String(IOUtils.toByteArray(request.getInputStream()),"UTF-8");
		System.out.println(tmp);
		String tmp2 = tmp.substring(22, tmp.length()-1);
		
		byte[] decoded = Base64.getDecoder().decode(tmp2);
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		parameterMap.forEach( (x,y) -> {
			System.out.println(x + ": " + Arrays.toString(y));
		});
		String filename = Double.toString(good.getIdGood()  + new Random().nextDouble() );
		
		System.out.println(filename.toUpperCase());
		//if (!imgFile.isEmpty()) {
			try {
				byte[] bytes = decoded;//IOUtils.toByteArray(request.getInputStream());
				
				// Creating the directory to store file
				
				
				File serverFile = new File(request.getSession().getServletContext().getRealPath("/resources/img/") + "\\" +  filename + ".png");
				System.out.println(serverFile.getAbsolutePath());
				serverFile.createNewFile();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				if (good != null) {
					good.setImagePath("resources/img/" + filename + ".png");
					goodsDao.create(good);
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				return "You failed to upload " + filename + " => " + e.getMessage();
			}
		//} else {
		//	return "You failed to upload " + filename + " because the file was empty.";
		//}
		
	
		return "ok";
	}
	
	
	@RequestMapping(value="/getOrdersByUser", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<Order> getOrdersByUser(@RequestParam Integer userId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersDao ordersDao =  (OrdersDao) applicationContextProvider.getApplicationContext().getBean("OrdersDao");
		//ObjectMapper mapper = new ObjectMapper();
		/*try {
			mapper.writeValue(response.getOutputStream(), ordersDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return ordersDao.findByIdClient(userId);
	}
	
	
	@RequestMapping(value="/getRegularOrdersByUser", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<RegularOrder> getRegularOrdersByUser(@RequestParam Integer userId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegularOrdersDao regularOrdersDao =  (RegularOrdersDao) applicationContextProvider.getApplicationContext().getBean("RegularOrdersDao");
		//ObjectMapper mapper = new ObjectMapper();
		/*try {
			mapper.writeValue(response.getOutputStream(), ordersDao.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return regularOrdersDao.findByIdClient(userId);
	}
	
	
	@RequestMapping(value="/changePassword",produces = "text/plain",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public void changePassword(HttpServletResponse response,@RequestParam(required=true) Integer id,@RequestParam(required=true) String newPassword) throws JsonProcessingException {
		//response.setCharacterEncoding("UTF-8");
		String errorJson = "{\"error\": \"incorrect login or pass\"}";	
		ClientsDao clientsDao =  (ClientsDao) applicationContextProvider.getApplicationContext().getBean("ClientsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			int Upd = clientsDao.updatePass(id,newPassword);
			//String result = new String(mapper.writeValueAsString(client).getBytes(),"UTF-8");
			//System.out.println(result);
			response.getWriter().write("OK");
		}catch (EmptyResultDataAccessException | IOException e){
			try {
				response.getOutputStream().write(errorJson.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value="/allClients",produces = "text/plain",method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public List<Client> getAllClients(HttpServletResponse response) throws JsonProcessingException {
		String errorJson = "{\"error\": \"incorrect login or pass\"}";	
		ClientsDao clientsDao =  (ClientsDao) applicationContextProvider.getApplicationContext().getBean("ClientsDao");
		ObjectMapper mapper = new ObjectMapper();
		try {
			return clientsDao.findAll();
		}catch (EmptyResultDataAccessException e){
			try {
				response.getOutputStream().write(errorJson.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/createUser", method=RequestMethod.POST)
	public void createUser(HttpServletRequest request, HttpServletResponse response, @RequestParam(required=true) String jsonUser) {
		System.out.println(jsonUser);
		ClientsDao clientsDao =  (ClientsDao) applicationContextProvider.getApplicationContext().getBean("ClientsDao");
		ObjectMapper mapper = new ObjectMapper();
		Client client= null;
		try {
			client = mapper.readValue(jsonUser, Client.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (client != null) {
			clientsDao.create(client);
		}
		System.out.println(client);
	}
	

}