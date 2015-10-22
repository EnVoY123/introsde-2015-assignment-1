/**
* Made by Ruslan Rad, 2015, EIT SDE, Thanks to Pavel K.
**/

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PeopleProfile {
	Document doc;
	XPath xpath;

	// Loading the xml file //
	public void loadXML() throws ParserConfigurationException, SAXException,
			IOException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		doc = builder.parse("people.xml");

		getXPathObj();
	}

	// Creating XPath Object // 
	public XPath getXPathObj() {
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		return xpath;
	}

	// Printing weight of person and his firstname and lastname	
	public void getWeight(String firstname, String lastname)
			throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person[firstname='"
				+ firstname + "' and lastname='" + lastname
				+ "' ]/healthprofile/weight");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		System.out.println(firstname + " " + lastname + "'s weight: "
				+ node.getTextContent());
	}

	// Printing height of person and his firstname and lastname	
	public void getHeight(String firstname, String lastname)
			throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person[firstname='"
				+ firstname + "' and lastname='" + lastname
				+ "' ]/healthprofile/height");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		System.out.println(firstname + " " + lastname + " height: "
				+ node.getTextContent());
	}

	// Printing Healthprofile of Person's ID
		// String or int? I don't know. When int - it doesn't works
	public void getPersonByID(String personID) throws XPathExpressionException {	
		XPathExpression expr = xpath.compile("/people/person[@id='" + personID
				+ "']/healthprofile");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		if (node != null) {
			System.out.println("Person Healthprofile and ID " + personID
					+ ": " + node.getTextContent());
		} else {
			System.out.println("Nobody, man! Be careful");
		}
	}
	
	// Printing person by condition to his weight
	public void getPersonByCondition(String weight, String condition)
			throws XPathExpressionException {

		XPathExpression expr = xpath.compile("//person/healthprofile[weight"
				+ condition + "'" + weight + "']/..");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("All persons with weight " + condition + " "
				+ weight);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			System.out.println(n.getTextContent());
		}
	}

	// Printing all List of Persons
	public void listAllPeople() throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("Full Person List");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			System.out.println(n.getTextContent());
		}
	}


	public void start(String [] argument) throws ParserConfigurationException, SAXException,
			IOException, XPathExpressionException {
		
		loadXML();	
		
		int argCount = argument.length;
		if(argCount == 1){
			getPersonByID(argument[0]);
		}else if (argCount == 2){
			
			getPersonByCondition(argument[0], argument[1]); 
		}
		
				
	}
	
	public static void main(String[] argument) throws Exception {
		PeopleProfile hpr = new PeopleProfile();
		try {
			hpr.start(argument); 
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
