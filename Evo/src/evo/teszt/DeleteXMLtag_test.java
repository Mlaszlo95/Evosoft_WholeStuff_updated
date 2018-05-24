package evo.teszt;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import evo.classes.Products;

public class DeleteXMLtag_test {

	public static void deleteTagFromTheXML(String productName) throws ParserConfigurationException, SAXException, IOException, TransformerException{
		org.w3c.dom.Document doc = null;
		Products.setFileLocation("proba.xml");
		doc= Products.tryOpenTheFile();
		
		NodeList nodes = doc.getElementsByTagName("item");

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element goods = (Element)nodes.item(i);

	      Element name = (Element)goods.getElementsByTagName("name").item(0);
	      String dName = name.getTextContent();
	      System.out.println("Name: "+dName);
	      
	      if (dName.equals(productName)) {
	    	 System.out.println("Name: "+dName);
	    	 Node parent = goods.getParentNode();
	    	 parent.removeChild(goods);
	    	 parent.normalize();
	    	 Products.toString(doc);
			}
	    }
	}

	
	public static void main(String[] args) {
		try {
			deleteTagFromTheXML("Ryzen 5 1600");
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
