package admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import restaurants.*;

public class admin implements Runnable{
	String adminUsername = "admin";
	String adminPassword = "admin123";
	
	Scanner scan = new Scanner(System.in);

	public boolean adminLogin(String adUsername, String adPassword) {
		if(adUsername.equals(adminUsername)) {
			if(adPassword.equals(adminPassword)) {
				return true;
			}
		}
		return false;
	}

	//////////////////
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
	static final String USER = "your_mysql_username";
	static final String PASS = "your_mysql_password";
	
	public boolean addRest(String restName, String restAddr, String restCity) throws Exception {
		
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Insert into restaurants(rest_name, rest_address, rest_city) values('" + restName + "', '" + restAddr + "', '" + restCity + "');";
			
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
	
	public boolean delRest(int restId) throws Exception {
		
		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select * from restaurants where rest_id = " + restId + ";";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				String sql1 = "Delete from restaurants where rest_id = " + restId + ";";
				
				int n = st.executeUpdate(sql1);
				
				if(n > 0) {
					return true;
				}
			}
			else {
				return false;
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
	
	public void displayRest(DefaultTableModel model) throws Exception {
		Connection con = null;
		Statement st = null;
		
//		System.out.println("----------------------------------------------------------------------------------------------------------------------");
//		System.out.println("|S.N.| Rest ID |    Restaurant Name     |          Address         |    City    |   Manager Name   | Manager Mob. No.|");
//		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			st = con.createStatement();
			
			String sql = "Select restaurants.rest_id, rest_name, rest_address, rest_city, rmg_name, rmg_mob_no from restaurants left join rest_manager using(rest_id);";
			
			ResultSet rs = st.executeQuery(sql);
			int i = 1;
			
			while(rs.next()) {
				//System.out.printf("|" +i + ".  | %-7d | %-22s | %-24s | %-10s | %-16s | %-15s |\n", rs.getInt("rest_id"), rs.getString("rest_name"), rs.getString("rest_address"), rs.getString("rest_city"), rs.getString("rmg_name"), rs.getString("rmg_mob_no"));
				
				model.addRow(new Object[] {rs.getInt("rest_id"), rs.getString("rest_name"), rs.getString("rest_address"), rs.getString("rest_city"), rs.getString("rmg_name"), rs.getString("rmg_mob_no")});
				
				i++;
			}
			//System.out.println("----------------------------------------------------------------------------------------------------------------------");
		}
		catch(Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		finally {
			st.close();
			con.close();	
		}
		//System.out.println("--------------------------");
	}
	
	public static String readString()
	{
	    Scanner scanner = new Scanner(System.in);
	    return scanner.nextLine();
	}
	
//	ServerSocket serverSocketAdmin2RM;
//	Socket socketAdmin2RM;
//	DataInputStream disAdmin2RM;
//	DataOutputStream dosAdmin2RM;
//	
//	public void admin2RMSocketStart() throws Exception {
//		serverSocketAdmin2RM = new ServerSocket(4045);
//		System.out.print("Server Socket Created : 4045");
//
//		socketAdmin2RM = serverSocketAdmin2RM.accept();  
//		System.out.print("Socket Accepted");
//		
//		disAdmin2RM = new DataInputStream(socketAdmin2RM.getInputStream());  
//		System.out.print("DIS Created");
//		
//		dosAdmin2RM = new DataOutputStream(socketAdmin2RM.getOutputStream());
//		System.out.print("DOS Created");
//	}
//
//	public void admin2RMSocketEnd() throws Exception {
//		disAdmin2RM.close();
//		System.out.print("DIS Closed");
//		
//		socketAdmin2RM.close();
//		System.out.print("Socket Closed");
//		
//		serverSocketAdmin2RM.close();
//		System.out.print("Server Socket Closed");
//	}
//	
//	public void chat2rm(JTextArea admin2RMChatTA, JTextField adminMsgTF)throws Exception{  
//		
//		String manager = "", you = "";  
// 
//		manager = disAdmin2RM.readUTF();  
//		
//		admin2RMChatTA.append("\n" +  manager);
//		System.out.println(manager);
//		
//		you = adminMsgTF.getText();
//		
//		adminMsgTF.setText("");
//		admin2RMChatTA.append("\nYou : " + you);
//		
//		dosAdmin2RM.writeUTF(you);
//		dosAdmin2RM.flush();  
//		
//		System.out.println("----------------------------");
//	}
//	
	
	private Socket socket = null;
	private ServerSocket serverSock = null;
	private BufferedReader console = null;
	private BufferedReader reader =  null;
	private PrintWriter writer = null;
	JTextArea area;
	
	public void chat2rm(JTextArea admin2RMChatTA, JButton chatRMBtn, JTextField adminMsgTF)throws Exception {
		
		chatRMBtn.addActionListener (new ActionListener()
		{
			public void actionPerformed (ActionEvent event)
			{
				String you = adminMsgTF.getText();
				writer.println (you);
				admin2RMChatTA.append ("\nYou : " + you);
				adminMsgTF.setText("");
			}
		});
		
		area = admin2RMChatTA;
		
		System.out.println("Establishing connection. Please wait ...");
		serverSock = new ServerSocket(4045);
		socket = serverSock.accept();
		
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


}
