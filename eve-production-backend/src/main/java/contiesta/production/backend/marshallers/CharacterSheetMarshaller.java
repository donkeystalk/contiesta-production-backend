package contiesta.production.backend.marshallers;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;
import org.springframework.stereotype.Component;

import contiesta.production.backend.models.EveCharacter;
import contiesta.production.backend.models.TrainedSkill;
import contiesta.production.backend.utils.XPathUtils;

@Component
public class CharacterSheetMarshaller{

	private static final Logger logger = Logger.getLogger(CharactersMarshaller.class);
	
	private static final String CHARACTER_ID_XPATH = "//characterID";
	private static final String SKILLS_XPATH = "//rowset[@name='skills']/row";
	private static final String NAME_XPATH = "//name";
	private static final String CORP_NAME_XPATH = "//corporationName";
	
	/**
	 * Not a true unmarshaller, using Xpath to parse the xml, due to the fact that
	 * the API sends back badly formed XML making it difficult to have a useful
	 * XSD
	 * 
	 * @param xml
	 * @return EveCharacter
	 */
	public EveCharacter unmarshallXMLToObject(String xml) {
		EveCharacter character = new EveCharacter();
		try
		{
			Document doc = DocumentHelper.parseText(xml);
			character.setTrainedSkills(parseForSkills(XPathUtils.parseForListOfElements(doc, SKILLS_XPATH), character));
			character.setName(XPathUtils.parseForText(doc, NAME_XPATH));
			character.setCorporationName(XPathUtils.parseForText(doc, CORP_NAME_XPATH));
			character.setCharacterId(Integer.parseInt(XPathUtils.parseForText(doc, CHARACTER_ID_XPATH)));
			return character;
		}
		catch(DocumentException e)
		{
			logger.error("Error parsing XML document", e);
		}
		return null;
	}
	
	protected List<TrainedSkill> parseForSkills(List<DefaultElement> elements, EveCharacter c)
	{
		List<TrainedSkill> retVal = new ArrayList<TrainedSkill>();
		for(DefaultElement s : elements)
		{
			TrainedSkill skill = new TrainedSkill();
			skill.setTypeID(Integer.parseInt(s.attribute("typeID").getValue()));
			skill.setSkillPoints(Integer.parseInt(s.attribute("skillpoints").getValue()));
			skill.setLevel(Integer.parseInt(s.attribute("level").getValue()));
			skill.setEveCharacter(c);
			retVal.add(skill);
		}
		return retVal;
	}

}
