package FoodOrderingSystemMain;

/**
 * @author Om Ingle
 *
 */

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import admin.*;
import users.*;
import restaurants.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main implements ActionListener, ItemListener {
	//user
	user u = new user();
			
	//restaurant manager
	restaurantManager rm = new restaurantManager();

	//admin
	admin ad = new admin();
	
	JFrame frame;
	JPanel p1, panel, layout, p2, container;
	JLabel l1, l2, l3, l4, passwordLbl, nameLbl, addrLbl, mobNoLbl, usernameLbl, cityLbl, restIDLbl;
	JLabel wlcmLbl, actAdminLbl;
	JButton userBtn, rmBtn, adminBtn, logBtn, canBtn, loginBtn, resetLogBtn, regBtn,  registerRMBtn, resetRMBtn, registerUserBtn, resetUserBtn, goBackLogBtn, goBackRMBtn, goBackUserBtn;
	JButton addRestBtn, delRestBtn, dispRestBtn, chatRMBtn, logoutAdminBtn, addRestDBBtn, delRestDBBtn, dispRestDBBtn, rstAddRestDBBtn, rstDelRestDBBtn;
	JTextField nameTF, addrTF, mobNoTF, usernameTF, cityTF, restIDTF;
	private JPasswordField passwordPF;
	JCheckBox showPasswordCB;
	DefaultTableModel model;
	JScrollPane scroll;
	JTable restTbl;
	
	String whoareyou = "";
	
	public Main() {
		frame = new JFrame();
		
		frame.setSize(1200, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mainFrame() {
		frame.setTitle("Food Ordering System");
		
		p1 = new JPanel();
		p1.setBackground(Color.CYAN);
		
		l1 = new JLabel("Welcome to Food Ordering System", JLabel.CENTER);
	    l1.setFont(new Font("",Font.BOLD,40));
	    l1.setForeground(Color.BLUE);
	    p1.add(l1);
		
	    panel = new JPanel(new BorderLayout());
	    panel.setBorder(new EmptyBorder(2, 3, 2, 3));
	    
	    JLabel layout = new JLabel(new ImageIcon("D:/PICT/TE-3 SEM-1 2020-21/SDL/SDL Assignments/main_image.jpg"));
	    layout.setLayout(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 5, 5, 5));
	    GridLayout gl = new GridLayout(3, 1, 10, 5);
	    
	    p2 = new JPanel(gl);
	    p2.setBorder(new EmptyBorder(5, 5, 5, 5));;
	    
	    userBtn = new JButton("User");
	    rmBtn = new JButton("Restaurant Manager");
	    adminBtn = new JButton("Admin");
	
	    userBtn.addActionListener(this);
	    rmBtn.addActionListener(this);
	    adminBtn.addActionListener(this);
	    
	    p2.add(userBtn);
	    p2.add(rmBtn);
	    p2.add(adminBtn);
	    
	    layout.add(p2);
	    panel.add(layout, BorderLayout.CENTER);
	    
	    frame.add(p1, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
		
	    
	    frame.setVisible(true);
		
	}
	
	public void loginFrame() {
		frame.setTitle("Login");
		
		JLabel logLbl = new JLabel("Login", JLabel.CENTER);
		logLbl.setFont(new Font("",Font.BOLD,40));
		
		panel = new JPanel(new BorderLayout());
	    panel.setBorder(new EmptyBorder(2, 3, 2, 3));
	    layout = new JPanel(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 5, 5, 5));
	    		
		
	    usernameLbl = new JLabel("USERNAME", JLabel.CENTER);
	    passwordLbl = new JLabel("PASSWORD", JLabel.CENTER);
	    
	    usernameTF = new JTextField(30);
	    passwordPF = new JPasswordField(30);
	    
	    loginBtn = new JButton("LOGIN");
	    resetLogBtn = new JButton("RESET");
	    
	    showPasswordCB = new JCheckBox("Show Password");
        
	    regBtn = new JButton("REGISTER");
	    goBackLogBtn = new JButton("GO BACK");
	    
	    GridLayout gl = new GridLayout(6, 2, 10, 10);
        container = new JPanel(gl);
        
        container.add(usernameLbl);
        container.add(usernameTF);
        container.add(passwordLbl);
        container.add(passwordPF);
        container.add(new JLabel(""));
        container.add(showPasswordCB);
        container.add(loginBtn);
        container.add(resetLogBtn);
        container.add(new JLabel(""));
        container.add(new JLabel(""));
        container.add(regBtn);
        container.add(goBackLogBtn);
        
        loginBtn.addActionListener(this);
        resetLogBtn.addActionListener(this);
        showPasswordCB.addActionListener(this);
        regBtn.addActionListener(this);
        goBackLogBtn.addActionListener(this);
        
        layout.add(container);
	    panel.add(layout, BorderLayout.CENTER);
        
	    frame.add(logLbl, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
	    
	    
	    frame.setVisible(true);
	    
	}
	
	public void registerFrameRM() {
		frame.setTitle("Restaurant Manager Registration");
		
		JLabel logLbl = new JLabel("Restaurant Manager Registration", JLabel.CENTER);
		logLbl.setFont(new Font("",Font.BOLD,40));
		
	    panel = new JPanel(new BorderLayout());
	    panel.setBorder(new EmptyBorder(2, 3, 2, 3));
	    layout = new JPanel(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 5, 5, 5));
	    
	    
	    nameLbl = new JLabel("NAME", JLabel.CENTER);
	    addrLbl = new JLabel("ADDRESS", JLabel.CENTER);
	    mobNoLbl = new JLabel("MOB. NO.", JLabel.CENTER);
	    restIDLbl = new JLabel("RESTAURANT ID", JLabel.CENTER);
	    usernameLbl = new JLabel("USERNAME", JLabel.CENTER);
	    passwordLbl = new JLabel("PASSWORD", JLabel.CENTER);
	    
	    nameTF = new JTextField(30);
	    addrTF = new JTextField(30);
	    mobNoTF = new JTextField(30);
	    restIDTF = new JTextField(30);
	    usernameTF = new JTextField(30);
	    passwordPF = new JPasswordField(30);
	    
	    showPasswordCB = new JCheckBox("Show Password");
	    
	    registerRMBtn = new JButton("REGISTER");
	    resetRMBtn = new JButton("RESET");
	    
	    goBackRMBtn = new JButton("Go Back");
	    
	    GridLayout gl = new GridLayout(8, 2, 10, 10);
        JPanel container = new JPanel(gl);
        
        container.add(nameLbl);
        container.add(nameTF);
        container.add(addrLbl);
        container.add(addrTF);
        container.add(mobNoLbl);
        container.add(mobNoTF);
        container.add(restIDLbl);
        container.add(restIDTF);
        container.add(usernameLbl);
        container.add(usernameTF);
        container.add(passwordLbl);
        container.add(passwordPF);
        container.add(new JLabel(""));
        container.add(showPasswordCB);
        container.add(registerRMBtn);
        container.add(resetRMBtn);
        
        registerRMBtn.addActionListener(this);
        resetRMBtn.addActionListener(this);
        showPasswordCB.addActionListener(this);
        goBackRMBtn.addActionListener(this);
        
        layout.add(container);
	    panel.add(layout, BorderLayout.CENTER);
        
	    frame.add(logLbl, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
	    frame.add(goBackRMBtn, BorderLayout.SOUTH);
	    
	    frame.setVisible(true);
	    
	}
	
	public void registerFrameUser() {
		frame.setTitle("User Registration");
		
		JLabel logLbl = new JLabel("User Registration", JLabel.CENTER);
		logLbl.setFont(new Font("",Font.BOLD,40));
		
	    panel = new JPanel(new BorderLayout());
	    panel.setBorder(new EmptyBorder(2, 3, 2, 3));
	    layout = new JPanel(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 5, 5, 5));
	    		
	    nameLbl = new JLabel("NAME", JLabel.CENTER);
	    addrLbl = new JLabel("ADDRESS", JLabel.CENTER);
	    mobNoLbl = new JLabel("MOB. NO.", JLabel.CENTER);
	    cityLbl = new JLabel("CITY", JLabel.CENTER);
	    usernameLbl = new JLabel("USERNAME", JLabel.CENTER);
	    passwordLbl = new JLabel("PASSWORD", JLabel.CENTER);
	    
	    nameTF = new JTextField(30);
	    addrTF = new JTextField(30);
	    mobNoTF = new JTextField(30);
	    cityTF = new JTextField(30);
	    usernameTF = new JTextField(30);
	    passwordPF = new JPasswordField(30);
	    
	    showPasswordCB = new JCheckBox("Show Password");

	    registerUserBtn = new JButton("REGISTER");
	    resetUserBtn = new JButton("RESET");
	    
	    goBackUserBtn = new JButton("Go Back");
	    
	    GridLayout gl = new GridLayout(8, 2, 10, 10);
        JPanel container = new JPanel(gl);
        
        container.add(nameLbl);
        container.add(nameTF);
        container.add(addrLbl);
        container.add(addrTF);
        container.add(cityLbl);
        container.add(cityTF);
        container.add(mobNoLbl);
        container.add(mobNoTF);
        container.add(usernameLbl);
        container.add(usernameTF);
        container.add(passwordLbl);
        container.add(passwordPF);
        container.add(new JLabel(""));
        container.add(showPasswordCB);
        container.add(registerUserBtn);
        container.add(resetUserBtn);
     
        registerUserBtn.addActionListener(this);
        resetUserBtn.addActionListener(this);
        goBackUserBtn.addActionListener(this);
        showPasswordCB.addActionListener(this);
	    
        layout.add(container);
	    panel.add(layout, BorderLayout.CENTER);
        
	    frame.add(logLbl, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
	    frame.add(goBackUserBtn, BorderLayout.SOUTH);
	    
	    frame.setVisible(true);
	    
	}
	
	JTextField adminMsgTF;
	JTextArea admin2RMChatTA;
	JButton adminChatServerOn;
	
	// Chat
	ServerSocket serverSocketAdmin2RM;
	Socket socketAdmin2RM;
	DataInputStream disAdmin2RM;
	DataOutputStream dosAdmin2RM;
	// Chat end
	
	public void adminFrame() throws Exception {
		
		frame.setTitle("Admin - Food Ordering System");
		
		p1 = new JPanel(new GridLayout(10, 1, 20, 20));
		p1.setBorder(BorderFactory.createTitledBorder("Admin Operations"));
		
		addRestBtn = new JButton("ADD RESTAURANT");
		delRestBtn = new JButton("DELETE RESTAURANT");
		dispRestBtn = new JButton("DISPLAY RESTAURANTS");
		logoutAdminBtn = new JButton("LOGOUT");
		
		JLabel helloAdLbl = new JLabel("Hello, Admin", JLabel.CENTER);
		helloAdLbl.setFont(new Font("", Font.BOLD, 25));
		
		p1.add(helloAdLbl);
		p1.add(new JLabel(""));
		p1.add(new JLabel(""));
		p1.add(addRestBtn);
		p1.add(delRestBtn);
        p1.add(dispRestBtn);
        p1.add(new JLabel(""));
        p1.add(new JLabel(""));
        p1.add(new JLabel(""));
        p1.add(logoutAdminBtn);
        
        p2 = new JPanel(new GridLayout(15, 1));
		
        wlcmLbl = new JLabel("WELCOME TO FOOD ORDERING SYSTEM", JLabel.CENTER);
		wlcmLbl.setFont(new Font("", Font.BOLD, 50));
		
		JLabel adMsgLbl1 = new JLabel("You are the admin of", JLabel.CENTER);
		adMsgLbl1.setFont(new Font("", Font.BOLD, 35));
		JLabel adMsgLbl2 = new JLabel("\"FOOD ORDERING SYSTEM\"", JLabel.CENTER);
		adMsgLbl2.setFont(new Font("", Font.BOLD, 35));
		
		JLabel adOpLbl1 = new JLabel("Here you can Add or Delete Restaurants,", JLabel.CENTER);
		adOpLbl1.setFont(new Font("", Font.BOLD, 25));
		JLabel adOpLbl2 = new JLabel("See the Restaurant's Details, Chat with Restaurant's Manager", JLabel.CENTER);
		adOpLbl2.setFont(new Font("", Font.BOLD, 25));
		JLabel adOpLbl3 = new JLabel("and Many More...", JLabel.CENTER);
		adOpLbl3.setFont(new Font("", Font.BOLD, 25));
      
		p2.add(new JLabel(""));
        p2.add(new JLabel(""));
        p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(adMsgLbl1);
		p2.add(adMsgLbl2);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(adOpLbl1);
		p2.add(adOpLbl2);
		p2.add(adOpLbl3);
		
		
		JPanel p3 = new JPanel(new BorderLayout());
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		
		admin2RMChatTA = new JTextArea(28, 25);
		admin2RMChatTA.setLineWrap(true);
		admin2RMChatTA.setEditable(false);
		
		JScrollPane admin2RMScroll = new JScrollPane(admin2RMChatTA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		admin2RMChatTA.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		adminMsgTF = new JTextField(16);
		chatRMBtn = new JButton("SEND");
		
		p4.add(adminMsgTF);
		p4.add(chatRMBtn);
		
		adminChatServerOn = new JButton("Start");
		
		p5.add(adminChatServerOn);
		
		p3.add(p5, BorderLayout.NORTH);
		p3.add(admin2RMScroll, BorderLayout.CENTER);
		p3.add(p4, BorderLayout.SOUTH);
		
		p3.setBorder(BorderFactory.createTitledBorder("Chat With Managers"));
		
        addRestBtn.addActionListener(this);
        delRestBtn.addActionListener(this);
        dispRestBtn.addActionListener(this);
        chatRMBtn.addActionListener(this);
        logoutAdminBtn.addActionListener(this);
        adminChatServerOn.addActionListener(this);
        
        panel = new JPanel();
        
	    frame.add(p1, BorderLayout.WEST);
	    frame.add(wlcmLbl, BorderLayout.NORTH);
	    frame.add(p2, BorderLayout.CENTER);
	    frame.add(p3, BorderLayout.EAST);
	    frame.add(panel, BorderLayout.SOUTH);

	    frame.setVisible(true);
	    
	}

	public void addRestFrame() {
		frame.setTitle("Admin - Food Ordering System");
		
		p2 = new JPanel();    
        
        wlcmLbl.setText("ADD RESTAURANT");
        wlcmLbl.setFont(new Font("", Font.BOLD, 40));
		
		p2.add(wlcmLbl);
       
		
        panel = new JPanel(new BorderLayout());
	    panel.setBorder(new EmptyBorder(2, 3, 2, 3));
	    layout = new JPanel(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 5, 50, 5));
	    		
	    nameLbl = new JLabel("NAME", JLabel.CENTER);
	    addrLbl = new JLabel("ADDRESS", JLabel.CENTER);
	    cityLbl = new JLabel("CITY", JLabel.CENTER);
	    
	    nameTF = new JTextField(30);
	    addrTF = new JTextField(30);
	    cityTF = new JTextField(30);
	    
	    addRestDBBtn = new JButton("ADD");
	    rstAddRestDBBtn = new JButton("RESET");
	    
	    GridLayout gl = new GridLayout(5, 2, 10, 10);
        JPanel container = new JPanel(gl);
        
        container.add(nameLbl);
        container.add(nameTF);
        container.add(addrLbl);
        container.add(addrTF);
        container.add(cityLbl);
        container.add(cityTF);
        container.add(new JLabel(""));
        container.add(new JLabel(""));
        container.add(addRestDBBtn);
        container.add(rstAddRestDBBtn);
     
        addRestDBBtn.addActionListener(this);
        rstAddRestDBBtn.addActionListener(this);
	    
        layout.add(container);
	    panel.add(layout, BorderLayout.CENTER);
        
		frame.add(p2, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
	    
	    frame.setVisible(true);   
	}
	
	public void delRestFrame() {
		frame.setTitle("Admin - Food Ordering System");
		
		p2 = new JPanel();    
        
        wlcmLbl.setText("DELETE RESTAURANT");
        wlcmLbl.setFont(new Font("", Font.BOLD, 40));
		p2.add(wlcmLbl);
       
		
        panel = new JPanel(new BorderLayout());
	    panel.setBorder(new EmptyBorder(2, 3, 2, 3));
	    layout = new JPanel(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 5, 50, 5));
	    		
	    restIDLbl = new JLabel("RESTAURANT ID", JLabel.CENTER);
	    
	    restIDTF = new JTextField(30);
	    
	    delRestDBBtn = new JButton("DELETE");
	    rstDelRestDBBtn = new JButton("RESET");
	    
	    GridLayout gl = new GridLayout(3, 2, 10, 10);
        JPanel container = new JPanel(gl);
        
        container.add(restIDLbl);
        container.add(restIDTF);
        container.add(new JLabel(""));
        container.add(new JLabel(""));
        container.add(delRestDBBtn);
        container.add(rstDelRestDBBtn);
     
        delRestDBBtn.addActionListener(this);
        rstDelRestDBBtn.addActionListener(this);
	    
        layout.add(container);
	    panel.add(layout, BorderLayout.CENTER);
        
		frame.add(p2, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
	    
	    frame.setVisible(true);   
	}
	
	public void dispRestFrame() {
		frame.setTitle("Admin - Food Ordering System");
		
		p2 = new JPanel();    
        
        wlcmLbl.setText("LIST OF RESTAURANTS");
        wlcmLbl.setFont(new Font("", Font.BOLD, 40));
		p2.add(wlcmLbl);
	    
		String[] columnNames = {"ID", "Name", "Address", "City", "Manager", "Mob. No."};
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);

		restTbl = new JTable();
		restTbl.setModel(model);
		
		restTbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//restTbl.setFillsViewportHeight(true);
		
		scroll = new JScrollPane(restTbl);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		try {
			ad.displayRest(model);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
		JTableHeader th = restTbl.getTableHeader();
		th.setBackground(Color.BLACK);
		th.setForeground(Color.WHITE);
		th.setFont(new Font("", Font.BOLD, 18));
		
		restTbl.setRowHeight(30);
		restTbl.setFont(new Font("", Font.BOLD, 15));
		
		scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		
        frame.add(p2, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
	    
	    frame.setVisible(true);   
	}
	
	String RMUsername = "", RMName = "", RMRestName = "";
	JButton chatRM2UserBtn, chatRM2AdminBtn;
	JButton catBtn, itemsBtn, viewOrdersBtn, logoutRMBtn, addCatBtn, delCatBtn, RM2UserChatServerOn, RM2UserAddClient, RM2AdminChatOn;
	JTabbedPane chatRMTP;
	JTextArea RM2AdminChatTA, RM2UserChatTA;
	JTextField RM2UserMsgTF, RM2AdminMsgTF;
	
	Socket socketRM2Admin;
	DataInputStream disRM2Admin;
	DataOutputStream dosRM2Admin;
	
	public void RMFrame() {
		frame.setTitle("Restaurant Manager - Food Ordering System");
		
		p1 = new JPanel(new GridLayout(10, 1, 20, 20));
		p1.setBorder(BorderFactory.createTitledBorder("Manager Operations"));
		
		catBtn = new JButton("       CATEGORIES       ");
		itemsBtn = new JButton("ITEMS");
		viewOrdersBtn = new JButton("VIEW ORDERS");
		logoutRMBtn = new JButton("LOGOUT");
		
		try {
			RMName = rm.getRMName(RMUsername);
		} catch (Exception e1) {
			System.out.println("Exception : " + e1.getMessage());
		}
		
		JLabel helloRMLbl = new JLabel("Hello, " + RMName, JLabel.CENTER);
		helloRMLbl.setFont(new Font("", Font.BOLD, 25));
		
		
		p1.add(helloRMLbl);
		p1.add(new JLabel(""));
		p1.add(new JLabel(""));
		p1.add(catBtn);
		p1.add(itemsBtn);
        p1.add(viewOrdersBtn);
        p1.add(new JLabel(""));
        p1.add(new JLabel(""));
        p1.add(new JLabel(""));
        p1.add(logoutRMBtn);
        
        p2 = new JPanel(new GridLayout(15, 1));
		
        wlcmLbl = new JLabel("WELCOME TO FOOD ORDERING SYSTEM", JLabel.CENTER);
		wlcmLbl.setFont(new Font("", Font.BOLD, 50));
		
		JLabel RMMsgLbl1 = new JLabel("You are the Manager of", JLabel.CENTER);
		RMMsgLbl1.setFont(new Font("", Font.BOLD, 35));
		
		
		try {
			RMRestName = rm.getRestName(RMUsername);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
		
		JLabel RMMsgLbl2 = new JLabel("\"" + RMRestName + "\"", JLabel.CENTER);
		RMMsgLbl2.setFont(new Font("", Font.BOLD, 35));
		
		JLabel RMOpLbl1 = new JLabel("Here you can Add or Delete Categories/Items,", JLabel.CENTER);
		RMOpLbl1.setFont(new Font("", Font.BOLD, 25));
		JLabel RMOpLbl2 = new JLabel("See the Order's Details, Chat with Users", JLabel.CENTER);
		RMOpLbl2.setFont(new Font("", Font.BOLD, 25));
		JLabel RMOpLbl3 = new JLabel("and Many More...", JLabel.CENTER);
		RMOpLbl3.setFont(new Font("", Font.BOLD, 25));
        
		
		p2.add(new JLabel(""));
        p2.add(new JLabel(""));
        p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(RMMsgLbl1);
		p2.add(RMMsgLbl2);
		p2.add(new JLabel(""));
		p2.add(new JLabel(""));
		p2.add(RMOpLbl1);
		p2.add(RMOpLbl2);
		p2.add(RMOpLbl3);
		
		chatRMTP = new JTabbedPane();
		
		JPanel p3 = new JPanel(new BorderLayout());
		JPanel p4 = new JPanel();
		
		RM2UserChatTA = new JTextArea(28, 25);
		RM2UserChatTA.setLineWrap(true);
		RM2UserChatTA.setEditable(false);
		
		RM2UserChatTA.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		JScrollPane RM2UserScroll = new JScrollPane(RM2UserChatTA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		RM2UserMsgTF = new JTextField(16);
		chatRM2UserBtn = new JButton("SEND");
		
		p4.add(RM2UserMsgTF);
		p4.add(chatRM2UserBtn);
		
		
		JPanel p7 = new JPanel();
		RM2UserChatServerOn = new JButton("Start");
		RM2UserAddClient = new JButton("Add");
		
		p7.add(RM2UserChatServerOn);
		p7.add(RM2UserAddClient);
		
		p3.add(p7, BorderLayout.NORTH);
		p3.add(RM2UserScroll, BorderLayout.CENTER);
		p3.add(p4, BorderLayout.SOUTH);
		
		chatRMTP.setBorder(BorderFactory.createTitledBorder("Chat With Customers/Admin"));
		
	
		JPanel p5 = new JPanel(new BorderLayout());
		JPanel p6 = new JPanel();
		
		RM2AdminChatTA = new JTextArea(28, 25);
		RM2AdminChatTA.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		RM2AdminChatTA.setLineWrap(true);
		RM2AdminChatTA.setEditable(false);
		
		JScrollPane RM2AdminScroll = new JScrollPane(RM2AdminChatTA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		RM2AdminMsgTF = new JTextField(16);
		chatRM2AdminBtn = new JButton("SEND");
		
		p6.add(RM2AdminMsgTF);
		p6.add(chatRM2AdminBtn);
		
		
		JPanel p8 = new JPanel();
		RM2AdminChatOn = new JButton("Start");
		
		p8.add(RM2AdminChatOn);
		
		p5.add(p8, BorderLayout.NORTH);
		p5.add(RM2AdminScroll, BorderLayout.CENTER);
		p5.add(p6, BorderLayout.SOUTH);
		
			
        chatRMTP.addTab("Users", p3);
        chatRMTP.addTab("Admin", p5);
        
        panel = new JPanel();
        
        
        catBtn.addActionListener(this);
        itemsBtn.addActionListener(this);
        viewOrdersBtn.addActionListener(this);
        //chatRM2UserBtn.addActionListener(this);
//        chatRM2AdminBtn.addActionListener(this);
        logoutRMBtn.addActionListener(this);
        RM2AdminChatOn.addActionListener(this);
        RM2UserChatServerOn.addActionListener(this);
        RM2UserAddClient.addActionListener(this);
        
	    frame.add(p1, BorderLayout.WEST);
	    frame.add(wlcmLbl, BorderLayout.NORTH);
	    frame.add(p2, BorderLayout.CENTER);
	    frame.add(chatRMTP, BorderLayout.EAST);
	    frame.add(panel, BorderLayout.SOUTH);

	    frame.setVisible(true);
	    
	}
	
	JLabel catIDLbl, catNameLbl;
	JTextField catIDTF, catNameTF;
	
	public void catRMFrame() {
		frame.setTitle("Restaurant Manager - Food Ordering System");
		
		p2 = new JPanel();
		
        wlcmLbl.setText("CATEGORIES");
		wlcmLbl.setFont(new Font("", Font.BOLD, 40));
		
		p2.add(wlcmLbl);
        
		panel = new JPanel(new BorderLayout());
        
        String[] columnNames = {"Category ID", "Category Name"};
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);

		restTbl = new JTable();
		restTbl.setModel(model);
		
		restTbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//restTbl.setFillsViewportHeight(true);
		
		scroll = new JScrollPane(restTbl);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		try {
			rm.showCat(rm.getRestId(RMUsername), model);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
		JTableHeader th = restTbl.getTableHeader();
		th.setBackground(Color.BLACK);
		th.setForeground(Color.WHITE);
		th.setFont(new Font("", Font.BOLD, 18));
		
		restTbl.setRowHeight(30);
		restTbl.setFont(new Font("", Font.BOLD, 15));
		
		scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel p7 = new JPanel(new GridLayout(3, 3, 10, 10));
		
		catNameLbl = new JLabel("ENTER CATEGORY NAME", JLabel.CENTER);
		catNameTF = new JTextField(20);
		addCatBtn = new JButton("ADD CATEGORY");
		
		catIDLbl = new JLabel("ENTER CATEGORY ID", JLabel.CENTER);
		catIDTF = new JTextField(20);
		delCatBtn = new JButton("DELETE CATEGORY");
		
		p7.add(catNameLbl);
		p7.add(new JLabel(""));
		p7.add(catIDLbl);
		p7.add(catNameTF);
		p7.add(new JLabel(""));
		p7.add(catIDTF);
		p7.add(addCatBtn);
		p7.add(new JLabel(""));
		p7.add(delCatBtn);
		
		p7.setBorder(BorderFactory.createEmptyBorder(10, 100, 50, 100));
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(p7, BorderLayout.SOUTH);
		
		addCatBtn.addActionListener(this);
		delCatBtn.addActionListener(this);
        
	    frame.add(p2, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);

	    frame.setVisible(true);
	    
	}
	
	JLabel itemIDLbl, itemNameLbl, itemPriceLbl;
	JTextField itemNameAddTF, itemPriceAddTF, itemIDDelTF, itemIDEditTF, itemNameEditTF, itemPriceEditTF;
	JButton addItemBtn, delItemBtn, editItemBtn;
	JComboBox<String> itemCatCB;
	
	public void itemsRMFrame() {
		frame.setTitle("Restaurant Manager - Food Ordering System");
		
		p2 = new JPanel();
		
        wlcmLbl.setText("ITEMS");
		wlcmLbl.setFont(new Font("", Font.BOLD, 40));
		
		p2.add(wlcmLbl);
        
		panel = new JPanel(new BorderLayout());
        
		JTabbedPane itemsTP = new JTabbedPane();
		
		try {
			rm.showItems(rm.getRestId(RMUsername), itemsTP);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
		JTabbedPane itemsOpTP = new JTabbedPane();
		
		
		itemIDLbl = new JLabel("ITEM ID", JLabel.RIGHT);
		itemNameLbl = new JLabel("ITEM NAME", JLabel.RIGHT);
		itemPriceLbl = new JLabel("ITEM PRICE", JLabel.RIGHT);
		
		
		
		JPanel p3 = new JPanel(new GridLayout(4, 2, 10, 10));
		
		itemNameAddTF = new JTextField(20);
		itemPriceAddTF = new JTextField(20);
		
		
		JLabel itemCatLbl = new JLabel("ITEM CATEGORY", JLabel.RIGHT);
		itemCatCB = new JComboBox<String>();
		
		try {
			rm.addCat2CB(rm.getRestId(RMUsername), itemCatCB);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
		
		addItemBtn = new JButton("ADD ITEM");
		
		p3.add(itemNameLbl);
		p3.add(itemNameAddTF);
		p3.add(itemPriceLbl);
		p3.add(itemPriceAddTF);
		p3.add(itemCatLbl);
		p3.add(itemCatCB);
		p3.add(new JLabel(""));
		p3.add(addItemBtn);
			
		p3.setBorder(BorderFactory.createEmptyBorder(10, 80, 10, 240));
		
		
		
		JPanel p4 = new JPanel(new GridLayout(2, 2, 10, 10));
		
		itemIDDelTF = new JTextField(20);
		delItemBtn = new JButton("DELETE ITEM");
		
		p4.add(itemIDLbl);
		p4.add(itemIDDelTF);
		p4.add(new JLabel(""));
		p4.add(delItemBtn);
			
		p4.setBorder(BorderFactory.createEmptyBorder(48, 80, 50, 240));
		
		
		
		JPanel p5 = new JPanel(new GridLayout(4, 2, 10, 10));
		
		itemIDLbl = new JLabel("ITEM ID", JLabel.RIGHT);
		itemIDEditTF = new JTextField(20);
		itemNameLbl = new JLabel("ITEM NAME", JLabel.RIGHT);
		itemNameEditTF = new JTextField(20);
		itemPriceLbl = new JLabel("ITEM PRICE", JLabel.RIGHT);
		itemPriceEditTF = new JTextField(20);
		editItemBtn = new JButton("UPDATE ITEM");
		
		p5.add(itemIDLbl);
		p5.add(itemIDEditTF);
		p5.add(itemNameLbl);
		p5.add(itemNameEditTF);
		p5.add(itemPriceLbl);
		p5.add(itemPriceEditTF);
		p5.add(new JLabel(""));
		p5.add(editItemBtn);
			
		p5.setBorder(BorderFactory.createEmptyBorder(10, 80, 10, 240));
		
		itemsOpTP.addTab("Add Items",  p3);
		itemsOpTP.addTab("Delete Items",  p4);
		itemsOpTP.addTab("Update Items",  p5);
		
		addItemBtn.addActionListener(this);
		delItemBtn.addActionListener(this);
		editItemBtn.addActionListener(this);
		
		panel.add(itemsTP, BorderLayout.CENTER);
		panel.add(itemsOpTP, BorderLayout.SOUTH);
		
	    frame.add(p2, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);

	    frame.setVisible(true);
	    
	}
	
	JTable ordersTbl;
	JTextField changeDelStatusTF;
	JButton showUndelOrderBtn, showDelOrderBtn, changeDelStatusBtn;
	
	String deliStatus = "N";
	
	public void viewOrdersRMFrame() {
		frame.setTitle("Restaurant Manager - Food Ordering System");
		
		p2 = new JPanel();
		
        wlcmLbl.setText("VIEW ORDERS");
		wlcmLbl.setFont(new Font("", Font.BOLD, 40));
		
		p2.add(wlcmLbl);

		
		panel = new JPanel(new BorderLayout());
		
		String[] columnNames = {"Sr. No.", "Customer Details", "Order Details"};
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);

		ordersTbl = new JTable();
		ordersTbl.setModel(model);
		
		ordersTbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//restTbl.setFillsViewportHeight(true);
		
		scroll = new JScrollPane(ordersTbl);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		try {
			rm.showAllOrders(rm.getRestId(RMUsername), deliStatus, model);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
		
		JTableHeader th = ordersTbl.getTableHeader();
		th.setBackground(Color.BLACK);
		th.setForeground(Color.WHITE);
		th.setFont(new Font("", Font.BOLD, 15));
		
		ordersTbl.setRowHeight(120);
		ordersTbl.setFont(new Font("", Font.BOLD, 12));
		
		scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panel.add(scroll, BorderLayout.CENTER);
		
		JPanel p3 = new JPanel(new GridLayout(1, 3, 10, 10));
		p3.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
		
		showUndelOrderBtn = new JButton("SHOW UNDELIVERD ORDERS");
		p3.add(showUndelOrderBtn);
		
		showDelOrderBtn = new JButton("SHOW DELIVERD ORDERS");
		p3.add(showDelOrderBtn);
		
		JPanel p4 = new JPanel(new GridLayout(2, 1));
		changeDelStatusTF = new JTextField(30);
		p4.add(changeDelStatusTF);
		
		changeDelStatusBtn = new JButton("CHANGE DELIVERY STATUS");
		p4.add(changeDelStatusBtn);
		
		p3.add(p4);
		
		showUndelOrderBtn.addActionListener(this);
		showDelOrderBtn.addActionListener(this);
		changeDelStatusBtn.addActionListener(this);
		
		panel.add(p3, BorderLayout.SOUTH);
		
		frame.add(p2, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);

	    frame.setVisible(true);
	    
	}
	
	String custUsername = "", nameOfUser = "";
	JButton restSelectUserBtn;
	int userSelectedRestID, userCartID;
	ButtonGroup userRestBG;
	
	public void userRSFrame() throws Exception {
		frame.setTitle("Customer - Food Ordering System");
		
		wlcmLbl = new JLabel("WELCOME TO FOOD ORDERING SYSTEM", JLabel.CENTER);
		wlcmLbl.setFont(new Font("", Font.BOLD, 50));
		
		try {
			nameOfUser = u.getNameofUser(custUsername);
		} catch (Exception e1) {
			System.out.println("Exception : " + e1.getMessage());
		}
		
		
		panel = new JPanel(new BorderLayout());
	    panel.setBorder(new EmptyBorder(2, 30, 2, 3));
	    layout = new JPanel(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 50, 5, 5));
	    		
		p1 = new JPanel(new GridLayout(7, 1));
        
        
		JLabel cityRestLbl = new JLabel("Restarants Available in " + u.getUserCity(custUsername) + " - ");
		cityRestLbl.setFont(new Font("", Font.BOLD, 35));
		
		JLabel selectRestLbl = new JLabel("Select Restaurant : ");
		selectRestLbl.setFont(new Font("", Font.BOLD, 25));
		
		p1.add(cityRestLbl);
		p1.add(selectRestLbl);
		
		userRestBG = new ButtonGroup();
		
		u.displayCityRest(custUsername, userRestBG, p1);
		
		restSelectUserBtn = new JButton("Go");
		restSelectUserBtn.addActionListener(this);
		p1.add(restSelectUserBtn);
		
		layout.add(p1);
	    panel.add(layout, BorderLayout.CENTER);
	    
		p2 = new JPanel(new GridLayout(5, 1));
		
       	JLabel userOpLbl1 = new JLabel("Here you can Place Your Order,", JLabel.CENTER);
		userOpLbl1.setFont(new Font("", Font.BOLD, 25));
		JLabel userOpLbl2 = new JLabel("See Your Cart, Make Payments, Chat with Restaurant Managers", JLabel.CENTER);
		userOpLbl2.setFont(new Font("", Font.BOLD, 25));
		JLabel userOpLbl3 = new JLabel("and Many More...", JLabel.CENTER);
		userOpLbl3.setFont(new Font("", Font.BOLD, 25));
        
		p2.add(new JLabel(""));
		p2.add(userOpLbl1);
		p2.add(userOpLbl2);
		p2.add(userOpLbl3);
		p2.add(new JLabel(""));
		
		frame.add(wlcmLbl, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(p2, BorderLayout.SOUTH);
	   
	    frame.setVisible(true);
	    
	}
	
	JButton selectItemsUserBtn, orderUserBtn, logoutUserBtn, viewCartUserBtn;
	JTabbedPane chatUserTP;
	JTextArea User2RMChatTA;
	JTextField User2RMMsgTF;
	JButton chatUser2RMBtn, User2RMChatConnect;
	
	public void userFrame() {
		frame.setTitle("Customer - Food Ordering System");
		
		p1 = new JPanel(new GridLayout(11, 1, 20, 20));
		p1.setBorder(BorderFactory.createTitledBorder("Customer Operations"));
		
		selectItemsUserBtn = new JButton("SELECT ITEMS");
		viewCartUserBtn = new JButton("VIEW CART");
		orderUserBtn = new JButton("          PLACE AN ORDER          ");
		logoutUserBtn = new JButton("LOGOUT");
		
		try {
			nameOfUser = u.getNameofUser(custUsername);
		} catch (Exception e1) {
			System.out.println("Exception : " + e1.getMessage());
		}
		
		JLabel helloUserLbl = new JLabel("Hello, " + nameOfUser, JLabel.CENTER);
		helloUserLbl.setFont(new Font("", Font.BOLD, 25));
		
		
		p1.add(helloUserLbl);
		p1.add(new JLabel(""));
		p1.add(new JLabel(""));
		p1.add(new JLabel(""));
		p1.add(selectItemsUserBtn);
		p1.add(viewCartUserBtn);
        p1.add(orderUserBtn);
        p1.add(new JLabel(""));
        p1.add(new JLabel(""));
        p1.add(new JLabel(""));
        p1.add(logoutUserBtn);
        
        try {
			wlcmLbl = new JLabel("" + u.getUserRestName(userSelectedRestID), JLabel.CENTER);
			wlcmLbl.setFont(new Font("", Font.BOLD, 50));
		} catch (Exception e1) {
			System.out.println("Exception : " + e1.getMessage());
		}
        
        panel = new JPanel(new BorderLayout());
        
        JLabel userOpLbl = new JLabel("    SELECT ITEMS", JLabel.CENTER);
        userOpLbl.setFont(new Font("", Font.BOLD, 28));
		        
        JTabbedPane itemsUserTP = new JTabbedPane();
        
        try {
			u.showItems2User(userSelectedRestID, itemsUserTP, userCartID);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
        
        panel.add(userOpLbl, BorderLayout.NORTH);
        panel.add(itemsUserTP, BorderLayout.CENTER);

        
		JPanel p3 = new JPanel(new BorderLayout());
		JPanel p4 = new JPanel();
		
		User2RMChatTA = new JTextArea(30, 25);
		User2RMChatTA.setLineWrap(true);
		User2RMChatTA.setEditable(false);
		
		User2RMChatTA.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		JScrollPane RM2UserScroll = new JScrollPane(User2RMChatTA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		User2RMMsgTF = new JTextField(16);
		chatUser2RMBtn = new JButton("SEND");
		
		p4.add(User2RMMsgTF);
		p4.add(chatUser2RMBtn);
		
		
		JPanel p5 = new JPanel();
		User2RMChatConnect = new JButton("Connect");
		
		
		p5.add(User2RMChatConnect);
		
		p3.add(p5, BorderLayout.NORTH);
		p3.add(RM2UserScroll, BorderLayout.CENTER);
		p3.add(p4, BorderLayout.SOUTH);
		
		p3.setBorder(BorderFactory.createTitledBorder("Chat With Restaurant Manager"));
		
		selectItemsUserBtn.addActionListener(this);
		viewCartUserBtn.addActionListener(this);
	    orderUserBtn.addActionListener(this);
//        chatUser2RMBtn.addActionListener(this);
        logoutUserBtn.addActionListener(this);
        User2RMChatConnect.addActionListener(this);
        
	    frame.add(p1, BorderLayout.WEST);
	    frame.add(wlcmLbl, BorderLayout.NORTH);
	    frame.add(panel, BorderLayout.CENTER);
	    frame.add(p3, BorderLayout.EAST);
	    

	    frame.setVisible(true);
	    
	}

	public void userSelectItemsFrame() {
		frame.setTitle("Customer - Food Ordering System");
		
		panel = new JPanel(new BorderLayout());
        
        JLabel userOpLbl = new JLabel("    SELECT ITEMS", JLabel.CENTER);
        userOpLbl.setFont(new Font("", Font.BOLD, 28));
		        
        JTabbedPane itemsUserTP = new JTabbedPane();
        
        try {
			u.showItems2User(userSelectedRestID, itemsUserTP, userCartID);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
        
        panel.add(userOpLbl, BorderLayout.NORTH);
        panel.add(itemsUserTP, BorderLayout.CENTER);

        
		frame.add(panel, BorderLayout.CENTER);
	    
	    frame.setVisible(true);
	    
	}

	int totalAmount = 0;
	public void userViewCartFrame() {
		frame.setTitle("Customer - Food Ordering System");
		
		panel = new JPanel(new BorderLayout());
        
        JLabel userOpLbl = new JLabel("    YOUR CART", JLabel.CENTER);
        userOpLbl.setFont(new Font("", Font.BOLD, 28));
        
        JPanel itCartP = new JPanel(new GridLayout(12, 3, 10, 10));
		
		JLabel itemNameLbl = new JLabel("Item Name", JLabel.CENTER);
		JLabel itemPriceLbl = new JLabel("Item Price", JLabel.CENTER);
		JLabel itemQtyLbl = new JLabel("Item Qty", JLabel.CENTER);
		
		itemNameLbl.setFont(new Font("", Font.BOLD, 20));
		itemPriceLbl.setFont(new Font("", Font.BOLD, 20));
		itemQtyLbl.setFont(new Font("", Font.BOLD, 20));
		
        itCartP.add(itemNameLbl);
        itCartP.add(itemPriceLbl);
        itCartP.add(itemQtyLbl);
        
        JScrollPane scroll = new JScrollPane(itCartP);
        
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
						
		scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        
        try {
        	totalAmount= u.showCart(userSelectedRestID, userCartID, itCartP);
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
		}
        
        panel.add(userOpLbl, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        
		frame.add(panel, BorderLayout.CENTER);
	    
	    frame.setVisible(true);
	    
	}
	
	JLabel payWayLbl, cardNoLbl, cardCVVLbl, cardExpLbl, amountLbl, amountToPayLbl;
	JComboBox<String> payWayCB;
	JTextField cardNoTF, cardCVVTF, cardExpTF;
	JButton orderBtn, rstOrderBtn;
	
	public void userOrderFrame() {
		frame.setTitle("Customer - Food Ordering System");
		
		panel = new JPanel(new BorderLayout());
        
        JLabel userOpLbl = new JLabel("    PLACE AN ORDER", JLabel.CENTER);
        userOpLbl.setFont(new Font("", Font.BOLD, 28));
        
        panel.setBorder(new EmptyBorder(2, 3, 2, 3));
	    layout = new JPanel(new GridBagLayout());
	    layout.setBorder(new EmptyBorder(5, 5, 50, 5));
	    	
	    
	    amountLbl = new JLabel("AMOUNT TO PAY : ", JLabel.CENTER);
	    amountLbl.setFont(new Font("", Font.BOLD, 28));
        
	    amountToPayLbl = new JLabel("Rs. " + totalAmount, JLabel.CENTER);
	    amountToPayLbl.setFont(new Font("", Font.BOLD, 28));
        
	    payWayLbl = new JLabel("SELECT PAYMENT WAY", JLabel.CENTER);
	    
	    cardNoLbl = new JLabel("CARD NO.", JLabel.CENTER);
	    cardExpLbl = new JLabel("CARD EXPIRY", JLabel.CENTER);
	    cardCVVLbl = new JLabel("CARD CVV", JLabel.CENTER);
	    
	    String payWays[] = { "Credit Card", "Debit Card", "Cash"};
	    payWayCB = new JComboBox<String>(payWays);
	    payWayCB.addItemListener(this);
	    
	    cardNoTF = new JTextField(30);
	    cardExpTF = new JTextField(30);
	    cardCVVTF = new JTextField(30);
	    
	    orderBtn = new JButton("PROCEED & PAY");
	    rstOrderBtn = new JButton("RESET");
	    
	    GridLayout gl = new GridLayout(8, 2, 10, 10);
        JPanel container = new JPanel(gl);
        
        container.add(amountLbl);
        container.add(amountToPayLbl);
        container.add(new JLabel(""));
        container.add(new JLabel(""));
        container.add(payWayLbl);
        container.add(payWayCB);
    	container.add(cardNoLbl);
        container.add(cardNoTF);
        container.add(cardExpLbl);
        container.add(cardExpTF);
        container.add(cardCVVLbl);
        container.add(cardCVVTF);
        container.add(new JLabel(""));
        container.add(new JLabel(""));
        container.add(orderBtn);
        container.add(rstOrderBtn);
     
        orderBtn.addActionListener(this);
	    rstOrderBtn.addActionListener(this);
	    
        layout.add(container);
        
        panel.add(userOpLbl, BorderLayout.NORTH);
        panel.add(layout, BorderLayout.CENTER);
    
        frame.add(panel, BorderLayout.CENTER);
	    
	    frame.setVisible(true);
	    
	}

	
	public void actionPerformed(ActionEvent e) {
		
		// mainFrame Start
		if(e.getSource() == userBtn) {

			frame.getContentPane().removeAll();
			frame.repaint();
			loginFrame();
			whoareyou = "User";
		}
		
		if(e.getSource() == rmBtn) {
			frame.getContentPane().removeAll();
			frame.repaint();
			loginFrame();
			whoareyou = "RM";
		}
		if(e.getSource() == adminBtn) {
			frame.getContentPane().removeAll();
			frame.repaint();
			loginFrame();
			whoareyou = "admin";
		}
		
		// mainFrame End
		
		// loginFrame Start
		
		if (e.getSource() == loginBtn) {
            String userText, pwdText;
            char[] pwd;
            
            userText = usernameTF.getText();
            pwd = passwordPF.getPassword();
            pwdText = String.valueOf(pwd);
            
            try {
            	
            	if(whoareyou.equals("User")) {
            		if (u.login(userText, pwdText)) {
    				    JOptionPane.showMessageDialog(frame, "Login Successful");
    				    custUsername = userText;
    					userCartID = u.getCartId(custUsername);
    				    frame.getContentPane().removeAll();
    					frame.repaint();
    					userRSFrame();
    				} else {
    				    JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
    				}
            	}
            	else if(whoareyou.equals("RM")) {
            		if (rm.loginRM(userText, pwdText)) {
    				    JOptionPane.showMessageDialog(frame, "Login Successful");
    				    frame.getContentPane().removeAll();
    					frame.repaint();
    					RMUsername = userText;
    					RMFrame();
    				} else {
    				    JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
    				}
            	}
            	else if(whoareyou.equals("admin")) {
            		if (ad.adminLogin(userText, pwdText)) {
    				    JOptionPane.showMessageDialog(frame, "Login Successful");
    				    frame.getContentPane().removeAll();
    					frame.repaint();
    					adminFrame();
    					
    				} else {
    				    JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
    				}
            	}
            	
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
 
        }

        if (e.getSource() == resetLogBtn) {
            usernameTF.setText("");
            passwordPF.setText("");
        }

        if (e.getSource() == showPasswordCB) {
            if (showPasswordCB.isSelected()) {
                passwordPF.setEchoChar((char) 0);
            } else {
                passwordPF.setEchoChar('*');
            }
        }
        
        if(e.getSource() == regBtn) {
        	if(whoareyou.equals("RM")) {
        		frame.getContentPane().removeAll();
    			frame.repaint();
    			registerFrameRM();
        	}
        	else if(whoareyou.equals("User")) {
        		frame.getContentPane().removeAll();
    			frame.repaint();
    			registerFrameUser();
        	}
        	else if(whoareyou.equals("admin")) {
        		JOptionPane.showMessageDialog(frame, "Already Registered...");
        	}
        }
        
        if (e.getSource() == goBackLogBtn) {
        	frame.getContentPane().removeAll();
			frame.repaint();
			mainFrame();
        }
        
		// loginFrame End

		// registerFrameUser Start
        
        if (e.getSource() == registerUserBtn) {
        		
       		String name = nameTF.getText();
       		long mobNo = Long.parseLong(mobNoTF.getText());
       		String address = addrTF.getText();
       		String city = cityTF.getText();
       		String username = usernameTF.getText();
       		char[] pwd = passwordPF.getPassword();
            String password = String.valueOf(pwd);
       		
    		try {
				if (u.register(name, mobNo, address, city, username, password)) {
				    JOptionPane.showMessageDialog(frame, "Registerd Successfully");
				} else {
				    JOptionPane.showMessageDialog(frame, "Please Select another Username");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
        
        if (e.getSource() == resetUserBtn) {
        	nameTF.setText("");
        	addrTF.setText("");
        	mobNoTF.setText("");
        	cityTF.setText("");
            usernameTF.setText("");
            passwordPF.setText("");
        }
        
        if (e.getSource() == goBackUserBtn) {
        	frame.getContentPane().removeAll();
			frame.repaint();
			loginFrame();
        }
        
        // registerFrameUser End
        
		// registerFrameRM Start
        
        if (e.getSource() == registerRMBtn) {
           	
        	String nameRM = nameTF.getText();
       		long mobNoRM = Long.parseLong(mobNoTF.getText());
       		String addressRM = addrTF.getText();
       		int restId = Integer.parseInt(restIDTF.getText());
       		String usernameRM = usernameTF.getText();
       		char[] pwd = passwordPF.getPassword();
            String passwordRM = String.valueOf(pwd);
            
			try {
				int x = rm.registerRM(nameRM, mobNoRM, addressRM, restId, usernameRM, passwordRM);
				
				if (x == 1) {
				    JOptionPane.showMessageDialog(frame, "Registered Successfully");
				}
	    		else if(x == 0){
				    JOptionPane.showMessageDialog(frame, "Please Select another Username");
				}
	    		else if(x == 2) {
					JOptionPane.showMessageDialog(frame, "Restaurant is not added by Admin");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
    	}
        
        
        if (e.getSource() == resetRMBtn) {
        	nameTF.setText("");
        	addrTF.setText("");
        	mobNoTF.setText("");
        	restIDTF.setText("");
            usernameTF.setText("");
            passwordPF.setText("");
        }
		
        
        if (e.getSource() == goBackRMBtn) {
        	frame.getContentPane().removeAll();
			frame.repaint();
			loginFrame();
        }
        
        // registerFrameRM End
        
        
        // adminFrame Start
        
        if(e.getSource() == addRestBtn) {
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	addRestFrame();
        }
        
        if(e.getSource() == addRestDBBtn) {
        	String restName = nameTF.getText();
        	String restAddr = addrTF.getText();
        	String restCity = cityTF.getText();
        	
        	try {
				if(ad.addRest(restName, restAddr, restCity)) {
					JOptionPane.showMessageDialog(frame, "Restaurant Added Successfully");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Restaurant not Added... Please Try again Later...");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
        
        if(e.getSource() == rstAddRestDBBtn) {
        	nameTF.setText("");
        	addrTF.setText("");
        	cityTF.setText("");            
        }
        
        if(e.getSource() == delRestBtn) {
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	delRestFrame();
        }
        
        if(e.getSource() == delRestDBBtn) {
        	int restID = Integer.parseInt(restIDTF.getText());
        	
        	try {
				if(ad.delRest(restID)) {
					JOptionPane.showMessageDialog(frame, "Restaurant Deleted Successfully");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Please Enter the Valid Restaurant ID.");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
        
        if(e.getSource() == rstDelRestDBBtn) {
        	restIDTF.setText("");            
        }
        
        if(e.getSource() == dispRestBtn) {
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	dispRestFrame();
        }
        
        if(e.getSource() == logoutAdminBtn) {
        	frame.getContentPane().removeAll();
        	frame.repaint();
        	loginFrame();
        }
		
        if(e.getSource() == adminChatServerOn) {
        	try {
        		ad.chat2rm(admin2RMChatTA, chatRMBtn, adminMsgTF);
        	}
        	catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
        
        // adminFrame End
        
        // RMFrame Start
        if(e.getSource() == catBtn) {
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	catRMFrame();
        }
        
        if(e.getSource() == addCatBtn) {
        	String cat = catNameTF.getText();
        	
        	try {
				if(rm.addCategory(cat, rm.getRestId(RMUsername))) {
					JOptionPane.showMessageDialog(frame, "Category Added Successfully");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Unable to add Category");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        	
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	catRMFrame();
        }
        
        if(e.getSource() == delCatBtn) {
        	int catID = Integer.parseInt(catIDTF.getText());
        	
        	try {
        		int x = rm.delCategory(catID, rm.getRestId(RMUsername));
				if(x == 1) {
					JOptionPane.showMessageDialog(frame, "Category Deleted Successfully");
				}
				else if(x == 0) {
					JOptionPane.showMessageDialog(frame, "Unable to add Category");
				}
				else if(x == -1) {
					JOptionPane.showMessageDialog(frame, "Please Enter the Valid Category ID");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        	
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	catRMFrame();
        }
        
        if(e.getSource() == itemsBtn) {
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	itemsRMFrame();
        }
        
        if(e.getSource() == addItemBtn) {
        	
        	String itemName = itemNameAddTF.getText();
        	float itemPrice = Float.parseFloat(itemPriceAddTF.getText());
        	
        	String str = (String) itemCatCB.getSelectedItem();
        	
        	// Replacing every non-digit number 
            // with a space(" ")
        	str = str.replaceAll("[^\\d]", " "); 
        	  
            // Remove extra spaces from the beginning 
            // and the ending of the string 
            str = str.trim(); 
            
            int catID = Integer.parseInt(str);
            
            try {
				if(rm.addItems(rm.getRestId(RMUsername), catID, itemName, itemPrice)) {
					JOptionPane.showMessageDialog(frame, "Item Added Successfully");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Unable to Add Item");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
            
            frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	itemsRMFrame();
        }
        
        if(e.getSource() == delItemBtn) {
        	
        	int itemID = Integer.parseInt(itemIDDelTF.getText());
        	
        	try {
        		int x = rm.delItems(itemID);
				if(x == 1) {
					JOptionPane.showMessageDialog(frame, "Item Deleted Successfully");
				}
				else if(x == -1) {
					JOptionPane.showMessageDialog(frame, "Please... Enter the Valid Item ID");
				}
				else if(x == 0) {
					JOptionPane.showMessageDialog(frame, "Unable to Delete Item");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
            
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	itemsRMFrame();
        }
        
        if(e.getSource() == editItemBtn) {
        	
        	int itemID = Integer.parseInt(itemIDEditTF.getText());
        	String itemName = itemNameEditTF.getText();
        	float itemPrice = Float.parseFloat(itemPriceEditTF.getText());
        	
        	try {
        		if(rm.editItems(itemID, itemName, itemPrice)) {
					JOptionPane.showMessageDialog(frame, "Item Updated Successfully");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Unable to Update Item");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	itemsRMFrame();
        }
        
        if(e.getSource() == viewOrdersBtn) {
        	frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	viewOrdersRMFrame();
        }
        
        if(e.getSource() == showUndelOrderBtn) {
       		deliStatus = "N";
			frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	viewOrdersRMFrame();
        }
        
		if(e.getSource() == showDelOrderBtn) {
			deliStatus = "Y";
			frame.getContentPane().remove(p2);
        	frame.getContentPane().remove(panel);
        	frame.repaint();
        	viewOrdersRMFrame();
		}
		
		if(e.getSource() == changeDelStatusBtn) {
			try {
				int ordId = Integer.parseInt(changeDelStatusTF.getText());
				
				rm.changeDeliveryStatus(ordId);
				frame.getContentPane().remove(p2);
	        	frame.getContentPane().remove(panel);
	        	frame.repaint();
	        	viewOrdersRMFrame();

			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
		}
        
        if(e.getSource() == logoutRMBtn) {
        	frame.getContentPane().removeAll();
        	frame.repaint();
        	loginFrame();
        }
        
        if(e.getSource() == RM2AdminChatOn) {
        	try {
        		rm.RM2AdminChat(RM2AdminChatTA, RM2AdminMsgTF, chatRM2AdminBtn, RMRestName);
        	}
        	catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }

        if(e.getSource() == RM2UserChatServerOn) {
        	try {
        		rm.startServer(3355);
        	}
        	catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
        
        if(e.getSource() == RM2UserAddClient) {
        	rm.addClient2Chat(RM2UserChatTA, chatRM2UserBtn, RM2UserMsgTF, RMRestName);
        }
        
        // RMFrame End
        
        // userFrame Start
        if(e.getSource() == restSelectUserBtn) {
        	userSelectedRestID = Integer.parseInt(userRestBG.getSelection().getActionCommand());
        	frame.getContentPane().removeAll();
			frame.repaint();
			userFrame();
        }
        
        if(e.getSource() == selectItemsUserBtn ) {
        	frame.getContentPane().remove(panel);
			frame.repaint();
			userSelectItemsFrame();
        }
        
        if(e.getSource() == viewCartUserBtn) {
        	frame.getContentPane().remove(panel);
			frame.repaint();
			userViewCartFrame();
        }
        
        if(e.getSource() == orderUserBtn) {
        	try {
				if(u.checkPrevOrder(userCartID)) {
					JOptionPane.showMessageDialog(frame, "You cannot place an Order until your Previous Orders gets Delivered...");
				}
				else {
					frame.getContentPane().remove(panel);
					frame.repaint();
					userOrderFrame();
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
        
        
        if(e.getSource() == orderBtn) {
        	String payWay = "" + payWayCB.getSelectedItem();
        	try {
				if(u.setOrders(custUsername, userCartID, userSelectedRestID, payWay, totalAmount)) {
					payWayCB.setSelectedItem("Credit Card");
					cardNoTF.setText("");
		        	cardExpTF.setText("");
		        	cardCVVTF.setText("");
		        	JOptionPane.showMessageDialog(frame, "Ordered Successfully...");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Unable to Order Now... Please Try Again Later");
				}
			} catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
        
        if(e.getSource() == rstOrderBtn) {
        	payWayCB.setSelectedItem("Credit Card");
        	cardNoTF.setText("");
        	cardExpTF.setText("");
        	cardCVVTF.setText("");
        }
        
        
        if(e.getSource() == User2RMChatConnect) {
        	try {
        		u.chat2rm(nameOfUser, User2RMChatTA, chatUser2RMBtn, User2RMMsgTF);
        	}
        	catch (Exception e1) {
				System.out.println("Exception : " + e1.getMessage());
			}
        }
                
        if(e.getSource() == logoutUserBtn) {
        	frame.getContentPane().removeAll();
        	frame.repaint();
        	loginFrame();
        }
        // userFram End
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == payWayCB) {
			if(payWayCB.getSelectedItem().equals("Cash")) {
				cardNoTF.setEditable(false);
				cardExpTF.setEditable(false);
				cardCVVTF.setEditable(false);
			}
			else 
			{
				cardNoTF.setEditable(true);
				cardExpTF.setEditable(true);
				cardCVVTF.setEditable(true);
			}
		}
	}
	
	public static void main(String[] args)throws Exception {
		Main fos = new Main();
		fos.mainFrame();
	}
}