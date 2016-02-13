package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.GameMap;
/*Author: Patrick Maley
 * 
 *Class: CSC 335
 * 
 *Project: Hunt the Wumpus
 * 
 *Date: February 13, 2016
 *
 *Professor: Dr. Mercer
 *
 *Section Lead: Cindy Trieu
 *
 *Class Description: TextView is a class that uses a Jpanel to create a 2-D array view
 *of the gamemap with brackets and letters.
 *
 */
@SuppressWarnings("serial")
public class TextView extends JPanel implements Observer{
	private JTextArea gameMapText;
	private JTextArea gameRules;
	private static final String GAMERULES = "\t\t  LEGEND\n" 
						+ "\t [H] is the Hunter! \n"
						+ "\t [X] is an unvisited room. Spooky!\n"
						+ "\t [S] is a slime pit. Disgusting!\n"
						+ "\t [B] is blood. Eww!\n"
						+ "\t [P] is a infinity pit. Don't fall in!\n"
						+ "\t [W] is the Wumpus. Kill it with fire!\n"
						+ "\t [G] is goop. A mix of disgusting and eww\n"
						+ "\t [ ] is an empty room. Cool?\n";
	
	//TextView sets up the Jpanel properties and also the JTextArea properties
	public TextView(String gameText){
		setBackground(new Color(102, 153, 153));
		Font gameFont = new Font("Courier", Font.BOLD, 20);
		Font legendFont = new Font("Courier", Font.BOLD,14);
		
		//Game area properties
		gameMapText = new JTextArea();
		gameMapText.setPreferredSize(new Dimension(500, 300));
		gameMapText.setBackground(new Color(0, 51, 51));
		gameMapText.setForeground(new Color(102, 153, 153));
		gameMapText.setMargin(new Insets(0, 10, 0, 0));
		gameMapText.setFont(gameFont);
		gameMapText.setText(gameText);
		gameMapText.setEditable(false);
		
		//Legend properties
		gameRules = new JTextArea();
		gameRules.setPreferredSize(new Dimension(500, 200));
		gameRules.setBackground(new Color(0, 51, 51));
		gameRules.setForeground(new Color(102, 153, 153));
		gameRules.setFont(legendFont);
		gameRules.setText(GAMERULES);
		gameRules.setEditable(false);
		
		//Adding to Jpanel
		add(gameMapText);
		add(gameRules);
	}
	
	//update() sets the gameMapText to the GameMap string everytime it is notified
	@Override
	public void update(Observable o, Object arg) {
		gameMapText.setText(((GameMap)o).toString());
	}

}
