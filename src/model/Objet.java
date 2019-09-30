package model;

/** Classe définissant un objet dans le système, comme une planète.
 * @author Lucas, Kévin
 */

public abstract class Objet {
	protected String name;
	protected double masse;
	protected Vecteur pos;
	protected Vecteur acc;
	protected Vecteur vitesse;
	
	
	public Objet(String name,double masse,Vecteur pos, Vecteur vitesse, Vecteur acc) {
		this.masse=masse;
		this.name=name;
		this.pos=pos;
		this.vitesse=vitesse;
		this.acc=acc;
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
	
}
