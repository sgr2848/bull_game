package src.images;

import java.awt.Graphics;
import java.util.ArrayList;

public class bulletManager {
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	Bullet bullet;
	Game game;
	enemyManager em;
	public bulletManager(Game game)
	{
		this.game = game;
		em = new enemyManager(this.game);
	}
	public void update()
	{
		for(int i =0; i < bulletList.size(); i++)
		{
			Bullet b = bulletList.get(i);
			b.move();	
			if(b.getY() < 0 || b.checkCollision(b))
			{
				removeBullet(bulletList.get(i));
			}
			
		}
	}

	public void render(Graphics g)
	{
		for(int i =0; i < bulletList.size(); i++)
		{
			bullet = bulletList.get(i);
			bullet.render(g);
		//	checkBullet(bullet);
		}
	}
	
	public void addBullet(Bullet b){
		bulletList.add(b);
	}
	public void removeBullet(Bullet b){
		bulletList.remove(b);
	}
}
