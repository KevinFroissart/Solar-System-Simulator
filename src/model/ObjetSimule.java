package model;
import java.util.ArrayList;
import java.util.Random;

import controller.*;

import javafx.scene.image.Image;

/** Classe définissant un objet dans le système, comme une planète.
 * @author //compléter Clément
 */
public class ObjetSimule extends Objet {
	
	
	public ObjetSimule(String name, String type, double masse, Vecteur pos, Vecteur vit, double attraction) {
		super(name, type, masse, pos,vit,0,attraction);
	}
	
	

}
