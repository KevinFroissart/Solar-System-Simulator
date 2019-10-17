package controller;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vaisseau;
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
	public void left(Vaisseau obj, boolean avance) {
		obj.left(avance);
	}

	/** Active le propulseur gauche du vaisseau pour se déplacer à droite */
	public void right(Vaisseau obj, boolean avance) {
		obj.right(avance);
	}

	/** Active le propulseur bas du vaisseau pour accélérer */
	public void down(Vaisseau obj, boolean avance) {
		obj.down(avance);
	}

	/** Active le propulseur haut du vaisseau pour ralentir/freiner/reculer */
	public void up(Vaisseau obj, boolean avance) {
		obj.up(avance);
	}

	/** Méthode qui définit la vitesse de l'objet en fonction de son accélération et de l'attraction des autres objets (planètes) */
	public void Force(Objet objA, Objet objB) {
		double distance = Math.sqrt(Math.pow((objA.getPos().getPosX() - objB.getPos().getPosX()),2) + Math.pow(objA.getPos().getPosY() - objB.getPos().getPosY(), 2));
		objA.setAttraction((sys.getG()*objA.getMasse()*objB.getMasse()) / Math.pow(distance, 2));
		double a = objA.getAttraction() / objA.getMasse();
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

	public ArrayList<Objet> resetObj() {
		return sl.objectInit();
	}

	public Systeme resetSys() {
		return sl.paramInit(4);
	}

	public File getFileExplorer (Stage stage) {
		final FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("Astro", "*.astro"));
		try {
			File selectedFile = fileChooser.showOpenDialog(stage);
			return selectedFile;
		} catch(NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return sl.getFile();
	}

	public void setSlider(Systeme sys, double value) {
		sys.setDt(value);
	}

	public ArrayList<Image> loadImages(){
		ArrayList<Image> image = new ArrayList<Image>();
		try {
			image.add(new Image("File:ressources/image/ceres.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/eris.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/jupiter.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/lune.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/mars.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/mercure.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/neptune.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/pluton.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/saturne.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/terre.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/uranus.png", 30,30,true,false));
			image.add(new Image("File:ressources/image/venus.png", 30,30,true,false));
		}
		catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return image;
	}

}