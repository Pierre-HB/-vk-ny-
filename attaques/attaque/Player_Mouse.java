package fr.pierrehb.attaques.attaque;

import org.lwjgl.input.Mouse;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Human;
import fr.pierrehb.entities.Particle;

public class Player_Mouse extends Souris{

	public Player_Mouse(Human joueur) {
		super(joueur);
	}

	@Override
	public void update() {
		angle = getAngle(-128+Mouse.getX()*256/rez[0],-72+Mouse.getY()*144/rez[1]);
	}

	@Override
	public float getAngle() {return angle;}

	@Override
	public void setTarget(Entity Target) {}

	@Override
	public void setTarget(Particle Target) {
		
	}

}
