package model;

/** Classe stockant les paramètres du système solaire utilisé pour la simulation
 *
 * */

public class Systeme {

	/**@param g la constante de la gravité
	 * @param dt la pas de temps
	 * @param fa le facteur accélération
	 * @param rayon étendue du système
	 * */

	private double g = 0;
	private double dt = 0;
	private double fa = 0;
	private double rayon = 0;
	
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
}