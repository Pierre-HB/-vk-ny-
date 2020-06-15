package fr.pierrehb.entities;

import fr.pierrehb.game.Game;
import fr.pierrehb.main.Main;
import fr.pierrehb.other.Color;

public abstract class Particle implements Color{
	protected float[] speed = {0.0f, 0.0f};
	public int id;
	public float x;
	public float y;
	protected int timeLife;
	protected int startTime = 0;
	protected int timeCounter = 0;
	protected int startCounter = 0;

	public Entity joueur;
	public int[] hitBox = {0,0,0,0};
	public boolean mortal = false;
	
	public Particle(int X, int Y, int TimeLife, Entity Joueur) {
		this.timeLife = TimeLife;
		this.x = X;
		this.y = Y;
		this.id = Main.getParticleID();
		this.joueur = Joueur;
	}
	public void update() {
		if(startCounter >= startTime) {
		x += speed[0];
		y += speed[1];
		
		trueUpdate();
		timeCounter++;
		if(timeCounter > timeLife)die();
	}else
		startCounter++;
	}
	public void render() {
		if(startCounter >= startTime)trueRender();
	}
	protected abstract void trueUpdate();
	protected abstract void trueRender();
	public void die() {
		Game.level.removeParticles.add(id);
		mortalEffect();
	}
	protected int random(int min, int max) {
		return (int)(Math.random()*(max-min)+min);
	}
	protected abstract void mortalEffect();
	
}
