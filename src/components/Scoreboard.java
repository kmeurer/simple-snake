package components;
import game.SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Scoreboard extends JPanel {
	private int score;
	private int time;
	private SnakeGame game;
	
	private int scoreboardWidth;
	private int scoreboardHeight;
	
	/*
	 * Constructor for the scoreboard
	 */
	public Scoreboard(SnakeGame theGame) {
		this.game = theGame;						// store reference to the game
		scoreboardHeight = this.game.FRAME_HEIGHT; 	// set scoreboard height
		scoreboardWidth = this.game.FRAME_WIDTH - this.game.BOARD_WIDTH; // set scoreboard width
		score = 0;
		time = 0;
		
		initGUI(); // initialize the UI
	}
	
	/*
	 * initialize our JPanel
	 */
	public void initGUI(){
		// set a background color
		setBackground(new java.awt.Color(250, 250, 250));
		// create a border around it
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		// create dimension to store board size
		Dimension boardSize = new Dimension(scoreboardHeight, scoreboardWidth);
		setSize(boardSize);
		setMaximumSize(boardSize);
		setVisible(true);
	}
	
	/* incrementScore - add to the score
	 * @param inc the number of integers to increment the score by
	 * 
	 */
	public void incrementScore(int inc){
		score += inc;
	}
	
	public int getScore(){
		return this.score;
	}

	public void incrementTimer(int inc){
		time += inc;
	}
}
