package evo.classes;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public abstract class Products {
	private String name; 		//term�k neve
	private String producer;	//gy�rt�
	private int quantity;		//term�k mennyis�ge
	private int price;			//�r
	
	private static String fileLocation = "products.xml";
	
	protected String type;
	//protected static int tax;		//ad�, mivel term�k fajt�k�nt lehet v�ltoz� ezten m�g dolgozunk. Nem tudom azt nem besz�lt�k, hogy szeretn�nk felvenni �lelmiszert vagy gy�gyszert egyenl�re, azoknak m�s az ad� kulcsuk
	//de akk�r static t�mb k�nt is t�rolhatjuk itt, az ad� fajt�kat, �s a k�vetkez� gyerekn�l be�llitjuk/kiv�lasztjuk azt a ad� nemet ami oda val�
	
	public Products(String name,String producer,int quantity, int price) {
		this.name = name;
		this.producer = producer;	
		this.quantity = quantity;	
		this.price = price;
		//this.tax = ;
	}
	
	public Products() {
		
	}
	
	public String getType() {
		return type;
	}
	
	public static String getFileLocation() {
		return fileLocation;
	}
	
	public static void setFileLocation(String fileloc) {
		fileLocation = fileloc;
	}
	
	public void readTheFileAndWriteDown(Element eElement) {
		if(eElement!=null) {
			System.out.println("\nTippus: " +type);
			System.out.print("Termek neve: " +eElement.getElementsByTagName("name").item(0).getTextContent() +"\n");
			System.out.print("Gy�rt�: " +eElement.getElementsByTagName("producer").item(0).getTextContent() +"\n");
			System.out.print("Darabsz�m: " +eElement.getElementsByTagName("quantity").item(0).getTextContent() +"\n" );
			System.out.print("�r: " +eElement.getElementsByTagName("price").item(0).getTextContent() +"\n" );
		}else {
			System.out.print("false");
		}
	}
	
	//megpr�b�lja megnyitni a f�jlt
	public static Document tryOpenTheFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		org.w3c.dom.Document doc = null;		//valami�rt nem tudja el�rni az ut vonalat ha nincs benne a "org.w3c.dom.Document". Feljebb mutatja, hogy nem haszn�lom az importot, passz nem tuduom mi�rt.
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(getFileLocation());
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	//vissza f�zi �rt�keket a f�jlba
	public static void toString(Document newDoc) throws TransformerException {
		//XML f�jl form�z�si stilus
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		//vissza ir�s az xmlbe
		DOMSource domSource = new DOMSource(newDoc);
	    StringWriter sw = new StringWriter();
	    StreamResult sr = new StreamResult(sw);
	    transformer.transform(domSource, sr);
	   // System.out.println(sw.toString());		kiirja az elemeket a hozz� f�z�s t�rl�s ut�n, benne hagyom h�tha m�g j� lesz
	}
}
