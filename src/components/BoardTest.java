package components;

import static org.junit.Assert.*;
import game.SnakeGame;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import components.Snake.Direction;

@RunWith(Parameterized.class)
public class BoardTest {
	// Establish Parameters for board testing. Format {colLocation, rowLocation, outOfBoundsResult}
	@Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][] {     
             { -1, 0, false}, // colLocation, rowLocation, outOfBoundsResult
             { 7, 9, true}, 	// colLocation, rowLocation, outOfBoundsResult
             { 0, 10 , true}	// colLocation, rowLocation, outOfBoundsResult
       });
    }
	/* PROPERTIES */
	SnakeGame game;
	Board board;
	int colLoc; // column location of our test point
	int rowLoc; // row location of our test point
	boolean outOfBoundsResult;
    
	// Test Constructor: assigns parameters to variables
	public BoardTest(int col, int row, boolean oobRes){
		colLoc = col;
		rowLoc = row;
		game = new SnakeGame();
		board = new Board(game);
		outOfBoundsResult = oobRes;
	}
	
	// Set Up before each
	@Before
	public void setUp() throws Exception {
		board = new Board(game); // reset board each time
	}
	
	// Test constructors
	@Test
	public void constructorTests() {
		// doesn't throw errors
		Board newBoard = new Board(game);
	}
	
	// Out of Bounds Testing
	@Test
	public void outOfBoundsTest(){
		// create the coords
		int[] coords = {colLoc, rowLoc};
		// test the coords against the param
		assertEquals(board.withinBounds(coords), outOfBoundsResult);
	}
}

