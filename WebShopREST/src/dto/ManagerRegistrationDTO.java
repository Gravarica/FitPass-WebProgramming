package dto;

import java.util.Date;

import beans.SportObject;
import enums.Gender;
import enums.Role;

public class ManagerRegistrationDTO extends RegistrationDTO{

	private int sportObjectId;
	
	public ManagerRegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManagerRegistrationDTO(String username, String password, String firstName, String lastName, Gender gender,
			Role role, Date dateOfBirth) {
		super(username, password, firstName, lastName, gender, role, dateOfBirth);
		// TODO Auto-generated constructor stub
	}

	public int getSportObjectId() {
		return sportObjectId;
	}

	public void setSportObjectId(int sportObject) {
		this.sportObjectId = sportObject;
	}

	
}
