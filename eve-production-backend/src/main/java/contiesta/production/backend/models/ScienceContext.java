package contiesta.production.backend.models;

import java.util.Comparator;

public class ScienceContext {
	private int totalPossible;
	private int inProgress;
	
	public int getTotalPossible() {
		return totalPossible;
	}
	public void setTotalPossible(int totalPossible) {
		this.totalPossible = totalPossible;
	}
	public int getInProgress() {
		return inProgress;
	}
	public void setInProgress(int inProgress) {
		this.inProgress = inProgress;
	}
	
	public class ScienceComparator implements Comparator<ScienceContext> 
	{

		public int compare(ScienceContext c1, ScienceContext c2) {
			if((c1.getTotalPossible() - c1.getInProgress()) > 
			   (c2.getTotalPossible() - c2.getInProgress()))
			{
				return -1;
			}
			else if((c1.getTotalPossible() - c1.getInProgress()) <
			   (c2.getTotalPossible() - c2.getInProgress()))
			{
				return 1;
			}
			return 0;
		}

	}
	
	@Override
	public String toString() {
		return inProgress + " / " + totalPossible;
	}
}
