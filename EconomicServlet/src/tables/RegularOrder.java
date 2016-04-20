package tables;

public class RegularOrder {
	int idRegord;
	int idClient;
	String client;
	int countOfGoods;
	int price;
	int countOfMonth;
	public RegularOrder(int idRegord, int idClient, int countOfGoods, int price, int countOfMonth) {
		super();
		this.idRegord = idRegord;
		this.idClient = idClient;
		this.countOfGoods = countOfGoods;
		this.price = price;
		this.countOfMonth = countOfMonth;
	}
	public RegularOrder() {
	}
	public int getIdRegord() {
		return idRegord;
	}
	public void setIdRegord(int idRegord) {
		this.idRegord = idRegord;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getCountOfGoods() {
		return countOfGoods;
	}
	public void setCountOfGoods(int countOfGoods) {
		this.countOfGoods = countOfGoods;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCountOfMonth() {
		return countOfMonth;
	}
	public void setCountOfMonth(int countOfMonth) {
		this.countOfMonth = countOfMonth;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
}
