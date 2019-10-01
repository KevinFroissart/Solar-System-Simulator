package model;

import java.util.Observable;

/** Classe définissant un objet dans le système, comme une planète.
 * @author Lucas, Kévin
 */

public abstract class Objet extends Observable{
	protected String name;
	protected double masse;
	protected Vecteur pos;
	protected Vecteur acc;
	protected Vecteur vitesse;
	protected String type;
	
	
	public Objet(String name, String type, double masse,Vecteur pos, Vecteur vitesse, Vecteur acc) {
		this.name = name;
		this.type = type;
		this.masse = masse;
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
	
	public Vecteur getVitesse() {
		return vitesse;
	}

	public void setPos(Vecteur pos) {
		this.pos = pos;
		setChanged();
		notifyObservers(pos);
	}
	
	public void setVit(Vecteur vit) {
		this.vitesse = vit;
		setChanged();
		notifyObservers(pos);
	}

	public String getType() {
		return type;
	}	
}