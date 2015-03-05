package components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Food {
	
	private Board board;
	private int[] location;
	private int foodSize;
	private Color foodColor;
	
	public Food(Board theBoard, int startCol, int startRow) {
		board = theBoard;	// store a reference to the board
		location = new int[2];
		location[0] = startRow;
		location[1] = startCol;
		foodSize = this.board.getUnitWidth();
		// Create a random number generator to generate a random color		
		Random rand = new Random();
		foodColor = new Color((int)Math.floor(rand.nextDouble() * 150), (int)Math.floor(rand.nextDouble() * 150), (int)Math.floor(rand.nextDouble() * 150));
	}
	
	/*
	 * paint - paints the food object on the JPanel
	 * @params
	 */
	public void paint(Graphics2D foodGraphic) {
		foodGraphic.setColor(foodColor);
		foodGraphic.fillOval(location[0] * foodSize, location[1] * foodSize, foodSize, foodSize);
	}

}
