package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

	/** Classe gérant l'affichage principal du système.
	 * @author Froissart Kevin
	*/

public class Affichage{
	
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
		
		
		
		stage.centerOnScreen();
		stage.show();
	}
	

}