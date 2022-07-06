package beans;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dto.TrainingScheduleDTO;
import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class TrainingHistory extends Entity {
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate checkInDate;
	private Training training;
	private User customer;
	private User trainer;
	
	public TrainingHistory() {
		super();
	}
	
	public TrainingHistory(int id, LocalDate checkInDate, Training training, User customer, User trainer) {
		super(id);
		this.checkInDate = checkInDate;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}

	public TrainingHistory(TrainingScheduleDTO dto) {
		this.checkInDate = dto.getCheckInDate();
		this.training = dto.getTraining();
		this.customer = dto.getCustomer();
		this.trainer = dto.getTraining().getTrainer();
	}
	
	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
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
