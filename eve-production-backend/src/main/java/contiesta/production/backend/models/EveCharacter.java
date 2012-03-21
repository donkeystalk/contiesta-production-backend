package contiesta.production.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EveCharacter {
	
	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
