package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Training;
import beans.TrainingHistory;

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

	
	
}
