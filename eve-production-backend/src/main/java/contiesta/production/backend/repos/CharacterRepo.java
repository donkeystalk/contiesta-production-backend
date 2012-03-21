package contiesta.production.backend.repos;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;

public interface CharacterRepo {

	EveCharacter findCharacterByName(String name);
	void saveApiContext(ApiContext apiContext);
}
