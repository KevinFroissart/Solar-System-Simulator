package model;

public class Position {
	
	private int posX;
	private int posY;
	
	
	public Position(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	@Override
	public String toString() {
		return "Position [posX=" + posX + ", posY=" + posY + "]";
	}
	
	

}
