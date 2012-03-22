package contiesta.production.backend.services;

import java.util.List;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;

public interface CharacterService {
	List<ApiContext> findAllApiContext();
	List<EveCharacter> findEveCharactersForApiContext(ApiContext context);
	boolean createApiContext(ApiContext context);
	void removeApiContext(ApiContext context);
}
