package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import Carte.Joueur;
import Executable.Plateau;
import Tuile.Pion;

public class FenetreJeu extends JFrame {

	// Affichage

	private JPanel panelControle = new JPanel(); // panel du haut (à qui le tour)
	private PlateauGrille panelGrille = new PlateauGrille(); // panel du bas ( grille de jeu )
	private JPanel panelDeck = new JPanel(); // panel pour le deck (a gauche)
	private JPanel panelHand = new JPanel(); // panel pour le hand (en bas)

	private JButton boutonDebuter = new JButton();
	private JTextField champTexte1 = new JTextField();
	private JTextField champTexte2 = new JTextField();
	private JTextField champTexte3 = new JTextField();
	private JTextField champTexte4 = new JTextField();
	private JButton boutonPoser = new JButton();
	private JButton boutonCompleterProg = new JButton();
	private JButton boutonExecuterProg = new JButton();
	private JButton boutonMurPierre = new JButton();
	private JButton boutonMurGlace = new JButton();
	private JButton boutonDeffauser = new JButton();
	private JButton boutonNotDeffauser = new JButton();
	private JButton boutonOK = new JButton();

	ImageIcon pierre = new ImageIcon("res/images/murPierre.jpg");
	ImageIcon glace = new ImageIcon("res/images/murGlace.png");
	ImageIcon carteBleu = new ImageIcon("res/images/cards/BlueCard.png");
	ImageIcon carteViolette = new ImageIcon("res/images/cards/PurpleCard.png");
	ImageIcon carteJaune = new ImageIcon("res/images/cards/YellowCard.png");
	ImageIcon carteLaser = new ImageIcon("res/images/cards/LaserCard.png");
	ImageIcon tortueBleue = new ImageIcon("res/images/tortueBleu.png");
	ImageIcon tortueViolette = new ImageIcon("res/images/tortueViolette.png");
	ImageIcon tortueRouge = new ImageIcon("res/images/tortueRouge.png");
	ImageIcon tortueVerte = new ImageIcon("res/images/tortueVerte.png");
	ImageIcon joyauxViolet = new ImageIcon("res/images/RUBY.png");
	ImageIcon handVide = new ImageIcon("res/images/handVide.PNG");

	private JLabel[][] tab;
	private GridLayout gridLayout1 = new GridLayout();

	private JLabel[][] tabHand;
	private GridLayout gridLayout2 = new GridLayout();



	// Jeu 

	protected int nombreJoueur;
	protected ArrayList<Integer>  CarteAsuppr;

	public boolean win= false;
	public Plateau tableau;
	public Joueur [] TourJoueur;
	public Joueur Joueur1;
	public Joueur Joueur2;
	public Joueur Joueur3;
	public Joueur Joueur4;
	public int[] Gagner;
	public int r;
	public int tour;


	private int caseAvecTortueBleu = 8;
	static int caseAvecTortueViolette = 9;
	static int caseAvecTortueVerte = 10;
	static int caseAvecTortueRouge = 11;





