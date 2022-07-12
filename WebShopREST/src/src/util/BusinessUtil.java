package src.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import beans.Content;
import beans.Entity;
import beans.SportObject;
import beans.User;
import beans.WorkHour;
import dto.NewContentDTO;
import enums.SubscriptionType;

public class BusinessUtil {

	public static String hashPassword(String password) {
		return Integer.toString(password.hashCode());
	}
	
	public static boolean stringContains(String property, String parameter) {
		return property.toLowerCase().contains(parameter.toLowerCase());
	}
	
	public static void filterUndeleted(List<Entity> entities) {
		for (Entity entity : entities) {
			if(entity.isDeleted()) {
				entities.remove(entity);
			}
		}
	}
	
	public static boolean checkIfContentExists(ArrayList<Content> contents, NewContentDTO dto) {
		for (Content content : contents) {
			if(dto.getName().equals(content.getName())){
				return true;
			}
		}
		
		return false;
	}
	
	public static String generateRandomString() {

		UUID randomUUID = UUID.randomUUID();

		return randomUUID.toString().replaceAll("_", "").substring(0,9);

	}
	
	public static LocalDate concludeExpirationDate(SubscriptionType type) {
		if (type == SubscriptionType.DAY) {
			return LocalDate.now().plusDays(1);
		} else if (type == SubscriptionType.WEEK) {
			return LocalDate.now().plusWeeks(1);
		} else if (type == SubscriptionType.MONTH) {
			return LocalDate.now().plusMonths(1);
		} 
		
		return LocalDate.now().plusYears(1);
	}
	
	public static boolean listContains(ArrayList<SportObject> objects, SportObject obj) {
		for(SportObject o : objects) {
			if(o.getId() == obj.getId()) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean listContains(ArrayList<User> users, User usr) {
		for(User u : users) {
			if(u.getUsername().toLowerCase().equals(usr.getUsername().toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean timeIsInZone(WorkHour wh) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime target = LocalTime.now();
		LocalTime startTime = LocalTime.parse(wh.getStartTime(), formatter);
		LocalTime endTime = LocalTime.parse(wh.getEndTime(), formatter);
		LocalTime midnight = LocalTime.parse("00:00", formatter);
		
		// U slucaj da je od 00 do 00
		if (startTime.equals(endTime)) {
			return true;
		}
		
		if (target.isAfter(startTime) && endTime.equals(midnight)) {
			return true;
		}
		
		return target.isAfter(startTime) && target.isBefore(endTime);
	}
}
