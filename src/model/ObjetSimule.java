package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;


/** Classe définissant un objet dans le système, comme une planète.
 * @author //compléter Clément
 */
public class ObjetSimule extends Objet {
	
	private int idx;
	private List<Image> Album;
	
	public ObjetSimule(String name, String type, double masse, Vecteur pos, Vecteur vit, double attraction) {
		super(name, type, masse, pos,vit,0,attraction, 0, null, null);
		Random rand = new Random();
		Album = new ArrayList<>();
		loadAlbums();
		idx = rand.nextInt(Album.size());
	}

	public void loadAlbums(){
		try {
			Album.add(new Image("File:ressources/planetes/ceres.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/eris.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/jupiter.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/lune.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/mars.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/mercure.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/neptune.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/pluton.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/saturne.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/terre.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/uranus.png", 30,30,true,false));
			Album.add(new Image("File:ressources/planetes/venus.png", 30,30,true,false));
		}
		catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Image getImage() {
		return Album.get(idx);
	}
}