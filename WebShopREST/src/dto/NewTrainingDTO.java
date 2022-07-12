package dto;

import beans.SportObject;
import beans.User;
import enums.TrainingType;

public class NewTrainingDTO {
	
	private User trainer;
	private String name;
	private TrainingType type;
	private SportObject object;
	private double duration;
	private String description;
	private String imgUrl;
	
	public NewTrainingDTO() {}
	
	public NewTrainingDTO(User trainer, String name, TrainingType type, SportObject object, double duration,
			String description, String imgUrl) {
		super();
		this.trainer = trainer;
		this.name = name;
		this.type = type;
		this.object = object;
		this.duration = duration;
		this.description = description;
		this.imgUrl = imgUrl;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
