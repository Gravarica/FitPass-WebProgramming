package src.util;

public class BusinessUtil {

	public static String hashPassword(String password) {
		return Integer.toString(password.hashCode());
	}
	
	public static boolean stringContains(String property, String parameter) {
		return property.toLowerCase().contains(parameter.toLowerCase());
	}
}
