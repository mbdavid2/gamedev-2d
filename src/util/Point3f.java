package util;
/*
 * Modified by Abraham Campbell on 15/01/2020.
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
//Modified from Graphics 3033J course point class  by Abey Campbell 

import java.util.ArrayList;

public class Point3f {

	private float x;
	private float y;
	private float z;
	
	private int boundary = 1280;
	
	
	// default constructor
	public Point3f() { 
		setX(0.0f);
		setY(0.0f);
		setZ(0.0f);
	}
	
	//initializing constructor
	public Point3f(float x, float y, float z) { 
		this.setX(x);
		this.setY(y);
		this.setZ(z); 
	}
	
	private void setBoundary(int boundary) {
		this.boundary = boundary;
		
	}

	// sometimes for different algorithms we will need to address the point using positions 0 1 2 
	public float getPostion(int postion)
	{
		switch(postion)
		{
		case 0: return getX();
		case 1: return getY();
		case 2: return getZ(); 
		default: return Float.NaN;  
		} 
	}
	
	public String toString()
	{
		return ("(" + getX() +"," + getY() +"," + getZ() +")");
    }



	 //implement Point plus a Vector and comment what the method does 
	public Point3f PlusVector(Vector3f Additonal) { 
		return new Point3f(this.getX()+Additonal.getX(), this.getY()+Additonal.getY(), this.getZ()+Additonal.getZ());
	} 
	
	 //implement Point minus a Vector and comment what the method does 
	public Point3f MinusVector(Vector3f Minus) { 
		return new Point3f(this.getX()-Minus.getX(), this.getY()-Minus.getY(), this.getZ()-Minus.getZ());
	}
	
	
	/// implement Point - Point  and comment what the method does  
	public Vector3f MinusPoint(Point3f Minus) { 
		return new Vector3f(this.getX()-Minus.getX(), this.getY()-Minus.getY(), this.getZ()-Minus.getZ());
	}
	 
	
	
	 //Use for direct application of a Vector 
	public void ApplyVector(Vector3f vector) { 
		 setX(CheckBoundary(this.getX()+vector.getX()));
		 setY(CheckBoundary(this.getY()-vector.getY()));
		 setZ(CheckBoundary(this.getZ()-vector.getZ())); 
	}
	
	public void ApplyVector(Vector3f vector, ArrayList<GameObject> obstacles, boolean playerOnUpper, int lastMovingDirection) { 
		 setX(CheckBoundary(this.getX()+vector.getX(), obstacles, playerOnUpper, lastMovingDirection, this.getX()));
		 setY(CheckBoundary(this.getY()-vector.getY()));
		 setZ(CheckBoundary(this.getZ()-vector.getZ())); 
	}

	private float CheckBoundary(float f) {
		if (f<0) f=0.0f;
		if (f>boundary*19/20)f=(float) boundary*19/20;
		return f;
	}
	
	private float CheckBoundary(float f, ArrayList<GameObject> obstacles, boolean playerOnUpper, 
								int lastMovingDirection, float current) {
		if (f<0) f=0.0f;
		if (f> boundary*19/20)f=(float) boundary*19/20;
		
		// Check for obstacles too
		for (GameObject obj : obstacles) {
//			System.out.println(obj.getObjectOnUpper() + " " + playerOnUpper);
			if (obj.getObjectOnUpper() == playerOnUpper) {
				int left = obj.getLeftBoundary();
				int right = obj.getRightBoundary();
//				System.out.println("Left: " + left);
//				System.out.println("Right: " + right);
//				System.out.println("Current: " + current);
//				System.out.println("Prev: " + lastMovingDirection + " | F: " + f);
				if (current <= left && f >= left && lastMovingDirection == 1) f = current;
				if (current >= right && f <= right && lastMovingDirection == -1) f = current;
			}
		}
		return f;
	}
	
	public Point3f copy() {
		return new Point3f(x, y, z);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	
	  
	  
	 // Remember point + point  is not defined so we not write a method for it.  
}

/*................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
................................................................................
....................................=?7777+.....................................
.............................,8MMMMMMMMMMMMMMMMM7...............................
...........................$MMMMMMMMMMMMMMMMMMMMMM7.............................
........................IMMMMMMMMMMMMMMMMMMMMMMMMMMMM?..........................
......................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN........................
.....................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$......................
...................ZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8....................
.................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM...................
................IMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$.................
...............=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ................
..............:MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
..............7MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM:...............
..............DMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ...............
..............MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8...............
..............MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN...............
..............NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO...............
..............$MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMI...............
..............+MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM=...............
...............8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM8................
................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN,................
................=MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................
.................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ..................
..................MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMD...................
...................?MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM.....................
....................8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM......................
.....................,8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM,.......................
........................NMMMMMMMMMMMMMMMMMMMMMMMMMMMMN?.........................
..........................NMMMMMMMMMMMMMMMMMMMMMMMMMI...........................
.............................$MMMMMMMMMMMMMMMMMMM?..............................
.................................,I$NMMMMMN$?...................................
................................................................................
................................................................................
................................................................................
.......................................................................*/
