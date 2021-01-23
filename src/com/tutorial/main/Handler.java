// Maintain, update,  and render the objects in the room

package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();     // Make a list of all the game objects in the game
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {     // Loop through every single game object
			GameObject tempObject = object.get(i);     // Allows us to get the ID of what object we are at
			
			
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
		
	}
	
	public void addObject(GameObject object) {     // This handles adding objects to the list
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {     // This handles removing objects from the list
		this.object.remove(object);
	}

}
