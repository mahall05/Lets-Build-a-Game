package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Shop extends MouseAdapter {
	
	Handler handler;
	HUD hud;
	Game game;
	
	public int setPrice;
	
	private int B1;
	private int B2;
	private int B3;
	
	public Shop(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		
		if(game.diff == 0) {
			setPrice = 100;
		}else if(game.diff == 1) {
			setPrice = 150;
		}
		
		B1 = setPrice;
		B2 = setPrice;
		B3 = setPrice;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("SHOP", Game.WIDTH/2-100, 50);
		
		g.setFont(new Font("arial", 0, 12));
		
		// Box 1
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost: " + B1, 110, 140);
		g.drawRect(100, 100, 100, 80);
		
		// Box 2
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Cost: " + B2, 260, 140);
		g.drawRect(250, 100, 100, 80);
		
		// Box 3
		g.drawString("Refill Health", 410, 120);
		g.drawString("Cost: " + B3, 410, 140);
		g.drawRect(400, 100, 100, 80);
		
		g.drawString("SCORE: " + hud.getScore(), Game.WIDTH/2-50, 300);
		g.drawString("Press Space to go Back: " + hud.getScore(), Game.WIDTH/2-50, 330);
	
		
	}
	
	public void mousePressed(MouseEvent e) {
		if(Game.gameState == STATE.Shop) {
			
		
			int mx = e.getX();
			int my = e.getY();
			
			// box 1
			if(mx >= 100 && mx <= 200) {
				if(my >= 100 && my <= 180) {
					//you've selected box 1
					if(hud.getScore() >= B1) {
						hud.setScore(hud.getScore() - B1);
						B1 += setPrice;	
						hud.bounds += 20;
						hud.HEALTH = (100 + (hud.bounds/2));
					}
				}
			}
			
			// box 2
			if(mx >= 250 && mx <= 350) {
				if(my >= 100 && my <= 180) {
					//you've selected box 2
					if(hud.getScore() >= B2) {
						hud.setScore(hud.getScore() - B2);
						B2 += setPrice;
						handler.spd++;
					}
				}
			}
					
			// box 3
			if(mx >= 400 && mx <= 500) {
				if(my >= 100 && my <= 180) {
					//you've selected box 3
					if(hud.getScore() >= B3) {
						hud.setScore(hud.getScore() - B3);
						hud.HEALTH = (100 + (hud.bounds/2));
					}
				}
			}
		}
		
	}
		
}
