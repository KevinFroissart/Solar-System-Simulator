package controller;

import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vecteur;

/** Cette classe contient les méthodes permettant le contrôle de la fusée dans l'interface.
 * @author Maxence
 */
public class AffichageControl {
	//TODO : construire le controleur de la saisie de contrôle du vaisseau

	SystemLoader sl;
	Systeme sys;

	public AffichageControl(SystemLoader s, Systeme sys) {
		sl = s;
		this.sys = sys;
	}

	public void left(Objet obj, double value) {
		obj.setVit(new Vecteur(-value, 0));
	}

	public void right(Objet obj, double value) {
		obj.setVit(new Vecteur(+value, 0));
	}

	public void down(Objet obj, double value) {
		obj.setVit(new Vecteur(0, +value));
	}

	public void up(Objet obj, double value) {
		obj.setVit(new Vecteur(0, -value));
	}

	public void Force(Objet objA, Objet objB) {
		double distance = Math.sqrt(Math.pow((objA.getPos().getPosX() - objB.getPos().getPosX()),2) + Math.pow(objA.getPos().getPosY() - objB.getPos().getPosY(), 2));
		double f = (sys.getG()*objA.getMasse()*objB.getMasse()) / Math.pow(distance, 2);
		double a = f / objA.getMasse() * sys.getFa();
		double dirX = (objB.getPos().getPosX() - objA.getPos().getPosX()) / distance;
		double dirY = (objB.getPos().getPosY() - objA.getPos().getPosY()) / distance;
		
		objA.setVit(new Vecteur(objA.getVitesse().getPosX() + dirX * a, objA.getVitesse().getPosY() + dirY * a));
	}
	
	public void pos(Objet obj) {
		obj.setPos(new Vecteur(obj.getPos().getPosX() + obj.getVitesse().getPosX(), obj.getPos().getPosY() + obj.getVitesse().getPosY()));
	}
	
	public void bordure(Objet obj) {
		if(obj.getPos().getPosX() > sys.getRayon()) obj.setPos(new Vecteur(-sys.getRayon(), obj.getPos().getPosY()));
		if(obj.getPos().getPosX() < -sys.getRayon()) obj.setPos(new Vecteur(sys.getRayon(), obj.getPos().getPosY()));
		if(obj.getPos().getPosY() > sys.getRayon()) obj.setPos(new Vecteur(obj.getPos().getPosX(), -sys.getRayon()));
		if(obj.getPos().getPosY() < -sys.getRayon()) obj.setPos(new Vecteur(obj.getPos().getPosX(), sys.getRayon()));
	}

	public SystemLoader getModel() {
		return sl;
	}

	public Systeme getSysteme() {
		return sys;
	}
}