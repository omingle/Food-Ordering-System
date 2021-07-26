package users;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class user implements ChangeListener, Runnable{
	Scanner scan = new Scanner(System.in);
	
		
	//////////////////
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
	static final String USER = "your_mysql_username";
	static final String PASS = "your_mysql_password";

	public boolean login(String uName, String pWord) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from users where username = '" + uName + "' and upassword = '" + pWord + "';";
			
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		return false;
	}
	
	public boolean register(String name, long mobNo, String address, String city, String username, String password) throws Exception {
		
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql1 = "Select * from users where username = '" + username + "';";
			ResultSet rs1 = st.executeQuery(sql1);
			
			if(rs1.next()) {
				return false;
			}
			
			String sql2 = "Select count(*) from users;";
			ResultSet rs2 = st.executeQuery(sql2);
			rs2.next();
			
			String sql3 = "Insert into users values('" + username + "', '" + name + "', " + mobNo + ", '" + address + "', '" + city + "', '" + password + "', " + (rs2.getInt(1)+1) + ");";
			
			int n = st.executeUpdate(sql3);
			
			if(n > 0) {
					return true;
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		return false;
	}
	
	public String getNameofUser(String custUsername) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select uname from users where username = '" + custUsername + "';";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("uname");
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		
		return "";
	}
	
	public String getUserCity(String custUsername) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select ucity from users where username = '" + custUsername + "';";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("ucity");
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		
		return "";
	}
	
	
	public void displayCityRest(String username, ButtonGroup userRestBG, JPanel p1) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from restaurants where rest_city = '" + getUserCity(username) + "';";
			
			ResultSet rs = st.executeQuery(sql);
			
			int i = 1;
			
			while(rs.next()) {
				JRadioButton restNameRB = new JRadioButton(rs.getString("rest_name") + " - " + rs.getString("rest_address"), false);
				restNameRB.setActionCommand("" + rs.getInt("rest_id"));
				Font newRadioButtonFont = new Font(restNameRB.getFont().getName(),restNameRB.getFont().getStyle(), 18);  
				restNameRB.setFont(newRadioButtonFont);  
				
				userRestBG.add(restNameRB);
				p1.add(restNameRB);
				
				i++;
			}
			
			if(i==1) {
				JLabel restNotAvblLbl = new JLabel("Sorry... Our Service is not Available in " + getUserCity(username), JLabel.CENTER);
				restNotAvblLbl.setFont(new Font("", Font.BOLD, 35));
				restNotAvblLbl.setForeground(Color.RED);
				p1.add(restNotAvblLbl);
			}
			
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}	
	}

	public static String readString()
	{
	    Scanner scanner = new Scanner(System.in);
	    return scanner.nextLine();
	}
	
	int userCartID;
	
	public void showItems2User(int restId, JTabbedPane itemsUserTP, int CartID) throws Exception {
		Connection con = null;
		Statement st1 = null;
		Statement st2 = null;
		
		userCartID = CartID;
		
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st1 = con.createStatement();
			
			String sql1 = "Select * from categories where rest_id = " + restId + ";";
			
			ResultSet rs1 = st1.executeQuery(sql1);
			
			while(rs1.next()) {
				
				st2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				String sql2 = "Select * from items where cat_id = " + rs1.getInt("cat_id") + ";";
				ResultSet rs2 = st2.executeQuery(sql2);
				
				JPanel itP = new JPanel(new GridLayout(12, 3, 10, 10));
				
				int rowNum = 1;
				
				JLabel itemNameLbl = new JLabel("Item Name", JLabel.CENTER);
				JLabel itemPriceLbl = new JLabel("Item Price", JLabel.CENTER);
				JLabel itemQtyLbl = new JLabel("Item Qty", JLabel.CENTER);
				
				itemNameLbl.setFont(new Font("", Font.BOLD, 20));
				itemPriceLbl.setFont(new Font("", Font.BOLD, 20));
				itemQtyLbl.setFont(new Font("", Font.BOLD, 20));
				
		        itP.add(itemNameLbl);
		        itP.add(itemPriceLbl);
		        itP.add(itemQtyLbl);
		        
		        JScrollPane scroll = new JScrollPane(itP);
		        
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
								
				scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				
				while(rs2.next()) {
					SpinnerModel userQtySM = new SpinnerNumberModel(0, 0, 20, 1);
			        JSpinner userQtySpinner = new JSpinner(userQtySM);
			        userQtySpinner.setName(rs2.getInt("item_id") + "");
			        userQtySpinner.addChangeListener(this);
			        JLabel itemName = new JLabel(rs2.getString("item_name") + "");
			        itemName.setFont(new Font("", Font.BOLD, 16));
			        
			        JLabel itemPrice = new JLabel(rs2.getFloat("item_price") + "", JLabel.CENTER);
			        itemPrice.setFont(new Font("", Font.BOLD, 16));

			        itP.add(itemName);
					itP.add(itemPrice);
					itP.add(userQtySpinner);
					
					rowNum++;
				}
				
				while(rowNum<12) {
					itP.add(new JLabel(""));
					itP.add(new JLabel(""));
					itP.add(new JLabel(""));
					rowNum ++;
				}
		
				itemsUserTP.addTab(rs1.getString("cat_name"), scroll);
				
			}
		
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st1.close();
			st2.close();
			con.close();	
		}	
		
	}
	
	
		
	public void addToCart(int cartId, int userItemID, int qty) throws Exception {
		
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql1 = "Select * from cart where cart_id = " + cartId + " and item_id = " + userItemID + ";";
			ResultSet rs1 = st.executeQuery(sql1);
			
			if(qty == 0) {
				String sql2 = "Delete from cart where cart_id = " + cartId + " and item_id = " + userItemID + ";";
				
				int n = st.executeUpdate(sql2);
			}
			else if(rs1.next()) {
				String sql2 = "Update cart set item_qty = " + qty + " where cart_id = " + cartId + " and item_id = " + userItemID + ";";
				
				int n = st.executeUpdate(sql2);
			}
			else {
				String sql3 = "Insert into cart values (" + cartId + ", " + userItemID + ", " + qty + ");";
				
				int n = st.executeUpdate(sql3);
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		
	}

	public void stateChanged(ChangeEvent e) {
		
		int userItemID = Integer.parseInt(((JSpinner) e.getSource()).getName());
		int qty = (int) ((JSpinner) e.getSource()).getValue();
		
		try {
			if(checkPrevOrder(userCartID) ) {
				JOptionPane.showMessageDialog(new JLabel(), "Cannot be Added to Cart");
			}
			else {
				addToCart(userCartID, userItemID, qty);
			}
		} catch (Exception e1) {
			System.out.println("Exception : " + e1.getMessage());
		}
	}

	private Socket socket = null;
	private BufferedReader console = null;
	private BufferedReader reader =  null;
	private PrintWriter writer = null;
	JTextArea area;
	
	public void chat2rm(String uname, JTextArea user2rmChatTA, JButton chatUser2RMBtn, JTextField user2rmMsgTF)throws Exception {
		
		chatUser2RMBtn.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				String you = user2rmMsgTF.getText();
				writer.println (uname + " : " + you);
				user2rmChatTA.append ("\nYou : " + you);
				user2rmMsgTF.setText("");
			}
		});
		
		area = user2rmChatTA;
		
		System.out.println("Establishing connection. Please wait ...");
		socket = new Socket("localhost", 3355);
		System.out.println("Connected: " + socket);
		start();
		
		Thread thread = new Thread (this);
		thread.start();
	}
	
	public void run ()
	{
		boolean flag = true;
		while (flag == true)
		{
			try
			{
				String line = reader.readLine();
				area.append ("\n" + line);
			}
			catch (Exception e)
			{}
		}
	}

	public void start() throws IOException
	{
		reader = new BufferedReader (new InputStreamReader (socket.getInputStream()));
		console   = new BufferedReader(new InputStreamReader(System.in));
		writer  = new PrintWriter (socket.getOutputStream(), true);
	}

	public String getUserRestName(int userSelectedRestID) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from restaurants where rest_id = " + userSelectedRestID + ";";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("rest_name");
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		
		return "";
	}

	public int getCartId(String username) throws Exception {
		
		Connection con = null;
		Statement st = null;

		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select cart_id from users where username = '" + username + "';";
			
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			
			return(rs.getInt("cart_id"));
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		return 0;
	}

	public int showCart(int userSelectedRestID, int userCartID, JPanel itCartP) throws Exception {
		
		Connection con = null;
		Statement st = null;
		int totalAmount = 0;
		
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			int rowNum = 1;
			
			String sql = "Select cart.item_id, items.item_name, items.item_price, cart.item_qty from items inner join cart using(item_id) where cart_id = " + userCartID + ";";
			
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {

				JLabel itemName = new JLabel(rs.getString("item_name") + "");
		        itemName.setFont(new Font("", Font.BOLD, 16));
		        
		        JLabel itemPrice = new JLabel(rs.getFloat("item_price") + "", JLabel.CENTER);
		        itemPrice.setFont(new Font("", Font.BOLD, 16));
		        
		        JLabel itemQty = new JLabel(rs.getInt("item_qty") + "", JLabel.CENTER);
		        itemQty.setFont(new Font("", Font.BOLD, 16));
		        
		        itCartP.add(itemName);
				itCartP.add(itemPrice);
				itCartP.add(itemQty);
				
				totalAmount = totalAmount + (rs.getInt("item_price") * rs.getInt("item_qty"));
				
				rowNum++;
			}
			
			itCartP.add(new JLabel("------------------------------------------------------"));
			itCartP.add(new JLabel("------------------------------------------------------"));
			itCartP.add(new JLabel("------------------------------------------------------"));
			rowNum++;
			
			JLabel totalLbl = new JLabel("Total Price : ", JLabel.CENTER);
			totalLbl.setFont(new Font("", Font.BOLD, 25));
			totalLbl.setForeground(Color.BLUE);
	        
	        JLabel totalPriceLbl = new JLabel("Rs. " + totalAmount, JLabel.CENTER);
	        totalPriceLbl.setFont(new Font("", Font.BOLD, 25));
	        totalPriceLbl.setForeground(Color.BLUE);
	        
	        itCartP.add(totalLbl);
			itCartP.add(totalPriceLbl);
			itCartP.add(new JLabel(""));
			
			rowNum++;
			
			while(rowNum<12) {
				itCartP.add(new JLabel(""));
				itCartP.add(new JLabel(""));
				itCartP.add(new JLabel(""));
				rowNum ++;
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}	
		return totalAmount;
	}


	public void removeItem(int cartID, int rcItem) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from cart where cart_id = " + cartID + " and item_id = " + rcItem + ";";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				String sql1 = "Delete from cart where cart_id = " + cartID + " and item_id = " + rcItem + ";";
				
				int n = st.executeUpdate(sql1);
				
				if(n > 0) {
					System.out.println("Item is Removed...");
				}
			}
			else {
				System.out.println("Please... Enter the Valid Item ID");
			}
			System.out.println("--------------------------");
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
	}

	public void removeAllItems(int cartID) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Delete from cart where cart_id = " + cartID + ";";
			
			int n = st.executeUpdate(sql);
			
			System.out.println("All items are removed...");
			
			System.out.println("--------------------------");
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
	}

	public boolean setOrders(String username, int cartID, int userRestID, String payWay, int totalAmount) throws Exception {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Insert into orders(username, cart_id, rest_id, total_price, payment_way, delivery_status) values('" + username + "', " + cartID + ", " + userRestID + ", " + totalAmount + ", '" + payWay + "', 'N');";
			
			int n = st.executeUpdate(sql);
			
			if(n > 0) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}	
		
		return false;
	}
	
	public boolean checkPrevOrder(int cartID) throws Exception {
		Connection con = null;
		Statement st = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from orders where cart_id = '" + cartID + "' and delivery_status = 'N';";
			
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}	
		
		return false;
	} 
}
