package Tuile;

public class Pion extends Tuile {
	 String direction;
	 int xspawn;
	 int yspawn;

		public Pion(String type, String couleur, int x , int y,int valueTableau,String direction,int xspawn,int yspawn) {
			super("PION",couleur,x,y,valueTableau);
			this.direction=direction;
		
		}
	
	
	
	
	public int getXspawn() {
			return xspawn;
		}




		public void setXspawn(int xspawn) {
			this.xspawn = xspawn;
		}




		public int getYspawn() {
			return yspawn;
		}




		public void setYspawn(int yspawn) {
			this.yspawn = yspawn;
		}




	public void setxspawn(int x1) {
		this.xspawn=x1;
		
	}
	public void setyspawn(int y1) {
		this.yspawn=y1;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}

}
