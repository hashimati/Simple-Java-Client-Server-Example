package com.ahmed.home;

import java.awt.*; 
import java.awt.geom.*;

public class Balls {
	
	int x, y,r; 
	int width, height, incX = 1, incY = 1; 
	Color color; 
	public Balls(int x, int y, int r, int width, int height, Color color)
	{
			this.x =x; 
			this.y = y; 
			this.r = r; 
			this.width = width; 
			this.height = height; 
			
			this.color = new Color(color.getRGB()); 
	}
	
	
	public void drawMe(Graphics g)
	{
			
			g.setColor(color); 
			g.fillOval(x, y, r, r);
			g.setColor(Color.black); 
			g.drawOval(x, y, r, r); 
	}
	
	public void update(int width, int height)
	{

		if(x == 0 )
			incX =1;
		else if(x == width - 60)
			incX = -1; 
		
		if(y == 0)
			incY = 1;
		else if(y == height - 65) 
			incY = -1;
		x += incX; 
		y += incY; 
		
		
		
		
	}
}
