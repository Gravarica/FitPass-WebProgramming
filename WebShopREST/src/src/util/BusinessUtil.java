package src.util;

import beans.Entity;

public class BusinessUtil {

	public static String hashPassword(String password) {
		return Integer.toString(password.hashCode());
	}
	
	public static boolean stringContains(String property, String parameter) {
		return property.toLowerCase().contains(parameter.toLowerCase());
	}
	
	public static void filterUndeleted(Collection<Entity> entities) {
		for (Entity entity : entities) {
			if(entity.isDeleted()) {
				entities.remove(entity);
			}
		}
	}
}
