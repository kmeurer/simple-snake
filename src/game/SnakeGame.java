/*
 * MAIN GAME CLASS: This class manages the game state.  It imports a number of game components from the gamecomponents package
 */
package game;

import components.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SnakeGame extends JFrame {
	
	private Board board;
	private Scoreboard scoreboard;

	public SnakeGame() {
		
		board = new Board(this);
		scoreboard = new Scoreboard();
		
		initGUI();
	}
	
	void initGUI(){
				
		setBackground( new Color(108, 122, 137) );
		
		// Add our board and scoreboard to the GUI.  Each handles itself
		add(board, BorderLayout.CENTER);
		add(scoreboard);
		
		setResizable(false);  			// User can't change the window's size.
		setLocation(100,100);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize(500, 500);
	}
	/**
	 * 
	 */
	public void startGame(){
		board.playGame();
	}
	
	public void incrementScore(int inc){
		scoreboard.incrementScore(inc);
	}
	
	public static void main(String[] args){
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                SnakeGame game = new SnakeGame();
	                game.setVisible(true);
//	                game.startGame();
	            }
	        });
	}
}