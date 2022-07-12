package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Content;
import beans.SportObject;
import comparators.object.GradeComparator;
import comparators.object.LocationComparator;
import comparators.object.ObjectNameComparator;
import dto.SportObjectCreateDTO;
import dto.AverageGradeDTO;
import dto.NewContentDTO;
import dto.SportObjectSearchDTO;
import enums.SportObjectContent;
import enums.SportObjectType;
import src.util.BusinessUtil;

public class SportObjectDAO {

	private ArrayList<SportObject> objects = new ArrayList<SportObject>();
	private File file;
	private ServletContext ctx;
	
	public SportObjectDAO() {}
	
	public SportObjectDAO(String contextPath, ServletContext ctx) {
		file = new File(contextPath + "/sport_objects.json");
		this.ctx = ctx;
		System.out.println(contextPath);
		loadSportObjects(contextPath);
		
	}
		
	private void loadSportObjects(String contextPath) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<SportObject> list = Arrays.asList(mapper.readValue(file,SportObject[].class));
			objects = filterUndeleted(new ArrayList<SportObject>(list));
			setStatus();
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}	
	
	private void setStatus() {
		for (SportObject obj : objects) {
			if(BusinessUtil.timeIsInZone(obj.getWorkHour())) {
				obj.setStatus(true);
			} else {
				obj.setStatus(false);
			}
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
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
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
		ArrayList<SportObject> retList = new ArrayList<SportObject>();
		for(SportObject it : objects) {
			if(it.getSportObjectType() != SportObjectType.NONE) {
				retList.add(it);
			}
		}
		return retList;
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
		System.out.println("PRIMIO SAM OVO: " + parameter);
		for(SportObject searchObj : objects) {
			if (searchObj.containsParameter(parameter)) {
				System.out.println("VRACAM: " + searchObj.getName());
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

		if(type == SportObjectType.NONE) {
			return objects;
		}
		
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
		System.out.println(dto.getCity());
		ArrayList<SportObject> returnList = searchByCity(dto.getCity());
		returnList.retainAll(searchByType(dto.getType()));
		returnList.retainAll(search(dto.getParameter()));
		
		return returnList;
	}
	
	public SportObject delete(int id) {
		SportObject returnObject = getById(id);
		returnObject.setDeleted(true);
		objects.remove(returnObject);
		saveSportObjects();
		return returnObject;
	}
  
	public SportObject createSportObject(SportObjectCreateDTO dto) {
		SportObject newObject = new SportObject(dto);
		newObject.setId(getMaxId());
		objects.add(newObject);
		getUserDAO().setSportObject(newObject.getId(), dto.getManagerUsername());
		saveSportObjects();
		return newObject;
	}
    
	//ADD CONTENT
	public SportObject addContent(NewContentDTO dto, int id) {
		SportObject object = getById(id);
		if(BusinessUtil.checkIfContentExists(object.getContents(), dto)) {
			return null;
		}
		
		Content content = new Content(dto);
		object.addContent(content);
		saveSportObjects();
		return object;
	}
	
	
	//GET CONTENT BY NAME
	private Content getContentByName(String name, SportObject object) {
		for(Content content : object.getContents()) {
			if(name.equals(content.getName())) {
				return content;
			}
		}
		
		return null;
	}
	
	//EDIT CONTENT
	public SportObject changeContent(NewContentDTO dto, int id) {
		SportObject object = getById(id);
		Content changeContent = getContentByName(dto.getName(), object);
		if(changeContent == null) {
			return null;
		}
		
		changeContent.modify(dto);
		saveSportObjects();
		return object;
	}
	
	public void calculateAverageGrade(SportObject obj, AverageGradeDTO dto) {
		obj.setAverageGrade(dto.getAverage());
		saveSportObjects();
	}
	
	public ArrayList<String> getSportObjectTypes(){
		ArrayList<String> retList = new ArrayList<String>();
		SportObjectType types[] = SportObjectType.values();
		for(SportObjectType it : types) {
			retList.add(it.toString());
		}
		return retList;
	}
	
	public ArrayList<SportObject> sortByName(boolean asc){
		ArrayList<SportObject> retList = new ArrayList<SportObject>(objects);
		if(asc) {
			Collections.sort(retList, new ObjectNameComparator()); 
		} else {
			Collections.sort(retList, new ObjectNameComparator().reversed());
		}
		
		return retList;
	}
	
	public ArrayList<SportObject> sortByGrade(boolean asc){
		ArrayList<SportObject> retList = new ArrayList<SportObject>(objects);
		if(asc) {
			Collections.sort(retList, new GradeComparator()); 
		} else {
			Collections.sort(retList, new GradeComparator().reversed());
		}
		
		return retList;
	}
	
	public ArrayList<SportObject> sortByLocation(boolean asc){
		ArrayList<SportObject> retList = new ArrayList<SportObject>(objects);
		if(asc) {
			Collections.sort(retList, new LocationComparator()); 
		} else {
			Collections.sort(retList, new LocationComparator().reversed());
		}
		
		return retList;
	}

	public ArrayList<SportObjectContent> getSportObjectContentTypes(int id){
		for(SportObject it : objects) {
			if(it.getId() == id) {
				return getContents(it.getContents());
			}
		}
		return null;
	}
	
	public ArrayList<SportObjectContent> getContents(ArrayList<Content> contents){
		ArrayList<SportObjectContent> retList = new ArrayList<SportObjectContent>();
		for(Content it : contents) {
			retList.add(it.getContentType());
		}
		return retList;
	}
	
	public boolean checkForContentName(int id,String name) {
		SportObject object = getById(id);
		
		for(Content it : object.getContents()) {
			if(it.getName().equals(name)) {
				return true; //ne moze ime ako vrati true zapamit to baki moj
			}
		}
		return false;
	}
	
}
