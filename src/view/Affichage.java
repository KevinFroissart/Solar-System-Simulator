package view;

import controller.AffichageControl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

/** Classe gérant l'affichage principal du système.
 * @author Kevin, Maxence
 */

public class Affichage implements Observer {

	Label tmp=new Label();
	Label str=new Label();
	Label sim=new Label();
	Label vais=new Label();
	Label cercl=new Label();
	DecimalFormat df = new DecimalFormat("0.00");
	DecimalFormat df2 = new DecimalFormat("0.00000");
	DecimalFormat df3 = new DecimalFormat("0.0000000");
	AffichageControl ac;
	SystemLoader sl;
	List<Objet> listeObjet;
	Systeme sys;
	Canvas layer1;
	Canvas layer2;
	GraphicsContext gc1;
	GraphicsContext gc2;
	Label info = new Label();
	TextField cryog;
	Button boutoncryo;
	boolean afficherVaisseau = true;
	boolean afficherPlanete = true;
	boolean afficherSoleil = true;
	boolean afficherCercle = true;
	boolean afficherTrajectoire;
	double vitesseSimu;
	HBox hb = new HBox();
	Timeline tl;
	Pane bpane = new Pane();
	Scene scene;
	int temps = 0;
	int seconde = 0;
	int minute = 0;
	int heure = 0;
	double oldDt;
	double rate;
	int disabled;

	public Affichage(AffichageControl ac) {
		this.ac = ac;
		sl = ac.getModel();
		listeObjet = sl.objectInit();
		sys = ac.getSysteme();
	}

	/**
	 * Constructeur de la vue du vaisseau, des planêtes, et de l'étoile.
	 * @param o donne l'objet à afficher
	 * @param gc GraphicsContext du canvas de la fenêtre
	 */
	public void drawObject(Objet o, GraphicsContext gc) {
		double x = o.getPos().getPosX()/2 + sys.getRayon()/2;
		double y = o.getPos().getPosY()/2 + sys.getRayon()/2;
		if(o.getType().matches("Fixe") && afficherSoleil) gc.drawImage(o.getImage(), x-(o.getTaille()/2), y-(o.getTaille()/2), o.getTaille(), o.getTaille());
		if(o.getType().matches("Simulé") && afficherPlanete) gc.drawImage(o.getImage(), x-(o.getTaille()/2), y-(o.getTaille()/2), o.getTaille(), o.getTaille());
		if(o.getType().matches("Vaisseau") && afficherVaisseau) gc.drawImage(o.getImage(),x-(o.getTaille()/4+3), y-(o.getTaille()/4+3), o.getTaille()/2+3, o.getTaille()/2+3);
		if(o.getType().matches("Cercle") && afficherCercle) gc.drawImage(o.getImage(), x-(o.getTaille()/2), y-(o.getTaille()/2), o.getTaille(), o.getTaille());
	}

	public void updateInfo() {
		 str.setText("			FIXE \n \n");
		sim.setText("\n			SIMULE \n \n");
		vais.setText("\n			VAISSEAU \n \n");
		cercl.setText("\n			CERCLE \n \n");
		for(Objet o2 : listeObjet) {
			if(o2.getType().equals("Fixe")) str.setText(str.getText()+"     "+ o2.getName()+"     Masse : "+o2.getMasse()+" kg\n");
			if(o2.getType().equals("Simulé")) sim.setText(sim.getText()+"     "+o2.getName()+"\n Masse : "+o2.getMasse()+
					" kg\n Position : X: "+df.format((o2.getPos().getPosX()))+ " m; Y: "+df.format(o2.getPos().getPosY())+
					" m\n Vitesse  X:"+df2.format(o2.getVitesse().getPosX()) +" m/s Y :"+df2.format(o2.getVitesse().getPosY())+" m/s\n");
			if(o2.getType().equals("Vaisseau")) vais.setText(vais.getText()+"     "+o2.getName()+"\n Masse : "+o2.getMasse()+
					" kg\n Position : X: "+df.format((o2.getPos().getPosX()))+" m; Y: "+df.format(o2.getPos().getPosY())+
					" m\n Vitesse  X:"+df2.format(o2.getVitesse().getPosX()) +" m/s Y :"+df2.format(o2.getVitesse().getPosY())+" m/s\n"+
					" Force Attraction: "+df2.format(o2.getAttraction())+" *10^-9 N\n\n");
			if(o2.getType().equals("Cercle"))  cercl.setText(cercl.getText()+"     "+o2.getName()+"\n Masse : "+o2.getMasse()+
					" kg\n Position : X: "+df.format((o2.getPos().getPosX()))+" m; Y: "+df.format(o2.getPos().getPosY())+"\n");
		}
		tmp.setText("Temps écoulé: " + heure + ":" + minute + ":" + seconde+"\n\n");
		info.setText(str.getText()+cercl.getText()+sim.getText()+vais.getText()+tmp.getText());
	}
	
