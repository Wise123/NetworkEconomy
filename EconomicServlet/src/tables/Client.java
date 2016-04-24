package tables;

public class Client {
	int idClient;
	String name;
	String lastName;
	String surname;
	String city;
	String country;
	int postIndex;
	String password;
	boolean isAdmin;
	
	
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSurName() {
		return surname;
	}
	public void setSurName(String surname) {
		this.surname = surname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPostIndex() {
		return postIndex;
	}
	public void setPostIndex(int postIndex) {
		this.postIndex = postIndex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	
	
	public Client() {
	}
	public Client(int idClient, String name, String lastName, String surname, String city, String country,
			int postIndex, String password, boolean isAdmin) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.lastName = lastName;
		this.surname = surname;
		this.city = city;
		this.country = country;
		this.postIndex = postIndex;
		this.password = password;
		this.isAdmin = isAdmin;
	}
}
