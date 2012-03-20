package contiesta.production.backend.repos;

import contiesta.production.backend.models.EveCharacter;

public interface CharacterRepo {

	EveCharacter findCharacterByName(String name);
	
}
