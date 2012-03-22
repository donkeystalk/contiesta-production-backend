package contiesta.production.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ApiContext {

	@Id
	private String keyId;
	private String verificationCode;
	
	@Cascade(value=CascadeType.ALL)
	@OneToMany(mappedBy="apiContext", orphanRemoval=true)
	private List<EveCharacter> eveCharacters;
	
	public List<EveCharacter> getEveCharacters() {
		return eveCharacters;
	}
	public void setEveCharacters(List<EveCharacter> eveCharacters) {
		this.eveCharacters = eveCharacters;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}	
	
}
