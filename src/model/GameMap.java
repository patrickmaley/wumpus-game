package model;

import java.util.Observable;
import java.util.Random;
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
 *Class Description: GameMap is the class that is the model for this game.
 *It creates a 2-D array of gameCells and uses RNG to populate a 10 by 10 
 *field with the necessary booleans
 *
 */
public class GameMap extends Observable{
	private GameCell[][] gameArray = new GameCell[10][10];
	private Hunter hunter = new Hunter();
	
	private boolean nearPit = false;
	private boolean nearWumpus = false;
	private boolean killedByWumpus = false;
	
	Random rng;
	
	//GameMap constructor method. It creates the rooms first, then the pits,
	//and then the hunter.
	public GameMap(Random rng){
		this.rng = rng;
		createRooms();
		instantiatePits();
		instantiateWumpus();
		instantiateHunter();
	}
	
	//createRooms() sets up all the rooms in the game
	private void createRooms() {
		for (int i = 0; i < gameArray.length; i++) {
			for (int j = 0; j < gameArray.length; j++) {
				gameArray[i][j] = new GameCell();
			}
		}
	}
	
	//instantiatePits() determines the locations of each pit and slime.
	private void instantiatePits() {
		int count = rng.nextInt(3)+ 3;
		while(count > 0){
			
			int xCoordinate = rng.nextInt(10);
			int yCoordinate = rng.nextInt(10);
			int row = gameArray[0].length;
			int column = gameArray.length;
			
			//This sets up slime above, right, below, and left of every pit
			if(!gameArray[yCoordinate][xCoordinate].getPit()){
				gameArray[yCoordinate][xCoordinate].setPit(true);
				
				//Slime above pit
				gameArray[(yCoordinate -1 + row) % row][xCoordinate].setSlime(true);
				//Right
				gameArray[yCoordinate][(xCoordinate + 1) % column].setSlime(true);
				//Down
				gameArray[(yCoordinate + 1) % row][xCoordinate].setSlime(true);
				//Left
				gameArray[yCoordinate][(xCoordinate - 1 + column)%column].setSlime(true);
				count--;
			}
		}
	}
	
	//instantiateWumpus sets up the Wumpus and its blood
	private void instantiateWumpus() {
		while(true){
			int xCoordinate = rng.nextInt(10);
			int yCoordinate = rng.nextInt(10);
			
			if(!gameArray[yCoordinate][xCoordinate].getPit()){
				gameArray[yCoordinate][xCoordinate].setWumpus(true);
				createBloodAndGoop(yCoordinate, xCoordinate);
				break;
			}
				
		}
   }
	
