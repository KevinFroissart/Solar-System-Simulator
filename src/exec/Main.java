package exec;
import controller.AffichageControl;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SystemLoader;
import model.Systeme;
import view.Affichage;

import java.io.File;
import java.io.FileNotFoundException;

/** Modèle initialisant les différentes vues
 * @author Kevin
 * @author Maxence
 */

public class Main extends Application{

	static File config;

	@Override
	public void start(Stage primaryStage) throws Exception {
		SystemLoader sl = new SystemLoader();
		try {
			sl.reader(config);
			Systeme sys = sl.paramInit(4);
			Affichage af = new Affichage(new AffichageControl(sl, sys));
			af.start(primaryStage);
		} catch (FileNotFoundException e) {
			System.err.println("Impossible de trouver le fichier spécifié. Vérifiez votre chemin de fichier. Ex : ressources/system.txt");
			System.exit(1);
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args){
		try {
			config = new File(args[0]);
		} catch (NullPointerException e) {
			System.err.println("Impossible de trouver le fichier spécifié. Vérifiez votre chemin de fichier. Ex : ressources/system.txt");
			System.exit(1);
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Le paramètre pris en ligne de commande est incorrect, le chemin du fichier config doit être le premier argument");
			System.err.println("Exemple : config = new File(args[x]); ---> x doit valoir le x ième argument du fichier en ligne de commande (pour nous x = 0)");
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		Application.launch(args);
	}
}