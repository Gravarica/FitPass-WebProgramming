package dto;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import beans.CustomerType;
import beans.User;
import enums.Gender;
import enums.Role;
import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class UserAccountInformationDTO {
	private String firstName;
	private String lastName;
	private String username;
	private Gender gender;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateOfBirth;
	private Role role;
	private CustomerType type;
	private int totalPoints;
	
	public UserAccountInformationDTO() {}

	public UserAccountInformationDTO(String firstName, String lastName, String username, Gender gender,
			LocalDate dateOfBirth, Role role, CustomerType type, int totalPoints) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
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
