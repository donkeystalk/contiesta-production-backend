package contiesta.production.backend.repos;

import java.io.Serializable;
import java.util.List;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;

public interface CharacterRepo {

	<T> void save(T apiContext);
	List<ApiContext> findAllApiContext();
	<T> void delete(T obj);
	<T> T findByKey(Class<T> c, int key);
}
