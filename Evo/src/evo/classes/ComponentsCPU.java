package evo.classes;

import org.w3c.dom.Element;

public class ComponentsCPU extends ComponentsPC{
	private int clock;
	private String socket;       
	private int cpu_cores;
	private int manufactorytech;
	

	public ComponentsCPU(String name, String producer, int quantity, int price,int clock, String socket,int cpu_cores, int manufactorytech) {
		super(name, producer, quantity, price);
		this.clock = clock;
		this.socket = socket;
		this.cpu_cores = cpu_cores;
		this.manufactorytech = manufactorytech;
		type="cpu";
	}
	
	public ComponentsCPU() {
		type="cpu";
	}

	@Override
	public void readTheFileAndWriteDown(Element eElement) {
		if(eElement.getAttribute("type").equals(type)) {
			super.readTheFileAndWriteDown(eElement);
			System.out.println("Core sebess�g: " +Integer.parseInt(eElement.getElementsByTagName("clock").item(0).getTextContent()));
			System.out.println("Foglalat: " +eElement.getElementsByTagName("socket").item(0).getTextContent());
			System.out.println("CPU magok sz�m: " +Integer.parseInt(eElement.getElementsByTagName("cpu_cores").item(0).getTextContent()));
			System.out.println("Gy�rt�si teknia (Nanomilimeter)" + Integer.parseInt(eElement.getElementsByTagName("manufactorytech").item(0).getTextContent()));
		}
	}
}
