package beans;

import java.util.Date;

public class TrainingHistory extends Entity {
	
	private Date checkInDate;
	private Training training;
	private Customer customer;
	private Trainer trainer;
	
	public TrainingHistory() {
		super();
	}
	
	public TrainingHistory(int id, Date checkInDate, Training training, Customer customer, Trainer trainer) {
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

	public Customer getBuyer() {
		return customer;
	}

	public void setBuyer(Customer customer) {
		this.customer = customer;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
}
