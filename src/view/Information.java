package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Objet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/** Classe g�rant l'affichage principal du syst�me.
 * @author Lucas,Kevin
 */


public class Information implements Observer{
	ArrayList<Objet> listeObjet;
	String str ="";
	Label info=new Label();
	DecimalFormat df = new DecimalFormat("0.00");

	public Information(ArrayList<Objet> listeObjet) {
		this.listeObjet = listeObjet;
	}
	
	public void setListe(ArrayList<Objet> listeObjet) {
		this.listeObjet = listeObjet;
	}
	
	public void start() throws Exception {
		Stage stage =new Stage();
		VBox root = new VBox();
		Scene scene = new Scene(root,450,300);


		root.getChildren().add(info);
		info.setText(str);
		stage.setResizable(true);
		stage.setTitle("Informations détaillées");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		str = "						FIXE \n \n \n \n";
		String sim="\n						SIMULE \n \n \n \n";
		String vais="\n						VAISSEAU \n \n \n \n";
		for(Objet o2 : listeObjet) {
			if(o2.getType().equals("Fixe")){
				str +="     "+ o2.getName()+"     Masse : "+o2.getMasse()+"\n";
			}
			if(o2.getType().equals("Simulé")) {
				sim += "     "+o2.getName()+"     Masse : "+o2.getMasse()+" Position : X: "+df.format((o2.getPos().getPosX()))+"; Y: "+df.format(o2.getPos().getPosY())+"\n";
			}
			if(o2.getType().equals("Vaisseau")) {
				vais += "     "+o2.getName()+"     Masse : "+o2.getMasse()+" Position : X: "+df.format((o2.getPos().getPosX()))+"; Y: "+df.format(o2.getPos().getPosY())+"\n";
			}
		}
		str+=sim+vais;
		info.setText(str);
	}
}