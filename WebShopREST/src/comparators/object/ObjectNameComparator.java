package comparators.object;

import java.util.Comparator;

import beans.SportObject;

public class ObjectNameComparator implements Comparator<SportObject>{

	@Override
	public int compare(SportObject a, SportObject b) {
		// TODO Auto-generated method stub
		return a.getName().compareToIgnoreCase(b.getName());
	}

}
