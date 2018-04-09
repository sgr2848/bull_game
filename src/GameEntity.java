package src.images;
/*
 * GameEntity class
 * super class for player, bullet and enemy
 */
public class GameEntity {
	//location of entity
	//protected so that class from same package can access restricting outside package
	protected int x,y;
	//velocity of 
    protected int velX = 0, velY = 0;
	
    /*
     * constructor
     * sets location of entity
     */
    public GameEntity(int x, int y){
            this.x = x;
            this.y = y;
    }   
    //sets x location
    public void setX(int x){
            this.x = x;
    }
    //sets y location
    public void setY(int y){
            this.y = y;
    }
    //get x location
    public int getX(){
            return x;
    }
    //gets y location
    public int getY(){
            return y;
    }
    
    //sets x velocity of enity
    public void setVelX(int velX){
           this.velX = velX;
    }
  //sets y velocity of enity
    public void setVelY(int velY){
            this.velY = velY;
    }
   
}
