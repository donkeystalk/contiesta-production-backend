package contiesta.production.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {

	@Id
	private int typeID;
	private int skillPoints;
	private int level;
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public int getSkillPoints() {
		return skillPoints;
	}
	public void setSkillPoints(int skillPoints) {
		this.skillPoints = skillPoints;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
