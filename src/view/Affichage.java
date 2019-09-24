package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	/** Classe gérant l'affichage principal du système.
	 * @author Froissart 
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
		
		stage.centerOnScreen();
		stage.show();
	}
	

}