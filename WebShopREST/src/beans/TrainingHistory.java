package beans;

import java.util.Date;

public class TrainingHistory extends Entity {
	
	private Date checkInDate;
	private Training training;
	private User customer;
	private User trainer;
	
	public TrainingHistory() {
		super();
	}
	
	public TrainingHistory(int id, Date checkInDate, Training training, User customer, User trainer) {
		super(id);
		this.checkInDate = checkInDate;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public User getBuyer() {
		return customer;
	}

	public void setBuyer(User customer) {
		this.customer = customer;
	}

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}
}
