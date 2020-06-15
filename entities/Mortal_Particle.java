package fr.pierrehb.entities;

import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;

public abstract class Mortal_Particle extends Particle {
	protected int HP;
	public Point p1, p2, p3, p4;
	public Quadrilataire square;
	public float x1, x2, x3, x4, y1, y2, y3, y4;


	public Mortal_Particle(int X, int Y, int TimeLife, Entity Joueur, int hp) {
		super(X, Y, TimeLife, Joueur);
		this.HP = hp;
		mortal = true;
	}
	public void getDamage(int damage) {
		if(damage > 0) HP -= damage;
		
	}

}
