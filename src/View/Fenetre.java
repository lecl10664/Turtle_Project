package View;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Executable.Jeu;


public class Fenetre extends JFrame implements  ActionListener{
	private Accueil p1 = new Accueil();
	JButton bouton2 = new JButton("2 joueurs");
	JButton bouton3 = new JButton("3 joueurs");
	JButton bouton4 = new JButton("4 joueurs");
	JLabel phrase = new JLabel("Choisissez le nombre de joueurs :");
	
	public Fenetre() {
		

			this.setSize(620, 802);
			this.setLocationRelativeTo(null);
			
			
			//On définit le layout en lui indiquant qu'il travaillera en ligne
			
		    p1.add(phrase);
		    this.add(p1);
	
			p1.add(bouton2);
			p1.add(bouton3);
			p1.add(bouton4);
			this.add(p1);
			
			JButton quitter = new JButton("quitter");
			p1.add(quitter);
			this.add(p1);
			
			
			bouton2.addActionListener(this);
			bouton3.addActionListener(this);
			bouton4.addActionListener(this);
			
			
			this.setContentPane(p1);
			this.setVisible(true);
	}
	
			
			

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == bouton2) {
				//nbJoueur = 2;
				this.dispose();
				FenetreJeu jeu = new FenetreJeu(2);
				jeu.setVisible(true);
				
				}

			
			if(e.getSource() == bouton3) {
				// nbJoueur = 3;
				this.dispose();
				FenetreJeu jeu = new FenetreJeu(3);
				jeu.setVisible(true);
				}

			
			if(e.getSource() == bouton4) {
				// nbJoueur = 4;
				this.dispose();
				FenetreJeu jeu = new FenetreJeu(4);
				jeu.setVisible(true);
				
				}
			
		}
  
}
