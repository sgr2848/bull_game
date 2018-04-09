package src.images;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;


public class enemy extends GameEntity{
	private static final int HEIGHT = 40;

	private static final int WIDTH = 42;
	
	private int enVelocity = 5;
	
	BufferedImage enImage = null;
	
	public enemy(int x, int y, Game game){
		//call GameEntity constructor 
		//which will set locations
		super(x,y);
		
		//load images
		try {
			enImage = ImageIO.read( getClass().getResource("images/en.png") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int) x,(int) y, WIDTH, HEIGHT);
	}
	
	public void move(){
		y += enVelocity;     
	}
	
	public void render(Graphics g){
		g.drawImage(enImage, x, y, null);
	}
	
	public   int getHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}
	public int getVel(){
		return enVelocity;
	}
	public void setVel(int n){
		enVelocity = n;
	}
}
