/*
 * Project 2
 * Name: Kevin Meurer
 * E-mail: kevin.a.meurer@gmail.com
 * Instructor: Singh
 * COSC 150
 * 
 * In accordance with the class policies and Georgetown's Honor Code,
 * I certify that, with the exceptions of the lecture and Blackboard
 * notes and those items noted below, I have neither given nor received
 * any assistance on this project.
 * 
 * Other Sources:  The Swing/Java docs were huge.  I also was inspired to use KeyBindings rather than key listeners from a number of stack overflow questions(I hope that's right)
 *
 * Additional Functionality: I implemented extra difficulty levels(with different speeds), a timer on my scoreboard, a welcome and game over screen, and food that has a different color every time!
 * 
 * Usability study: My feedback was positive!  I've included it elsewhere, but here is the link to the responses: https://docs.google.com/a/georgetown.edu/spreadsheets/d/1PpDw9Hb8IosW505HYzb3BlegKcp8vLQvEmojxxLmma4/edit#gid=1424467329
 *
 * Description: Welcome to my Snake Program!  This is the main game class.  It serves as the driver for all of my different views
 * across the game.  When the game is running, it also connects my board class(the class containing most of the game code) with my scoreboard.  When the game starts,
 * it creates a TitlePage, which displays a brief description in addition to options for difficulty levels.  The easy and Intermediate levels do NOT have any walls, while the Advanced
 * and impossible levels do.  When a game mode is selected, it instantiates a new Board and a new scoreboard.  The board is started using a separate thread called gameRunner.  I did this
 * so the computation required for the game would not interfere with updating the GUI.
 * 
 * In the board class, you will find links to Food, Wall, and Snake Classes, which are all instantiated as needed.  Initially, only a snake and food object is instantiated.
 * Walls are instantiated whenever the user hits a food item.  The game ends as expected, when the user hits itself, moves outside the box, or hits a wall.  I included both arrow key and wasd
 * movement support.  The board class employs an enum of unit types to track positions on the board and the Snake also uses a separate enum to track Directions.
 * 
 * When the user makes a mistake, the game ends by breaking out of the while loop and calling the gameOver function(in this file), which ends the game and displays
 * a game over JPanel (a separate class).  I implemented parameterized tests for my Snake class and for my board class.
 * 
 * Thank you for grading my program!
 */
 
/*
 * MAIN GAME CLASS: This class manages the game state.  It imports a number of game components from the gamecomponents package
 */
package game;

// IMPORTS
import components.*;
import java.awt.*;
import javax.swing.*;

public class SnakeGame extends JFrame {
	
	/*PROPERTIES */
	private Board board;				// used to keep track of the board which manages the game
	private Scoreboard scoreboard;		// scoreboard panel to display and manage the score
	private TitlePage titlePage;		// title page. the first instantiated jpanel
	private GameOver gameOverPage;		// jpanel displayed when game ends	
	private boolean gameRunning;		// bool that determines whether the game is running
	private Thread gameRunner;			// thread used to run the game
	
	public final int FRAME_WIDTH = 700;	// width of our frame
	public final int FRAME_HEIGHT = 503;// height of our frame
	
	/* PUBLIC SETTINGS */
	public final String FONT_NAME = "Helvetica";
	public final int BOARD_WIDTH = 480;
	public final int BOARD_ROW_COUNT = 20; 				// create n x n board
	
	/* CONSTRUCTOR */
	public SnakeGame() {
		// game initialy not running
		gameRunning = false; 
		// init our GUI
		initGUI();
	}
	
	/*
	 * initGUI - sets up our frame and sets it to visible
	 */
	void initGUI(){
		// use border layout for our main frame
		setLayout(new BorderLayout());
		
		// create a new TitlePage and add it to the GUI. This is initially the only instantiated item
		titlePage = new TitlePage(this);
		add(titlePage);
		
		setBackground( new Color(30, 130, 76) );	// set a green background
		setResizable(false);  						// User can't change the window's size.
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	// exit when we close
		setSize(FRAME_WIDTH, FRAME_HEIGHT);					// set size of JFrame
		// set it to visible
		setVisible(true);
	}
	
	/**
	 * changeToBoardView - cycles away from title or game over page and paints the board
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
	 * @params speedLvl - the level of speed for the game, walls - whether te game will include walls
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
		gameRunner.start(); // start the thread, which starts the game
	}
	
	/*
	 * incrementScore - adds a specific amount to the score
	 * @params inc-the amount by which to increment the score 
	 */
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
            	// remove our board and scoreboard
            	getContentPane().remove(board);
            	getContentPane().remove(scoreboard);
            	// create a game over page
            	gameOverPage = new GameOver(thisGame, scoreboard.getScore());
            	// set our board and scoreboard to null
            	board = null;
            	scoreboard = null;
            	// add the game over page
            	add(gameOverPage);
            	// validate and repaint
            	validate();
            	repaint();
            }
		});	
	}
	
	/*
	 * Main runner - instantiates a new game.
	 */
	public static void main(String[] args){
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                // create our game and set it to visible
	            	SnakeGame game = new SnakeGame();
	            }
	        });
	}
}