/*
 * MAIN GAME CLASS: This class manages the game state.  It imports a number of game components from the gamecomponents package
 */
package game;

import components.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

public class SnakeGame extends JFrame implements Runnable{
	
	private Board board;
	private Scoreboard scoreboard;
	private TitlePage titlePage;
	private GameOver gameOverPage;
	private boolean gameRunning;
	private Thread gameRunner;

	public SnakeGame() {
		gameRunning = false; 
		
		initGUI();
	}
	
	void initGUI(){
		// create a new TitlePage and add it to the GUI. This is initially the only instantiated item
		titlePage = new TitlePage(this);
		add(titlePage);
		
//		board = new Board(this);			// create a new board that manages the game
		
		setBackground( new Color(108, 122, 137) );
		setResizable(false);  			// User can't change the window's size.
		setLocation(100,100);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize(750, 500);
	}
	/**
	 * 
	 */
	
	public void changeToBoardView(){
		if (titlePage != null){
			getContentPane().remove(titlePage); 	// remove the title page from the GUI
			titlePage = null;	
		}
		if (gameOverPage != null){
			// remove the page if it exists
			getContentPane().remove(gameOverPage);
			gameOverPage = null;	// set the page to null
		}
		scoreboard = new Scoreboard(this);		// create a new scoreboard to track the game's score
		board = new Board(this);
		validate(); // validate and repaint to reflect the removal of old panels
		repaint();
		add(board);		// add board to the frame
		add(scoreboard, BorderLayout.EAST);	// add scoreboard to the frame
	}
	
	public void startGame(final int speedLvl){	
		gameRunning = true;
		gameRunner = new Thread(new Runnable(){
			@Override
			public void run(){
				board.playGame(speedLvl);
			}
		});
		gameRunner.start();
	}
	
	public void incrementScore(int inc){
		scoreboard.incrementScore(inc);
	}
	
	/*
	 * isRunning() External function used to track whether the game is running
	 * @params none
	 * @return a boolean indicating whether or not the game is running
	 */
	public boolean isRunning(){
		return gameRunning;
	}
	
	/*
	 * gameOver() - ends the current game in progress and displays the game over screen.
	 * @params none
	 * @return none
	 */
	public void gameOver(){
		System.out.println("over");
		gameRunning = false;
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	getContentPane().remove(board);
            	validate();
            	repaint();
            }
		});
		
	}
	
	public static void main(String[] args){
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                SnakeGame game = new SnakeGame();
	                game.setVisible(true);
	            }
	        });
	}

	@Override
	public void run() {
		gameRunner = new Thread();
		
	}
}