package view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Objet;

/** Classe gérant l'affichage principal du système.
 * @author Lucas,Kevin
 */


public class Information implements Observer{
	ArrayList<Objet> listeObjet;
	String str ="";
	Label info=new Label();
	DecimalFormat df = new DecimalFormat("0.00");
	DecimalFormat df2 = new DecimalFormat("0.00000");
	DecimalFormat df3 = new DecimalFormat("0.0000000");
	DecimalFormat df4 = new DecimalFormat("0.000000000");
	

	public Information(ArrayList<Objet> listeObjet) {
		this.listeObjet = listeObjet;
	}
	
	public void setListe(ArrayList<Objet> listeObjet) {
		this.listeObjet = listeObjet;
	}
	
	public void start() throws Exception {
		Stage stage =new Stage();
		VBox root = new VBox();
		Scene scene = new Scene(root,350,450);


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
		str = "			FIXE \n \n";
		String sim="\n			SIMULE \n \n";
		String vais="\n			VAISSEAU \n \n";
		for(Objet o2 : listeObjet) {
			if(o2.getType().equals("Fixe")){
				str +="     "+ o2.getName()+"     Masse : "+o2.getMasse()+" kg\n";
			}
			if(o2.getType().equals("Simulé")) {
				sim += "     "+o2.getName()+"\n Masse : "+o2.getMasse()+" kg\n Position : X: "+df.format((o2.getPos().getPosX()))+
					   " m; Y: "+df.format(o2.getPos().getPosY())+" m\n Vitesse  X:"+df2.format(o2.getVitesse().getPosX()) +" m/s Y :"+df2.format(o2.getVitesse().getPosY())+" m/s\n"
					  +" Force Attraction : "+df3.format(o2.getAttraction()) +" N\n \n";
			}
			if(o2.getType().equals("Vaisseau")) {
				vais += "     "+o2.getName()+"\n Masse : "+o2.getMasse()+" kg\n Position : X: "+df.format((o2.getPos().getPosX()))+
						   " m; Y: "+df.format(o2.getPos().getPosY())+" m\n Vitesse  X:"+df2.format(o2.getVitesse().getPosX()) +" m/s Y :"+df2.format(o2.getVitesse().getPosY())+" m/s\n"
							  +" Force Attraction : "+df4.format(o2.getAttraction()) +" N\n \n";
				//System.out.println(o2.getAttraction());
			}
		}
		str+=sim+vais;
		info.setText(str);
	}
}