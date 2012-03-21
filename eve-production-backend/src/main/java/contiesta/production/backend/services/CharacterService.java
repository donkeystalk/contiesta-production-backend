package contiesta.production.backend.services;

import java.util.List;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;

public interface CharacterService {
	
	void addCharacter(ApiContext context);
	List<EveCharacter> findEveCharactersForApiContext(ApiContext context);
}
