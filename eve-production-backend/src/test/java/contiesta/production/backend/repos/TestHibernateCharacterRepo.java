package contiesta.production.backend.repos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
}
