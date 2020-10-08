package Executable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Carte.Carte;
import Carte.Joueur;
import Tuile.Pion;
import View.Fenetre;
import View.FenetreJeu;

public class Jeu{



	protected int nombreJoueur;
	protected ArrayList<Integer>  CarteAsuppr;

	private int caseAvecTortueBleu = 8;
	static int caseAvecTortueViolette = 9;
	static int caseAvecTortueVerte = 10;
	static int caseAvecTortueRouge = 11;

	public Jeu() {}

	public Jeu(int nombre) {
		boolean win= false;
		this.nombreJoueur = nombre;
		Plateau tableau = new Plateau(nombre);

		Joueur [] TourJoueur = new Joueur[nombre];
		Joueur Joueur1 = new Joueur(caseAvecTortueBleu);
		TourJoueur[0]=Joueur1;
		Joueur Joueur2 = new Joueur(caseAvecTortueViolette);
		TourJoueur[1]=Joueur2;
		if(nombre>=3) {

			Joueur Joueur3 = new Joueur(caseAvecTortueVerte);
			TourJoueur[2]=Joueur3;
		}

		if(nombre>=4) {
			Joueur Joueur4 = new Joueur(caseAvecTortueRouge);
			TourJoueur[3]=Joueur4;

		}
		int[] Gagner=new int[nombre];
		for(int i=0;i<Gagner.length;i++) {
			Gagner[i]=0;
		}

		int r =new Random().nextInt(nombre);

		int tour=Tour2Joueur(r,TourJoueur);

		while(win==false) {
			Scanner sc = new Scanner(System.in);
			tour=Tour2Joueur(tour,TourJoueur);
			if (Gagner[tour]==1) {
				System.out.println("Le joueur    "+(tour+1)+"    a gagné");

			}
			else {
				if(TourJoueur[tour].getSizeHand()<5) {

					TourJoueur[tour].piocher((5-TourJoueur[tour].getSizeHand()));

				}

				//action du joueur 
				tableau.afficherPlateau();
				System.out.println("Tour du joueur "+ (tour+1));
				System.out.println("Votre tortue est la"+"    "+TourJoueur[tour].getvalueTortue());
				System.out.println("Direction de votre tortue    "+JoueurToPion(TourJoueur[tour],tableau).getDirection());
				if((TourJoueur[tour].getnombreMurGlace()>0)||((TourJoueur[tour].getnombreMurPierre()>0))) {
					int saisie=0;
					do {
						System.out.println("Saisir votre action 1-Select Programme 2-Defausser 3-Poser un mur");
						int saisi = sc.nextInt();
						saisie=saisi;
					}while((saisie<1)&&(saisie>3));

					if(saisie==1) {//Select Programme

						TourJoueur[tour].afficherHand();
						do {
							System.out.println("Combien de carte voulez vous mettre dans votre programme ?");
							int saisi = sc.nextInt();
							saisie=saisi;
						}while((saisie<1)&&(saisie>5));

						CarteAsuppr= new ArrayList<>();
						TourJoueur[tour].afficherHand();
						TourJoueur[tour].afficherProg();
						for(int i=0;i<saisie; i++) {
							int sais=0;
							do {

								System.out.println("Quel carte voulez vous intégrer à votre programme ?");
								int saisi = sc.nextInt();
								sais=saisi;
							}while((saisie<1)&&(saisie>5));
							TourJoueur[tour].ajouterProgramme(sais-1);
							TourJoueur[tour].afficherHand();
							TourJoueur[tour].afficherProg();


						}



						do {
							System.out.println("Saisir votre action 1-Terminer tour 2-Defausser ");
							int saisi = sc.nextInt();
							saisie=saisi;
						}while((saisie<1)&&(saisie>2));

						if(saisie==2) {	
							int sais=0;

							do {
								System.out.println("Combien de carte voulez vous défausser ?");
								int saisi = sc.nextInt();
								saisie=saisi;
							}while((saisie<1)&&(saisie>5));
							for(int i=0;i<saisie; i++) {

								do {
									System.out.println("Quel carte voulez vous défausser ?");
									TourJoueur[tour].afficherHand();
									int saisi = sc.nextInt();
									sais=saisi;

								}while((sais<1)&&(sais>(TourJoueur[tour].getSizeHand()-1)));

								TourJoueur[tour].defausser(sais);

							}


						}

						System.out.println("Lancement du programme !");

						for(int i=0;i<TourJoueur[tour].getSizeProg();i++) {


							if(TourJoueur[tour].getProg().get(i).getCouleurCarte()=="bleu") {

								Gagner[tour]=tableau.Avancer(JoueurToPion(TourJoueur[tour],tableau).getDirection(),JoueurToPion(TourJoueur[tour],tableau).getValueTableau(), JoueurToPion(TourJoueur[tour],tableau).getx(), JoueurToPion(TourJoueur[tour],tableau).gety(),JoueurToPion(TourJoueur[tour],tableau).getXspawn(),JoueurToPion(TourJoueur[tour],tableau).getYspawn());



							}
							if(TourJoueur[tour].getProg().get(i).getCouleurCarte()=="jaune") {

								JoueurToPion(TourJoueur[tour],tableau).setDirection(tableau.changeDirection(JoueurToPion(TourJoueur[tour],tableau).getDirection(), false));



							}
							if(TourJoueur[tour].getProg().get(i).getCouleurCarte()=="violette") {

								JoueurToPion(TourJoueur[tour],tableau).setDirection(tableau.changeDirection(JoueurToPion(TourJoueur[tour],tableau).getDirection(), true));





							}
							if(TourJoueur[tour].getProg().get(i).getCouleurCarte()=="laser") {

								tableau.tireLaser(JoueurToPion(TourJoueur[tour],tableau).getDirection(),JoueurToPion(TourJoueur[tour],tableau).getValueTableau(), JoueurToPion(TourJoueur[tour],tableau).getx(), JoueurToPion(TourJoueur[tour],tableau).gety(),JoueurToPion(TourJoueur[tour],tableau).getXspawn(),JoueurToPion(TourJoueur[tour],tableau).getYspawn());



							}
							TourJoueur[tour].getProg().clear();
							tableau.afficherPlateau();


						}
					}
					if(saisie==2) { //defausser direct
						int sais=0;
						do {
							System.out.println("Combien de carte voulez vous défausser ?");
							int saisi = sc.nextInt();
							saisie=saisi;
						}while((saisie<1)&&(saisie>5));
						for(int i=0;i<saisie; i++) {

							do {
								System.out.println("Quel carte voulez vous défausser ?");
								int saisi = sc.nextInt();
								sais=saisi;
							}while((sais<1)&&(sais>5));
							TourJoueur[tour].defausser(sais);

						}


					}
					if(saisie==3) { //placer obstable

						if((TourJoueur[tour].getnombreMurGlace()<0)||(TourJoueur[tour].getnombreMurPierre()<0)) {
							System.out.println("Vous n'avez plus obstacle, choissisez une autre action !");
							ReJoueur(tour,TourJoueur);
							break;
						}

						do{
							System.out.println("Nombre de mur de glace"+"  "+TourJoueur[tour].getnombreMurGlace());
							System.out.println("Nombre de mur de pierre"+"  "+TourJoueur[tour].getnombreMurPierre());
							System.out.println("Saisir votre Mur 1-Glace 2-Pierre"); //choix du mur
							int saisi = sc.nextInt();
							saisie=saisi;
						}while(!((saisie==1)&&(TourJoueur[tour].getnombreMurGlace()>0)||(saisie==2)||(TourJoueur[tour].getnombreMurPierre()>0)));

						int saisiex=0;  //choix de la position
						int saisiey=0;
						do {

							System.out.println("Coordonné x ?");
							int saisix = sc.nextInt();
							saisiex=saisix;

							System.out.println("Coordonné y ?");
							int saisiy = sc.nextInt();
							saisiey=saisiy;


						}while(((saisiex<0)&&(saisiex>8))&&((saisiex<0)&&(saisiex>8))&&tableau.isFull(saisiex,saisiey));

						if(saisie==1) {
							tableau.setPlateau(7, saisiex, saisiey);

						}
						else {
							tableau.setPlateau(6, saisiex, saisiey);
						}
						System.out.println("Obstacle placé !");

						do {
							System.out.println("Combien de carte voulez vous défausser ?");
							int saisi = sc.nextInt();
							saisie=saisi;
						}while((saisie<1)&&(saisie>5));
						for(int i=0;i<saisie; i++) {

							do {
								System.out.println("Quel carte voulez vous défausser ?");
								int saisi = sc.nextInt();
								saisie=saisi;
							}while((saisie<1)&&(saisie>5));
							TourJoueur[tour].defausser(saisie);

						}


					}

				}

				int j=0;
				System.out.println(j);
				for(int i=0;i<Gagner.length;i++) {
					if(Gagner[i]>0) {
						j=j+1;
						System.out.println(j);
					}
				}
				System.out.println(j);
				if((nombre-j)==1) {
					win=true;
					System.out.println("Le jeu est fini");

				}

				System.out.println("     ");
				System.out.println("     ");
				System.out.println("     ");
				System.out.println("     ");

			}}}	








































