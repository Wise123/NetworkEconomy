package tables;

public class Provider {
	String idProvider;
	String address;
	String title;
	String description;
	public Provider(String idProvider, String address, String title, String description) {
		super();
		this.idProvider = idProvider;
		this.address = address;
		this.title = title;
		this.description = description;
	}
	public Provider() {
		// TODO Auto-generated constructor stub
	}
	public String getIdProvider() {
		return idProvider;
	}
	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
