package beans;

import java.util.ArrayList;
import java.util.Date;

import dto.LoginDTO;
import dto.RegistrationDTO;
import enums.CustomerTypeName;
import enums.Gender;
import enums.Role;

public class User extends Entity {

	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected Gender gender;
	protected Role role;
	protected Date dateOfBirth;
	protected CustomerType customerType;
	private SportObject object;
	private Subscription membershipFee;
	private ArrayList<SportObject> objectsVisited;
	private int totalPoints;
	private ArrayList<TrainingHistory> trainingHistory;
	
	public User() {
		super();
	}
	
	public User(int id, String username, String password, String firstName, String lastName, 
			Gender gender, Role role, Date dateOfBirth, CustomerType userType) {
		super(id);
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.customerType = userType;
	}
	
	// Konstruktor za registraciju kupca
	public User(RegistrationDTO dto) {
		instantiateData(dto);
	}
	
	// Konstruktor za registraciju menadzera
	public User(String username, String password, String firstName, String lastName, Gender gender, Date dateOfBirth, SportObject sportObject) {
		
	}
	
	private void instantiateData(RegistrationDTO dto) {
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.gender = dto.getGender();
		this.role = dto.getRole();
		this.dateOfBirth = dto.getDateOfBirth();
		instantiateEmptyFields();
		if (role == Role.CUSTOMER) {
			instantiateCustomerFields();
		} else {
			instantiateTrainerFields();
		}
	}
	
	private void instantiateEmptyFields() {
		this.object = null;
		this.totalPoints = -1;
		this.membershipFee = null;
		this.objectsVisited = null;
		this.trainingHistory = null;
		this.customerType = null;
	}
	
	private void instantiateTrainerFields() {
		this.trainingHistory = new ArrayList<TrainingHistory>();
	}
	
	private void instantiateCustomerFields() {
		this.totalPoints = 0;
		this.customerType = new CustomerType(CustomerTypeName.BRONZE);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType userType) {
		this.customerType = userType;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public SportObject getObject() {
		return object;
	}

	public void setObject(SportObject object) {
		this.object = object;
	}

	public Subscription getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(Subscription membershipFee) {
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

	public ArrayList<TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(ArrayList<TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	
	public boolean passwordMatches(LoginDTO dto) {
		return password.equals(dto.getPassword());
	}
		
}
