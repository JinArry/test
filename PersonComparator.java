import java.util.Comparator;


public class PersonComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		Person s1=(Person) o1;
		Person s2=(Person) o2;
//		if (s1.getScore()>s2.getScore()) {
//			return -1;
//		}
//		if (s1.getScore()<s2.getScore()) {
//			return 1;
//		}
		if (s1.getUser_Id().equals(s2.getUser_Id())) {
			if (s1.getScore()>s2.getScore()) {
				return -1;
			}
			if (s1.getScore()<s2.getScore()) {
				return 1;
			}
		}
//		
		// TODO Auto-generated method stub
		return 0;
	}

}
