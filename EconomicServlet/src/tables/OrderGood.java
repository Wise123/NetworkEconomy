package tables;

public class OrderGood {
	int idOg;
	int idOrder;
	int idGood;
	public OrderGood(int idOg, int idOrder, int idGood) {
		super();
		this.idOg = idOg;
		this.idOrder = idOrder;
		this.idGood = idGood;
	}
	public int getIdOg() {
		return idOg;
	}
	public void setIdOg(int idOg) {
		this.idOg = idOg;
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
