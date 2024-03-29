package dto;

import java.util.Base64;

import beans.Location;
import enums.SportObjectType;

public class SportObjectCreateDTO {

	private String name;
	private String type;
	private Location location;
	private Base64 logo;
	private String managerUsername;
	private String startTime;
	private String endTime;
	
	public SportObjectCreateDTO() {
		super();
	}
	
	public SportObjectCreateDTO(String name, String type, Location location, Base64 logo,String manager,String startTime,String endTime) {
		super();
		this.name = name;
		this.type = type;
		this.location = location;
		this.logo = logo;
		this.managerUsername = manager;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public SportObjectType convertToType(String type) {
		for(SportObjectType it : SportObjectType.values()) {
			if(it.toString().equals(type)) {
				return it;
			}
		}
		return null;
	}
}
