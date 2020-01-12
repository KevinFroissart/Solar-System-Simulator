package model;

/** Classe définissant la position des objets dans l'espace du système
 * @author Cl�ment
 */
public class Vecteur {
	

	/**
	 * @param posX position x du vecteur
	 * @param posY position y du vecteur
	 * */
	private double posX;
	private double posY;
	
	/**
	 * Contructeur qui instancie un Vecteur à l'aide des valeurs de chacun des attributs mis en paramètre
	 * @param posX
	 * @param posY
	 */
	public Vecteur(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * Contructeur qui instancie un Vecteur à l'aide des valeurs de deux autre vecteurs
	 * @param objA
	 * @param objB
	 */
	public Vecteur(Vecteur objA, Vecteur objB){
		this.posX = objB.getPosX() - objA.getPosX();
		this.posY = objB.getPosY() - objA.getPosY();
	}
	
	/**
	 * Retourne la valeur de X du vecteur
	 * @return posX du vecteur
	 */
	public double getPosX() {
		return posX;
	}

	/**
	 * Modifie la valeur de x du vecteur
	 * @param posX
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * Retourne la valeur de y du vecteur
	 * @return posY du vecteur
	 */
	public double getPosY() {
		return posY;
	}
	
	/**
	 * Modifie la valeur de y du vecteur
	 * @param posY
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getNorme() { return Math.sqrt((posX*posX)+(posY*posY)); }

	/**
	 * Retourne un chaine de caractère donnant les informations posX et posY du vecteur
	 * @return String
	 */
	@Override
	public String toString() {
		return "Vecteur [posX=" + posX + ", posY=" + posY + "]";
	}
}