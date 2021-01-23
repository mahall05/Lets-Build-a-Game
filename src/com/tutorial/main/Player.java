package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();

	public Player(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x,  0, Game.WIDTH - 48);
		y = Game.clamp(y,  0, Game.HEIGHT - 70);
	}

	public void render(Graphics g) {
		g.setColor(Color.white); 
		g.fillRect(x,  y, 32, 32);
	}
	
	

}
