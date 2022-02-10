package level;
import java.util.ArrayList;
import level.LevelScreen;

public class GameLevel {
	private Integer currentScreenIndex = 0;
	
	private ArrayList<LevelScreen> screens = new ArrayList<LevelScreen>();
	
	public GameLevel(ArrayList<LevelScreen> levelScreens) {
		this.screens = levelScreens;
	}
	
	public boolean playerCanSwitch() {
		// Check whether the player can switch between upper and lower
		LevelScreen currentScreen = screens.get(currentScreenIndex);
		return currentScreen.playerCanSwitch();
	}
	
	// Switch the state, return true means the player is on upper, false on lower
	public boolean switchFloor() {
		LevelScreen currentScreen = screens.get(currentScreenIndex);
		return currentScreen.switchFloor();		
	}
}
