package model;

/** Classe héritant de Objet définissant un vaisseau dans le système.
 * @author Lucas, Kevin
 */
public class Vaisseau extends Objet{
	/**
	 * Constructeur qui instancie le vaisseau (héritage de objet) à l'aide des valeurs de chacun des attributs mis en paramètre
	 * @param name
	 * @param type
	 * @param masse
	 * @param pos
	 * @param vitesse
	 * @param acc
	 */
	protected double pprincipal;
	protected double pretro;
	
	public Vaisseau(String name, String type, double masse, Vecteur pos,Vecteur vitesse,Vecteur acc,double attraction, double pprincipal, double pretro) {
		super(name, type, masse, pos, vitesse, acc, attraction);
		this.pprincipal = pprincipal;
		this.pretro = pretro;
	}
	
	public void up(boolean avance) {
		if(avance) this.setVit(new Vecteur(this.getVitesse().getPosX(), this.getVitesse().getPosY()+pretro));
		else this.setVit(new Vecteur(this.getVitesse().getPosX(), this.getVitesse().getPosY()+0));
	}
	
	public void down(boolean avance) {
		if(avance) this.setVit(new Vecteur(this.getVitesse().getPosX(), this.getVitesse().getPosY()-pprincipal));
		else this.setVit(new Vecteur(this.getVitesse().getPosX(), this.getVitesse().getPosY()-0));
	}
	
	public void right(boolean avance) {
		if(avance) this.setVit(new Vecteur(this.getVitesse().getPosX()-pretro, this.getVitesse().getPosY()));
		else this.setVit(new Vecteur(this.getVitesse().getPosX()-0, this.getVitesse().getPosY()));
	}
	
	public void left(boolean avance) {
		if(avance) this.setVit(new Vecteur(this.getVitesse().getPosX()+pretro, this.getVitesse().getPosY()));
		else this.setVit(new Vecteur(this.getVitesse().getPosX()+0, this.getVitesse().getPosY()));
	}
}