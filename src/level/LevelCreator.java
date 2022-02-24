package level;

import java.util.ArrayList;

import util.GameObject;
import util.Point3f;

// Class that creates the object for each level/level screen
public class LevelCreator
{
	
	private final Integer resWidth;
	private final Integer resHeight;
	
	private final Integer doorUpper;
	private final Integer doorLower; 
	
	public LevelCreator(Integer resWidth, Integer resHeight) {
		this.resWidth = resWidth;
		this.resHeight = resHeight;
		
		this.doorLower = resHeight - 220;
		this.doorUpper = resHeight/2 - 220;
		
	}
	
	
	public GameLevel createLevel1() {
		ArrayList<LevelScreen> levelScreens = new ArrayList<LevelScreen>();
		levelScreens.add(createLevel1Screen1());
		levelScreens.add(createLevel1Screen2());
		levelScreens.add(createLevel1Screen3());
		
		return new GameLevel(levelScreens);
	}
	
	public LevelScreen createLevel1Screen1() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal((int)(resWidth*2.5/8), (int)(resWidth*3.5/8)));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		GameObject wall = new GameObject("res/level/wall.png", (int)(resWidth/11), (int)(resHeight/2.2), new Point3f(resWidth*4/8, resHeight - 220, 0), false);
		obstacles.add(wall);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, door);
		return levelScreen;
	}
	
	public LevelScreen createLevel1Screen2() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*3/8, resWidth*5/8));
//		portals1.add(new Portal(resWidth*1/8, resWidth*3/8));
		
		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*4/8, resHeight/2 - 110, 0), true);
		objects.add(crate);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorUpper, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, door);
		return levelScreen;
	}
	
	public LevelScreen createLevel1Screen3() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*3/8, resWidth*5/8));
//		portals1.add(new Portal(resWidth*1/8, resWidth*3/8));
		
		GameObject wall = new GameObject("res/level/wall.png", (int)(resWidth/11), (int)(resHeight/2.2), new Point3f(resWidth*4/8, resHeight - 220, 0), false);
		obstacles.add(wall);
		
		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*4/8, resHeight/2 - 110, 0), true);
		objects.add(crate);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, door);
		return levelScreen;
	}
	
	
}
