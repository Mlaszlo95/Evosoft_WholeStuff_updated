package products;

import org.beryx.textio.TextIO;

public class login_system {
	private static String Admin_username="admin";
	private static String Admin_password="admin";
	private static String User_username="user";
	private static String User_password="user";
	private static boolean you_are_admin=false;
	
	public static int max_MenuValueIfyouAreadmin() {
		if(you_are_admin) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public boolean login_check(String username, String password){
		you_are_admin=false;
		if((User_username.equals(username) && User_password.equals(password))||(Admin_username.equals(username) && Admin_password.equals(password))){
			if(Admin_username.equals(username)) {
				you_are_admin=true;
			}
			return true;
		}else{
			return false;
		}
	}
	
	public void login_menu_writeOut(TextIO textIO){
		if(you_are_admin) {
			chosed_menu_options.menu_admin(textIO);
		}else {
			chosed_menu_options.menu_user(textIO);
		}
	}
}
