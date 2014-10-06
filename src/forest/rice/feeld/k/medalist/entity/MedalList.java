package forest.rice.feeld.k.medalist.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MedalList {

	public List<Medal> allMedal = new ArrayList<Medal>();

	public void add(Medal medal) {
		if (medal == null) {
			return;
		}
		allMedal.add(medal);
	}

	public void add(MedalList medalList) {
		allMedal.addAll(medalList.allMedal);
	}

	public class MedalComparator implements Comparator<Medal> {

		@Override
		public int compare(Medal medal1, Medal medal2) {

			int id1 = Integer.parseInt(medal1.sort_num.trim());
			int id2 = Integer.parseInt(medal2.sort_num.trim());

			return id1 - id2;
		}
	}
}
