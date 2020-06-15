package fr.pierrehb.graphic;

import fr.pierrehb.other.Caracteres;

public abstract class Caractere implements Caracteres{
	protected int[] coord = new int[2];
	protected float taille;
	protected caractere chara;
	protected boolean color;
	protected int offset;
	protected int xStart = 0;
	protected int xIni = 0;
	protected int yIni = 0;

	
	public Caractere(int x, int y, float Taille, caractere Chara, int xStart) {
		this.coord[0] = x;
		this.coord[1] = y;
		this.taille = Taille;
		this.chara = Chara;
		this.xStart = xStart;

		
	}
	public Caractere(int x, int y, float Taille, caractere Chara) {
		this.coord[0] = x;
		this.coord[1] = y;
		this.taille = Taille;
		this.chara = Chara;

		
	}
	public abstract int[] getLetter();
	public float getTaille() {
		return taille;
	}
	public int[] getCoord() {
		return coord;
	}
	public boolean getColor() {
		return color;
	}
	public abstract int getOffset() ;
	public void setCoord(int xini, int yini) {
		this.xIni = xini;
		this.yIni = yini;
	}
	public int getxIni() {
		return xIni;
	}
	public int getyIni() {
		return yIni;
	}
}
