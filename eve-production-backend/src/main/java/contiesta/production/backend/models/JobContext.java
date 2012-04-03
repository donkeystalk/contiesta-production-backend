package contiesta.production.backend.models;

import java.util.List;

public class JobContext {
	private String name;
	private ScienceContext scienceContext;
	private IndustryContext industryContext;
	private List<Job> jobs;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ScienceContext getScienceContext() {
		return scienceContext;
	}
	public void setScienceContext(ScienceContext scienceContext) {
		this.scienceContext = scienceContext;
	}
	public IndustryContext getIndustryContext() {
		return industryContext;
	}
	public void setIndustryContext(IndustryContext industryContext) {
		this.industryContext = industryContext;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}
