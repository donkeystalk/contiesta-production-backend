package contiesta.production.backend.marshallers;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.stream.events.Characters;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Component;

import contiesta.production.backend.models.EveCharacter;

@Component
public class CharactersMarshaller {

	@Autowired
	@Qualifier(value="charactersCastorMarshaller")
	private Unmarshaller unmarshaller;
	
	public List<EveCharacter> unmarshallXMLToObject(String xml) throws XmlMappingException, IOException
	{
		StringReader sr = new StringReader(xml);
		CharactersMapping marshallObj = (CharactersMapping)unmarshaller.unmarshal(new StreamSource(sr));
		return marshallObj.getCharacters();
	}
}
