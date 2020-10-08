package Carte;

import java.util.ArrayList;
import java.util.Collections;
import Executable.Main;
import Executable.Plateau;
import Executable.Main.color;
import Tuile.Obstacle;

public class Joueur {

	private int valueTortue;

	public ArrayList<Carte> deck;

	public ArrayList<Carte> hand;

	public ArrayList<Carte> programme;

	public ArrayList<Carte> defausse;

	public int nombreMurPierre=3;
	
	public int nombreMurGlace=2;

	/**
	 * Constructor
	 */
	public Joueur(int valueTortue) {
		this.valueTortue = valueTortue;
		initialiserDeck();
		hand = new ArrayList<>();
		programme = new ArrayList<>();
		defausse = new ArrayList<>();
		piocher(5);
		initialiserObstacle();
	}

	public void initialiserDeck() { // méthode pour initialiser le deck du joueur
		ArrayList<Carte> deckBase = new ArrayList<>();
		for (int i = 0; i < 18; i++) {
			deckBase.add(new Carte("bleu"));
		}
		for (int i = 0; i < 8; i++) {
			deckBase.add(new Carte("violette"));
			deckBase.add(new Carte("jaune"));
		}
		deckBase.add(new Carte("laser"));
		deckBase.add(new Carte("laser"));
		deckBase.add(new Carte("laser"));

		Collections.shuffle(deckBase);
		this.deck = (ArrayList<Carte>) deckBase.clone();
	}

	public void initialiserObstacle() {
		
	}

	public void piocher(int i) {
		for (int k = 0; k < i; k++) {
			this.hand.add(this.deck.remove(0));
		}
	}

	public void defausser(int positionMain) {
		
		this.defausse.add(hand.remove(positionMain-1));

		if (this.getSizeDeck() == 0) {
			this.deck = this.defausse;
			Collections.shuffle(this.deck);
		}

	}

	public void ajouterProgramme(int positionMain) {
		this.programme.add(getCarteHand(positionMain));
		this.hand.remove(positionMain); // rajouter
	}

	

	public Carte getCarteHand(int i) {
		return this.hand.get(i);
	}

	public Carte getCarteDeck(int i) {
		return this.deck.get(i);
	}


	/**
	 * Getter
	 * 
	 * @return
	 */
	public int getvalueTortue() {
		return this.valueTortue;
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public ArrayList<Carte> getDeck() {
		return this.deck;
	}
	
	public ArrayList<Carte> getProg() {
		return this.programme;
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public int getSizeDeck() {
		return this.deck.size();
	}
	public int getSizeProg() {
		return this.programme.size();
	}


	/**
	 * Getter
	 * 
	 * @return
	 */
	public ArrayList<Carte> getHand() {
		return this.hand;
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public int getSizeHand() {
		return hand.size();
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public int getSizeDefausse() {
		return this.defausse.size();
	}
	public int getnombreMurPierre() {
		return this.nombreMurPierre;
	}
	public int getnombreMurGlace() {
		return this.nombreMurGlace;
	}
	
	public void setnombreMurGlace(int x1) {
		this.nombreMurGlace=x1;
		
	}
	public void setnombreMurPierre(int y1) {
		this.nombreMurPierre=y1;
	}
	public void afficherHand() {
		System.out.println("Main :");
		for(int i=0;i<this.getHand().size();i++) {
			
			System.out.println((i+1) +"    "+this.getHand().get(i).getCouleurCarte());
			
		}
		}
		public void afficherProg() {
			System.out.println("Programme :");
			
			for(int i=0;i<this.getProg().size();i++) {
				
				System.out.println((i+1) +"    "+this.getProg().get(i).getCouleurCarte());
				
			}
	}



}
