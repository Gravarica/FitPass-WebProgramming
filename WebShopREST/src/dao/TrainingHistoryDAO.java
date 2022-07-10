package dao;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Training;
import beans.TrainingHistory;
import beans.User;
import dto.TrainingScheduleDTO;
import enums.TrainingType;

public class TrainingHistoryDAO {

	private ArrayList<TrainingHistory> trainingHistories = new ArrayList<TrainingHistory>();
	private File file;
	
	public TrainingHistoryDAO() {}

	public TrainingHistoryDAO(String contextPath) {
		file = new File(contextPath + "/Resources/Data/training_histories.json");
		loadTrainingHistories(contextPath);
	}

	//LOAD
	private void loadTrainingHistories(String contextPath) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<TrainingHistory> list = Arrays.asList(mapper.readValue(file,TrainingHistory[].class));
			trainingHistories = new ArrayList<TrainingHistory>(list);		
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}	

	//SAVE
	private void saveTrainingHistory() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ArrayList<TrainingHistory> objectList = new ArrayList<TrainingHistory>(trainingHistories);
			mapper.writeValue(file,objectList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getMaxId() {
		Integer maxId = -1;
		for (TrainingHistory it : trainingHistories) {
			if (it.getId() > maxId) {
				maxId = it.getId();
			}
		}
		
		return ++maxId;
	}
	
	public ArrayList<TrainingHistory> getAll(){
		return trainingHistories;
	}
	
	//Svi treninzi sportskog objekta
	public ArrayList<TrainingHistory> getSportObjectTrainingHistory(int id){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : trainingHistories) {
			if(it.getTraining().getObject().getId() == id) {
				retList.add(it);
			}
		}
		return retList;
	}

	//Otkazivanje personalnog treninga
	public TrainingHistory cancelPersonalTraining(int id){
		for(TrainingHistory  it : trainingHistories) {
			if(it.getId() == id && it.getTraining().getType() == TrainingType.PERSONAL) {
				executeCancel(it);
				return it;
			}
		}
		return null;
	}
	
	public void executeCancel(TrainingHistory t) {
		if(t.getCheckInDate().isBefore(t.getCheckInDate().plusDays(2)) || t.getCheckInDate().isEqual(t.getCheckInDate().plusDays(2))) {
			t.setDeleted(true);
			saveTrainingHistory();
		}
	}

	//Prijava na trening
	public TrainingHistory scheduleTraining(TrainingScheduleDTO dto) {
		TrainingHistory newTraining =  new TrainingHistory(dto);
		if(dto.getTraining().getType()==TrainingType.PERSONAL) {
			newTraining.setCanCancel(checkForDate(dto.getCheckInDate()));
			}
		
		newTraining.setId(getMaxId());
		trainingHistories.add(newTraining);
		saveTrainingHistory();
		return newTraining;
	}

	//Pretrazivanje treninga u odnosu na sportski objekat
	public ArrayList<TrainingHistory> searchBySportObject(int id){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : trainingHistories) {
			if(it.getTraining().getObject().getId() == id) {
				retList.add(it);
			}
		}
		return retList;
	}

	//Svi treninzi kupca
	public ArrayList<TrainingHistory> getCustomerTrainingHistory(String username){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : trainingHistories) {
			if(it.getCustomer().getUsername().equals(username)) {
				retList.add(it);
			}
		}
		return FilterTrainingHistory(retList);
	}


	//Metoda koja vraca treninge trenera 
	public ArrayList<TrainingHistory> getTrainerTrainingHistory(String username){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : trainingHistories) {
			if(it.getTrainer().getUsername().equals(username)) {
				retList.add(it);
			}
		}
		return retList;
	}

	//Vraca sve personalne treninge
	public ArrayList<TrainingHistory> getTrainerPersonalTrainings(String username){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : getTrainerTrainingHistory(username)) {
			if(it.getTraining().getType() == TrainingType.PERSONAL) {
				retList.add(it);
			}
		}
		return retList;
	}

	//Vraca sve grupne treninge trenera
	public ArrayList<TrainingHistory> getTrainerGroupTrainings(String username){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : getTrainerTrainingHistory(username)) {
			if(it.getTraining().getType() == TrainingType.GROUP) {
				retList.add(it);
			}
		}
		return retList;
	}
	
	//Metoda koja vraca treninge u prethodnih mesec dana
	public ArrayList<TrainingHistory> FilterTrainingHistory(ArrayList<TrainingHistory> trainings){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : trainings) {
			if(it.getCheckInDate().isAfter(LocalDate.now().minusMonths(1))) {
				retList.add(it);
			}
		}
		return retList;
	}

	public boolean checkForDate(LocalDate date) {
		return LocalDate.now().plusDays(2).isBefore(date) || LocalDate.now().plusDays(2).isEqual(date);
	}
	
}