	//createBloodAndGoop() determines if the current cell can have blood 
	//and if the cell has slime to turn it into goop. It also checks to make
	//sure the cell doesn't already have a pit on it.
	private void createBloodAndGoop(int yCoordinate, int xCoordinate) {
		int rowLength = gameArray[0].length;
		int columnLength = gameArray.length;
		
		//Create Blood on empty cells
		// Up
		if (!gameArray[(yCoordinate - 1 + rowLength) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][xCoordinate].setBlood(true);
		// Up + 1
		if (!gameArray[(yCoordinate - 2 + rowLength) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate - 2 + rowLength) % rowLength][xCoordinate].setBlood(true);
		// Right
		if (!gameArray[yCoordinate][(xCoordinate + 1) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate + 1) % columnLength].setBlood(true);
		// Right + 1
		if (!gameArray[yCoordinate][(xCoordinate + 2) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate + 2) % columnLength].setBlood(true);
		// Down
		if (!gameArray[(yCoordinate + 1) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate + 1) % rowLength][xCoordinate].setBlood(true);
		// Down + 1
		if (!gameArray[(yCoordinate + 2) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate + 2) % rowLength][xCoordinate].setBlood(true);
		// Left
		if (!gameArray[yCoordinate][(xCoordinate - 1 + columnLength) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate - 1 + columnLength) % columnLength].setBlood(true);
		// Left + 1
		if (!gameArray[yCoordinate][(xCoordinate - 2 + columnLength) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate - 2 + columnLength) % columnLength].setBlood(true);
		// Upper Right
		if (!gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate + 1) % columnLength].getPit())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate + 1) % columnLength].setBlood(true);
		// Down Right
		if (!gameArray[(yCoordinate + 1) % rowLength][(xCoordinate + 1) % columnLength].getPit())
			gameArray[(yCoordinate + 1) % rowLength][(xCoordinate + 1) % columnLength].setBlood(true);
		// Down Left
		if (!gameArray[(yCoordinate + 1) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getPit())
			gameArray[(yCoordinate + 1) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].setBlood(true);
		// Upper Left
		if (!gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getPit())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].setBlood(true);
		
		//Create Goop on slime cells
		//Up
		if (gameArray[(yCoordinate - 1 + rowLength) % rowLength][xCoordinate].getSlime()
				&& !gameArray[(yCoordinate - 1 + rowLength) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][xCoordinate].setGoop(true);
		// Up + 1
		if (gameArray[(yCoordinate - 2 + rowLength) % rowLength][xCoordinate].getSlime()
				&& !gameArray[(yCoordinate - 2 + rowLength) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate - 2 + rowLength) % rowLength][xCoordinate].setGoop(true);
		// Right
		if (gameArray[yCoordinate][(xCoordinate + 1) % columnLength].getSlime()
				&& !gameArray[yCoordinate][(xCoordinate + 1) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate + 1) % columnLength].setGoop(true);
		// Right + 1
		if (gameArray[yCoordinate][(xCoordinate + 2) % columnLength].getSlime()
				&& !gameArray[yCoordinate][(xCoordinate + 2) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate + 2) % columnLength].setGoop(true);
		// Down
		if (gameArray[(yCoordinate + 1) % rowLength][xCoordinate].getSlime()
				&& !gameArray[(yCoordinate + 1) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate + 1) % rowLength][xCoordinate].setGoop(true);
		// Down + 1
		if (gameArray[(yCoordinate + 2) % rowLength][xCoordinate].getSlime()
				&& !gameArray[(yCoordinate + 2) % rowLength][xCoordinate].getPit())
			gameArray[(yCoordinate + 2) % rowLength][xCoordinate].setGoop(true);
		// Left
		if (gameArray[yCoordinate][(xCoordinate - 1 + columnLength) % columnLength].getSlime()
				&& !gameArray[yCoordinate][(xCoordinate - 1 + columnLength) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate - 1 + columnLength) % columnLength].setGoop(true);
		// Left + 1
		if (gameArray[yCoordinate][(xCoordinate - 2 + columnLength) % columnLength].getSlime()
				&& ! gameArray[yCoordinate][(xCoordinate - 2 + columnLength) % columnLength].getPit())
			gameArray[yCoordinate][(xCoordinate - 2 + columnLength) % columnLength].setGoop(true);
		// Upper Right
		if (gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate + 1) % columnLength].getSlime()
				&& !gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate + 1) % columnLength].getPit())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate + 1) % columnLength].setGoop(true);
		// Down Right
		if (gameArray[(yCoordinate + 1) % rowLength][(xCoordinate + 1) % columnLength].getSlime()
				&& !gameArray[(yCoordinate + 1) % rowLength][(xCoordinate + 1) % columnLength].getPit())
			gameArray[(yCoordinate + 1) % rowLength][(xCoordinate + 1) % columnLength].setGoop(true);
		// Down Left
		if (gameArray[(yCoordinate + 1) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getSlime()
				&& !gameArray[(yCoordinate + 1) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getPit())
			gameArray[(yCoordinate + 1) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].setGoop(true);
		// Upper Left
		if (gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getSlime()
				&& !gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getPit())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].setGoop(true);
	}
	
	//This method places the hunter into an empty cell. The idea is to start the Hunter in a location that is not
	//directly on a slime pit or wumpus
	private void instantiateHunter() {
		while(true){
		int xCoordinate = rng.nextInt(10);
		int yCoordinate = rng.nextInt(10);
		
		//Finds the location of the hunter so it starts off in a safe location
		if(!gameArray[xCoordinate][yCoordinate].getBlood() 
				&& !gameArray[xCoordinate][yCoordinate].getPit()
				&& !gameArray[xCoordinate][yCoordinate].getSlime() 
				&& !gameArray[xCoordinate][yCoordinate].getWumpus()
				&& !gameArray[xCoordinate][yCoordinate].getGoop()){
			gameArray[xCoordinate][yCoordinate].hasHunter(true);
			hunter.setXYPosition(xCoordinate, yCoordinate);
			break;
		}
		}
		
	}
	
	//toVisibleString() is a method used to display the board for testing purposes.
	public String toVisibleString(){
		String gameString = "";
		for (int i = 0; i < gameArray.length; i++) {
			for (int j = 0; j < gameArray.length; j++) {
				if(i == hunter.getXPosition() && j == hunter.getYPosition()){
					gameString += "[H] ";
				}else if(gameArray[i][j].getWumpus()){
					gameString += "[W] ";
				}else if(gameArray[i][j].getGoop()){
					gameString += "[G] ";
				}else if(gameArray[i][j].getPit()){
					gameString += "[P] ";
				}else if(gameArray[i][j].getBlood()){
					gameString += "[B] ";
				}else if(gameArray[i][j].getSlime()){
					gameString += "[S] ";
				}else if(gameArray[i][j].getVisible()){
					gameString += "[ ] ";
				}else if(!gameArray[i][j].getVisible()){
					gameString += "[X] ";
				}
			}
			gameString += "\n";
		}
		return gameString;
	}
	
	//toString() is used by the TextView view to generate the string that the user can see
	public String toString(){
		String gameString = "";
		for (int i = 0; i < gameArray.length; i++) {
			for (int j = 0; j < gameArray.length; j++) {
				if(gameArray[i][j].getVisible()){
					    if(i == hunter.getXPosition() && j == hunter.getYPosition()){
					    	gameString += "[H] ";
					    }else if(gameArray[i][j].getWumpus()){
							gameString += "[W] ";
						}else if(gameArray[i][j].getGoop()){
							gameString += "[G] ";
						}else if(gameArray[i][j].getPit()){
							gameString += "[P] ";
						}else if(gameArray[i][j].getBlood()){
							gameString += "[B] ";
						}else if(gameArray[i][j].getSlime()){
							gameString += "[S] ";
						}else{
							gameString += "[ ] ";
						}
			    }else{
					gameString += "[X] ";
				}
			}
			gameString += "\n";
		}
		return gameString;
	}
	
	//setHunter(Move direction) is the method used by the controller to notify its observables
	//that the position of the hunter has changed.
	public void setHunter(Move direction){
		int row = this.getHunter().getXPosition();
		int col = this.getHunter().getYPosition();
		//Sets the new location of the hunter
		if(direction == Move.MOVE_UP){
			gameArray[(row - 1 + 10) % 10][col].setVisible(true);
			this.getHunter().setXYPosition((row - 1 + 10) % 10, col);
		}else if(direction == Move.MOVE_RIGHT){
			gameArray[row][(col + 1) % 10].setVisible(true);
			this.getHunter().setXYPosition(row, (col + 1) % 10);
		}else if(direction == Move.MOVE_DOWN){
			gameArray[(row + 1) % 10][col].setVisible(true);
			this.getHunter().setXYPosition((row + 1) % 10, col);
		}else if(direction == Move.MOVE_LEFT){
			gameArray[row][(col -1 + 10) % 10].setVisible(true);
			this.getHunter().setXYPosition(row, (col -1 + 10) % 10);
		}
		
		//Determines if the new location is a pit or wumpus also
		this.isDead(gameArray[this.getHunter().getXPosition()][this.getHunter().getYPosition()]);
		
		//Determines if the surround locations have a pit or wumpus
		isNearPitOrWumpus(this.getHunter().getXPosition(), this.getHunter().getYPosition());
		
		//This updates the views
	    this.setChanged();
	    notifyObservers();
	}
	
	//isNearPitorWumpus checks the surround cells to determine if there is a pit or wumpus
	//nearby.
	private void isNearPitOrWumpus(int i, int j) {
		
			if(this.getNearPit() || this.getNearWumpus()){
				this.setNearPit(false);
				this.setNearWumpus(false);
			}
			//Up
			if (gameArray[(i - 1 + 10) % 10][j].getPit())
				this.setNearPit(true);
			if (gameArray[(i - 1 + 10) % 10][j].getWumpus())
				this.setNearWumpus(true);
			// Right
			if (gameArray[i][(j + 1) % 10].getPit())
				this.setNearPit(true);
			if (gameArray[i][(j + 1) % 10].getWumpus())
				this.setNearWumpus(true);
			// Down
			if (gameArray[(i + 1) % 10][j].getPit())
				this.setNearPit(true);
			if (gameArray[(i + 1) % 10][j].getWumpus())
				this.setNearWumpus(true);
			// Left
			if (gameArray[i][(j - 1 + 10) % 10].getPit())
				this.setNearPit(true);
			if (gameArray[i][(j - 1 + 10) % 10].getWumpus())
				this.setNearWumpus(true);
			// Upper Right
			if (gameArray[(i - 1 + 10) % 10][(j + 1) % 10].getPit())
				this.setNearPit(true);
			if (gameArray[(i - 1 + 10) % 10][(j + 1) % 10].getWumpus())
				this.setNearWumpus(true);
			// Down Right
			if (gameArray[(i + 1) % 10][(j + 1) % 10].getPit())
				this.setNearPit(true);
			if (gameArray[(i + 1) % 10][(j + 1) % 10].getWumpus())
				this.setNearWumpus(true);
			// Down Left
			if (gameArray[(i + 1) % 10][(j - 1 + 10)% 10].getPit())
				this.setNearPit(true);
			if (gameArray[(i + 1) % 10][(j - 1 + 10)% 10].getWumpus())
				this.setNearWumpus(true);
			// Upper Left
			if (gameArray[(i - 1 + 10) % 10][(j - 1 + 10)% 10].getPit())
				this.setNearPit(true);
			if (gameArray[(i - 1 + 10) % 10][(j - 1 + 10)% 10].getWumpus())
				this.setNearWumpus(true);
	}

	//shootArrow() utilizes wrap around to determine if the wumpus is in the line that the arrow is fired. If
	//the wumpus is  it returns true. If not it returns false;
	public boolean shootArrow(Move direction) {
		boolean wumpusKilled = false;
		int row = this.getHunter().getXPosition();
		int col = this.getHunter().getYPosition();
		
		//Utilizes direction enum to determine which location it should checked
		if(direction == Move.MOVE_UP || direction == Move.MOVE_DOWN){
			for (int i = 0; i < gameArray.length; i++) {
				if(gameArray[i][col].getWumpus()){
					wumpusKilled = true;
					break;
				}
			}
		}else if(direction == Move.MOVE_RIGHT || direction == Move.MOVE_LEFT){
			for (int i = 0; i < gameArray.length; i++) {
				if(gameArray[row][i].getWumpus()){
					wumpusKilled = true;
					break;
				}
			}
		}
		
		//Sets the game board to visible
		for (int i = 0; i < gameArray.length; i++) {
			for (int j = 0; j < gameArray.length; j++) {
				gameArray[i][j].setVisible(true);
			}
		}
		
		//If the hunter misses, the hunter dies.
		if(!wumpusKilled) this.getHunter().setIsDead(true);
		
		//Updates the views
		this.setChanged();
	    notifyObservers();
	    
	    //Returns the boolean back to the controller
		if(wumpusKilled){
			return true;
		}else{
			return false;
		}
	}

	//isDead() determines if the hunter steps on a pit or wumpus. If true, he dies.
	public void isDead(GameCell gameCell) {
		if(gameCell.getWumpus() || gameCell.getPit()){
			for (int i = 0; i < gameArray.length; i++) {
				for (int j = 0; j < gameArray.length; j++) {
					gameArray[i][j].setVisible(true);
				}
			}
			if(gameCell.getWumpus()) killedByWumpus  = true;
			this.getHunter().setIsDead(true);
			
			//Update views
			this.setChanged();
		    notifyObservers();
		}
	}
	
	/*
	 * The following method are the getters and setters for this class. 
	 */
	public void setNearPit(boolean b){
		this.nearPit = b;
	}
	
	public void setNearWumpus(boolean b){
		this.nearWumpus = b;
	}
	
	public boolean getNearPit(){
		return this.nearPit;
	}
	
	public boolean getNearWumpus(){
		return this.nearWumpus;
	}
	
	public boolean getDeath(){
		return this.killedByWumpus;
	}
	
	public GameCell[][] getGameArray(){
		return this.gameArray;
	}
	
	public Hunter getHunter(){
		return this.hunter;
	}
}
