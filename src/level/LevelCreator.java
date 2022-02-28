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
	
	private Integer deaths = 0;
	
	public LevelCreator(Integer resWidth, Integer resHeight) {
		this.resWidth = resWidth;
		this.resHeight = resHeight;
		
		this.doorLower = resHeight - 220;
		this.doorUpper = resHeight/2 - 220;
	}
	
	
	public GameLevel createLevel1(Integer deaths) {
		this.deaths = deaths;
		ArrayList<LevelScreen> levelScreens = new ArrayList<LevelScreen>();
		levelScreens.add(createLevel1Screen1());
		levelScreens.add(createLevel1Screen2());
		levelScreens.add(createLevel1Screen3());
		levelScreens.add(createLevel1Screen4());
		
		return new GameLevel(levelScreens);
	}
	
	// Basic tutorial
	public LevelScreen createLevel1Screen1() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal((int)(resWidth*2.5/8), (int)(resWidth*3.5/8)));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		GameObject wall = new GameObject("res/level/wall.png", (int)(resWidth/11), (int)(resHeight/2.2), new Point3f(resWidth*4/8, resHeight - 220, 0), false);
		obstacles.add(wall);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		if (deaths == 1) levelScreen.setName("Again...");
		else if (deaths == 2) levelScreen.setName("Well...");
		return levelScreen;
	}
	
	// Has a switching object for the first time
	public LevelScreen createLevel1Screen2() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*3/8, resWidth*5/8));
//		portals1.add(new Portal(resWidth*1/8, resWidth*3/8));
		
		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*4/8, resHeight/2 - 110, 0), true);
		objects.add(crate);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorUpper, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		if (deaths == 1) levelScreen.setName("You know the drill");
		else if (deaths == 2) levelScreen.setName("Third time is the charm...");
		return levelScreen;
	}
	
	// Has a switching object for the first time that needs to be used
	public LevelScreen createLevel1Screen3() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*7/8));
		portals1.add(new Portal(resWidth*3/8, resWidth*4/8));
		
		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*6/8, resHeight/2 - 110, 0), true);
		objects.add(crate);
		
		GameObject button = new GameObject("res/crate.png", 70, 15, new Point3f(resWidth*6/8, resHeight - 70, 0), false);
		buttons.add(button);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		if (deaths == 1) levelScreen.setName("Faster this time :)");
		else if (deaths == 2) levelScreen.setName("Right...?");
		return levelScreen;
	}
	
	// Has lava for the first time, teaches that you can teleport mid air
	public LevelScreen createLevel1Screen4() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*1/8, resWidth*3/8));
		portals1.add(new Portal(resWidth*9/20, resWidth*11/20));
		portals1.add(new Portal(resWidth*13/20, resWidth*17/20));
//		portals1.add(new Portal(resWidth*1/8, resWidth*3/8));
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorUpper, 0), true);
		
		GameObject fireFloor = new GameObject("res/lava_spr_strip45.png", resWidth/4, resHeight/5, new Point3f(resWidth*6/8, resHeight, 0));
		deathObjs.add(fireFloor);
		
		GameObject wall = new GameObject("res/level/wall.png", (int)(resWidth/11), (int)(resHeight/2.2), new Point3f(resWidth*8/20, resHeight - 220, 0), false);
		GameObject wall2 = new GameObject("res/level/wall.png", (int)(resWidth/11), (int)(resHeight/2.2), new Point3f(resWidth*12/20, resHeight/5, 0), true);
		obstacles.add(wall);
		obstacles.add(wall2);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		levelScreen.setName("Press SPACE to jump");
		return levelScreen;
	}
	
	public GameLevel createLevel2(Integer deaths) {
		this.deaths = deaths;
		ArrayList<LevelScreen> levelScreens = new ArrayList<LevelScreen>();
		levelScreens.add(createLevel2Screen1());
		levelScreens.add(createLevel2Screen2());
//		levelScreens.add(createLevel1Screen3());
		levelScreens.add(createLevel2Screen4());
		
		return new GameLevel(levelScreens, 2);
	}
	
	// Basic tutorial with key
	public LevelScreen createLevel2Screen1() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal((int)(resWidth*2.5/8), (int)(resWidth*3.5/8)));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		GameObject key = new GameObject("res/level/keyPixel.png", 60, 40, new Point3f((int)(resWidth*3.75/8), resHeight/2 - 110, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door, key);
		if (deaths == 0) levelScreen.setName("New level");
		return levelScreen;
	}
	
	public LevelScreen createLevel2Screen2() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal((int)(resWidth*2.5/8), (int)(resWidth*3.5/8)));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorUpper, 0), true);
		
		GameObject key = new GameObject("res/level/keyPixel.png", 60, 40, new Point3f((int)(resWidth*3/8), resHeight/2 - 110, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door, key);
		return levelScreen;
	}
	
	public LevelScreen createLevel2Screen4() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal((int)(resWidth*2.5/8), (int)(resWidth*3.5/8)));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		GameObject key = new GameObject("res/level/keyPixel.png", 60, 40, new Point3f((int)(resWidth*3/8), resHeight/2 - 110, 0), true);
		
		GameObject fireFloor = new GameObject("res/lava_spr_strip45.png", resWidth/8, resHeight/5, new Point3f(resWidth*4/8, resHeight, 0));
		deathObjs.add(fireFloor);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door, key);
		return levelScreen;
	}
	
	public GameLevel createLevel3(Integer deaths) {
		this.deaths = deaths;
		ArrayList<LevelScreen> levelScreens = new ArrayList<LevelScreen>();
		levelScreens.add(createLevel3Screen1());
		levelScreens.add(createLevel3Screen2());
		
		return new GameLevel(levelScreens, 3);
	}
	
	public LevelScreen createLevel3Screen1() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal((int)(resWidth*2.5/8), (int)(resWidth*3.5/8)));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorUpper, 0), true);
		
		GameObject key = new GameObject("res/level/keyPixel.png", 60, 40, new Point3f((int)(resWidth*3/8), resHeight/2 - 110, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door, key);
		return levelScreen;
	}
	
	public LevelScreen createLevel3Screen2() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal((int)(resWidth*2.5/8), (int)(resWidth*3.5/8)));
				
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		GameObject key = new GameObject("res/level/keyPixel.png", 60, 40, new Point3f((int)(resWidth*3/8), resHeight/2 - 110, 0), true);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door, key);
		return levelScreen;
	}
	
	public GameLevel createLevel4(Integer deaths) {
		this.deaths = deaths;
		ArrayList<LevelScreen> levelScreens = new ArrayList<LevelScreen>();
		levelScreens.add(createLevel4Screen0());
		levelScreens.add(createLevel4Screen1());
		levelScreens.add(createLevel4Screen3());
		levelScreens.add(createLevel4Screen2());

		
		return new GameLevel(levelScreens, 4);
	}
	
	public LevelScreen createLevel4Screen0() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		ArrayList<GameObject> enemies = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*5/8, resWidth*6/8));
		portals1.add(new Portal(resWidth*3/8, resWidth*4/8));
		
