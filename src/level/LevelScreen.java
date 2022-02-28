package level;
import java.util.ArrayList;

import util.GameObject;
import util.Point3f;

public class LevelScreen {
	private GameObject lowerFloor;
	private GameObject upperFloor;
	private GameObject spikes;
	
	private boolean playerOnUpper = false;
	
	private ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
	private ArrayList<Portal> portals = new ArrayList<Portal>();
	private ArrayList<GameObject> buttons = new ArrayList<GameObject>();
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
	private Point3f originalSpikeCenter;
	private GameObject key;
	private GameObject originalKey;
	private boolean hasKey = false;
	private GameObject door;
	public boolean playerHasKey = false;
	private boolean playerGotKeyOnUpper = false;
	
	private String name = "no_name";
	
	public LevelScreen(GameObject lowerFloor, GameObject upperFloor, GameObject spikes, 
					   ArrayList<Portal> portals, ArrayList<GameObject> objects,
					   ArrayList<GameObject> obstacles, ArrayList<GameObject> deathObjs, 
					   ArrayList<GameObject> buttons, GameObject door) {
		this.lowerFloor = lowerFloor;
		this.upperFloor = upperFloor;
		this.spikes = spikes;
		this.portals = portals;
		this.objects = objects;
		this.door = door;
		this.obstacles = obstacles;
		this.deathObjs = deathObjs;
		this.buttons = buttons;
		this.playerHasKey = false;
		this.playerGotKeyOnUpper = false;
		Point3f centre = spikes.getCentre();
		this.originalSpikeCenter = new Point3f(centre.getX(), centre.getY(), centre.getZ()).copy();
	}
	
	public LevelScreen(GameObject lowerFloor, GameObject upperFloor, GameObject spikes, 
			   ArrayList<Portal> portals, ArrayList<GameObject> objects,
			   ArrayList<GameObject> obstacles, ArrayList<GameObject> deathObjs, 
			   ArrayList<GameObject> buttons, GameObject door, GameObject key) {
		this.lowerFloor = lowerFloor;
		this.upperFloor = upperFloor;
		this.spikes = spikes;
		this.portals = portals;
		this.objects = objects;
		this.door = door;
		this.obstacles = obstacles;
		this.deathObjs = deathObjs;
		this.buttons = buttons;
		this.key = key;
		this.hasKey = true;
		this.playerHasKey = false;
		Point3f centre = spikes.getCentre();
		this.originalKey = key.copy();
		this.originalSpikeCenter = new Point3f(centre.getX(), centre.getY(), centre.getZ()).copy();
	}
	
	public void resetScreen() {
		spikes.setCentre(this.originalSpikeCenter.copy());
		if (hasKey) {
			playerHasKey = false;
			key = originalKey.copy();
		}
	}
	
	public void setPlayerHasKey() {
		playerHasKey = true;
	}
	
	public boolean getPlayerHasKey() {
		return playerHasKey;
	}
	
	public boolean getPlayerGotKeyUpper() {
		return playerGotKeyOnUpper;
	}
	
	public void setPlayerGotKeyUpper() {
		playerGotKeyOnUpper = true;
	}
	
	public boolean hasKey() {
		return hasKey;
	}
	
	public GameObject getKey() {
		return key;
	}
	
	public boolean hasName() {
		return !name.equals("no_name");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public ArrayList<GameObject> getButtons() {
		return buttons;
	}
	
	public ArrayList<GameObject> getObstacles() {
		return obstacles;
	}
		
	public GameObject getSpikes() {
		return spikes;
	}
	
	public ArrayList<GameObject> getDeathObjs() {
		return deathObjs;
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
	
	public Portal getPortal(Integer portalIndex) {
		return portals.get(portalIndex);
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
	
	public void setPlayerOnUpper(boolean playerOnUpper) {
		this.playerOnUpper = playerOnUpper;
	}
	
}
