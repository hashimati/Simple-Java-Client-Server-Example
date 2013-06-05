package com.ahmed.home;
/* Programmed By Ahmed Al Hashmi
 * E-Mail: hashimati.ahmed@gmail.com
 * 
*/
import java.net.*; 
import java.awt.Color;
import java.io.*; 
import java.util.*; 

import javax.sql.rowset.spi.SyncResolver;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;

public class BallConnectionHandler implements Runnable{

	Socket socket = null; 
	ArrayList<Balls> list; 
	int width, height;  
	public BallConnectionHandler(Socket socket, ArrayList<Balls> list, int w, int h)
	{
		width = w; 
		height = h; 
		
		this.socket = socket;
		this.list = list; 
		
		
	}
	@Override
	public void run()
	{
		Random r = new Random(); 
		try{
			InputStream stream = socket.getInputStream(); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream)); 
				String line; 
				while(true){
				line = reader.readLine();
				if(line.equals("quit")) 
					break; 
				else {
					synchronized (list) {
						
					
					list.add(new Balls(r.nextInt(width - 50), r.nextInt(height - 50), 50, width, height, 
							new Color(r.nextInt(254),r.nextInt(254),r.nextInt(254))));
					
					}
				}
				System.out.println(line); 
				}
				socket.close();
				System.out.println("Disconnected bye"); 
			}
			catch(Exception ex)
			{
				
				 
			}
		
	}


}
