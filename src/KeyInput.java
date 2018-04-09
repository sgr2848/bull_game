package src.images;
/*
 * KeyInput class
 * this class will control the player movements and shoot bullet
 * extends keyAdapter
 *
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class KeyInput extends KeyAdapter{
	
	Players hero;
	Game game;
	bulletManager bm;
	/*
	 * keyInput Constructor
	 * input : Instance of game class
	 */
	public KeyInput(Game game2) {
		// TODO Auto-generated constructor stub
		//access the current game
		this.game = game2;
		
		//access instance of player from current game
		this.hero = this.game.getPlayer();
		
		//access instance of current bullet manager from game
		this.bm = this.game.getBm();
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 * sets the value of velocity of player when key is pressed
	 * shoot bullet from player when space is pressed by user
	 * 
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();      

			if(key == KeyEvent.VK_UP) { hero.setVelY(-7);  }
			if(key == KeyEvent.VK_DOWN) { hero.setVelY(7); }
			if(key == KeyEvent.VK_RIGHT) { hero.setVelX(7);  }
			if(key == KeyEvent.VK_LEFT) { hero.setVelX(-7); }
			if(key == KeyEvent.VK_SPACE) { bm.addBullet(new Bullet(hero.getX() +
						hero.getWidth()/2-5, hero.getY()-15, game));}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
	 * sets velocity of player to zero
	 */
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();      
		if(key == KeyEvent.VK_UP) { hero.setVelY(0);  }
		if(key == KeyEvent.VK_DOWN) { hero.setVelY(0); }
		if(key == KeyEvent.VK_RIGHT) { hero.setVelX(0);  }
		if(key == KeyEvent.VK_LEFT) { hero.setVelX(0); }
	}
	
}