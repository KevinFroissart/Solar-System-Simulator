package model;

/** Classe définissant un objet dans le système, comme une planète.
 * @author //compléter Lucas
 */

public abstract class Objet {
	protected String name;
	protected double masse;
	protected Vecteur pos;
	protected Vecteur acc;
	protected double vitesse;
	
	
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
	
	public Vecteur getacc() {
		return acc;
	}

	public void setPos(Vecteur pos) {
		this.pos = pos;
	}

	public void placerObjet() {
		//TODO : méthode pour mettre un objet sur l'interface
	}
	
}
