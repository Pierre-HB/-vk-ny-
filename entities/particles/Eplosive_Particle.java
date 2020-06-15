package fr.pierrehb.entities.particles;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Mortal_Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Pixel;

public class Eplosive_Particle extends Mortal_Particle{
	private Pixel[] pixels = new Pixel[44];
	int damage = 5;


	public Eplosive_Particle(int X, int Y, Entity joueur) {
		super(X, Y, 20, joueur, 6);
		float angle = (float)random(-31415,31415)/10000;
		float distance = (float)random(5, 15)/20;
		this.speed[0] = (float)Math.cos(angle)*distance;
		this.speed[1] = (float)Math.sin(angle)*distance;
		ini();
	}
	public Eplosive_Particle(int X, int Y, float[] Speed, Entity joueur) {
		super(X, Y, 20, joueur, 6);
		this.speed[0] = Speed[0];
		this.speed[1] = Speed[1];
		ini();
	}
	public Eplosive_Particle(int X, int Y, float[] Speed, Entity joueur, int Damage) {
		super(X, Y, 20, joueur, 6);
		this.speed[0] = Speed[0];
		this.speed[1] = Speed[1];
		this.damage = Damage;
		ini();
	}
	private void ini() {
		iniPixel();
		hitBox[0] = (int)x;
		hitBox[1] = (int)y;
		hitBox[2] = (int)x+8;
		hitBox[3] = (int)y+8;
		p1 = new Point(hitBox[0], hitBox[1]);
		p2 = new Point(hitBox[2], hitBox[1]);
		p3 = new Point(hitBox[2], hitBox[3]);
		p4 = new Point(hitBox[0], hitBox[3]);
		square = new Quadrilataire(p1, p2, p3, p4);


	}
	@Override
	protected void trueUpdate() {
		for(Pixel pixel : pixels) {
			pixel.increaseX(speed[0]);
			pixel.increaseY(speed[1]);
		}
		p1.x += speed[0];
		p2.x += speed[0];
		p3.x += speed[0];
		p4.x += speed[0];
		p1.y += speed[1];
		p2.y += speed[1];
		p3.y += speed[1];
		p4.y += speed[1];

		
		square = new Quadrilataire(p1, p2, p3, p4);
		hitBox[0] = (int)x;
		hitBox[1] = (int)y;
		hitBox[2] = (int)x+8;
		hitBox[3] = (int)y+8;
		if(Game.level.giveDamageSquare(square, joueur, damage));


	}

	@Override
	protected void trueRender() {
		for(Pixel pixel : pixels)pixel.render();
	}
	private float[] getColor(int X, int Y) {
		float[] color = {0,0,0,1.0f};
		if(((x == 3 || x == 4) && y >=2 && y <= 5) || ((y == 3 || y == 4) && x >=2 && x <= 5)) {
			color[0] = (float)random(100,255)/255.0f;
			color[1] = (float)random(0,50)/255.0f;
			color[2] = (float)random(0,50)/255.0f;
		}else {
			color[0] = 1.0f;
			color[1] = (float)random(114,192)/255.0f;
			color[2] = (float)random(20,86)/255.0f;
		}
		
		
		return color;
	}
	private void iniPixel() {
		int w = 0;
		for(int u = 0; u < 6; u++) {
			for(int v = 0; v < 6; v++) {
				pixels[w] = new Pixel(1+u+x,1+v+y,getColor(1+u,1+v));
				w++;
			}}
		pixels[36] = new Pixel(3+x,0+y,getColor(3,0));
		pixels[37] = new Pixel(4+x,0+y,getColor(4,0));
		pixels[38] = new Pixel(0+x,3+y,getColor(0,3));
		pixels[39] = new Pixel(0+x,4+y,getColor(0,4));
		pixels[40] = new Pixel(3+x,7+y,getColor(3,7));
		pixels[41] = new Pixel(4+x,7+y,getColor(4,7));
		pixels[42] = new Pixel(7+x,3+y,getColor(7,3));
		pixels[43] = new Pixel(7+x,4+y,getColor(7,4));
		

	}
	@Override
	protected void mortalEffect() {
		
	}

}
