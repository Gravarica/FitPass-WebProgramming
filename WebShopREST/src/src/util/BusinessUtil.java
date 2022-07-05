package src.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import beans.Content;
import beans.Entity;
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
}
