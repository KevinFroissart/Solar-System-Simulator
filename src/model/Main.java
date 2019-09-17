package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Affichage;

/** Modèle initialisant les différentes vues
 * @author Froissart 
*/

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		Affichage af = new Affichage();

		try {
			af.start(primaryStage);
		} catch (Exception e1) {
			e1.printStackTrace();

		}
	}
	public static void main(String[] a){
		Application.launch(a);
	}
}