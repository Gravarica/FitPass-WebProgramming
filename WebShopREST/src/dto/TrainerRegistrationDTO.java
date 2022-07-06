package dto;

import java.util.Date;

import beans.Training;
import beans.User;
import enums.Gender;
import enums.Role;

public class TrainerRegistrationDTO extends RegistrationDTO{

	private Training training;
	
	public TrainerRegistrationDTO() {}
	
	public TrainerRegistrationDTO(String username, String password, String firstName, String lastName, Gender gender,
			Role role, Date dateOfBirth) {
		super(username, password, firstName, lastName, gender, role, dateOfBirth);
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public void setTrainingTrainer(User trainer) {
		this.training.setTrainer(trainer);
	}
}