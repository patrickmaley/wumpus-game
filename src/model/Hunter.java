package model;


public class Hunter{
	private int xPosition;
	private int yPosition;
	private boolean isDead = false;
	
	public void setXYPosition(int x, int y){
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public int getXPosition(){
		return xPosition;
	}
	
	public int getYPosition(){
		return yPosition;
	}
	
	public void setIsDead(boolean b){
		this.isDead = b;
	}
	
	public boolean getIsDead(){
		return isDead;
	}
}
