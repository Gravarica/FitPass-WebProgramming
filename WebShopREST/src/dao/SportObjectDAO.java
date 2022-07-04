package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.SportObject;
import beans.User;
import dto.SportObjectCreateDTO;
import dto.SportObjectSearchDTO;
import enums.SportObjectType;

public class SportObjectDAO {

	private ArrayList<SportObject> objects = new ArrayList<SportObject>();
	private File file;
	
	public SportObjectDAO() {}
	
	public SportObjectDAO(String contextPath) {
		file = new File(contextPath + "/sport_objects.json");
		loadSportObjects(contextPath);
	}
		
	private void loadSportObjects(String contextPath) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<SportObject> list = Arrays.asList(mapper.readValue(file,SportObject[].class));
			objects = filterUndeleted(new ArrayList<SportObject>(list));		
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}	
	
	private void saveSportObjects() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ArrayList<SportObject> objectList = new ArrayList<SportObject>(objects);
			mapper.writeValue(file,objectList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Metoda koja vraca ID
	private int getMaxId() {
		Integer maxId = -1;
		for (SportObject it : objects) {
			if (it.getId() > maxId) {
				maxId = it.getId();
			}
		}
		
		return ++maxId;
	}
	
	
	public ArrayList<SportObject> filterUndeleted(ArrayList<SportObject> objects) {
		ArrayList<SportObject> objectsNew = new ArrayList<SportObject>();
		for(SportObject object: objects) {
			if(!object.isDeleted()) {
				objectsNew.add(object);
			}
		}
		
		return objectsNew;
	}

	public ArrayList<SportObject> getAll(){
		return objects;
	}

	public SportObject getById(int id){
		for(SportObject object : objects) {
			if(object.getId() == id) 
				return object;
		}
		return null;
	}
	
	public ArrayList<SportObject> search(String parameter){
		ArrayList<SportObject> returnList = new ArrayList<>();
		for(SportObject searchObj : objects) {
			if (searchObj.containsParameter(parameter)) {
				returnList.add(searchObj);
			}
		}
		return returnList;
	}
	
	public ArrayList<SportObject> searchByCity(String city){
		ArrayList<SportObject> returnList = new ArrayList<>();		
		for(SportObject searchObj : objects) {
			if(searchObj.getLocation().getAddress().cityMatches(city)) {
				returnList.add(searchObj);
			}
		}
		return returnList;
	}
	
	public ArrayList<SportObject> searchByType(SportObjectType type){

		ArrayList<SportObject> returnList = new ArrayList<>();
		for(SportObject searchObj : objects) {
			if(searchObj.typeMatches(type)) {
				returnList.add(searchObj);
			}
		}
		return returnList;
	}
	
	public ArrayList<SportObject> searchByAverageGrade(double avgGrade){
		ArrayList<SportObject> returnList = new ArrayList<>();
		for(SportObject searchObj : objects) {
			if(searchObj.gradeApplies(avgGrade)) {
				returnList.add(searchObj);
			}
		}
		return returnList;
	}
	
	public ArrayList<SportObject> searchByMultipleParameters(SportObjectSearchDTO dto){
		ArrayList<SportObject> returnList = searchByCity(dto.getCity());
		returnList.retainAll(searchByType(dto.getType()));
		returnList.retainAll(searchByAverageGrade(dto.getGrade()));
		
		return returnList;
	}
	
	public SportObject delete(int id) {
		SportObject returnObject = getById(id);
		returnObject.setDeleted(true);
		objects.remove(returnObject);
		return returnObject;
	}
	
	public SportObject createSportObject(SportObjectCreateDTO dto) {
		SportObject newObject = new SportObject(dto);
		newObject.setId(getMaxId());
		objects.add(newObject);
		saveSportObjects();
		return newObject;
	}
	
}
