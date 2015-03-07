/*
 * MAIN GAME CLASS: This class manages the game state.  It imports a number of game components from the gamecomponents package
 */
package game;

import components.*;

import java.awt.*;

import javax.swing.*;

public class SnakeGame extends JFrame {
	
	private Board board;				// used to keep track of the board which manages the game
	private Scoreboard scoreboard;		// scoreboard panel to display and manage the score
	private TitlePage titlePage;		// title page. the first instantiated jpanel
	private GameOver gameOverPage;		// jpanel displayed when game ends	
	private boolean gameRunning;		// bool that determines whether the game is running
	private Thread gameRunner;			// thread used to run the game
	
	public final int FRAME_WIDTH = 700;
	public final int FRAME_HEIGHT = 503;
	
	/* PUBLIC SETTINGS */
	public final String FONT_NAME = "Helvetica";
	public final int BOARD_WIDTH = 480;
	public final int BOARD_ROW_COUNT = 20; 				// create n x n board
	
	public SnakeGame() {
		gameRunning = false; 
		
		initGUI();
	}
	
	void initGUI(){
		
		setLayout(new BorderLayout());
		
		// create a new TitlePage and add it to the GUI. This is initially the only instantiated item
		titlePage = new TitlePage(this);
		add(titlePage);
		
		
		
		setBackground( new Color(30, 130, 76) );
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
		this.add(board, BorderLayout.EAST);		// add board to the frame
		this.add(scoreboard, BorderLayout.EAST);	// add scoreboard to the frame
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
		gameRunning = false;	// set gameRunning to false
		final SnakeGame thisGame = this;	// store reference to the current game
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
}