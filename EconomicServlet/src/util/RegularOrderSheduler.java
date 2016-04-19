package util;

import java.util.Date;
import java.util.List;

import tables.Card;
import tables.Client;
import tables.RegularOrder;
import dao.CardsDao;
import dao.ClientsDao;
import dao.RegularOrdersDao;


public class RegularOrderSheduler {
	
	public RegularOrderSheduler(ApplicationContextProvider arg) {
		this.applicationContextProvider = arg;
	}
	
	public ApplicationContextProvider applicationContextProvider;
	
	public void process(){
		
		//пройтись по регялрным заказами- для каждого найти юзера - дял каждого юзера найти карточку и снять сумму заказа
		RegularOrdersDao regularOrdersDao =  (RegularOrdersDao) applicationContextProvider.getApplicationContext().getBean("RegularOrdersDao");
		CardsDao cardsDao =  (CardsDao) applicationContextProvider.getApplicationContext().getBean("CardsDao");
		ClientsDao clientsDao =  (ClientsDao) applicationContextProvider.getApplicationContext().getBean("ClientsDao");
		List<RegularOrder> regOrders = regularOrdersDao.findAll();
		System.out.print("before cash withdrawal - ");
		for(RegularOrder order: regOrders){
			int clientId = order.getIdClient();
			Client client = clientsDao.findById(clientId);
			Card card = cardsDao.findByClientId(clientId);
			System.out.print(client.getName() + ": " + card.getNumber() + " ");
			card.setNumber(card.getNumber() - order.getPrice());
			cardsDao.update(card);
			
		}
		System.out.println();
		System.out.print("after cash withdrawal  - ");
		for(RegularOrder order: regOrders){
			int clientId = order.getIdClient();
			Client client = clientsDao.findById(clientId);
			
			Card card = cardsDao.findByClientId(clientId);
			
			System.out.print(client.getName() + ": " + card.getNumber() + " ");
			
		}
		System.out.println();
		System.out.println("-------------------------------------------------");
		
		
	}

}
