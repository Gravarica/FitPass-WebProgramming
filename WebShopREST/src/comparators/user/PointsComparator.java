package comparators.user;

import java.util.Comparator;

import beans.User;

public class PointsComparator implements Comparator<User>{

	@Override
	public int compare(User a, User b) {
		// TODO Auto-generated method stub
		if (a.getTotalPoints() > b.getTotalPoints()) {
			return 1;
		} else if (a.getTotalPoints() < b.getTotalPoints()){
			return -1;
		} 
		
		return 0;
	}

}
