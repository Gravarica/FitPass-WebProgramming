package beans;

import java.util.ArrayList;
import java.util.Base64;

import enums.SportObjectType;
import enums.SportObjectContent;


public class SportObject extends Entity {

	private String name;
	private SportObjectType sportObjectType;
	private ArrayList<SportObjectContent> contents;
	private boolean status;
	private Location location;
	private double averageGrade;
	private WorkHour workHour;
	private Base64 image;
	
	public SportObject() {
		super();
		this.contents = new ArrayList<SportObjectContent>();
	}
	
	public SportObject(int id, String name, SportObjectType sportObjectType, ArrayList<SportObjectContent> contents,
			boolean status, Location location, double averageGrade, WorkHour workHour, Base64 image) {
		super(id);
		this.name = name;
		this.sportObjectType = sportObjectType;
		this.contents = contents;
		this.status = status;
		this.location = location;
		this.averageGrade = averageGrade;
		this.workHour = workHour;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SportObjectType getSportObjectType() {
		return sportObjectType;
	}

	public void setSportObjectType(SportObjectType sportObjectType) {
		this.sportObjectType = sportObjectType;
	}

	public ArrayList<SportObjectContent> getContents() {
		return contents;
	}

	public void setContents(ArrayList<SportObjectContent> contents) {
		this.contents = contents;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public WorkHour getWorkHour() {
		return workHour;
	}

	public void setWorkHour(WorkHour workHour) {
		this.workHour = workHour;
	}

	public Base64 getImage() {
		return image;
	}

	public void setImage(Base64 image) {
		this.image = image;
	}
	
	
	
	
}