package components;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {

	public enum Direction {UP, DOWN, LEFT, RIGHT};
	private Board board;
	private Direction direction;
	ArrayList<Rectangle> segments;
	private int segmentSize;
	
	
	public Snake(Board snakeBoard, int startCol, int startRow) {
		this.board = snakeBoard;
		this.direction = Direction.DOWN;
		this.segmentSize = board.getUnitWidth();
		this.segments = new ArrayList<Rectangle>();
		this.segments.add(new Rectangle(segmentSize * startRow, segmentSize * startCol, segmentSize, segmentSize));
	}
	
	public void paint(Graphics2D snakeGraphic) {
		for( int i = 0; i < segments.size(); i++ ){
			snakeGraphic.fillRect((int)segments.get(i).getX(), (int)segments.get(i).getY(), (int)segments.get(i).getWidth(), (int)segments.get(i).getHeight());
		}
	}

	public void move(){
		// Create a new rectangle to add to the front.  It's equivalent to the old rectangle, but moved
		int newX;
		int newY;
		if ( direction == Direction.UP ){
			newX = (int)segments.get(0).getX();
			newY = (int)(segments.get(0).getY() - segmentSize);  
		} else if ( direction == Direction.DOWN ){
			newX = (int)segments.get(0).getX();
			newY = (int)(segments.get(0).getY() + segmentSize);  
		} else if ( direction == Direction.LEFT ){
			newX = (int)segments.get(0).getX() - segmentSize;
			newY = (int)segments.get(0).getY();  
		} else {
			newX = (int)segments.get(0).getX() + segmentSize;
			newY = (int)segments.get(0).getY();  
		}
		
		Rectangle newRect = new Rectangle(newX, newY, segmentSize, segmentSize);
		segments.add(0, newRect);
		segments.remove(segments.size() - 1);
		System.out.println(segments.get(0).getX());
	}
	
	public void changeDirection(Direction newDirection){
		this.direction = newDirection;
	}

}
