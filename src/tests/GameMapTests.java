package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;//Guava
import java.util.Random;
import model.GameCell;
import model.GameMap;
import model.Move;

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
 *Class Description: GameMapTests tests spawns using dependency injection. From these spawns,
 *the movement, shooting, deaths, victorys, and near pit/ wumpus methods are tested. 
 *
 */
public class GameMapTests {

	private static final List<Integer> HUNTER_MAP_MIDDLE = ImmutableList.of(
			
			0,   //Creates 3 pits
			0, 1,//Location of pit 1
		    0, 2,//Location of pit 2
		    0, 3,//Location of pit 3
		    0, 4,//Location of Wumpus
			3, 3 //Location of Hunter
	);
	
	private static final List<Integer> HUNTER_MAP_EDGE = ImmutableList.of(
			
			0,   //Creates 3 pits
			0, 1,//Location of pit 1
		    0, 2,//Location of pit 2
		    0, 3,//Location of pit 3
		    0, 4,//Location of Wumpus
			0, 1 //Location of Hunter
	);
	
	private static final List<Integer> TESTSPAWNS1 = ImmutableList.of(
			2,   //Creates 5 pits
			1, 1,//Location of pit 1
		    0, 1,//Location of pit 2
		    0, 2,//Location of pit 3
		    2, 2,//Location of pit 4
		    0, 5,//Location of pit 5
		    0, 0,//Location of Wumpus
			3, 3 //Location of Hunter
	);
	
	private static final List<Integer> TESTSPAWNS2 = ImmutableList.of(
			2,   //Creates 5 pits
			1, 1,//Location of pit 1
		    0, 1,//Location of pit 2
		    0, 2,//Location of pit 3
		    2, 2,//Location of pit 4
		    0, 5,//Location of pit 5
		    1, 0,//Location of Wumpus
			3, 3 //Location of Hunter
	);
	
	private static final List<Integer> TESTSPAWNS3 = ImmutableList.of(
			2,   //Creates 5 pits
			1, 1,//Location of pit 1
		    0, 1,//Location of pit 2
		    0, 2,//Location of pit 3
		    2, 2,//Location of pit 4
		    0, 5,//Location of pit 5
		    0, 5,//Location of Wumpus //bad spawn
		    1, 2,
			3, 3 //Location of Hunter
	);
	
	private static final List<Integer> TESTSPAWNS4 = ImmutableList.of(
			2,   //Creates 5 pits
			1, 1,//Location of pit 1
		    0, 1,//Location of pit 2
		    0, 2,//Location of pit 3
		    2, 2,//Location of pit 4
		    2, 2,//Location of pit 5 // bad spawn
		    0, 5,
		    0, 5,//Location of Wumpus //bad spawn
		    1, 2,
			3, 3 //Location of Hunter
	);
	
