// Every object in the game is a GameObject
// So this is just creating variables and other things that every game object will have in common

package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x, y;     // Protected means can only be accessed by objects that inherit GameObject
	protected ID id;
	protected int velX, velY;     // Variables that control speed
	
	public GameObject(int x, int y, ID id) {     // When a new GameObject is created, it will need these parameters
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();     // These are two things that are abstract so they will need to be set individually when a new object is created
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	// Getters and Setter methods:
	// Can change position by calling these methods by using the parameters
	// These are all methods that can be used by any GameObject that we create, without having to create these for every single object
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getVelX() {
		return velX;
	}
	public int getVelY() {
		return velY;
	}
	

}
