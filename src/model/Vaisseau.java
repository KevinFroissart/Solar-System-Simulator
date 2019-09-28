package model;

/** Classe définissant un vaisseau dans le système.
 * @author //compléter Lucas
 */
public class Vaisseau extends Objet{

	private static double masse=0.001;

	public Vaisseau(String name, Vecteur pos,Vecteur vitesse,Vecteur acc) {
		super(name, masse, pos, vitesse, acc);
	}
	
	
	

}