	public int Tour2Joueur(int LastJoueur,Joueur TourJoueur[]) {  //Prend le joueur d'avant et sort le joueur d'après
		int JoueurT=0;
		for(int i=0  ; i<TourJoueur.length;i++){
			if(TourJoueur[LastJoueur]==TourJoueur[i]) {
				JoueurT=i;

			}  
		}
		JoueurT=JoueurT+1;
		if(JoueurT>(this.nombreJoueur-1)) {
			JoueurT=0;
		}


		return JoueurT;
	}

	public int ReJoueur(int JoueurAct,Joueur TourJoueur[]) {  //Prend le joueur d'avant et sort le joueur d'après
		int JoueurT=0;
		for(int i=0  ; i<TourJoueur.length;i++){
			if(TourJoueur[JoueurAct]==TourJoueur[i]) {
				JoueurT=i;

			}  
		}
		JoueurT=JoueurT-1;
		if(JoueurT<0) {
			JoueurT=this.nombreJoueur;
		}


		return JoueurT;
	}

	public Pion JoueurToPion(Joueur J,Plateau tableau) {
		switch(J.getvalueTortue()) {
		case 8:
			return tableau.getTortueBleu();
		case 10:
			return tableau.getTortueVerte();
		case 11:
			return tableau.getTortueRouge();
		case 9:
			return tableau.getTortueViolette();

		}
		return tableau.getTortueBleu();
	}





	/**
	 * Getter
	 * @return
	 */
	public int getNombreJoueur() {
		return nombreJoueur;
	}








}