package components;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Scoreboard extends JPanel {
	private int score;
	private int time;
	
	public Scoreboard() {
		score = 0;
		time = 0;
	}
	
	/* incrementScore - add to the score
	 * @param inc the number of integers to increment the score by
	 * 
	 */
	public void incrementScore(int inc){
		score += inc;
	}

	public void incrementTimer(int inc){
		time += inc;
	}
}
