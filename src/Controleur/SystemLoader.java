package Controleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SystemLoader {

	static String system = "";
	static double G;
	static double dt;
	static double fa;
	static double rayon;

	public static void reader() {
		try {
			File file = new File("ressources/system.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String read = "";
			while ((read = br.readLine()) != null) system += read + ";";
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void paramInit() {
		String param = system;

		int cpt = 0;

		while(param.charAt(cpt) != ';') {
			if(system.charAt(cpt) == 'G') {
				G = 0.0001;
			}
			if(system.substring(cpt,cpt+2) == "fa") {
				System.out.println("oui2");

			}
			if(system.substring(cpt,cpt+2) == "dt") {
				System.out.println("oui3");

			}
			if(system.substring(cpt,cpt+5) == "rayon") {
				System.out.println("oui4");

			}
		}

	}

	public static void main(String[] args) {
		reader();
		System.out.println(system);
		paramInit();
		System.out.println(G);
	}
}
