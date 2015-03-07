/*
 * GameOver - Game over screen displayed when the user loses the game.
 */

package components;

import game.SnakeGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JPanel implements ActionListener {
	
	private final String TITLE_TEXT = "GAME OVER!";
	
	private JButton level1But;
	private JButton level2But;
	private JButton level3But;
	private JButton level4But;
	private SnakeGame game;
	private String scoreMsg;
	private final String playAgainMsg = "Play again?";
	
	public GameOver(SnakeGame theGame, int finalScore) {
		this.game = theGame;
		level1But = new JButton("Easy");
		level1But.addActionListener(this);
		level2But = new JButton("Intermediate");
		level2But.addActionListener(this);
		level3But = new JButton("Advanced");
		level3But.addActionListener(this);
		level4But = new JButton("Impossible");
		level4But.addActionListener(this);
		
		scoreMsg = "Your Score: " + finalScore;
		
		initGUI();
	}
	
	private void initGUI() {
		// declare a button panel to hold our buttons
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 5));
		// add our buttons to the panel
		buttonPanel.add(level1But);
		buttonPanel.add(level2But);
		buttonPanel.add(level3But);
		buttonPanel.add(level4But);
		// align in the middle of the screen
		buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
        // Create a panel to store title information
		JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(0, 1, 0, 0));
        titlePanel.setLocation(10, 0);
        titlePanel.setSize(20, 20);
        
        // Create a label for our title, the game over message
        JLabel title = new JLabel(TITLE_TEXT);
        title.setFont(new Font(game.FONT_NAME, Font.BOLD, 72));
        title.setHorizontalAlignment(0);
        title.setForeground(Color.white);
        titlePanel.add(title);
        
        // Create a label for our subtitle, which displays the score and a message asking if the user would like to play again?
        JLabel subtitle = new JLabel();
        subtitle.setText("<html><center>"+ scoreMsg + "<br>" + playAgainMsg + "</center></html>");
	    subtitle.setFont(new Font(game.FONT_NAME, Font.ITALIC, 36));
	    subtitle.setForeground(Color.white);
	    titlePanel.add(subtitle);
	    titlePanel.setMaximumSize(titlePanel.getPreferredSize());
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // Set layout for our main panel to use BoxLayout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		// Add title panel and button panel 
		this.add(Box.createVerticalGlue());
		this.add(titlePanel);
		this.add(buttonPanel);
	    this.add(Box.createVerticalGlue());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Determine which button it was that was clicked
    	JButton button = (JButton) e.getSource();
    	// swap into our board view
    	game.changeToBoardView();
    	// depending on the level specified, start the game with different settings
    	if (button == level1But){
			game.startGame(1, false);
    	} else if (button == level2But){
			game.startGame(2, false);
    	} else if (button == level3But){
			game.startGame(2, true);
    	} else if (button == level4But){
			game.startGame(3, true);
    	}
    	
	}

}