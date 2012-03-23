package contiesta.production.backend.marshallers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.utils.ClassPathResourceUtil;

@ContextConfiguration("classpath:config/backend-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCharacterSheetMarshaller {

	@Autowired
	private CharacterSheetMarshaller marshaller;
	
	@Test
	public void testInject()
	{
		assertNotNull(marshaller);
	}
	
	@Test
	public void testUnmarshallXMLToObject() throws IOException
	{
		EveCharacter character = marshaller.unmarshallXMLToObject(ClassPathResourceUtil.getResourceAsString("testFiles/characterSheet.xml"));
		assertNotNull(character.getTrainedSkills());
		assertEquals(2, character.getTrainedSkills().size());
		assertEquals("Gummi Test1", character.getName());
		assertEquals("Starbase Anchoring Corp", character.getCorporationName());
	}
}
