package tables;

public class Client {
	int idClient;
	int idAdmin;
	String name;
	String lastName;
	String surName;
	String city;
	String country;
	int postIndex;
	int password;
	
	
	
	
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
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
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
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
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public Client(int idClient, int idAdmin, String name, String lastName, String surName, String city, String country,
			int postIndex, int password) {
		super();
		this.idClient = idClient;
		this.idAdmin = idAdmin;
		this.name = name;
		this.lastName = lastName;
		this.surName = surName;
		this.city = city;
		this.country = country;
		this.postIndex = postIndex;
		this.password = password;
	}
}
