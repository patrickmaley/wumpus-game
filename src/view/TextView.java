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
						
	public TextView(String gameText){
		setBackground(new Color(102, 153, 153));
		gameMapText = new JTextArea();
		gameMapText.setPreferredSize(new Dimension(500, 300));
		gameMapText.setBackground(new Color(0, 51, 51));
		gameMapText.setForeground(new Color(102, 153, 153));
		gameMapText.setMargin(new Insets(0, 10, 0, 0));
		Font gameFont = new Font("Courier", Font.BOLD, 20);
		gameMapText.setFont(gameFont);
		gameMapText.setText(gameText);
		gameMapText.setEditable(false);
		
		gameRules = new JTextArea();
		gameRules.setPreferredSize(new Dimension(500, 200));
		gameRules.setEditable(false);
		gameRules.setBackground(new Color(0, 51, 51));
		gameRules.setForeground(new Color(102, 153, 153));
		Font legendFont = new Font("Courier", Font.BOLD,14);
		gameRules.setFont(legendFont);
		gameRules.setText(GAMERULES);
		add(gameMapText);
		add(gameRules);
	}
	@Override
	public void update(Observable o, Object arg) {
		String theString = ((GameMap)o).toString();
		gameMapText.setText(theString);
	}

}
