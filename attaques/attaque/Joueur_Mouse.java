package fr.pierrehb.attaques.attaque;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Particle;

public class Joueur_Mouse extends Souris {
	public Entity target;
	private Particle ptarget;
	private boolean entity = false;
	private boolean particle = false;

	public Joueur_Mouse(Entity joueur) {
		super(joueur);
		target = joueur;
	}

	@Override
	public void update() {
		
	}

	@Override
	public float getAngle() {
		if(entity) {
			return getAngle((target.getCoord()[0]-joueur.getCoord()[0]), (-target.getCoord()[1]+joueur.getCoord()[1]));
		}
		if(particle) {

			return getAngle((ptarget.x-joueur.x), (-ptarget.y+joueur.y));
		}
		return 0.0f;

	}

	@Override
	public void setTarget(Entity Target) {
		target = Target;
		entity = true;
		particle = false;
	}
	@Override
	public void setTarget(Particle Target) {
		ptarget = Target;
		particle = true;
		entity = false;
	}

}
