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
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private GameObject door;
	
	public LevelScreen(GameObject lowerFloor, GameObject upperFloor, GameObject spikes, 
					   ArrayList<Portal> portals, ArrayList<GameObject> objects,
					   GameObject door) {
		this.lowerFloor = lowerFloor;
		this.upperFloor = upperFloor;
		this.spikes = spikes;
		this.portals = portals;
		this.objects = objects;
		this.door = door;
	}
	
	public GameObject getDoor() {
		return door;
	}
	
	public ArrayList<Portal> getPortals() {
		return portals;
	}
	
	public ArrayList<GameObject> getObjects() {
		return objects;
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
	
	public Integer playerCanSwitch(float playerPosition) {
		// Check whether the player can switch between upper and lower
		Integer index = 0;
		for (Portal p : portals) {
			if (p.isInPortalRange(playerPosition)) return index;
			index++;
		}
		return -1;
	}
	
	public boolean isPositionInPortal(Integer portalIndex, float position) {
		return portals.get(portalIndex).isInPortalRange(position);
	}
	
	public boolean getPlayerOnUpper() {
		return playerOnUpper;
	}
	
	public boolean switchFloor() {
		playerOnUpper = !playerOnUpper;
		return playerOnUpper;
	}
	
}
