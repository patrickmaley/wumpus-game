package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.GameMap;

public class TextView extends JPanel implements Observer{
	private JTextArea gameMapText;
	
	public TextView(String gameText){
		gameMapText = new JTextArea();
		gameMapText.setPreferredSize(new Dimension(625, 625));
		gameMapText.setBackground(new Color(0, 51, 51));
		gameMapText.setForeground(new Color(102, 153, 153));
		
		Font font = new Font("Courier", Font.BOLD, 25);
		gameMapText.setFont(font);
		gameMapText.setText(gameText);
		add(gameMapText);
	}
	@Override
	public void update(Observable o, Object arg) {
		String theString = ((GameMap)o).toString();
		gameMapText.setText(theString);
	}

}
