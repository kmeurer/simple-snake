package components;

import game.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.border.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.Snake.Direction;

public class Board extends JPanel {
	/* PROPERTIES */
	private enum UnitTypes {SNAKE, EMPTY, WALL, FOOD}; 	// enum used to track where things are on our board
	private UnitTypes[][] positions;					// matrix used to track position of objects on the board
	private Snake snake;								// snake used to plot the snake
	private SnakeGame game;								// game used to link to the game, which manages the scoreboard
	private LinkedList<int[]> snakePositions;
	
	/* CONSTANTS */
	private final int B_WIDTH = 400;
	private final int B_ROW_COUNT = 25; 				// create n x n board
	private final int SNAKE_START_ROW = 10;
	private final int SNAKE_START_COL = 10;
	private final int UNIT_WIDTH = (int)B_WIDTH / B_ROW_COUNT;
	private final int UP_KEY = 38;
	private final int DOWN_KEY = 40;
	private final int LEFT_KEY = 37;
	private final int RIGHT_KEY = 39;
	private final int PAUSE_KEY = 32;
	
	
	public Board(SnakeGame thisGame) {
		this.game = thisGame;
		this.positions = new UnitTypes[B_ROW_COUNT][B_ROW_COUNT];
		for (int i = 0; i < B_ROW_COUNT; i++){
			for (int j = 0; j < B_ROW_COUNT; j++){
				this.positions[i][j] = UnitTypes.EMPTY;
			}
		}
		snake = new Snake(this, SNAKE_START_ROW, SNAKE_START_COL);
		this.positions[SNAKE_START_ROW][SNAKE_START_COL] = UnitTypes.SNAKE;
		this.snakePositions = new LinkedList<int[]>();
		snakePositions.addFirst(snake.getHeadIndices());
		initListeners();
		initGUI();
	}
	
	public void playGame(){
		while(game.isRunning()){
			moveSnake();
			repaint();
        	
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	public void initGUI(){
		
		setBackground(new java.awt.Color(250, 250, 250));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		Dimension boardSize = new Dimension(B_WIDTH, B_WIDTH);
		setSize(boardSize);
		setVisible(true);
	}
	
	/* initListeners - setup keyListeners and add them to the 
	 * JPanel (the board)
	 * 
	 * @param none
	 * @return none
	 * 
	 */
	public void initListeners() {
		KeyListener listener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == UP_KEY){
					snake.changeDirection(Direction.UP);
				} else if (e.getKeyCode() == DOWN_KEY){
					snake.changeDirection(Direction.DOWN);
				} else if (e.getKeyCode() == RIGHT_KEY){
					snake.changeDirection(Direction.RIGHT);
				} else if (e.getKeyCode() == LEFT_KEY) {
					snake.changeDirection(Direction.LEFT);
				} else if (e.getKeyCode() == PAUSE_KEY){
					// TODO pause game 
				}
			}
		};
		addKeyListener(listener);
		
		// The keyboard can only listen on the focused component.
		// Here, we set the focus to the JPanel that the keyboard
		//listening to.
		setFocusable(true);

	}
	
	@Override
	public void paint(Graphics theGraphic) {
		super.paint(theGraphic);
		Graphics2D the2DGraphic = (Graphics2D) theGraphic;
		the2DGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		snake.paint(the2DGraphic);
//		racquet.paint(the2DGraphic);
	}
	
	/*
	 * moveSnake() - moves the snake using the snake's move method and updates board management accordingly
	 * @params none
	 * @return none
	 * 
	 */
	public void moveSnake(){
		// call the snake function to actually make the move
		snake.move();
		// update board position data
		int[] currentHeadPosition = snake.getHeadIndices();									// store the indices of the new head
		if (validPosition(currentHeadPosition)){
			snakePositions.addFirst(currentHeadPosition);										// add indices to our snake positions
			int[] lastPos = snakePositions.removeLast();										// remove the tail from our snake positions
			this.positions[lastPos[0]][lastPos[1]] = UnitTypes.EMPTY;							// set last position to empty
			this.positions[currentHeadPosition[0]][currentHeadPosition[1]] = UnitTypes.SNAKE;	// set new head to be a snake
		} else {
//			game.gameOver();
		}
	}
	
	/*
	 * validPosition() - determines if the indices passed in are a valid position for the snake to move
	 * @params - a tuple of indices [row, col] that is cross-referenced with locations in our positions array
	 * @return - a boolean indicating if the position is valid.
	 */
	private boolean validPosition(int[] indices){
		int row = indices[0]; // store row variable for convenience. this is the row index
		int col = indices[1]; // store col variable for convenience. this is the col index
		// first test if it is outside of our bounds
		if ( row < 0 || col < 0 || row > B_ROW_COUNT - 1 || col > B_ROW_COUNT - 1 ){
			return false;
		} else if (this.positions[row][col] != UnitTypes.EMPTY || this.positions[row][col] != UnitTypes.FOOD){
			return false;
		} else {
			return true;
		}
	}
	
	public int getUnitWidth(){
		return UNIT_WIDTH;
	}
	
	
	
}
