package mvc;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

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
 */ 

//Singeton pattern
public class Controller implements KeyListener {
        
	   private static boolean KeyAPressed= false;
	   private static boolean KeySPressed= false;
	   private static boolean KeyDPressed= false;
	   private static boolean KeyWPressed= false;
	   private static boolean WasdControll = true;
	   private static boolean KeySpacePressed= false;
	   private static boolean gameOver = false;
	   private static boolean gameOverPrinted = false;
	   
	   private static final Controller instance = new Controller();
	   
	 public Controller() { 
	}
	 
	 public static Controller getInstance(){
	        return instance;
	    }
	   
	@Override
	// Key pressed , will keep triggering 
	public void keyTyped(KeyEvent e) { 
		 
	}
	
	public boolean getWASDControl() {
		return WasdControll;
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{ 
		if (WasdControll) {
			switch (e.getKeyChar()) 
			{
				case 'a':setKeyAPressed(true);break;  
				case 's':setKeySPressed(true);break;
				case 'w':setKeyWPressed(true);break;
				case 'd':setKeyDPressed(true);break;
				case 'A':setKeyAPressed(true);break;  
				case 'S':setKeySPressed(true);break;
				case 'W':setKeyWPressed(true);break;
				case 'D':setKeyDPressed(true);break;
				case 'c':setWASDControll(true);break;
				case 'C':setWASDControll(true);break;
				case 'v':setWASDControll(false);break;
				case 'V':setWASDControll(false);break;
				case ' ':setKeySpacePressed(true);break;   
			    default:
			    	//System.out.println("Controller test:  Unknown key pressed");
			        break;
			} 
		}
		else {
			switch (e.getKeyCode()) 
			{
				case KeyEvent.VK_LEFT: setKeyAPressed(true);break;  
				case KeyEvent.VK_DOWN: setKeySPressed(true);break;
				case KeyEvent.VK_UP: setKeyWPressed(true);break;
				case KeyEvent.VK_RIGHT: setKeyDPressed(true);break;
				case 'c':setWASDControll(true);break;
				case 'C':setWASDControll(true);break;
				case 'v':setWASDControll(false);break;
				case 'V':setWASDControll(false);break;
				case ' ':setKeySpacePressed(true);break;   
			    default:
			    	//System.out.println("Controller test:  Unknown key pressed");
			        break;
			}  
		}	
	}
	
	public void reset() {
		gameOver = false;
		gameOverPrinted = false;
		KeyAPressed= false;
		KeySPressed= false;
		KeyDPressed= false;
		KeyWPressed= false;
		KeySpacePressed= false;
	}
	
	public void setGameOver() {
		gameOver = true;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public void setGameOverPrinted(boolean printed) {
		gameOverPrinted = printed;	
	}
	
	public boolean getGameOverPrinted() {
		return gameOverPrinted;
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		if (WasdControll) {
			switch (e.getKeyChar()) 
			{
				case 'a':setKeyAPressed(false);break;  
				case 's':setKeySPressed(false);break;
				case 'w':setKeyWPressed(false);break;
				case 'd':setKeyDPressed(false);break;
				case 'A':setKeyAPressed(false);break;  
				case 'S':setKeySPressed(false);break;
				case 'W':setKeyWPressed(false);break;
				case 'D':setKeyDPressed(false);break;
				case ' ':setKeySpacePressed(false);break;   
			    default:
			    	//System.out.println("Controller test:  Unknown key pressed");
			        break;
			} 
		}
		else {
			switch (e.getKeyCode()) 
			{
				case KeyEvent.VK_LEFT: setKeyAPressed(false);break;  
				case KeyEvent.VK_DOWN: setKeySPressed(false);break;
				case KeyEvent.VK_UP: setKeyWPressed(false);break;
				case KeyEvent.VK_RIGHT: setKeyDPressed(false);break;
				case ' ':setKeySpacePressed(false);break;   
			    default:
			    	//System.out.println("Controller test:  Unknown key pressed");
			        break;
			} 
		}		
	}


	public boolean isKeyAPressed() {
		return KeyAPressed;
	}


	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}


	public boolean isKeySPressed() {
		return KeySPressed;
	}


	public void setKeySPressed(boolean keySPressed) {
		KeySPressed = keySPressed;
	}


	public boolean isKeyDPressed() {
		return KeyDPressed;
	}


	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}

	public void setWASDControll(boolean wasdControll) {
		WasdControll = wasdControll;
	}


	public boolean isKeyWPressed() {
		return KeyWPressed;
	}


	public void setKeyWPressed(boolean keyWPressed) {
		KeyWPressed = keyWPressed;
	}


	public boolean isKeySpacePressed() {
		return KeySpacePressed;
	}


	public void setKeySpacePressed(boolean keySpacePressed) {
		KeySpacePressed = keySpacePressed;
	} 
	
	 
}

/*
 * 
 * KEYBOARD :-) . can you add a mouse or a gamepad 

 *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@

  @@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@     @@@     @@@     @@@     @@@     @@@     @@@  

  @@@     @@@     @@@     @@@@    @@@@     @@@     @@@     @@@     @@@     @@@  

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@     @@@     @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@     @@@   W   @@@     @@@      @@      @@@     @@@     @@@     @@@     @@@     @

@@    @@@@     @@@@    @@@@    @@@@    @@@@     @@@     @@@     @@@     @@@     @

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@N@@@@@@@@@@@@@@@@@@@@@@@@@@@

@@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@    

@@@   A   @@@  S     @@  D     @@      @@@     @@@     @@@     @@@     @@@     @@@    

@@@@ @  @@@@@@@@@@@@ @@@@@@@    @@@@@@@@@@@@    @@@@@@@@@@@@     @@@@   @@@@@   

    @@@     @@@@    @@@@    @@@@    $@@@     @@@     @@@     @@@     @@@     @@@

    @@@ $   @@@      @@      @@ /Q   @@ ]M   @@@     @@@     @@@     @@@     @@@

    @@@     @@@      @@      @@      @@      @@@     @@@     @@@     @@@     @@@

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

@       @@@                                                @@@       @@@       @

@       @@@              SPACE KEY       @@@        @@ PQ     

@       @@@                                                @@@        @@        

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 * 
 * 
 * 
 * 
 */
