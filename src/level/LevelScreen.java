package level;
import java.util.ArrayList;

import util.GameObject;

public class LevelScreen {
	private GameObject lowerFloor;
	private GameObject upperFloor;
	
	private boolean playerOnUpper = false;
	
	// private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	// private ArrayList<Portal> portals = new ArrayList<portal>();
	
	public LevelScreen(GameObject lowerFloor, GameObject upperFloor) {
		this.lowerFloor = lowerFloor;
		this.upperFloor = upperFloor;
	}
	
	public boolean playerCanSwitch() {
		// Check whether the player can switch between upper and lower
		return true;
	}
	
	public boolean switchFloor() {
		playerOnUpper = !playerOnUpper;
		return playerOnUpper;
	}
	
}
