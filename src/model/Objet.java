package model;

import java.util.Observable;

/** Classe définissant un objet dans le système, comme une planète.
 * @author Lucas, Kévin
 */


public abstract class Objet extends Observable{
	
	/**
	 * @param name nom de l'Objet
	 * @param masse la masse de l'objet
	 * @param pos Vecteur position initiale de l'Objet
	 * @param acc Vecteur accélération de l'Objet
	 * @param vitesse Vecteur vitesse de l'Objet
	 * @param type nom du type de l'Objet
	 */
	protected String name;
	protected double masse;
	protected Vecteur pos;
	protected double acc;
	protected Vecteur vitesse;
	protected String type;
	protected double attraction;
	
	/**
	 * Constructeur qui instancie un objet à l'aide des valeurs de chacun des attributs mis en paramètre
	 * @param name
	 * @param type
	 * @param masse
	 * @param pos
	 * @param vitesse
	 * @param acc
	 * @param attraction
	 */
	public Objet(String name, String type, double masse,Vecteur pos, Vecteur vitesse, double acc, double attraction) {
		this.name = name;
		this.type = type;
		this.masse = masse;
		this.name=name;
		this.pos=pos;
		this.vitesse=vitesse;
		this.acc=acc;
		this.attraction=attraction;
		
	}

	/**
	 * Retourne nom de l'Objet
	 * @return nom de l'Objet
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Retourne masse de l'Objet
	 * @return masse de l'Objet
	 */
	public double getMasse() {
		return masse;
	}
	
	/**
	 * Retourne Vecteur position de l'Objet
	 * @return Vecteur position de l'Objet
	 */
	public Vecteur getPos() {
		return pos;
	}
	
	/**
	 * Retourne Vecteur accélération de l'Objet
	 * @return Vecteur accélération de l'Objet
	 */
	public double getacc() {
		return acc;
	}
	
	/**
	 * Retourne Vecteur vitesse de l'Objet
	 * @return Vecteur vitesse de l'Objet
	 */
	public Vecteur getVitesse() {
		return vitesse;
	}

	/**
	 * Modifie valeur du Vecteur position de l'Objet
	 * @param pos
	 */
	public void setPos(Vecteur pos) {
		this.pos = pos;
		setChanged();
		notifyObservers(pos);
	}
	
	/**
	 * Modifie valeur du Vecteur vitesse de l'Objet
	 * @param vit
	 */
	public void setVit(Vecteur vit) {
		this.vitesse = vit;
		setChanged();
		notifyObservers(vit);
	}
	
	/**
	 * Modifie valeur de l'accélération de l'Objet
	 * @param acc
	 */
	public void setAcc(double acc) {
		this.acc = acc;
		setChanged();
		notifyObservers(acc);
	}

	/**
	 * Retourne le type de l'Objet
	 * @return String du type d'Objet
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Retourne la force d'attraction que subit l'objet
	 * @return valeur double de la force d'attraction
	 */
	public double getAttraction() {
		return attraction;
	}

	/**
	 * Modifie la valeur de la force d'attraction que subit l'objet
	 * @param attraction
	 */
	public void setAttraction(double attraction) {
		this.attraction = attraction;
		setChanged();
		notifyObservers(pos);
	}	
}