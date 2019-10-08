package view;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import model.Objet;
import model.SystemLoader;
import model.Systeme;

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

	public Affichage(AffichageControl ac, Information info, ArrayList<Objet> listeObjet) {
		this.ac = ac;
		sl = ac.getModel();
		this.listeObjet = listeObjet;
		sys = ac.getSysteme();
		this.info = info;
	}

	/**
	 * Constructeur de la vue du vaisseau, des planêtes, et de l'étoile. 
	 * Cette fonction instancie le vaisseau aux coordonn�es indiqu� en param�tre
	 * @param x indique la position x initiale du vaisseau � son instanciation
	 * @param y indique la position y initiale du vaisseau � son instanciation 
	 * @param gc GraphicsContext du canvas de la fenêtre
	 */
	public void createSpaceShip(double x, double y , GraphicsContext gc){
		gc.setFill(Color.ORANGE);
		gc.fillRect(x, y, 7, 3);
	}

	public void createSun(double x, double y , GraphicsContext gc){
		gc.setFill(Color.RED);
		gc.fillOval(x, y, 40, 40);
	}

	public void createPlanete(double x, double y, GraphicsContext gc){
		gc.setFill(Color.BLACK);
		gc.fillOval(x, y, 15, 15);
	}

	public void start(Stage stage) throws Exception {

		canvas = new Canvas(sys.getRayon(),sys.getRayon());
		gc = canvas.getGraphicsContext2D();

		VBox vb = new VBox();
		ToolBar toolBar = new ToolBar();
		Button open = new Button("Ouvrir");
		Button reset = new Button("Reset");
		Separator separator = new Separator();
		Button bvs = new Button("Vaisseau");
		Button bp = new Button("Planètes");
		Button bs = new Button("Soleil");
		Button binfo = new Button("Infos");
		
		toolBar.getItems().addAll(open,reset,separator,bvs,bp,bs,binfo);
		
		Timeline tl = new Timeline(new KeyFrame(Duration.seconds(sys.getDt()/sys.getFa()/10), e -> run(gc))); //enlevez le /10
		tl.setCycleCount(Timeline.INDEFINITE);

		Group root = new Group();
		Scene scene = new Scene(root, 500, 580);

		open.setOnAction( e-> {
			//TODO: ouverture de fichier
		});
		
		reset.setOnAction( e-> {
			listeObjet = ac.reset();
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
					if(e.getCode().equals(KeyCode.DOWN)) ac.down(o, 0.005);
					if(e.getCode().equals(KeyCode.UP)) ac.up(o, 0.005);
					if(e.getCode().equals(KeyCode.RIGHT)) ac.right(o, 0.005);
					if(e.getCode().equals(KeyCode.LEFT)) ac.left(o, 0.005);
				}
			}
		});

		scene.setOnKeyReleased( e-> {
			for(Objet o : listeObjet) {
				if(o.getType().equals("Vaisseau")) {
					if(e.getCode().equals(KeyCode.DOWN)) ac.down(o, 0);
					if(e.getCode().equals(KeyCode.UP)) ac.up(o, 0);
					if(e.getCode().equals(KeyCode.RIGHT)) ac.right(o, 0);
					if(e.getCode().equals(KeyCode.LEFT)) ac.left(o, 0);
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
		tl.play();
	}

	private void run(GraphicsContext gc) {
		gc.clearRect(0, 0, sys.getRayon(), sys.getRayon());
		for(Objet o : listeObjet) {
			if(o.getType().matches("Fixe") && afficherSoleil) createSun(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY() + sys.getRayon()/2, gc);
			if(o.getType().matches("Simulé") && afficherPlanete) {
				createPlanete(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
				for(Objet o2 : listeObjet) {
					if(o2.getType().equals("Fixe")) {
						ac.Force(o, o2);
					}
				}
				ac.pos(o);
			}
			if(o.getType().equals("Vaisseau") && afficherVaisseau) {
				createSpaceShip(o.getPos().getPosX()/2 + sys.getRayon()/2, o.getPos().getPosY()/2 + sys.getRayon()/2, gc);
				for(Objet o2 : listeObjet) {
					if(!o2.getType().equals("Vaisseau")) {
						ac.Force(o, o2);
					}
				}
				ac.pos(o);
				ac.bordure(o);
			}
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
	}
}