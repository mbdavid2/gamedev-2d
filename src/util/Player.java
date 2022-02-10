package util;
import mvc.Controller;

public class Player extends GameObject {
	private boolean canJumpAgain = true;
	private int movingDirection = 0; // -1 left, 0 still, 1 right
	private int lastMovingDirection = 1;
	private int jumpingIterations = 0;
	 
	public Player() {  
		
	}
	
    public Player(String textureLocation,int width,int height,Point3f centre) { 
    	super(textureLocation, width, height, centre);
	}
    
    public boolean isColliding(GameObject lowerLevel, GameObject upperLevel)  {
    	return this.getCentre().getY() < 525;
    }
    
    public int getMovementDirection() {
		return movingDirection;
	}
	
	public int getLastMovingDirection() {
		return lastMovingDirection;
	}
    
    public void playerLogic(GameObject levelLower, GameObject levelUpper) {
		
		// smoother animation is possible if we make a target position  // done but may try to change things for students  
		 
		//check for movement and if you fired a bullet 
		// Implement gravity
		movingDirection = 0;
		if(Controller.getInstance().isKeyAPressed()){
			movingDirection = -1;
			this.getCentre().ApplyVector( new Vector3f(-3,0,0)); 
			lastMovingDirection = -1;
		}
		
		if(Controller.getInstance().isKeyDPressed())
		{
			movingDirection = 1;
			this.getCentre().ApplyVector( new Vector3f(3,0,0));
			lastMovingDirection = 1;
		}

		/*if(Controller.getInstance().isKeyWPressed())
		{
			Player.getCentre().ApplyVector( new Vector3f(0,2,0));
		}*/
		
		//if(Controller.getInstance().isKeySPressed()){Player.getCentre().ApplyVector( new Vector3f(0,-2,0));}
//		System.out.println(jumpingIterations);
		if(Controller.getInstance().isKeySpacePressed() && jumpingIterations == 0 && canJumpAgain)
		{
			canJumpAgain = false;
			jumpingIterations = 15;
			this.getCentre().ApplyVector( new Vector3f(0,3,0));
			//CreateBullet();
			Controller.getInstance().setKeySpacePressed(false);
		}
		else if (jumpingIterations > 0) {
			jumpingIterations--;
			this.getCentre().ApplyVector( new Vector3f(0,13,0));
		}
		
		// Gravity
		// TODO: use collision with floor instead of hardcoded coordinate
		if (isColliding(levelLower, levelUpper)) {
			this.getCentre().ApplyVector(new Vector3f(0,-4,0));
		}
		else {
			canJumpAgain = true;
		}
		
	}
}
