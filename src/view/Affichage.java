package view;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;

/** Classe gérant l'affichage principal du système.
 * @author Froissart Kevin
 */

public class Affichage{
	/**
	 * Constructeur de la vue du vaisseau. 
	 * Cette fonction instancie le vaisseau aux coordonnées indiqué en paramètre
	 * @param x indique la position x initiale du vaisseau à son instanciation
	 * @param y indique la position y initiale du vaisseau à son instanciation 
	 * @param root layer racine de l'IHM
	 */
	public void createSpaceShip(int x, int y , Group root){

		Rectangle vaisseau = new Rectangle(x, y,7,3);
		vaisseau.setFill(Color.RED);
		root.getChildren().add(vaisseau);
	}

	public void createSun(int x, int y ,Group root){

		Circle sun = new Circle(x,y,15);
		//sun.setFill(null);
		root.getChildren().addAll(sun);
	}

	public void createPlanete(int x, int y, Group root ){

		Circle planete = new Circle(x,y,5);
		//planete.setFill(null);
		root.getChildren().addAll(planete);
	}

	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 480, 580);




		stage.setResizable(false);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 


		// forme exemple :
		Circle p1 = new Circle(240,300,15);
		p1.setFill(Color.LIGHTSEAGREEN);
		Circle p2 = new Circle(240,250,5);
		Circle p3 = new Circle(190,330,5);
		Circle p4 = new Circle(350,300,5);
		
		createSun(300, 200, root);
		createPlanete(100, 400, root);
		createSpaceShip(80, 80, root);

		root.getChildren().addAll(p1,p2,p3,p4);

		stage.centerOnScreen();
		stage.show();
	}



}