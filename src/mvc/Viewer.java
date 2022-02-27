package mvc;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import level.GameLevel;
import level.Portal;
import util.GameObject;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private long currentAnimationTime= 0; 
	private final String portalTexture = "res/level/floorLower.png";
	private Integer lvNameFrame = 0;
	private boolean resetGameOver = true;
	private Integer currentScreenIndex = 0;
	
	Model gameworld = new Model(1280, 720); 
	 
	public Integer getResWidth() {
		 return gameworld.getResWidth();
	}
	
	public Integer getResHeight() {
		return gameworld.getResHeight();
	}
	
	public Viewer(Model World) {
		this.gameworld=World;
		lvNameFrame = 0;
		resetGameOver = true;
		currentScreenIndex = 0;
		currentAnimationTime = 0;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}
	
	public static void printText(String textToPrint, int x, int y, Graphics g, int thickness, int fontSize) {
		g.setFont(new Font("UPHEAVAL TT -BRK-", Font.BOLD, fontSize)); 
		g.setColor(Color.BLACK);
		g.drawString(textToPrint, x+thickness, y-thickness);
		g.drawString(textToPrint, x+thickness, y+thickness);
		g.drawString(textToPrint, x-thickness, y-thickness);
		g.drawString(textToPrint, x-thickness, y+thickness);
		g.setColor(Color.WHITE);		
		g.drawString(textToPrint, x, y);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		currentAnimationTime++; // runs animation time step 
		
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		String texture = gameworld.getPlayer().getTexture();
		
		//Draw background 
		drawBackground(g);
		
		// Draw level
		drawDoor(g);
		drawLevel(g);
		drawPortals(g);
		drawSpikes(g);
		drawDeathObj(g);
		
		//Draw player
		drawPlayer(x, y, width, height, texture, g);	
		
		if (Controller.getInstance().getGameOver()) {
			if (resetGameOver) {
				lvNameFrame = 0;
				resetGameOver = false;
			}
			boolean finished = drawLevelName(g, true);
			if (finished) {
				Controller.getInstance().setGameOverPrinted(true);
				resetGameOver = true;
			}
//			printText("GAME OVER", getResWidth()/2, getResHeight()/4, g, 4);
		}
		else {
			drawLevelName(g, false);
		}
	}

	private boolean drawLevelName(Graphics g, boolean gameOver) {
		GameLevel level = gameworld.getLevel();
		boolean finished = false;
		if (level.getCurrentIndex() != currentScreenIndex) {
			currentScreenIndex = level.getCurrentIndex();
			lvNameFrame = 0;
		}

		Integer limit = (int)(getResWidth()*3/5);
		Integer currentPosition = (int)(getResWidth()/6 + lvNameFrame);
		
		double speed;
		if (currentPosition < limit) {
			speed = Math.log(limit - currentPosition)*6;
		}
		else {
			speed = 1;
			finished = true;
		}
			// Create the name string
		
		String msgText = "Game Over";
		if (!gameOver) {
			Integer currentIndex = level.getCurrentIndex() + 1;
			StringBuilder title = new StringBuilder();
			title.append("LEVEL ");
			title.append("1");
			title.append(" - ");
			title.append(currentIndex);
			msgText = title.toString();
		}

		if (gameOver) {
			printText(msgText, getResWidth()/3, getResHeight()/4, g, 6, 90);
		}
		else {
			printText(msgText, getResWidth()/6 + (int)(lvNameFrame), getResHeight()/4, g, 4, 70);
		}
		lvNameFrame = (int) (lvNameFrame + speed);
			
		return finished;
	}
	
	private void drawBackground(Graphics g)
	{
		File TextureToLoad = new File("res/exterior-parallaxBG1.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0, 0, getResWidth(), getResHeight(), 0 , 0, 256, 144, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawSpikes(Graphics g) {	
		GameObject spikes = gameworld.getSpikes();
		File TextureToLoad = new File(spikes.getTexture());  
		try {
			Integer height = getResHeight();
			Image myImage = ImageIO.read(TextureToLoad);
			int currentPositionInAnimation = ((int) ((currentAnimationTime%20)/10))*64;
			
			int xStart = -25 + (int)spikes.getCentre().getX();
			int xEnd = 175 + (int)spikes.getCentre().getX();

			g.drawImage(myImage, xStart, 0 - height/16, xEnd, height/4 - height/16, 0, currentPositionInAnimation, 64, currentPositionInAnimation + 64, null);
			g.drawImage(myImage, xStart, 0 + height/8 - height/16, xEnd, height/4 + height/8  - height/16, 0, currentPositionInAnimation, 64, currentPositionInAnimation + 64, null);
			g.drawImage(myImage, xStart, height/4 - height/16, xEnd, height/2 - height/16, 0, currentPositionInAnimation, 64, currentPositionInAnimation + 64, null);
			
			g.drawImage(myImage, xStart, height/2, xEnd, height*3/4, 0, currentPositionInAnimation, 64, currentPositionInAnimation + 64, null);
			g.drawImage(myImage, xStart, height/2 + height/8, xEnd, height*3/4 + height/8, 0, currentPositionInAnimation, 64, currentPositionInAnimation + 64, null);
			g.drawImage(myImage, xStart, height*3/4, xEnd, height, 0, currentPositionInAnimation, 64, currentPositionInAnimation + 64, null);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void drawDeathObj(Graphics g) {	
		ArrayList<GameObject> deathObjs = gameworld.getLevel().getDeathObjs();
		
		for (GameObject deathObj : deathObjs) {
			File TextureToLoad = new File(deathObj.getTexture());  
			try {
				Integer height = getResHeight();
				Image myImage = ImageIO.read(TextureToLoad);
				int currentPositionInAnimation = ((int) ((currentAnimationTime%20)/10))*16;
				
				int xStart = (int) (deathObj.getCentre().getX() - deathObj.getWidth()/2);
				int xEnd = (int) (deathObj.getCentre().getX() + deathObj.getWidth()/2);
	
				g.drawImage(myImage, xStart, height*58/64, xEnd, height, currentPositionInAnimation, 0, currentPositionInAnimation + 16, 16, null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
		
	private void drawPortals(Graphics g) {
		GameLevel level = gameworld.getLevel();	
		
		try {
			File TextureToLoad = new File(portalTexture);
			Image portalImage = ImageIO.read(TextureToLoad);
			for (Portal p : level.getPortals()) {
				g.drawImage(portalImage, p.getStart(), getResHeight()*3/8, p.getEnd(), getResHeight()/2, 90, 25, 0, 0, null);
			}
			
			// Print button prompt if first level
			if (level.getCurrentIndex() == 0) {
				Integer portalIndex = level.playerCanSwitch(gameworld.getPlayer().getCentre().getX() + gameworld.getPlayer().getWidth()*2/3);
				if (portalIndex != -1) {
					Portal p = level.getPortal(portalIndex);
					if (level.getPlayerOnUpper()) {
						TextureToLoad = new File("res/s_key_bg.png");
					}
					else {
						TextureToLoad = new File("res/w_key_bg.png");
					}
					portalImage = ImageIO.read(TextureToLoad);
					Integer portalCenter = (int) (p.getStart() + (p.getEnd() - p.getStart())/2);
					float height = (getResHeight()/2 - getResHeight()*3/8);
					Integer xStart = (int) (portalCenter - height/4);
					Integer xEnd = (int) (portalCenter + height/4);
					height = xEnd - xStart;
					g.drawImage(portalImage, xStart, (int) (getResHeight()*3/8 + height/2) , xEnd, (int) (getResHeight()/2 - height/2), 0, 0, 210, 189, null);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void drawDoor(Graphics g) {
		GameLevel level = gameworld.getLevel();	
		
		try {
			// Print the door
			GameObject door = level.getDoor();
			File TextureToLoad = new File(door.getTexture());
			Image myImageLower = ImageIO.read(TextureToLoad);
			int x = (int) door.getCentre().getX();
			int y = (int) door.getCentre().getY();
			g.drawImage(myImageLower, x, y, x + door.getWidth(), y + door.getHeight(), 0, 0, 57, 77, null);
			
			// Print button prompt if first level
			if (level.getCurrentIndex() == 0) {
				if (gameworld.getPlayer().isWithinDoor(level)) {
					TextureToLoad = new File("res/level/doorW.png");
					myImageLower = ImageIO.read(TextureToLoad);
					g.drawImage(myImageLower, x, y, x + door.getWidth(), y + door.getHeight(), 0, 0, 57, 77, null);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void drawLevel(Graphics g) {
		GameLevel level = gameworld.getLevel();
		
		try {
			File TextureToLoad;
			Image myImageLower;
			
			// Print the objects (crates, keys...)
			for (GameObject obj : level.getObjects()) {
				TextureToLoad = new File(obj.getTexture());
				myImageLower = ImageIO.read(TextureToLoad);
				int x = (int) obj.getCentre().getX();
				int y = (int) obj.getCentre().getY();
				g.drawImage(myImageLower, x, y, x + obj.getWidth(), y + obj.getHeight(), 0, 0, 10, 10, null);
			}
			
			// Print the walls/obstacles
			for (GameObject obj : level.getObstacles()) {
				TextureToLoad = new File(obj.getTexture());
				myImageLower = ImageIO.read(TextureToLoad);
				int x = (int) obj.getCentre().getX() - obj.getWidth()/2;
				int y = (int) obj.getCentre().getY() - obj.getHeight()/2;
				g.drawImage(myImageLower, x, y, x + obj.getWidth(), y + obj.getHeight(), 0, 0, 23, 46, null);
			}
			
			Integer height = getResHeight();
			Integer width = getResWidth();
			
			// Lower level
			TextureToLoad = new File(level.getLowerFloor().getTexture());
			myImageLower = ImageIO.read(TextureToLoad);
			g.drawImage(myImageLower, 0, height*7/8, width, height, 0, 0, 312, 20, null);
//			g.drawImage(myImageLower, getResWidth()/4, getResHeight()*5/6, getResWidth()*2/4, getResHeight(), 110, 0, 159, 15, null);
//			g.drawImage(myImageLower, getResWidth()*2/3, getResHeight()*5/6, getResWidth(), getResHeight(), 110, 0, 159, 15, null);
			
			// Upper level
			TextureToLoad = new File(level.getUpperFloor().getTexture());
			myImageLower = ImageIO.read(TextureToLoad);
			g.drawImage(myImageLower, 0, height*3/8, width, height/2, 0, 0, 312, 20, null);			

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			int direction = gameworld.getMovementDirection();
			int lastDirection = gameworld.getLastMovingDirection();
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			if (direction == 1 || direction == 0 && lastDirection == 1) {
				int currentPositionInAnimation= ((int) ((currentAnimationTime%40)/10))*32; //slows down animation so every 10 frames we get another frame so every 100ms 
				if (direction == 0) currentPositionInAnimation = 0;
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation, 0, currentPositionInAnimation + 31, 32, null); 
			}
			else if (direction == -1 || direction == 0 && lastDirection == -1) {
				int currentPositionInAnimation= ((int) ((currentAnimationTime%40)/10))*32; //slows down animation so every 10 frames we get another frame so every 100ms 
				if (direction == 0) currentPositionInAnimation = 0;
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation, 96, currentPositionInAnimation + 31, 128, null); 
			}			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images
		
	}
		 
	 

}


/*
 * 
 * 
 *              VIEWER HMD into the world                                                             
                                                                                
                                      .                                         
                                         .                                      
                                             .  ..                              
                               .........~++++.. .  .                            
                 .   . ....,++??+++?+??+++?++?7ZZ7..   .                        
         .   . . .+?+???++++???D7I????Z8Z8N8MD7I?=+O$..                         
      .. ........ZOZZ$7ZZNZZDNODDOMMMMND8$$77I??I?+?+=O .     .                 
      .. ...7$OZZ?788DDNDDDDD8ZZ7$$$7I7III7??I?????+++=+~.                      
       ...8OZII?III7II77777I$I7II???7I??+?I?I?+?+IDNN8??++=...                  
     ....OOIIIII????II?I??II?I????I?????=?+Z88O77ZZO8888OO?++,......            
      ..OZI7III??II??I??I?7ODM8NN8O8OZO8DDDDDDDDD8DDDDDDDDNNNOZ= ......   ..    
     ..OZI?II7I?????+????+IIO8O8DDDDD8DNMMNNNNNDDNNDDDNDDNNNNNNDD$,.........    
      ,ZII77II?III??????DO8DDD8DNNNNNDDMDDDDDNNDDDNNNDNNNNDNNNNDDNDD+.......   .
      7Z??II7??II??I??IOMDDNMNNNNNDDDDDMDDDDNDDNNNNNDNNNNDNNDMNNNNNDDD,......   
 .  ..IZ??IIIII777?I?8NNNNNNNNNDDDDDDDDNDDDDDNNMMMDNDMMNNDNNDMNNNNNNDDDD.....   
      .$???I7IIIIIIINNNNNNNNNNNDDNDDDDDD8DDDDNM888888888DNNNNNNDNNNNNNDDO.....  
       $+??IIII?II?NNNNNMMMMMDN8DNNNDDDDZDDNN?D88I==INNDDDNNDNMNNMNNNNND8:..... 
   ....$+??III??I+NNNNNMMM88D88D88888DDDZDDMND88==+=NNNNMDDNNNNNNMMNNNNND8......
.......8=+????III8NNNNMMMDD8I=~+ONN8D8NDODNMN8DNDNNNNNNNM8DNNNNNNMNNNNDDD8..... 
. ......O=??IIIIIMNNNMMMDDD?+=?ONNNN888NMDDM88MNNNNNNNNNMDDNNNMNNNMMNDNND8......
........,+++???IINNNNNMMDDMDNMNDNMNNM8ONMDDM88NNNNNN+==ND8NNNDMNMNNNNNDDD8......
......,,,:++??I?ONNNNNMDDDMNNNNNNNNMM88NMDDNN88MNDN==~MD8DNNNNNMNMNNNDND8O......
....,,,,:::+??IIONNNNNNNDDMNNNNNO+?MN88DN8DDD888DNMMM888DNDNNNNMMMNNDDDD8,.... .
...,,,,::::~+?+?NNNNNNNMD8DNNN++++MNO8D88NNMODD8O88888DDDDDDNNMMMNNNDDD8........
..,,,,:::~~~=+??MNNNNNNNND88MNMMMD888NNNNNNNMODDDDDDDDND8DDDNNNNNNDDD8,.........
..,,,,:::~~~=++?NMNNNNNNND8888888O8DNNNNNNMMMNDDDDDDNMMNDDDOO+~~::,,,.......... 
..,,,:::~~~~==+?NNNDDNDNDDNDDDDDDDDNNND88OOZZ$8DDMNDZNZDZ7I?++~::,,,............
..,,,::::~~~~==7DDNNDDD8DDDDDDDD8DD888OOOZZ$$$7777OOZZZ$7I?++=~~:,,,.........   
..,,,,::::~~~~=+8NNNNNDDDMMMNNNNNDOOOOZZZ$$$77777777777II?++==~::,,,......  . ..
...,,,,::::~~~~=I8DNNN8DDNZOM$ZDOOZZZZ$$$7777IIIIIIIII???++==~~::,,........  .  
....,,,,:::::~~~~+=++?I$$ZZOZZZZZ$$$$$777IIII?????????+++==~~:::,,,...... ..    
.....,,,,:::::~~~~~==+?II777$$$$77777IIII????+++++++=====~~~:::,,,........      
......,,,,,:::::~~~~==++??IIIIIIIII?????++++=======~~~~~~:::,,,,,,.......       
.......,,,,,,,::::~~~~==+++???????+++++=====~~~~~~::::::::,,,,,..........       
.........,,,,,,,,::::~~~======+======~~~~~~:::::::::,,,,,,,,............        
  .........,.,,,,,,,,::::~~~~~~~~~~:::::::::,,,,,,,,,,,...............          
   ..........,..,,,,,,,,,,::::::::::,,,,,,,,,.,....................             
     .................,,,,,,,,,,,,,,,,.......................                   
       .................................................                        
           ....................................                                 
               ....................   .                                         
                                                                                
                                                                                
                                                                 GlassGiant.com
                                                                 */
