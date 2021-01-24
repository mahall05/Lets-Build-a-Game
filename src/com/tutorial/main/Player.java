package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x,  0, Game.WIDTH - 48);
		y = Game.clamp(y,  0, Game.HEIGHT - 70);
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 32, 32, 0.08f, handler));
		
		collision();
		
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {     // Run through all the objects in the game
			
			GameObject tempObject = handler.object.get(i);     // Create a temporary object. Gets each instance of the for loop
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {     // tempObject is now basic enemy
				if(getBounds().intersects(tempObject.getBounds())){     // If Player intersects with the boundaries of the enemy (tempObject)
					//collision code
					HUD.HEALTH -= 2;     // Health goes down 2
				}
					
					
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white); 
		g.fillRect((int)x,  (int)y, 32, 32);
	}
	
	

}
