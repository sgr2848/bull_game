package src.images;
/*
 * player class
 * it extends the gameEntity Class
 * creates player and allow user to move 
 * - every time enemy hits the player it will decrease the life of player
 * - our player will have bufferedimage
 */

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Players extends GameEntity{
	
	//dimensions // class variable
	private static final int  WIDTH = 64;
	private static final int HEIGHT = 64;
	//starting location of player
	private static final int x_loc = 640/2;
	private static final int y_loc = 416;
	
	// instance variable to hold the value of 
	//score, ScorePoint for different level
	//starting health and health thats over time
	private int score = 0;
	private int ScorePoint = 1;
	private int healthBar = 5;
	

	enemyManager em; //object of enemy manager
	BufferedImage playerImg = null;//image variable for player
	Game game; //instance of game . this will hold the instance of running game
	
	/*
	 * player constructor with parameter of Game Object
	 * it will set the location player by calling constructor of super class(gameEntity Class)
	 * load image for player
	 */
	public Players(Game game) {
		//call constructor of GameObject class 
		super(x_loc, y_loc);
		
		//load image for player throws io exception
		try {
			playerImg = ImageIO.read(getClass().getResource("images/player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.game = game; //assign game 
		//get instance of enemyManger from game class
		this.em = this.game.getEnemyManager();
		
	}
	/*
	 * return the height of player
	 */
	public static  int getHeight() {
		// TODO Auto-generated method stub
		return HEIGHT;
	}
	
	//returns the width of player
	public int getWidth() {
		// TODO Auto-generated method stub
		return WIDTH;
	}
	/*
	 * returns the Rectangle object with dimension of player
	 * this will be used when checking for collision
	 */
	public Rectangle getBounds(){
        return new Rectangle( x,y, WIDTH, HEIGHT);
	}
	
	/*
	 * check for player update
	 * moves player and restrict the player movement inside window
	 */
	public void update() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		checkOffScreen();
		
	}
	/*
	 * method that restrict the player movement inside window
	 * even though key is pressed to move, player will not move beyond window
	 */
	public void checkOffScreen()
	{
		//if the new x location is less than zero then set it to zero
		if(x <= 0) 
			x =0;
		//if the new x location is greater than window width - player width
		//then set to window width - player width
		if(x >= game.getWidth()- WIDTH)
			x = game.getWidth()- WIDTH;
		
		//if the new Y location is less than zero then set it to zero
		if(y <= 0)
			y =0;
		//if the new y location is greater than window height - player height
				//then set to window height - player height
		if(y >= game.getHeight()- HEIGHT)
			y =game.getHeight()- HEIGHT;
				
		
	}
	
	/*
	 * this method will draw player on screen
	 */
	public void drawPlayer(Graphics g)
	{
		g.drawImage(playerImg, x, y, null);
		
	}
	
	/*
	 * reduced the health bar when player is hit by enemy 
	 * health bar will not be reduced beyond zero
	 */
	public void reduceHealth(){
		if(healthBar != 0){
			healthBar--;
		}else{
			healthBar = 0;
		}
		
	}
	//returns the current healthbar of player
	public int gethealthBar() {
		// TODO Auto-generated method stub
		return healthBar;
	}

	//returns the current score
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	
	//sets score when bullet hits the enemy
	public void setScore() {
		// TODO Auto-generated method stub
		score += ScorePoint;
	}
	
	/*
	 * score points are how much points player get score when bullet hits the enemy
	 * sets the Score points for level
	 * starting Score points is 1 
	 */
	public void setScorePoints(int n) {
		// TODO Auto-generated method stub
		ScorePoint += n;
	}
	
	//returns the current Score points
	public int  getScorePoints() {
		// TODO Auto-generated method stub
		return ScorePoint;
	}

}
