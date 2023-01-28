package Code;

/**
* 	Users class handles the attributes and method 
*   of the users handling this app.
* 
*
* @author  Vanish
* @version 1.0
* @since   2023-01-27 
*/

import java.util.ArrayList;

public class Users {

	String id,name,email,password;

	public Users(String id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	static ArrayList<Users> users=new ArrayList<>();
	
	/**
	   *
	   * function to handle login operation by
	   * comparing email & password with records
	   * @param email as String
	   * @param password as String
	   * @return String
	   */
	public static String login(String email,String password) {
		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).email.equalsIgnoreCase(email) && users.get(i).password.equalsIgnoreCase(password)) {
				return users.get(i).name;
			}
		}
		
		return "wrong";
		
	}
	
}
