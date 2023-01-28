package Code;

/**
* 	Quiz class handles the attributes and methods of the 
*   main quizes. 
* 
*
* @author  Vanish
* @version 1.0
* @since   2023-01-27 
*/

import java.sql.SQLException;
import java.util.ArrayList;

public class Quiz {

	String quizId,quizName;
	
	static ArrayList<Quiz> tasks=new ArrayList<>();
	static ArrayList<Quiz> completedTasks = new ArrayList<>();

	/**
	   * parameterized constructor.
	   * 
	   * @param quizId as String
	   * @param quizName as String
	   * @return object
	   */
	public Quiz(String quizId, String quizName) {
		super();
		this.quizId = quizId;
		this.quizName = quizName;
	}

	
	/**
	   * function to handle task completion 
	   * by looking for records, updating the attributes
	   * 
	   * @param quizName as String
	   * @return boolean
	   * @exception SQLException On database operation error.
	   */
	public static boolean completeTask(String taskName) throws SQLException{
    	
    	for(int i=0;i<Quiz.tasks.size();i++) {
    		if(Quiz.tasks.get(i).quizName.equalsIgnoreCase(taskName)) {
    			PersonalProfile.incrementForTaskCompletion();
    			completedTasks.add(Quiz.tasks.get(i));
    			tasks.remove(i);		//remove it from unCompleted tasks               
                
    			DBOperations.deleteQuizesData();
                DBOperations.deleteCompletedQuizesData();
                DBOperations.writeCompletedQuizes();                
                DBOperations.writeQuizes();
                
                return true;
    		}
    	}
    	
    	return false;
    }
	
	
	/**
	   * 
	   * function to handle task deletion 
	   * by looking for records then updating the database
	   * @param quizName as String
	   * @return boolean
	   * @exception SQLException On database operation error.
	   */
	public static  boolean deleteTask(String taskName) throws SQLException{
    	
    	for(int i=0;i<Quiz.tasks.size();i++) {
    		if(Quiz.tasks.get(i).quizName.equalsIgnoreCase(taskName)) {
    			
    			tasks.remove(i);		//remove it from unCompleted tasks
                DBOperations.deleteQuizesData();         
                DBOperations.writeQuizes();
                return true;
    		}
    	}
    	
    	return false;
    }
	
	/**
	   * 
	   * function to handle task addition 
	   * by inserting records then updating the database
	   * @return boolean
	   * @exception SQLException On database operation error.
	   */
	public static  boolean addTask(String taskName) throws SQLException{
    	
    	int index=Quiz.tasks.size();
    	Quiz.tasks.add(new Quiz(""+index+1,taskName));
    	DBOperations.deleteQuizesData();         
        DBOperations.writeQuizes();    	
    	return true;
    }
	
}
