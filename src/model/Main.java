package model;

import java.util.ArrayList;

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

		ArrayList<Objet> listeObjet;
		
		SystemLoader sl = new SystemLoader();
		sl.reader();
		listeObjet = sl.objectInit();
		Systeme sys = sl.paramInit(4);
	
		Information info = new Information(listeObjet);
		Affichage af = new Affichage(new AffichageControl(sl, sys),info,listeObjet);

		for(Objet o : listeObjet) {
			o.addObserver(af);
			o.addObserver(info);
		}
		
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