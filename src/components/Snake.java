package components;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {

	private enum Direction {UP, DOWN, LEFT, RIGHT};
	private Board board;
	private Direction direction;
	ArrayList<Rectangle> segments;
	
	
	public Snake(Board snakeBoard) {
		this.board = snakeBoard;
		this.direction = Direction.UP;
		this.segments = new ArrayList<Rectangle>();
		this.segments.add(new Rectangle(0, 0, board.getUnitWidth(), board.getUnitWidth()));
	}
	
	public void paint(Graphics2D snakeGraphic) {
		for(int i = 0; i < segments.size(); i++){
			snakeGraphic.fillRect((int)segments.get(i).getX(), (int)segments.get(i).getY(), (int)segments.get(i).getWidth(), (int)segments.get(i).getHeight());
		}
	}

	public void move(){
		// Create a new rectangle to add to the front.  It's equivalent to the old rectangle, but moved
		Rectangle newRect = new Rectangle((int)segments.get(0).getWidth(), (int)segments.get(0).getHeight(), (int)(segments.get(0).getX() + segments.get(0).getWidth()), (int)(segments.get(0).getY() + segments.get(0).getWidth()));
		segments.add(0, newRect);
		segments.remove(segments.size() - 1);
		System.out.println(segments.get(0).getX());
	}
	
	public void changeDirection(Direction newDirection){
		this.direction = newDirection;
	}

}
