package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Subscription;
import beans.User;
import dto.NewSubscriptionDTO;

public class SubscriptionDAO {

	private ArrayList<Subscription> subscriptions = new ArrayList<>();
	private File file;
	private ServletContext ctx;
	
	public SubscriptionDAO() {}
	
	public SubscriptionDAO(ServletContext ctx) {
		file = new File(ctx.getRealPath("") + "/Resources/Data/subscriptions.json");
		this.ctx = ctx;
		loadSubscriptions();
	}
	
	private UserDAO getUserDAO() {
		return (UserDAO) ctx.getAttribute("userDAO");
	}
	
	private void loadSubscriptions() {

		ObjectMapper mapper = new ObjectMapper();
	
		try {
			subscriptions = mapper.readValue(file, new TypeReference<List<Subscription>>() {});
			subscriptions = filterUndeleted(subscriptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Subscription> filterUndeleted(ArrayList<Subscription> subscriptions) {
		ArrayList<Subscription> filteredList = new ArrayList<>();
		for(Subscription s : subscriptions) {
			if(!s.isDeleted()) {
				filteredList.add(s);
			}
		}
		
		return filteredList;
	}
	
	public ArrayList<Subscription> getAll(){
		return subscriptions;
	}
	
	public Subscription getById(int id) {
		for(Subscription s : subscriptions) {
			if(s.getId() == id) {
				return s;
			}
		}
		
		return null;
	}
	
	public Subscription getByCode(String code) {
		for(Subscription s: subscriptions) {
			if(s.getCode().equals(code)) {
				return s;
			}
		}
		
		return null;
	}
	
	public Subscription createSubscription(NewSubscriptionDTO dto) {
		dto.setUsername(getUserDAO().getLoggedUser().getUsername());
		
		for (Subscription s : subscriptions) {
			if(s.exists(dto.getUsername())) {
				s.setActive(false);
				calculatePoints(s);
				getUserDAO().checkForUpgrade();
			}
		}
		
		
		Subscription newInstance = new Subscription(dto);
		newInstance.setId(getMaxId());
		subscriptions.add(newInstance);
		save();
		return newInstance;
	}
	
	public User addPointsToCustomer(Subscription subscription, User customer) {
		if(subscription.hasExpired()) {
			calculatePoints(subscription);
		}
		
		return customer;
	}
	
	private void calculatePoints(Subscription subscription) {
		int totalAppearances = subscription.getTotalAppearances();
		double price = subscription.getPrice();
		User customer = getUserDAO().getByUsername(subscription.getUsername());
		double points = customer.getTotalPoints();
		
		if(subscription.getDoneTrainings() >= totalAppearances/3) {
			points += price/1000*subscription.getDoneTrainings();
		} else {
			points -= price/1000*133*4;
		}
		
		points = points < 0 ? 0 : points;
		customer.setTotalPoints(points);
	}
	
	public Subscription getByUser(String username) {
		for(Subscription s : subscriptions) {
			if(s.exists(username)) {
				return s;
			}
		}
		
		return null;
	}
	
	
	private int getMaxId() {
		Integer maxId = -1;
		for (Subscription s : subscriptions) {
			if (s.getId() > maxId) {
				maxId = s.getId();
			}
		}
		
		return ++maxId;
	}
	
	private void save() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			mapper.writeValue(file, subscriptions);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Subscription increaseTrainingCounter(int id) {
		Subscription s = getById(id);
		s.trainingDone();
		return s;
	}
	
	public Subscription getByLoggedUser() {
		User user = getUserDAO().getLoggedUser();
		for (Subscription s : subscriptions) {
			if(s.exists(user.getUsername())) {
				return s;
			}
		}
		return null;
	}
	
	public void checkForExpired() {
		Subscription s = getByLoggedUser();
		if (s == null || !s.hasExpired()) return;
			
		s.setActive(false);
		calculatePoints(s);
		save();
		getUserDAO().checkForUpgrade();
	}
}
