package model;

public class Planete {
	private String name;
	private double masse;
	private Position pos;
	
	
	public Planete(String name,double masse,Position pos) {
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


	public double getMasse() {
		return masse;
	}


	public void setMasse(double masse) {
		this.masse = masse;
	}


	public Position getPos() {
		return pos;
	}


	public void setPos(Position pos) {
		this.pos = pos;
	}
	
}
