package contiesta.production.backend.repos;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import contiesta.production.backend.models.ApiContext;
import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.models.TrainedSkill;

@ContextConfiguration("classpath:config/backend-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationHibernateCharacterRepo {

	@Autowired
	private HibernateCharacterRepo repo;

	private static final String KEY_ID = "542800";
	private static final String VERIFICATION_CODE = "WJ2c99vYKqmKRQ12SuKXLtpo5jT8rKILbgmgVePK61iBYah9f77EgVZXRWCyRod3";
	
	private static final Logger logger = Logger.getLogger(IntegrationHibernateCharacterRepo.class);
	
	@Test
	public void testInject()
	{
		assertNotNull(repo);
	}
	
	@Test
	public void testSaveApiContext()
	{
		ApiContext c = new ApiContext();
		c.setKeyId(KEY_ID);
		c.setVerificationCode(VERIFICATION_CODE);
		repo.save(c);
	}
	
	@Test
	public void testSaveApiContextWithEveCharacter()
	{
		ApiContext c = new ApiContext();
		c.setKeyId(KEY_ID);
		c.setVerificationCode(VERIFICATION_CODE);
		List<EveCharacter> chars = new ArrayList<EveCharacter>();
		for(int i=0; i<3; i++)
		{
			chars.add(createEveCharacter(c, i));
		}
		c.setEveCharacters(chars);
		repo.save(c);
	}

	@Test
	public void testDeleteApiContext()
	{
		ApiContext context = repo.findByKey(ApiContext.class, 1);
		assertNotNull(context);
		repo.delete(context);
	}
	
	@Test
	@Transactional
	public void testFindSkillLevelForTrainedSkill()
	{
		int charId = 790502185;
		int typeId = 3308;
		int level = repo.findSkillLevelForTrainedSkill(charId, typeId);
		logger.info(level);
	}
	
	private EveCharacter createEveCharacter(ApiContext c, int i) {
		EveCharacter e = new EveCharacter();
		e.setApiContext(c);
		e.setCharacterId(1234 + i);
		e.setCorporationName("Test Corp");
		e.setName("Test Char");
		e.setTrainedSkills(createTrainedSkills(e));
		return e;
	}

	private List<TrainedSkill> createTrainedSkills(EveCharacter e) {
		List<TrainedSkill> s = new ArrayList<TrainedSkill>();
		for(int i=0; i<5; i++)
		{
			s.add(createTrainedSkill(e));
		}		
		return s;
	}

	private TrainedSkill createTrainedSkill(EveCharacter e)
	{
		TrainedSkill s = new TrainedSkill();
		s.setEveCharacter(e);
		s.setLevel(2);
		s.setSkillPoints(256000);
		s.setTypeId(1234);
		return s;
	}
	
}
