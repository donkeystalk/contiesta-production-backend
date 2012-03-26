package contiesta.production.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class TrainedSkill {

	@Id
	@GeneratedValue
	private int id;
	private int typeID;
	private int skillPoints;
	private int level;
	
	@JoinColumn(name="typeID", referencedColumnName="typeName", insertable=false, updatable=false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="characterID")
	private EveCharacter eveCharacter;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EveCharacter getEveCharacter() {
		return eveCharacter;
	}
	public void setEveCharacter(EveCharacter eveCharacter) {
		this.eveCharacter = eveCharacter;
	}	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
