package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.GameCell;
import model.GameMap;

public class GameMapTests {

//	@Test
//	public void gameMapStringtest() {
//		GameMap map = new GameMap();
//		String testMap = map.toString();
//		String trueMap = "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
//				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n";
//		assertEquals(testMap, trueMap);
//	}
	
	@Test
	public void printVisibleString() {
		GameMap map = new GameMap();
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void printString() {
		GameMap map = new GameMap();
		System.out.println(map.toString());
	}
	
	@Test
	public void instantiateHunter() {
		int hunterPresent = 0;
		GameMap map = new GameMap();
		
		GameCell[][] testArray = map.getGameArray();
		for (int i = 0; i < testArray.length; i++) {
			for (int j = 0; j < testArray.length; j++) {
				if(testArray[i][j].getHunter()){
					hunterPresent++;
				}
			}
		}
		assertEquals(1, hunterPresent);
	}
	
	@Test
	public void instantiateWumpus() {
		int hunterPresent = 0;
		GameMap map = new GameMap();
		
		GameCell[][] testArray = map.getGameArray();
		for (int i = 0; i < testArray.length; i++) {
			for (int j = 0; j < testArray.length; j++) {
				if(testArray[i][j].getWumpus()){
					hunterPresent++;
				}
			}
		}
		assertEquals(1, hunterPresent);
	}

}
