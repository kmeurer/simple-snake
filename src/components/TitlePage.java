/*
 * TitlePage: Manages a JPanel as an intro screen for the game.
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

public class TitlePage extends JPanel implements ActionListener {
	/* PROPERTIES */
	private final String TITLE_TEXT = "Snake!";
	private final String SUBTITLE_TEXT = "Welcome to Snake.  Use the arrow keys to navigate the snake towards food, but don't hit the walls!  Select a level to begin.";
	
	private JButton level1But;
	private JButton level2But;
	private JButton level3But;
	private JButton level4But;
	private SnakeGame game;
	
	/* CONSTRUCTOR */
	public TitlePage(SnakeGame theGame) {
		this.game = theGame;				// store reference to the game
		// instantiate our buttons
		level1But = new JButton("Easy");
		level1But.addActionListener(this);
		level2But = new JButton("Intermediate");
		level2But.addActionListener(this);
		level3But = new JButton("Advanced");
		level3But.addActionListener(this);
		level4But = new JButton("Impossible");
		level4But.addActionListener(this);
		
		initGUI();
	}
	
	/*
	 * initGUI - Initialize our Title page user interface.  Sets up a title and a button group
	 */
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
		
        // create a panel to hold our title information
		JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(0, 1, 0, 0));
        titlePanel.setLocation(10, 0);
        titlePanel.setSize(20, 20);
        
        JLabel title = new JLabel(TITLE_TEXT);
        title.setFont(new Font(game.FONT_NAME, Font.BOLD, 72));
        title.setHorizontalAlignment(0);
        title.setForeground(Color.white);
        titlePanel.add(title);
        
        JLabel subtitle = new JLabel();
        subtitle.setText("<html><center>"+ SUBTITLE_TEXT +"</center></html>");
	    subtitle.setFont(new Font(game.FONT_NAME, Font.ITALIC, 24));
	    subtitle.setForeground(Color.white);
	    titlePanel.add(subtitle);
	    
	    
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
