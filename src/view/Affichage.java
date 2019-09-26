package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/** Classe gérant l'affichage principal du système.
 * @author Froissart Kevin
 */

public class Affichage{

	public void createSpaceShip(GraphicsContext gc /*aujouté coordonnées*/){
		/*
		Polygon triangle = new Polygon();  
		triangle.getPoints().addAll(new Double[]{  
				0.0, 0.0,  
				100.0, 200.0,  
				200.0, 100.0 });  
	*/
		//gc.fillPolygon(new Double[]{50.0,100.0,150.0}, new Double[]{50.0,100.0,150.0}, 3);
		
		Polygon triangle = new Polygon();
		triangle.getPoints().setAll(
		   50.0, 50.0,
		   60.0, 60.0,
		   20.0, 40.0
		);

	}


	public void start(Stage stage) throws Exception {

		VBox root = new VBox();

		Scene scene = new Scene(root,480,580);
		final Canvas canvas = new Canvas(480,580);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		stage.setResizable(false);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		root.getChildren().add(canvas);
		gc.setFill(Color.LIGHTSEAGREEN);
		gc.fillOval(240, 300, 15, 15);
		gc.setFill(Color.DARKRED);
		gc.fillOval(240, 250, 5, 5);
		gc.fillOval(190, 330, 5, 5);
		gc.fillOval(350, 300, 5, 5);

		
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(
		   150.0, 150.0,
		   160.0, 160.0,
		   120.0, 140.0
		);
		
		
		root.getChildren().add(triangle);

		stage.centerOnScreen();
		stage.show();
	}



}