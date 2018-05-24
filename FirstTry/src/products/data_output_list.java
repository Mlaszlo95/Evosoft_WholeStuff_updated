package products;

import save_method.xml_operator;
import java.io.File;

import org.beryx.textio.TextIO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.beryx.textio.TextTerminal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class data_output_list {
	public void write_DownTheList(TextIO textIO) {
		TextTerminal<?> terminal = textIO.getTextTerminal();
		File XmlFile=new File(xml_operator.xmlFileLocation);
		
		if(XmlFile.exists()) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(XmlFile);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");

				for (int i = 0; i < nList.getLength(); i++) {

					Node nNode = nList.item(i);	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						terminal.print("----------------------------------------------------------------------------------------\n");
						terminal.println("Gyarto: " + eElement.getElementsByTagName("producer").item(0).getTextContent());
						terminal.println("Termek: " + eElement.getElementsByTagName("serrial").item(0).getTextContent() + " " + eElement.getElementsByTagName("serrialnumber").item(0).getTextContent());
						terminal.println("Foglalat: " + eElement.getElementsByTagName("socket").item(0).getTextContent());
						terminal.println("Órajel: " + eElement.getElementsByTagName("clock").item(0).getTextContent());
						terminal.println("Magok szam: " + eElement.getElementsByTagName("cpu_cores").item(0).getTextContent());
						terminal.println("Gyartasi teknologia(nm): " + eElement.getElementsByTagName("manufactorytech").item(0).getTextContent());
						terminal.println("Raktaron levo mennyiség: " + eElement.getElementsByTagName("quantity").item(0).getTextContent());
						terminal.println("Ár: " + eElement.getElementsByTagName("price").item(0).getTextContent());
						terminal.print("----------------------------------------------------------------------------------------\n");
					}
				}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
			terminal.print("\n");
		}else {
			terminal.println("A kimeneti fajl nem letezik!");
		}
	}
}
