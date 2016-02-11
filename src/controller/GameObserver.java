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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import model.GameMap;
import view.GraphicsView;
import view.TextView;

public class GameObserver extends JFrame {

	private GameMap theMap = new GameMap();
	private JPanel buttonsPanel = new JPanel();
	private GridBagConstraints jFrameConstraints = new GridBagConstraints();
	private JTextArea gameTextArea = new JTextArea();
	private JTabbedPane twoViews;
	
	private JPanel textView = new TextView(theMap.toString());
	private JPanel graphicsView = new GraphicsView(theMap);
	
	private JButton moveUp = new JButton("^");
	private JButton moveDown = new JButton("v");
	private JButton moveLeft = new JButton("<");
	private JButton moveRight = new JButton(">");
	
	private JButton shootUp = new JButton("w");
	private JButton shootDown = new JButton("s");
	private JButton shootLeft = new JButton("a");
	private JButton shootRight = new JButton("d");
	
	private final static String nearPitMessage = "I can feel the wind...";
	private final static String nearWumpusMessage = "I can smell the blood...";
	public static void main(String[] args) {
		new GameObserver().setVisible(true);
	}
	
	public GameObserver(){
		frameProperties();
	}

	private void frameProperties() {
		setTitle("Hunt the Wumpus");
		setLayout(new GridBagLayout());
		setSize(850, 615);
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
		moveRight.addActionListener(new MoveRightListener());
		moveLeft.addActionListener(new MoveLeftListener());
		moveDown.addActionListener(new MoveDownListener());
		shootUp.addActionListener(new ShootUpListener());
		shootRight.addActionListener(new ShootRightListener());
		shootDown.addActionListener(new ShootDownListener());
		shootLeft.addActionListener(new ShootLeftListener());
	}
	
	private void gameOverShootScreen(boolean gameOver) {
		if(gameOver){
			this.gameTextArea.setText("Congratulations, you killed the wumpus!");
		}else{
			this.gameTextArea.setText("OH NO! You shot yourself!");
		}
		disableButtons();
	}
	
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
	
	private void setObservers() {
		theMap.addObserver((Observer) textView);
		theMap.addObserver((Observer) graphicsView);
	}

	private void setGameArea() {
		twoViews = new JTabbedPane();
		twoViews.setPreferredSize(new Dimension(500, 525));
		twoViews.setBackground(new Color(0, 51, 51));
		twoViews.setForeground(new Color(102, 153, 153));
		
		twoViews.add(textView, "Text View");
		twoViews.add(graphicsView, "Graphics View");
		//jFrameConstraints.ipady = 0;
		//jFrameConstraints.weightx = 1.0;
		//jFrameConstraints.weighty = 1.0;
		jFrameConstraints.anchor = GridBagConstraints.LINE_START;
		//jFrameConstraints.insets = new Insets(0, 0,0,0);
		jFrameConstraints.gridx = 1;
		jFrameConstraints.gridwidth = 0;
		//jFrameConstraints.gridy = 1;
		add(twoViews, jFrameConstraints);
	}
	//http://stackoverflow.com/questions/3213045/centering-text-in-a-jtextarea-or-jtextpane-horizontal-text-alignment
	//Learned how to center text in a jtextarea from this link
	private void setGameTextArea() {
		Dimension buttonPanelSize = new Dimension(300, 50);
		gameTextArea.setPreferredSize(buttonPanelSize);
		gameTextArea.setEditable(false);
		gameTextArea.setFont(new Font("Verdana", Font.BOLD, 12));
		gameTextArea.setBackground(new Color(13,77,77));
		gameTextArea.setForeground(Color.WHITE);
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
