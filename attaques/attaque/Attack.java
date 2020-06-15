package fr.pierrehb.attaques.attaque;

import fr.pierrehb.entities.Human;

public abstract class Attack {
	public Human joueur;
	public boolean done = false;
	public int reload = 1;
	public float xo = 0;
	public float yo = 4;
	public int level;
	protected int timeToDone = 0;
	protected int doneCount = 0;
	protected int damageHeal = 0;
	
	public Attack(Human Joueur, int Level) {
		this.joueur = Joueur;
		this.level = Level;
	}
	public abstract void render();
	public void update() {
		if(!done) {
			doneCount++;
			if(doneCount>timeToDone) {
				doneCount = 0;
				done = true;
			}
		}
		trueUpdate();
	}
	public abstract void trueUpdate();
	public abstract void renderAttack();
	public abstract void startAttack();
	public abstract void updatePreparing();
	public abstract void stopAttack();
	
}
