package beans;

import enums.CustomerTypeName;

public class CustomerType {

	private CustomerTypeName name;
	private double discount;
	private int pointsRequired;
	
	public CustomerType(CustomerTypeName name, double discount, int pointsRequired) {
		super();
		this.name = name;
		this.discount = discount;
		this.pointsRequired = pointsRequired;
	}
	
	public CustomerType(CustomerTypeName name) {
		this.name = name;
		this.discount = 10;
		this.pointsRequired = 1000;
	}
	
	public CustomerType() {}

	public CustomerTypeName getName() {
		return name;
	}

	public void setName(CustomerTypeName name) {
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
