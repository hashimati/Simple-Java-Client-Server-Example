
package com.ahmed.home;

/* Programmed By Ahmed Al Hashmi
 * E-Mail: hashimati.ahmed@gmail.com
 * 
*/
import java.net.*; 
import java.io.*; 
import java.util.*; 

import javax.swing.JOptionPane;
public class BallServer implements Runnable
{
	ServerSocket server = null; 
	Socket client = null; 
	int width, height; 
	ArrayList<Balls> list = null; 
	public BallServer(ArrayList<Balls> list, int width, int height)
	{
		
		this.list = list; 
		this.width = width;  
		this.height = height; 
	}
	public void update(int w, int h)
	{
		width = w; 
		height = h; 
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try{
			ServerSocket server = new ServerSocket(9444); 
			while(true){
			Socket client = server.accept(); 
				new Thread(new BallConnectionHandler(client, list, width, height)).start(); 
			}
		}		
		catch(Exception ex)
		{
			
		}	
	}
}