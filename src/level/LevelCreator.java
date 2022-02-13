package level;

import java.util.ArrayList;

import util.GameObject;
import util.Point3f;

public class LevelCreator
{
	
	private final Integer resWidth;
	private final Integer resHeight;
	
	public LevelCreator(Integer resWidth, Integer resHeight) {
		this.resWidth = resWidth;
		this.resHeight = resHeight;
	}
	
	
	public GameLevel createLevel1() {
		ArrayList<LevelScreen> levelScreens = new ArrayList<LevelScreen>();
		levelScreens.add(createLevel1Screen1());
		levelScreens.add(createLevel1Screen2());
		
		return new GameLevel(levelScreens);
	}
	
	public LevelScreen createLevel1Screen1() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*4/8, resWidth*5/8));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, resHeight/2 - 220, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, door);
		return levelScreen;
	}
	
	public LevelScreen createLevel1Screen2() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*4/8, resWidth*5/8));
		portals1.add(new Portal(resWidth*1/8, resWidth*3/8));
		
		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*2/8, resHeight/2 - 110, 0), true);
		objects.add(crate);
		
		GameObject door = new GameObject("res/level/door.png", 50, 50, new Point3f(resWidth*2/8, resHeight/2 - 110, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, door);
		return levelScreen;
	}
	
	
}
