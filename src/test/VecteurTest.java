package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Vecteur;

public class VecteurTest {

	@Test
	public void getPosXTest(){
		Vecteur v = new Vecteur (1.0,1.0);
		double res = v.getPosX();
		assertTrue(res == 1.0);

	}

	@Test
	public void getPosYTest(){
		Vecteur v = new Vecteur (1.0,1.0);
		double rs = v.getPosX();
		assertTrue(rs == 1.0);

	}

	@Test
	public void setPosXTest() {
		Vecteur v = new Vecteur (1.0,1.0);
		v.setPosX(2.0);
		double res = v.getPosX();
		assertTrue(res == 2.0);
	}


	@Test
	public void setPosYTest() {
		Vecteur v = new Vecteur (1.0,1.0);
		v.setPosY(2.0);
		double res = v.getPosY();
		assertTrue(res == 2.0);
	}

	@Test
	public void setToString() {
		Vecteur v = new Vecteur (1.0,1.0);
		String ts = "Vecteur [posX=1.0, posY=1.0]";
		assertTrue(v.toString().equals(ts));


	}
}
