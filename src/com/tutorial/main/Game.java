package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;     // Setting the size of the window
	
	private Thread thread;     // The game runs within this thread
	private boolean running = false;
	
	public static boolean paused = false;
	public int diff;
	
	// 0 = normal
	// 1 = hard
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
	public enum STATE{
		Menu,
		Select,
		Help,
		Shop,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public static BufferedImage sprite_sheet;
	
	public Game() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		sprite_sheet = loader.loadImage("/sprite_sheet.png");

		
		
		
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud, this);
		menu = new Menu(this, handler, hud, shop);
		this.addKeyListener(new KeyInput(handler, this));     // This just tells the game to start "listening" for key presses
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
		
		spawner = new Spawn(handler, hud, this);
		r = new Random();
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		}else {
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH - 16), r.nextInt(HEIGHT - 16), ID.MenuParticle, handler));
			}
		}
		
	}

	public synchronized void start() {
		thread = new Thread(this);     // Initializing thread as a new thread. "this" refers to this instance of the game class
		thread.start();     // Start the thread
		running = true;     // Thread is running
	}
	
	public synchronized void stop() {
		try {
			thread.join();     // Stops the thread
			running = false;     // Thread is not running
		}catch(Exception e) {
			e.printStackTrace();     // Run an error message
		}
	}
	
	public void run() {     // The game loop
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;     // The amount of ticks/second
		double ns = 1000000000 / amountOfTicks;     // The amount of nanoticks/tick
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		if(gameState == STATE.Game) {
			
			if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					for(int i = 0; i < 20; i++) {
						handler.addObject(new MenuParticle(r.nextInt(WIDTH - 16), r.nextInt(HEIGHT - 16), ID.MenuParticle, handler));
					}
				}
			}
			
			
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
			menu.tick();
			handler.tick();
		}
		
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);     // The number of buffers it creates (in this case, 3)
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused) {
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}else if(gameState == STATE.Shop) {
			shop.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
			menu.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]) {
		new Game();
		
	}

}
