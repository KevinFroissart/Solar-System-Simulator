package controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.ObjetFixe;
import model.Params;
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

	public void paramInit(int expected) {

		for(int i = 0; i < lignes.size(); i++) {

			int cpt = 0;
			int valid = 0;
			int lim = lignes.get(i).length();

			while(cpt < lim && valid == expected) {

				if(cpt+1 < lim && lignes.get(i).substring(cpt,cpt+1).equals("G")) {
					Params.setG(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
					valid++;
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("dt")) {
					Params.setDt(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
					valid++;
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("fa")) {
					Params.setFa(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
					valid++;
				}
				if(cpt+5 < lim && lignes.get(i).substring(cpt,cpt+5).equals("rayon")) {
					Params.setRayon(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
					valid++;
				}
				cpt++;
			}
			if(valid != expected) {
				System.err.println("Arguments manquants ou incorrect");
				i = lim;
			}
		}
	}

	public ObjetFixe FixedObjectInit(int expected) {

		for(int i = 0; i < lignes.size(); i++) {

			int cpt = 0;
			int valid = 0;
			int lim = lignes.get(i).length();
			double masse = 0;
			double posx = 0;
			double posy = 0;
			String nom = "";

			if(cpt+6 < lim && lignes.get(i).substring(cpt,cpt+6).equals("Soleil")) {
				while(cpt < lim && valid == expected) {
					
					if(cpt+6 < lim) nom = wordReader(cpt, lim, lignes.get(i),' ',':');

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
				}
				if(valid != expected) System.err.println("Arguments manquants ou incorrect");
				else {
					Vecteur pos = new Vecteur(posx,posy);
					return new ObjetFixe(nom, masse, pos);
				}
				i = lim;
			}
		}
		return null;
	}

	public void ObjectInit(int expected) {

		for(int i = 0; i < lignes.size(); i++) {

			int cpt = 0;
			int valid = 0;
			int lim = lignes.get(i).length();

			if(cpt+7 < lim && lignes.get(i).substring(cpt,cpt+7).equals("Planète") || lignes.get(i).substring(cpt,cpt+7).equals("Planéte")) {
				while(cpt < lim && valid == expected) {

					if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("masse")) {
						//Params.setG(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
						valid++;
					}
					if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("posx")) {
						//Params.setDt(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
						valid++;
					}
					if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("posy")) {
						//Params.setFa(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
						valid++;
					}
					if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("vity")) {
						//Params.setFa(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
						valid++;
					}
					if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("vity")) {
						//Params.setFa(Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ')));
						valid++;
					}
					cpt++;
				}
			}
			if(valid != expected) {
				System.err.println("Arguments manquants ou incorrect");
				i = lim;
			}
		}
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
