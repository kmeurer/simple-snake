package components;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall {
	private int[] coordinates;
	private Board board;
	private int wallSize;
	private final Color wallColor = new Color(192, 57, 43);
	
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
