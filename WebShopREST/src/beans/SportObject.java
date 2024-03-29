package beans;

import java.util.ArrayList;
import java.util.Base64;

import dto.SportObjectCreateDTO;
import enums.SportObjectType;
import src.util.BusinessUtil;
import enums.SportObjectContent;


public class SportObject extends Entity {

	private String name;
	private SportObjectType sportObjectType;
	private ArrayList<Content> contents;
	private boolean status;
	private Location location;
	private double averageGrade;
	private WorkHour workHour;
	private String description;
	private Base64 image;
	
	public SportObject() {
		super();
		this.contents = new ArrayList<Content>();
	}
	
	public SportObject(int id, String name, SportObjectType sportObjectType, ArrayList<Content> contents,
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
	
	//Konstruktor za kreiranje novog sportskog objekta
	public SportObject(SportObjectCreateDTO dto) {
		this.name = dto.getName();
		this.location = dto.getLocation();
		this.image = dto.getLogo();
		this.sportObjectType = dto.convertToType(dto.getType());
		this.workHour = new WorkHour(dto.getStartTime(),dto.getEndTime());
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

	public ArrayList<Content> getContents() {
		return contents;
	}

	public void setContents(ArrayList<Content> contents) {
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
	
	public boolean containsParameter(String parameter) {
		return BusinessUtil.stringContains(name, parameter);
	}
	
	public boolean typeMatches(SportObjectType type) {
		return this.sportObjectType == type;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean gradeApplies(double grade) {
		return this.averageGrade >= grade;
	}
	
	public void addContent(Content content) {
		contents.add(content);
	}
}
