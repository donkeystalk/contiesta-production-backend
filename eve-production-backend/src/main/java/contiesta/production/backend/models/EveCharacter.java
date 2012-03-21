package contiesta.production.backend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class EveCharacter {
	
	@Id
	private String name;
	private long characterID;
	private String corporationName;
	
	@ManyToOne
	private ApiContext apiContext;

	public long getCharacterID() {
		return characterID;
	}

	public void setCharacterID(long characterID) {
		this.characterID = characterID;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public ApiContext getApiContext() {
		return apiContext;
	}

	public void setApiContext(ApiContext apiContext) {
		this.apiContext = apiContext;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
