package comparators.user;

import java.util.Comparator;

import beans.User;

public class LastNameComparator implements Comparator<User>{

	@Override
	public int compare(User a, User b) {
		// TODO Auto-generated method stub
		return a.getLastName().compareToIgnoreCase(b.getLastName());
	}

}
