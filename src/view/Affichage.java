package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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
import java.util.Random;


import controller.AffichageControl;
import javafx.scene.Group;

/** Classe gérant l'affichage principal du système.
 * @author Kevin, Maxence
 */

public class Affichage implements Observer{

	AffichageControl ac;
	SystemLoader sl;
	ArrayList<Objet> listeObjet;
	Systeme sys;
	Canvas canvas;
	GraphicsContext gc;
	Information info;
	boolean afficherVaisseau = true;
	boolean afficherPlanete = true;
	boolean afficherSoleil = true;
	final int tailleSoleil = 80;
	final int taillePlanete = 30;
	Image imgSoleil = new Image("File:ressources/soleil.png", tailleSoleil, tailleSoleil, true, false);
	Image imgVaisseau = new Image("File:ressources/vaisseau.png", 15, 15, true, false);
	private static ArrayList<Image> planetes;
	double vitesseSimu;
	HBox hb = new HBox();
	VBox vbVitesse = new VBox();
	VBox vbZoom = new VBox();
	Label labelVitesse = new Label("Vitesse de la simulation");
	Label labelZoom = new Label("Zoom");
	Slider vitesseSimuSLider = new Slider();
	Slider zoomSlider = new Slider();
	Timeline tl;

	public Affichage(AffichageControl ac) {
		this.ac = ac;
		sl = ac.getModel();
		listeObjet = sl.objectInit();
		sys = ac.getSysteme();
	}

	/**
	 * Constructeur de la vue du vaisseau, des planêtes, et de l'étoile. 
	 * Cette fonction instancie le vaisseau aux coordonn�es indiqu� en param�tre
	 * @param x indique la position x initiale du vaisseau � son instanciation
	 * @param y indique la position y initiale du vaisseau � son instanciation 
	 * @param gc GraphicsContext du canvas de la fenêtre
	 */
	public void createSpaceShip(double x, double y , GraphicsContext gc){
		double h = imgVaisseau.getHeight();
		double w = imgVaisseau.getWidth();
		gc.drawImage(imgVaisseau, x-h/2, y-w/2);
	}

	public void createSun(double x, double y , GraphicsContext gc){
		gc.drawImage(imgSoleil, x-tailleSoleil/2, y-tailleSoleil/2, tailleSoleil, tailleSoleil);
	}

	public void createPlanete(double x, double y, GraphicsContext gc){
		Random rand = new Random();
		gc.drawImage(planetes.get(1) ,x-taillePlanete/2, y-taillePlanete/2, taillePlanete, taillePlanete);
	}
	//On charge toutes les images du dossier des planetes dans l'arraylist globale du meme nom
	//TODO : rajouter try catch
	public void chargerImgPlanetes() {
		planetes = ac.loadImages();
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
		canvas = new Canvas(sys.getRayon(),sys.getRayon());
		gc = canvas.getGraphicsContext2D();
		chargerImgPlanetes();

		VBox vb = new VBox();
		ToolBar toolBar = new ToolBar();
		Button open = new Button("Ouvrir");
		Button reset = new Button("Reset");
		Separator separator = new Separator();
		Separator separator2 = new Separator();
		Button bvs = new Button("Vaisseau");
		Button bp = new Button("Planètes");
		Button bs = new Button("Soleil");
		Button binfo = new Button("Infos");
		
		
		vbVitesse.getChildren().addAll(labelVitesse,vitesseSimuSLider);
		vbZoom.getChildren().addAll(labelZoom,zoomSlider);
		
		vitesseSimuSLider.setMin(1);
		vitesseSimuSLider.setMax(sys.getDt()*1000);
		vitesseSimuSLider.setOrientation(Orientation.HORIZONTAL);
		vitesseSimuSLider.setMinWidth(sys.getRayon()/2);
		vitesseSimuSLider.setShowTickLabels(true);
		vitesseSimuSLider.setShowTickMarks(true);
		vitesseSimuSLider.setValue(sys.getFa());
		
		zoomSlider.setMin(-10);
		zoomSlider.setMax(10);
		zoomSlider.setOrientation(Orientation.HORIZONTAL);
		zoomSlider.setMinWidth(sys.getRayon()/2);
		zoomSlider.setShowTickLabels(true);
		zoomSlider.setShowTickMarks(true);
		
		hb.getChildren().addAll(vbVitesse, vbZoom);
		toolBar.getItems().addAll(open,reset,separator,bvs,bp,bs,separator2,binfo);

		//Unité de temps et de simulation du systeme
		tl = new Timeline(new KeyFrame(Duration.seconds(sys.getDt()/sys.getFa()), e -> run(gc)));
		tl.setCycleCount(Timeline.INDEFINITE);

		Group root = new Group();
		Scene scene = new Scene(root, sys.getRayon(), sys.getRayon() + 130);

		//Ici j'ajoute le background image à la VBox qui est root de notre systeme
		BackgroundImage back = new BackgroundImage(new Image("File:ressources/background.jpg",0, 0, true, false), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		vb.setBackground(new Background(back));
		
		vitesseSimuSLider.setOnMouseDragged( e-> {
			ac.setSlider(sys, vitesseSimuSLider.getValue());
		});

		open.setOnAction( e-> {
			try {
				File file = ac.getFileExplorer(stage);
				sl.reader(file);
				listeObjet = ac.resetObj();
				sys = ac.resetSys();
				vitesseSimuSLider.setValue(sys.getDt());
				for(Objet o : listeObjet) {
					o.addObserver(info);
				}
				sys.addObserver(this);
				info.setListe(listeObjet);
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
			if(afficherVaisseau) afficherVaisseau = false;
			else afficherVaisseau = true;
		});

		bp.setOnAction( e-> {
			if(afficherPlanete) afficherPlanete = false;
			else afficherPlanete = true;
		});

		bs.setOnAction( e-> {
			if(afficherSoleil) afficherSoleil = false;
			else afficherSoleil = true;		
		});

		binfo.setOnAction( e-> {
			try {
				creerInfo();
				info.start();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		scene.setOnKeyPressed( e-> {
			if(e.getCode().equals(KeyCode.I)) {
				try {
					creerInfo();
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
		
		vb.getChildren().addAll(toolBar,canvas,hb);
		root.getChildren().add(vb);
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
		stage.getIcons().add(imgSoleil);
		tl.play();
	}

	private void run(GraphicsContext gc) {
		tl.setRate((sys.getDt()/sys.getFa()));
		gc.clearRect(0, 0, sys.getRayon(), sys.getRayon());
		gc.strokeLine(sys.getRayon()/2, 0, sys.getRayon()/2, sys.getRayon());
		gc.strokeLine(0, sys.getRayon()/2, sys.getRayon(), sys.getRayon()/2);
		for(Objet o : listeObjet) {
			if(o.getType().matches("Fixe") && afficherSoleil) createSun(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY() + sys.getRayon()/2, gc);
			if(o.getType().matches("Simulé")) {
				for(Objet o2 : listeObjet) {
					if(o2.getType().equals("Fixe")) {
						ac.Force(o, o2);
					}
				}
				ac.pos(o);
				if(afficherPlanete) createPlanete(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			}
			if(o.getType().equals("Vaisseau")) {
				for(Objet o2 : listeObjet) {
					if(!o2.getType().equals("Vaisseau")) {
						ac.Force(o, o2);
					}
				}
				ac.pos(o);
				ac.bordure(o);
				if(afficherVaisseau) createSpaceShip(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		vitesseSimuSLider.setValue(sys.getDt());
		System.out.println(sys.getDt());
	}
}