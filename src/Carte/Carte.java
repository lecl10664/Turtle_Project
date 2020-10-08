package Carte;

import Executable.Jeu;

public class Carte extends Jeu {
	
	private String couleurCarte;

	
	
	/**
	 * Setter
	 * @param couleur
	 */
	
	public Carte(String couleur) {
		this.couleurCarte=couleur;
		}
	
	
	
	
/**
 * Getter
 * @return
 */
	public String getCouleurCarte() {
		return couleurCarte;
		
	}
	
	


}



