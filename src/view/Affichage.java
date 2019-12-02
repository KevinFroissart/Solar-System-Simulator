package view;

import javafx.scene.Scene;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.input.KeyCode;
import model.Objet;
import model.SystemLoader;
import model.Systeme;
import model.Vaisseau;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.AffichageControl;
import javafx.scene.Group;
import model.ObjetSimule;

/** Classe gérant l'affichage principal du système.
 * @author Kevin, Maxence
 */

public class Affichage implements Observer{

	AffichageControl ac;
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	Canvas layer1;
	Canvas layer2;
	GraphicsContext gc1;
	GraphicsContext gc2;
	Information info;
	boolean afficherVaisseau = true;
	boolean afficherPlanete = true;
	boolean afficherSoleil = true;
	boolean afficherTrajectoire = false;
	double vitesseSimu;
	HBox hb = new HBox();
	VBox vbVitesse = new VBox();
	VBox vbZoom = new VBox();
	Label labelVitesse = new Label("Vitesse de la simulation");
	Label labelZoom = new Label("Zoom");
	Slider vitesseSimuSLider = new Slider();
	Slider zoomSlider = new Slider();
	Timeline tl;
	Pane bpane = new Pane();
	int temps = 0;
	Scene scene;
	
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
	public void createObject(Objet o, GraphicsContext gc) {
		double x = o.getPos().getPosX()/2* sys.getZoom() + sys.getRayon()/(2* sys.getZoom());
		double y = o.getPos().getPosY()/2* sys.getZoom() + sys.getRayon()/(2* sys.getZoom());
		if(o.getType().matches("Fixe") && afficherSoleil) gc.drawImage(o.getImage(), x-(o.getTaille()/2), y-(o.getTaille()/2), o.getTaille(), o.getTaille());
		if(o.getType().matches("Simulé") && afficherPlanete) {
			ObjetSimule o2 = (ObjetSimule) o;
			gc.drawImage(o2.getImage(), x-(o.getTaille()/2* sys.getZoom()), y-(o.getTaille()/2* sys.getZoom()), o.getTaille()* sys.getZoom(), o.getTaille()* sys.getZoom());
		}
		if(o.getType().matches("Vaisseau") && afficherVaisseau){
			gc.save();
			//gc.rotate(25);
			gc.drawImage(o.getImage(),x-((o.getTaille()/4+6)* sys.getZoom()), y-((o.getTaille()/4+6)* sys.getZoom()), (o.getTaille()/2+3)* sys.getZoom(), (o.getTaille()/2+3) * sys.getZoom());
			gc.restore();
		}
	}
	
	void zoom (double d, GraphicsContext gc , Objet o) {
		sys.setZoom(1.5);
		sys.setRayon(sys.getRayon()*sys.getZoom());
		
//		layer1.setScaleX(sc);
//		layer1.setScaleY(sc);
//		layer2.setScaleX(sc);
//		layer2.setScaleY(sc);
		
//		layer1.setHeight(sys.getRayon());
//		layer1.setWidth(sys.getRayon());
//		layer2.setHeight(sys.getRayon());
//		layer2.setWidth(sys.getRayon());
	}

	public void creerInfo() {
		info = new Information(listeObjet);
		for(Objet o : listeObjet) {
			o.addObserver(info);
		}
		sys.addObserver(this);
	}

	public void start(Stage stage) throws Exception {

		creerInfo();
		layer1 = new Canvas(sys.getRayon(),sys.getRayon());
		layer2 = new Canvas(sys.getRayon(),sys.getRayon());
		gc1 = layer1.getGraphicsContext2D();
		gc2 = layer2.getGraphicsContext2D();
		bpane.setMinSize(sys.getRayon(),sys.getRayon());
		bpane.getChildren().addAll(layer1,layer2);
		layer1.toFront();

		VBox vb = new VBox();
		ToolBar toolBar = new ToolBar();
		Button open = new Button("Ouvrir");
		Button reset = new Button("Reset");
		Separator separator = new Separator();
		Separator separator2 = new Separator();
		ToggleButton bvs = new ToggleButton("Vaisseau");
		ToggleButton bp = new ToggleButton("Planètes");
		ToggleButton bs = new ToggleButton("Soleil");
		Button binfo = new Button("Infos");
		ToggleButton blayer = new ToggleButton("Trajectoire");

		vbVitesse.getChildren().addAll(labelVitesse,vitesseSimuSLider);
		vbZoom.getChildren().addAll(labelZoom,zoomSlider);
		vbVitesse.setStyle("-fx-padding: 0 0 0 30;");
		vitesseSimuSLider.setMin(1);
		vitesseSimuSLider.setMax(4);
		vitesseSimuSLider.setOrientation(Orientation.HORIZONTAL);
		vitesseSimuSLider.setMinWidth(sys.getRayon()/2.5);
		vitesseSimuSLider.setShowTickLabels(true);
		vitesseSimuSLider.setShowTickMarks(true);
		vitesseSimuSLider.setValue(sys.getDt());

		zoomSlider.setMin(-1);
		zoomSlider.setMax(1);
		zoomSlider.setOrientation(Orientation.HORIZONTAL);
		zoomSlider.setMinWidth(sys.getRayon()/2.5);
		zoomSlider.setShowTickLabels(true);
		zoomSlider.setShowTickMarks(true);
		zoomSlider.setDisable(true);
		labelZoom.setStyle("-fx-font-weight:bold;");
		labelVitesse.setStyle("-fx-font-weight:bold;");

		hb.getChildren().addAll(vbVitesse, vbZoom);
		hb.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
				+ "-fx-border-width: 2;" + "-fx-border-insets: 10;"
				+ "-fx-border-radius: 10;" + "-fx-border-color: black;");
		toolBar.getItems().addAll(open,reset,separator,bvs,bp,bs,blayer,separator2,binfo);

