package restaurants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

import javax.swing.*;
import javax.swing.table.*;

public class restaurantManager implements Runnable{
	
	//////////////////
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
	static final String USER = "your_mysql_username";
	static final String PASS = "your_mysql_password";

	Scanner scan = new Scanner(System.in);

	// manager login and register
	public boolean loginRM(String uNameRM, String pWordRM) throws Exception {
		
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from rest_manager where rmg_username = '" + uNameRM + "' and rmg_password = '" + pWordRM + "';";
			
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
	
	public int registerRM(String nameRM, long mobNoRM, String addressR, int restId, String usernameRM, String passwordRM) throws Exception {
		
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql1 = "Select * from restaurants where rest_id = " + restId + ";";
			ResultSet rs1 = st.executeQuery(sql1);
				
			if(rs1.next())
			{
				String sql2 = "Select * from rest_manager where rmg_username = '" + usernameRM + "';";
				ResultSet rs2 = st.executeQuery(sql2);
								
				if(rs2.next())
				{	
					return 0;
				}
				else {
					String sql3 = "Insert into rest_manager values('" + usernameRM + "', '" + restId + "', '" + nameRM + "', '" + addressR + "', " + mobNoRM + ", '" + passwordRM + "');";
					
					int n = st.executeUpdate(sql3);
				
					if(n > 0) {
						return 1;
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		return 2;
	}
	
	// manager login and register end
	
	public int getRestId(String rmUName) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from rest_manager where rmg_username = '" + rmUName + "';";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getInt("rest_id");
			}
			
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
	
	public String getRMName(String rmUName) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from rest_manager where rmg_username = '" + rmUName + "';";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("rmg_name");
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
	
	public String getRestName(String rmUName) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from restaurants where rest_id = '" + getRestId(rmUName) + "';";
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


	// displaying all orders
	public void showAllOrders(int restId, String deliveryStatus, DefaultTableModel model) throws Exception {
		Connection con1 = null;
		Statement st1 = null;
		
		Connection con2 = null;
		Statement st2 = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			con1 = DriverManager.getConnection(DB_URL, USER, PASS);
			st1 = con1.createStatement();
			
			String sql1 = null;
			
			sql1 = "Select o.order_id, u.uname, u.uaddress, u.cart_id, o.total_price, o.payment_way, o.delivery_status from orders o inner join users u using(cart_id) where rest_id = " + restId + " and delivery_status = '" + deliveryStatus +"';";

			ResultSet rs1 = st1.executeQuery(sql1);
			
			int i = 1;
			while(rs1.next()) {
				
//				System.out.println(i + ". Order ID         : " + rs1.getInt("order_id"));
//				System.out.println("   Customer Name    : " + rs1.getString("uname"));
//				System.out.println("   Customer Address : " + rs1.getString("uaddress"));
//				System.out.println("   Total Price      : " + rs1.getInt("total_price"));
//				System.out.println("   Payment Way      : " + rs1.getString("payment_way"));
//				System.out.println("   Delivery Status  : " + rs1.getString("delivery_status"));
				
				String custDetails = "<html><body>Order ID : " + rs1.getInt("order_id") + "<br>Customer Name : " + rs1.getString("uname") + "<br>Address : " + rs1.getString("uaddress") + "<br>Total Price : " + rs1.getInt("total_price") + "<br>Payment Way : " + rs1.getString("payment_way") + "<br> Delivery Status : " + rs1.getString("delivery_status") + "</body></html>";
				String orderDetails = "<html><body>";
				
				if(deliveryStatus == "N") {
					int cartId = rs1.getInt("cart_id");
					
					con2 = DriverManager.getConnection(DB_URL, USER, PASS);
					st2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					String sql2 = "Select cart.item_id, items.item_name, items.item_price, cart.item_qty from items inner join cart using(item_id) where cart_id = " + cartId + ";";
					
					ResultSet rs2 = st2.executeQuery(sql2);
					
					if(rs2.next()) {
						
						rs2.previous();
						
//						System.out.println("   --------------------------------------------------------------");
//						System.out.println("   |S.N.| Item ID |         Item Name         |  Price  |  Qty  |");
//						System.out.println("   --------------------------------------------------------------");
//						
						int j = 1;
						
						while(rs2.next()) {
							orderDetails = orderDetails + j + ". " + rs2.getString("item_name") + "  - " + rs2.getInt("item_qty") + "<br>";
							j++;
						}
						orderDetails = orderDetails + "</body></html>";
					}
					
				}
				
				model.addRow(new Object[] {i, custDetails, orderDetails});
				i++;
				
			}			
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st1.close();
			con1.close();	
		}	
	}

	public void changeDeliveryStatus(int ordId) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Update orders set delivery_status = 'Y' where order_id = " + ordId + ";";
			
			int n = st.executeUpdate(sql);
			
			if(n > 0) {
			
				System.out.println("--------------------------");
				System.out.println("Status Changed...");
				System.out.println("--------------------------");
				emptyUserCart(ordId);
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
	
	public void emptyUserCart(int ordId) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql1 = "Select cart_id from orders where order_id = " + ordId + ";";
			ResultSet rs1 = st.executeQuery(sql1);
			
			if(rs1.next()) {
				String sql2 = "Delete from cart where cart_id = " + rs1.getInt("cart_id") + ";";
				st.executeUpdate(sql2);
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
	// manager operations end
	
	// manager categories operation
	public void showCat(int restId, DefaultTableModel model) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from categories where rest_id = " + restId + ";";
			ResultSet rs = st.executeQuery(sql);
			
			//int i = 1;
			//System.out.println("----------------------------------------");
			//System.out.println("|S.N.| Category ID |   Category Name   |");
			//System.out.println("----------------------------------------");
			while(rs.next()) {
				//System.out.printf("| " + i + ". |    %-8d | %-17s |\n", rs.getInt("cat_id"), rs.getString("cat_name"));
				
				model.addRow(new Object[] {rs.getInt("cat_id"), rs.getString("cat_name")});
				//i++;
			}
			//System.out.println("----------------------------------------");
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
	}

	
	public boolean addCategory(String addCat, int restId) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Insert into categories(rest_id, cat_name) values (" + restId + ", '" + addCat + "');";
			int n = st.executeUpdate(sql);
			
			if(n > 0) {
				System.out.println("--------------------------");
				System.out.println("Category is Added...");
				System.out.println("--------------------------");
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
	
	public int delCategory(int delCatID, int restId) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from categories where cat_id = " + delCatID + ";";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				String sql1 = "Delete from categories where cat_id = " + delCatID + ";";
				
				int n = st.executeUpdate(sql1);
				
				if(n > 0) {
					System.out.println("Category is Deleted...");
					return 1;
				}
			}
			else {
				System.out.println("Please... Enter the Valid Category ID");
				return -1;
			}
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
	// manager categories operation end
	
	
	// manager items operation
	public void showItems(int restId, JTabbedPane itemsTP) throws Exception {
		Connection con = null;
		Statement st1 = null;
		Statement st2 = null;
		
//		System.out.println("-------------------------------------------------------------");
//		System.out.println("|S.N.| Item ID |       Item Name        |  Item Price (Rs.) |");
//		System.out.println("-------------------------------------------------------------");
		
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st1 = con.createStatement();
			
			String sql1 = "Select * from categories where rest_id = " + restId + ";";
			
			ResultSet rs1 = st1.executeQuery(sql1);
			//int i = 1;
			
			while(rs1.next()) {
				
				//JPanel catItem = new JPanel();
				
				String[] columnNames = {"Item ID", "Item Name", "Item Price (Rs.)"};
				
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);

				JTable itemsTbl = new JTable();
				itemsTbl.setModel(model);
				
				itemsTbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				
				JScrollPane scroll = new JScrollPane(itemsTbl);
				scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				JTableHeader th = itemsTbl.getTableHeader();
				th.setBackground(Color.BLACK);
				th.setForeground(Color.WHITE);
				th.setFont(new Font("", Font.BOLD, 18));
				
				itemsTbl.setRowHeight(30);
				itemsTbl.setFont(new Font("", Font.BOLD, 15));
				
				scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

				//System.out.printf("|" + 1 + ".  | %-7d | %-22s |\n", rs1.getInt("cat_id"), rs1.getString("cat_name"));
				
				st2 = con.createStatement();
				String sql2 = "Select * from items where cat_id = " + rs1.getInt("cat_id") + ";";
				ResultSet rs2 = st2.executeQuery(sql2);
				
				while(rs2.next()) {
					model.addRow(new Object[] {rs2.getInt("item_id"), rs2.getString("item_name"), rs2.getFloat("item_price")});

					//System.out.printf("|" + 1.1 + ".  | %-7d | %-22s | %-17d |\n", rs2.getInt("item_id"), rs2.getString("item_name"), rs2.getFloat("item_price"));
					//i++;
				}
				
				//catItem.add(scroll);
				
				itemsTP.addTab(rs1.getString("cat_name"), scroll);
				
				
			}
			//System.out.println("-------------------------------------------------------------");
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
	
	public void addCat2CB(int restId, JComboBox<String> itemCatCB) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from categories where rest_id = " + restId + ";";
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				itemCatCB.addItem(rs.getInt("cat_id") + " - " + rs.getString("cat_name"));
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
	
	public boolean addItems(int restId, int catId, String addItem, float priceItem) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Insert into items(cat_id, rest_id, item_name, item_price) values(" + catId + ", " + restId + ", '" + addItem + "', " + priceItem + ");";
			
			int n = st.executeUpdate(sql);
			
			if(n > 0) {
			
				System.out.println("--------------------------");
				System.out.println("Item is Added...");
				System.out.println("--------------------------");
				
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

	public int delItems(int delItem) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from items where item_id = " + delItem + ";";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				String sql1 = "Delete from items where item_id = " + delItem + ";";
				
				int n = st.executeUpdate(sql1);
				
				if(n > 0) {
					System.out.println("Item is Deleted...");
					return 1;
				}
			}
			else {
				System.out.println("Please... Enter the Valid Item ID");
				return -1;
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
		return 0;
	}

	public boolean editItems(int editItemID, String newEditItem, float newEditPrice) throws Exception {
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Update items set item_name = '" + newEditItem + "', item_price = " + newEditPrice + " where item_id = " + editItemID + ";";
			
			int n = st.executeUpdate(sql);
			
			if(n > 0) {
			
				System.out.println("--------------------------");
				System.out.println("Item Updated...");
				System.out.println("--------------------------");
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

	// manager item operation end
	
		
	// To store unique values in set
	
	public static String readString()
	{
	    Scanner scanner = new Scanner(System.in);
	    return scanner.nextLine();
	}
	
	
	Socket socket;
	ServerSocket server;
	Thread thread;
	static ExecutorService pool = Executors.newFixedThreadPool(3);
	int ID = 1;
	
	public void startServer(int port)
	{
		try
		{
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
		}
		
		System.out.println("Server started: " + server);
		System.out.println("Waiting for a client ...");
	}
	
	
	public void addClient2Chat(JTextArea rM2UserChatTA, JButton chatRM2UserBtn, JTextField RM2UserMsgTF, String RMRestName) {
		try
		{
			ServerThread st = new ServerThread(server.accept(), rM2UserChatTA, chatRM2UserBtn, RM2UserMsgTF, ID, RMRestName);
			System.out.println("Client#" + ID + " accepted ");
			pool.execute(st);
			ID++;
		}
		catch (IOException e3)
		{}
		
	}
	
	
	Socket socketRM2Admin;
	private BufferedReader console = null;
	private BufferedReader reader =  null;
	private PrintWriter writer = null;
	JTextArea area;
	
	public void RM2AdminChat(JTextArea RM2AdminChatTA, JTextField RM2AdminMsgTF, JButton chatRM2AdminBtn, String rMRestName) throws Exception {
		socketRM2Admin = new Socket("localhost", 4045);
		
		reader = new BufferedReader (new InputStreamReader (socketRM2Admin.getInputStream()));
		console   = new BufferedReader(new InputStreamReader(System.in));
		writer  = new PrintWriter (socketRM2Admin.getOutputStream(), true);
		String you = "", admin = "";
		
		area = RM2AdminChatTA;
		
		chatRM2AdminBtn.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				String you = RM2AdminMsgTF.getText();
				writer.println (rMRestName + "'s Manager : " + you);
				RM2AdminChatTA.append ("\nYou : " + you);
				RM2AdminMsgTF.setText("");
			}
		});
		
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
				area.append ("\nAdmin : " + line);
			}
			catch (Exception e)
			{}
		}
	}
}