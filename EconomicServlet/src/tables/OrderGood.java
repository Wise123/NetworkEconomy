package tables;

public class OrderGood {
	int idOrder;
	int idGood;
	public OrderGood() {
	}
	public OrderGood(int idOrder, int idGood) {
		super();
		this.idOrder = idOrder;
		this.idGood = idGood;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdGood() {
		return idGood;
	}
	public void setIdGood(int idGood) {
		this.idGood = idGood;
	}
	
	
}
