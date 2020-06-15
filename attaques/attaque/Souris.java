package fr.pierrehb.attaques.attaque;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Particle;
import fr.pierrehb.main.Main;

public abstract class Souris {
	public Entity joueur;
	protected int[] rez = Main.save.size;
	public float angle;
	
	public Souris(Entity joueur) {
	this.joueur = joueur;
	}
public abstract void update();
public Entity getEntity() {
	return joueur;
}
public abstract float getAngle();
public abstract void setTarget(Entity Target);
public abstract void setTarget(Particle Target);


public static float getAngle(float X, float Y) {
	float D = (float)Math.sqrt(X*X+Y*Y);

	if(D==0)return 0.0f;
	float Angle = (float)Math.acos(Math.abs(X)/D);
	if(X<0) Angle=(float)(Math.PI-Angle);

	if(Y<0) Angle = -Angle;
	return Angle;
}
}
