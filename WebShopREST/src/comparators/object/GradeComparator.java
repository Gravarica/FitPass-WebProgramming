package comparators.object;

import java.util.Comparator;

import beans.SportObject;

public class GradeComparator implements Comparator<SportObject>{

	@Override
	public int compare(SportObject a, SportObject b) {
		// TODO Auto-generated method stub
		if (a.getAverageGrade() > b.getAverageGrade()) {
			return 1;
		} else if(a.getAverageGrade() < b.getAverageGrade()) {
			return -1;
		}
		
		return 0;
	}

}
