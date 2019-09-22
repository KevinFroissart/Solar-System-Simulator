package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SystemLoader {

	static ArrayList<String> lignes;
	static double G;
	static double dt;
	static double fa;
	static double rayon;

	public static void reader() {

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

	public static void paramInit() {
		
		for(int i = 0; i < lignes.size(); i++) {
		
			int cpt = 0;
			int lim = lignes.get(i).length();
			
			while(cpt < lim) {
				
				if(cpt+1 < lim && lignes.get(i).substring(cpt,cpt+1).equals("G")) {
					G = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("dt")) {
					dt = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
				}
				if(cpt+2 < lim && lignes.get(i).substring(cpt,cpt+2).equals("fa")) {
					fa = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
				}
				if(cpt+5 < lim && lignes.get(i).substring(cpt,cpt+5).equals("rayon")) {
					rayon = Double.parseDouble(wordReader(cpt, lim, lignes.get(i),'=',' '));
				}
				cpt++;
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
	
	public static void main(String[] args) {
		reader();
		paramInit();
		System.out.println(G);
		System.out.println(dt);
		System.out.println(fa);
		System.out.println(rayon);
	}
}
