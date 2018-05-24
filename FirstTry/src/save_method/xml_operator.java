package save_method;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.BufferedWriter;

import java.io.IOException;

import org.beryx.textio.TextIO;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


import javax.xml.parsers.*;



public class xml_operator {
	public static String xmlFileLocation="products.xml";
	private static File XmlFile=null;
	private static TextIO textIO;
	
	//CPU data details
	private String producer=null;
	private String socket=null;
	private int clock=0;
	private int cpu_cores=0;
	private int manufactorytech=0;
	//global
	private String choosed_type=null;
	private String serrial=null;
	private String serrialnumber=null;
	private int quantity=0;
	private int price=0;
	
	public void choosed_type(String choosed_type) {
		this.choosed_type=choosed_type;
	}
	public void set_IO(TextIO IO) {
		textIO=IO;
	}
	
	public void take_parts(String alltypein){
		String [] split=null;
		if(alltypein.indexOf(" ")>= 0) {
			split=alltypein.split(" ");
			serrial=split[0];
			serrialnumber=split[1];
		}else if(alltypein.indexOf("-")>= 0){
			split=alltypein.split("-");
			serrial=split[0];
			serrialnumber=split[1];
		}
	}
	
	public void choosed_serial() {
		String serial = textIO.newStringInputReader()
				.withMinLength(2)
				.withMaxLength(10)
				.read("Irja be sorozatot sorozatszámát");
		take_parts(serial);
	}
	public void choosed_producer() {
		producer = textIO.newStringInputReader()
				.withMinLength(0)
				.withMaxLength(6)
				.read("Gyártó:");
	}
	public void choosed_socket() {
		socket = textIO.newStringInputReader()
			.withMinLength(0)
			.withMaxLength(8)
			.read("Processzor foglala:");
	}
	public void choosed_clock() {
		clock = textIO.newIntInputReader()
			.withMinVal(0)
			.withMaxVal(4500)
			.read("Órajel (Mhz) :");
	}
	public void choosed_cores() {
		cpu_cores = textIO.newIntInputReader()
			.withMinVal(0)
			.withMaxVal(8)
			.read("Proccesor magok száma :");
	}
	public int chosed_save() {
		int save = textIO.newIntInputReader()
			.withMinVal(0)
			.withMaxVal(8)
			.read("1-igen, 2-nem\n");
		return save;
	}
	
	public boolean check_isItSameValue() {
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
					
					String local_serrial=eElement.getElementsByTagName("serrial").item(0).getTextContent();
					String local_serrialnumber=eElement.getElementsByTagName("serrialnumber").item(0).getTextContent();
					if(local_serrial.equals(serrial) && local_serrialnumber.equals(serrialnumber)){
						return true;
					}
				}
			}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
			
		return false;
	}
	
	public static boolean open_xmlFile() {
		XmlFile = new File(xmlFileLocation);
		if(XmlFile.exists()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void save_Theinput(int mod) {
		if(mod==1) {
			save_Append_input();
		}else {
			save_OverwriteXml_element();
		}
	}

	public void save_Append_input() {	
		DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuild = null;
		try {
			
			docBuild = docFact.newDocumentBuilder();
			Document doc = docBuild.parse(xmlFileLocation);
			//Document doc = docBuild.newDocument();
			Element root = doc.getDocumentElement();
			Element type =  (Element) root .getElementsByTagName(choosed_type).item(0);
			//Element root = doc.createElement("products");
			//doc.appendChild(root);
			/*Element type = doc.createElement(choosed_type);
			root.appendChild(type);*/
				
			Element item = doc.createElement("item");
			type.appendChild(item);
			
			Element out_producer = doc.createElement("producer");
			out_producer.setTextContent(producer);	
			item.appendChild(out_producer);
			
			Element out_serrial = doc.createElement("serrial");
			out_serrial.setTextContent(serrial);	
			item.appendChild(out_serrial);
			
			Element out_serrialnumber = doc.createElement("serrialnumber");
			out_serrialnumber.setTextContent(serrialnumber);	
			item.appendChild(out_serrialnumber);
			
			Element out_socket = doc.createElement("socket");
			out_socket.setTextContent(socket);	
			item.appendChild(out_socket);
			
			Element out_clock = doc.createElement("clock");
			//out_clock .appendChild(doc.createTextNode(Integer.valueOf(this.clock).toString()));
			out_clock.setTextContent(Integer.valueOf(clock).toString());
			item.appendChild(out_clock);
			
			Element out_cores = doc.createElement("cpu_cores");
			out_cores.setTextContent(Integer.valueOf(cpu_cores).toString());
			item.appendChild(out_cores);
	
			Element out_manufactorytech = doc.createElement("manufactorytech");
			out_manufactorytech .setTextContent(Integer.valueOf(manufactorytech).toString());
			item.appendChild(out_manufactorytech);
			
			//vissza irjuk a fájlba
			//itt formázuk szépre
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFileLocation));
			transformer.transform(source, result);

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  } catch (IOException ioe) {
			ioe.printStackTrace();
		  } catch (SAXException sae) {
			sae.printStackTrace();
		 }
	}
	
	void save_OverwriteXml_element() {
		DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuild = null;
		try {
			docBuild = docFact.newDocumentBuilder();
			Document doc = docBuild.parse(xmlFileLocation);

			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("item");
			
			for (int i = 0; i < nodes.getLength(); i++) {
				NodeList subChildNodes = nodes.item(i).getChildNodes();
				
				System.out.print(subChildNodes.item(6).getTextContent());
				if(subChildNodes.item(3).getTextContent().equals(serrial) && subChildNodes.item(5).getTextContent().equals(serrialnumber)) {
					if(producer!=null)subChildNodes.item(1).setTextContent(producer);
					if(socket!=null)subChildNodes.item(7).setTextContent(socket);
					if(clock!=0)subChildNodes.item(9).setTextContent(Integer.valueOf(clock).toString());
					if(cpu_cores!=0)subChildNodes.item(11).setTextContent(Integer.valueOf(cpu_cores).toString());
					if(manufactorytech!=0)subChildNodes.item(13).setTextContent(Integer.valueOf(manufactorytech).toString());
				}
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
			transformer.transform(source, result);

            String strTemp = writer.toString();

            FileWriter fileWriter = new FileWriter(xmlFileLocation);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(strTemp);
            bufferedWriter.flush();
            bufferedWriter.close();
			
		} catch(Exception ex) {
       
		}
	}
}
