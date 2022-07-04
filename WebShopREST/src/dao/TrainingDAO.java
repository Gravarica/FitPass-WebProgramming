package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.SportObject;
import beans.Training;
import beans.TrainingHistory;

public class TrainingDAO {
	
	private ArrayList<Training> trainings = new ArrayList<Training>();
	private File file;
	
	
	public TrainingDAO() {}
	
	public TrainingDAO(String contextPath) {
		file = new File(contextPath + "/Resources/Data/training.json");
		loadTrainings(contextPath);
	}
	
	//LOAD
	private void loadTrainings(String contextPath) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<Training> list = Arrays.asList(mapper.readValue(file,Training[].class));
			trainings = new ArrayList<Training>(list);		
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}	
	
	//SAVE
	private void saveTraining() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ArrayList<Training> objectList = new ArrayList<Training>(trainings);
			mapper.writeValue(file,objectList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//MAX ID
	private int getMaxId() {
		Integer maxId = -1;
		for (Training it : trainings) {
			if (it.getId() > maxId) {
				maxId = it.getId();
			}
		}
		
		return ++maxId;
	}	

	public ArrayList<Training> getSportObjectTrainings(int id){
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training it : trainings) {
			if(it.getObject().getId() == id)
				retList.add(it);
		}
		return retList;
	}
}
