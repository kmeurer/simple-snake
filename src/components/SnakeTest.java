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
public class SnakeTest {
	// Establish Parameters for snake testing. Format {startCol, startRow}
	@Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][] {     
             { 5, 10 }, // startCol, startRow
             { 7, 9 }, 	// startCol, startRow
             { 11, 18 }	// startCol, startRow
       });
    }
	/* PROPERTIES */
    int snakeCol;
    int snakeRow;
	Snake snake;
	SnakeGame game;
	Board board;
    
	// Test Constructor: assigns parameters to variables
	public SnakeTest(int col, int row){
		snakeCol = col;
		snakeRow = row;
		game = new SnakeGame();
		board = new Board(game);
		snake = new Snake(board, col, row);
	}
	
	// Set Up before each
	@Before
	public void setUp() throws Exception {
		snake = new Snake(board, snakeCol, snakeRow); // reset snake each time
	}
	
	// Test constructors
	@Test
	public void constructorTests() {
		// doesn't throw errors
		Snake otherSnake = new Snake(board, 1, 2);
		// It should set the head of the snake to the passed in points
		int[] comparedPoints = {snakeCol, snakeRow};
		assertArrayEquals(comparedPoints, snake.getHeadIndices());
		// should start with length of 1
		assertEquals(snake.getLength(), 1);
	}
	
	// Getters Test
	@Test
	public void gettersTests(){
		// it should get the indices of the head when requested
		int[] compare = {snakeCol, snakeRow};
		assertArrayEquals(compare, snake.getHeadIndices());
		// it should get the length when requested
		assertEquals(1, snake.getLength());
	}
	
	// Eat Food Test
	@Test
	public void eatFoodTest(){
		// it should increment the length after eating food
		assertEquals(1, snake.getLength());
		snake.eatFood();
		assertEquals(2, snake.getLength());
	}
	
	// Change Direction Test(also tests getDirection())
	@Test
	public void changeDirectionTest(){
		// it should start by moving down
		assertEquals(snake.getDirection(), Direction.DOWN);
		snake.changeDirection(Direction.LEFT);
		// should successfully have changed directions
		assertEquals(snake.getDirection(), Direction.LEFT);
		// should change to the opposite direction while the length is one
		snake.changeDirection(Direction.RIGHT);
		assertEquals(snake.getDirection(), Direction.RIGHT);
		// should not change direction to the opposite once it has a length greater than 1
		snake.eatFood();
		snake.changeDirection(Direction.LEFT);
		assertEquals(snake.getDirection(), Direction.RIGHT); // should not have changed
	}
}
