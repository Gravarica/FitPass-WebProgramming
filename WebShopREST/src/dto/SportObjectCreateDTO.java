package dto;

import java.util.Base64;

import beans.Location;
import enums.SportObjectType;

public class SportObjectCreateDTO {

	private String name;
	private SportObjectType type;
	private Location location;
	private Base64 logo;
	
	private String managerUsername;
	
	public SportObjectCreateDTO() {
		super();
	}
	
	public SportObjectCreateDTO(String name, SportObjectType type, Location location, Base64 logo,String manager) {
		super();
		this.name = name;
		this.type = type;
		this.location = location;
		this.logo = logo;
		this.managerUsername = manager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SportObjectType getType() {
		return type;
	}

	public void setType(SportObjectType type) {
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Base64 getLogo() {
		return logo;
	}

	public void setLogo(Base64 logo) {
		this.logo = logo;
	}

	public String getManagerUsername() {
		return managerUsername;
	}

	public void setManagerUsername(String manager) {
		this.managerUsername = manager;
	}
}