//		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*6/8, resHeight/2 - 110, 0), true);
//		objects.add(crate);
		
//		GameObject button = new GameObject("res/crate.png", 70, 15, new Point3f(resWidth*4/8, resHeight - 70, 0), false);
//		buttons.add(button);
		
		GameObject enemy = new GameObject("res/characters_flip.png", 90, 90, new Point3f(this.resWidth*8/10, this.resHeight*2/3, 0), false);
		enemies.add(enemy);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		levelScreen.setEnemies(enemies);
		return levelScreen;
	}
	
	public LevelScreen createLevel4Screen1() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		ArrayList<GameObject> enemies = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*6/8, resWidth*7/8));
		portals1.add(new Portal(resWidth*3/8, resWidth*4/8));
		
//		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*6/8, resHeight/2 - 110, 0), true);
//		objects.add(crate);
		
		GameObject button = new GameObject("res/crate.png", 70, 15, new Point3f(resWidth*4/8, resHeight - 70, 0), false);
		buttons.add(button);
		
		GameObject enemy = new GameObject("res/characters_flip.png", 90, 90, new Point3f(this.resWidth*8/10, this.resHeight*2/3, 0), false);
		enemies.add(enemy);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		levelScreen.setEnemies(enemies);
		return levelScreen;
	}
	
	public LevelScreen createLevel4Screen2() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		ArrayList<GameObject> enemies = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*6/8, resWidth*7/8));
		portals1.add(new Portal(resWidth*3/8, resWidth*4/8));
		
//		GameObject crate = new GameObject("res/crate.png", 50, 50, new Point3f(resWidth*6/8, resHeight/2 - 110, 0), true);
//		objects.add(crate);
		
		GameObject button = new GameObject("res/crate.png", 70, 15, new Point3f(resWidth*2/8, resHeight/2 - 70, 0), true);
		buttons.add(button);
		
		GameObject enemy = new GameObject("res/characters_flip.png", 90, 90, new Point3f(this.resWidth*8/10, this.resHeight*2/3, 0), false);
		enemies.add(enemy);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		levelScreen.setEnemies(enemies);
		return levelScreen;
	}
	
	public LevelScreen createLevel4Screen3() {
		ArrayList<Portal> portals1 = new ArrayList<Portal>();
		ArrayList<GameObject> obstacles= new ArrayList<GameObject>();
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		ArrayList<GameObject> deathObjs = new ArrayList<GameObject>();
		ArrayList<GameObject> buttons = new ArrayList<GameObject>();
		ArrayList<GameObject> enemies = new ArrayList<GameObject>();
		
		GameObject lowerFloor = new GameObject("res/level/floorLower.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight, 0));
		GameObject upperFloor= new GameObject("res/level/floorUpper.png", 1280, resHeight/8, new Point3f(resWidth/2, resHeight/2, 0));
		GameObject spikes = new GameObject("res/fire.png", 20, 20, new Point3f(0, resHeight/2, 0));
		portals1.add(new Portal(resWidth*4/8, resWidth*7/8));
		
		GameObject button = new GameObject("res/crate.png", 70, 15, new Point3f(resWidth*3/8, resHeight - 70, 0), false);
		buttons.add(button);
		
		GameObject fireFloor = new GameObject("res/lava_spr_strip45.png", resWidth/4, resHeight/5, new Point3f(resWidth*14/20, resHeight, 0));
		deathObjs.add(fireFloor);
		
		GameObject enemy = new GameObject("res/characters_flip.png", 90, 90, new Point3f(this.resWidth*8/10, 0, 0), true);
		enemies.add(enemy);
		
		GameObject door = new GameObject("res/level/door.png", (int)(resWidth/11), (int)(resHeight/4.6), new Point3f(resWidth*7/8, doorLower, 0), false);
		
		LevelScreen levelScreen = new LevelScreen(lowerFloor, upperFloor, spikes, portals1, objects, obstacles, deathObjs, buttons, door);
		levelScreen.setEnemies(enemies);
		return levelScreen;
	}
}
