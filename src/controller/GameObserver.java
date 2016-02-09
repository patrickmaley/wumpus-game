package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import model.GameMap;
import view.GraphicsView;
import view.TextView;

public class GameObserver extends JFrame {
	
	private JPanel graphicsView = new GraphicsView();
	private GameMap theMap = new GameMap();
	private JPanel buttonsPanel = new JPanel();
	private GridBagConstraints jFrameConstraints = new GridBagConstraints();
	private JTextArea gameTextArea = new JTextArea();
	private JTabbedPane twoViews;
	
	private JPanel textView = new TextView(theMap.toString());
	
	private JButton moveUp = new JButton("^");
	private JButton moveDown = new JButton("v");
	private JButton moveLeft = new JButton("<");
	private JButton moveRight = new JButton(">");
	
	private JButton shootUp = new JButton("w");
	private JButton shootDown = new JButton("s");
	private JButton shootLeft = new JButton("a");
	private JButton shootRight = new JButton("d");
	
	public static void main(String[] args) {
		new GameObserver().setVisible(true);
	}
	
	public GameObserver(){
		frameProperties();
	}

	private void frameProperties() {
		setTitle("Hunt the Wumpus");
		setLayout(new GridBagLayout());
		setSize(1000, 768);
		setLocation(200,200);
		getContentPane().setBackground(new Color(102, 153, 153));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setButtonsPanel();
		setGameArea();
		setGameTextArea();
		registerButtonListeners();
		setObservers();
	}

	private void registerButtonListeners() {
		moveUp.addActionListener(new MoveUpListener());
		
	}
	
	private class MoveUpListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			theMap.setHunter(Move.MOVE_UP);
		}
	}
	private void setObservers() {
		theMap.addObserver((Observer) textView);
		
	}

	private void setGameArea() {
		twoViews = new JTabbedPane();
		
		
		twoViews.add(textView, "TextView");
		//jFrameConstraints.ipady = 0;
		//jFrameConstraints.weightx = 1.0;
		//jFrameConstraints.weighty = 1.0;
		jFrameConstraints.anchor = GridBagConstraints.LINE_START;
		//jFrameConstraints.insets = new Insets(, 0,0,0);
		jFrameConstraints.gridx = 1;
		//jFrameConstraints.gridwidth = 0;
		//jFrameConstraints.gridy = 1;
		add(twoViews, jFrameConstraints);
	}

	private void setGameTextArea() {
		Dimension buttonPanelSize = new Dimension(300, 50);
		gameTextArea.setPreferredSize(buttonPanelSize);
		gameTextArea.setEditable(false);
		gameTextArea.setBackground(new Color(13,77,77));
		//FrameConstraints.fill = GridBagConstraints.BOTH;
		jFrameConstraints.ipady = 0;
		jFrameConstraints.weightx = 1.0;
		jFrameConstraints.weighty = 1.0;
		jFrameConstraints.anchor = GridBagConstraints.PAGE_END;
		//jFrameConstraints.insets = new Insets(, 0,0,0);
		jFrameConstraints.gridx = 1;
		//jFrameConstraints.gridwidth = 0;
		jFrameConstraints.gridy = 1;
		add(gameTextArea,jFrameConstraints);
	}

	private void setButtonsPanel() {
		Dimension buttonPanelSize = new Dimension(300, 500);
		buttonsPanel.setPreferredSize(buttonPanelSize);
		buttonsPanel.setBackground(new Color(13,77,77));
		//jFrameConstraints.fill = GridBagConstraints.LINE_START;
		//jFrameConstraints.ipady = 0;
		jFrameConstraints.weighty = 1.0;
		jFrameConstraints.weightx = 1;
		jFrameConstraints.anchor = GridBagConstraints.LINE_START;
		jFrameConstraints.insets = new Insets(0,10,0,0);
		jFrameConstraints.gridx = 0;	
		jFrameConstraints.gridy = 0;
		addButtons();
		add(buttonsPanel, jFrameConstraints);
	}

	private void addButtons() {
		JTextArea moveText = new JTextArea("Move");
		JTextArea shootText = new JTextArea("Shoot");
		moveText.setBackground(new Color(13,77,77));
		moveText.setFont(new Font("Verdana", Font.BOLD, 15));
		moveText.setForeground(Color.WHITE);
		shootText.setBackground(new Color(13,77,77));
		shootText.setFont(new Font("Verdana", Font.BOLD, 15));
		shootText.setForeground(Color.WHITE);
		buttonsPanel.setLayout(new GridBagLayout());
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		
		//Movement Buttons
		buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
		buttonConstraints.gridx = 1;	
		buttonConstraints.gridy = 0;
		buttonsPanel.add(moveText, buttonConstraints);
		buttonConstraints.gridy = 1;
		buttonsPanel.add(moveUp, buttonConstraints);
		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = 2;
		buttonsPanel.add(moveLeft, buttonConstraints);
		buttonConstraints.gridx = 1;
		buttonsPanel.add(moveDown, buttonConstraints);
		buttonConstraints.gridx = 2;
		buttonsPanel.add(moveRight, buttonConstraints);
		
		//Shooting Buttons
		buttonConstraints.insets = new Insets(100,0,0,0);
		buttonConstraints.gridx = 1;	
		buttonConstraints.gridy = 5;
		buttonsPanel.add(shootText, buttonConstraints);
		buttonConstraints.gridy = 6;
		buttonConstraints.insets = new Insets(0,0,0,0);
		buttonsPanel.add(shootUp, buttonConstraints);
		buttonConstraints.gridx = 0;
		buttonConstraints.gridy = 7;
		buttonsPanel.add(shootLeft, buttonConstraints);
		buttonConstraints.gridx = 1;
		buttonsPanel.add(shootDown, buttonConstraints);
		buttonConstraints.gridx = 2;
		buttonsPanel.add(shootRight, buttonConstraints);
	}
	
}
