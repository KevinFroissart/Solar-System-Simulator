package controller;

import java.io.File;
import java.util.ArrayList;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vecteur;

/** Cette classe contient les méthodes permettant le contrôle de la fusée et l'interaction des objets entre eux.
 * @author Maxence, Kévin
 */
public class AffichageControl {

	SystemLoader sl;
	Systeme sys;

	public AffichageControl(SystemLoader s, Systeme sys) {
		sl = s;
		this.sys = sys;
	}

	/** Active le propulseur droit du vaisseau pour se déplacer à gauche */
	public void left(Objet obj, double value) {
		obj.setVit(new Vecteur(obj.getVitesse().getPosX()+value, obj.getVitesse().getPosY()));
	}

	/** Active le propulseur gauche du vaisseau pour se déplacer à droite */
	public void right(Objet obj, double value) {
		obj.setVit(new Vecteur(obj.getVitesse().getPosX()-value, obj.getVitesse().getPosY()));
	}
	
	/** Active le propulseur bas du vaisseau pour accélérer */
	public void down(Objet obj, double value) {
		obj.setVit(new Vecteur(obj.getVitesse().getPosX(), obj.getVitesse().getPosY()-value));
	}

	/** Active le propulseur haut du vaisseau pour ralentir/freiner/reculer */
	public void up(Objet obj, double value) {
		obj.setVit(new Vecteur(obj.getVitesse().getPosX(), obj.getVitesse().getPosY()+value));
	}

	/** Méthode qui définit la vitesse de l'objet en fonction de son accélération et de l'attraction des autres objets (planètes) */
	public void Force(Objet objA, Objet objB) {
		double distance = Math.sqrt(Math.pow((objA.getPos().getPosX() - objB.getPos().getPosX()),2) + Math.pow(objA.getPos().getPosY() - objB.getPos().getPosY(), 2));
		double f = (sys.getG()*objA.getMasse()*objB.getMasse()) / Math.pow(distance, 2);
		double a = f / objA.getMasse() * sys.getFa();
		double dirX = (objB.getPos().getPosX() - objA.getPos().getPosX()) / distance;
		double dirY = (objB.getPos().getPosY() - objA.getPos().getPosY()) / distance;
		
		objA.setVit(new Vecteur(objA.getVitesse().getPosX() + dirX * a, objA.getVitesse().getPosY() + dirY * a));
	}
	
	/** Changer la position de l'objet en paramètre*/ 
	public void pos(Objet obj) {
		obj.setPos(new Vecteur(obj.getPos().getPosX() + obj.getVitesse().getPosX(), obj.getPos().getPosY() + obj.getVitesse().getPosY()));
	}
	
	/** Effet "PacMan", quand un objet traverse une bordure de fenêtre il apparait à la bordure opposé*/
	public void bordure(Objet obj) {
		if(obj.getPos().getPosX() > sys.getRayon()) obj.setPos(new Vecteur(-sys.getRayon(), obj.getPos().getPosY()));
		if(obj.getPos().getPosX() < -sys.getRayon()) obj.setPos(new Vecteur(sys.getRayon(), obj.getPos().getPosY()));
		if(obj.getPos().getPosY() > sys.getRayon()) obj.setPos(new Vecteur(obj.getPos().getPosX(), -sys.getRayon()));
		if(obj.getPos().getPosY() < -sys.getRayon()) obj.setPos(new Vecteur(obj.getPos().getPosX(), sys.getRayon()));
	}

	/** Retourne le SystemLoader du controller 
	 * 
	 * @return sl*/
	public SystemLoader getModel() {
		return sl;
	}

	/** Retourne le systeme actuel du controller
	 * 
	 * @return sys;
	 */
	public Systeme getSysteme() {
		return sys;
	}
	
	public ArrayList<Objet> reset() {
		return sl.objectInit();
	}
	
	public File getFileExplorer (Stage stage) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("Astro", "*.astro"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		return selectedFile;
	}

}