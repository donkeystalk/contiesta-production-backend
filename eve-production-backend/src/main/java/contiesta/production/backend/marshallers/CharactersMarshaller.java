package contiesta.production.backend.marshallers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.tree.DefaultElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import contiesta.production.backend.models.Job;
import contiesta.production.backend.utils.XPathUtils;

@Component
public class CharactersMarshaller {

	private static final Logger logger = LoggerFactory.getLogger(CharactersMarshaller.class);
	
	private static final String NAMES_XPATH = "//rowset[@name='characters']/row";
	private static final String JOBS_XPATH = "//rowset[@name='jobs']/row";
	private static final String INSTALLED_ITEM_TYPE_ID = "installedItemTypeID";
	private static final String OUTPUT_ITEM_TYPE_ID = "outputTypeID";
	
	public List<String> findCharacterIds(String xml)
	{
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
	
	public List<Job> findNumberOfJobsForCharacter(String xml)
	{
		try
		{
			Document doc = DocumentHelper.parseText(xml);
			List<DefaultElement> elements = XPathUtils.parseForListOfElements(doc, JOBS_XPATH);
			List<Job> jobs = new ArrayList<Job>();
			if(elements != null && !elements.isEmpty())
			{
				jobs = parseElementsForJobs(elements);
			}
			return jobs;
		}
		catch(DocumentException e)
		{
			logger.error("Error parsing XML", e);
		}
		return Collections.EMPTY_LIST;
	}

	private List<Job> parseElementsForJobs(List<DefaultElement> elements) {
		List<Job> jobs = new ArrayList<Job>();
		for(DefaultElement e : elements)
		{
			Job j = new Job();
			j.setBlueprintTypeId(Integer.parseInt(XPathUtils.parseElementForAttribute(e, INSTALLED_ITEM_TYPE_ID)));
			j.setOutputTypeId(Integer.parseInt(XPathUtils.parseElementForAttribute(e, OUTPUT_ITEM_TYPE_ID)));
			jobs.add(j);
		}
		return jobs;
	}
}
