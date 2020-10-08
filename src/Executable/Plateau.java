package Executable;

import java.util.ArrayList;

import Carte.Joueur;
import Tuile.Joyaux;
import Tuile.Pion;
import Tuile.Tuile;
import Tuile.Obstacle;

public class Plateau extends Jeu {


	private static final int taille = 8;

	public int [][] plateau;
    protected int nombreJoueur;
	static int caseVide = 0;
	static int caseAvecJoyauxBleu = 2;
	static int caseAvecJoyauxViolet = 3;
	static int caseAvecJoyauxVert = 4;
	static int caseAvecJoyauxRouge = 5;
	static int caseAvecMurPierre = 6;
	static int caseAvecMurGlace = 7;
	static int caseAvecTortueBleu = 8;
	static int caseAvecTortueViolette = 9;
	static int caseAvecTortueVerte = 10;
	static int caseAvecTortueRouge = 11;
	Pion TortueBleu= new Pion("PION","BLEU",0,0,caseAvecTortueBleu,"S",0,0);
	Pion TortueViolette= new Pion("PION","VIOLETTE",0,2,caseAvecTortueViolette,"S",0,2);
	Pion TortueVerte= new Pion("PION","VERTE",0,5,caseAvecTortueVerte,"S",0,5);
	Pion TortueRouge= new Pion("PION","ROUGE",0,7,caseAvecTortueRouge,"S",0,7);
	Joyaux JoyauxBleu= new Joyaux("JOYAUX","BLEU",7,1,caseAvecJoyauxBleu);	
	Joyaux JoyauxViolet= new Joyaux("JOYAUX","VIOLET",7,6,caseAvecJoyauxViolet);
	Joyaux JoyauxVert= new Joyaux("JOYAUX","VERT",7,6,caseAvecJoyauxRouge);
	String[] Direction = new String[4];
	
	
    
	public Plateau(int nombreJoueur) {
		this.nombreJoueur=nombreJoueur;
		plateau = new int[8][8];
		initialiserPlateau();
		
		
	}

	public void initialiserPlateau() {
		Direction[0]="N";
		Direction[1]="E";
		Direction[2]="S";
		Direction[3]="O";
		

		if (nombreJoueur == 2) {
			for (int i=0; i<taille; i++) {
				for (int j=0; j<taille; j++) {
					plateau[i][j] = caseVide;		
				}
			}
			for (int k=0; k<8; k++) {
				plateau[k][7] =  caseAvecMurPierre;
			}
			plateau[0][1] = caseAvecTortueBleu;
			
			TortueBleu.setx(0);
			TortueBleu.sety(1);
			TortueBleu.setxspawn(0);
			TortueBleu.setyspawn(1);
			plateau[0][5] = caseAvecTortueViolette;
			
			TortueViolette.setx(0);
			TortueViolette.sety(5);
			TortueViolette.setxspawn(0);
			TortueViolette.setyspawn(5);
			plateau[7][3] = caseAvecJoyauxBleu;
			
			JoyauxBleu.setx(7);
			JoyauxBleu.sety(3);
		}


		if (nombreJoueur== 3) {
			
			for (int i=0; i<8; i++) {
				for (int j=0; j<7; j++) {
					plateau[i][j] = caseVide;		
				}
			}
			for (int k=0; k<8; k++) {
				plateau[k][7] =  caseAvecMurPierre;
			}
			plateau[0][0] = caseAvecTortueBleu;
			
			TortueBleu.setx(0);
			TortueBleu.sety(0);
			TortueBleu.setxspawn(0);
			TortueBleu.setyspawn(0);
			plateau[0][3] = caseAvecTortueViolette;
			
			TortueViolette.setx(0);
			TortueViolette.sety(3);
			TortueViolette.setxspawn(0);
			TortueViolette.setyspawn(3);
			plateau[0][6] = caseAvecTortueVerte;
			
			TortueVerte.setx(0);
			TortueVerte.sety(6);
			TortueVerte.setxspawn(0);
			TortueVerte.setyspawn(6);
			plateau[7][0] = caseAvecJoyauxBleu;
			
			JoyauxBleu.setx(7);
			JoyauxBleu.sety(0);
			
			plateau[7][3] = caseAvecJoyauxViolet;
			
			JoyauxViolet.setx(7);
			JoyauxViolet.sety(3);
			plateau[7][6] = caseAvecJoyauxVert;
			
			JoyauxVert.setx(7);
			JoyauxVert.sety(6);

		}
		if (nombreJoueur== 4) {
			for (int i=0; i<taille; i++) {
				for (int j=0; j<7; j++) {
					plateau[i][j] = caseVide;		
				}
			}
			plateau[0][0] = caseAvecTortueBleu;
			
			TortueBleu.setx(0);
			TortueBleu.sety(0);
			TortueBleu.setxspawn(0);
			TortueBleu.setyspawn(0);
			
			plateau[0][7] = caseAvecTortueViolette;
			
			TortueViolette.setx(0);
			TortueViolette.sety(7);
			TortueViolette.setxspawn(0);
			TortueViolette.setyspawn(7);
			
			
			plateau[0][5] = caseAvecTortueVerte;
			
			TortueVerte.setx(0);
			TortueVerte.sety(5);
			TortueVerte.setxspawn(0);
			TortueVerte.setyspawn(5);
			plateau[0][7] = caseAvecTortueRouge;
			
			TortueRouge.setx(0);
			TortueRouge.sety(7);
			TortueRouge.setxspawn(0);
			TortueRouge.setyspawn(7);
			plateau[7][1] = caseAvecJoyauxBleu;
			
			JoyauxBleu.setx(7);
			JoyauxBleu.sety(1);
			plateau[7][6] = caseAvecJoyauxViolet;
			JoyauxViolet.setx(7);
			JoyauxViolet.sety(6);
		}

	}
	public boolean isFull(int x, int y) {
		if (plateau[x][y] == 0) {
			return false;
		}
		else {
			return true;
		}		
	}
	
