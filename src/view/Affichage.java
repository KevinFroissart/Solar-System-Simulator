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
	Canvas canvas;
	GraphicsContext gc;
	Information info;
	boolean afficherVaisseau = true;
	boolean afficherPlanete = true;
	boolean afficherSoleil = true;
	final int tailleSoleil = 60;
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
	 * @param o donne l'objet à afficher
	 * @param gc GraphicsContext du canvas de la fenêtre
	 */
	public void createObject(Objet o, GraphicsContext gc) {
		double x = o.getPos().getPosX()/2 + sys.getRayon()/2;
		double y = o.getPos().getPosY()/2 + sys.getRayon()/2;
		if(o.getType().matches("Fixe") && afficherSoleil) gc.drawImage(imgSoleil, x-(o.getMasse()*2+5)/2, y-(o.getMasse()*2+5)/2, o.getMasse()*2+5, o.getMasse()*2+5);
		if(o.getType().matches("Simulé") && afficherPlanete) {
			ObjetSimule o2 = (ObjetSimule) o;
			gc.drawImage(o2.getImage(), x-(o.getMasse()*6+5)/2, y-(o.getMasse()*6+5)/2, o.getMasse()*6+5, o.getMasse()*6+5);
		}
		if(o.getType().matches("Vaisseau") && afficherVaisseau) gc.drawImage(imgVaisseau, x-imgVaisseau.getHeight()/2, y-imgVaisseau.getWidth()/2);
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
				info.start();
			} catch (Exception e1) {
				e1.printStackTrace();
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
		gc.setFill(Color.WHITE);
//		gc.strokeLine(sys.getRayon()/2, 0, sys.getRayon()/2, sys.getRayon());
//		gc.strokeLine(0, sys.getRayon()/2, sys.getRayon(), sys.getRayon()/2);
		for(Objet o : listeObjet) {
			createObject(o, gc);
			for(Objet o2 : listeObjet) {
				if(o.getType().matches("Simulé") && o2.getType().equals("Fixe")) {
					ac.Force(o, o2);
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
		vitesseSimuSLider.setValue(sys.getDt());
	}
}