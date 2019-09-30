package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vaisseau;
import model.Vecteur;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/** Classe g�rant l'affichage principal du syst�me.
 * @author Lucas
 */

public class Information{
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	Vaisseau vs;
	
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
			if(o.getType().matches("Vaisseau")) vs = (Vaisseau) o;
		}
		
		vs.getPos().getPosX().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				vs.setPos(new Vecteur(newValue.doubleValue(),vs.getPos().getPosY().doubleValue()));
			}
		});

		vs.getPos().getPosY().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				vs.setPos(new Vecteur(vs.getPos().getPosY().doubleValue(), newValue.doubleValue()));
			}
		});
		
		info.setText(phra);
		stage.setResizable(true);
		stage.setTitle("Informations détaillées");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
	}

}
