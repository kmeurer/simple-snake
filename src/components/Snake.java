package components;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Snake {
	
	/* PROPERTIES */
	public enum Direction {UP, DOWN, LEFT, RIGHT}; 	// enum used to manage locations in matrix
	private Board board;							// board variable to link to main game driver
	private Direction direction;					// current direction
	LinkedList<Rectangle> segments;					// rectangle linked list to store snake segments - linked list allows for easy removal of items
	private int segmentSize;						// size of each segment(serves as both length and width)
	private int[] headIndices;						// the indices of the current head
	private int length;								// keep track of the length of our 
	
	/* METHODS */
	/*
	 * Constructor
	 * @params - takes a board that the snake will move on, a starting row, and a starting column for the snake
	 */
	public Snake(Board snakeBoard, int startCol, int startRow) {
		this.board = snakeBoard;						// link to our board
		this.direction = Direction.DOWN;				// set initial direction to down
		this.segmentSize = board.getUnitWidth();		// set segment size based on the width of a row/column
		this.length = 1;
		
		// Initialize head indices variable, which tracks which indices our snake head is at
		headIndices = new int[2];
		headIndices[0] = startCol;
		headIndices[1] = startRow;
		
		// Initialize our segments linked list and add the snake head to our segments
		this.segments = new LinkedList<Rectangle>();
		this.segments.add(new Rectangle(segmentSize * startRow, segmentSize * startCol, segmentSize, segmentSize));
		System.out.println(startRow);
	}
	
	public void paint(Graphics2D snakeGraphic) {
		for( int i = 0; i < segments.size(); i++ ){
			snakeGraphic.fillRect((int)segments.get(i).getX(), (int)segments.get(i).getY(), (int)segments.get(i).getWidth(), (int)segments.get(i).getHeight());
		}
	}

	/*
	 * move() - Moves the snake a single unit based on the direction it is currently moving
	 * @params none
	 * @return none
	 */
	public int[] move(){
		// create a newx and y variable to store our next location
		int newX;
		int newY;
		
		// Move the snake based on the current direction
		if ( direction == Direction.UP ){
			// set new values. only y changes
			newX = (int)segments.get(0).getX();
			newY = (int)(segments.get(0).getY() - segmentSize); 
			this.headIndices[0] -= 1;  // update indices
		} else if ( direction == Direction.DOWN ){
			// set new values. only y changes
			newX = (int)segments.get(0).getX();
			newY = (int)(segments.get(0).getY() + segmentSize);
			this.headIndices[0] += 1;  // update indices
		} else if ( direction == Direction.LEFT ){
			// set new values.  only x changes
			newX = (int)segments.get(0).getX() - segmentSize;
			newY = (int)segments.get(0).getY();
			this.headIndices[1] -= 1; // update indices
		} else { // if we are moving RIGHT
			// set new values. only x changes
			newX = (int)segments.get(0).getX() + segmentSize;
			newY = (int)segments.get(0).getY();
			this.headIndices[1] += 1; // update indices
		}
		// create a new rectangle that will be our new head
		Rectangle newHead = new Rectangle(newX, newY, segmentSize, segmentSize);
		segments.addFirst(newHead); // add new head
		segments.removeLast();		// remove old tail
		return this.headIndices;
	}
	
	/*
	 * eatFood() - calls the increase length function to manage the snake eating the food
	 */
	public void eatFood() {
		this.increaseLength();
	}
	
	public int getLength() {
		return this.length;
	}
	
	/*
	 * increaseLength() - increments the length of the snake by copying the last item in the linked list.  When the last item is removed, the snake will now have an extra item
	 */
	public void increaseLength(){
		this.length += 1;										// increment our length
		Rectangle newRect = new Rectangle(segments.peekLast()); // create a new rectangle as a copy of our last rectangle
		segments.addLast(newRect);								// add our new rectangle to the end of our list
		System.out.println("added");
	}
	
	/*
	 * getHeadIndices() - returns the current indices of the snake's head in the format [rowIdx, colIdx]
	 * @param none
	 * @return the indices of the the snake's head
	 */
	public int[] getHeadIndices(){
		return this.headIndices.clone();
	}
	
	public void changeDirection(Direction newDirection){
		// We only allow the user to move in any direction when the length is one.  Otherwise, they cannot move in the opposit direction
		if (this.length == 1){
			this.direction = newDirection;
		} else if (direction == Direction.UP && newDirection != Direction.DOWN){
			this.direction = newDirection;
		} else if (direction == Direction.DOWN && newDirection != Direction.UP){
			this.direction = newDirection;
		} else if (direction == Direction.LEFT && newDirection != Direction.RIGHT){
			this.direction = newDirection;
		} else if (direction == Direction.RIGHT && newDirection != Direction.LEFT){
			this.direction = newDirection;
		}
	}

}
