package contiesta.production.backend.repos;

import java.util.List;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;

public interface CharacterRepo {

	ApiContext findApiContextByID(String keyID);
	<T> void save(T apiContext);
	List<ApiContext> findAllApiContext();
	<T> void delete(T obj);
}
