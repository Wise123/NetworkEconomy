package tables;

import java.sql.Date;

public class Order {
	int idOrder;
	int idClient;
	String client;
	Date date;
	int price;
	boolean status;
	public Order(int idOrder, int idClient, Date date, int price, boolean status) {
		super();
		this.idOrder = idOrder;
		this.idClient = idClient;
		this.date = date;
		this.price = price;
		this.status = status;
	}
	public Order() {
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	
}
