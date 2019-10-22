package model;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Cette classe permet de lire et charger le fichier texte qui contient la configuration pré-établie du système
 * à simuler.
 * @author Maxence, Kévin
 */
public class SystemLoader {

	private static ArrayList<String> lignes;
	File read;

	public File getFile() {
		return read;
	}

	/** Méthode qui s'occupe de charger le fichier passé en paramètre de ligne de commande */
	public void reader(File toRead) {
		read = toRead;
		lignes = new ArrayList<String>();
		File config;
		BufferedReader br;
		try {
			if(!toRead.equals(null) && toRead.canRead()) {
				config = toRead;
			} else {
				System.out.println("Impossible de lire le fichier du chemin spécifié, lecture du fichier par défaut initialisée");
				config = new File("ressources/system.txt");
			}
			br = new BufferedReader(new FileReader(config));
			String read = "";
			while ((read = br.readLine()) != null) {
				read += ";";
				lignes.add(read);
			}
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	} 



	/** Méthode qui charge les paramètres du sytème lus dans la classe Système {@link model.Systeme#Systeme(double, double, double, double)}  */
	public Systeme paramInit(int expected) {

		for(int i = 0; i < lignes.size(); i++) {
			int cpt = 0;
			int valid = 0;
			int lim = lignes.get(i).length();
			double g = 0;
			double dt = 0;
			double fa = 0;
			double rayon = 0;

			while(cpt < lim && valid != expected) {

				try {
					if(cpt+1 < lim && lignes.get(i).substring(cpt,cpt+1).equals("G")) {
						g = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
						System.out.println(g);
						valid++;
					}
					if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("dt")) {
						dt = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
						System.out.println(g);
						valid++;
					}
					if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("fa")) {
						fa = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
						System.out.println(g);
						valid++;
					}
					if(cpt+5 < lim && lignes.get(i).substring(cpt,cpt+5).equals("rayon")) {
						rayon = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
						System.out.println(g);
						valid++;
					}
				}catch(NumberFormatException e) {
					System.err.println("Erreur de format !");
					e.printStackTrace();
					System.exit(1);
				}catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				cpt++;
			}
			if(valid == expected && expected == 4) {
				return new Systeme(g, dt, fa, rayon); 	
			}
		}
		System.err.println("Arguments manquants ou incorrect");
		System.exit(1);
		return null;
	}			

	/** Méthode qui ajoute les objets lus dans le fichier system.txt dans une ArrayList et la retourne */
	public ArrayList<Objet> objectInit() {		

		ArrayList<Objet> objectList = new ArrayList<Objet>();
		int nbVaisseau = 0;
		int expected = 0;
		int valid = 0;
		@SuppressWarnings("unused")
		boolean paramsPremiereLigne = false;

		for(int i = 0; i < lignes.size(); i++) {

			int cpt = 0;
			int lim = lignes.get(i).length();
			double masse = 0;
			double posx = 0;
			double posy = 0;
			double vitx = 0;
			double vity = 0;
			double pprincipal = 0;
			double pretro = 0;
			String nom = "";
			String type = "";

			if(cpt+20 < lim) {
				nom = wordReader(0, lim, lignes.get(i),'=',':',true);
				System.out.println(nom);
				type = wordReader(nom.length()+2, lim, lignes.get(i),'=',' ',true);
				switch(type) {
				case "Fixe" : expected += 3; break;
				case "Simulé" : expected += 5; break;
				case "Cercle" : expected += 5; break;
				case "Ellipse" : expected += 6; break;
				case "Vaisseau" : expected += 7; break;
				}
			}
			cpt = 0;
			while(cpt < lim && valid != expected) {
				if(1 < lim && lignes.get(i).charAt(0) != '#') {
					//TODO: tester si PARAMS est la première ligne
					try {
						if(cpt+5 < lim && lignes.get(i).substring(cpt,cpt+5).equals("masse")) {
							masse = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
							valid++;
						}
						if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("posx")) {
							posx = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
							valid++;
						}
						if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("posy")) {
							posy = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
							valid++;
						}
						if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("vity")) {
							vity = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
							valid++;
						}
						if(cpt+4 < lim && lignes.get(i).substring(cpt,cpt+4).equals("vitx")) {
							vitx = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
							valid++;
						}
						if(cpt+10 < lim && lignes.get(i).substring(cpt,cpt+10).equals("pprincipal")) {
							pprincipal = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
							valid++;
						}
						if(cpt+6 < lim && lignes.get(i).substring(cpt,cpt+6).equals("pretro")) {
							pretro = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' ',false));
							valid++;
						}
					}catch(NumberFormatException e) {
						System.err.println("Erreur de format !");
						e.printStackTrace();
						System.exit(1);
					}catch(Exception e) {
						e.printStackTrace();
						System.exit(1);
					}
				}
				cpt++;
			}

			if(valid == expected) {
				switch(type) {
				case "Fixe" : 
					Vecteur pos = new Vecteur(posx,posy);
					objectList.add(new ObjetFixe(nom, type, masse, pos));
					break;
				case "Simulé" : 
					pos = new Vecteur(posx,posy);
					Vecteur vit = new Vecteur(vitx,vity);
					objectList.add(new ObjetSimule(nom, type, masse, pos, vit, 0));
					break;
				case "Ellipse" : ; break;
				case "Cercle" : ; break;
				case "Vaisseau" : 
					pos = new Vecteur(posx,posy);
					vit = new Vecteur(vitx,vity);
					objectList.add(new Vaisseau(nom, type, masse, pos, vit, 0, 0, pprincipal, pretro));
					nbVaisseau++;
					break;
				}
			} else {
				System.err.println("Arguments incorrect ou manquants !");
				System.exit(1);
			}

			if(nbVaisseau > 1) {
				System.err.println("Le système ne support qu'un vaisseau");
				System.exit(1);
			}
		}
		return objectList;
	}

	/** Méthode qui retire les accents des mots lus dans le fichier system.txt pour rendre générique la lecture */
	public static String removeAccent(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
	}

	/** Méthode qui retourne les informations comprise entre les deux caractères passé en paramètre */
	public static String wordReader(int idx, int lim, String txt, char beg, char end, boolean name) {
		int debut = 0;
		if(name) debut = idx;
		int fin = 0;
		for(int i = idx; i < lim; i++) {
			if(txt.charAt(i) == beg && !name) debut = i+1;
			if(!name) {
				if(txt.charAt(i) == end || txt.charAt(i) == ';') {  
					fin = i;
					break;
				}
			} else if(name) {
				if(txt.charAt(i) == end || txt.charAt(i) == ':') {
					fin = i;
					break;
				}

			}
		}
		System.out.println("debut : " + debut + " ; fin : " + fin);
		return txt.substring(debut,fin);
	}
}