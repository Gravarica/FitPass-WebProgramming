package beans;

public class Artikal {

	private String id;
	private String name;
	private String type;
	private double price;
	private int quantity;
	private boolean available;
	
	public Artikal() {}

	public Artikal(String id, String name, String type, double price, int quantity, boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.available = available;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public void checkAvailable() {
		if (available && (price == 0 || quantity == 0)) {
			available = false;
		}
		if (!available && price > 0 && quantity > 0) {
			available = true;
		}
	}
	
	public double getTotalPrice() {
		return price*quantity;
	}
}
