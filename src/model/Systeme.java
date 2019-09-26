package model;

public class Systeme {

	private double g = 0;
	private double dt = 0;
	private double fa = 0;
	private double rayon = 0;
	
	
	public Systeme(double g, double dt, double fa, double rayon) {
		this.g = g;
		this.dt = dt;
		this.fa = fa;
		this.rayon = rayon;
	}

	public double getG() {
		return g;
	}


	public double getDt() {
		return dt;
	}


	public double getFa() {
		return fa;
	}


	public double getRayon() {
		return rayon;
	}	
}