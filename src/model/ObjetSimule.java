package model;

/** Classe définissant un objet dans le système, comme une planète.
 * @author //compléter Lucas
 */
public class ObjetSimule extends Objet {

	public ObjetSimule(String name, String type, double masse, Vecteur pos, Vecteur vit, double attraction) {
		super(name, type, masse, pos,vit,0,attraction);
	}

}
