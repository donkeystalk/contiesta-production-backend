package contiesta.production.backend.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ApiContext {

	@Id
	private String keyId;
	private String verificationCode;
	
	@OneToMany(mappedBy="apiContext")
	private List<EveCharacter> eveCharacter;
	
	public List<EveCharacter> getEveCharacter() {
		return eveCharacter;
	}
	public void setEveCharacter(List<EveCharacter> eveCharacter) {
		this.eveCharacter = eveCharacter;
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
