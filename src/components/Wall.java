package components;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall {
	/* PROPERTIES */
	private int[] coordinates;	// the coordinates of the wall
	private Board board;		// board it is on	
	private int wallSize;		// size of the wall
	private final Color wallColor = new Color(192, 57, 43);	// final color for all walls, set to red
	
	/* CONSTRUCTOR */
	public Wall(Board theBoard, int col, int row) {
		// set coordinates
		this.coordinates = new int[2];
		this.coordinates[0] = row;
		this.coordinates[1] = col;
		board = theBoard;
		wallSize = this.board.getUnitWidth();
	}

	/*
	 * paint - paints the wall object on the JPanel
	 * @params
	 */
	public void paint(Graphics2D wallGraphic) {
		wallGraphic.setColor(wallColor);
		wallGraphic.fillRect(coordinates[0] * wallSize, coordinates[1] * wallSize, wallSize, wallSize);
	}

}
