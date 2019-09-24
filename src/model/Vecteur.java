package model;

/** Classe définissant la position des objets dans l'espace du système
 * @author Cl�ment
 */
public class Vecteur {
	
	private int posX;
	private int posY;
	
	
	public Vecteur(int posX, int posY) {
		
		this.posX = posX;
		this.posY = posY;
	}


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
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
