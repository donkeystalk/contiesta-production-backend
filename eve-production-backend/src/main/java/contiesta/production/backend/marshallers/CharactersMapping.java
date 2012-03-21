package contiesta.production.backend.marshallers;

import java.util.List;

import contiesta.production.backend.models.EveCharacter;

public class CharactersMapping {

	private int version;
	private List<EveCharacter> characters;
	
	public List<EveCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(List<EveCharacter> characters) {
		this.characters = characters;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
