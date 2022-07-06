package dto;

import java.time.LocalDate;

import beans.SportObject;
import beans.Training;
import beans.User;

public class TrainingScheduleDTO {

	private SportObject sportObject; //ovo fakticki nece ni trebati al ae
	private User customer;
	private Training training;
	private LocalDate checkInDate;
	
	public TrainingScheduleDTO() {}

	public TrainingScheduleDTO(SportObject sportObject, Training training, LocalDate checkInDate,User customer) {
		super();
		this.sportObject = sportObject;
		this.customer = customer;
		this.training = training;
		this.checkInDate = checkInDate;
	}

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
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
