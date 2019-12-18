package view;

import controller.AffichageControl;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
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
import model.*;

import java.io.File;
import java.util.*;

/** Classe gérant l'affichage principal du système.
 * @author Kevin, Maxence
 */

public class Affichage implements Observer {

	AffichageControl ac;
	SystemLoader sl;
	List<Objet> listeObjet;
	Systeme sys;
	Canvas layer1;
	Canvas layer2;
	GraphicsContext gc1;
	GraphicsContext gc2;
	Information info;
	boolean afficherVaisseau = true;
	boolean afficherPlanete = true;
	boolean afficherSoleil = true;
	boolean afficherTrajectoire;
	double vitesseSimu;
	HBox hb = new HBox();
	VBox vbZoom = new VBox();
	Label labelVitesse = new Label("Vitesse de la simulation");
	Label labelZoom = new Label("Zoom");
	Slider zoomSlider = new Slider();
	Timeline tl;
	Pane bpane = new Pane();
	Scene scene;

	public Affichage(AffichageControl ac) {
		this.ac = ac;
		sl = ac.getModel();
		listeObjet = sl.objectInit();
		sys = ac.getSysteme();
	}
	
	public List<Objet> getObjets(){
		return listeObjet;
	}
	
	public Systeme getSys() {
		return sys;
	}

	public AffichageControl getAc() {
		return ac;
	}
	/**
	 * Constructeur de la vue du vaisseau, des planêtes, et de l'étoile.
	 * @param o donne l'objet à afficher
	 * @param gc GraphicsContext du canvas de la fenêtre
	 */
	public void createObject(Objet o, GraphicsContext gc) {
		double x = o.getPos().getPosX()/2 + sys.getRayon()/2;
		double y = o.getPos().getPosY()/2 + sys.getRayon()/2;
		if(o.getType().matches("Fixe") && afficherSoleil) gc.drawImage(o.getImage(), x-(o.getTaille()/2), y-(o.getTaille()/2), o.getTaille(), o.getTaille());
		if(o.getType().matches("Simulé") && afficherPlanete) {
			ObjetSimule o2 = (ObjetSimule) o;
			gc.drawImage(o2.getImage(), x-(o.getTaille()/2), y-(o.getTaille()/2), o.getTaille(), o.getTaille());
		}
		if(o.getType().matches("Vaisseau") && afficherVaisseau){
			gc.save();
			//gc.rotate(25);
			gc.drawImage(o.getImage(),x-(o.getTaille()/4+6), y-(o.getTaille()/4+6), o.getTaille()/2+3, o.getTaille()/2+3);
			gc.restore();
		}
	}

	public void creerInfo() {
		info = new Information(listeObjet);
		for(Objet o : listeObjet) {
			o.addObserver(info);
		}
		sys.addObserver(this);
	}

	public void initImageObs() {
		for(Objet o : listeObjet) {
			o.addObserver(this);
			createObject(o, gc1);
			for(Objet o2 : listeObjet) {
				if(o.getType().matches("Simulé") && o2.getType().equals("Fixe")) {
					gc2.setFill(Color.WHITE);
					if(afficherTrajectoire) gc2.fillOval(o.getPos().getPosX()/2+sys.getRayon()/2-0.5,o.getPos().getPosY()/2+sys.getRayon()/2-0.5,1,1);
				}
			}
		}
	}
	public void start(Stage stage) throws Exception {
		System.out.println("salut");
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

		vbZoom.getChildren().addAll(labelZoom,zoomSlider);

		zoomSlider.setMin(-1);
		zoomSlider.setMax(1);
		zoomSlider.setOrientation(Orientation.HORIZONTAL);
		zoomSlider.setMinWidth(sys.getRayon()/2.5);
		zoomSlider.setShowTickLabels(true);
		zoomSlider.setShowTickMarks(true);
		zoomSlider.setDisable(true);
		labelZoom.setStyle("-fx-font-weight:bold;");
		labelVitesse.setStyle("-fx-font-weight:bold;");

		hb.getChildren().add(vbZoom);
		hb.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
				+ "-fx-border-width: 2;" + "-fx-border-insets: 10;"
				+ "-fx-border-radius: 10;" + "-fx-border-color: black;");
		toolBar.getItems().addAll(open,reset,separator,bvs,bp,bs,blayer,separator2,binfo);

		/*//Unité de temps et de simulation du systeme
		tl = new Timeline(new KeyFrame(Duration.seconds(sys.getDt()/sys.getFa()), e -> run()));
		tl.setCycleCount(Timeline.INDEFINITE);*/

		Group root = new Group();
		scene = new Scene(root);
		//Ici j'ajoute le background image à la VBox qui est root de notre systeme
		BackgroundImage back = new BackgroundImage(new Image("File:ressources/background.jpg",0, 0, true, false), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		vb.setBackground(new Background(back));

		zoomSlider.setOnMouseDragged( e-> {
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
					for(Objet o : listeObjet) {
						o.addObserver(info);
					}
					sys.addObserver(Affichage.this);
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
		//tl.play();
		initImageObs();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("test");
		gc1.clearRect(0, 0, sys.getRayon(), sys.getRayon());
		for(Objet o : listeObjet) {
			createObject(o, gc1);
			for(Objet o2 : listeObjet) {
				if(o.getType().matches("Simulé") && o2.getType().equals("Fixe")) {
					gc2.setFill(Color.WHITE);
					if(afficherTrajectoire) gc2.fillOval(o.getPos().getPosX()/2+sys.getRayon()/2-0.5,o.getPos().getPosY()/2+sys.getRayon()/2-0.5,1,1);
				}
			}
		}
	}
	//effacer la vue, parcourir les objets, afficher pour chaque nouvelle pos
}