package util;
import javax.imageio.ImageIO;

import level.GameLevel;
import mvc.Controller;

//David Moreno Borr�s 21200646

public class Player extends GameObject {
	private boolean canJumpAgain = true;
	private int movingDirection = 0; // -1 left, 0 still, 1 right
	private int lastMovingDirection = 1;
	private int jumpingIterations = 0;
	private boolean movedNextLevel = false; 
	 
	private Point3f originalCenter;
	
	public Player() {  

	}
	
    public Player(String textureLocation,int width,int height,  Point3f centre, boolean isObjectOnUpper) { 
    	super(textureLocation, width, height, centre, isObjectOnUpper);
    	
    	this.originalCenter = new Point3f(centre.getX(), centre.getY(), centre.getZ()).copy();
//    	System.out.println(centre);
//    	System.out.println(this.originalCenter);
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
	
	// Check for location and conditions
	public boolean isDoorAvailable(GameLevel gamelevel) {
		GameObject door = gamelevel.getDoor();
		float doorLeft = door.getCentre().getX() - door.getWidth()/2;
		float doorRight = door.getCentre().getX() + door.getWidth()/2;
		boolean sameLevel = gamelevel.getPlayerOnUpper() == door.getObjectOnUpper();
		boolean doorOpen = true;
		
		for (GameObject obj : gamelevel.getButtons()) {
			doorOpen = false;
			if (obj.getIsPressed()) {
				doorOpen = true;
			}
		}
		
		if (gamelevel.hasKey() && gamelevel.getPlayerHasKey()) {
			doorOpen = doorOpen && true;
		}
		else if (gamelevel.hasKey() && !gamelevel.getPlayerHasKey()) doorOpen = false;
		
		return doorOpen && sameLevel 
			   && (getCentre().getX() - this.getWidth()*2/5 > doorLeft && getCentre().getX() - this.getWidth()*2/5 < doorRight);
	}
	
	public void resetPlayer(GameLevel gameLevel) {
		this.setCentre(this.originalCenter.copy());
		gameLevel.setPlayerOnUpper(false);
	}
	
	public boolean doorLogic(GameLevel gameLevel) {
		// Check that there is no portal collision
		Integer portalIndex = gameLevel.playerCanSwitch(this.getCentre().getX() + this.getWidth()*2/3);
		if(Controller.getInstance().isKeyWPressed() && gameLevel.isDoorEnabled() && portalIndex == -1 && !movedNextLevel)
		{	
			if (isDoorAvailable(gameLevel)) {
//		    	System.out.println("Before:" + this.getCentre());
				gameLevel.moveNextScreen();
				resetPlayer(gameLevel);
				movedNextLevel = true;
				return true;
//				System.out.println("After:" + this.getCentre());
			}
		}
		else if (!Controller.getInstance().isKeyWPressed()) {
			// Doing this to prevent portal movement if after opening the
			// door and moving to the next level we end up under a portal/door
			movedNextLevel = false;
		}
		return false;
	}
    
	public void portalLogic(GameLevel gameLevel) {
		// Going up
		if(Controller.getInstance().isKeyWPressed() && !movedNextLevel)
		{	
			jumpingIterations = 0; // Cancel the jump
			Integer portalIndex = gameLevel.playerCanSwitch(this.getCentre().getX() + this.getWidth()*5/8);
			
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
				
				// Switch the enemies in the range
				for (GameObject obj : gameLevel.getEnemies()) {
					if (gameLevel.isPositionInPortal(portalIndex, obj.getCentre().getX())) {
						// Be sure that the object is on the opposite side
						if (gameLevel.getPlayerOnUpper() != obj.getObjectOnUpper()) {
							obj.getCentre().ApplyVector(new Vector3f(0, -switchMovement/2, 0));
							obj.switchUpper();
						}
					}
				}
				
				if (gameLevel.hasKey() && !gameLevel.getPlayerHasKey()) {
					GameObject obj = gameLevel.getKey();
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
		else if (!Controller.getInstance().isKeyWPressed()) {
			// Doing this to prevent portal movement if after opening the
			// door and moving to the next level we end up under a portal/door
			movedNextLevel = false;
		}
		
		// Going down
		if(Controller.getInstance().isKeySPressed())
		{
			jumpingIterations = 0; // Cancel the jump
			
			Integer portalIndex = gameLevel.playerCanSwitch(this.getCentre().getX() + this.getWidth()*5/8);
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
				
				// Switch the enemies in the range
				for (GameObject obj : gameLevel.getEnemies()) {
					if (gameLevel.isPositionInPortal(portalIndex, obj.getCentre().getX())) {
						// Be sure that the object is on the opposite side
						if (gameLevel.getPlayerOnUpper() != obj.getObjectOnUpper()) {
							obj.getCentre().ApplyVector(new Vector3f(0, switchMovement, 0));
							obj.switchUpper();
						}
					}
				}
				
				if (gameLevel.hasKey() && !gameLevel.getPlayerHasKey()) {
					GameObject obj = gameLevel.getKey();
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
	
    public boolean playerLogic(GameLevel gameLevel) {
		
		// smoother animation is possible if we make a target position  // done but may try to change things for students  
		 
		//check for movement
		// Implement gravity
		movingDirection = 0;
		if(Controller.getInstance().isKeyAPressed()){
			movingDirection = -1;
			this.getCentre().ApplyVector( new Vector3f(-5,0,0), gameLevel.getObstacles(), gameLevel.getPlayerOnUpper(), lastMovingDirection); 
			lastMovingDirection = -1;
		}
		
		if(Controller.getInstance().isKeyDPressed())
		{
			movingDirection = 1;
			this.getCentre().ApplyVector( new Vector3f(5,0,0), gameLevel.getObstacles(), gameLevel.getPlayerOnUpper(), lastMovingDirection);
			lastMovingDirection = 1;
		}

		
		//if(Controller.getInstance().isKeySPressed()){Player.getCentre().ApplyVector( new Vector3f(0,-2,0));}
//		System.out.println(jumpingIterations);
		if(Controller.getInstance().isKeySpacePressed() && jumpingIterations == 0 && canJumpAgain)
		{
			canJumpAgain = false;
			jumpingIterations = 20;
			this.getCentre().ApplyVector( new Vector3f(0,15,0));
			//CreateBullet();
			Controller.getInstance().setKeySpacePressed(false);
		}
		else if (jumpingIterations > 0) {
			jumpingIterations--;
			this.getCentre().ApplyVector( new Vector3f(0,15,0));
		}
		
		// Gravity
		if (isOnAir(gameLevel)) {
			this.getCentre().ApplyVector(new Vector3f(0,-6,0));
		}
		else {
			canJumpAgain = true;
		}
		
		portalLogic(gameLevel);
		return doorLogic(gameLevel);
	}
}
