package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Content;
import beans.SportObject;
import dto.NewContentDTO;
import dto.SportObjectSearchDTO;
import enums.SportObjectType;
import src.util.BusinessUtil;

public class SportObjectDAO {

	private ArrayList<SportObject> objects = new ArrayList<SportObject>();
	private File file;
	
	public SportObjectDAO() {}
	
	public SportObjectDAO(String contextPath) {
		//file = new File(contextPath + "/sport_objects.json");
		loadSportObjects(contextPath);
	}
		
	private void loadSportObjects(String contextPath) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<SportObject> list = Arrays.asList(mapper.readValue(new File(contextPath + "/sport_objects.json"),SportObject[].class));
			objects = filterUndeleted(new ArrayList<SportObject>(list));		
		}catch(Exception e) {
			e.printStackTrace();
		}	
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
	
	public SportObject addContent(NewContentDTO dto, int id) {
		SportObject object = getById(id);
		if(BusinessUtil.checkIfContentExists(object.getContents(), dto)) {
			return null;
		}
		
		Content content = new Content(dto);
		object.addContent(content);
		return object;
	}
	
	private Content getContentByName(String name, SportObject object) {
		for(Content content : object.getContents()) {
			if(name.equals(content.getName())) {
				return content;
			}
		}
		
		return null;
	}
	
	public SportObject changeContent(NewContentDTO dto, int id) {
		SportObject object = getById(id);
		Content changeContent = getContentByName(dto.getName(), object);
		if(changeContent == null) {
			return null;
		}
		
		changeContent.modify(dto);
		return object;
	}
}
