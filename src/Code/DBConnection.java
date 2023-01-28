package Code;

/**
* 	DBConnection class makes the initial handshake with
*   the postegreSQL database server by sending the credentials.
* 
*
* @author  Vanish
* @version 1.0
* @since   2023-01-27 
*/

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
		static Connection c = null;
	    
		/**
		   * 
		   * static function to establish
		   * the database connection by providing: schema name & password
		   * and store connection info in a variable
		   * @param nill
		   * @return void
		   */
		public static void connect() {

	        try {
	            Class.forName("org.postgresql.Driver");
	            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProgressTracker",
	                            "postgres", "vanish");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println(e.getClass().getName()+": "+e.getMessage());
	            System.exit(0);
	        }
	        System.out.println("Opened database successfully");
	    }



	}
