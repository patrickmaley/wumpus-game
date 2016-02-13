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
 *Class Description: GameCell creates the individual cells for the GameMap class.
 *Each cell holds all the necessary information to determine what should be displayed
 *to the user in the game.
 *
 */

public class GameCell {
	//All the properties of a GameCell
	private boolean isPit = false;
	private boolean isVisible = false;
	private boolean isSlime = false;
	private boolean isWumpus = false;
	private boolean isBlood = false;
	private boolean isGoop = false;
	private boolean hasHunter = false;
	
	//All of the methods in this class are getters and setters 
	//for the classes private data fields.
	public void setPit(boolean set){
		this.isPit = set;
	}
	
	public void setVisible(boolean set){
		this.isVisible = set;
	}
	
	public void setSlime(boolean set){
		this.isSlime = set;
	}
	
	public void setWumpus(boolean set){
		this.isWumpus = set;
	}
	
	public void setBlood(boolean set){
		this.isBlood = set;
	}
	
	public void setGoop(boolean set){
		this.isGoop = set;
	}
	
	public boolean getPit(){
		return this.isPit;
	}
	
	public boolean getBlood(){
		return this.isBlood;
	}
	
	public boolean getWumpus(){
		return this.isWumpus;
	}
	
	public boolean getSlime(){
		return this.isSlime;
	}
	
	public boolean getVisible(){
		return this.isVisible;
	}
	
	public boolean getGoop(){
		return this.isGoop;
	}

	public void hasHunter(boolean b) {
		this.isVisible = true;
		this.hasHunter = true;
	}
	
	public void setHunter(boolean b) {
		this.hasHunter = b;
	}
	
	public boolean getHunter() {
		return this.hasHunter;
	}

}
