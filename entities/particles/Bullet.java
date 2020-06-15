package fr.pierrehb.entities.particles;

import fr.pierrehb.entities.Entity;
import fr.pierrehb.entities.Mortal_Particle;
import fr.pierrehb.game.Game;
import fr.pierrehb.geometrie.Point;
import fr.pierrehb.geometrie.Quadrilataire;
import fr.pierrehb.graphic.Renderer;
import fr.pierrehb.graphic.Texture;
import fr.pierrehb.main.Main;
import fr.pierrehb.sound.Sound_lector;

public class Bullet extends Mortal_Particle{
	
	private float angle;
	private float celerity = 3.0f;
	int damage = 10;
	private float longDistance = 12.64911064f;
	private int smallDistance = 4;
	private float litleAngle = 0.3217505544f;
	private Texture texture = Texture.loadTexture("res/textures/entity/Bullet.png");
	private float[] nb_texture = {1.0f, 1.0f};
	private int[] minColor1 = {0, 0, 0};
	private int[] maxColor1 = {50, 50, 50};
	private int[] minColor2 = {50, 50, 50};
	private int[] maxColor2 = {60, 60, 60};
	private static Sound_lector sound;



	public Bullet(int X, int Y,int TimeLife, float Angle, Entity joueur) {
		super(X, Y, TimeLife, joueur, 1);
		this.angle = Angle;
		ini();
	}
	public Bullet(int X, int Y, float Angle, Entity joueur) {
		super(X, Y, 60, joueur, 1);
		this.angle = Angle;
		ini();
	}
	public Bullet(int X, int Y, float Angle, float Celerity, Entity joueur) {
		super(X, Y, 60, joueur, 1);
		this.celerity = Celerity;
		this.angle = Angle;
		ini();
	}
	public Bullet(int X, int Y, float Angle, float Celerity, Entity joueur, int Damage) {
		super(X, Y, 60, joueur, 1);
		this.celerity = Celerity;
		this.angle = Angle;
		this.damage = Damage;
		ini();
	}
	private void ini() {
		speed[0] = (float)Math.cos(angle)*celerity;
		speed[1] = (float)Math.sin(angle)*celerity;
		x1 = x+(float)Math.cos(angle+Math.PI/2)*smallDistance;
		x2 = x+(float)Math.cos(angle+litleAngle)*longDistance;
		x3 = x+(float)Math.cos(angle-litleAngle)*longDistance;
		x4 = x+(float)Math.cos(angle-Math.PI/2)*smallDistance;
		y1 = y+(float)Math.sin(angle+Math.PI/2)*smallDistance;
		y2 = y+(float)Math.sin(angle+litleAngle)*longDistance;
		y3 = y+(float)Math.sin(angle-litleAngle)*longDistance;
		y4 = y+(float)Math.sin(angle-Math.PI/2)*smallDistance;
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
		p3 = new Point(x3, y3);
		p4 = new Point(x4, y4);
		square = new Quadrilataire(p1, p2, p3, p4);




	}
	@Override
	protected void trueUpdate() {
	
		
		p1.x += speed[0];
		p2.x += speed[0];
		p3.x += speed[0];
		p4.x += speed[0];
		p1.y += speed[1];
		p2.y += speed[1];
		p3.y += speed[1];
		p4.y += speed[1];


		
		square = new Quadrilataire(p1, p2, p3, p4);

		if(Game.level.giveDamageSquare(square, joueur, damage))die();
		if(HP <= 0)die();
	}

	@Override
	protected void trueRender() {
	Renderer.renderPixel(p1, p2, p3, p4, texture, 0, 0, nb_texture, WHITE);
	}
	@Override
	protected void mortalEffect() {
		sound = new Sound_lector("res/sound/sou/objects/explosion2.wav", Main.save.soundVolume);
		sound.start();
		for(int a = 0; a < 10; a++) {
		Game.level.summonParticle(new Eplosive_Particle((int)x, (int)y, joueur));	
		}	
		for(int a = 0; a < 50; a++) {
			Game.level.summonParticle(new Smoke((int)x, (int)y, 35, Game.getPlayer(), minColor1, maxColor1, minColor2, maxColor2));	

		}

	}

	

}