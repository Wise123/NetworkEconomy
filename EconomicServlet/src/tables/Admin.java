package tables;

public class Admin {
	int idAdmin;
	String login;
	int password;
	String email;
	
	public Admin(int idAdmin, String login, int password, String email) {
		super();
		this.idAdmin = idAdmin;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
