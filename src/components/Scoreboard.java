package components;
import game.SnakeGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Scoreboard extends JPanel {
	private int score;
	private int time;
	private SnakeGame game;
	private JLabel scoreValue;
	private JLabel timeValue;
	
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
		// Initialize the GUI from our swing event thread
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				initGUI(); // initialize the UI
			}
		});
	}
	
	/*
	 * initialize our JPanel
	 */
	public void initGUI(){
		
		// set a background color
		setBackground(new java.awt.Color(0, 0, 0));
		// create a border around it
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		// create bounds for the scoreboard
		Dimension boardSize = new Dimension(scoreboardWidth, scoreboardHeight);
		setSize(boardSize);
		setMaximumSize(boardSize);
		setMinimumSize(boardSize);
		setPreferredSize(boardSize);
		
		JLabel score = new JLabel("Score");
		score.setFont(new Font(game.FONT_NAME, Font.BOLD, 45));
        score.setHorizontalAlignment(0);
        score.setForeground(Color.white);
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        scoreValue = new JLabel("0");
        scoreValue.setFont(new Font(game.FONT_NAME, Font.PLAIN, 45));
        scoreValue.setHorizontalAlignment(0);
        scoreValue.setForeground(Color.white);
        scoreValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel time = new JLabel("Time");
		time.setFont(new Font(game.FONT_NAME, Font.BOLD, 45));
        time.setHorizontalAlignment(0);
        time.setForeground(Color.white);
        time.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        timeValue = new JLabel("0");
        timeValue.setFont(new Font(game.FONT_NAME, Font.PLAIN, 45));
        timeValue.setHorizontalAlignment(0);
        timeValue.setForeground(Color.white);
        timeValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		// Add our JLabels
		this.add(Box.createVerticalGlue());
		add(score);
        add(scoreValue);
        add(time);
        add(timeValue);
	    this.add(Box.createVerticalGlue());
        
		setVisible(true);
	}
	
	/* incrementScore - add to the score
	 * @param inc the number of integers to increment the score by
	 * 
	 */
	public void incrementScore(int inc){
		score += inc;
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				scoreValue.setText(score + "");
				validate();
				repaint();
			}
		});		
	}
	
	public void incrementTime(){
		time += 1;
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				timeValue.setText(time + "");
				validate();
				repaint();
			}
		});		
	}
	
	public int getScore(){
		return this.score;
	}

	public void incrementTimer(int inc){
		time += inc;
	}
}
