import java.io.FileReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {
	String xml;
	
	public XMLParser(String _xml){
		xml=_xml;
		xmlParse(xml);
	}
	
	private void xmlParse(String xml) {
		   
		   try{
			
//			   InputSource is = new InputSource(new FileReader("C:/TEMP/test.xml"));
//			   Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

			   InputSource is = new InputSource(new StringReader(xml)); 
			   Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
			    // xpath »ý¼º
			   XPath  xpath = XPathFactory.newInstance().newXPath();
			   
			   String expression = "//*/person";
			   NodeList  cols = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
			   
						
			   for( int idx=0; idx<cols.getLength(); idx++ ){
				   
				   String ssn=cols.item(idx).getAttributes().item(0).getTextContent();
				   System.out.println("ssn................"+ssn);
				   
				   expression = "//*[@ssn="+ssn+"]/firstName";
				   String firstName = xpath.compile(expression).evaluate(document);
				   System.out.println("firstName................"+firstName);
				   
				   expression = "//*[@ssn="+ssn+"]/lastName";
				   String lastName = xpath.compile(expression).evaluate(document);
				   System.out.println("lastName................"+lastName);
				  
			   }
	 		
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
}
