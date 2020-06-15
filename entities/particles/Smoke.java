package fr.pierrehb.entities.particles;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.graphic.Pixel;

public class Smoke extends Particle{
	private float angle = (float)(Math.random()*2*Math.PI);
	private float distance = (float)(Math.random()/2);
	private Pixel[] pixels = new Pixel[5];
	private int[] minColor1 = {200, 200, 200};
	private int[] maxColor1 = {255, 255, 255};
	private int[] minColor2 = {200, 200, 200};
	private int[] maxColor2 = {255, 255, 255};
	private boolean haveSpeed = false;

	
	public Smoke(int X, int Y, int TimeLife, Entity Joueur) {
		super(X, Y, TimeLife, Joueur);
		ini();		
	}
	public Smoke(int X, int Y, int TimeLife, Entity Joueur, int[] MinColor1, int[] MaxColor1, int[] MinColor2, int[] MaxColor2) {
		super(X, Y, TimeLife, Joueur);
		for(int a = 0; a < 3; a++) {
			this.minColor1[a] = MinColor1[a];
			this.maxColor1[a] = MaxColor1[a];
			this.minColor2[a] = MinColor2[a];
			this.maxColor2[a] = MaxColor2[a];

		}
		ini();		
	}
	public Smoke(int X, int Y, int TimeLife,float Distance, Entity Joueur, int[] MinColor1, int[] MaxColor1, int[] MinColor2, int[] MaxColor2) {
		super(X, Y, TimeLife, Joueur);
		for(int a = 0; a < 3; a++) {
			this.minColor1[a] = MinColor1[a];
			this.maxColor1[a] = MaxColor1[a];
			this.minColor2[a] = MinColor2[a];
			this.maxColor2[a] = MaxColor2[a];

		}
		this.distance = Distance;
		ini();		
	}
	public Smoke(int X, int Y, int TimeLife, int StartTime, float[] Speed, Entity Joueur, int[] MinColor1, int[] MaxColor1, int[] MinColor2, int[] MaxColor2) {
		super(X, Y, TimeLife, Joueur);
		haveSpeed = true;
		for(int a = 0; a < 3; a++) {
			this.minColor1[a] = MinColor1[a];
			this.maxColor1[a] = MaxColor1[a];
			this.minColor2[a] = MinColor2[a];
			this.maxColor2[a] = MaxColor2[a];
		}
		this.speed = Speed;
		this.startTime = StartTime;
		ini();		
	}
	public Smoke(int X, int Y) {
		super(X, Y, 30, Game.getPlayer());
		ini();		
	}

	@Override
	protected void trueUpdate() {
		if(!haveSpeed) {
		x += Math.cos(angle)*distance;
		y -= Math.sin(angle)*distance;
		

		for(Pixel pixel : pixels) {			
			pixel.x += Math.cos(angle)*distance;
			pixel.y -= Math.sin(angle)*distance;
		}}else{
			for(Pixel pixel : pixels) {			
				pixel.x += speed[0];
				pixel.y -= speed[1];
			}
		}

	}

	@Override
	protected void trueRender() {
		for(Pixel pixel : pixels) pixel.render();		
	}
	private void ini() {
		
			pixels[0] = new Pixel(x+1, y, new float[]{(float)random(minColor1[0], maxColor1[0])/255, (float)random(minColor1[1], maxColor1[1])/255, (float)random(minColor1[2], maxColor1[2])/255, 1.0f});
			pixels[1] = new Pixel(x, y+1, new float[]{(float)random(minColor1[0], maxColor1[0])/255, (float)random(minColor1[1], maxColor1[1])/255, (float)random(minColor1[2], maxColor1[2])/255, 1.0f});
			pixels[2] = new Pixel(x+1, y+1, new float[]{(float)random(minColor2[0], maxColor2[0])/255, (float)random(minColor2[1], maxColor2[1])/255, (float)random(minColor2[2], maxColor2[2])/255, 1.0f});
			pixels[3] = new Pixel(x+2, y+1, new float[]{(float)random(minColor1[0], maxColor1[0])/255, (float)random(minColor1[1], maxColor1[1])/255, (float)random(minColor1[2], maxColor1[2])/255, 1.0f});
			pixels[4] = new Pixel(x+1, y+2, new float[]{(float)random(minColor1[0], maxColor1[0])/255, (float)random(minColor1[1], maxColor1[1])/255, (float)random(minColor1[2], maxColor1[2])/255, 1.0f});

		
	}
	@Override
	protected void mortalEffect() {
		
	}

}
