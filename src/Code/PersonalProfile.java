package Code;

/**
* 	PersonalProfile class handles the the progress and points
*   and relevant methods. 
* 
*
* @author  Vanish
* @version 1.0
* @since   2023-01-27 
*/

import java.util.ArrayList;

public class PersonalProfile {

	static int progress = 1;
    static int points = 0;
    
    static int[] levelThresholds = {0, 100, 150, 200, 250, 300};
    
   
    /**
	   * 
	   * function to handle updation to the user
	   * points and progress w.r.t thresholds provided.
	   * @param nill
	   * @return void
	   */
    public static void incrementForTaskCompletion() { 	
    	points += 15;   //increment points
       
        
        for (int i = 1; i < levelThresholds.length; i++) {
            if (progress >= levelThresholds[i]) {			//This code checks for the progress that if it has crossed
            	progress = i + 1;						//and threshold, then promote his level
            } else {
                break; // break the loop to stop searching if not such thing
            }
        }
    
    }
	
}
