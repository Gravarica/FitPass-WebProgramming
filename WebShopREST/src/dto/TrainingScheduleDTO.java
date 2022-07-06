package dto;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import beans.SportObject;
import beans.Training;
import beans.User;
import src.util.LocalDateDeserializer;
import src.util.LocalDateSerializer;

public class TrainingScheduleDTO {

	private User customer;
	private Training training;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate checkInDate;
	
	public TrainingScheduleDTO() {}

	public TrainingScheduleDTO(Training training, LocalDate checkInDate,User customer) {
		super();
		this.customer = customer;
		this.training = training;
		this.checkInDate = checkInDate;
	}
	
	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	public boolean checkForDate() {
		return this.checkInDate.isBefore(LocalDate.now().plusDays(1));
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}
}
