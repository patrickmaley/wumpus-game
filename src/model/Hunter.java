package model;
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
 *Class Description: Hunter is a class that holds the position and state of the hunter
 *
 */

public class Hunter{
	private int yPosition;
	private int xPosition;
	private boolean isDead = false;
	
	public void setXYPosition(int y, int x){
		this.yPosition = y;
		this.xPosition = x;
	}
	
	public int getXPosition(){
		return yPosition;
	}
	
	public int getYPosition(){
		return xPosition;
	}
	
	public void setIsDead(boolean b){
		this.isDead = b;
	}
	
	public boolean getIsDead(){
		return isDead;
	}
}
