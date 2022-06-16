package dao;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Artikal;

public class ArtikalDAO {

	private HashMap<String, Artikal> artikli = new HashMap<String, Artikal>();
	private File fajl;
	
	public ArtikalDAO() {}
	
	public ArtikalDAO(String contextPath) {
		fajl = new File(contextPath + "/artikli.json");
		loadArtikli(contextPath);
	}
	
	private void loadArtikli(String contextPath) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<Artikal> artikliList = Arrays.asList(mapper.readValue(new File(contextPath + "/artikli.json"), Artikal[].class));
			convertListToMap(artikliList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void convertListToMap(List<Artikal> artikliList) {
		for(Artikal a : artikliList) {
			artikli.put(a.getId(), a);
		}
	}
	
	public Collection<Artikal> getAll(){
		return artikli.values();
	}
	
	public Artikal getById(String id){
		return artikli.get(id);
	}
	
	public Artikal delete(String id) {
		return artikli.remove(id);
	}
	
	public Artikal insert(Artikal artikal) {
		if (!unique(artikal)) {
			return null;
		}
		artikal.checkAvailable();
		Integer maxId = getMaxId();
		artikal.setId(maxId.toString());
		artikli.put(artikal.getId(), artikal);
		return artikal;
	}
	
	private boolean unique(Artikal artikal) {
		for(Artikal a: artikli.values()) {
			if(a.getId().toLowerCase().equals(artikal.getId().toLowerCase())) {
				return false;
			}
		}
		
		return true;
	}
	
	private int getMaxId() {
		Integer maxId = -1;
		for (String id : artikli.keySet()) {
			int idNum =Integer.parseInt(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		
		return ++maxId;
	}
	
	public double getTotalPrice() {
		double total = 0;
		for (Artikal a: artikli.values()) {
			total += a.getTotalPrice();
		}
		
		return total;
	}
}
