package model;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import controller.Move;

public class GameMap extends Observable{
	private GameCell[][] gameArray = new GameCell[10][10];
	private Hunter hunter = new Hunter();
	
	Random rng = new Random();
	/*
	 * Integer Mappings: 
	 * Undiscovered Room = 0;
	 * Discovered Room = 1;
	 * Hunter in Room = 2;
	 * Wumpus in Room = 3;
	 * Pit in Room = 4;
	 * Slime in Room = 5;
	 * Blood in Room = 6;
	 * Goop in Room = 7;
	 */
	public GameMap(){
		createRooms();
		instantiatePit();
		instantiateWumpus();
		instantiateHunter();
		
	}

	private void instantiatePit() {
		int count = rng.nextInt(3)+ 3;
		while(count > 0){
			//System.out.println(count);
			int xCoordinate = rng.nextInt(10);
			int yCoordinate = rng.nextInt(10);
			int rowLength = gameArray[0].length;
			int columnLength = gameArray.length;
			
			if(!gameArray[yCoordinate][xCoordinate].getPit()){
				gameArray[yCoordinate][xCoordinate].setPit(true);
				
				//Slime above pit
				gameArray[(yCoordinate -1 + rowLength) % rowLength][xCoordinate].setSlime(true);
				//Right
				gameArray[yCoordinate][(xCoordinate + 1) % columnLength].setSlime(true);
				//Down
				gameArray[(yCoordinate + 1) % rowLength][xCoordinate].setSlime(true);
				//Left
				gameArray[yCoordinate][(xCoordinate - 1 + columnLength)%columnLength].setSlime(true);
				count--;
			}
		}
	}

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
	
		//Slime above pit
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
		if (gameArray[(yCoordinate - 1 + rowLength) % rowLength][xCoordinate].getSlime())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][xCoordinate].setGoop(true);
		// Up + 1
		if (gameArray[(yCoordinate - 2 + rowLength) % rowLength][xCoordinate].getSlime())
			gameArray[(yCoordinate - 2 + rowLength) % rowLength][xCoordinate].setGoop(true);
		// Right
		if (gameArray[yCoordinate][(xCoordinate + 1) % columnLength].getSlime())
			gameArray[yCoordinate][(xCoordinate + 1) % columnLength].setGoop(true);
		// Right + 1
		if (gameArray[yCoordinate][(xCoordinate + 2) % columnLength].getSlime())
			gameArray[yCoordinate][(xCoordinate + 2) % columnLength].setGoop(true);
		// Down
		if (gameArray[(yCoordinate + 1) % rowLength][xCoordinate].getSlime())
			gameArray[(yCoordinate + 1) % rowLength][xCoordinate].setGoop(true);
		// Down + 1
		if (gameArray[(yCoordinate + 2) % rowLength][xCoordinate].getSlime())
			gameArray[(yCoordinate + 2) % rowLength][xCoordinate].setGoop(true);
		// Left
		if (gameArray[yCoordinate][(xCoordinate - 1 + columnLength) % columnLength].getSlime())
			gameArray[yCoordinate][(xCoordinate - 1 + columnLength) % columnLength].setGoop(true);
		// Left + 1
		if (gameArray[yCoordinate][(xCoordinate - 2 + columnLength) % columnLength].getSlime())
			gameArray[yCoordinate][(xCoordinate - 2 + columnLength) % columnLength].setGoop(true);
		// Upper Right
		if (gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate + 1) % columnLength].getSlime())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate + 1) % columnLength].setGoop(true);
		// Down Right
		if (gameArray[(yCoordinate + 1) % rowLength][(xCoordinate + 1) % columnLength].getSlime())
			gameArray[(yCoordinate + 1) % rowLength][(xCoordinate + 1) % columnLength].setGoop(true);
		// Down Left
		if (gameArray[(yCoordinate + 1) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getSlime())
			gameArray[(yCoordinate + 1) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].setGoop(true);
		// Upper Left
		if (gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].getSlime())
			gameArray[(yCoordinate - 1 + rowLength) % rowLength][(xCoordinate - 1 + columnLength)% columnLength].setGoop(true);
		
		
	}
	
	//This method places the hunter into an empty cell. The idea is to start the Hunter in a location that is not
	//directly on or next to a slime pit or wumpus
	private void instantiateHunter() {
		while(true){
		int xCoordinate = rng.nextInt(10);
		int yCoordinate = rng.nextInt(10);
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

	private void createRooms() {
		for (int i = 0; i < gameArray.length; i++) {
			for (int j = 0; j < gameArray.length; j++) {
				gameArray[i][j] = new GameCell();
			}
		}
	}
	
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
	public GameCell[][] getGameArray(){
		return this.gameArray;
	}
	
	public Hunter getHunter(){
		return this.hunter;
	}
	
	public void setHunter(Move direction){
		int row = this.getHunter().getXPosition();
		int col = this.getHunter().getYPosition();
		if(direction == Move.MOVE_UP){
			gameArray[(row - 1 + 10) % 10][col].setVisible(true);
			this.getHunter().setXYPosition((row - 1 + 10) % 10, col);
		}
		
		 // Do NOT forget to tell this object the state has changed 
	    // Otherwise, notifyObservers does nothing.
	    this.setChanged();
	    // Otherwise, the next notifyObservers message will not 
	    // send update messages to the registered Observers
	    notifyObservers();
	}
}
