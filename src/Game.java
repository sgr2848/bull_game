/*
 *Created By:	Pratap Gurung
 *				Sagar Shrestha 
 *				Bishant tripathi
 *
 *Description: 	game.java is main class. In this class we will create our game loop
 *				and inside game loop we will update every entities and draw entities
 *				on screen.
 *				It extends Canvas to draw and implements runnable to run the game which will start thread
 *				
 *				In our game, enemies will fall from top of the window and when the enemy hits the player, 
 *				reduce the health of player by one bar
 *				
 *				player can move inside any location of window and shoots infinite amount of bullet.
 *				when bullet hits the enemy it will increase the score by one at the beginning
 *
 *				At first number of enemy is 15 but once player destroy all enemy, new increase
 *				 number of enemy will be produced and ScorePoint for each enemy will be increase by 1
 *
 *				
 *				
 */
package src.images;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * 
 */

public class Game extends Canvas implements Runnable {
	

	//dimension for frame
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	//instance of threat
	private Thread thread;
	Players hero;

	//variable to check status of game
	// used to stop the thread 
	private boolean running = false;   

	//instance of bullet manager
	private bulletManager bm;
	//instance of graphics to draw
	Graphics g;
	
	//instance of enemy manager
	enemyManager enemy;

	//insatnce of bufferedImage, this will hold the image for background
	private BufferedImage image = null;

	//to calculate the message dimension
	private FontMetrics fontSize;
	//access font style size using font class
	private Font msgsFont;

	//same as gameOver bt this will be true if the player health bar is zero
	private boolean gameOver = false;

	//game constructor 
	public Game(){
		//set canvas focusable
		this.setFocusable(true);  
		
		//requestfocus will notify the game if there is any key or mouse event
		this.requestFocus();
		//new GameFrame(WIDTH,HEIGHT,"Silver Sufer", this);

		//our hero, instance of players class
		hero = new Players(this);	
		
		//enemy manager this will create and manage the enemy
		enemy = new enemyManager(this);

		//bullet manager this will creat and manage the bullet
		bm = new bulletManager(this);

		//keylistener this will check if any key is pressed
		addKeyListener(new KeyInput(this));

		//load images for background for buffer image
		try {
			image = ImageIO.read(getClass().getResource("images/bgImage.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set up message font
		msgsFont = new Font("SansSerif", Font.BOLD, 46);
		fontSize = this.getFontMetrics(msgsFont);
	}
	
	/*
	 * start the thread 
	 * runs the game
	 */
	public  void start(){
		//if runnning is true i.e game is already started 
		// so return 
		if(running)
			return;
		
		//since game is not started yet
		//so running needs to be true
		running = true;
		//creates new thread 
		thread = new Thread(this);
		//starts the thread
		thread.start();

	}
	//stop the game
	//stops the thread
	public  void stop(){
		//if the game is already stopped then return
		if(!running)
			return;
		//sets running to false
		running = false;
		try{
			//waits for the completion of other thread
			thread.join();

		}catch(Exception e){
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	//our game run method
	public void run()
	/* The frames of the animation are drawn inside the while loop. */
	{
		//thread start time
		long lastTime = System.nanoTime();
		
		//amount of ticks i.e number of updates each game loop will have 
		double numberOfTicks = 60.0;
		
		//amount of nano time for each frame or ticks  
		double ns = 1000000000 / numberOfTicks;
		
		//variable to catch 
		double delta = 0;

		//game loop until the thread is stopped
		while(running){
			//current of time in nano seconds
			long now = System.nanoTime();
			
			//amount of time cpu takesfrom line 149 to 162 in nano seconds 
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			//update game 60 times 
			while(delta >= 1){
				//updates game 
				gameUpdate();
				delta--;
			}

			//render or draw game entities on canvas
			gameRender();    
		}
		stop();
	} // end of run()

	/*
	 * this method will update every game entities 
	 * will check if all enemy has been killed then creates increased number of enemy by 5
	 */
	private void gameUpdate() {
		// TODO Auto-generated method stub
		//update game for each loop of run
		hero.update();
		enemy.update();
		bm.update();
		
		//if all enemy is killed start new level with increased number of enemy and Score Points
		if(enemy.getEnemyNumber() <= 0){
			hero.setScorePoints(hero.getScorePoints()+1);
			enemy.setEnemyNumber(enemy.getStartingEnemyNumber()+5);
			enemy.createEnemy();
		}

	}

	/*
	 * game Update just updates the game but 
	 * this method will display the updated game entities on window with 3 buffer strategy
	 */
	private void gameRender(){
		//create buffer strategy
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}    
		
		//  
		g = bs.getDrawGraphics();
		g.drawImage(image, 0,0,getWidth(), getHeight(), this);

		//////////////////////////

		///everything goes here for rendering

		hero.drawPlayer(g);
		enemy.render(g);
		bm.render(g);

		displayScore(g);
		if(hero.gethealthBar() <= 0){
			gameOver = true;
			gameOverMessage(g);
		}
		/////////////////
		g.dispose();
		bs.show();
	}
	
	public bulletManager getBm() {
		// TODO Auto-generated method stub
		return bm;
	}
	public enemyManager getEnemyManager() {
		// TODO Auto-generated method stub
		return enemy;
	}  

	public Players getPlayer()
	{
		return hero;
	}

	
	/*
	 * game over message
	 * it will display this messegge when game is over
	 */
	private void gameOverMessage(Graphics g)
	//display the display the game over message 
	{
		String msg = "Game Over." + " Your score: " + hero.getScore();
		//int x = (this.getHeight() - fontSize.stringWidth(msg))/2;
		int y = (this.getHeight() - fontSize.getHeight())/2;

		g.setColor(Color.white);
		g.setFont(msgsFont);
		g.drawString(msg, 190, y);
	}  // end of gameOverMessage()

	public boolean getStatus(){
		return gameOver;
	}

	private void displayScore(Graphics g)
	//display the score one screen
	{
		if (true)  
			g.setColor(Color.red);
		msgsFont = new Font("Time New Roman", Font.BOLD, 24);
		g.setFont(msgsFont);
		g.drawString("Score: " + hero.getScore(), 15, 50);
		g.drawString("Life Remaining:  " + hero.gethealthBar(), 15, 25);
		g.setColor(Color.black);
	}  // end of reportStats()

		
	public static void main(String args[])
	{  
		Game game = new Game(); 
		JFrame frame = new JFrame();
		frame.setTitle("Gaurdian Of Galaxy");
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));             
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

}