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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import model.GameMap;
import model.Move;
import view.GraphicsView;
import view.TextView;
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
 *Class Description: GameObserver acts as the GUI setup and controller for Hunt The Wumpus.
 *It initially sets up the playing field with the button layout and jpanels on a jframe.
 *Then it calls a GameMap object to initiate the game. The buttons each have their own
 *action listener which communicate with both the views and the model of this game.
 *
 */

@SuppressWarnings("serial")
public class GameObserver extends JFrame {
	private Random rng = new Random();
	private GameMap theMap = new GameMap(rng);
	
	private GridBagConstraints jFrameConstraints = new GridBagConstraints();
	private JPanel buttonsPanel = new JPanel();
	private JTextArea gameTextArea = new JTextArea();
	private JTabbedPane twoViews;
	
	private JPanel textView = new TextView(theMap.toString());
	private JPanel graphicsView = new GraphicsView(theMap);
	
	private JButton moveUp = new JButton("^");
	private JButton moveDown = new JButton("v");
	private JButton moveLeft = new JButton("<");
	private JButton moveRight = new JButton(">");
	
	private JButton shootUp = new JButton("^");
	private JButton shootDown = new JButton("v");
	private JButton shootLeft = new JButton("<");
	private JButton shootRight = new JButton(">");
	
	private JButton newGame = new JButton("New Game");
	
	private final static String nearPitMessage = "I can feel the wind...";
	private final static String nearWumpusMessage = "I can smell the blood...";
	
	//Initiates the GUI and the game logic
	public static void main(String[] args) {
		new GameObserver().setVisible(true);
	}
	
	//GameObserver() calls the method to create the JFrame
	public GameObserver(){
		frameProperties();
	}
	
	//frameProperties() sets up the JFrame with its size, location, and color. It 
	//then calls other methods to set up the 3 Jpanels in the program
	private void frameProperties() {
		//JFrame properties
		setTitle("Hunt the Wumpus");
		setLayout(new GridBagLayout());
		setSize(850, 615);
		setLocation(200,200);
		this.setResizable(false);
		getContentPane().setBackground(new Color(102, 153, 153));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Jpanel methods
		setButtonsPanel();
		setGameArea();
		setGameTextArea();
		registerButtonListeners();
		setObservers();
	}
	
	//Adds a Jpanel for the user commands. This adds 3 jpanels and 9 buttons to the game.
	private void setButtonsPanel() {
		//Panel size and color
		Dimension buttonPanelSize = new Dimension(300, 500);
		buttonsPanel.setPreferredSize(buttonPanelSize);
		buttonsPanel.setBackground(new Color(13,77,77));
		
		//NewGame button
//		jFrameConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
//		jFrameConstraints.insets = new Insets(13,10,0,0);
//		add(newGame, jFrameConstraints);
//		
		//Movement and shooting buttons method
		addButtons();
		
		//Button Jpanel properties and location
		jFrameConstraints.weighty = 1.0;
		jFrameConstraints.weightx = 1;
		jFrameConstraints.anchor = GridBagConstraints.LINE_START;
		jFrameConstraints.insets = new Insets(0,10,0,0);
		jFrameConstraints.gridx = 0;	
		jFrameConstraints.gridy = 0;
		add(buttonsPanel, jFrameConstraints);
	}

