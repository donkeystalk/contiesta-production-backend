package contiesta.production.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TrainedSkill {

	@Id
	@GeneratedValue
	private int id;
	private int typeId;
	private int skillPoints;
	private int level;
	
	@ManyToOne
	@JoinColumn(name="characterId")
	private EveCharacter eveCharacter;
	
	public EveCharacter getEveCharacter() {
		return eveCharacter;
	}
	public void setEveCharacter(EveCharacter eveCharacter) {
		this.eveCharacter = eveCharacter;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
