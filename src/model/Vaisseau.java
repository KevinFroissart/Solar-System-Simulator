package model;

/** Classe héritant de Objet définissant un vaisseau dans le système.
 * @author Lucas, Kevin
 */
public class Vaisseau extends Objet{
	/**
	 * Constructeur qui instancie le vaisseau (héritage de objet) à l'aide des valeurs de chacun des attributs mis en paramètre
	 * @param name
	 * @param type
	 * @param masse
	 * @param pos
	 * @param vitesse
	 * @param acc
	 */
	public Vaisseau(String name, String type, double masse, Vecteur pos,Vecteur vitesse,Vecteur acc) {
		super(name, type, masse, pos, vitesse, acc);
	}
}