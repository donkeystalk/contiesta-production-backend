package contiesta.production.backend.repos;

import java.util.List;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;

public interface CharacterRepo {

	<T> void save(T apiContext);
	List<ApiContext> findAllApiContext();
	<T> void delete(T obj);
}
