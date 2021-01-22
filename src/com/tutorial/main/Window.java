package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -240840600533728354L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);     // JFrame is the frame of the window (java built-in library) (saved under variable name "frame")
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // This basically allows the "X" button in the top right to close the program
		frame.setResizable(false);     // Can the window be resizable? In this case, we have false, which means no, it is not resizable
		frame.setLocationRelativeTo(null);     // Game starts in the middle of the screen, rather than top left
		frame.add(game);     // Adds the game class to the frame
		frame.setVisible(true);     // Can actually see the frame, "true"
		game.start();     // Running the start method that was created in the game class
		
		
	}

}
