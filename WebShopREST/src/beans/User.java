package beans;

import java.util.Date;

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
	protected UserType userType;
	
	public User() {
		super();
	}
	
	public User(String username, String password, String firstName, String lastName, 
			Gender gender, Role role, Date dateOfBirth, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.userType = userType;
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


	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Date getBirthday() {
		return dateOfBirth;
	}

	public void setBirthday(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
		
}
