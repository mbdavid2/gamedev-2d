package util;

public class Player extends GameObject {
	public Player() {  
		
	}
	
    public Player(String textureLocation,int width,int height,Point3f centre) { 
    	super(textureLocation, width, height, centre);
	}
    
    public boolean isColliding(GameObject lowerLevel, GameObject upperLevel)  {
    	return this.getCentre().getY() < 400;
    }
}
