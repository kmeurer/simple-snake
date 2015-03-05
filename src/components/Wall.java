package components;

public class Wall {
	int[] coordinates;
	
	
	public Wall(Board board, int col, int row) {
		this.coordinates = new int[2];
		this.coordinates[0] = col;
		this.coordinates[1] = row;
	}

}
