package contiesta.production.backend.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;

@ContextConfiguration("classpath:config/backend-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationCharacterServiceImpl {

	private static final String KEY_ID = "542800";
	private static final String VERIFICATION_CODE = "WJ2c99vYKqmKRQ12SuKXLtpo5jT8rKILbgmgVePK61iBYah9f77EgVZXRWCyRod3";
	
	@Autowired
	private CharacterService service;
	
	@Test
	public void testInject()
	{
		assertNotNull(service);
	}
	
	@Test
	public void testFindEveCharactersForApiContext() {
		ApiContext context = new ApiContext();
		context.setKeyId(KEY_ID);
		context.setVerificationCode(VERIFICATION_CODE);
		List<EveCharacter> characters = service.findEveCharactersForApiContext(context);
		assertNotNull(characters);
	}
	
}
