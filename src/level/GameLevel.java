package level;
import java.util.ArrayList;

import util.GameObject;

public class GameLevel {
	private Integer currentScreenIndex = 0;
	
	private ArrayList<LevelScreen> screens = new ArrayList<LevelScreen>();
	private LevelScreen currentScreen;
	
	public GameLevel(ArrayList<LevelScreen> levelScreens) {
		this.screens = levelScreens;
		updateCurrentScreen();
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
	
	private void updateCurrentScreen() {
		currentScreen = screens.get(currentScreenIndex);
	}
	
	public boolean playerCanSwitch() {
		// Check whether the player can switch between upper and lower
		return currentScreen.playerCanSwitch();
	}
	
	public boolean getPlayerOnUpper() {
		return currentScreen.getPlayerOnUpper();
	}
	
	// Switch the state, return true means the player is on upper, false on lower
	public boolean switchFloor() {
		return currentScreen.switchFloor();		
	}
}
