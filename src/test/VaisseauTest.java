package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Vaisseau;
import model.Vecteur;

public class VaisseauTest {
	Vaisseau Starship = new Vaisseau("Vaisseau", "Soyouz", 3.0, new Vecteur(0.0,0.0), new Vecteur(1.0,1.0),new Vecteur(2.0,2.0),20.0);
	
	/*
	if (Starship.getName()== "Vaisseau" && Starship.getType()== "Soyouz" && Starship.getPos().equals(new Vecteur(0.0,0.0)) && Starship.getMasse()== 3.0 &&
		Starship.getVitesse().equals(new Vecteur(1.0,1.0)))
	{

		assertTrue(false);
	}
	 */
	
	@Test
	public void VaisseauUpTest() {
		Starship.up(20.0);
		double res = Starship.getVitesse().getPosY() ;
		if (res != 21.0) {
			assertTrue(false);
		}
	}
	
	
	@Test
	public void VaisseauDownTest() {
		Starship.down(20.0);
		double res = Starship.getVitesse().getPosY() ;
		if (res != -19.0) {
			assertTrue(false);
		}
	}
	

	@Test
	public void VaisseauLeftTest() {
		Starship.left(20.0);
		double res = Starship.getVitesse().getPosX() ;
		if (res != 21.0) {
			assertTrue(false);
		}
	}
	
	@Test
	public void VaisseauRightTest() {
		Starship.right(20.0);
		double res = Starship.getVitesse().getPosX() ;
		if (res != -19.0) {
			assertTrue(false);
		}
	}
}
