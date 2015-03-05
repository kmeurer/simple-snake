package components;

import java.awt.Color;

public class Wall {
	private int[] coordinates;
	private Board board;
	private int wallSize;
	private final Color wallColor = new Color(0, 0, 0);
	
	/* CONSTRUCTOR */
	public Wall(Board theBoard, int col, int row) {
		// set coordinates
		this.coordinates = new int[2];
		this.coordinates[0] = col;
		this.coordinates[1] = row;
		board = theBoard;
		wallSize = this.board.getUnitWidth();
	}

	

}
