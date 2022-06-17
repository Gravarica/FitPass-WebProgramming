package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.SportObject;

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
			objects = new ArrayList<SportObject>(list);
		}catch(Exception e) {
			e.printStackTrace();
		}	
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
}
