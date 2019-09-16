package model;

public class Planete {
	private String name;
	private int masse;
	private Position pos;
	
	
	public Planete(String name,int masse,Position pos) {
		this.masse=masse;
		this.name=name;
		this.pos=pos;
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getMasse() {
		return masse;
	}


	public void setMasse(int masse) {
		this.masse = masse;
	}


	public Position getPos() {
		return pos;
	}


	public void setPos(Position pos) {
		this.pos = pos;
	}
	
}