	public FenetreJeu(int nbJoueur) {
		try {
			jbInit(nbJoueur);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * initialise la surface de jeu. CrŽŽ tout les Žlements et initialise leur position leur couleur.. etc
	 */
	private void jbInit(int nombre) throws Exception {

		// Gui

		this.nombreJoueur = nombre;
		tableau = new Plateau(nombre);

		TourJoueur = new Joueur[nombre];
		Joueur1 = new Joueur(caseAvecTortueBleu);
		TourJoueur[0]=Joueur1;
		Joueur2 = new Joueur(caseAvecTortueViolette);
		TourJoueur[1]=Joueur2;
		if(nombre>=3) {

			Joueur3 = new Joueur(caseAvecTortueVerte);
			TourJoueur[2]=Joueur3;
		}

		if(nombre>=4) {
			Joueur4 = new Joueur(caseAvecTortueRouge);
			TourJoueur[3]=Joueur4;

		}

		Gagner = new int[nombre];
		for(int i=0;i<Gagner.length;i++) {
			Gagner[i]=0;
		}

		r =new Random().nextInt(nombre);
		
		tour=Tour2Joueur(r,TourJoueur);





		// Leo

		tab = new JLabel[8][8]; // création du tableau de JLabel
		tabHand = new JLabel [5][1];

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(784, 585));
		this.setTitle("Jeu Turtle");
		this.setLocationRelativeTo(null);
		this.pack();
		this.setExtendedState(Frame.MAXIMIZED_BOTH);	// la fenetre occupe tout l'ecran
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // arret du programme quand fermeture

		panelControle.setBounds(new Rectangle(565, 10, 120, 35));
		panelControle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panelControle.setLayout(null);

		panelGrille.setBounds(new Rectangle(65, 10, 496, 496));
		panelGrille.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panelGrille.setLayout(new BorderLayout());
		panelGrille.setLayout(gridLayout1);

		gridLayout1.setColumns(8);
		gridLayout1.setRows(8);

		panelDeck.setBounds(new Rectangle(700, 10, 650, 650));
		panelDeck.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panelDeck.setLayout(null);

		panelHand.setBounds(new Rectangle(40, 530, 550, 150));
		panelHand.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		panelHand.setLayout(null);
		panelHand.setLayout(gridLayout2);

		gridLayout2.setColumns(5);
		gridLayout2.setRows(1);


		panelDeck.add(champTexte1, null);
		panelDeck.add(champTexte2, null);
		panelDeck.add(champTexte3, null);
		panelDeck.add(champTexte4, null);
		panelControle.add(boutonDebuter, null);
		boutonDebuter.setBounds(new Rectangle(5, 5, 110, 25));
		boutonDebuter.setText("DEBUTER");
		champTexte1.setBounds(new Rectangle(10, 10, 630, 30));
		champTexte2.setBounds(new Rectangle(10, 40, 630, 30));
		champTexte3.setBounds(new Rectangle(10, 70, 630, 30));
		champTexte4.setBounds(new Rectangle(10, 100, 630, 30));

		panelDeck.add(boutonPoser, null);
		boutonPoser.setBounds(new Rectangle(250, 150, 150,25));
		boutonPoser.setText("POSER UN MUR");
		panelDeck.add(boutonCompleterProg, null);
		boutonCompleterProg.setBounds(new Rectangle(15, 150, 220,25));
		boutonCompleterProg.setText("COMPLETER LE PROGRAMME");
		panelDeck.add(boutonExecuterProg, null);
		boutonExecuterProg.setBounds(new Rectangle(420, 150, 220,25));
		boutonExecuterProg.setText("EXECUTER LE PROGRAMME");
		panelDeck.add(boutonDeffauser, null);
		boutonDeffauser.setBounds(new Rectangle(50, 450, 220,25));
		boutonDeffauser.setText("DEFFAUSER");
		panelDeck.add(boutonNotDeffauser, null);
		boutonNotDeffauser.setBounds(new Rectangle(350, 450, 220,25));
		boutonNotDeffauser.setText("NE PAS DEFFAUSER DE CARTE");
		panelDeck.add(boutonOK, null);
		boutonOK.setBounds(new Rectangle(50, 450, 220,25));
		boutonOK.setText("VALIDER");
		


		this.getContentPane().add(panelControle, null); // ajoute les 2 cadres
		this.getContentPane().add(panelDeck, null);
		this.getContentPane().add(panelHand, null);
		this.getContentPane().add(panelGrille, null);

		panelDeck.add(boutonMurPierre, null);
		panelDeck.add(boutonMurGlace, null);
		boutonMurPierre.setBounds(new Rectangle(150, 180, 110,150));
		boutonMurPierre.setIcon(pierre);
		boutonMurGlace.setBounds(new Rectangle(350, 180, 110,150));
		boutonMurGlace.setIcon(glace);
		boutonMurPierre.hide();
		boutonMurGlace.hide();
		boutonPoser.hide();
		boutonCompleterProg.hide();
		boutonExecuterProg.hide();
		boutonDeffauser.hide();
		boutonNotDeffauser.hide();
		boutonOK.hide();

		// ASSOCIATION BOUTON ACTION

		boutonDebuter.addActionListener(new BoutonDebuterListener());
		boutonMurPierre.addActionListener(new BoutonPierreListener());
		boutonMurGlace.addActionListener(new BoutonGlaceListener());
		boutonDeffauser.addActionListener(new DeffauserListener());
		boutonNotDeffauser.addActionListener(new NotDeffauserListener());
		boutonOK.addActionListener(new okListener());
		
	


		// creation du tableau de JLabel pour le plateau et du clique

		for (int ligne = 0; ligne < 8; ligne++) {
			for (int colonne = 0; colonne < 8; colonne++) {
				tab[colonne][ligne] = new JLabel();
				tab[colonne][ligne].setOpaque(false);
				panelGrille.add(tab[colonne][ligne]); // ajouter la tableau à la grille
				tab[colonne][ligne].setHorizontalAlignment(SwingConstants.CENTER); 

			}
		}



		// creation du tableau de JLabel pour la main 


		for (int colonne = 0; colonne < 5; colonne++) {
			tabHand[colonne][0] = new JLabel(); // creation du JLabel
			tabHand[colonne][0].setOpaque(false);
			panelHand.add(tabHand[colonne][0]); // ajouter au Pane
			tabHand[colonne][0].setHorizontalAlignment(SwingConstants.CENTER); 

		}





	}


	// void Guilhem (JEU)

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







	public void updatePlateau() {

		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (tableau.getPlateau()[j][i] == 7)	 {
					tab[i][j].setIcon(glace);
				}
				else if (tableau.getPlateau()[j][i] == 6) {
					tab[i][j].setIcon(pierre);
				}
				else if (tableau.getPlateau()[j][i] == 8) {
					tab[i][j].setIcon(tortueBleue);
				}
				else if (tableau.getPlateau()[j][i] == 9) {
					tab[i][j].setIcon(tortueViolette);
				}
				else if (tableau.getPlateau()[j][i] == 10) {
					tab[i][j].setIcon(tortueVerte);
				}
				else if (tableau.getPlateau()[j][i] == 11) {
					tab[i][j].setIcon(tortueRouge);
				}
				else if (tableau.getPlateau()[j][i] == 2 || tableau.getPlateau()[j][i] == 3 || tableau.getPlateau()[j][i] == 4 || tableau.getPlateau()[j][i] == 5) {
					tab[i][j].setIcon(joyauxViolet);
				}
				else if (tableau.getPlateau()[j][i] == 0) {
					tab[i][j].setIcon(null);
				}
			}
		}
	}

	public void updateHand() {
		for (int i=0; i<TourJoueur[tour].getHand().size(); i++) {
			if (TourJoueur[tour].getHand().get(i).getCouleurCarte().equals("violette"))	 {
				tabHand[i][0].setIcon(carteViolette);
			}
			else if (TourJoueur[tour].getHand().get(i).getCouleurCarte().equals("jaune")) {
				tabHand[i][0].setIcon(carteJaune);
			}
			else if (TourJoueur[tour].getHand().get(i).getCouleurCarte().equals("bleu")) {
				tabHand[i][0].setIcon(carteBleu);
			}
			else if (TourJoueur[tour].getHand().get(i).getCouleurCarte().equals("laser")) {
				tabHand[i][0].setIcon(carteLaser);
			}
			else {
				tabHand[i][0].setIcon(handVide);
			}
		}
	}

	public void razHand() {
		for (int i=0; i<5; i++) {
			tabHand[i][0].setIcon(handVide);
		}
	}

	public void razPlateau() {
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (tableau.getPlateau()[i][j] == 0) {
					tab[i][j].repaint();
				}
				
				
			}
		}
	}







	class Menu implements ActionListener {


		public void actionPerformed(ActionEvent e) {


			if (win==true) {
				champTexte1.setText("FIN DE LA PARTIE");
				
				
			}
			
			if (Gagner[tour]==1) {
				champTexte1.setText("Le joueur    "+(tour+1)+"    a gagné");
				champTexte1.setText("C'est le tour du joueur " + (tour+1)); // tour joueur 
				champTexte2.setText("Votre tortue est la"+"    "+ JoueurToPion(TourJoueur[tour],tableau).getcouleur());
				champTexte3.setText("Direction de votre tortue : "+JoueurToPion(TourJoueur[tour],tableau).getDirection() +"    Nombre de carte dans votre programme : " + TourJoueur[tour].getSizeProg());
				champTexte4.setText("Quelle action voulez-vous faire ?");
				updatePlateau();
				updateHand();
			}
			else {
				
				

				if((TourJoueur[tour].getnombreMurGlace()==0) && ((TourJoueur[tour].getnombreMurPierre()==0))) {
					boutonPoser.hide();
				}


				if(TourJoueur[tour].getSizeHand()<5) {

					TourJoueur[tour].piocher((5-TourJoueur[tour].getSizeHand()));

				}

				// si on clique sur le bouton POSER UN MUR
				if  (e.getSource() == boutonPoser) {
					boutonMurPierre.show();
					boutonMurGlace.show();
					if (TourJoueur[tour].getnombreMurGlace()<1 ) {
						boutonMurGlace.hide();
					}
					if (TourJoueur[tour].getnombreMurPierre()<1)
						boutonMurPierre.hide();
					champTexte4.setText("Nombre de mur de glace : " + TourJoueur[tour].getnombreMurGlace() + "    Nombre de mur de pierre : "+TourJoueur[tour].getnombreMurPierre());

					boutonMurPierre.setEnabled(true);
					boutonMurGlace.setEnabled(true);
					
					boutonPoser.setEnabled(false);
					boutonCompleterProg.setEnabled(false);
					boutonExecuterProg.setEnabled(false);	
				}

				// si on clique sur le bouton COMPLETER LE PROGRAMME
				if (e.getSource() == boutonCompleterProg) {
					updateHand();
					updatePlateau();
					champTexte4.setText("Choissisez la/les cartes que vous voulez ajouter à votre programme");	
					boutonPoser.setEnabled(false);
					boutonCompleterProg.setEnabled(false);
					boutonExecuterProg.setEnabled(false);
					for (int j = 0; j < 5; j++) {
						tabHand[j][0].addMouseListener(new ChoisirHandListener());
					}
					TourJoueur[tour].afficherHand();
					TourJoueur[tour].afficherProg();

					
				}

				// si on clique sur le bouton EXECUTER LE PROGRAMME
				if (e.getSource() == boutonExecuterProg) {
					champTexte2.setText("Execution du programme en cours... ");
					boutonPoser.setEnabled(false);
					boutonCompleterProg.setEnabled(false);
					boutonExecuterProg.setEnabled(false);
					for(int i=0;i<TourJoueur[tour].getSizeProg();i++) {


						if(TourJoueur[tour].getProg().get(i).getCouleurCarte().equals("bleu")) {

							Gagner[tour]=tableau.Avancer(JoueurToPion(TourJoueur[tour],tableau).getDirection(),JoueurToPion(TourJoueur[tour],tableau).getValueTableau(), JoueurToPion(TourJoueur[tour],tableau).getx(), JoueurToPion(TourJoueur[tour],tableau).gety(),JoueurToPion(TourJoueur[tour],tableau).getXspawn(),JoueurToPion(TourJoueur[tour],tableau).getYspawn());


						}
						if(TourJoueur[tour].getProg().get(i).getCouleurCarte().equals("jaune")) {

							JoueurToPion(TourJoueur[tour],tableau).setDirection(tableau.changeDirection(JoueurToPion(TourJoueur[tour],tableau).getDirection(), false));



						}
						if(TourJoueur[tour].getProg().get(i).getCouleurCarte().equals("violette")) {

							JoueurToPion(TourJoueur[tour],tableau).setDirection(tableau.changeDirection(JoueurToPion(TourJoueur[tour],tableau).getDirection(), true));





						}
						if(TourJoueur[tour].getProg().get(i).getCouleurCarte().equals("laser")) {

							tableau.tireLaser(JoueurToPion(TourJoueur[tour],tableau).getDirection(),JoueurToPion(TourJoueur[tour],tableau).getValueTableau(), JoueurToPion(TourJoueur[tour],tableau).getx(), JoueurToPion(TourJoueur[tour],tableau).gety(),JoueurToPion(TourJoueur[tour],tableau).getXspawn(),JoueurToPion(TourJoueur[tour],tableau).getYspawn());



						}
						//razPlateau();
						updatePlateau();
						
					}
					TourJoueur[tour].getProg().clear();
					updatePlateau();
					boutonDeffauser.show();
					boutonNotDeffauser.show();


				}
			}
		}


	}




	class BoutonDebuterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// si on clique sur le bouton débuter
			if (e.getSource() == boutonDebuter) {
				//initialise le champ texte avec les tour de jeu, appelle la method debuter 
				boutonDebuter.setEnabled(false);
				
				
				boutonPoser.show();
				boutonCompleterProg.show();
				boutonExecuterProg.show();
				Menu menu = new Menu();
				boutonPoser.addActionListener(menu);
				boutonCompleterProg.addActionListener(menu);
				boutonExecuterProg.addActionListener(menu);
				updatePlateau();
				updateHand();
				
				// info du joueur
				champTexte1.setText("C'est le tour du joueur " + (tour+1)); // tour joueur 
				champTexte2.setText("Votre tortue est la"+"    "+ JoueurToPion(TourJoueur[tour],tableau).getcouleur());
				champTexte3.setText("Direction de votre tortue : "+JoueurToPion(TourJoueur[tour],tableau).getDirection() +"    Nombre de carte dans votre programme : " + TourJoueur[tour].getSizeProg());
				champTexte4.setText("Quelle action voulez-vous faire ?");
				System.out.println(tour);
			}

		}
	}



	class BoutonPierreListener implements ActionListener {

		public int ligneClic;
		public int colonneClic;

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == boutonMurPierre) {	// si on appuie sur le mur pierre
				boutonMurGlace.hide();
				boutonMurPierre.hide();
				champTexte4.setText("Vous avez choisi de placer UN mur de pierre");
				for (int i = 0; i < 8; i++) {		
					for (int j = 0; j < 8; j++) {
						tab[j][i].addMouseListener(new PlacerMurPierreListener());
						
					}
				}
				
			}
		}
	}


	class BoutonGlaceListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == boutonMurGlace) {		// si on appuie sur le mur glace
				boutonMurPierre.hide();
				boutonMurGlace.hide();
				champTexte4.setText("Vous avez choisi de placer UN mur de glace");

				for (int i = 0; i < 8; i++) {		
					for (int j = 0; j < 8; j++) {
						tab[j][i].addMouseListener(new PlacerMurGlaceListener());
					}
				}
			}
		}
	}


	class PlacerMurPierreListener extends MouseAdapter {

		private int ligneClic;
		private int colonneClic;

		public void mouseClicked(MouseEvent e) {	
			for (int i = 0; i < 8; i++) {		//	on determine sur quelle Jlabel (case) on a cliqué
				for (int j = 0; j < 8; j++) {
					if (e.getSource() == tab[j][i]) {
						ligneClic = i;
						colonneClic = j;
					}
				}
			}
			if (tableau.getPlateau()[ligneClic][colonneClic] == 0) {
				tab[colonneClic][ligneClic].setIcon(pierre);
				tableau.setPlateau(6, ligneClic, colonneClic);
				champTexte4.setText("Votre mur de Pierre est placé ! Vous pouvez maintenant choisir de deffausser ou non");
				TourJoueur[tour].setnombreMurPierre(TourJoueur[tour].getnombreMurPierre() -1);
				
				razPlateau();
				updatePlateau();
			}
			


			// une fois l'action terminé, apparition du bouton defausser
			boutonDeffauser.show();
			boutonNotDeffauser.show();
			
			/*boutonDeffauser.addActionListener(new DeffauserListener());
			boutonNotDeffauser.addActionListener(new NotDeffauserListener());*/

		}
	}

	class PlacerMurGlaceListener extends MouseAdapter {

		private int ligneClic;
		private int colonneClic;

		public void mouseClicked(MouseEvent e) {	
			for (int i = 0; i < 8; i++) {		//	on determine sur quelle Jlabel (case) on a cliqué
				for (int j = 0; j < 8; j++) {
					if (e.getSource() == tab[j][i]) {
						ligneClic = i;
						colonneClic = j;
					}
				}
			}
			if (tableau.getPlateau()[ligneClic][colonneClic] == 0) {
				tab[colonneClic][ligneClic].setIcon(glace);
				tableau.setPlateau(7, ligneClic, colonneClic);
				champTexte4.setText("Votre mur de Glace est placé ! Vous pouvez maintenant choisir de deffausser ou non");
				TourJoueur[tour].setnombreMurGlace(TourJoueur[tour].getnombreMurGlace() -1);

			}


			// une fois l'action terminé, apparition du bouton defausser
			razHand();
			updateHand();
			updatePlateau();
			boutonDeffauser.show();
			boutonNotDeffauser.show();
			
			/*boutonDeffauser.addActionListener(new DeffauserListener());
			boutonNotDeffauser.addActionListener(new NotDeffauserListener());*/


		}
	}



	class ChoisirHandListener extends MouseAdapter {

		public int colonneClic;

		@Override
		public void mouseClicked(MouseEvent e) {	
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == tabHand[j][0]) {
					colonneClic = j;

				}
			}
			tabHand[colonneClic][0].setIcon(handVide);
			TourJoueur[tour].ajouterProgramme(colonneClic);
			TourJoueur[tour].afficherHand();
			TourJoueur[tour].afficherProg();
			razHand();
			updateHand();




			// une fois l'action terminé, apparition du bouton defausser
			boutonDeffauser.show();
			boutonNotDeffauser.show();
			/*boutonDeffauser.addActionListener(new DeffauserListener());
			boutonNotDeffauser.addActionListener(new NotDeffauserListener());*/
		}	

	}




	class DeffauserListener implements ActionListener {	// si on clique sur le bouton DEFFAUSER
		
		public int colonneClic;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == boutonDeffauser) {
				champTexte4.setText("Quelle carte(s) voulez-vous deffauser ?");
				boutonDeffauser.hide();
				boutonNotDeffauser.hide();
				razHand();
				updateHand();
				TourJoueur[tour].afficherHand();
			}
				
			if (e.getSource() instanceof JLabel) {
				for (int j = 0; j < 5; j++) {
					if (e.getSource() == tabHand[j][0]) {
						colonneClic = j;

					}
				}
				tabHand[colonneClic][0].setIcon(handVide);
				TourJoueur[tour].defausser(colonneClic);
				TourJoueur[tour].afficherHand();
				razHand();
				updateHand();
				
				
				boutonOK.show();
				boutonDeffauser.hide();
				boutonNotDeffauser.hide();
			}	

				/*for (int j = 0; j < 5; j++) {
					tabHand[j][0].addMouseListener(new ChoisirDeffauseListener());
				}*/
			}
		}


	class ChoisirDeffauseListener extends MouseAdapter {

		public int colonneClic;

		@Override
		public void mouseClicked(MouseEvent e) {	
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == tabHand[j][0]) {
					colonneClic = j;

				}
			}
			tabHand[colonneClic][0].setIcon(handVide);
			TourJoueur[tour].defausser(colonneClic);
			TourJoueur[tour].afficherHand();
			razHand();
			updateHand();
			
			
			boutonOK.show();
			boutonDeffauser.hide();
			boutonNotDeffauser.hide();
		}	

	}

	class NotDeffauserListener implements ActionListener {	// si on clique sur le bouton NE PAS DEFFAUSER

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == boutonNotDeffauser) {
				/* desactiver placerMur
				for (int i = 0; i < 8; i++) {		
					for (int j = 0; j < 8; j++) {
						tab[j][i].disable();
						
					}
				}*/
				
				
				
				champTexte2.setText("Vous ne voulez pas deffauser de carte");
				boutonNotDeffauser.hide();
				boutonDeffauser.hide();
				boutonPoser.setEnabled(true);
				boutonCompleterProg.setEnabled(true);
				boutonExecuterProg.setEnabled(true);
				
				tour=Tour2Joueur(tour,TourJoueur);
				// info du joueur
				champTexte1.setText("C'est le tour du joueur " + (tour+1)); // tour joueur 
				champTexte2.setText("Votre tortue est la"+"    "+ JoueurToPion(TourJoueur[tour],tableau).getcouleur());
				champTexte3.setText("Direction de votre tortue : "+JoueurToPion(TourJoueur[tour],tableau).getDirection() +"    Nombre de carte dans votre programme : " + TourJoueur[tour].getSizeProg());
				champTexte4.setText("Quelle action voulez-vous faire ?");
				updatePlateau();
				updateHand();
				System.out.println("");
				tableau.afficherPlateau();


			}
		}
	}
	
	class okListener implements ActionListener {	// si on clique sur le bouton NE PAS DEFFAUSER

		@Override
		public void actionPerformed(ActionEvent e) {
			boutonOK.hide();
			boutonPoser.setEnabled(true);
			boutonCompleterProg.setEnabled(true);
			boutonExecuterProg.setEnabled(true);
			
			tour=Tour2Joueur(tour,TourJoueur);
			// info du joueur
			champTexte1.setText("C'est le tour du joueur " + (tour+1)); // tour joueur 
			champTexte2.setText("Votre tortue est la"+"    "+ JoueurToPion(TourJoueur[tour],tableau).getcouleur());
			champTexte3.setText("Direction de votre tortue : "+JoueurToPion(TourJoueur[tour],tableau).getDirection() +"    Nombre de carte dans votre programme : " + TourJoueur[tour].getSizeProg());
			champTexte4.setText("Quelle action voulez-vous faire ?");
			updatePlateau();
			updateHand();
			
			
			
			
		}
	}





}



