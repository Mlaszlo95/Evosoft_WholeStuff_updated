package products;

import org.beryx.textio.TextIO;
import save_method.xml_operator;

import org.beryx.textio.TextTerminal;
/*import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import org.w3c.dom.*;*/

class spec{

}


class data_inputto_list {

	
	public static void cpu_input(TextIO textIO) {
		TextTerminal<?> terminal = textIO.getTextTerminal();
		if(xml_operator.open_xmlFile()) {
			xml_operator cpu = new xml_operator();
			
			cpu.set_IO(textIO);
			cpu.choosed_type("cpu");
			cpu.choosed_serial();
			
			if(cpu.check_isItSameValue()) {
				terminal.print("Felülirás\n");
				
				cpu.choosed_producer();
				cpu.choosed_socket();
				cpu.choosed_clock();
				cpu.choosed_cores();
				terminal.print("Biztos felül irja? ");
				if(1==cpu.chosed_save()) {
					cpu.save_Theinput(2);		//1-hozzá fûzés, 2-es felülirás
				}
			}else {
				terminal.print("Új adatfelvitele\n");
				
				cpu.choosed_producer();
				cpu.choosed_socket();
				cpu.choosed_clock();
				cpu.choosed_cores();
				terminal.print("Biztos menti irja? ");
				if(1==cpu.chosed_save()) {
					cpu.save_Theinput(1);
				}
			}
		}else {
			terminal.print(" Hiba! Nincs ilyen fájl!");
		}
	}
	
	public void gpu_input(TextIO textIO) {
		
	}
	
	
	void new_data_inputOption(TextIO textIO){
		TextTerminal<?> terminal = textIO.getTextTerminal();
		chosed_menu_options cmo=new chosed_menu_options();
		
		terminal.println("1 - CPU, 2 - GPU, 3 - Alaplap");
		cmo.navigate_intoMenu(textIO, 1, 3);
		switch(cmo.chosed_menu) {
			case 1:
				cpu_input(textIO);
				break;
			case 2:
				gpu_input(textIO);
				break;
			case 3:
				
				
			break;
		}
		
	}
}
