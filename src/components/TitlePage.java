package components;

import game.SnakeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TitlePage extends JPanel implements ActionListener {
	
	private JButton level1But;
	private JButton level2But;
	private SnakeGame game;
	
	public TitlePage(SnakeGame theGame) {
		this.game = theGame;
		
		initGUI();
	}
	
	private void initGUI() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Determine which button it was that was clicked
    	JButton button = (JButton) e.getSource();
		
	}

}
