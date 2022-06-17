package beans;

public class UserType {

	private String name;
	private double discount;
	private int pointsRequired;
	
	public UserType(String name, double discount, int pointsRequired) {
		super();
		this.name = name;
		this.discount = discount;
		this.pointsRequired = pointsRequired;
	}
	
	public UserType() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(int pointsRequired) {
		this.pointsRequired = pointsRequired;
	}
	
	
	
	
}
