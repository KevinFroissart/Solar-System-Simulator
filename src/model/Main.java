package model;

import controller.AffichageControl;
import javafx.application.Application;
import javafx.stage.Stage;
import view.Affichage;
import view.Information;

/** Modèle initialisant les différentes vues
 * @author Froissart Kevin
 */

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		SystemLoader sl = new SystemLoader();
		sl.reader();	
		Systeme sys = sl.paramInit(4);

	
		Affichage af = new Affichage(new AffichageControl(sl, sys));
		Information info = new Information(sl,sys);

		for(Objet o : sl.objectInit()) {
			o.addObserver(af);
			o.addObserver(info);
		}
		
		try {
			af.start(primaryStage);
			info.start();
		} catch (Exception e1) {
			e1.printStackTrace();

		}
	}

	public static void main(String[] a){
		Application.launch(a);
	}
}