	@Test
	public void testSpawns() {
		GameMap map = new GameMap(new TestRandom(TESTSPAWNS1));
		System.out.println(map.toVisibleString());
	}
	@Test
	public void testSpawns2() {
		GameMap map = new GameMap(new TestRandom(TESTSPAWNS2));
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testSpawns3() {
		GameMap map = new GameMap(new TestRandom(TESTSPAWNS3));
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testSpawns4() {
		GameMap map = new GameMap(new TestRandom(TESTSPAWNS4));
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void gameMapStringtest() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		String testMap = map.toString();
		String trueMap = "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [H] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n";
		assertEquals(testMap, trueMap);
	}
	
	@Test
	public void gameMapVisibleStringtest() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		String testMap = map.toVisibleString();
		String trueMap = "[S] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[P] [S] [X] [X] [X] [X] [X] [X] [X] [S] \n"
				+ "[P] [S] [X] [X] [X] [X] [X] [X] [X] [S] \n"
				+ "[P] [G] [X] [H] [X] [X] [X] [X] [X] [G] \n"
				+ "[W] [B] [B] [X] [X] [X] [X] [X] [B] [B] \n"
				+ "[B] [B] [X] [X] [X] [X] [X] [X] [X] [B] \n"
				+ "[B] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n"
				+ "[X] [X] [X] [X] [X] [X] [X] [X] [X] [X] \n";
		assertEquals(testMap, trueMap);
	}
	
	@Test
	public void printVisibleString() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void printString() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toString());
	}
	
	@Test
	public void instantiateHunter() {
		int hunterPresent = 0;
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		
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
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		
		GameCell[][] testArray = map.getGameArray();
		for (int i = 0; i < testArray.length; i++) {
			for (int j = 0; j < testArray.length; j++){
				if(testArray[i][j].getWumpus()){
					hunterPresent++;
				}
			}
		}
		assertEquals(1, hunterPresent);
	}
	
	@Test
	public void testHunterMovementLeft() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		map.setHunter(Move.MOVE_LEFT);
		System.out.println(map.toVisibleString());
		assertEquals(3, map.getHunter().getXPosition());
		assertEquals(2, map.getHunter().getYPosition());
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testHunterMovementRight() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		map.setHunter(Move.MOVE_RIGHT);
		System.out.println(map.toVisibleString());
		assertEquals(3, map.getHunter().getXPosition());
		assertEquals(4, map.getHunter().getYPosition());
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testHunterMovementUp() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_UP);
		assertEquals(2, map.getHunter().getXPosition());
		assertEquals(3, map.getHunter().getYPosition());
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testHunterMovementDown() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_DOWN);
		assertEquals(4, map.getHunter().getXPosition());
		assertEquals(3, map.getHunter().getYPosition());
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testHunterMovementWrapAroundTopLeft() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_EDGE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_UP);
		System.out.println(map.toVisibleString());
		assertEquals(9, map.getHunter().getXPosition());
		assertEquals(1, map.getHunter().getYPosition());
		System.out.println(map.toVisibleString());
		
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		assertEquals(9, map.getHunter().getXPosition());
		assertEquals(9, map.getHunter().getYPosition());
		System.out.println(map.toVisibleString());
		
		map.setHunter(Move.MOVE_RIGHT);
		assertEquals(9, map.getHunter().getXPosition());
		assertEquals(0, map.getHunter().getYPosition());
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testDeathWumpus() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_DOWN);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		System.out.println(map.toVisibleString());
		assertTrue(map.getDeath());
		assertTrue(map.getHunter().getIsDead());
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testDeathPit() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_UP);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		System.out.println(map.toVisibleString());
		assertTrue(map.getHunter().getIsDead());
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootDeathLeft() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.shootArrow(Move.MOVE_LEFT);
		System.out.println(map.toVisibleString());
		assertTrue(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootDeathRight() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.shootArrow(Move.MOVE_RIGHT);
		System.out.println(map.toVisibleString());
		assertTrue(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootDeathUp() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.shootArrow(Move.MOVE_UP);
		System.out.println(map.toVisibleString());
		assertTrue(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootDeathDown() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.shootArrow(Move.MOVE_DOWN);
		System.out.println(map.toVisibleString());
		assertTrue(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootWinLeft() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_DOWN);
		map.shootArrow(Move.MOVE_LEFT);
		System.out.println(map.toVisibleString());
		assertFalse(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootWinRight() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_DOWN);
		map.shootArrow(Move.MOVE_RIGHT);
		System.out.println(map.toVisibleString());
		assertFalse(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootWinUp() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_DOWN);
		map.setHunter(Move.MOVE_DOWN);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		map.shootArrow(Move.MOVE_UP);
		System.out.println(map.toVisibleString());
		assertFalse(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testShootWinDown() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_DOWN);
		map.setHunter(Move.MOVE_DOWN);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		map.shootArrow(Move.MOVE_DOWN);
		System.out.println(map.toVisibleString());
		assertFalse(map.getHunter().getIsDead());
		boolean isdead = map.getDeath();
		assertFalse(isdead);
		System.out.println(map.toVisibleString());
	}
	
	@Test
	public void testNearWumpusNearPit() {
		GameMap map = new GameMap(new TestRandom(HUNTER_MAP_MIDDLE));
		System.out.println(map.toVisibleString());
		map.setHunter(Move.MOVE_LEFT);
		map.setHunter(Move.MOVE_LEFT);
		System.out.println(map.toVisibleString());
		assertTrue(map.getNearWumpus());
		assertTrue(map.getNearPit());
	
		map.setHunter(Move.MOVE_DOWN);
		assertTrue(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_DOWN);
		assertTrue(map.getNearWumpus());
		assertFalse(map.getNearPit());
		map.setHunter(Move.MOVE_LEFT);
		assertTrue(map.getNearWumpus());
		assertFalse(map.getNearPit());
		map.setHunter(Move.MOVE_LEFT);
		assertTrue(map.getNearWumpus());
		assertFalse(map.getNearPit());
		map.setHunter(Move.MOVE_UP);
		assertTrue(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_UP);
		assertTrue(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_UP);
		assertFalse(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_UP);
		assertFalse(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_UP);
		assertFalse(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_RIGHT);
		assertFalse(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_DOWN);
		assertFalse(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_DOWN);
		assertFalse(map.getNearWumpus());
		assertTrue(map.getNearPit());
		map.setHunter(Move.MOVE_DOWN);
		assertTrue(map.getNearWumpus());
		assertTrue(map.getNearPit());
		System.out.println(map.toVisibleString());
	}
	
	/**
	 * A test class that we can use deterministically return ints from a random
	 * @author Jeremy
	 */
	@SuppressWarnings("serial")
	private class TestRandom extends Random {
		private List<Integer> sequenceOfInts;
		private int currentIndex;
		
		public TestRandom(List<Integer> sequenceOfInts) {
			this.sequenceOfInts = sequenceOfInts;
			this.currentIndex = 0;
		}
		
		@Override
		public int nextInt(int i) {
			int result = this.sequenceOfInts.get(currentIndex);
			this.currentIndex++;
			return result;
		}
	}
}
