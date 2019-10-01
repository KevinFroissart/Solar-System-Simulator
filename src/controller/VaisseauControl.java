package controller;

import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vaisseau;
import model.Vecteur;

/** Cette classe contient les méthodes permettant le contrôle de la fusée dans l'interface.
 * @author Maxence
 */
public class VaisseauControl {
    //TODO : construire le controleur de la saisie de contrôle du vaisseau
	
	SystemLoader sl;
	Systeme sys;
	Vaisseau vs;
	Vecteur vit;
	
	public VaisseauControl(SystemLoader s, Systeme sys) {
		sl = s;
		this.sys = sys;
	}
	
	public void setVaisseau() {
		vs = sl.getVaisseau();
		vit = vs.getVitesse();
	}
	
	public void maj() throws InterruptedException {
		int timer = (int) sys.getDt()*1000;
		boolean running = true;
		while(running) {
			Thread.sleep(timer);
			vs.setPos(new Vecteur(vs.getPos().getPosX().doubleValue() + vs.getVitesse().getPosX().doubleValue(), vs.getPos().getPosY().doubleValue() + vs.getVitesse().getPosY().doubleValue()));
		}
	}
	
	public void up(Objet obj, double value) {
		obj.setVit(new Vecteur(vs.getVitesse().getPosX().doubleValue(), vs.getVitesse().getPosY().doubleValue() + value));
	}
	
	public void down(Objet obj, double value) {
		obj.setVit(new Vecteur(vs.getVitesse().getPosX().doubleValue(), vs.getVitesse().getPosY().doubleValue() - value));
	}
	
	public void left(Objet obj, double value) {
		obj.setVit(new Vecteur(vs.getVitesse().getPosX().doubleValue() - value, vs.getVitesse().getPosY().doubleValue()));
	}
	
	public void right(Objet obj, double value) {
		obj.setVit(new Vecteur(vs.getVitesse().getPosX().doubleValue() + value, vs.getVitesse().getPosY().doubleValue()));
	}

	public SystemLoader getModel() {
		return sl;
	}

	public Systeme getSysteme() {
		return sys;
	}
	
	
	
	
	
	
}
