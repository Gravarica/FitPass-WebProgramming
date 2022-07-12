package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.org.apache.bcel.internal.classfile.Utility;

import beans.Artikal;
import beans.CustomerType;
import beans.Entity;
import beans.SportObject;
import beans.Training;
import beans.TrainingHistory;
import beans.User;
import comparators.user.FirstNameComparator;
import comparators.user.LastNameComparator;
import comparators.user.NameComparator;
import comparators.user.PointsComparator;
import dto.CustomerTypeDTO;
import dto.LoginDTO;
import dto.LoginReturnDTO;
import dto.ManagerRegistrationDTO;
import dto.RegistrationDTO;
import dto.TrainerRegistrationDTO;
import dto.UpdateProfileDTO;
import dto.UserAccountInformationDTO;
import enums.CustomerTypeName;
import enums.Role;
import enums.TrainingType;
import src.util.Bridge;
import src.util.BusinessUtil;

/***
 * <p>Klasa namenjena da u�ita korisnike iz fajla i pru�a operacije nad njima (poput pretrage).
 * Korisnici se nalaze u fajlu WebContent/users.txt u obliku: <br>
 * firstName;lastName;email;username;password</p>
 * <p><b>NAPOMENA:</b> Lozinke se u praksi <b>nikada</b> ne snimaju u �istu tekstualnom obliku.</p>
 * @author Lazar
 *
 */
