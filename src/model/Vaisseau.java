package model;

/** Classe définissant un vaisseau dans le système.
 * @author Lucas, Kevin
 */
public class Vaisseau extends Objet{
	
	public Vaisseau(String name, String type, double masse, Vecteur pos,Vecteur vitesse,Vecteur acc) {
		super(name, type, masse, pos, vitesse, acc);
	}
}