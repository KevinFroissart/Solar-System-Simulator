package view;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
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

/** Classe g�rant l'affichage principal du syst�me.
 * @author Froissart Kevin
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
	private Image imgVaisseau = new Image("File:ressources/vaisseau.png", 15, 15, true, false);
	final int tailleSoleil = 40;
	final int taillePlanete = 15;
	
	public Affichage(AffichageControl ac) {
		this.ac = ac;
		sl = ac.getModel();
		this.listeObjet = sl.objectInit();
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
		gc.setFill(Color.ORANGE);
		gc.drawImage(imgVaisseau, x-h/2, y-w/2);
	
	}

	public void createSun(double x, double y , GraphicsContext gc){
		gc.setFill(Color.RED);
		gc.fillOval(x-tailleSoleil/2, y-tailleSoleil/2, tailleSoleil, tailleSoleil);
	}

	public void createPlanete(double x, double y, GraphicsContext gc){
		int taille = 15;
		gc.setFill(Color.BLACK);
		gc.fillOval(x-taillePlanete/2, y-taillePlanete/2, taillePlanete, taillePlanete);
	}
	
	public void creerInfo() {
		info = new Information(listeObjet);
		for(Objet o : listeObjet) {
			o.addObserver(info);
		}
	}

	public void start(Stage stage) throws Exception {

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
		
		toolBar.getItems().addAll(open,reset,separator,bvs,bp,bs,separator2,binfo);
		
		Timeline tl = new Timeline(new KeyFrame(Duration.seconds(sys.getDt()/sys.getFa()/10), e -> run(gc))); //enlevez le /10
		tl.setCycleCount(Timeline.INDEFINITE);

		Group root = new Group();
		Scene scene = new Scene(root, sys.getRayon(), sys.getRayon() + 80);

		open.setOnAction( e-> {
			File file = ac.getFileExplorer(stage);
			sl.reader(file);
			listeObjet = ac.reset();
			for(Objet o : listeObjet) {
				o.addObserver(info);
			}
			info.setListe(listeObjet);
		});
		
		reset.setOnAction( e-> {
			listeObjet = ac.reset();
			for(Objet o : listeObjet) {
				o.addObserver(info);
			}
			info.setListe(listeObjet);
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
					if(e.getCode().equals(KeyCode.DOWN)) ac.down((Vaisseau)o, 0.005);
					if(e.getCode().equals(KeyCode.UP)) ac.up((Vaisseau)o, 0.005);
					if(e.getCode().equals(KeyCode.RIGHT)) ac.right((Vaisseau)o, 0.005);
					if(e.getCode().equals(KeyCode.LEFT)) ac.left((Vaisseau)o, 0.005);
				}
			}
		});

		scene.setOnKeyReleased( e-> {
			for(Objet o : listeObjet) {
				if(o.getType().equals("Vaisseau")) {
					if(e.getCode().equals(KeyCode.DOWN)) ac.down((Vaisseau)o, 0);
					if(e.getCode().equals(KeyCode.UP)) ac.up((Vaisseau)o, 0);
					if(e.getCode().equals(KeyCode.RIGHT)) ac.right((Vaisseau)o, 0);
					if(e.getCode().equals(KeyCode.LEFT)) ac.left((Vaisseau)o, 0);
				}
			}
		});
		vb.getChildren().addAll(toolBar,canvas);
		root.getChildren().add(vb);
		stage.setResizable(true);
		stage.setTitle("Solar System Simulator");
		stage.setScene(scene); 
		stage.centerOnScreen();
		stage.show();
		stage.getIcons().add(imgVaisseau);
		tl.play();
	}

	private void run(GraphicsContext gc) {
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
	}
}