	public void start(Stage stage) throws Exception {

		updateInfo();
		oldDt = sys.getDt();
		layer1 = new Canvas(sys.getRayon(),sys.getRayon());
		layer2 = new Canvas(sys.getRayon(),sys.getRayon());
		gc1 = layer1.getGraphicsContext2D();
		gc2 = layer2.getGraphicsContext2D();
		bpane.setMinSize(sys.getRayon(),sys.getRayon());
		bpane.getChildren().addAll(layer1,layer2);
		layer1.toFront();
		VBox fenetre = new VBox();
		HBox informations = new HBox();
		Label cryo = new Label("Entrez un entier ");
		cryog = new TextField();
		
		VBox vb = new VBox();
		ToolBar toolBar = new ToolBar();
		Button open = new Button("Ouvrir");
		Button reset = new Button("Reset");
		Separator separator = new Separator();
		boutoncryo = new Button("Cryo-sommeil");
		ToggleButton bvs = new ToggleButton("Vaisseau");
		ToggleButton bp = new ToggleButton("Planètes");
		ToggleButton bs = new ToggleButton("Soleil");
		ToggleButton blayer = new ToggleButton("Trajectoire");
		ToggleButton bc = new ToggleButton("Cercle");

		fenetre.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
				+ "-fx-border-width: 2;" + "-fx-border-insets: 10;"
				+ "-fx-border-radius: 10;" + "-fx-border-color: black;");
		toolBar.getItems().addAll(open,reset,separator,bvs,bp,bs,bc,blayer);

		//Unité de temps et de simulation du systeme
		tl = new Timeline(new KeyFrame(Duration.seconds(sys.getDt()/sys.getFa()), e->run()));
		tl.setCycleCount(Timeline.INDEFINITE);

