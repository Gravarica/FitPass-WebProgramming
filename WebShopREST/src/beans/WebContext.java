package beans;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.GenericDAOImpl;
import dao.ProductDAO;
import dao.UserDAO;

public class WebContext {

	private static WebContext instance;
	private ArrayList<Entity> users = new ArrayList<Entity>();
	private ArrayList<Entity> buyers = new ArrayList<Entity>();
	private ArrayList<Entity> coaches = new ArrayList<Entity>();
	private ArrayList<Entity> managers = new ArrayList<Entity>();
	private ArrayList<Entity> locations = new ArrayList<Entity>();
	private ArrayList<Entity> comments = new ArrayList<Entity>();
	private ArrayList<Entity> sportObjects = new ArrayList<Entity>();
	private ArrayList<Entity> trainings = new ArrayList<Entity>();
	private ArrayList<Entity> subscriptions = new ArrayList<Entity>();
	private ArrayList<Entity> trainingHistories = new ArrayList<Entity>();
	 
	private WebContext() {}
	
	public static WebContext getInstance() {
		if (instance == null) {
			instance = new WebContext();
		}
		
		return instance;
	}
	
	public void Load() {
		//GenericLoad("/users",users);
	}
	
	/*
	private void GenericLoad(String path, ArrayList<Entity> entites, Entity cls) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<Entity> lista = Arrays.asList(mapper.readValue(new File(path), cls));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public ArrayList<Entity> get(GenericDAOImpl dao) 
	{
		
		if(dao instanceof UserDAO) {
			return users;
		} 
		
		/*if(cls == Buyer.class) {
			return buyers;
		}
		
		if(cls == Coach.class) {
			return coaches;
		}
		
		if(cls == Comment.class) {
			return comments;
		}
		
		if(cls == Manager.class) {
			return managers;
		}
		
		if(cls == Training.class) {
			return trainings;
		}
		
		if(cls == SportObject.class) {
			return sportObjects;
		}
		
		if(cls == Location.class) {
			return locations;
		}
		
		if(cls == Subscription.class) {
			return subscriptions;
		}
		
		if(cls == TrainingHistory.class) {
			return trainingHistories;
		}*/
		
		return null;
	}
	
}
