package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Training;
import beans.TrainingHistory;
import dto.TrainingScheduleDTO;
import enums.TrainingType;

public class TrainingHistoryDAO {

	private ArrayList<TrainingHistory> trainingHistories = new ArrayList<TrainingHistory>();
	private File file;
	
	public TrainingHistoryDAO() {}

	public TrainingHistoryDAO(String contextPath) {
		file = new File(contextPath + "/Resources/Data/training_history.json");
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
		newTraining.setId(getMaxId());
		saveTrainingHistory();
		return newTraining;
	}
}
