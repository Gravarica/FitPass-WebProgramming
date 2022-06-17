package beans;

import java.util.Date;

import enums.Gender;
import enums.Role;


public class Manager extends User {

	private SportObject object;

	public Manager() {
		super();
	}

	public Manager(int id, String username, String password, String name, String surname, Gender gender, 
			Date birthday, Role role,CustomerType type,SportObject object) {
		super(id, username, password, name, surname, gender, role, birthday, type);
		this.object = object;
	}

	public SportObject getObject() {
		return object;
	}

	public void setObject(SportObject object) {
		this.object = object;
	}

}
