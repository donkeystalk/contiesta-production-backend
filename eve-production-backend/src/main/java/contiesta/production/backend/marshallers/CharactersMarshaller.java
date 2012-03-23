package contiesta.production.backend.marshallers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import contiesta.production.backend.utils.XPathUtils;

@Component
public class CharactersMarshaller {

	private static final Logger logger = LoggerFactory.getLogger(CharactersMarshaller.class);
	
	private static final String NAMES_XPATH = "//rowset[@name='characters']/row";
	
	public List<String> findCharacterIds(String xml)
	{
		StringReader sr = new StringReader(xml);
		try
		{
			Document doc = DocumentHelper.parseText(xml);
			List<DefaultElement> elements = XPathUtils.parseForListOfElements(doc, NAMES_XPATH);
			List<String> retVal = new ArrayList<String>();
			for(DefaultElement e : elements)
			{
				retVal.add(e.attributeValue("characterID"));
			}
			return retVal;
		}
		catch(DocumentException e)
		{
			logger.error("Error parsing XML", e);
		}
		return null;
	}
}
