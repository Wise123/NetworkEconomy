package tables;

public class Card {

	int idCard;
	int idClient;
	int number;
	int yearOfEnd;
	
	public Card(int idCard, int idClient, int number, int yearOfEnd) {
		super();
		this.idCard = idCard;
		this.idClient = idClient;
		this.number = number;
		this.yearOfEnd = yearOfEnd;
	}
	
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getYearOfEnd() {
		return yearOfEnd;
	}
	public void setYearOfEnd(int yearOfEnd) {
		this.yearOfEnd = yearOfEnd;
	}
}
