package contiesta.production.backend.models;

public class Job {
	private int blueprintTypeId;
	private int outputTypeId;
	
	public int getBlueprintTypeId() {
		return blueprintTypeId;
	}
	public void setBlueprintTypeId(int blueprintTypeId) {
		this.blueprintTypeId = blueprintTypeId;
	}
	public int getOutputTypeId() {
		return outputTypeId;
	}
	public void setOutputTypeId(int outputTypeId) {
		this.outputTypeId = outputTypeId;
	}
}
