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
	private TitlePage titlePage;
	private boolean gameRunning;

	public SnakeGame() {
		gameRunning = false;
		// Add our board and 
		
		initGUI();
	}
	
	void initGUI(){
		titlePage = new TitlePage(this);
		setBackground( new Color(108, 122, 137) );
		setResizable(false);  			// User can't change the window's size.
		setLocation(100,100);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize(500, 500);
	}
	/**
	 * 
	 */
	public void startGame(){
		board = new Board(this);
		gameRunning = true;
		scoreboard = new Scoreboard(this);
		add(scoreboard, BorderLayout.CENTER);
		add(board, BorderLayout.SOUTH);
		board.playGame();
	}
	
	public void incrementScore(int inc){
		scoreboard.incrementScore(inc);
	}
	
	public boolean isRunning(){
		return gameRunning;
	}
	
	public void gameOver(){
		gameRunning = false;
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	getContentPane().remove(board);
            	validate();
            	repaint();
            }
		});
		
//		JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);
		
//		System.exit(ABORT);
	}
	
	public static void main(String[] args){
//		 EventQueue.invokeLater(new Runnable() {
//	            @Override
//	            public void run() {
	                SnakeGame game = new SnakeGame();
	                game.setVisible(true);
	                game.startGame();
//	            }
//	        });
	}
}