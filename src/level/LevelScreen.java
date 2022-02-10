package level;
import java.util.ArrayList;

import util.GameObject;

public class LevelScreen {
	private GameObject lowerFloor;
	private GameObject upperFloor;
	private GameObject spikes;
	
	private boolean playerOnUpper = false;
	
	// private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<Portal> portals = new ArrayList<Portal>();
	
	public LevelScreen(GameObject lowerFloor, GameObject upperFloor, GameObject spikes, 
					   ArrayList<Portal> portals) {
		this.lowerFloor = lowerFloor;
		this.upperFloor = upperFloor;
		this.spikes = spikes;
		this.portals = portals;
	}
	
	public ArrayList<Portal> getPortals() {
		return portals;
	}
		
	public GameObject getSpikes() {
		return spikes;
	}
	
	public GameObject getLowerFloor() {
		return lowerFloor;
	}
	
	public GameObject getUpperFloor() {
		return upperFloor;
	}
	
	public boolean playerCanSwitch(float playerPosition) {
		// Check whether the player can switch between upper and lower
		for (Portal p : portals) {
			if (p.isInPortalRange(playerPosition)) return true; 
		}
		return false;
	}
	
	public boolean getPlayerOnUpper() {
		return playerOnUpper;
	}
	
	public boolean switchFloor() {
		playerOnUpper = !playerOnUpper;
		return playerOnUpper;
	}
	
}
