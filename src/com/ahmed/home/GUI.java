package com.ahmed.home;


/* Programmed By Ahmed Al Hashmi
 * E-Mail: hashimati.ahmed@gmail.com
 * 
*/
import java.util.*; 
import javax.swing.*; 
import java.awt.*; 
import java.io.*; 

public class GUI extends JFrame
{
	ArrayList<Balls> ballsList = new ArrayList<Balls>(); 
	JPanel panel; 
	final JFrame me = this; 
	public GUI()
	{
		this.setTitle("Balls Server"); 
		setSize(800, 500); 
		panel = new JPanel(true){
			
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g); 
				this.setSize(me.getWidth(), me.getHeight()); 
				setBackground(Color.white); 
				
				if(!ballsList.isEmpty())
				{
					Iterator<Balls> iter = ballsList.iterator(); 
					while(iter.hasNext())
					{
						Balls b = iter.next(); 
						b.drawMe(g); 
						repaint(); 
						b.update(me.getWidth(), me.getHeight()); 
					}
				}
				try
				{
					
					Thread.sleep(5);
					if(this.isPaintingForPrint() || !this.isPaintingForPrint())
						repaint(); 
					
					repaint(); 
				
				}
				catch(Exception ex)
				{}
	
			}
		};  
		add(panel); 
		setVisible(true); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		new Thread(new BallServer(ballsList, this.getWidth(), this.getHeight())).start(); 
	}
	public void addBall()
	{
		Random r = new Random(); 
		ballsList.add(new Balls(r.nextInt(this.getWidth() - 50), r.nextInt(this.getHeight() - 50), 50, this.getWidth(), this.getHeight(), 
				new Color(r.nextInt(254),r.nextInt(254),r.nextInt(254)))); 
	}
	public static void main(String... args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new GUI(); 
			}
		}
		); 
	}
}