public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	private File file;
	private ServletContext ctx;
	private User loggedUser;
	
	public UserDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */
	public UserDAO(String contextPath, ServletContext ctx) {
		file = new File(contextPath + "/Resources/Data/users.json");
		loadUsers(contextPath);
		this.ctx = ctx;
		System.out.println("PATH: " + contextPath);
		//System.out.println(contextPath);
	}
	
	/**
	 * Vra�a korisnika za prosle�eno korisni�ko ime i �ifru. Vra�a null ako korisnik ne postoji
	 * @param username
	 * @param password
	 * @return
	 */
	public User find(String username, String password) {
		if (!users.containsKey(username)) {
			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}
	
	private SportObjectDAO getSportObjectDAO() {
		return (SportObjectDAO) ctx.getAttribute("sportObjectDAO");
	}
	
	public Collection<User> getAll() {
		printDateForEachUser();
		return users.values();
	}
	
	private void printDateForEachUser() {
		for (User u : users.values()) {
			System.out.println(u.getDateOfBirth().toString());
		}
	}
	
	/**
	 * U�itava korisnike iz WebContent/users.txt fajla i dodaje ih u mapu {@link #users}.
	 * Klju� je korisni�ko ime korisnika.
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadUsers(String contextPath) {
		
		ObjectMapper mapper = new ObjectMapper();
	
		try {
			List<User> userList = mapper.readValue(file, new TypeReference<List<User>>() {});
			userList = filterUndeleted(userList);
			convertListToMap(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private ArrayList<User> filterUndeleted(List<User> userList) {
		ArrayList<User> usersNew = new ArrayList<User>();
		for(User u : userList) {
			if(!u.isDeleted()) {
				usersNew.add(u);
			}
		}
		return usersNew;
	}
	
	public User register(RegistrationDTO dto) {
		
		if (users.containsKey(dto.getUsername())) {
			return null;
		}
		User saveUser = new User(dto);
		saveUser.setPassword(BusinessUtil.hashPassword(saveUser.getPassword()));
		saveUser.setId(getMaxId());
		users.put(saveUser.getUsername(), saveUser);
		saveUsers();
		
		return saveUser;
	}
	
	private void saveUsers() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ArrayList<User> userList = new ArrayList<User>(users.values());
			mapper.writeValue(file, userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private int getMaxId() {
		Integer maxId = -1;
		for (User user : users.values()) {
			if (user.getId() > maxId) {
				maxId = user.getId();
			}
		}
		
		return ++maxId;
	}
	
	private void convertListToMap(List<User> userList) {
		for(User u : userList) {
			users.put(u.getUsername(), u);
			System.out.println(u.getId());
		}
	}
	
	public LoginReturnDTO login(LoginDTO dto) {
		System.out.println("OVO JE PRIMLJENO U FUNKCIJI " + dto.getUsername() +" "+ dto.getPassword());
		System.out.println("OVO JE HARD KODOVANO" + users.get("gravarica"));
		if(users == null) {System.out.println("USERI SU NULA");}
		User user = users.get(dto.getUsername());
		//System.out.println("PRONADJENI USER" + user.getUsername() + user.getPassword());
		if (user == null || !user.passwordMatches(dto)) return new LoginReturnDTO(null, null, false);
		loggedUser = user;
		return new LoginReturnDTO(user.getUsername(), user.getRole(), true);
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
	public ArrayList<User> search(String parameter){
		ArrayList<User> returnList = new ArrayList<>();
		for(User searchUser : users.values()) {
			if(searchUser.containsParameter(parameter)) {
				returnList.add(searchUser);
			}
		}
		return returnList;
	}
	
	public ArrayList<User> getUsersByRole(Role role){
		if (role == Role.NONE) {
			return new ArrayList<User>(users.values());
		}
		
		ArrayList<User> returnList = new ArrayList<>();
		for(User searchUser : users.values()) {
			if(searchUser.roleMatches(role)) {
				returnList.add(searchUser);
			}
		}
		return returnList;
	}
	
	public User logout() {
		User returnUser = getLoggedUser();
		this.loggedUser = null;
		return returnUser;
	}
	
	public User delete(String username) {
		User returnUser = users.get(username);
		returnUser.setDeleted(true);
		users.remove(username);
		return returnUser;
	}
	
	//Metoda koja vraca sve MENAGERE
	public ArrayList<User> getAllManagers(){
		ArrayList<User> retList = new ArrayList<User>();
		for(User it : users.values()) {
			if(it.roleMatches(Role.MANAGER)) {
				retList.add(it);
			}
		}
		return retList;
	}
	
	//Metoda koja vraca sve SLOBODNE MENAGERE
	public ArrayList<User> getAllAvailableManagers(){
		ArrayList<User> retList = new ArrayList<User>();
		for(User it : getAllManagers()) {
			if((it).getObjectId() == -1) {
				retList.add(it);
			}
		}
		return retList;
	}
	
	public ArrayList<User> getAllEmployedManagers(){
		ArrayList<User> retList = new ArrayList<User>();
		for(User it : getAllManagers()) {
			if((it).getObject() != null) {
				retList.add(it);
			}
		}
		return retList;
	}
	
	//Register MANAGER
	public User registerManager(ManagerRegistrationDTO dto, int objectId) {
		if (users.containsKey(dto.getUsername())) {
			return null;
		}
		User manager = new User(dto, objectId);
		manager.setPassword(BusinessUtil.hashPassword(manager.getPassword()));
		manager.setId(getMaxId());
		users.put(manager.getUsername(), manager);
		saveUsers();
		
		return manager;
	}
	
	//Register Trainer
	public User registerTrainer(TrainerRegistrationDTO dto) {
		if(users.containsKey(dto.getUsername())) {
			return null;
		}
	
		User newTrainer = new User(dto);
		newTrainer.setPassword(BusinessUtil.hashPassword(newTrainer.getPassword()));
		newTrainer.setId(getMaxId());
		users.put(newTrainer.getUsername(), newTrainer);
		saveUsers();
				
		return newTrainer;
	}
	
	public User setSportObject(int objectId, String username) {
		User manager = users.get(username);
		if(!manager.roleMatches(Role.MANAGER) || manager.getObject() != null) {
			return null;
		}
		
		manager.setObjectId(objectId);
		saveUsers();
		return manager;
	}
	
	public SportObject getManagerSportObject() {
		for(User it : getAllManagers()){
			if(it.getUsername().toLowerCase().equals(loggedUser.getUsername().toLowerCase())) {
				if (it.getId() != -1) {
					return getSportObjectDAO().getById(it.getObjectId());
				}
				
			}
		}
		return null;
	}
	
	
  
	//Metoda koja vraca treninge kupca na mesecnom nivou
	public ArrayList<TrainingHistory> getCustomerMonthlyTrainingHistory(String username){
		for(User it : users.values()) {
			if(it.getUsername().equals(username)) {
				return FilterTrainingHistory(it.getTrainingHistory());
			}
		}
		return null;
	}
	
	//Metoda koja vrsi tu filtraciju na mesecnom nivou
	public ArrayList<TrainingHistory> FilterTrainingHistory(ArrayList<TrainingHistory> trainings){
		ArrayList<TrainingHistory> retList = new ArrayList<TrainingHistory>();
		for(TrainingHistory it : trainings) {
			//ubaciti logiku za datum !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		}
		return retList;
	}
	
	public UserAccountInformationDTO getUserAccountInfromation() {
		return new UserAccountInformationDTO(getLoggedUser());
	}
		
	public User getByUsername(String username) {
		return users.get(username);
	}
	
	public void increaseObjectVisited(String username,SportObject object) {
		 User customer = users.get(username);
		 if(!checkCustomersObjectVisited(object.getId(),customer)) {
			 customer.increaseObjectVisited(object);
			 saveUsers();
		 }
	}
	
	public boolean checkCustomersObjectVisited(int id,User customer) {
		if(customer.getObjectsVisited() == null) {
			return false;
		}		
	
		for(SportObject it : customer.getObjectsVisited()) {
			if(it.getId() == id) {
				return true;
			}
		}
	
		return false;
	}
	
	public void updateTrainingHistory(TrainingHistory t) {
		 updateTrainerTrainingHistory(t);
		 updateCustomerTrainingHistory(t);
		 saveUsers();
	}
	
	public void updateTrainerTrainingHistory(TrainingHistory t) {
		 User trainer = users.get(t.getTrainer().getUsername());
		 trainer.updateTrainingHistory(t);
	}
	  
	public void updateCustomerTrainingHistory(TrainingHistory t) {
		 User customer = users.get(t.getCustomer().getUsername());
		 customer.updateTrainingHistory(t);
	}
  
	public void unemployManager(int id) {
		for(User it : getAllEmployedManagers()) {
			if(it.getObject().getId() == id) {
				it.setObject(null);
			}
		}
  }
    
	public UserAccountInformationDTO editProfile(UpdateProfileDTO dto) {
		User user = getLoggedUser();
		System.out.println("PRIMIO SAM: " + dto.getFirstName() + " | " + dto.getLastName() + " | " + dto.getUsername() + " | " + dto.getOldPassword() + " | " + dto.getNewPassword());
		if(dto.getOldPassword() != null) {
			user.setPassword(BusinessUtil.hashPassword(dto.getNewPassword()));
		}
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setDateOfBirth(dto.getDateOfBirth());
		saveUsers();
		return getUserAccountInfromation();
	}
	
	public CustomerTypeDTO getLoggedUserCustomerType() {
		CustomerType retVal = loggedUser.getCustomerType();
		boolean show = true;
		if (retVal.getName() == CustomerTypeName.BRONZE) {
			show = false;
		}
		
		return new CustomerTypeDTO(retVal.getName(), retVal.getDiscount(), show);
	}
	
	public void checkForUpgrade() {
		User loggedUser = getLoggedUser();
		int pointsRequired = Bridge.getPointsNeeded(loggedUser.getCustomerType().getName());
		if (loggedUser.getTotalPoints() >= pointsRequired) {
			loggedUser.setCustomerType(Bridge.getBasedOnPoints(loggedUser.getTotalPoints()));
		}
		saveUsers();
	}


	public ArrayList<User> getCustomersFromSportObject(int id){
		ArrayList<User> retList = new ArrayList<User>();
		for(User it : users.values()) {
			if(checkForObject(it.getObjectsVisited(),id)){
				retList.add(it);
			}
		}
		return retList;
	}

	public boolean checkForObject(ArrayList<SportObject> objects,int id) {
		if(objects == null)
			return false;
		
		for(SportObject it : objects) {
			if(it.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<SportObject> getObjectsVisitedByUser(){
		return getLoggedUser().getObjectsVisited();
	}

 	public boolean checkUsername(String username) {
		return users.containsKey(username);
	}
	
	public ArrayList<User> sortByFirstName(boolean asc){
		ArrayList<User> userList = new ArrayList<User>(users.values());
		if (asc) {
			Collections.sort(userList, new FirstNameComparator());
		} else {
			Collections.sort(userList, new FirstNameComparator().reversed());
		}
		
		return userList;
	}
	
	public ArrayList<User> sortByLastName(boolean asc){
		ArrayList<User> userList = new ArrayList<User>(users.values());
		if (asc) {
			Collections.sort(userList, new LastNameComparator());
		} else {
			Collections.sort(userList, new LastNameComparator().reversed());
		}
		
		return userList;
	}
	
	public ArrayList<User> sortByPoints(boolean asc){
		ArrayList<User> userList = new ArrayList<User>(users.values());
		if (asc) {
			Collections.sort(userList, new PointsComparator());
		} else {
			Collections.sort(userList, new PointsComparator().reversed());
		}
		
		return userList;
	}
	
	public ArrayList<User> sortByUsername(boolean asc){
		ArrayList<User> userList = new ArrayList<User>(users.values());
		if (asc) {
			Collections.sort(userList, new NameComparator());
		} else {
			Collections.sort(userList, new NameComparator().reversed());
		}
		
		return userList;
	}
	
	public ArrayList<User> getByCustomerType(CustomerTypeName name){
		if(name == CustomerTypeName.NONE) {
			return new ArrayList<User>(users.values());
		}
		
		ArrayList<User> retList = new ArrayList<User>();
		for (User u : users.values()) {
			if(u.getRole() == Role.CUSTOMER) {
				if(u.getCustomerType().getName() == name) {
					retList.add(u);
				}
			}
		}
		return retList;
	}
 
	public ArrayList<User> getAllTrainers(){
		ArrayList<User> retList = new ArrayList<User>();
		for(User it : users.values()) {
			if(it.getRole() == Role.TRAINER) {
				retList.add(it);
			}
		}
		return retList;
	}
	
	public ArrayList<User> getAllCustomers(){
		ArrayList<User> retList = new ArrayList<User>();
		for(User it : users.values()) {
			if(it.getRole() == Role.CUSTOMER) {
				retList.add(it);
			}
		}
		return retList;
	}
	
}
