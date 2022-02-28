package level;
import java.util.ArrayList;

import util.GameObject;

public class GameLevel {
	
	private final Integer resWidth = 1280;
	private final Integer resHeight = 720;
	private Integer currentScreenIndex = 0;
	private boolean doorEnabled = true;
	private boolean portalsEnabled = true;
	
	private ArrayList<LevelScreen> screens = new ArrayList<LevelScreen>();
	private LevelScreen currentScreen;
	
	private Integer levelNumber = 1;
	
	public Integer getCurrentIndex() {
		return currentScreenIndex;
	}
	
	public Integer getLevelNumber() {
		return levelNumber;
	}
	
	public GameLevel(ArrayList<LevelScreen> levelScreens) {
		this.screens = levelScreens;
		updateCurrentScreen();
	}
	
	public GameLevel(ArrayList<LevelScreen> levelScreens, Integer levelNumber) {
		this.screens = levelScreens;
		updateCurrentScreen();
		this.levelNumber = levelNumber;
	}
	
	public ArrayList<GameObject> getDeathObjs() {
		return currentScreen.getDeathObjs();
	}
	
	public ArrayList<GameObject> getButtons() {
		return currentScreen.getButtons();
	}
	
	public boolean hasKey() {
		return currentScreen.hasKey();
	}
	
	public GameObject getKey() {
		return currentScreen.getKey();
	}
	
	public void setPlayerHasKey() {
		currentScreen.setPlayerHasKey();
	}
	
	public boolean getPlayerHasKey() {
		return currentScreen.getPlayerHasKey();
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
	
	public boolean isDoorEnabled() {
		return doorEnabled;
	}
	
	public void setPlayerOnUpper(boolean playerOnUpper) {
		currentScreen.setPlayerOnUpper(playerOnUpper);
	}
	
	public void moveNextScreen() {
		portalsEnabled = false;
		currentScreenIndex++;
		if (currentScreenIndex >= screens.size()) currentScreenIndex = -1;
		updateCurrentScreen();
		portalsEnabled = true;
	}
	
	public Portal getPortal(Integer portalIndex) {
		return currentScreen.getPortal(portalIndex);
	}
	
	public boolean hasName() {
		return currentScreen.hasName();
	}
	
	public String getName() {
		return currentScreen.getName();
	}
	
	private void updateCurrentScreen() {
		System.out.println("Moving to screen " + (currentScreenIndex+1) + " out of " + screens.size());
		if (currentScreenIndex != -1) {
			currentScreen = screens.get(currentScreenIndex);
		}
		else System.out.println("No more screens in this level!!");
	}
	
	public ArrayList<Portal> getPortals() {
		return currentScreen.getPortals();
	}
	
	public ArrayList<GameObject> getObjects() {
		return currentScreen.getObjects();
	}
	
	public ArrayList<GameObject> getObstacles() {
		return currentScreen.getObstacles();
	}
	
	public boolean isPositionInPortal(Integer portalIndex, float position) {
		return currentScreen.isPositionInPortal(portalIndex, position);
	}
	
	public Integer playerCanSwitch(float playerPosition) {
		// Check whether the player can switch between upper and lower
		if (!portalsEnabled) return -1;
		else return currentScreen.playerCanSwitch(playerPosition);
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
