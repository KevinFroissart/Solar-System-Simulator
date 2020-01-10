package model;

import javafx.scene.image.Image;

/** Cette classe définit un objet fixe immobile dans le système : principalement le soleil.
 * @author Maxence
 */
public class ObjetFixe extends Objet {

    public ObjetFixe(String name, String type, double masse, Vecteur pos) {
        super(name, type, masse, pos, new Vecteur(0,0), 0,0, 0, null, null);
    }

    public Image getImage(){
        return new Image("File:ressources/soleil.png", 60, 60, true, false);
    }
}