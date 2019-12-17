package controller;

import java.io.File;
import java.util.List;
import java.util.Observable;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.*;

/** Cette classe contient les méthodes permettant le contrôle de la fusée et l'interaction des objets entre eux.
 * @author Maxence, Kévin
 */
public class AffichageControl extends Observable {

	SystemLoader sl;
	Systeme sys;

	public AffichageControl(SystemLoader s, Systeme sys) {
		sl = s;
		this.sys = sys;
	}

	/**
	 * Active le propulseur droit du vaisseau pour se déplacer à gauche
	 */
	public void left(Vaisseau obj, boolean avance) {
		obj.left(avance);
	}

	/**
	 * Active le propulseur gauche du vaisseau pour se déplacer à droite
	 */
	public void right(Vaisseau obj, boolean avance) {
		obj.right(avance);
	}

	/**
	 * Active le propulseur bas du vaisseau pour accélérer
	 */
	public void down(Vaisseau obj, boolean avance) {
		obj.down(avance);
	}

	/**
	 * Active le propulseur haut du vaisseau pour ralentir/freiner/reculer
	 */
	public void up(Vaisseau obj, boolean avance) {
		obj.up(avance);
	}

	/**
	 * Changer la position de l'objet en paramètre
	 */
	public void pos(Objet obj) { //TO:DO ajouter le temps t à la signature et au corps de la fonction
		if(!obj.getType().equals("Cercle") && !obj.getType().equals("Ellipse")) {
			obj.setPos(new Vecteur(obj.getPos().getPosX() + obj.getVitesse().getPosX(), obj.getPos().getPosY() + obj.getVitesse().getPosY()));
		} else if(obj.getType().equals("Cerlce")) {
			ObjetCercle o = (ObjetCercle) obj;
			double angle = 2*Math.PI / (/*t%*/ o .getPeriode());
			double x2 = Math.pow(o.getPos().getPosX()-o.getCentre().getPos().getPosX(),2);
			double y2 = Math.pow(o.getPos().getPosY()-o.getCentre().getPos().getPosY(),2);
			double x = o.getCentre().getPos().getPosX() + Math.sqrt(x2+y2) * Math.cos(angle);
			double y = o.getCentre().getPos().getPosX() + Math.sqrt(x2+y2) * Math.sin(angle);
			obj.setPos(new Vecteur(x, y));
		} else {
			ObjetEllipse o = (ObjetEllipse) obj;
			
		}
	}

	/**
	 * Retourne le SystemLoader du controller
	 *
	 * @return sl
	 */
	public SystemLoader getModel() {
		return sl;
	}

	/**
	 * Retourne le systeme actuel du controller
	 *
	 * @return sys;
	 */
	public Systeme getSysteme() {
		return sys;
	}

	public List<Objet> resetObj() {
		return sl.objectInit();
	}

	public Systeme resetSys() {
		return sl.paramInit(4);
	}

	public File getFileExplorer(Stage stage) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("Astro", "*.astro"));
		try {
			File selectedFile = fileChooser.showOpenDialog(stage);
			return selectedFile;
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return sl.getFile();
	}
}
