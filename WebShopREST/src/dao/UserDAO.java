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

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Artikal;
import beans.User;
import dto.LoginDTO;
import dto.RegistrationDTO;

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
	private User loggedUser;
	
	public UserDAO() {
		
	}
	
	/***
	 * @param contextPath Putanja do aplikacije u Tomcatu. Mo�e se pristupiti samo iz servleta.
	 */
	public UserDAO(String contextPath) {
		file = new File(contextPath + "/users.json");
		loadUsers(contextPath);
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
	
	public Collection<User> getAll() {
		return users.values();
	}
	
	/**
	 * U�itava korisnike iz WebContent/users.txt fajla i dodaje ih u mapu {@link #users}.
	 * Klju� je korisni�ko ime korisnika.
	 * @param contextPath Putanja do aplikacije u Tomcatu
	 */
	private void loadUsers(String contextPath) {
		
		ObjectMapper mapper = new ObjectMapper();
	
		try {
			List<User> userList = Arrays.asList(mapper.readValue(file, User[].class));
			convertListToMap(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public User register(RegistrationDTO dto) {
		
		User saveUser = new User(dto);
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
			mapper.writeValue(file, userList.toArray());
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
	
	public User login(LoginDTO dto) {
		User user = users.get(dto.getUsername());
		if (user == null || !user.passwordMatches(dto)) return null;
		
		return loggedUser = user;
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
}
