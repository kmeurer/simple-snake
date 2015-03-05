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
	
	public final int FRAME_WIDTH = 750;
	public final int FRAME_HEIGHT = 505;
	
	/* PUBLIC SETTINGS */
	public final String FONT_NAME = "Helvetica";
	public final int BOARD_WIDTH = 480;
	public final int BOARD_ROW_COUNT = 20; 				// create n x n board
	
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
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
		validate(); 	// validate and repaint to reflect the removal of old panels
		repaint();		// repaint the board
		add(board);		// add board to the frame
		add(scoreboard, BorderLayout.EAST);	// add scoreboard to the frame
	}
	
	/*
	 * startGame sets up a new thread to manage the game
	 */
	public void startGame(final int speedLvl, final boolean walls){	
		gameRunning = true;						// the game is now running
		// here we set up a thread to manage the game
		gameRunner = new Thread(new Runnable(){
			@Override
			public void run(){
				board.playGame(speedLvl, walls);
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
	public void endGame(){
		gameRunning = false;
		final SnakeGame thisGame = this;
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	getContentPane().remove(board);
            	getContentPane().remove(scoreboard);
            	board = null;
            	gameOverPage = new GameOver(thisGame, scoreboard.getScore());
            	scoreboard = null;
            	add(gameOverPage);
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