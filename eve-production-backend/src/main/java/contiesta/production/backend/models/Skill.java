package contiesta.production.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Skill {

	@Id
	private int typeID;
	private String typeName;
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
