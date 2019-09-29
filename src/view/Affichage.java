package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Objet;
import model.Systeme;

import java.util.ArrayList;

import controller.SystemLoader;
import javafx.scene.Group;

/** Classe g�rant l'affichage principal du syst�me.
 * @author Froissart Kevin
 */

public class Affichage{
	
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	
	public Affichage(SystemLoader sl, Systeme sys) {
		this.sl = sl;
		this.sys = sys;
		listeObjet = sl.objectInit();
	}

	/**
	 * Constructeur de la vue du vaisseau. 
	 * Cette fonction instancie le vaisseau aux coordonn�es indiqu� en param�tre
	 * @param x indique la position x initiale du vaisseau � son instanciation
	 * @param y indique la position y initiale du vaisseau � son instanciation 
	 * @param root layer racine de l'IHM
	 */
	public void createSpaceShip(double x, double y , GraphicsContext gc){
		gc.setFill(Color.ORANGE);
		gc.fillRect(x, y, 7, 3);
	}

	public void createSun(double x, double y , GraphicsContext gc){
		gc.setFill(Color.RED);
		gc.fillOval(x, y, 15, 15);
	}

	public void createPlanete(double x, double y, GraphicsContext gc){
		gc.setFill(Color.BLACK);
		gc.fillOval(x, y, 5, 5);
	}

	public void start(Stage stage) throws Exception {
		
		final Canvas canvas = new Canvas(sys.getRayon(),sys.getRayon());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Group root = new Group();
		Scene scene = new Scene(root, 500, 580);
		
		for(Objet o : listeObjet) {
			if(o.getName().matches("Soleil")) createSun(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			if(o.getName().length() > 6 && SystemLoader.removeAccent(o.getName().substring(0,7)).matches("Planete")) createPlanete(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			if(o.getName().matches("X")) createSpaceShip(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
		}

		root.getChildren().add(canvas);
		
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		

		stage.centerOnScreen();
		stage.show();
	}



}