		//Unité de temps et de simulation du systeme
		tl = new Timeline(new KeyFrame(Duration.seconds(sys.getDt()/sys.getFa()), e -> run()));
		tl.setCycleCount(Timeline.INDEFINITE);

		Group root = new Group();
		scene = new Scene(root,sys.getRayon(), sys.getRayon() + 130);
		//Ici j'ajoute le background image à la VBox qui est root de notre systeme
		BackgroundImage back = new BackgroundImage(new Image("File:ressources/background.jpg",0, 0, true, false), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		vb.setBackground(new Background(back));

		vitesseSimuSLider.setOnMouseDragged( e-> {
			ac.setSlider(sys, vitesseSimuSLider.getValue());
		});

		zoomSlider.setOnMouseDragged( e-> {
			ac.setZoom(zoomSlider.getValue());
			for(Objet o : listeObjet){
				if(zoomSlider.getValue() > 0) o.setTaille(o.getTaille()*(zoomSlider.getValue()+1));
				if(zoomSlider.getValue() < 0) o.setTaille(o.getTaille()/(zoomSlider.getValue()+1));
			}
		});

		open.setOnAction( e-> {
			try {
				File file = ac.getFileExplorer(stage);
				if(file != null) { 
					sl.reader(file);
					listeObjet = ac.resetObj();
					sys = ac.resetSys();
					vitesseSimuSLider.setValue(sys.getFa());
					for(Objet o : listeObjet) {
						o.addObserver(info);
					}
					sys.addObserver(this);
					info.setListe(listeObjet);
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
			listeObjet = ac.resetObj();
			sys = ac.resetSys();
			gc2.clearRect(0,0,sys.getRayon(),sys.getRayon());
			afficherTrajectoire = false;
			blayer.setSelected(false);
			bp.setSelected(false);
			bs.setSelected(false);
			bvs.setSelected(false);
			try {
				info.setListe(listeObjet);
			}catch(NullPointerException e1) {
				e1.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			for(Objet o : listeObjet) {
				o.addObserver(info);
			}		
			sys.addObserver(this);
			vitesseSimuSLider.setValue(sys.getDt());
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

		binfo.setOnAction( e-> {
			try {
				info.start();
			} catch (Exception e1) {
				e1.printStackTrace();
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

		scene.getRoot().requestFocus();
		scene.setOnKeyPressed( e-> {
			if(e.getCode().equals(KeyCode.I)) {
				try {
					info.start();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
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

		vb.getChildren().addAll(toolBar,bpane,hb);
		root.getChildren().add(vb);
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
		stage.getIcons().add(new Image("File:ressources/soleil.png", 60, 60, true, false));
		tl.play();
		
		

		
	}

	private void run() {
		tl.setRate(sys.getFa());
		scene.getRoot().requestFocus();
		gc1.clearRect(0, 0, sys.getRayon(), sys.getRayon());
		gc1.setFill(Color.WHITE);
		//gc1.strokeLine(sys.getRayon()/2, 0, sys.getRayon()/2, sys.getRayon());
		//gc1.strokeLine(0, sys.getRayon()/2, sys.getRayon(), sys.getRayon()/2);
		for(Objet o : listeObjet) {
			createObject(o, gc1);
			for(Objet o2 : listeObjet) {
				if(o.getType().matches("Simulé") && o2.getType().equals("Fixe")) {
					ac.Force(o, o2);
					gc2.setFill(Color.WHITE);
					if(afficherTrajectoire) gc2.fillOval(o.getPos().getPosX()/2+sys.getRayon()/2-0.5,o.getPos().getPosY()/2+sys.getRayon()/2-0.5,1,1);
				}
				if(o.getType().equals("Vaisseau") && !o2.getType().equals("Vaisseau")) {
					ac.Force(o, o2);
					
				}
			}
			ac.pos(o);
			
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		vitesseSimuSLider.setValue(sys.getFa());
		zoomSlider.setValue(sys.getZoom());
	}
}