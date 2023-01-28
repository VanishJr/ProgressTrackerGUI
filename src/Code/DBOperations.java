package Code;

/**
* 	DBOperations class handles the database operations such as read and write
*   from the postegreSQL schemas. 
* 
*
* @author  Vanish
* @version 1.0
* @since   2023-01-27
*  
*/

import java.sql.*;

public class DBOperations {

	
	//--------------------------- Quiz Operations----------------------------------------
	
	// all quiz related queries
	
	static final String readQuizQuery = "SELECT * FROM quizes;";
	static final String readPointsQuery = "SELECT * FROM points;";
	static final String readCompletedQuizQuery = "SELECT * FROM completedquizes;";
	static final String deleteQuizRecordQuery = "DELETE FROM quizes;";
	static final String deleteCompletedQuizRecordQuery = "DELETE FROM completedquizes;";
	
	
	 /**
	   * Reads the progress & points of user.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void readPoints() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
        ResultSet resultSet = statement.executeQuery(readPointsQuery);
		
        while(resultSet.next()) {
        	
        	PersonalProfile.points= Integer.parseInt(resultSet.getString(1));
        	PersonalProfile.progress=Integer.parseInt(resultSet.getString(2));
        	
        }       
        
	}
	
	
	/**
	   * Reads the present unfinished quizes.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void readQuizes() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
        ResultSet resultSet = statement.executeQuery(readQuizQuery);
		
        while(resultSet.next()) {
        	
        	Quiz.tasks.add(new Quiz(resultSet.getString(1),resultSet.getString(2)));
        	
        }       
        
	}
	
	/**
	   * Reads the present finished quizes.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void readCompletedQuizes() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
        ResultSet resultSet = statement.executeQuery(readCompletedQuizQuery);
		
        while(resultSet.next()) {
        	
        	Quiz.completedTasks.add(new Quiz(resultSet.getString(1),resultSet.getString(2)));
        	
        }
        
        
	}
	
	/**
	   * Deletes the table records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void deleteQuizesData() throws SQLException {
		Statement statement = DBConnection.c.createStatement();
        statement.execute(deleteQuizRecordQuery);
	}
		
	/**
	   * Deletes the table records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void deleteCompletedQuizesData() throws SQLException {
		Statement statement = DBConnection.c.createStatement();
        statement.execute(deleteCompletedQuizRecordQuery);
	}
	
	/**
	   * writes the quiz records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
   public static  void writeQuizes() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
               
        for(int j=0;j<Quiz.tasks.size();j++) {
        	statement.execute("INSERT INTO quizes (id, name) VALUES ('"+j+"', '"+Quiz.tasks.get(j).quizName+"');");
        }
        
        statement.execute("DELETE FROM points;");
        statement.execute("INSERT INTO points (points, level) VALUES ('"+ (""+PersonalProfile.points) +"', '"+(""+PersonalProfile.progress)+"');");
              
        
	}
   
   /**
	   * writes the completed quiz records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
   public static void writeCompletedQuizes() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
              
       for(int j=0;j<Quiz.completedTasks.size();j++) {
       	statement.execute("INSERT INTO completedquizes (id, name) VALUES ('"+j+"', '"+Quiz.completedTasks.get(j).quizName+"');");
       }
             
       
	}
   
   
   //------------------------------------- End -----------------------------------------------------
   
   
 //------------------------------------- Shopping Operations -----------------------------------------------------
   
    static final String readItemQuery = "SELECT * FROM shop;";
	static final String readShoppingHistoryQuery = "SELECT * FROM shoppingHistory;";
	static final String deleteItemRecordQuery = "DELETE FROM shop;";
	static final String deleteShoppingHistoryQuery = "DELETE FROM shoppingHistory;";
   
   
	/**
	   * reads shopping items records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void readItems() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
        ResultSet resultSet = statement.executeQuery(readItemQuery);
		
        while(resultSet.next()) {
        	
        	ShoppingItem.shop.add(new ShoppingItem(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
        	
        }       
        
	}
	
	/**
	   * reads buying history.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void readBoughtItems() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
        ResultSet resultSet = statement.executeQuery(readShoppingHistoryQuery);
		
        while(resultSet.next()) {
        	
        	ShoppingItem.history.add(new ShoppingItem(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        }       
        
	}
	
	/**
	   * deletes the items data.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void deleteItemsData() throws SQLException {
		Statement statement = DBConnection.c.createStatement();
        statement.execute(deleteItemRecordQuery);
	}
	
	/**
	   * writes shopping items records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void writeItems() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
               
        for(int j=0;j<ShoppingItem.shop.size();j++) {
        	statement.execute("INSERT INTO shop (id, name,price) VALUES ('"+j+"', '"+ShoppingItem.shop.get(j).itemName+"','"+ShoppingItem.shop.get(j).itemPrice+"');");
            }
        
               
        
	}
	
	/**
	   * deletes buying history records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void deleteBoughtItemsData() throws SQLException {
		Statement statement = DBConnection.c.createStatement();
        statement.execute(deleteShoppingHistoryQuery);
	}
	
	/**
	   * writes the bought items records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void writeBoughtItems() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
               
        for(int j=0;j<ShoppingItem.history.size();j++) {
        	statement.execute("INSERT INTO shoppingHistory (id, name,price,qty) VALUES ('"+j+"', '"+ShoppingItem.history.get(j).itemName+"','"+ShoppingItem.history.get(j).itemPrice+"','"+ShoppingItem.history.get(j).qty+"');");
        }
        
        statement.execute("DELETE FROM points;");
        statement.execute("INSERT INTO points (points, level) VALUES ('"+ (""+PersonalProfile.points) +"', '"+(""+PersonalProfile.progress)+"');");
              
        
	}
	
	//---------------------------------------------------------------------------------------------------------------

	
	static final String readCustomer = "SELECT * FROM customer;";
	static final String deleteuserRecordQuery = "DELETE FROM customer;";
	
	
	/**
	   * reads registered user  records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void readUsers() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
		
		ResultSet resultSet = statement.executeQuery(readCustomer);
		
        while(resultSet.next()) {
        	
        	Users.users.add(new Users(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)));
        	
        }       
        
	}
	
	/**
	   * writes the user records.
	   * 
	   * @param nill
	   * @return void
	   * @exception SQLException On database operation error.
	   */
	public static  void writeUsers() throws SQLException {
		
		Statement statement = DBConnection.c.createStatement();
		
		statement.execute(deleteuserRecordQuery);
		            
        for(int j=0;j<Users.users.size();j++) {
        	statement.execute("INSERT INTO customer (id, name,email,password) VALUES ('"+j+"', '"+Users.users.get(j).name+"','"+Users.users.get(j).email+"','"+Users.users.get(j).password+"');");
        }
        
        
              
        
	}
}
