package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();     // Sets "key" to a number binding that corresponds to the letter key that was just pressed
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {     // Only goes into the "if" statement if the object has the ID of "Player"
				//key events for player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(-5);     // Check if we're hitting W, and move up
				if(key == KeyEvent.VK_S) tempObject.setVelY(5);      // Check if we're hitting S, and move down
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);      // Check if we're hitting D, and move right
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);     // Check if we're hitting A, and move left
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {     // Only goes into the "if" statement if the object has the ID of "Player"
				//key events for player 1
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
			}
		}
	}
}
