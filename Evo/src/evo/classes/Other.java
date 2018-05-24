package evo.classes;

import org.w3c.dom.Element;

public class Other extends Products{

	//ha otheren bel�l szeretn�nk m�g egy gyermeket gy�gyszernek, vagy egy�bb k�l�nleges dolognak
	private String[] OtherTypes = {"cpu","gpu"};	//kiv�tel, hogy minden tippus irjon ki csak azokat nem
	
	public Other(String name,String producer,int quantity, int price) {
		super(name,producer,quantity,price);
		type="other";
	}
	
	public Other() {
		type="other";
	}
	
	@Override
	public void readTheFileAndWriteDown(Element eElement) {
		if( (!(eElement.getAttribute("type").equals(OtherTypes[0])) && (!(eElement.getAttribute("type").equals(OtherTypes[1])) ))) {
			super.readTheFileAndWriteDown(eElement);
		}
	}
}
