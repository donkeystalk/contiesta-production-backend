package contiesta.production.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {

	@Id
	private int typeID;
	private String name;
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
