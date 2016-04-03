package tables;

public class RegularOrder {
	int idRegord;
	int idClient;
	int countOfGoods;
	String name;
	int price;
	int countOfMonth;
	public RegularOrder(int idRegord, int idClient, int countOfGoods, String name, int price, int countOfMonth) {
		super();
		this.idRegord = idRegord;
		this.idClient = idClient;
		this.countOfGoods = countOfGoods;
		this.name = name;
		this.price = price;
		this.countOfMonth = countOfMonth;
	}
	public RegularOrder() {
		// TODO Auto-generated constructor stub
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
	
}
