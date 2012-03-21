package contiesta.production.backend.marshallers;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.stream.events.Characters;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Component;

import contiesta.production.backend.models.EveCharacter;

@Component
public class CharactersMarshaller implements EveMarshaller<List<EveCharacter>>{

	@Autowired
	@Qualifier(value="charactersCastorMarshaller")
	private Unmarshaller unmarshaller;
	
	private static final Logger logger = LoggerFactory.getLogger(CharactersMarshaller.class);
	
	public List<EveCharacter> unmarshallXMLToObject(String xml)
	{
		StringReader sr = new StringReader(xml);
		try
		{
			CharactersMapping marshallObj = (CharactersMapping)unmarshaller.unmarshal(new StreamSource(sr));
			return marshallObj.getCharacters();
		}
		catch(IOException e)
		{
			logger.error("Error while marshalling", e);
		}
		return null;
	}
}
