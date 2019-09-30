package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Objet;
import model.SystemLoader;
import model.Systeme;
import java.util.ArrayList;
import javafx.application.Application;

/** Classe g�rant l'affichage principal du syst�me.
 * @author Lucas
 */

public class Information{
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	
	public Information(SystemLoader sl, Systeme sys) {
		this.sl = sl;
		this.sys = sys;
		listeObjet = sl.objectInit();
	}
	
public void start() throws Exception {
		Stage stage =new Stage();
		VBox root = new VBox();
		Label info=new Label();
		String phra ="";
		Scene scene = new Scene(root);

		root.getChildren().add(info);
		for(Objet o : listeObjet) {
			phra+=o.getName()+"         Masse : "+o.getMasse()+"        X: "+o.getPos().getPosX()+"; Y: "+o.getPos().getPosY()+"    \n";
		}
		info.setText(phra);
		stage.setResizable(true);
		stage.setTitle("Informations détaillées");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
	}

}
