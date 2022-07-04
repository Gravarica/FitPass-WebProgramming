package dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Subscription;
import beans.User;
import dto.NewSubscriptionDTO;

public class SubscriptionDAO {

	private ArrayList<Subscription> subscriptions = new ArrayList<>();
	private File file;
	
	public SubscriptionDAO() {}
	
	public SubscriptionDAO(String contextPath) {
		file = new File(contextPath + "/Resources/Data/subscriptions.json");
		loadSubscriptions(contextPath);
	}
	
	private void loadSubscriptions(String contextPath) {

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
	
	public Subscription createSubscription(NewSubscriptionDTO dto) {
		
	} 
	
	
}
