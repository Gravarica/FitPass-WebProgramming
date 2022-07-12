package comparators.user;

import java.util.Comparator;

import beans.User;

public class NameComparator implements Comparator<User>{

	@Override
	public int compare(User a, User b) {
		// TODO Auto-generated method stub
		return a.getUsername().compareToIgnoreCase(b.getUsername());
	}

}
