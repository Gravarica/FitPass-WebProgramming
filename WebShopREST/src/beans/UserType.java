package beans;

import enums.UserTypeName;

public class UserType {

	private UserTypeName name;
	private double discount;
	private int pointsRequired;
	
	public UserType(UserTypeName name, double discount, int pointsRequired) {
		super();
		this.name = name;
		this.discount = discount;
		this.pointsRequired = pointsRequired;
	}
	
	public UserType() {}

	public UserTypeName getName() {
		return name;
	}

	public void setName(UserTypeName name) {
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
