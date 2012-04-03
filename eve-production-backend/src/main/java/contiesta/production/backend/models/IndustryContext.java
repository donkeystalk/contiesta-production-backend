package contiesta.production.backend.models;

import java.util.Comparator;

public class IndustryContext {
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
	
	public class IndustryComparator implements Comparator<IndustryContext>
	{
		@Override
		public int compare(IndustryContext c1, IndustryContext c2) {
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
