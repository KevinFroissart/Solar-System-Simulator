package model;

import controller.SystemLoader;
import controller.VaisseauControl;
import javafx.application.Application;
import javafx.stage.Stage;
import view.Affichage;

/** Modèle initialisant les différentes vues
 * @author Froissart Kevin
*/

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		SystemLoader sl = new SystemLoader();
		sl.reader();
		VaisseauControl vc = new VaisseauControl();
		Systeme = new Systeme(sl.paramInit(4));
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