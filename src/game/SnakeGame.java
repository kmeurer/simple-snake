/*
 * MAIN GAME CLASS: This class manages the game state.  It imports a number of game components from the gamecomponents package
 */

package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SnakeGame extends JFrame {

	public SnakeGame() {
		initGUI();
	}
	
	void initGUI(){
		setBackground( new Color(130,50,40) );
		setLayout( new BorderLayout(3,3) );
	}
}