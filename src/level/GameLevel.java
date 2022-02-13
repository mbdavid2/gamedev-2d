package level;
import java.util.ArrayList;

import util.GameObject;

public class GameLevel {
	
	private final Integer resWidth = 1280;
	private final Integer resHeight = 720;
	private Integer currentScreenIndex = 0;
	
	private ArrayList<LevelScreen> screens = new ArrayList<LevelScreen>();
	private LevelScreen currentScreen;
	
	public GameLevel(ArrayList<LevelScreen> levelScreens) {
		this.screens = levelScreens;
		updateCurrentScreen();
	}
	
	public Integer getResWidth() {
		return resWidth;
	}
	
	public Integer getResHeight() {
		return resHeight;
	}
	
	public GameObject getLowerFloor() {
		return currentScreen.getLowerFloor();
	}
	
	public GameObject getUpperFloor() {
		return currentScreen.getUpperFloor();
	}
	
	public GameObject getSpikes() {
		return currentScreen.getSpikes();
	}
	
	public void moveNextScreen() {
		currentScreenIndex++;
		updateCurrentScreen();
	}
	
	private void updateCurrentScreen() {
		currentScreen = screens.get(currentScreenIndex);
	}
	
	public ArrayList<Portal> getPortals() {
		return currentScreen.getPortals();
	}
	
	public ArrayList<GameObject> getObjects() {
		return currentScreen.getObjects();
	}
	
	public boolean isPositionInPortal(Integer portalIndex, float position) {
		return currentScreen.isPositionInPortal(portalIndex, position);
	}
	
	public Integer playerCanSwitch(float playerPosition) {
		// Check whether the player can switch between upper and lower
		return currentScreen.playerCanSwitch(playerPosition);
	}
	
	public boolean getPlayerOnUpper() {
		return currentScreen.getPlayerOnUpper();
	}
	
	// Switch the state, return true means the player is on upper, false on lower
	public boolean switchFloor() {
		return currentScreen.switchFloor();		
	}
	
	public GameObject getDoor() {
		return currentScreen.getDoor();
	}
}
