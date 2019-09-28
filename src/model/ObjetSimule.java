package model;

/** Classe définissant un objet dans le système, comme une planète.
 * @author //compléter Lucas
 */
public class ObjetSimule extends Objet {

	public ObjetSimule(String name, double masse, Vecteur pos, Vecteur vit) {
		super(name, masse, pos,vit,new Vecteur(0,0));
	}

}
