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

	public VaisseauControl(SystemLoader s, Systeme sys) {
		sl = s;
		this.sys = sys;
	}

	public void maj() throws InterruptedException {
		int timer = (int) sys.getDt()*1000;
		for(int i = 0; i<1000000; i++) {
			Thread.sleep(timer);
			pos();
			System.out.println(sl.getVaisseau().getPos());
		}
	}

	public void left(Objet obj, double value) {
		sl.getVaisseau().setVit(new Vecteur(-value, 0));
		System.out.println(sl.getVaisseau().getPos());
	}

	public void right(Objet obj, double value) {
		sl.getVaisseau().setVit(new Vecteur(+value, 0));
		System.out.println(sl.getVaisseau().getPos());
	}

	public void down(Objet obj, double value) {
		sl.getVaisseau().setVit(new Vecteur(0, +value));
		System.out.println(sl.getVaisseau().getPos());
	}

	public void up(Objet obj, double value) {
		sl.getVaisseau().setVit(new Vecteur(0, -value));
		System.out.println(sl.getVaisseau().getPos());
	}

	public void pos() {
		sl.getVaisseau().setPos(new Vecteur(sl.getVaisseau().getPos().getPosX() + sl.getVaisseau().getVitesse().getPosX(), sl.getVaisseau().getPos().getPosY() + sl.getVaisseau().getVitesse().getPosY()));
	}
	
	public void bordure() {
		if(sl.getVaisseau().getPos().getPosX() > sys.getRayon()) sl.getVaisseau().setPos(new Vecteur(-sys.getRayon(), sl.getVaisseau().getPos().getPosY()));
		if(sl.getVaisseau().getPos().getPosX() < -sys.getRayon()) sl.getVaisseau().setPos(new Vecteur(sys.getRayon(), sl.getVaisseau().getPos().getPosY()));
		if(sl.getVaisseau().getPos().getPosY() > sys.getRayon()) sl.getVaisseau().setPos(new Vecteur(sl.getVaisseau().getPos().getPosX(), -sys.getRayon()));
		if(sl.getVaisseau().getPos().getPosY() < -sys.getRayon()) sl.getVaisseau().setPos(new Vecteur(sl.getVaisseau().getPos().getPosX(), sys.getRayon()));
	}
	
	public SystemLoader getModel() {
		return sl;
	}

	public Systeme getSysteme() {
		return sys;
	}
}