package model;

/** Classe définissant un objet dans le système, comme une planète.
 * @author //compléter
 */

public abstract class Objet {
	private String name;
	private double masse;
	private Vecteur pos;
	private Vecteur acc;
	private double vitesse;
	
	
	public Objet(String name,double masse,Vecteur pos) {
		this.masse=masse;
		this.name=name;
		this.pos=pos;
		
	}


	public String getName() {
		return name;
	}

	public double getMasse() {
		return masse;
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
