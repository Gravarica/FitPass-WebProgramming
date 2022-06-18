package src.util;

public class BusinessUtil {

	public static String hashPassword(String password) {
		return Integer.toString(password.hashCode());
	}
}
