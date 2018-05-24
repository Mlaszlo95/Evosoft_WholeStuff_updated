package products;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

public class chosed_menu_options {
	public int chosed_menu=0;
	private int adminValue;
	public void navigate_intoMenu(TextIO textIO, int min, int max) {
		do {
			chosed_menu = textIO.newIntInputReader()
					.withMinVal(min)
					.withMaxVal(max)
					.read("Menu:");
		}while(chosed_menu==0);
	}
	
	public static void menu_admin(TextIO textIO) {
		TextTerminal<?> terminal = textIO.getTextTerminal();
		terminal.println("1 - Új adat felvetele");
		terminal.println("2 - Listazas");
		terminal.println("3 - Beallitasok");
		terminal.println("4 - Keszitõk");
		terminal.println("5 - Kijelentkezes");
		terminal.println("6 - Kilépés");
	}
	
	public static void menu_user(TextIO textIO) {
		TextTerminal<?> terminal = textIO.getTextTerminal();
		terminal.println("1 - Listazas.");
		terminal.println("2 - Beallitasok");
		terminal.println("3 - Keszitõk");
		terminal.println("4 - Kijelentkezes");
		terminal.println("5 - Kilépés");
	}
	
	public void options_menu_point(TextIO textIO) {
		TextTerminal<?> terminal = textIO.getTextTerminal();
		chosed_menu=0;
		
		terminal.println("Mit szeretne csinalni?");
		terminal.println("1 - Betûszin beállitása");
		terminal.println("2 - Betûk hátérszine");
		terminal.println("3 - Hátér szin");
		terminal.println("Nyelv SOON");
		terminal.println("Felhasznalo/jelszo csere SOON");
		terminal.println("6 - Kilépés");
		boolean run=true;
		do {
			navigate_intoMenu(textIO,1,6);
			switch(chosed_menu) {
				case 1:
					String WordcolorType=textIO.newStringInputReader()
						.withDefaultValue("Add meg Hex color codot")
						.read("Betû szin:");
					terminal.getProperties().setPromptColor(WordcolorType);
					break;
				case 2:
					String BackGroundTextColorType=textIO.newStringInputReader()
						.withDefaultValue("Add meg Hex color codot")
						.read("Betûk háterének szinét:");
					terminal.getProperties().setPromptBackgroundColor(BackGroundTextColorType);
					break;
				case 3:
					String BackGroundColorType=textIO.newStringInputReader()
						.withDefaultValue("Add meg Hex color codot")
						.read("Környezet háterének színét:");
					terminal.getProperties().setInputBackgroundColor(BackGroundColorType);
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					chosed_menu=0;
					run=false;
			}
		}while(run);
	}
	
	public boolean entering_intoTheMenu(TextIO textIO) {
		chosed_menu=0;
		adminValue = login_system.max_MenuValueIfyouAreadmin();
		data_inputto_list dil= new data_inputto_list();
		data_output_list dol= new data_output_list();
		boolean exit=false, logout=false;
		do {
			if(adminValue==1){
				navigate_intoMenu(textIO,1,6);
				switch(chosed_menu) {
					case 1:dil.new_data_inputOption(textIO);break;
					case 2:dol.write_DownTheList(textIO);break;
					case 3:options_menu_point(textIO);break;
					case 5:logout=true;break;
					case 6:exit=true;break;
				}
				if(!logout && !exit) {
					menu_admin(textIO);
				}
			}else {
				navigate_intoMenu(textIO,1,5);
				switch(chosed_menu) {
					case 2:options_menu_point(textIO);break;
					case 4:logout=true;break;
					case 5:exit=true;break;
				}
				if(!logout && !exit) {
					menu_user(textIO);
				}
			}
		}while(!(exit || logout));
		chosed_menu=0;
		return exit;
	}
}
