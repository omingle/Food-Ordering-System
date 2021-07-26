package restaurants;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ServerThread implements Runnable, ActionListener
{
	JTextArea area = null;
	private Socket socket = null;
	JButton button;
	JTextField field;
	BufferedReader reader = null;
	PrintWriter writer = null;
	BufferedReader console = null;
	int ID;
	int fg;
	String restName;
	
	public ServerThread(Socket s, JTextArea a, JButton btn, JTextField tf, int i, String rMRestName)
	{
		socket = s;
		area = a;
		button = btn;
		field = tf;
		ID = i;
		restName = rMRestName;
		button.addActionListener(this);
	}
	
	public void actionPerformed (ActionEvent event)
	{
		fg = 0;
	}
	public void run()
	{
		try {
			reader = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			writer = new PrintWriter (socket.getOutputStream(), true);
			console = new BufferedReader(new InputStreamReader(System.in));
		}
		catch(Exception e) {}
		
		boolean flag = true;
		while (flag == true)
		{
			try
			{
				String line = reader.readLine();
				area.append ("\n" + line + "\n");
				fg = 1;
				while(fg == 1) {}
				
				writer.println (restName + "'s Manager : " + field.getText());
				area.append ("You : " + field.getText());
				field.setText("");
			}
			catch (Exception e)
			{}
		}
	}
	
}
