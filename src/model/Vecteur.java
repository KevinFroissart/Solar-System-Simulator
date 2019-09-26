package model;

/** Classe définissant la position des objets dans l'espace du système
 * @author Cl�ment
 */
public class Vecteur {
	
	private double posX;
	private double posY;
	
	
	public Vecteur(double posX, double posY) {
		
		this.posX = posX;
		this.posY = posY;
	}


	public double getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public double getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	@Override
	public String toString() {
		return "Vecteur [posX=" + posX + ", posY=" + posY + "]";
	}
	
	


}
