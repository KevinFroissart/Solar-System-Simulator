package model;

/** Classe stockant les paramètres de la simulation
 * @author Kevin Froissart
 */

public class Params {

	private static double G;
	private static double dt;
	private static double fa;
	private static double rayon;
	
	
	public static double getG() {
		return G;
	}
	public static void setG(double g) {
		G = g;
	}
	public static double getDt() {
		return dt;
	}
	public static void setDt(double dt) {
		Params.dt = dt;
	}
	public static double getFa() {
		return fa;
	}
	public static void setFa(double fa) {
		Params.fa = fa;
	}
	public static double getRayon() {
		return rayon;
	}
	public static void setRayon(double rayon) {
		Params.rayon = rayon;
	}
}