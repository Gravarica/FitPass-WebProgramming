package comparators.object;

import java.util.Comparator;

import beans.SportObject;

public class LocationComparator implements Comparator<SportObject>{

	@Override
	public int compare(SportObject o1, SportObject o2) {
		// TODO Auto-generated method stub
		if (o1.getLocation().cityIsAlphabeticallySmaller(o2.getLocation())) {
			return -1;
		} else if (o1.getLocation().cityIsAlphabeticallyGreater(o2.getLocation())){ 
			return 1;
		}
		
		if (o1.getLocation().streetIsAlphabeticallySmaller(o2.getLocation())) {
			return -1;
		} else if (o1.getLocation().streetIsAlphabeticallyGreater(o2.getLocation())){
			return 1;
		}
		
		return 0;
	}

}
