package src.images;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class enemyManager{
	ArrayList<enemy> enemyList = new ArrayList<enemy>();

	Game game;
	private int enStartingYPos = 0;
	private int enStartingXPos = 0;
	private int enemyNumber = 15;
	private int startingEnemyNumber = 15;
	
	private Random r = new Random();;
	private Players p;
	
	public enemyManager(Game game)
	{	
		this.game = game;
		createEnemy();
		this.p = this.game.getPlayer(); 
	}
	public void createEnemy()
	{	
		for(int i=0; i < enemyNumber; i++)
		{
			enStartingYPos = r.nextInt(1000-10) + 10;
			enemy tempEm = new enemy(enStartingXPos, -enStartingYPos, game);
			enStartingXPos = r.nextInt(640 - 2* tempEm.getWidth()) + tempEm.getWidth();
			enemyList.add(tempEm);
		}
	}
	
	//updates enemy position 
	//checks for collision
	public void update()
	{
		for(int i =0; i < enemyList.size(); i++)
		{
			enemy tempEnemy = enemyList.get(i);
			tempEnemy.move();
			if(checkCollsion(tempEnemy)){
				enemyList.remove(i);
			}
			
			enStartingYPos = r.nextInt(1000-10) + 10;
			if(tempEnemy.getY() > game.getHeight())
			{
				enemyList.get(i).setY(-enStartingYPos);
			}	
		}
	}

	public void render(Graphics g)
	{
		for(int i =0; i < enemyList.size(); i++)
		{
			enemy en = enemyList.get(i);
			en.render(g);
		}

	}
	public void removeEnemy(enemy b){
		enemyList.remove(b);
	}
	public void addEnemy(enemy b){
		enemyList.add(b);
	}
	public ArrayList<enemy> getEnemyList() {
		// TODO Auto-generated method stub
		return enemyList;
	}

	public void setEnemyKilled(){
		enemyNumber--;
	}
	public int getEnemyNumber() {
		// TODO Auto-generated method stub
		return enemyNumber;
	}
	public void setEnemyNumber(int n) {
		// TODO Auto-generated method stub
		enemyNumber = n;
	}

	public boolean checkCollsion(enemy e){

		//bullet bound and enemy bounds are same 
		//that means they are colliding 
		if(e.getBounds().intersects(p.getBounds())){
			setEnemyKilled();
			System.out.println("Enemy NUmber: " + getEnemyNumber());
			p.reduceHealth();
			System.out.println("Health bar " + p.gethealthBar());
			return true;
		}

		return false;
	}
	public int getStartingEnemyNumber() {
		// TODO Auto-generated method stub
		return startingEnemyNumber;
	}

}