	public void afficherPlateau() {
		
		for (int y = 0; y < plateau.length; ++y) {
			for (int x = 0; x < plateau[y].length; ++x) {
				System.out.print(plateau[y][x]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void setPlateau(int typeObject,int x, int y ) {	
		
		System.out.println(typeObject);
	   if(typeObject>7) {
		   
		   if(typeObject==8) {
			   
			   plateau[x][y] = 8;
			   TortueBleu.setx(x);
			   TortueBleu.sety(y);
		   }
		   else if(typeObject==9) {
			   
        	   plateau[x][y] = 9;
        	   TortueViolette.setx(x);
			   TortueViolette.sety(y);
			   
		   }
		   else if(typeObject==10) {
			   
        	   plateau[x][y] = 10;
        	   TortueVerte.setx(x);
			   TortueVerte.sety(y);
	   
           }
		   else if(typeObject==11) {
			   
        	   plateau[x][y] = 11;
        	   TortueRouge.setx(x);
			   TortueRouge.sety(y);
	   
           }
		   
		   
	   }
       if(typeObject<8) {
    	  
           if(typeObject==7) {
        	  
        	   Obstacle MurGlace=new Obstacle("Glace",x,y,7);
        	   plateau[x][y] = caseAvecMurGlace;
        	   
        	   
			   
		   }
           if(typeObject==6) {
        	   Obstacle MurPierre=new Obstacle("Pierre",x,y,6);
        	   plateau[x][y] = caseAvecMurPierre;
			   
		   }
		   
		   
	   }
     
    	 
    	 
    	 
     } 
		
	public String changeDirection(String LastDir,boolean rotatViolette) {	
			int Dir1=0;
			for(int i=0  ; i<Direction.length;i++){
				if(LastDir==Direction[i]) {
					Dir1=i;
				}  
			}
			if(rotatViolette==true) {
			Dir1=Dir1+1;}
			else{
				Dir1=Dir1-1;
			}
			if(Dir1>3) {
				Dir1=0;
			}
			if(Dir1<0) {
				Dir1=3;
			}
			return Direction[Dir1];
	}
	
	public boolean testDep(int nouvX,int nouvY){       //test si il peut avancer 
		if(plateau[nouvX][nouvY]>5) { 
			return false;
		}
		return true;
		}
	public boolean testGagne(int nouvX,int nouvY){  
		System.out.println("yo");//test si il gagne 
		if((plateau[nouvX][nouvY]>1)&& (plateau[nouvX][nouvY]<6)) { 
			System.out.println("you");
			return true;
			
		}
		else {
		return false;
		}
		}
	public boolean testPion(int nouvX,int nouvY){       //test si deux tortue se superposent
		if((plateau[nouvX][nouvY])>8){ 
			return true;
		}
		return false;
		}	
	public boolean testSortTableau(int nouvX,int nouvY){       //test si la tortue sort du terrain 
		if((nouvX<0)&&(nouvX>7)){ 
			return true;
		}
		if((nouvY<0)&&(nouvY>7)){ 
			return true;
		}
		return false;
		}		
	public int Avancer(String Direction,int value,int x,int y,int xs, int ys) {
		int y1=y;
		int x1=x;
		int Gagne=0;
		
		if(Direction=="N") {
			x=x-1;
				
		}
		else if(Direction=="E") {
			y=y+1;
			
		}
		else if(Direction=="S") {
			x=x+1;
			
		}
		else if(Direction=="O") {
			y=y-1;
			
        
		}  
       
        
        
         if(testSortTableau(x, y)) {
			 plateau[x1][y1]=0;
			 setPlateau(value,xs,ys);
			 switch(value) {
				case 8:
					TortueBleu.setDirection("S");
				case 10:
					TortueVerte.setDirection("S");
				case 11:
					TortueRouge.setDirection("S");
				case 9:
					TortueViolette.setDirection("S");
					
				}
			
		 }
         else if(testDep(x,y)){
			 setPlateau(value,x, y);
			 plateau[x1][y1]=0;
		 }
	     else if(testGagne(x,y)){
			 
			 plateau[x1][y1]=0;
			 Gagne=1;
			 
		 }
		
		 else if(testPion(x, y)) {
			 plateau[x1][y1]=0;
			 setPlateau(value,xs,ys);
			 switch(value) {
				case 8:
					TortueBleu.setDirection("S");
				case 10:
					TortueVerte.setDirection("S");
				case 11:
					TortueRouge.setDirection("S");
				case 9:
					TortueViolette.setDirection("S");
					
				}
			 
			 int ValueTortue=plateau[x][y];
			 plateau[x][y]=0;
			 switch(ValueTortue) {
				case 8:
					setPlateau(caseAvecTortueBleu,TortueBleu.getXspawn(),TortueBleu.getYspawn());
					TortueBleu.setDirection("S");
				case 10:
					setPlateau(caseAvecTortueVerte,TortueVerte.getXspawn(),TortueVerte.getYspawn());
					TortueVerte.setDirection("S");
				case 11:
					setPlateau(caseAvecTortueRouge,TortueRouge.getXspawn(),TortueRouge.getYspawn());
					TortueRouge.setDirection("S");
				case 9:
					setPlateau(caseAvecTortueViolette,TortueViolette.getXspawn(),TortueViolette.getYspawn());
					TortueViolette.setDirection("S");
					
				}
			 }
        return Gagne;
			
		}
    public void tireLaser(String Direction,int value,int x,int y,int xs, int ys) {
    	int x1=x;
    	int y1=y;	
    	for(int i=0;i<8;i++) {
    	if(testSortTableau(x,y)) {
    		
    		
    	
    	if(Direction=="N") {
			x=x-1;
				
		}
		else if(Direction=="E") {
			y=y+1;
			
		}
		else if(Direction=="S") {
			x=x+1;
			
		}
		else if(Direction=="O") {
			y=y-1;
			
        
		} 
    	
    	if(plateau[x][y]==7) {
    		 plateau[x1][y1]=0;
			 setPlateau(value,xs,ys);
			 switch(value) {
				case 8:
					TortueBleu.setDirection("S");
				case 10:
					TortueVerte.setDirection("S");
				case 11:
					TortueRouge.setDirection("S");
				case 9:
					TortueViolette.setDirection("S");
					
				}
    		
    	}
        if(plateau[x][y]>7) {
        	 switch(plateau[x][y]) {
				case 8:
					plateau[TortueBleu.getXspawn()][TortueBleu.getYspawn()]=8;
					plateau[x][y]=0;
					TortueBleu.setDirection("S");
				case 10:
					plateau[TortueVerte.getXspawn()][TortueVerte.getYspawn()]=10;
					plateau[x][y]=0;
					TortueVerte.setDirection("S");
				case 11:
					plateau[TortueRouge.getXspawn()][TortueRouge.getYspawn()]=11;
					plateau[x][y]=0;
					TortueRouge.setDirection("S");
				case 9:
					plateau[TortueViolette.getXspawn()][TortueViolette.getYspawn()]=9;
					plateau[x][y]=0;
					TortueViolette.setDirection("S");
        	 }
				
        	
    		
    		
    	
  
    	
    	
    }}}}
		
		
		
	
	
	public Pion getTortueBleu() {
		return TortueBleu;
	}
	public void setTortueBleu(Pion tortueBleu) {
		TortueBleu = tortueBleu;
	}
	public Pion getTortueViolette() {
		return TortueViolette;
	}
	public void setTortueViolette(Pion tortueViolette) {
		TortueViolette = tortueViolette;
	}
	public Pion getTortueVerte() {
		return TortueVerte;
	}
	public void setTortueVerte(Pion tortueVerte) {
		TortueVerte = tortueVerte;
	}
	public Pion getTortueRouge() {
		return TortueRouge;
	}
	public void setTortueRouge(Pion tortueRouge) {
		TortueRouge = tortueRouge;
	}
	/**
	 * Getter
	 */
	public int[][] getPlateau() {
		return plateau;
		}
		
	
}
