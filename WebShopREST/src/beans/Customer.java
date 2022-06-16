package beans;

import enums.Gender;
import enums.UserType;

public class Customer extends User{

	private double membershipFee;
	//private ArrayList<SportObject> objectsVisited;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password, String firstName, String lastName, Gender gender,
			UserType userType) {
		super(username, password, firstName, lastName, gender, userType);
		// TODO Auto-generated constructor stub
	}

	public double getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(double membershipFee) {
		this.membershipFee = membershipFee;
	}
	
	
}
