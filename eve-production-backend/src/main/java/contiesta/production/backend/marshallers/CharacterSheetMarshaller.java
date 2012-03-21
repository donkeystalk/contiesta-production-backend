package contiesta.production.backend.marshallers;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.KeyStore.Builder;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.models.Skill;

@Component
public class CharacterSheetMarshaller{

	private static final Logger logger = LoggerFactory.getLogger(CharactersMarshaller.class);
	
	public EveCharacter unmarshallXMLToObject(String xml) {
		try{
			Document document = DocumentHelper.parseText(xml);
			EveCharacter character = new EveCharacter();
			return character;
		}
		catch(DocumentException e)
		{
			logger.error("Error parsing XML", e);
		}
		return null;
	}

}
