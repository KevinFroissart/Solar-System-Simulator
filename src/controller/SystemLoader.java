package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.Objet;
import model.ObjetFixe;
import model.ObjetSimule;
import model.Vecteur;

/**
 * Cette classe permet de lire et charger le fichier texte qui contient la configuration pré-établie du système
 * à afficher.
 * @author Maxence, Kévin
 */
public class SystemLoader {

	private static ArrayList<String> lignes;

	public void reader() {

		lignes = new ArrayList<String>();

		try {
			File file = new File("ressources/system.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String read = "";
			while ((read = br.readLine()) != null) {
				read += ";";
				lignes.add(read);
			}
			br.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Systeme paramInit(int expected) {

		for(int i = 0; i < lignes.size(); i++) {

			int cpt = 0;
			int valid = 0;
			int lim = lignes.get(i).length();
			double g = 0;
			double dt = 0;
			double fa = 0;
			double rayon = 0;

			while(cpt < lim && valid == expected) {

				if(cpt+1 < lim && lignes.get(i).substring(cpt,cpt+1).equals("G")) {
					g = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("dt")) {
					dt = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("fa")) {
					fa = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
				}
				if(cpt+5 < lim && lignes.get(i).substring(cpt,cpt+5).equals("rayon")) {
					rayon = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				cpt++;
			}
			if(valid == expected && expected == 4) {
				return new System(g, dt, fa, rayon); 
				i = lim;				
			}
		}
		System.err.println("Arguments manquants ou incorrect");
		return null;
	}			
	
	public Objet objectInit(int expected, String type) {

		for(int i = 0; i < lignes.size(); i++) {

			int cpt = 0;
			int valid = 0;
			int lim = lignes.get(i).length();
			double masse = 0;
			double posx = 0;
			double posy = 0;
			double vitx = 0;
			double vity = 0;
			String nom = "";

			if(cpt+10 < lim) nom = nameReader(cpt, lim, lignes.get(i),':');

			while(cpt < lim && valid != expected) {

				if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("masse")) {
					masse = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("posx")) {
					posx = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("posy")) {
					posy = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("vity")) {
					vity = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("vitx")) {
					vitx = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+1 < lim && lignes.get(i).substring(cpt,cpt+1).equals("G")) {
					G = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("dt")) {
					dt = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("fa")) {
					fa = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				if(cpt+5 < lim && lignes.get(i).substring(cpt,cpt+5).equals("rayon")) {
					rayon = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
					valid++;
				}
				cpt++;
			}
			
			if(valid == expected && expected == 4 && type.equals("Fixe")) {
				Vecteur pos = new Vecteur(posx,posy);
				return new ObjetFixe(nom, masse, pos);
			}
			if(valid == expected && expected == 6 && type.equals("Simule")) {
				Vecteur pos = new Vecteur(posx,posy);
				Vecteur vit = new Vecteur(vitx,vity);
				return new ObjetSimule(nom, masse, pos, vit);
			}
			if(valid == expected && expected == 6 && type.equals("Ellipse")) {
			}
			if(valid == expected && expected == 6 && type.equals("Cercle")) {
			}
			if(valid == expected && expected == 6 && type.equals("Vesseau")) {
			}
			i = lim;
		}
		System.err.println("Arguments manquants ou incorrect");
		return null;
	}
	
	public static int occurenceReader(String nom) {
		int len = nom.length();
		int occ = 0;
		for(int i = 0; i < lignes.size(); i++) {
			int lim = lignes.get(i).length();
			int cpt = 0;
			while(cpt+len < lim) {
				if(lignes.get(i).substring(cpt,cpt+len).equals(nom)) {
					occ++;
				}
			}
		}		
		return occ;
	}

	public static String nameReader(int idx, int lim, String txt, char end) {
		int debut = 0;
		int fin = 0;
		for(int i = idx; i < lim; i++) {
			if(txt.charAt(i) == end || txt.charAt(i) == ':') {
				fin = i;
				break;
			}
		}
		return txt.substring(debut,fin);
	}

	public static String wordReader(int idx, int lim, String txt, char beg, char end) {
		int debut = 0;
		int fin = 0;
		for(int i = idx; i < lim; i++) {
			if(txt.charAt(i) == beg) debut = i+1;
			if(txt.charAt(i) == end || txt.charAt(i) == ';') {
				fin = i;
				break;
			}
		}
		return txt.substring(debut,fin);
	}
}
