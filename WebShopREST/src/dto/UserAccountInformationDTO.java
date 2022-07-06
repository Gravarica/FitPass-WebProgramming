package dto;

import java.util.Date;

import beans.CustomerType;
import beans.User;
import enums.Gender;
import enums.Role;

public class UserAccountInformationDTO {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private Gender gender;
	private Date dateOfBirth;
	private Role role;
	private CustomerType type;
	private int totalPoints;
	
	public UserAccountInformationDTO() {}

	public UserAccountInformationDTO(String firstName, String lastName, String username, String password, Gender gender,
			Date dateOfBirth, Role role, CustomerType type, int totalPoints) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.role = role;
		this.type = type;
		this.totalPoints = totalPoints;
	}

	public UserAccountInformationDTO(User user) {
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.gender = user.getGender();
		this.dateOfBirth = user.getDateOfBirth();
		this.role = user.getRole();
		this.type = user.getCustomerType();
		this.totalPoints = user.getTotalPoints();
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
}
