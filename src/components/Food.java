package components;

import java.awt.Graphics2D;

public class Food {
	
	private Board board;
	private int[] location;
	private int foodSize;
	
	public Food(Board theBoard, int startRow, int startCol) {
		board = theBoard;	// store a reference to the board
		location = new int[2];
		location[0] = startRow;
		location[1] = startCol;
		foodSize = this.board.getUnitWidth();
	}
	
	/*
	 * paint - paints the food object on the JPanel
	 * @params
	 */
	public void paint(Graphics2D foodGraphic) {
		foodGraphic.fillOval(location[0] * foodSize, location[1] * foodSize, foodSize, foodSize);
	}

}
