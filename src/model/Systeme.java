package model;

import java.util.Observable;

/** Classe stockant les paramètres du système solaire utilisé pour la simulation
 *
 * */

public class Systeme extends Observable{

	/**@param g la constante de la gravité
	 * @param dt la pas de temps
	 * @param fa le facteur accélération
	 * @param rayon étendue du système
	 * */

	private double g;
	private double dt;
	private double fa;
	private double rayon;
	private double zoom;
	
	/**
	 * Contructeur qui instancie le Systeme à l'aide des valeurs de chacun des attributs mis en paramètre
	 * @param g
	 * @param dt
	 * @param fa
	 * @param rayon
	 */
	public Systeme(double g, double dt, double fa, double rayon) {
		this.g = g;
		this.dt = dt;
		this.fa = fa;
		this.rayon = rayon;
		zoom = 1.0;
	}

	/**
	 * Retourne la valeur de la constante de gravité
	 * @return g
	 */
	public double getG() {
		return g;
	}

	/**
	 * Retourne la valeur du pas de temps du systeme
	 * @return valeur de dt du Systeme
	 */
	public double getDt() {
		return dt;
	}

	/**
	 * Retourne la valeur du facteur d'accélération du systeme
	 * @return valeur du (fa) facteur d'accélération du systeme 
	 */
	public double getFa() {
		return fa;
	}

	/**
	 * Retourne la valeur du rayon du Systeme
	 * @return valeur du rayon du systeme
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Modifie la valeur du pas du Systeme
	 * @param fa
	 */
	public void setFa(double fa) {
		this.fa = fa;
		setChanged();
		notifyObservers(fa);
	}	
	
	/**
	 * Modifie la valeur du pas du Systeme
	 * @param dt
	 */
	public void setDt(double dt) {
		this.dt = dt;
		setChanged();
		notifyObservers(dt);
	}

	public double getZoom(){
		return zoom;
	}

	public void setZoom(double value) {
		this.zoom = value;
		setChanged();
		notifyObservers(value);
	}

	public void setRayon(double value) {
		this.rayon = value;
	}
}