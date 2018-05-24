package products;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

//netbeans
public class customer_main{
	public static void login(TextIO textIO) {
		TextTerminal<?> terminal = textIO.getTextTerminal();
		login_system ls=new login_system();
		chosed_menu_options cmo = new chosed_menu_options();
		boolean justDoit,exit=false;
		do {	
			do {
				justDoit=false;
				String user = textIO.newStringInputReader().read("Felhasználó név:");
				String password = textIO.newStringInputReader()
						.withMinLength(4)
						.withInputMasking(true)
						.read("Jelszo:");
				if(!(ls.login_check(user, password))) {
					terminal.println("Hibas felhasználó vagy jelszó. Kérem irja be újra!");
					justDoit=true;
				}
			}while(justDoit);
			ls.login_menu_writeOut(textIO);
			exit=cmo.entering_intoTheMenu(textIO);
		}while(!exit);
	}
	
	public static void main(String args[]) {
		TextIO textIO = null;
		TextTerminal<?> terminal = null;
		try {
			textIO = TextIoFactory.getTextIO();
			terminal = textIO.getTextTerminal();
			
			login(textIO);
		}finally {
			if(terminal!=null) {
				terminal.getProperties().setPromptColor("7AFF33");
				terminal.getProperties().setPromptBackgroundColor("black");
				terminal.dispose();
			}
			if(textIO==null) {
				terminal.dispose();
				System.out.print("Hiba! :'(");
			}
		}
	}
}
