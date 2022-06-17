package beans;

import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.Subject;

import enums.Gender;
import enums.Role;

public class Customer extends User{

	private Subscription membershipFee;
	private ArrayList<SportObject> objectsVisited;
	private int totalPoints;

	public Customer() {
		super();
		this.objectsVisited = new ArrayList<SportObject>();
	}

	public Customer(String username, String password, String firstName, String lastName, Gender gender,
			Role role, Date birthday, UserType userType,Subscription membershipFee, ArrayList<SportObject> objectsVisited,int totalPoints) {
		super(username, password, firstName, lastName, gender, role, birthday, userType);
		this.membershipFee = membershipFee;
		this.objectsVisited = objectsVisited;
		this.totalPoints = totalPoints;
	}

	public Subscription  getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(Subscription  membershipFee) {
		this.membershipFee = membershipFee;
	}

	public ArrayList<SportObject> getObjectsVisited() {
		return objectsVisited;
	}

	public void setObjectsVisited(ArrayList<SportObject> objectsVisited) {
		this.objectsVisited = objectsVisited;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}
