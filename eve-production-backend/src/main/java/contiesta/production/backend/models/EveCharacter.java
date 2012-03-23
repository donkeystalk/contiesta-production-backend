package contiesta.production.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class EveCharacter {
	
	@Id
	private int characterId;
	private String name;
	private String corporationName;
	
	@OneToMany(mappedBy="eveCharacter")
	@Cascade(value=CascadeType.ALL)
	private List<TrainedSkill> trainedSkills;

	@ManyToOne
	@JoinColumn(name="apiId")
	private ApiContext apiContext;

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}

	public List<TrainedSkill> getTrainedSkills() {
		return trainedSkills;
	}

	public void setTrainedSkills(List<TrainedSkill> trainedSkills) {
		this.trainedSkills = trainedSkills;
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
