package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.classfile.Utility;

import beans.Artikal;
import beans.User;
import dto.LoginDTO;
import dto.LoginReturnDTO;
import dto.RegistrationDTO;
import enums.CustomerTypeName;
import enums.Role;
import src.util.BusinessUtil;

/***
 * <p>Klasa namenjena da uèita korisnike iz fajla i pruža operacije nad njima (poput pretrage).
 * Korisnici se nalaze u fajlu WebContent/users.txt u obliku: <br>
 * firstName;lastName;email;username;password</p>
 * <p><b>NAPOMENA:</b> Lozinke se u praksi <b>nikada</b> ne snimaju u èistu tekstualnom obliku.</p>
 * @author Lazar
 *
 */
public class UserDAO {
	private Map<String, User> users = new HashMap<>();
	private File file;
	private User loggedUser;
	
	public UserDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Može se pristupiti samo iz servleta.
	 */
	public UserDAO(String contextPath) {
		file = new File(contextPath + "/Resources/Data/users.json");
		loadUsers(contextPath);
		//System.out.println(contextPath);
	}
	
	/**
	 * Vraæa korisnika za prosleðeno korisnièko ime i šifru. Vraæa null ako korisnik ne postoji
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
	 * Uèitava korisnike iz WebContent/users.txt fajla i dodaje ih u mapu {@link #users}.
	 * Kljuè je korisnièko ime korisnika.
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadUsers(String contextPath) {
		
		ObjectMapper mapper = new ObjectMapper();
	
		try {
			List<User> userList = mapper.readValue(file, new TypeReference<List<User>>() {});
			convertListToMap(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
			System.out.println("USAO SAM OVDE");
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
		User user = users.get(dto.getUsername());
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
	
	
}
