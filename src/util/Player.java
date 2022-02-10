package util;
import level.GameLevel;
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
    
    public boolean isOnAir(GameLevel gameLevel)  {
    	// This should call gameLevel
    	if (gameLevel.getPlayerOnUpper()) {
    		GameObject upper = gameLevel.getUpperFloor();
    		return this.getCentre().getY() + getHeight()/1.5 < upper.getCentre().getY() - upper.getHeight();
    	}
    	else {
    		GameObject lower = gameLevel.getLowerFloor();
//       		System.out.println("Player " + getCentre().getY());
//    		System.out.println(lower.getCentre().getY() + " height: " + lower.getHeight());
    		return this.getCentre().getY() + getHeight()/1.5 < lower.getCentre().getY() - lower.getHeight();
    	}
    	
    }
    
    public int getMovementDirection() {
		return movingDirection;
	}
	
	public int getLastMovingDirection() {
		return lastMovingDirection;
	}
    
    public void playerLogic(GameLevel gameLevel) {
		
		// smoother animation is possible if we make a target position  // done but may try to change things for students  
		 
		//check for movement
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
		if (isOnAir(gameLevel)) {
			this.getCentre().ApplyVector(new Vector3f(0,-4,0));
		}
		else {
			canJumpAgain = true;
		}
		
	}
}
