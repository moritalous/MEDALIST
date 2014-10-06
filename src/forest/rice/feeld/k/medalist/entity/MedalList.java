package forest.rice.feeld.k.medalist.entity;

import java.util.ArrayList;
import java.util.List;

public class MedalList {
	
	public List<Medal> allMedal = new ArrayList<Medal>();
	
	public void add(Medal medal) {
		if(medal == null) return;
		allMedal.add(medal);
	}
	
	public void add(MedalList medalList){
		allMedal.addAll(medalList.allMedal);
	}

}
