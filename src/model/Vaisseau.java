package model;

/** Classe définissant un vaisseau dans le système.
 * @author Lucas, Kevin
 */
public class Vaisseau extends Objet{
	
	public Vaisseau(String name, double masse, Vecteur pos,Vecteur vitesse,Vecteur acc) {
		super(name, masse, pos, vitesse, acc);
	}
}