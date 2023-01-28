package Code;

/**
* 	ShoppingItem class handles the attributes and methods of items
*   in shopping list. 
* 
*
* @author  Vanish
* @version 1.0
* @since   2023-01-27 
*/

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ShoppingItem {

	String itemId,itemName,itemPrice,qty;

	public ShoppingItem(){}
	
	public ShoppingItem(String itemId, String itemName, String itemPrice) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	
	public ShoppingItem(String itemId, String itemName, String itemPrice,String qty) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.qty=qty;
	}
	
	static ArrayList<ShoppingItem> shop= new ArrayList<>();
	static ArrayList<ShoppingItem> history= new ArrayList<>();
	
	/**
	   * 
	   * function to delete shopping item from record.
	   * @param id as String
	   * @return boolean
	   * @exception SQLException On database operation error.
	   */
	public static boolean deleteItem(String id) throws SQLException {
		
		for(int i=0;i<ShoppingItem.shop.size();i++) {
    		if(i == Integer.parseInt(id)) {
    			
    			ShoppingItem.shop.remove(i);		//remove it from unCompleted tasks
                DBOperations.deleteItemsData();         
                DBOperations.writeItems();
                return true;
    		}
    	}
    	
    	return false;
		
	}
	
	/**
	   * 
	   * function to handle buying operation by verifying item,
	   * checking for available credit, and updating records.
	   * @param Itemid as String
	   * @param quantity as String
	   * @return boolean
	   * @exception SQLException On database operation error.
	   */
	public static boolean buyItem(String id,String qty) throws SQLException {
		
		for(int i=0;i<ShoppingItem.shop.size();i++) {
    		if(i == Integer.parseInt(id)) {
    			
    			if(PersonalProfile.points >= Integer.parseInt(ShoppingItem.shop.get(i).itemPrice)) {
    			
    			ShoppingItem.history.add(new ShoppingItem(id,ShoppingItem.shop.get(i).itemName,qty));
    			DBOperations.deleteBoughtItemsData();         
                DBOperations.writeBoughtItems();
                PersonalProfile.points=PersonalProfile.points-Integer.parseInt(ShoppingItem.shop.get(i).itemPrice);
                JOptionPane.showMessageDialog(null, "\t Done!", "Info",
			            JOptionPane.INFORMATION_MESSAGE);
                return true;
    			
    			}
    			JOptionPane.showMessageDialog(null, "\t Low on Points!", "Info",
			            JOptionPane.INFORMATION_MESSAGE);
    			break;
    		}
    	}
    	
    	return false;
		
	}
	
	/**
	   * 
	   * function to handle item addition into records
	   * @param name as String
	   * @param price as String
	   * @return boolean
	   * @exception SQLException On database operation error.
	   */
	public static boolean addItem(String name,String price) throws SQLException {
		
		
			int i=ShoppingItem.shop.size();
    		try {
    			ShoppingItem.shop.add(new ShoppingItem(""+i,name,price));
    			DBOperations.deleteItemsData();        
                DBOperations.writeItems();
                return true;
    		}catch(Exception e) {
    				System.out.println(e);
    		}
			
    		return false;
		
	}
}