	private void addButtons() {
		//Button text information
		JTextArea moveText = new JTextArea("Move");
		JTextArea shootText = new JTextArea("Shoot");
		moveText.setBackground(new Color(13,77,77));
		moveText.setFont(new Font("Verdana", Font.BOLD, 15));
		moveText.setForeground(Color.WHITE);
		shootText.setBackground(new Color(13,77,77));
		shootText.setFont(new Font("Verdana", Font.BOLD, 15));
		shootText.setForeground(Color.WHITE);
		
		//Layout for buttons and text
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
	
	//setGameArea() adds the graphics and text views to the game
	private void setGameArea() {
		//JtabbedPane properties
		twoViews = new JTabbedPane();
		twoViews.setPreferredSize(new Dimension(500, 525));
		twoViews.setBackground(new Color(0, 51, 51));
		twoViews.setForeground(new Color(102, 153, 153));
		
		//Adding both views
		twoViews.add(textView, "Text View");
		twoViews.add(graphicsView, "Graphics View");
		
		//Setting up the JTabbedPane location and adding it to the Jframe
		jFrameConstraints.anchor = GridBagConstraints.LINE_START;
		jFrameConstraints.gridx = 1;
		jFrameConstraints.gridwidth = 0;
		add(twoViews, jFrameConstraints);
	}
	
	//setGameTextArea() sets up the text box that holds information on 
	//what is happening in the game
	private void setGameTextArea() {
		//JTextarea properties
		Dimension gameTextAreaPanelSize = new Dimension(300, 50);
		gameTextArea.setPreferredSize(gameTextAreaPanelSize);
		gameTextArea.setEditable(false);
		gameTextArea.setFont(new Font("Verdana", Font.BOLD, 12));
		gameTextArea.setBackground(new Color(13,77,77));
		gameTextArea.setForeground(Color.WHITE);

		//jTextArea location and adding to jFrame
		jFrameConstraints.ipady = 0;
		jFrameConstraints.weightx = 1.0;
		jFrameConstraints.weighty = 1.0;
		jFrameConstraints.anchor = GridBagConstraints.PAGE_END;
		jFrameConstraints.gridx = 1;
		jFrameConstraints.gridy = 1;
		add(gameTextArea,jFrameConstraints);
	}

	//registerButtonListeners() adds all the listener methods to their buttons
	private void registerButtonListeners() {
		moveUp.addActionListener(new MoveUpListener());
		moveRight.addActionListener(new MoveRightListener());
		moveLeft.addActionListener(new MoveLeftListener());
		moveDown.addActionListener(new MoveDownListener());
		shootUp.addActionListener(new ShootUpListener());
		shootRight.addActionListener(new ShootRightListener());
		shootDown.addActionListener(new ShootDownListener());
		shootLeft.addActionListener(new ShootLeftListener());
	}
	
	
	
	/*
	 * All of the listener methods communicate with the GameMap model to 
	 * determine the location of the hunter. They also determine whether the 
	 * hunter accurately shot at the wumpus or whether or not the hunter
	 * went into the same room as a pit or wumpus.
	 */
	private class ShootUpListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean gameOver = theMap.shootArrow(Move.MOVE_UP);
			gameOverShootScreen(gameOver);
		}
	}
	
	private class ShootRightListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean gameOver = theMap.shootArrow(Move.MOVE_RIGHT);
			gameOverShootScreen(gameOver);
		}
	}
	
	private class ShootDownListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean gameOver = theMap.shootArrow(Move.MOVE_DOWN);
			gameOverShootScreen(gameOver);
		}
	}
	
	private class ShootLeftListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean gameOver = theMap.shootArrow(Move.MOVE_LEFT);
			gameOverShootScreen(gameOver);
		}
	}
	
	private class MoveUpListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			theMap.setHunter(Move.MOVE_UP);
			nearObstacle();
			gameOverMoveScreen(theMap.getHunter().getIsDead());
		}
	}
	
	private class MoveRightListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			theMap.setHunter(Move.MOVE_RIGHT);
			nearObstacle();
			gameOverMoveScreen(theMap.getHunter().getIsDead());
		}
	}
	
	private class MoveLeftListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			theMap.setHunter(Move.MOVE_LEFT);
			nearObstacle();
			gameOverMoveScreen(theMap.getHunter().getIsDead());
		}
	}
	
	private class MoveDownListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			theMap.setHunter(Move.MOVE_DOWN);
			nearObstacle();
			gameOverMoveScreen(theMap.getHunter().getIsDead());
		}
	}
	
	//nearObstacle() determines whether or not a pit or the wumpus
	//is located in one of the 9 adjacent cells to the hunter
	private void nearObstacle() {
		if(theMap.getNearPit() && theMap.getNearWumpus())
			this.gameTextArea.setText(nearPitMessage + "\n " + nearWumpusMessage);
		else if(theMap.getNearPit())
			this.gameTextArea.setText(nearPitMessage);
		else if(theMap.getNearWumpus())
			this.gameTextArea.setText(nearWumpusMessage);
		else
			this.gameTextArea.setText("Nothing here");
		
	}
	
	//gameOverShootScreen() gives the message on whether or not the hunter killed the 
	//wumpus. It also disables the shoot and movement buttons
	private void gameOverShootScreen(boolean gameOver) {
		if(gameOver){
			this.gameTextArea.setText("Congratulations, you killed the wumpus!");
		}else{
			this.gameTextArea.setText("OH NO! You shot yourself!");
		}
		disableButtons();
	}
	
	//gameOverShootScreen() gives the message on whether or not the hunter 
	// fell into a pit or was eaten by the Wumpus. It also disables 
	//the shoot and movement buttons
	private void gameOverMoveScreen(boolean isDead) {
		if(isDead){
			if(theMap.getDeath()){
				this.gameTextArea.setText("Oh No, You've been eaten!");
			}else{
				this.gameTextArea.setText("Oh No, You fell in the pit!");
			}
			disableButtons();
		}
		
	}
	
	//After the player loses or wins, this disables all eight buttons.
	private void disableButtons() {
		moveUp.setEnabled(false);
		moveDown.setEnabled(false);
		moveLeft.setEnabled(false);
		moveRight.setEnabled(false);

		shootUp.setEnabled(false);
		shootDown.setEnabled(false);
		shootLeft.setEnabled(false);
		shootRight.setEnabled(false);
	}
	
	//Adds both views as Observers to the model
	private void setObservers() {
		theMap.addObserver((Observer) textView);
		theMap.addObserver((Observer) graphicsView);
	}
}
