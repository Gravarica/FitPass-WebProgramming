package dto;

import enums.SportObjectType;

public class SportObjectSearchDTO {

	private String city;
	private SportObjectType type;
	private String parameter;
	
	public SportObjectSearchDTO(String city, SportObjectType type, String parameter) {
		super();
		this.city = city.replace("%20", " ");
		this.type = type;
		this.parameter = parameter;
	}

	public SportObjectSearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public SportObjectType getType() {
		return type;
	}

	public void setType(SportObjectType type) {
		this.type = type;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	
	
}
