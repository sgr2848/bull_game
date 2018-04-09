

package src.images;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


//import java.awt.Graphics;
//import acm.graphics.*;;

public class Bullet extends GameEntity{
	
	private final static double bullet_speed = 7;
	

	//dimensions of bullet
	private final static int x_dimension = 42;
	private final static int y_dimension = 51;
	
	BufferedImage bImage = null;
	
	enemyManager em;
	
	bulletManager bm;
	
	Players p;
	
	Game game;
	/*
	 * check for the collision with any other entity present on the top 
	 * z-level component on that location
	 */
	public Bullet(int x, int y, Game game)
	{
		super(x,y);
		
		//load image
		try {
			bImage = ImageIO.read( getClass().getResource("images/fireball.gif") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		this.game = game;
		em = this.game.getEnemyManager();
		p = this.game.getPlayer();
	}
	
	//return the rectagular bound of bullet 
	public Rectangle getBounds(){
          return new Rectangle((int) x,(int) y, x_dimension, y_dimension);
	}
	
	//move bullet upward direction
	public void move() {
		// TODO Auto-generated method stub
		y -= bullet_speed;
	}

	boolean checkCollision(Bullet b) {
		// TODO Auto-generated method stub
		ArrayList<enemy>el = em.getEnemyList();
		for(int i = 0; i < el.size(); i++)
		{
			//bullet bound and enemy bounds are same 
			//that means they are colliding 
			if(this.getBounds().intersects(el.get(i).getBounds())){
				el.remove(i);
				em.setEnemyKilled();
				if(!game.getStatus()){
					p.setScore();
				}
				
				return true;
			}
		}
		return false;
	}
	
	//draw bullet on bufferImage or in canvas
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(bImage, x, y, null);
	}
	
}
