package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import model.Objet;
import model.SystemLoader;
import model.Systeme;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.VaisseauControl;
import javafx.scene.Group;

/** Classe g�rant l'affichage principal du syst�me.
 * @author Froissart Kevin
 */

public class Affichage implements Observer{

	VaisseauControl vc;
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	Canvas canvas;
	GraphicsContext gc;

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
		
		canvas = new Canvas(sys.getRayon(),sys.getRayon());
		gc = canvas.getGraphicsContext2D();		
		Timeline tl = new Timeline(new KeyFrame(Duration.seconds(sys.getDt()), e -> run(gc)));
		tl.setCycleCount(Timeline.INDEFINITE);

		
		Group root = new Group();
		Scene scene = new Scene(root, 500, 580);
		
		scene.setOnKeyPressed( e-> {
			if(e.getCode().equals(KeyCode.DOWN)) vc.down(sl.getVaisseau(), 4);
			if(e.getCode().equals(KeyCode.UP)) vc.up(sl.getVaisseau(), 4);
			if(e.getCode().equals(KeyCode.RIGHT)) vc.right(sl.getVaisseau(), 4);
			if(e.getCode().equals(KeyCode.LEFT)) vc.left(sl.getVaisseau(), 4);
		});
		
		root.getChildren().add(canvas);
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
		tl.play();
	}
	
	private void run(GraphicsContext gc) {
		gc.clearRect(0, 0, sys.getRayon(), sys.getRayon());
		for(Objet o : listeObjet) {
			if(o.getType().matches("Fixe")) createSun(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY() + sys.getRayon()/2, gc);
			if(o.getType().matches("Simulé")) createPlanete(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
		}
		
		vc.pos();
		createSpaceShip(sl.getVaisseau().getPos().getPosX()/2 + sys.getRayon()/2, sl.getVaisseau().getPos().getPosY()/2 + sys.getRayon()/2, gc);
		vc.bordure();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}
}