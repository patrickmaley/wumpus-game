package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

public class GraphicsView extends JPanel implements Observer {
	
	private Image blood, goop, slime, slimepit, hunter, wumpus, ground, darkness, gameOver;
	
	private static final int CELLSINROW = 10;
	private GameMap gameMap;
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
	
	@Override
	public void update(Observable o, Object arg) {
		GameMap theMap = (GameMap) o;
		this.gameMap = theMap;
		this.repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawGround(g);
		drawOther(g);
	}
	
	private void drawOther(Graphics g) {
		int xPosition = 50;
		int yPosition = 50;
		GameCell[][] otherObjects = this.gameMap.getGameArray();
		
		//Since paint uses the fourth quadrant, the nested for loop is switch with the i and j values to match the textview
		for (int i = 0; i < CELLSINROW; i++) {
			for (int j = 0; j < CELLSINROW; j++) {
				    if(otherObjects[j][i].getWumpus()){
				    	g.drawImage(this.wumpus, i * xPosition , j * yPosition, this);
					}else if(otherObjects[j][i].getGoop()){
						g.drawImage(this.goop, i * xPosition, j * yPosition, this);
					}else if(otherObjects[j][i].getPit()){
						g.drawImage(this.slimepit, i * xPosition, j * yPosition, this);
					}else if(otherObjects[j][i].getBlood()){
						g.drawImage(this.blood, i * xPosition, j * yPosition, this);
					}else if(otherObjects[j][i].getSlime()){
						g.drawImage(this.slime, i * xPosition, j * yPosition, this);
					}
				 
				    if(!otherObjects[j][i].getVisible()){
				    	g.drawImage(darkness, i * xPosition, j * yPosition, this);
				    }
				    if(j == gameMap.getHunter().getXPosition() && i == gameMap.getHunter().getYPosition()){
				    	g.drawImage(this.hunter, i * xPosition, j * yPosition, this);
				    }
				   
			}
		}
		//If hunter goes on top of a pit or Wumpus or shoots a bad arrow
   	 if(gameMap.getHunter().getIsDead()){
	    		g.drawImage(this.gameOver, 100, 200, this);
	    }
		
	}

	private void drawGround(Graphics g) {
		int xPosition = 50;
		int yPosition = 50;
		
		
		for (int i = 0; i < CELLSINROW; i++) {
			for (int j = 0; j < CELLSINROW; j++) {
				g.drawImage(this.ground, i * xPosition, j * yPosition, this);
			}
		}
		
		
	}

}
