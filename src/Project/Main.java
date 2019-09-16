package Project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
	public void start(Stage stage) throws Exception {
		
		VBox root = new VBox();
		
		Scene scene = new Scene(root,480,580);

		stage.setResizable(false);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void main(String[] a){
		Application.launch(a);
	}
}
