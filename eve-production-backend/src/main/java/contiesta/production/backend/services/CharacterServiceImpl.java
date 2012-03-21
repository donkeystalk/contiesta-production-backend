package contiesta.production.backend.services;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import contiesta.production.backend.marshallers.CharactersMarshaller;
import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.repos.CharacterRepo;
import contiesta.production.backend.utils.ApiServiceUtils;

@Service
public class CharacterServiceImpl implements CharacterService{

	@Autowired
	private CharacterRepo characterRepo;
	
	@Autowired
	private CharactersMarshaller charactersMarshaller;
	
	private static final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);
	
	@Transactional
	public void addCharacter(ApiContext context) {
		characterRepo.saveApiContext(context);
	}

	public List<EveCharacter> findEveCharactersForApiContext(ApiContext context) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> re = rt.getForEntity(ApiServiceUtils.CHARACTERS_SERVICE, String.class, context.getKeyId(), context.getVerificationCode());
		try
		{
			return charactersMarshaller.unmarshallXMLToObject(re.getBody());
		}
		catch(IOException e)
		{
			logger.error("Error while unmarshalling API context", e);
		}
		return null;
	}

}
