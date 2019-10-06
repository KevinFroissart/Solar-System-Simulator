package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vaisseau;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/** Classe g�rant l'affichage principal du syst�me.
 * @author Lucas,Kevin
 */

public class Information implements Observer{
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	Vaisseau vs;
	String str ="";
	Label info=new Label();

	public Information(SystemLoader sl, Systeme sys) {
		this.sl = sl;
		this.sys = sys;
		listeObjet = sl.objectInit();
	}

	public void start() throws Exception {
		Stage stage =new Stage();
		VBox root = new VBox();
		Scene scene = new Scene(root);

		root.getChildren().add(info);
		for(Objet o : listeObjet) {
			str += o.getName()+"         Masse : "+o.getMasse()+"        X: "+o.getPos().getPosX()+"; Y: "+o.getPos().getPosY()+"    \n";
		}
		str += sl.getVaisseau().getName() + "         Masse : "+sl.getVaisseau().getMasse()+"        X: "+sl.getVaisseau().getPos().getPosX()+"; Y: "+sl.getVaisseau().getPos().getPosY()+"    \n";

		
		info.setText(str);
		stage.setResizable(true);
		stage.setTitle("Informations détaillées");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		str = "";
		for(Objet o2 : listeObjet) {
			str += o2.getName()+"         Masse : "+o2.getMasse()+"        X: "+o2.getPos().getPosX()+"; Y: "+o2.getPos().getPosY()+"    \n";
		}
		str += sl.getVaisseau().getName() + "         Masse : "+sl.getVaisseau().getMasse()+"        X: "+sl.getVaisseau().getPos().getPosX()+"; Y: "+sl.getVaisseau().getPos().getPosY()+"    \n";
		info.setText(str);
	}

}