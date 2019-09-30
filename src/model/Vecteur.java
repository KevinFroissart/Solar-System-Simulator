package model;

import javafx.beans.property.SimpleDoubleProperty;

/** Classe définissant la position des objets dans l'espace du système
 * @author Cl�ment
 */
public class Vecteur {
	
	private SimpleDoubleProperty posX = new SimpleDoubleProperty();
	private SimpleDoubleProperty posY = new SimpleDoubleProperty();
	
	public Vecteur(double posX, double posY) {
		
		this.posX.setValue(posX);
		this.posY.setValue(posY);
	}

	public SimpleDoubleProperty getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX.setValue(posX);
	}


	public SimpleDoubleProperty getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY.setValue(posY);;
	}

	@Override
	public String toString() {
		return "Vecteur [posX=" + posX + ", posY=" + posY + "]";
	}
}