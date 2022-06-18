package dto;

import java.util.Date;

import beans.SportObject;
import enums.Gender;
import enums.Role;

public class ManagerRegistrationDTO extends RegistrationDTO{

	private SportObject sportObject;
	
	public ManagerRegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManagerRegistrationDTO(String username, String password, String firstName, String lastName, Gender gender,
			Role role, Date dateOfBirth) {
		super(username, password, firstName, lastName, gender, role, dateOfBirth);
		// TODO Auto-generated constructor stub
	}

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}

	
}
