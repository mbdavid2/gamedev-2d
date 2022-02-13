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
	
    public Player(String textureLocation,int width,int height,Point3f centre, boolean isObjectOnUpper) { 
    	super(textureLocation, width, height, centre, isObjectOnUpper);
	}
    
    public boolean isOnAir(GameLevel gameLevel)  {
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
	
	public void doorLogic(GameLevel gameLevel) {
		// Going up
		if(Controller.getInstance().isKeyWPressed())
		{	
			GameObject door = gameLevel.getDoor();
			float doorLeft = door.getCentre().getX() - door.getWidth()/2;
			float doorRight = door.getCentre().getX() + door.getWidth()/2;
			if (getCentre().getX() > doorLeft && getCentre().getX() < doorRight) {
				gameLevel.moveNextScreen();
			}
		}
	}
    
	public void portalLogic(GameLevel gameLevel) {
		// Going up
		if(Controller.getInstance().isKeyWPressed())
		{	
			jumpingIterations = 0; // Cancel the jump
			Integer portalIndex = gameLevel.playerCanSwitch(this.getCentre().getX());
			
			if (portalIndex != -1 && !gameLevel.getPlayerOnUpper()) {
				float switchMovement = gameLevel.getUpperFloor().getCentre().getY();
				// Switch the objects in the range
				for (GameObject obj : gameLevel.getObjects()) {
					if (gameLevel.isPositionInPortal(portalIndex, obj.getCentre().getX())) {
						// Be sure that the object is on the opposite side
						if (gameLevel.getPlayerOnUpper() != obj.getObjectOnUpper()) {
							obj.getCentre().ApplyVector(new Vector3f(0, -switchMovement, 0));
							obj.switchUpper();
						}
					}
				}
				
				// Switch player 				
				gameLevel.switchFloor();
				this.getCentre().ApplyVector(new Vector3f(0, switchMovement, 0));
			}
		}		
		
		// Going down
		if(Controller.getInstance().isKeySPressed())
		{
			jumpingIterations = 0; // Cancel the jump
			
			Integer portalIndex = gameLevel.playerCanSwitch(this.getCentre().getX());
			if (portalIndex != -1 && gameLevel.getPlayerOnUpper()) {	
				float switchMovement = gameLevel.getUpperFloor().getCentre().getY();
				
				// Switch the objects in the range
				for (GameObject obj : gameLevel.getObjects()) {
					if (gameLevel.isPositionInPortal(portalIndex, obj.getCentre().getX())) {
						// Be sure that the object is on the opposite side
						if (gameLevel.getPlayerOnUpper() != obj.getObjectOnUpper()) {
							obj.getCentre().ApplyVector(new Vector3f(0, switchMovement, 0));
							obj.switchUpper();
						}
					}
				}
				
				// Switch player
				gameLevel.switchFloor();
				this.getCentre().ApplyVector(new Vector3f(0, -switchMovement*3/4, 0));
			}
		}
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

		
		//if(Controller.getInstance().isKeySPressed()){Player.getCentre().ApplyVector( new Vector3f(0,-2,0));}
//		System.out.println(jumpingIterations);
		if(Controller.getInstance().isKeySpacePressed() && jumpingIterations == 0 && canJumpAgain)
		{
			canJumpAgain = false;
			jumpingIterations = 15;
			this.getCentre().ApplyVector( new Vector3f(0,15,0));
			//CreateBullet();
			Controller.getInstance().setKeySpacePressed(false);
		}
		else if (jumpingIterations > 0) {
			jumpingIterations--;
			this.getCentre().ApplyVector( new Vector3f(0,15,0));
		}
		
		// Gravity
		// TODO: use collision with floor instead of hardcoded coordinate
		if (isOnAir(gameLevel)) {
			this.getCentre().ApplyVector(new Vector3f(0,-6,0));
		}
		else {
			canJumpAgain = true;
		}
		
		portalLogic(gameLevel);
		doorLogic(gameLevel);
		
	}
}
