package tables;

public class Good {
	int idGood;
	String idProvider;
	String name;
	int price;
	String description;
	String category;
	int countOnStock;
	String imagePath;

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getIdGood() {
		return idGood;
	}
	public void setIdGood(int idGood) {
		this.idGood = idGood;
	}
	public String getIdProvider() {
		return idProvider;
	}
	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCountOnStock() {
		return countOnStock;
	}
	public void setCountOnStock(int countOnStock) {
		this.countOnStock = countOnStock;
	}
	public Good(int idGood, String idProvider, String name, int price, String description, String category,
			int countOnStock, String imagePath) {
		super();
		this.idGood = idGood;
		this.idProvider = idProvider;
		this.name = name;
		this.price = price;
		this.description = description;
		this.category = category;
		this.countOnStock = countOnStock;
		this.imagePath = imagePath;
	}
	public Good() {
	}
	
}
