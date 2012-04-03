package contiesta.production.backend.utils;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.tree.DefaultElement;

public class XPathUtils {
	
	public static String parseForText(Document doc, String xpath)
	{
		return ((DefaultElement)doc.selectSingleNode(xpath)).getText();
	}
	
	public static List<DefaultElement> parseForListOfElements(Document doc, String xpath)
	{
		return doc.selectNodes(xpath);
	}
	
	public static String parseElementForAttribute(DefaultElement element, String xpath)
	{
		return element.attributeValue(xpath);
	}
}
