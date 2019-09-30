package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vaisseau;

import java.util.ArrayList;

import controller.VaisseauControl;
import javafx.scene.Group;

/** Classe g�rant l'affichage principal du syst�me.
 * @author Froissart Kevin
 */

public class Affichage{
	
	VaisseauControl vc;
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	Vaisseau vs;
	
	public Affichage(VaisseauControl vc) {
		this.vc = vc;
		sl = vc.getModel();
		listeObjet = sl.objectInit();
		sys = vc.getSysteme();
	}

	/**
	 * Constructeur de la vue du vaisseau, des planêtes, et de l'étoile. 
	 * Cette fonction instancie le vaisseau aux coordonn�es indiqu� en param�tre
	 * @param x indique la position x initiale du vaisseau � son instanciation
	 * @param y indique la position y initiale du vaisseau � son instanciation 
	 * @param gc GraphicsContext du canvas de la fenêtre
	 */
	public void createSpaceShip(double x, double y , GraphicsContext gc){
		gc.setFill(Color.ORANGE);
		gc.fillRect(x, y, 7, 3);
	}

	public void createSun(double x, double y , GraphicsContext gc){
		gc.setFill(Color.RED);
		gc.fillOval(x, y, 40, 40);
	}

	public void createPlanete(double x, double y, GraphicsContext gc){
		gc.setFill(Color.BLACK);
		gc.fillOval(x, y, 15, 15);
	}

	
	public void start(Stage stage) throws Exception {
				
		final Canvas canvas = new Canvas(sys.getRayon(),sys.getRayon());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		Group root = new Group();
		Scene scene = new Scene(root, 500, 580);
		
		for(Objet o : listeObjet) {
			if(o.getType().matches("Fixe")) createSun(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			if(o.getType().matches("Simulé")) createPlanete(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			if(o.getType().matches("Vaisseau")) {
				createSpaceShip(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
				vs = (Vaisseau) o;
			}
		}
		
		scene.setOnKeyPressed( e-> {
			if(e.getCode().equals(KeyCode.DOWN)) vc.down(vs, 20);;
			if(e.getCode().equals(KeyCode.UP)) vc.up(vs, 20);
			if(e.getCode().equals(KeyCode.RIGHT)) vc.right(vs, 20);
			if(e.getCode().equals(KeyCode.LEFT)) vc.left(vs, 20);
		});
		
		root.getChildren().add(canvas);
		
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		

		stage.centerOnScreen();
		stage.show();
	}



}