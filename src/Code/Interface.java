package Code;

/**
* The Progress Tracker keeps track of your progress acheieved
* after completing tasks and shopping items.
*
* @author  Vanish
* @version 1.0
* @since   2023-01-27 
*/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;


public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField points;
	private JTextField level;
	private JTable quizTable;
	static DefaultTableModel TaskModel;
	static DefaultTableModel shopList;
	static DefaultTableModel history;
	private JTable bought;
	private JTable available;
	private JTextField emailLogin;
	private JTextField passwordLogin;
	private JTextField emailSignup;
	private JTextField nameSignup;
	private JTextField passwordSignup;
	private JTable table;
	static int login=0;
	
	/**
	   * Launch the appication.
	   * 
	   * @param optional command line arguments
	   * @return void
	   */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DBConnection.connect();
					DBOperations.readCompletedQuizes();
					DBOperations.readQuizes();
					DBOperations.readPoints();
					DBOperations.readItems();
					DBOperations.readBoughtItems();
					DBOperations.readUsers();
					
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	  /**
	   * Creates the frame.
	   * 
	   * @param null
	   * @return void
	   */
	public Interface() {
		
		String[] columnNames = {"Id","Task Name"};
		TaskModel = new DefaultTableModel();
		TaskModel.setColumnIdentifiers(columnNames);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 593);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(40,36,33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel navbar = new JPanel();
		navbar.setBorder(new LineBorder(new Color(51, 255, 0)));
		navbar.setBounds(10, 44, 126, 391);
		navbar.setBackground(new Color(40,36,33));
		contentPane.add(navbar);
		navbar.setLayout(null);
		
		JLabel lblProfileIcon = new JLabel("");
		lblProfileIcon.setIcon(new ImageIcon("icon2.PNG"));
		lblProfileIcon.setForeground(new Color(226, 177, 56));
		lblProfileIcon.setBounds(10, 11, 106, 125);
		navbar.add(lblProfileIcon);
		
		JButton tasks = new JButton("Tasks");
		
		tasks.setForeground(new Color(226,177,56));
		tasks.setBackground(new Color(40,36,33));
		tasks.setBounds(10, 194, 106, 23);
		navbar.add(tasks);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHelp.setForeground(new Color(226, 177, 56));
		btnHelp.setBackground(new Color(40, 36, 33));
		btnHelp.setBounds(10, 465, 106, 23);
		navbar.add(btnHelp);
		
		JButton shopping = new JButton("Shop");
		shopping.setForeground(new Color(226, 177, 56));
		shopping.setBackground(new Color(40, 36, 33));
		shopping.setBounds(10, 243, 106, 23);
		navbar.add(shopping);
		
		JLabel lblHi = new JLabel("");
		lblHi.setForeground(new Color(226, 177, 56));
		lblHi.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblHi.setBounds(10, 130, 150, 23);
		navbar.add(lblHi);
		
		JPanel taskmanager = new JPanel();
		taskmanager.setBounds(153, 46, 500, 497);
		taskmanager.setBackground(new Color(40,36,33));
		taskmanager.setBorder(new LineBorder(new Color(51, 255, 0)));
		contentPane.add(taskmanager);
		taskmanager.setLayout(null);
		
		JPanel quizes = new JPanel();
		quizes.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, null, null, null));
		quizes.setBounds(0, 0, 537, 499);
		quizes.setBackground(new Color(40,36,33));
		taskmanager.add(quizes);
		quizes.setLayout(null);
		
		JComboBox quizList = new JComboBox();
		JComboBox quizListDelete = new JComboBox();
		
		
		JButton addQuizbtn = new JButton("Add");
		addQuizbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int index=Quiz.tasks.size()+1;
				JFrame f=new JFrame();   
				String name=JOptionPane.showInputDialog(f,"Enter Task Name");      
				
				try {
					if(Quiz.addTask(name)) {
						JOptionPane.showMessageDialog(null, "\t Task Added!", "Info",
		                        JOptionPane.INFORMATION_MESSAGE);
//						refreshDeleteQuizList(quizListDelete);
//						refreshQuizList(quizList);
						
						quizListDelete.removeAllItems();
						for(int i=0;i<Quiz.tasks.size();i++)
							quizListDelete.addItem(Quiz.tasks.get(i).quizName);
						
						quizList.removeAllItems();
						for(int i=0;i<Quiz.tasks.size();i++)
							quizList.addItem(Quiz.tasks.get(i).quizName);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "\t Error!", "Info",
	                        JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			
			}
		});
		addQuizbtn.setBounds(96, 144, 331, 23);
		addQuizbtn.setForeground(new Color(226, 177, 56));
		addQuizbtn.setBackground(new Color(40, 36, 33));
		quizes.add(addQuizbtn);
		
		quizListDelete.setBounds(96, 227, 331, 22);
		quizes.add(quizListDelete);
		
		for(int i=0;i<Quiz.tasks.size();i++)
			quizListDelete.addItem(Quiz.tasks.get(i).quizName);
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					if(Quiz.deleteTask(""+quizListDelete.getSelectedItem())) {
						JOptionPane.showMessageDialog(null, "\t Task Deleted!", "Info",
		                        JOptionPane.INFORMATION_MESSAGE);
						
//						refreshDeleteQuizList(quizListDelete);
//						refreshQuizList(quizList);
						
						quizListDelete.removeAllItems();
						for(int i=0;i<Quiz.tasks.size();i++)
							quizListDelete.addItem(Quiz.tasks.get(i).quizName);
						
						quizList.removeAllItems();
						for(int i=0;i<Quiz.tasks.size();i++)
							quizList.addItem(Quiz.tasks.get(i).quizName);
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "\t Error!", "Info",
	                        JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			
			}
		});
		btnDelete.setForeground(new Color(226, 177, 56));
		btnDelete.setBackground(new Color(40, 36, 33));
		btnDelete.setBounds(96, 260, 331, 23);
		quizes.add(btnDelete);
		
		JLabel lblCompleteAQuiz = new JLabel("Complete a quiz:");
		lblCompleteAQuiz.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblCompleteAQuiz.setForeground(new Color(226, 177, 56));
		lblCompleteAQuiz.setBounds(182, -1, 150, 23);
		quizes.add(lblCompleteAQuiz);
		
		quizList.setBounds(96, 33, 331, 22);
		quizes.add(quizList);
		
		for(int i=0;i<Quiz.tasks.size();i++)
			quizList.addItem(Quiz.tasks.get(i).quizName);
		
		JButton completeQuiz = new JButton("Mark as complete");
		completeQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					if(Quiz.completeTask(""+quizList.getSelectedItem())) {
						JOptionPane.showMessageDialog(null, "\t Task Completed!", "Info",
		                        JOptionPane.INFORMATION_MESSAGE);
						
//						refreshTable(quizTable);
//						refreshDeleteQuizList(quizListDelete);
//						refreshQuizList(quizList);
						
						quizListDelete.removeAllItems();
						for(int i=0;i<Quiz.tasks.size();i++)
							quizListDelete.addItem(Quiz.tasks.get(i).quizName);
						
						quizList.removeAllItems();
						for(int i=0;i<Quiz.tasks.size();i++)
							quizList.addItem(Quiz.tasks.get(i).quizName);
						
						TaskModel = new DefaultTableModel();
						TaskModel.setColumnIdentifiers(columnNames);						
						TaskModel.addRow(new Object[]{"Id","Task Name"});
						for(int i=0;i<Quiz.completedTasks.size();i++)
				        	TaskModel.addRow(new Object[]{Quiz.completedTasks.get(i).quizId,Quiz.completedTasks.get(i).quizName});
						
						quizTable.setModel(TaskModel);
						
						level.setText(""+PersonalProfile.progress);
						points.setText(""+PersonalProfile.points);
						
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "\t Error!", "Info",
	                        JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		});
		completeQuiz.setForeground(new Color(226, 177, 56));
		completeQuiz.setBackground(new Color(40, 36, 33));
		completeQuiz.setBounds(96, 66, 331, 23);
		quizes.add(completeQuiz);
		
		JLabel lblCompletedQuizes = new JLabel("Completed quizes:");
		lblCompletedQuizes.setForeground(new Color(226, 177, 56));
		lblCompletedQuizes.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblCompletedQuizes.setBounds(182, 296, 150, 23);
		quizes.add(lblCompletedQuizes);
		
		quizTable = new JTable();
		quizTable.setBounds(96, 330, 331, 158);
		quizes.add(quizTable);
		
		
		TaskModel.addRow(new Object[]{"Id","Task Name"});
		for(int i=0;i<Quiz.completedTasks.size();i++)
        	TaskModel.addRow(new Object[]{Quiz.completedTasks.get(i).quizId,Quiz.completedTasks.get(i).quizName});
		
		quizTable.setModel(TaskModel);
		
		JLabel lblDeleteAQuiz = new JLabel("Delete a quiz:");
		lblDeleteAQuiz.setForeground(new Color(226, 177, 56));
		lblDeleteAQuiz.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblDeleteAQuiz.setBounds(196, 192, 150, 23);
		quizes.add(lblDeleteAQuiz);
		
		
		JLabel lblAddAQuiz = new JLabel("Add a quiz:");
		lblAddAQuiz.setForeground(new Color(226, 177, 56));
		lblAddAQuiz.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblAddAQuiz.setBounds(196, 110, 150, 23);
		quizes.add(lblAddAQuiz);
		
		JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		logout.setForeground(new Color(226,177,56));
		logout.setBounds(594, 10, 89, 23);
		logout.setBackground(new Color(40,36,33));
		contentPane.add(logout);
		
		JLabel lblNewLabel = new JLabel("Points");
		lblNewLabel.setForeground(new Color(226,177,56));
		lblNewLabel.setBounds(453, 10, 45, 23);
		contentPane.add(lblNewLabel);
		
		points = new JTextField();
		points.setEditable(false);
		points.setBounds(508, 11, 66, 20);
		contentPane.add(points);
		points.setColumns(10);
		
		level = new JTextField();
		level.setEditable(false);
		level.setColumns(10);
		level.setBounds(377, 13, 66, 20);
		contentPane.add(level);
		
		level.setText(""+PersonalProfile.progress);
		points.setText(""+PersonalProfile.points);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setForeground(new Color(226, 177, 56));
		lblLevel.setBounds(332, 14, 35, 23);
		contentPane.add(lblLevel);
		
		JLabel lblProgressTrackerApp = new JLabel("Progress Tracker App");
		lblProgressTrackerApp.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblProgressTrackerApp.setForeground(new Color(226, 177, 56));
		lblProgressTrackerApp.setBounds(10, 14, 218, 14);
		contentPane.add(lblProgressTrackerApp);
		
		JPanel shopmanager = new JPanel();
		shopmanager.setLayout(null);
		shopmanager.setBorder(new LineBorder(new Color(51, 255, 0)));
		shopmanager.setBackground(new Color(40, 36, 33));
		shopmanager.setBounds(146, 44, 537, 499);
		contentPane.add(shopmanager);
		
		JPanel shop = new JPanel();
		shop.setLayout(null);
		shop.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, null, null, null));
		shop.setBackground(new Color(40, 36, 33));
		shop.setBounds(0, 0, 537, 499);
		shopmanager.add(shop);
		
		JButton addItemsbtn = new JButton("Add");
		addItemsbtn.setForeground(new Color(226, 177, 56));
		addItemsbtn.setBackground(new Color(40, 36, 33));
		addItemsbtn.setBounds(405, 75, 108, 23);
		shop.add(addItemsbtn);
		
		JButton deleteItemsbtn = new JButton("Delete");
		
		
		deleteItemsbtn.setForeground(new Color(226, 177, 56));
		deleteItemsbtn.setBackground(new Color(40, 36, 33));
		deleteItemsbtn.setBounds(405, 109, 108, 23);
		shop.add(deleteItemsbtn);
		
		JLabel lblOperations = new JLabel("Operations");
		lblOperations.setForeground(new Color(226, 177, 56));
		lblOperations.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblOperations.setBounds(410, 35, 150, 23);
		shop.add(lblOperations);
		
		JLabel lblBoughtItemsList = new JLabel("Bought Items List");
		lblBoughtItemsList.setForeground(new Color(226, 177, 56));
		lblBoughtItemsList.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblBoughtItemsList.setBounds(138, 296, 170, 23);
		shop.add(lblBoughtItemsList);
		
		
		bought = new JTable();
		bought.setBounds(43, 330, 331, 158);
		shop.add(bought);
		
		String[] columns = {"Id","Item Name","Price","Qty"};
		history = new DefaultTableModel();
		history.setColumnIdentifiers(columns);
		history.addRow(new Object[]{"Id","Item Name","Price","Qty"});
		for(int i=0;i<ShoppingItem.history.size();i++)
			history.addRow(new Object[]{ShoppingItem.history.get(i).itemId,ShoppingItem.history.get(i).itemName,ShoppingItem.history.get(i).itemPrice,ShoppingItem.history.get(i).qty});
		
		bought.setModel(history);
		
		
		available = new JTable();
		available.setBounds(43, 69, 331, 158);
		shop.add(available);
		
		String[] columns2 = {"Id","Item Name","Price"};
		shopList = new DefaultTableModel();
		shopList.setColumnIdentifiers(columns);
		shopList.addRow(new Object[]{"Id","Item Name","Price"});
		for(int i=0;i<ShoppingItem.shop.size();i++)
			shopList.addRow(new Object[]{ShoppingItem.shop.get(i).itemId,ShoppingItem.shop.get(i).itemName,ShoppingItem.shop.get(i).itemPrice});
		
		available.setModel(shopList);
		
		JLabel lblAvailableItems = new JLabel("Available Items");
		lblAvailableItems.setForeground(new Color(226, 177, 56));
		lblAvailableItems.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblAvailableItems.setBounds(142, 35, 150, 23);
		shop.add(lblAvailableItems);
		
		JButton buyItemsbtn = new JButton("Buy");
		buyItemsbtn.setForeground(new Color(226, 177, 56));
		buyItemsbtn.setBackground(new Color(40, 36, 33));
		buyItemsbtn.setBounds(405, 204, 108, 23);
		shop.add(buyItemsbtn);
		
		JPanel register = new JPanel();
		register.setLayout(null);
		register.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, null, null, null));
		register.setBackground(new Color(40, 36, 33));
		register.setBounds(153, 44, 500, 391);
		contentPane.add(register);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(226, 177, 56));
		lblLogin.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblLogin.setBounds(207, 24, 150, 23);
		register.add(lblLogin);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setForeground(new Color(226, 177, 56));
		lblSignUp.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblSignUp.setBounds(207, 201, 150, 23);
		register.add(lblSignUp);
		
		emailLogin = new JTextField();
		emailLogin.setBounds(137, 75, 204, 20);
		register.add(emailLogin);
		emailLogin.setColumns(10);
		
		passwordLogin = new JTextField();
		passwordLogin.setColumns(10);
		passwordLogin.setBounds(137, 106, 204, 20);
		register.add(passwordLogin);
		
		taskmanager.setVisible(false);
		shopmanager.setVisible(false);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String ret=Users.login(emailLogin.getText(),passwordLogin.getText());
				if(!ret.equals("wrong") ) {
					JOptionPane.showMessageDialog(null, "\t Login Successfull!", "Info",
				            JOptionPane.INFORMATION_MESSAGE);
					
					login=1;
					register.setVisible(false);
					taskmanager.setVisible(true);
					lblHi.setText("Hi, "+ret);
					
					
				}else
					JOptionPane.showMessageDialog(null, "\t Login attempt failed!", "Info",
				            JOptionPane.INFORMATION_MESSAGE);
					
				
			}
		});
		
		
		tasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(login==1) {
				taskmanager.setVisible(true);
				shopmanager.setVisible(false);
				}
			}
		});
		
		shopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(login==1) {
				taskmanager.setVisible(false);
				shopmanager.setVisible(true);
				}
			}
		});
		
		loginBtn.setBounds(186, 137, 89, 23);
		register.add(loginBtn);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(226, 177, 56));
		lblEmail.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblEmail.setBounds(54, 73, 150, 23);
		register.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(226, 177, 56));
		lblPassword.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblPassword.setBounds(54, 106, 150, 23);
		register.add(lblPassword);
		
		emailSignup = new JTextField();
		emailSignup.setColumns(10);
		emailSignup.setBounds(137, 235, 204, 20);
		register.add(emailSignup);
		
		nameSignup = new JTextField();
		nameSignup.setColumns(10);
		nameSignup.setBounds(137, 266, 204, 20);
		register.add(nameSignup);
		
		passwordSignup = new JTextField();
		passwordSignup.setColumns(10);
		passwordSignup.setBounds(137, 298, 204, 20);
		register.add(passwordSignup);
		
		JButton signUpbtn = new JButton("Sign Up");
		signUpbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Users.users.add(new Users(""+Users.users.size(),nameSignup.getText(),emailSignup.getText(),passwordSignup.getText()));
				JOptionPane.showMessageDialog(null, "\t Registeration Successful! Try signing in now.", "Info",
			            JOptionPane.INFORMATION_MESSAGE);
				try {
					DBOperations.writeUsers();
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		signUpbtn.setBounds(186, 329, 89, 23);
		register.add(signUpbtn);
		
		table = new JTable();
		table.setBounds(24, 182, 455, 8);
		register.add(table);
		
		JLabel lblEmail_1 = new JLabel("Email");
		lblEmail_1.setForeground(new Color(226, 177, 56));
		lblEmail_1.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblEmail_1.setBounds(54, 233, 150, 23);
		register.add(lblEmail_1);
		
		JLabel lblEmail_1_1 = new JLabel("Name");
		lblEmail_1_1.setForeground(new Color(226, 177, 56));
		lblEmail_1_1.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblEmail_1_1.setBounds(54, 264, 150, 23);
		register.add(lblEmail_1_1);
		
		JLabel lblEmail_1_2 = new JLabel("Password");
		lblEmail_1_2.setForeground(new Color(226, 177, 56));
		lblEmail_1_2.setFont(new Font("Perpetua", Font.PLAIN, 20));
		lblEmail_1_2.setBounds(54, 297, 150, 23);
		register.add(lblEmail_1_2);
	
		
		deleteItemsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f=new JFrame();   
				String id=JOptionPane.showInputDialog(f,"Enter Item Id:");
				try {
					if(ShoppingItem.deleteItem(id)) {
						JOptionPane.showMessageDialog(null, "\t Item deleted!", "Info",
					            JOptionPane.INFORMATION_MESSAGE);
						
						shopList = new DefaultTableModel();
						shopList.setColumnIdentifiers(columns);
						shopList.addRow(new Object[]{"Id","Item Name","Price"});
						for(int i=0;i<ShoppingItem.shop.size();i++)
							shopList.addRow(new Object[]{ShoppingItem.shop.get(i).itemId,ShoppingItem.shop.get(i).itemName,ShoppingItem.shop.get(i).itemPrice});
						
						available.setModel(shopList);
						
					}else
						JOptionPane.showMessageDialog(null, "\t Error encountered!", "Info",
					            JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		addItemsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f=new JFrame();   
				String name=JOptionPane.showInputDialog(f,"Enter Item name:");
				String price=JOptionPane.showInputDialog(f,"Enter Item price:");
				
				try {
					if(ShoppingItem.addItem(name, price) ) {
						JOptionPane.showMessageDialog(null, "\t Item added!", "Info",
					            JOptionPane.INFORMATION_MESSAGE);
						
						shopList = new DefaultTableModel();
						shopList.setColumnIdentifiers(columns);
						shopList.addRow(new Object[]{"Id","Item Name","Price"});
						for(int i=0;i<ShoppingItem.shop.size();i++)
							shopList.addRow(new Object[]{ShoppingItem.shop.get(i).itemId,ShoppingItem.shop.get(i).itemName,ShoppingItem.shop.get(i).itemPrice});
						
						available.setModel(shopList);
						
					}else
						JOptionPane.showMessageDialog(null, "\t Error encountered!", "Info",
					            JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		buyItemsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame f=new JFrame();   
				String id=JOptionPane.showInputDialog(f,"Enter Item id:");
				String qty=JOptionPane.showInputDialog(f,"Enter Item quantity:");
				
				try {
					if(ShoppingItem.buyItem(id, qty) ) {
						
						
						history = new DefaultTableModel();
						history.setColumnIdentifiers(columns);
						history.addRow(new Object[]{"Id","Item Name","Price"});
						for(int i=0;i<ShoppingItem.history.size();i++)
							history.addRow(new Object[]{ShoppingItem.history.get(i).itemId,ShoppingItem.history.get(i).itemName,ShoppingItem.history.get(i).itemPrice});
						
						bought.setModel(history);
						points.setText(""+PersonalProfile.points);
						
						
					}else {}
						
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	
		
		
	}
	
	
	public static void refreshQuizList(JComboBox quizList) {
		quizList = new JComboBox();
		for(int i=0;i<Quiz.tasks.size();i++)
			quizList.addItem(Quiz.tasks.get(i).quizName);
	}
	
	
	
	public static void refreshDeleteQuizList(JComboBox quizListDelete) {
		quizListDelete = new JComboBox();
		for(int i=0;i<Quiz.tasks.size();i++)
			quizListDelete.addItem(Quiz.tasks.get(i).quizName);
	}
	
	public static void refreshTable(JTable quizTable) {
		String[] columnNames = {"Id","Task Name"};
		TaskModel = new DefaultTableModel();
		TaskModel.setColumnIdentifiers(columnNames);
		
		
		TaskModel.addRow(new Object[]{"Id","Task Name"});
		for(int i=0;i<Quiz.completedTasks.size();i++)
        	TaskModel.addRow(new Object[]{Quiz.completedTasks.get(i).quizId,Quiz.completedTasks.get(i).quizName});
	}
}
