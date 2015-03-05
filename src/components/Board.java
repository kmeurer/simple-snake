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
import java.util.Random;

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
	private Food food;
	
	/* CONSTANTS */
	private final int B_WIDTH = 400;
	private final int B_ROW_COUNT = 20; 				// create n x n board
	private final int SNAKE_START_ROW = 10;
	private final int SNAKE_START_COL = 10;
	private final int UNIT_WIDTH = (int)B_WIDTH / B_ROW_COUNT;
	private final int UP_KEY = 38;
	private final int DOWN_KEY = 40;
	private final int LEFT_KEY = 37;
	private final int RIGHT_KEY = 39;
	private final int PAUSE_KEY = 32;
	
	
	public Board(SnakeGame thisGame) {
		this.game = thisGame;										// create a link to the game we pass in
		this.positions = new UnitTypes[B_ROW_COUNT][B_ROW_COUNT];	// initialize positions
		for (int i = 0; i < B_ROW_COUNT; i++){						// make all positions initially empty
			for (int j = 0; j < B_ROW_COUNT; j++){
				this.positions[i][j] = UnitTypes.EMPTY;
			}
		}
		
		// create our snake
		createSnake();
		
		// place the first food on the board
		placeFood();
		
		initListeners();											// initialize key listeners to track the movement of the snake
		initGUI();													// initialize the GUI	
	}
	
	public void createSnake(){
		snake = new Snake(this, SNAKE_START_COL, SNAKE_START_ROW);	// place the snake on the map
		this.positions[SNAKE_START_COL][SNAKE_START_ROW] = UnitTypes.SNAKE; // reflect our placement using our enum
		this.snakePositions = new LinkedList<int[]>();				// initialize our linked list of snake positions
		snakePositions.addFirst(snake.getHeadIndices());			// add position to the front of the list
	}
	
	/*
	 * playGame() - Primary game runner.  Cycles through the game while it is running
	 */
	public void playGame(){
		while(game.isRunning()){
			// move the snake one unit
			snake.move();
			// check to make sure position is valid
			if (checkHeadPosition() == false) {
				break;
			}
			// repaint the panel
			repaint();
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
	    game.gameOver();
		
	}
	
	public void initGUI(){
		
		setBackground(new java.awt.Color(250, 250, 250));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		Dimension boardSize = new Dimension(B_WIDTH, B_WIDTH);
		setSize(boardSize);
		setVisible(true);
	}
	
	/* initListeners - setup keyListeners and add them to the JPanel (the board)
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
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics theGraphic) {
		super.paint(theGraphic);
		Graphics2D the2DGraphic = (Graphics2D) theGraphic;
		the2DGraphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		snake.paint(the2DGraphic);
		food.paint(the2DGraphic);
	}
	
	/*
	 * checkHeadPosition() - moves the snake using the snake's move method and updates board management accordingly
	 * @params none
	 * @return none
	 */
	public boolean checkHeadPosition(){
//		for( int i = 0; i < B_ROW_COUNT; i++ ){
//			for(int j = 0; j< B_ROW_COUNT; j++){
//				if(this.positions[i][j] == UnitTypes.FOOD){
//					System.out.println("FOOD!");
//					System.out.print(i + " ");
//					System.out.print(j);
//					
//				}
//			}
//		}
		
		// update board position data
		int[] currentHeadPosition = this.snake.getHeadIndices();					// store the indices of the new head
		int headCol = currentHeadPosition[0];
		int headRow = currentHeadPosition[1];
		
		
		int[] lastCoord;
		// if we are still on the board
		if ( withinBounds(currentHeadPosition) ) { 							// if we are not outside of the matrix...
			UnitTypes newSpotValue = this.positions[headCol][headRow];
			snakePositions.addFirst(currentHeadPosition);					// add indices to our snake positions
			lastCoord = snakePositions.removeLast();						// remove the tail from our snake positions
			
			
			// is the new snake coord on an empty spot?
			if (newSpotValue == UnitTypes.EMPTY){
				// if so, make that spot a snake spot
				this.positions[lastCoord[0]][lastCoord[1]] = UnitTypes.EMPTY;
				this.positions[headCol][headRow] = UnitTypes.SNAKE;
				return true;
			}
			
			if (newSpotValue == UnitTypes.SNAKE){
				System.out.println("NO");
				return false;
			} else if (newSpotValue == UnitTypes.FOOD){
				this.positions[lastCoord[0]][lastCoord[1]] = UnitTypes.SNAKE;
				handleFoodCollision();
				this.positions[headCol][headRow] = UnitTypes.SNAKE;
				snakePositions.addLast(lastCoord);
				return true;
			} else if (newSpotValue == UnitTypes.WALL){
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/*
	 * handleFoodCollision() - called when the snake hits food.  Grows the snake by one, removes the food, and puts new food in its place
	 * @params none
	 * @return none
	 */
	public void handleFoodCollision(){
		// grow the snake's size by 1
		this.snake.eatFood();
		// remove the food from the board
		food = null;
		placeFood();
		game.incrementScore(1);
		System.out.println("hello");
	}
	
	/*
	 * placeFood() - Randomly places a unit of food on the board
	 * @params none
	 * @return none
	 */
	public void placeFood(){
		// create a random number generator to randomly place the food
		Random rand = new Random();
		// select a random row and random column in which to place it
		int randCol = (int)Math.floor(rand.nextDouble() * B_ROW_COUNT);
		int randRow = (int)Math.floor(rand.nextDouble() * B_ROW_COUNT);
		
		while(this.positions[randCol][randRow] == UnitTypes.SNAKE){
			randCol = (int)Math.floor(rand.nextDouble() * B_ROW_COUNT);
			randRow = (int)Math.floor(rand.nextDouble() * B_ROW_COUNT);
		}
		// create the food
		this.food = new Food(this, randRow, randCol);
		// update positions matrix with food location
		this.positions[randCol][randRow] = UnitTypes.FOOD;
	}
	
	/*
	 * withinBounds() - determines if the indices passed in are a valid position for the snake to move
	 * @params - a tuple of indices [row, col] that is cross-referenced with locations in our positions array
	 * @return - a boolean indicating if the position is valid.
	 */
	private boolean withinBounds(int[] indices){
		int row = indices[1]; // store row variable for convenience. this is the row index
		int col = indices[0]; // store col variable for convenience. this is the col index
		// first test if it is outside of our bounds
		if ( row < 0 || col < 0 || row > B_ROW_COUNT - 1 || col > B_ROW_COUNT - 1 ){
			return false;
		} else {
			return true;
		}
	}
	
	public int getUnitWidth(){
		return UNIT_WIDTH;
	}
	
	
	
}
