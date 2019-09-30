package controller;

import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vecteur;

/** Cette classe contient les méthodes permettant le contrôle de la fusée dans l'interface.
 * @author Maxence
 */
public class VaisseauControl {
    //TODO : construire le controleur de la saisie de contrôle du vaisseau
	
	SystemLoader sl;
	Systeme sys;
	
	VaisseauControl(SystemLoader s, Systeme sys) {
		sl = s;
		this.sys = sys;
	}
	
	public void up(Objet obj, double value) {
		obj.setPos(new Vecteur(0, -value));
	}
	
	public void down(Objet obj, double value) {
		obj.setPos(new Vecteur(0, value));
	}
	
	public void left(Objet obj, double value) {
		obj.setPos(new Vecteur(-value, 0));
	}
	
	public void right(Objet obj, double value) {
		obj.setPos(new Vecteur(value, 0));
	}

	public SystemLoader getModel() {
		return sl;
	}

	public Systeme getSysteme() {
		return sys;
	}
	
	
	
	
	
	
}
