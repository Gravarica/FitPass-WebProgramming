package dto;

import enums.SportObjectType;

public class SportObjectSearchDTO {

	private String city;
	private SportObjectType type;
	private double grade;
	
	public SportObjectSearchDTO(String city, SportObjectType type, double grade) {
		super();
		this.city = city;
		this.type = type;
		this.grade = grade;
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

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
	
}
