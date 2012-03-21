package contiesta.production.backend.repos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import contiesta.production.backend.models.EveCharacter;

@ContextConfiguration("classpath:config/backend-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestHibernateCharacterRepo {

	@Autowired
	private HibernateCharacterRepo repo;
	
	@Test
	public void testInject()
	{
		assertNotNull(repo);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void testFindCharacterByName()
	{
		EveCharacter ec = new EveCharacter();
		ec.setName("dardo");
		repo.saveCharacter(ec);
		ec = null;
		ec = repo.findCharacterByName("dardo");
		assertNotNull(ec);
		assertEquals("dardo", ec.getName());
	}
	
}
