package model;

/** Classe définissant un objet dans le système, comme une planète.
 * @author //compléter
 */

public class Objet {
	private String name;
	private double masse;
	private Vecteur pos;
	private Vecteur acc;
	private Vecteur vitesse;
	
	
	public Objet(String name,double masse,Vecteur pos) {
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


	public Vecteur getPos() {
		return pos;
	}


	public void setPos(Vecteur pos) {
		this.pos = pos;
	}

	public void placerObjet() {
		//TODO : méthode pour mettre un objet sur l'interface
	}
	
}
