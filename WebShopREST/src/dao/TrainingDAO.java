package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.SportObject;
import beans.Training;
import beans.TrainingHistory;
import beans.User;
import dto.TrainingScheduleDTO;
import enums.TrainingType;
import src.util.BusinessUtil;

public class TrainingDAO {
	
	private ArrayList<Training> trainings = new ArrayList<Training>();
	private File file;
	
	
	public TrainingDAO() {}
	
	public TrainingDAO(String contextPath) {
		file = new File(contextPath + "/Resources/Data/trainings.json");
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

	public ArrayList<Training> getAll(){
		return trainings;
	}
	
	public void setTrainingTrainer(Training t,User trener) {
		t.setTrainer(trener);
		saveTraining();
	}
	
	public ArrayList<Training> getSportObjectTrainings(int id){
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training it : trainings) {
			if(it.getObject().getId() == id)
				retList.add(it);
		}
		return retList;
	}


	public ArrayList<Training> getTrainingsWithoutTrainer(int id){
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training it : trainings) {
			if(it.getObject().getId() == id && it.getTrainer() == null) {
				retList.add(it);
			}
		}
		return retList;
	}


	public ArrayList<Training> getSportObjectTrainingsWithTrainer(int id){
		ArrayList<Training> retList = new ArrayList<Training>();
		for(Training it : trainings) {
			if(it.getObject().getId() == id && it.getTrainer() != null) {
				retList.add(it);
			}
		}
		return retList;
	}
	
	public ArrayList<User> getTrainersBySportObject(int objectId){
		ArrayList<User> retList = new ArrayList<User>();
		for(Training t : trainings) {
			if (t.getObject().getId() == objectId && t.getTrainer() != null) {
				if(!BusinessUtil.listContains(retList, t.getTrainer())) {
					retList.add(t.getTrainer());
				}
			}
			
		}
		return retList;
	}

}
