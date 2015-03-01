package components;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
	private Board board;
	private String direction = "UP";
	ArrayList<Rectangle> segments;
	
	
	public Snake(Board snakeBoard) {
		board = snakeBoard;
		segments = new ArrayList<Rectangle>();
		segments.add(new Rectangle(board.getWidth()/20, board.getHeight()/20, 20, 20));
	}
	
	public void paint(Graphics2D snakeGraphic) {
		for(int i = 0; i < segments.size(); i++){
			snakeGraphic.fillRect((int)segments.get(i).getX(), (int)segments.get(i).getX(), (int)segments.get(i).getWidth(), (int)segments.get(i).getHeight());
		}
	}
	

	public void move(){
		Rectangle newRect = new Rectangle((int)segments.get(0).getWidth(), (int)segments.get(0).getHeight(), (int)(segments.get(0).getX() + segments.get(0).getWidth()), (int)(segments.get(0).getY() + segments.get(0).getWidth()));
		segments.add(0, newRect);
		segments.remove(segments.size() - 1);
		System.out.println(segments.get(0).getX());
	}
	
	public void changeDirection(String newDirection){
		direction = newDirection;
	}

}
