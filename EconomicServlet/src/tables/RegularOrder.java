package tables;

public class RegularOrder {
	int idRecord;
	int idClient;
	int countOfGoods;
	String name;
	int price;
	int countOfMonth;
	public RegularOrder(int idRecord, int idClient, int countOfGoods, String name, int price, int countOfMonth) {
		super();
		this.idRecord = idRecord;
		this.idClient = idClient;
		this.countOfGoods = countOfGoods;
		this.name = name;
		this.price = price;
		this.countOfMonth = countOfMonth;
	}
	public int getIdRecord() {
		return idRecord;
	}
	public void setIdRecord(int idRecord) {
		this.idRecord = idRecord;
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
