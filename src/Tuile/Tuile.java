package Tuile;

import java.util.ArrayList;

import Executable.Plateau;

public class Tuile {
	
	private String type;
	private String couleur;
	private int x;
	private int y;
	private int valueTableau;
	

	
	/*
	 *Constructor
	 */
	public Tuile(String type, String couleur,int x,int y,int valueTableau) {
		
		this.type = type;
		this.couleur = couleur;
		this.x=x;
		this.y=y;
		this.valueTableau=valueTableau;
		
	}
	/**
	 * Getter
	 * @return
	 */
	public String getTypeTuile() {
		return this.type;
	}
	
	public int getValueTableau() {
		return valueTableau;
	}
	public void setValueTableau(int valueTableau) {
		this.valueTableau = valueTableau;
	}
	public String getcouleur() {
		return couleur;
	}
	public int getx() {
		return this.x;
	}
	public int gety() {
		return this.y;
	}
	
	/**
	 * setter
	 * @return
	 */
	public void setx(int x1) {
		this.x=x1;
		
	}
	public void sety(int y1) {
		this.y=y1;
	}
}
