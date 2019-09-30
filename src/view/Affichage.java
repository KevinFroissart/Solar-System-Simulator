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
			if(o.getName().matches("Soleil")) createSun(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			if(o.getName().length() > 6 && SystemLoader.removeAccent(o.getName().substring(0,7)).matches("Planete")) createPlanete(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			if(o.getName().matches("X")) createSpaceShip(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
		}
		
		scene.setOnKeyPressed( e-> {
			if(e.getCode().equals(KeyCode.DOWN)) System.out.println("Down Key Pressed");
			if(e.getCode().equals(KeyCode.UP)) System.out.println("UP Key Pressed");
			if(e.getCode().equals(KeyCode.RIGHT)) System.out.println("Right Key Pressed");
			if(e.getCode().equals(KeyCode.LEFT)) System.out.println("Left Key Pressed");
		});
		
		
		root.getChildren().add(canvas);
		
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		

		stage.centerOnScreen();
		stage.show();
	}



}