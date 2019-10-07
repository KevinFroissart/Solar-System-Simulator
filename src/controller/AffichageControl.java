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
		sl.getVaisseau().setVit(new Vecteur(-value, 0));
	}

	public void right(Objet obj, double value) {
		sl.getVaisseau().setVit(new Vecteur(+value, 0));
	}

	public void down(Objet obj, double value) {
		sl.getVaisseau().setVit(new Vecteur(0, +value));
	}

	public void up(Objet obj, double value) {
		sl.getVaisseau().setVit(new Vecteur(0, -value));
	}

	public void posVaisseau() {
		double f = (sys.getG()*sl.getSoleil().getMasse()*sl.getVaisseau().getMasse())
				/
				((Math.pow(sl.getVaisseau().getPos().getPosX()-sl.getSoleil().getPos().getPosX(), 2)
						+
						Math.pow(sl.getVaisseau().getPos().getPosY()-sl.getSoleil().getPos().getPosY(), 2)));
		double acc = f/sl.getVaisseau().getMasse();
		double hypothenus = Math.sqrt(Math.pow(sl.getVaisseau().getPos().getPosX()-sl.getSoleil().getPos().getPosX(), 2)
				+
				Math.pow(sl.getVaisseau().getPos().getPosY()-sl.getSoleil().getPos().getPosY(), 2));
		double hypoSurAcc = hypothenus/acc;
		sl.getVaisseau().setPos(
				new Vecteur(sl.getVaisseau().getPos().getPosX() + sl.getVaisseau().getVitesse().getPosX() - sl.getVaisseau().getPos().getPosX()/hypoSurAcc, 
						sl.getVaisseau().getPos().getPosY() + sl.getVaisseau().getVitesse().getPosY() - sl.getVaisseau().getPos().getPosY()/hypoSurAcc));
		System.out.println("posX :"+ sl.getVaisseau().getPos().getPosX());
		double px = sl.getVaisseau().getPos().getPosX() + sl.getVaisseau().getVitesse().getPosX();
		System.out.println("posX + vit :" + px);
		double pxa = (sl.getVaisseau().getPos().getPosX() + sl.getVaisseau().getVitesse().getPosX()) - (sl.getVaisseau().getPos().getPosX()/hypoSurAcc);
		System.out.println("posX + vit - acc :" + pxa);
		System.out.println("hypothenus :" + hypothenus);
		System.out.println("hypo / acc :" + hypoSurAcc);
		System.out.println("force :" + f);
		System.out.println("acc : " + acc);
	}

	public void posPlanete() {
		double f = (sys.getG()*sl.getSoleil().getMasse()*sl.getPlanete().getMasse())
				/
				((Math.pow(sl.getPlanete().getPos().getPosX()-sl.getSoleil().getPos().getPosX(), 2)
						+
						Math.pow(sl.getPlanete().getPos().getPosY()-sl.getSoleil().getPos().getPosY(), 2)));
		//System.out.println("Force d'attraction :" + f);
		double acc = f/sl.getPlanete().getMasse();
		//System.out.println("Acceleration :" + acc);
		double hypothenus = Math.sqrt(Math.pow(sl.getPlanete().getPos().getPosX()-sl.getSoleil().getPos().getPosX(), 2)
				+
				Math.pow(sl.getPlanete().getPos().getPosY()-sl.getSoleil().getPos().getPosY(), 2));
		double hypoSurAcc = hypothenus/acc;
		sl.getPlanete().setPos(
				new Vecteur(sl.getPlanete().getPos().getPosX() - sl.getPlanete().getPos().getPosX()/hypoSurAcc + sl.getPlanete().getVitesse().getPosX(), 
						sl.getPlanete().getPos().getPosY() - sl.getPlanete().getPos().getPosY()/hypoSurAcc + sl.getPlanete().getVitesse().getPosY()));
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