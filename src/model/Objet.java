package model;

public class Objet {
	private String name;
	private double masse;
	private Vecteur pos;
	private Vecteur acc;
	private Vecteur vitesse;
	
	
	public Objet(String name,double masse,Position pos) {
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
