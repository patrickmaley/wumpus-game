package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.GameCell;
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
 *Class Description: GraphicsView is a class that generates the images of the game 
 *on a JPanel. It observes the model to determine if anything changes. Once notified
 *
 */
@SuppressWarnings("serial")
public class GraphicsView extends JPanel implements Observer {
	
	private Image blood, goop, slime, slimepit, hunter, wumpus, ground, darkness, gameOver;
	private static final int CELLSINROW = 10;
	private GameMap gameMap;
	
	//GraphicsView() sets up the layout of the Jpanel and its properties. 
	//The images are also loaded and so is the GameMap object
	public GraphicsView(GameMap theMap) {
		this.setLayout(new GridLayout(10,10));
		this.setVisible(true);
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(new Color(0, 51, 51));
		this.setForeground(new Color(102, 153, 153));
		this.gameMap = theMap;
		
		try {
			blood = ImageIO.read(new File("src/images/Blood.png"));
			goop = ImageIO.read(new File("src/images/Goop.png"));
			slime = ImageIO.read(new File("src/images/Slime.png"));
			slimepit = ImageIO.read(new File("src/images/SlimePit.png"));
			wumpus = ImageIO.read(new File("src/images/Wumpus.png"));
			ground = ImageIO.read(new File("src/images/Ground.png"));
			hunter = ImageIO.read(new File("src/images/TheHunter.png"));
			darkness = ImageIO.read(new File("src/images/Darkness.png"));
			//Generated the Game over image from http://textcraft.net/
			gameOver = ImageIO.read(new File("src/images/Game-Over.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//update() repaints the Jpanel everytime its notified that something changed.
	@Override
	public void update(Observable o, Object arg) {
		GameMap theMap = (GameMap) o;
		this.gameMap = theMap;
		this.repaint();
		
	}
	
	//paintComponent() is the workhorse method that draws out the images
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawOther(g);
	}
	
	private void drawOther(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int xPosition = 50;
		int yPosition = 50;
		GameCell[][] otherObjects = this.gameMap.getGameArray();
		
		//Paints the ground
		for (int i = 0; i < CELLSINROW; i++) {
			for (int j = 0; j < CELLSINROW; j++) {
				g2.drawImage(this.ground, i * xPosition, j * yPosition, this);
			}
		}
		
		//Since paint uses the fourth quadrant, the nested for loop is switch with the i and j values to match the textview
		for (int i = 0; i < CELLSINROW; i++) {
			for (int j = 0; j < CELLSINROW; j++) {
				    if(otherObjects[j][i].getWumpus()){
				    	g2.drawImage(this.wumpus, i * xPosition , j * yPosition, this);
					}else if(otherObjects[j][i].getGoop()){
						g2.drawImage(this.goop, i * xPosition, j * yPosition, this);
					}else if(otherObjects[j][i].getPit()){
						g2.drawImage(this.slimepit, i * xPosition, j * yPosition, this);
					}else if(otherObjects[j][i].getBlood()){
						g2.drawImage(this.blood, i * xPosition, j * yPosition, this);
					}else if(otherObjects[j][i].getSlime()){
						g2.drawImage(this.slime, i * xPosition, j * yPosition, this);
					}
				 
				    if(!otherObjects[j][i].getVisible()){
				    	g2.drawImage(darkness, i * xPosition, j * yPosition, this);
				    }
				    if(j == gameMap.getHunter().getXPosition() && i == gameMap.getHunter().getYPosition()){
				    	g2.drawImage(this.hunter, i * xPosition, j * yPosition, this);
				    }
				   
			}
		}
	 //If hunter goes on top of a pit or Wumpus or shoots a bad arrow
   	 if(gameMap.getHunter().getIsDead()){
	    		g2.drawImage(this.gameOver, 100, 200, this);
	    }
	}
}
