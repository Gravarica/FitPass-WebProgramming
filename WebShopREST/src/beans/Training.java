package beans;

import java.util.Base64;
import java.util.Date;

import dto.NewTrainingDTO;
import enums.TrainingType;

public class Training extends Entity {
	
	private String name;
	private TrainingType type;
	private SportObject object;
	private double duration;
	private String description;
	private Base64 image;
	private User trainer;
	
	public Training() {
		super();
	}
		
	public Training(int id, String name, TrainingType type, SportObject object, double duration, String description,
			Base64 image, User trainer) {
		super(id);
		this.name = name;
		this.type = type;
		this.object = object;
		this.duration = duration;
		this.description = description;
		this.image = image;
		this.trainer = trainer;
	}

	//Konstruktor za kreiranje novog treninga
	public Training(NewTrainingDTO dto) {
		this.name = dto.getName();
		this.type = dto.getType();
		this.object = dto.getObject();
		this.duration = dto.getDuration();
		this.description = dto.getDescription();
		this.trainer = dto.getTrainer();
		
		//Slika boga pitaj
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrainingType getType() {
		return type;
	}

	public void setType(TrainingType type) {
		this.type = type;
	}

	public SportObject getObject() {
		return object;
	}

	public void setObject(SportObject object) {
		this.object = object;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Base64 getImage() {
		return image;
	}

	public void setImage(Base64 image) {
		this.image = image;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}
}

