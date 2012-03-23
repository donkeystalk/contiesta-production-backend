package contiesta.production.backend.marshallers;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.utils.ClassPathResourceUtil;

@ContextConfiguration("classpath:config/backend-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCharactersMarshaller {
	
	@Autowired
	private CharactersMarshaller marshaller;

	@Test
	public void testInject()
	{
		assertNotNull(marshaller);
	}
	
	@Test
	public void testUnmarshallXMLToObject()
	{
		String xml = ClassPathResourceUtil.getResourceAsString("testFiles/characters.xml");
		List<String> characters = marshaller.findCharacterIds(xml);
		assertNotNull(characters);
		assertEquals(3,characters.size());
	}
}
