package beans;

import java.util.ArrayList;
import java.util.Date;

import enums.Gender;
import enums.Role;

public class Trainer extends User {

	private ArrayList<TrainingHistory> trainingHistory;

	public Trainer() {
		super();
		this.trainingHistory = new ArrayList<TrainingHistory>();
	}

	public Trainer(int id, String username, String password, String firstName, String lastName, Gender gender, Role role,
			Date birthday, CustomerType userType, ArrayList<TrainingHistory> trainingHistory) {
		super(id, username, password, firstName, lastName, gender, role, birthday, userType);
		this.trainingHistory = trainingHistory;
	}

	public ArrayList<TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(ArrayList<TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	
	
}
