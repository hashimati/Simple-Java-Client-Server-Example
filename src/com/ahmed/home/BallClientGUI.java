package com.ahmed.home;

/* Programmed By Ahmed Al Hashmi
 * E-Mail: hashimati.ahmed@gmail.com
 * 
*/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.net.Socket;


public class BallClientGUI extends JFrame {

	private JPanel contentPane;
	private JTextField host;
	private JTextField portField;
	Socket socket = null; 
	boolean connected = false; 
	PrintWriter writer = null;  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BallClientGUI frame = new BallClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BallClientGUI() {
		setTitle("Ball Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 227);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		host = new JTextField();
		host.setText("127.0.0.1");
		host.setBounds(58, 27, 262, 20);
		contentPane.add(host);
		host.setColumns(10);
		
		portField = new JTextField();
		portField.setText("9444");
		portField.setBounds(58, 58, 105, 20);
		contentPane.add(portField);
		portField.setColumns(10);
		
		JLabel lblHost = new JLabel("Host:");
		lblHost.setBounds(10, 30, 46, 14);
		contentPane.add(lblHost);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(10, 61, 46, 14);
		contentPane.add(lblPort);
		
		final JLabel lblOffline = new JLabel("Offline");
		lblOffline.setForeground(Color.RED);
		lblOffline.setBounds(58, 130, 89, 14);
		contentPane.add(lblOffline);
		final JLabel state = lblOffline; 
		
	final JButton add = new JButton("Add Ball");
		final JButton aB = add; 
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					writer = new PrintWriter(socket.getOutputStream(), true);
					writer.println("Add");
					writer.flush(); 
					
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex.getMessage() ); 
					ex.printStackTrace();
				}
			
			}
		});
		add.setBounds(58, 157, 89, 23);
		contentPane.add(add);
		add.setEnabled(false); 
		
		final JButton btnConnect = new JButton("Connect");
		final JButton conb =btnConnect;  
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(connected == false){
					try{
						socket = new Socket(host.getText(), Integer.parseInt(portField.getText()));
						if(socket.isConnected())
							JOptionPane.showMessageDialog(null, "Connected"); 
						btnConnect.setText("Disconnect"); 
						connected = true; 
						lblOffline.setForeground(Color.magenta);
						lblOffline.setText("Connected");
						aB.setEnabled(true); 
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex.getMessage() ); 
						ex.printStackTrace(); 
						
					}
				}
				else if(connected == true)
				{
					if(socket.isConnected())
					{
						try{
							socket.close(); 
							connected = false; 
							btnConnect.setText("Connect"); 
							lblOffline.setForeground(Color.red); 
							lblOffline.setText("Offline"); 
							aB.setEnabled(false); 
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, ex.getMessage() ); 

							ex.printStackTrace(); 
							
						}
					}
					
				}
			}
		});
		btnConnect.setBounds(58, 89, 105, 23);
		contentPane.add(btnConnect);
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(10, 130, 46, 14);
		contentPane.add(lblStatus);
	}
}
