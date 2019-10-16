package model;

import java.io.File;
import controller.AffichageControl;
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
		sl.reader(new File("ressources/system.txt"));
		Systeme sys = sl.paramInit(4);

	
		Affichage af = new Affichage(new AffichageControl(sl, sys));

		
		try {
			af.chargerImgPlanetes();
			af.start(primaryStage);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] a){
		Application.launch(a);
	}
}