		Group root = new Group();
		scene = new Scene(root);
		//Ici j'ajoute le background image à la VBox qui est root de notre systeme
		BackgroundImage back = new BackgroundImage(new Image("File:ressources/background.jpg",0, 0, true, false),
		BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		vb.setBackground(new Background(back));

		open.setOnAction( e-> {
			try {
				File file = ac.getFileExplorer(stage);
				if(file != null) {
					sl.reader(file);
					listeObjet = ac.resetObj();
					sys = ac.resetSys();
					sys.addObserver(Affichage.this);
				}
			}
			catch(NullPointerException e1) {
				e1.printStackTrace();
			}
			catch(Exception e2) {
				e2.printStackTrace();
			}
		});

		reset.setOnAction( e-> {
			temps = 0;
			listeObjet = ac.resetObj();
			sys = ac.resetSys();
			gc2.clearRect(0,0,sys.getRayon(),sys.getRayon());
			afficherTrajectoire = false;
			blayer.setSelected(false);
			bp.setSelected(false);
			bs.setSelected(false);
			bvs.setSelected(false);
			sys.addObserver(Affichage.this);
		});

		bvs.setOnAction( e-> {
			if(bvs.isSelected()){
				bvs.setSelected(true);
				afficherVaisseau = false;
			}
			else{
				bvs.setSelected(false);
				afficherVaisseau = true;
			}
		});

		bp.setOnAction( e-> {
			if(bp.isSelected()){
				bp.setSelected(true);
				afficherPlanete = false;
			}
			else{
				bp.setSelected(false);
				afficherPlanete = true;
			}
		});

		bs.setOnAction( e-> {

			if(bs.isSelected()){
				bs.setSelected(true);
				afficherSoleil = false;
			}
			else{
				bs.setSelected(false);
				afficherSoleil = true;
			}
		});

		bc.setOnAction( e-> {
			if(bc.isSelected()){
				bc.setSelected(true);
				afficherCercle = false;
			}
			else{
				bc.setSelected(false);
				afficherCercle = true;
			}
		});

		blayer.setOnAction( e -> {
			if(blayer.isSelected()){
				blayer.setSelected(true);
				afficherTrajectoire = true;
			}
			else{
				blayer.setSelected(false);
				afficherTrajectoire = false;
				gc2.clearRect(0,0,sys.getRayon(),sys.getRayon());
			}
		});

		scene.setOnKeyPressed( e-> {
			for(Objet o : listeObjet) {
				if(o.getType().equals("Vaisseau")) {
					if(e.getCode().equals(KeyCode.DOWN)) ac.down((Vaisseau)o, true);
					if(e.getCode().equals(KeyCode.UP)) ac.up((Vaisseau)o, true);
					if(e.getCode().equals(KeyCode.RIGHT)) ac.right((Vaisseau)o, true);
					if(e.getCode().equals(KeyCode.LEFT)) ac.left((Vaisseau)o, true);
				}
			}
		});

		scene.setOnKeyReleased( e-> {
			for(Objet o : listeObjet) {
				if(o.getType().equals("Vaisseau")) {
					if(e.getCode().equals(KeyCode.DOWN)) ac.down((Vaisseau)o, false);
					if(e.getCode().equals(KeyCode.UP)) ac.up((Vaisseau)o, false);
					if(e.getCode().equals(KeyCode.RIGHT)) ac.right((Vaisseau)o, false);
					if(e.getCode().equals(KeyCode.LEFT)) ac.left((Vaisseau)o, false);
				}
			}
		});

		boutoncryo.setOnAction( e-> {
			if(cryog.getText().matches("[0-9]*") && !cryog.getText().equals("")){
				if(Double.parseDouble(cryog.getText()) <= 1000) {
					gc2.clearRect(0, 0, sys.getRayon(), sys.getRayon());
					int tempsCalcule = (int) (Double.parseDouble(cryog.getText()) * sys.getFa() / sys.getDt());
					temps += tempsCalcule;
					ac.cryoSommeil(tempsCalcule, listeObjet, temps);
				} else {
					cryog.setText("Doit être compris entre 1 et 1000");
				}
			} else {
				cryog.setText("Veuillez entrer un entier");
			}
			boutoncryo.setDisable(true);
			disabled = temps;
		});

		cryog.setOnMouseClicked( e-> {
			cryog.setText("");
		});

		vb.getChildren().addAll(toolBar,bpane,hb);
		fenetre.getChildren().addAll(info,cryo,cryog,boutoncryo);
		informations.getChildren().addAll(vb,fenetre);
		root.getChildren().add(informations);
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		stage.getIcons().add(new Image("File:ressources/soleil.png", 60, 60, true, false));
		tl.play();
		rate = tl.getRate();
	}

	private void run() {
		updateInfo();
		if(boutoncryo.isDisabled())if(disabled+40 == temps) boutoncryo.setDisable(false);
		temps++;
		seconde = temps % 60;
		minute = temps / 60 % 60;
		heure = temps / 60 / 60;
		gc1.clearRect(0, 0, sys.getRayon(), sys.getRayon());
		for(Objet o : listeObjet) {
			drawObject(o, gc1);
			for(Objet o2 : listeObjet) {
				if(o.getType().equals("Cercle") && o2.getName().equals(o.getCentre().getName())) ac.pos(o, temps);
				if(!o.getType().equals("Fixe") && !o.getType().equals("Vaisseau") && !o.getType().equals("Cercle") && o2.getType() != o.getType()){
					IntegrationE.eulerExplicite(o, o2, sys);
					gc2.setFill(Color.WHITE);
				}
				if(afficherTrajectoire) gc2.fillOval(o.getPos().getPosX()/2+sys.getRayon()/2-0.5,o.getPos().getPosY()/2+sys.getRayon()/2-0.5,1,1);
				if(o.getType().equals("Vaisseau") && !o2.getType().equals("Vaisseau")) {
					if(o.getAttraction() > 100) {
						boutoncryo.setDisable(true);
						cryog.setText("Attraction trop importante");
						double nb = o.getAttraction()/100.0;
						sys.setDt(sys.getDt()/nb);
						tl.setRate(tl.getRate()+nb);
					} else {
						if(disabled+40 < temps){
							if(cryog.getText().equals("Attraction trop importante")) cryog.setText("");
							boutoncryo.setDisable(false);
						}
						tl.setRate(rate);
						sys.setDt(oldDt);
					}
					IntegrationE.eulerExplicite(o, o2, sys);
				}
			}
			ac.pos(o,temps);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}
}