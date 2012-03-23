package contiesta.production.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import contiesta.production.backend.marshallers.CharacterSheetMarshaller;
import contiesta.production.backend.marshallers.CharactersMarshaller;
import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.models.TrainedSkill;
import contiesta.production.backend.repos.CharacterRepo;
import contiesta.production.backend.utils.ApiServiceUtils;

@Service
public class CharacterServiceImpl implements CharacterService{

	@Autowired
	private CharacterRepo characterRepo;
	
	@Autowired
	private CharactersMarshaller charactersMarshaller;
	@Autowired
	private CharacterSheetMarshaller characterSheetMarshaller;
	
	private static final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);
	
	/**
	 * 
	 * @param context
	 * @return True - ApiContext was saved to database with EveCharacter's associated with it
	 * False - ApiContext already exists
	 */
	@Transactional
	public void createApiContext(ApiContext context)
	{
		List<EveCharacter> chars = new ArrayList<EveCharacter>();
		for(String c : findEveCharacterIdsApiContext(context))
		{
			chars.add(findCharacterSheet(context, c));
		}
		context.setEveCharacters(chars);
		characterRepo.save(context);
	}

	@Transactional
	public List<ApiContext> findAllApiContext()
	{
		return characterRepo.findAllApiContext();
	}
	
	public List<String> findEveCharacterIdsApiContext(ApiContext context) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> re = rt.getForEntity(ApiServiceUtils.CHARACTERS_SERVICE, String.class, context.getKeyId(), context.getVerificationCode());
		return charactersMarshaller.findCharacterIds(re.getBody());
	}
	
	public EveCharacter findCharacterSheet(ApiContext context, String id)
	{
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> re = rt.getForEntity(ApiServiceUtils.CHARACTER_SHEET_SERVICE, String.class, context.getKeyId(), context.getVerificationCode(), id);
		EveCharacter retVal = characterSheetMarshaller.unmarshallXMLToObject(re.getBody());
		retVal.setApiContext(context);
		return retVal;
	}

	@Transactional
	public void removeApiContext(ApiContext context) {
		characterRepo.delete(context);
	}

}
