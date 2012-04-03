package contiesta.production.backend.services;

import java.util.List;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.models.JobContext;

public interface CharacterService {
	List<ApiContext> findAllApiContext();
	List<String> findEveCharacterIdsApiContext(ApiContext context);
	boolean createApiContext(ApiContext context);
	void removeApiContext(String id);
	List<EveCharacter> findEveCharactersByKeyId(String id);
	List<JobContext> findAllIndustryJobs();
}
