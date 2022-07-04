package src.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import beans.Content;
import beans.Entity;
import dto.NewContentDTO;

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
}
